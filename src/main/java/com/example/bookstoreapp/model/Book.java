package com.example.bookstoreapp.model;


import com.example.bookstoreapp.dto.BookDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    public Book (BookDto bookDto){
        this.bookName = bookDto.bookName;
        this.authorName = bookDto.authorName;
        this.bookDescription = bookDto.bookDescription;
        this.bookImg = bookDto.bookImg;
        this.price = bookDto.price;
        this.quantity = bookDto.quantity;
    }

}
