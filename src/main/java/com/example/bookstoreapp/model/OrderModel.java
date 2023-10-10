package com.example.bookstoreapp.model;


import com.example.bookstoreapp.dto.OrderDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int orderID;
    public LocalDate date=LocalDate.now();

    private int totalPrice;
    private int quantity;
    private String address;
    private int userID;
    private int bookID;
    private boolean cancel;

    public OrderModel(OrderDto orderDto){
        this.date = orderDto.date;
        this.totalPrice=orderDto.totalPrice;
        this.quantity=orderDto.quantity;
        this.address=orderDto.address;
        this.userID=orderDto.userID;
        this.bookID=orderDto.bookID;

    }
}
