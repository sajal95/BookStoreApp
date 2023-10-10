package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class OrderDto {
    public LocalDate date;
    @Min(1)
    public int totalPrice;
    @Min(1)
    public int quantity;
    @NotEmpty
    public String address;
    @Min(1)
    public int userID;
    @Min(1)
    public int bookID;
}
