package com.librarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patron extends AuditorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;

    @Column(nullable = false)
    @NotNull
    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    @Column(nullable = false)
    @NotNull
    @NotBlank(message = "Patron phone is mandatory")
    private String phone;

    @Column
    @Email
    private String email;

    @OneToMany(mappedBy = "patron")
    private Set<BorrowingRecord> borrowingRecords = new HashSet<>();
}
