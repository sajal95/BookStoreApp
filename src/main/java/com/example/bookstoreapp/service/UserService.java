package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.LoginDto;
import com.example.bookstoreapp.dto.ResponseDto;
import com.example.bookstoreapp.dto.UserDto;
import com.example.bookstoreapp.exception.PasswordsNotMatchingException;
import com.example.bookstoreapp.exception.UserNotFoundException;
import com.example.bookstoreapp.model.UserModel;
import com.example.bookstoreapp.repository.UserRepo;
import com.example.bookstoreapp.util.EmailSenderService;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService implements iUserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    EmailSenderService emailSenderService;
@Override
    public UserModel registerUser(UserDto userDto) {
        UserModel user = new UserModel(userDto);
        userRepo.save(user);
        String token = tokenUtil.createToken(user.getUserId());
        System.out.println(token);
        emailSenderService.sendEmail(user.getEmail(), "Registration Done Successfully.", "New user has been added. The name of the person is: " + user.getFirstName() +
                '\n' + "To get the details click the link: http://localhost:8015/user/getByToken/" + token);
        return user;
    }

    @Override
    public List<UserModel> showAllUser() {
        return userRepo.findAll();
    }

    @Override
    public UserModel getbyID(int userId) {
        if (userRepo.existsById(userId)) {
            return userRepo.findById(userId).get();
        } else {
            throw new UserNotFoundException("No such user found with this userId");
        }
    }

    @Override
    public UserModel getbyEmailID(String emailId) {
        UserModel user = userRepo.getUserByEmail(emailId);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("Search with another Email.");
        }
    }

    @Override
    public UserModel forgotPassword(String emailId, String NewPassword) {
        UserModel user = userRepo.getUserByEmail(emailId);
        if (user != null) {
            user.setPassword(NewPassword);
            userRepo.save(user);
            emailSenderService.sendEmail(user.getEmail(), "Password has reset link.", "The password has been reset.");
            return user;
        } else {
            throw new UserNotFoundException("EmailId Incorrect...!");
        }
    }

    @Override
    public UserModel changePassword(LoginDto loginDto, String NewPassword) {
        UserModel user = userRepo.getUserByEmail(loginDto.getEmail());
        if (user!=null){
            if (user.getPassword().equals(loginDto.getPassword())) {
                user.setPassword(NewPassword);
                userRepo.save(user);
                emailSenderService.sendEmail(user.getEmail(), "Password changed Successfully.", "The password has been changed successfully.");
                return user;
            } else {
                throw new PasswordsNotMatchingException("Old Password is Incorrect...!");
            }
        }else {
            throw new UserNotFoundException("User not found. Check emailId...!");
        }
    }

    @Override
    public ResponseEntity<ResponseDto> updateUserByEmailId(String emailId, UserDto userDto) {
        UserModel user = userRepo.getUserByEmail(emailId);
        if (user != null) {
            user.setPassword(userDto.password);
            user.setDob(userDto.dob);
            user.setAddress(userDto.address);
            user.setFirstName(userDto.firstName);
            user.setLastName(userDto.lastName);
            userRepo.save(user);
            emailSenderService.sendEmail(user.getEmail(), "User details update", "The password has been updated successfully.");
            ResponseDto responseDto = new ResponseDto("Put call successful", user);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("Incorrect EmailId.");
        }
    }


    public ResponseEntity<ResponseDto> loginUser(LoginDto loginDto) {
        UserModel user = userRepo.getUserByEmail(loginDto.getEmail());
        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            ResponseDto responseDto = new ResponseDto("Login Successfully", user);
            return new ResponseEntity<>(responseDto, HttpStatus.ACCEPTED);
        } else {
            throw new UserNotFoundException("Either Username or Password is Invalid");
        }
    }

    public ResponseEntity<ResponseDto> deleteUserById(int userId) {
        if (userRepo.existsById(userId)) {
            String toEmail= userRepo.findById(userId).get().getEmail();
            userRepo.deleteById(userId);
            ResponseDto responseDto = new ResponseDto("User Deleted Successfully", false);
            emailSenderService.sendEmail(toEmail, "User deleted Successfully.", "The user has been deleted.");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("UserId Entered is Incorrect.");
        }
    }

    @Override
    public UserModel getUserByToken(String token){
        int userId = tokenUtil.decodeToken(token);
        return userRepo.findById(userId).get();
    }
}
