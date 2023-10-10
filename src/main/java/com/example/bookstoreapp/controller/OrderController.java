package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.OrderDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> insert(@Valid @RequestBody OrderDto orderDto){
        ResponseDto responseDto = new ResponseDto("New order created",orderService.insert(orderDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAll(){
        ResponseDto responseDto = new ResponseDto("Get all orders",orderService.getAll());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getById/{orderId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int orderId){
        ResponseDto responseDto = new ResponseDto("Get order by id: "+orderId,orderService.getById(orderId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int orderId){
        return orderService.delete(orderId);
    }

    @PutMapping("/updateById/{orderId}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable int orderId,@Valid @RequestBody OrderDto orderDto){
        ResponseDto responseDto = new ResponseDto("Get orders by id: "+orderId,orderService.updateById(orderId, orderDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable int orderId){
        return orderService.cancelOrder(orderId);
    }
}
