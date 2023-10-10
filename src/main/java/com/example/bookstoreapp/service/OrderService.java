package com.example.bookstoreapp.service;


import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.exception.OrderNotFoundException;
import com.example.bookstoreapp.model.OrderModel;
import com.example.bookstoreapp.model.UserModel;
import com.example.bookstoreapp.repository.OrderRepo;
import com.example.bookstoreapp.repository.UserRepo;
import com.example.bookstoreapp.util.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements iOrderService{

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    UserRepo userRepo;

    @Override
    public OrderModel insert(OrderDto orderDto){
        OrderModel orderModel = new OrderModel(orderDto);
        orderModel.setDate(LocalDate.now());
        orderRepo.save(orderModel);
        Optional<UserModel> user = userRepo.findById(orderModel.getUserID());
        emailSenderService.sendEmail(user.get().getEmail(), "Order placed.", "The order has been placed successfully for the books.");
        return orderModel;
    }
    @Override
    public List<OrderModel> getAll(){
        List<OrderModel> orderModelList = orderRepo.findAll();
        return orderModelList;
    }

    @Override
    public OrderModel getById(int orderId){
        if (orderRepo.existsById(orderId)){
            return orderRepo.findById(orderId).get();
        }else {
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> delete(int orderId){
        if (orderRepo.existsById(orderId)){
            orderRepo.deleteById(orderId);
            ResponseDto responseDto= new ResponseDto("Order Deleted", false);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }else{
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }
    @Override
    public OrderModel updateById(int orderId, OrderDto orderDto){
        if (orderRepo.existsById(orderId)){
            OrderModel orderModel = new OrderModel(orderDto);
            orderModel.setOrderID(orderId);
            orderModel.setDate(LocalDate.now());
            orderRepo.save(orderModel);
            Optional<UserModel> user = userRepo.findById(orderModel.getUserID());
            emailSenderService.sendEmail(user.get().getEmail(), "Order Updated.", "The order has been updated successfully for the books.");
            return orderModel;
        }else {
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> cancelOrder(int orderId){
        if(orderRepo.existsById(orderId)){
            OrderModel orderModel = orderRepo.findById(orderId).get();
            Optional<UserModel> user = userRepo.findById(orderModel.getUserID());
            orderModel.setOrderID(orderId);
            orderModel.setCancel(true);
            orderRepo.save(orderModel);
            ResponseDto responseDto = new ResponseDto("Order cancelled.", orderModel);
            emailSenderService.sendEmail(user.get().getEmail(), "Order cancelled.", "The order has been cancelled successfully for the books.");
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }else {
            throw new OrderNotFoundException("Order not found. Check orderId.");
        }
    }
}
