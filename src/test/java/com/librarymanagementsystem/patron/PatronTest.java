package com.librarymanagementsystem.patron;

import com.librarymanagementsystem.model.Book;
import com.librarymanagementsystem.model.Patron;
import com.librarymanagementsystem.repo.PatronRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback
public class PatronTest {

    @Autowired
    private PatronRepo patronRepo;

    @Test
    public void testFindAll(){
        List<Patron> patrons = patronRepo.findAll();
        assertThat(patrons).isNotEqualTo(null);
    }

    @Test
    public void testFindById(){
        Patron patron = patronRepo.findById(1L).orElse(null);
        assertThat(patron).isNotEqualTo(null);
    }

    @Test
    public void testDelete(){
        patronRepo.deleteById(1L);
        Patron patron = patronRepo.findById(1L).orElse(null);
        assertThat(patron).isEqualTo(null);
    }

    @Test
    public void testUpdate(){
        Patron patron = patronRepo.findById(1L).orElse(null);
        patron.setFirstName("New Name");
        patronRepo.save(patron);
        Patron updatedPatron = patronRepo.findById(1L).orElse(null);
        assertThat(updatedPatron.getFirstName()).isEqualTo("New Name");
    }
}
