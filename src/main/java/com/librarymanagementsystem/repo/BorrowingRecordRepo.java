package com.librarymanagementsystem.repo;

import com.librarymanagementsystem.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BorrowingRecordRepo extends JpaRepository<BorrowingRecord , Long> {
    @Query("""
        SELECT R FROM BorrowingRecord R WHERE R.book.id =:bookId AND R.patron.id =:patronId
    """)
    Optional<BorrowingRecord> findByBookIdAndPatronId(@Param("bookId") Long bookId , @Param("patronId") Long patronId);
}
