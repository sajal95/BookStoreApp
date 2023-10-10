package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class LoginDto {

    public String email;

    @Pattern(regexp = "^((?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&*]).{8,20})$", message = "Invalid password.")
    public String password;

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
