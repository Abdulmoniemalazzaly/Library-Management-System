package com.librarymanagementsystem.repo;

import com.librarymanagementsystem.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepo extends JpaRepository<Token, Integer> {

    @Query("""
        select t from Token t join User u on u.id = t.user.id
        where u.id =:userId and (t.expired = false and t.revoked = false )
    """)
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
