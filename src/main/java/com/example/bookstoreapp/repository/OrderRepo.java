package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel, Integer> {
}
