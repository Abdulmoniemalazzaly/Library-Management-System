package com.librarymanagementsystem.service;

import com.librarymanagementsystem.model.Patron;
import com.librarymanagementsystem.repo.PatronRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatronService extends GenericService<Patron>{

    private final PatronRepo patronRepo;

    @Override
    public JpaRepository getRepo() {
        return patronRepo;
    }
}
