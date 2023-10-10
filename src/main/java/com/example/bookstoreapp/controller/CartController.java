package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.dto.CartDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")

public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDto> insert(@Valid @RequestBody CartDto cartDto){
        ResponseDto responseDto = new ResponseDto("New Cart created.",cartService.insert(cartDto));
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> getAll(){
        ResponseDto responseDto = new ResponseDto("Get All Cart details.",cartService.getAll());
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDto> delete(@PathVariable int cartId){
        return cartService.delete(cartId);
    }

    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDto> updateById(@PathVariable int cartId, @Valid @RequestBody CartDto cartDto){
        ResponseDto responseDto = new ResponseDto("Get All Cart details.",cartService.updateById(cartId, cartDto));
        return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED) ;
    }

    @PutMapping("/updateQuantity/{cartId}")
    public ResponseEntity<ResponseDto> updateQuantity(@PathVariable int cartId,@RequestParam int quantity){
        return cartService.updateQuantity(cartId, quantity);
    }
}
