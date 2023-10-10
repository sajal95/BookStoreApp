package com.example.bookstoreapp.model;


import com.example.bookstoreapp.dto.CartDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int cartID;
    private int userID;
    private int bookID;
    private int quantity;

    public Cart (CartDto cartDto){
        this.userID = cartDto.userID;
        this.bookID = cartDto.bookID;
        this.quantity = cartDto.quantity;
    }

}
