package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.LoginDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.dto.UserDto;
import com.example.bookstoreapp.model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface iUserService {

    public UserModel registerUser(UserDto userDto);

    public List<UserModel> showAllUser();

    public UserModel getbyID(int userId);

    public UserModel getbyEmailID(String emailId);

    public UserModel forgotPassword(String emailId, String NewPassword);

    public UserModel changePassword(LoginDto loginDto, String NewPassword);
    public ResponseEntity<ResponseDto> updateUserByEmailId(String emailId,UserDto userDto);
    public ResponseEntity<ResponseDto> loginUser(LoginDto loginDto);
    public ResponseEntity<ResponseDto> deleteUserById(int userId);
    public UserModel getUserByToken(String token);
}
