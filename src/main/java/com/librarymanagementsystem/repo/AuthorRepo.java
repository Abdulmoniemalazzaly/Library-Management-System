package com.librarymanagementsystem.repo;

import com.librarymanagementsystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author , Long> {
}
