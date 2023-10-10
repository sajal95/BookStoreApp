package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.dto.LoginDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.dto.UserDto;
import com.example.bookstoreapp.service.UserService;
import com.example.bookstoreapp.service.iUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    iUserService service;

    @PostMapping("/newUser")
    public ResponseEntity<ResponseDto> newUser(@Valid @RequestBody UserDto userDto) {
        ResponseDto responseDto = new ResponseDto("New user added.", service.registerUser(userDto));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDto> showAll() {
        ResponseDto responseDto = new ResponseDto("Users List.", service.showAllUser());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getById/{userId}")
    public ResponseEntity<ResponseDto> getById(@PathVariable int userId) {
        ResponseDto responseDto = new ResponseDto("User Details of ID :"+userId, service.getbyID(userId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/getByEmailId")
    public ResponseEntity<ResponseDto> getById(@RequestParam String emailId) {
        ResponseDto responseDto = new ResponseDto("Get user by email."+emailId, service.getbyEmailID(emailId));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<ResponseDto> forgotPassword(@RequestParam String emailId, @RequestParam String NewPassword) {
        ResponseDto responseDto = new ResponseDto("Password Reset Done.", service.forgotPassword(emailId, NewPassword));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody LoginDto loginDto, @RequestParam String NewPassword) {
        ResponseDto responseDto = new ResponseDto("Password Changed.", service.changePassword(loginDto, NewPassword));
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("/updateUserByEmailId")
    public ResponseEntity<ResponseDto> updateUserByEmailId(@RequestParam String emailId,@Valid @RequestBody UserDto userDto) {
        return service.updateUserByEmailId(emailId, userDto);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDto> loginUser(@Valid @RequestBody LoginDto loginDto) {
        return service.loginUser(loginDto);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable int userId){
        return service.deleteUserById(userId);
    }

    @GetMapping("/getByToken/{token}")
    public ResponseEntity<ResponseDto> getUserByToken(@PathVariable String token){
        ResponseDto responseDto = new ResponseDto("Get User by token.", service.getUserByToken(token));
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}