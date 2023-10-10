package com.example.bookstoreapp.dto;


import jakarta.validation.constraints.Min;

public class CartDto {

    @Min(1)
    public int userID;
    @Min(1)
    public int bookID;

    @Min(1)
    public int quantity;
}
