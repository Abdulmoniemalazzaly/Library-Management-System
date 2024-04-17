package com.librarymanagementsystem.book;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class BookTest {

    @Autowired
    private BookRepo bookRepo;

    @Test
    public void testFindAll(){
        List<Book> books = bookRepo.findAll();
        assertThat(books).isNotEqualTo(null);
    }

    @Test
    public void testFindById(){
        Book book = bookRepo.findById(2L).orElse(null);
        assertThat(book).isNotEqualTo(null);
    }

    @Test
    public void testDelete(){
        bookRepo.deleteById(2L);
        Book book = bookRepo.findById(2L).orElse(null);
        assertThat(book).isEqualTo(null);
    }

    @Test
    public void testUpdate(){
        Book book = bookRepo.findById(2L).orElse(null);
        book.setTitle("New Title");
        bookRepo.save(book);
        Book updatedBook = bookRepo.findById(2L).orElse(null);
        assertThat(updatedBook.getTitle()).isEqualTo("New Title");
    }
}
