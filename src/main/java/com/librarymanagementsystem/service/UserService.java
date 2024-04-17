package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.User;
import com.librarymanagementsystem.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService {

    private final UserRepo userRepo;

    @Cacheable(value = "caching")
    public Optional<User> getUser(String username) {
        log.info("Fetching user {} " , username);
        Optional<User> user = userRepo.findByUsername(username);
        user.ifPresent(value -> log.info("User retrieved --- > {}", value.getUsername()));
        return user;
    }

    @Transactional
    @CachePut
    public User saveUser(User user) {
        log.info("saving user {} ." , user.getUsername());
        return userRepo.save(user);
    }
}
