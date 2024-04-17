package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService extends GenericService<Book>{

    private final BookRepo bookRepo;

    @Override
    public JpaRepository getRepo() {
        return bookRepo;
    }
}
