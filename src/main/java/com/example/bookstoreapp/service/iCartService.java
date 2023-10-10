package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.CartDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.model.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface iCartService {
    public Cart insert(CartDto cartDto);

    public List<Cart> getAll();

    public ResponseEntity<ResponseDto> delete(int cartId);

    public Cart updateById(int cartId, CartDto cartDto);

    public ResponseEntity<ResponseDto> updateQuantity(int cartId, int quantity);

}
