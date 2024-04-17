package com.librarymanagementsystem.repo;

import com.librarymanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book , Long> {
}
