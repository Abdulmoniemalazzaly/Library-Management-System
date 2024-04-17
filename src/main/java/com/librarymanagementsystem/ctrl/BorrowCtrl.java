package com.librarymanagementsystem.ctrl;

import com.librarymanagementsystem.model.BorrowingRecord;
import com.librarymanagementsystem.service.BorrowingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
@Tag(name = "Borrow Ctrl" , description = "The borrowing management controller")
public class BorrowCtrl {

    private final BorrowingService borrowingService;

    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable(name = "bookId") Long bookId ,
                                                     @PathVariable(name = "patronId") Long patronId ){
        borrowingService.borrowBook(bookId , patronId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable(name = "bookId") Long bookId ,
                                                     @PathVariable(name = "patronId") Long patronId ){
        borrowingService.returnBook(bookId , patronId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
