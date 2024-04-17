package com.librarymanagementsystem.service;

import com.librarymanagementsystem.enums.TokenType;
import com.librarymanagementsystem.model.Token;
import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.repo.TokenRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepo tokenRepo;

    public List<Token> findAllValidTokensByUser(Long userId){
        return tokenRepo.findAllValidTokensByUser(userId);
    }

    @Cacheable(value = "caching")
    public Optional<Token> findByToken(String token){
        return tokenRepo.findByToken(token);
    }

    public void saveToken(final String token , final User user , final TokenType tokenType, HttpServletRequest request){
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        tokenRepo.save(Token.builder()
                .token(token)
                .user(user)
                .tokenType(tokenType)
                .userAgent(userAgent)
                .ipAddress(ip.getHostAddress())
                .remoteIpAddress(request.getRemoteAddr())
                .expired(false)
                .revoked(false)
                .build());
    }

    public void saveAll(List<Token> tokens) {
        tokenRepo.saveAll(tokens);
    }

    public void save(Token token) {
        tokenRepo.save(token);
    }
}
