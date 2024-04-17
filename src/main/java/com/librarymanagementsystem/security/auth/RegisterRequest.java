package com.librarymanagementsystem.security.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotNull
    @NotBlank(message = "Firstname is mandatory")
    private String firstname;
    @NotNull
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotNull
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotNull
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
}
