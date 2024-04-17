package com.librarymanagementsystem.repo;

import com.librarymanagementsystem.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepo extends JpaRepository<Patron , Long> {
}
