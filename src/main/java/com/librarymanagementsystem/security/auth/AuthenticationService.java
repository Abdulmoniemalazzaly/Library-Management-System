package com.librarymanagementsystem.security.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagementsystem.enums.TokenType;
import com.librarymanagementsystem.exception.UserNotFoundException;
import com.librarymanagementsystem.model.Token;
import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.security.JWT.JWTAuthenticationFilter;
import com.librarymanagementsystem.security.JWT.JWTService;
import com.librarymanagementsystem.service.TokenService;
import com.librarymanagementsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final HttpServletRequest request;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        final User savedUser = userService.saveUser(user);
        return saveTokenAndBuildResponse(savedUser);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        final User user = userService.getUser(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        return saveTokenAndBuildResponse(user);
    }

    private AuthenticationResponse saveTokenAndBuildResponse(final User user){
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user.getId());
        tokenService.saveToken(accessToken ,user  , TokenType.ACCESS, request);
        tokenService.saveToken(refreshToken ,user , TokenType.REFRESH , request);
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(Long userId){
        List<Token> tokens = tokenService.findAllValidTokensByUser(userId);
        if (!tokens.isEmpty()){
            tokens.forEach(token -> {
                token.setRevoked(true);
                token.setExpired(true);
            });
            tokenService.saveAll(tokens);
        }
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(JWTAuthenticationFilter.AUTHORIZATION_PREFIX))
            return;
        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            final User user = userService.getUser(username).orElseThrow(() -> new UserNotFoundException("The user doesn't exists!"));
            if (jwtService.isTokenValid(refreshToken , user)){
                final String accessToken = jwtService.generateToken(user);
                // revoke the user tokens and save token
                revokeAllUserTokens(user.getId());
                tokenService.saveToken(accessToken ,user  , TokenType.ACCESS, request);
                tokenService.saveToken(refreshToken ,user , TokenType.REFRESH , request);
                final AuthenticationResponse authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
