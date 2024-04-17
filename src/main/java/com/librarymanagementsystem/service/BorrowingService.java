package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.BorrowingRecord;
import com.librarymanagementsystem.model.Patron;
import com.librarymanagementsystem.repo.BookRepo;
import com.librarymanagementsystem.repo.BorrowingRecordRepo;
import com.librarymanagementsystem.repo.PatronRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Service
public class BorrowingService {

    private final BorrowingRecordRepo borrowingRecordRepo;
    private final BookRepo bookRepo;
    private final PatronRepo patronRepo;

    public BorrowingRecord borrowBook(Long bookId , Long patronId){
        final Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new RuntimeException("There is no such book id!"));
        final Patron patron = patronRepo.findById(patronId)
                .orElseThrow(() -> new RuntimeException("There is no such patron id!"));
        BorrowingRecord borrowingRecord = BorrowingRecord.builder()
                .book(book)
                .patron(patron)
                .borrowingDate(new Timestamp(System.currentTimeMillis()))
                .build();
        return borrowingRecordRepo.save(borrowingRecord);
    }

    public BorrowingRecord returnBook(Long bookId , Long patronId){
        BorrowingRecord borrowingRecord = borrowingRecordRepo.findByBookIdAndPatronId(bookId , patronId)
                .orElseThrow(() -> new RuntimeException("Invalid book id or patron id"));
        borrowingRecord.setReturnDate(new Timestamp(System.currentTimeMillis()));
        return borrowingRecordRepo.save(borrowingRecord);
    }
}
