package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.model.OrderModel;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface iOrderService {
    public OrderModel insert(OrderDto orderDto);
    public List<OrderModel> getAll();
    public OrderModel getById(int orderId);
    public ResponseEntity<ResponseDto> delete(int orderId);
    public OrderModel updateById(int orderId, OrderDto orderDto);
    public ResponseEntity<ResponseDto> cancelOrder(int orderId);

}
