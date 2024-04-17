package com.librarymanagementsystem.ctrl;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.service.GenericService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
@Tag(name = "Books Ctrl" , description = "The books management controller")
public class BookCtrl extends GenericCtrl<Book>{

    private final BookService bookService;

    @Override
    public GenericService<Book> getService() {
        return bookService;
    }
}
