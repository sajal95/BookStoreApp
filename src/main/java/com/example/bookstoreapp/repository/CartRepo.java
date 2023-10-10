package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Integer> {
}
