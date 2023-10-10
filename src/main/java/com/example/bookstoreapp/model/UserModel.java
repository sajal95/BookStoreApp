package com.example.bookstoreapp.model;


import com.example.bookstoreapp.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class UserModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String dob;
    private String password;

    public UserModel(UserDto userDto){
        this.firstName=userDto.firstName;
        this.lastName= userDto.lastName;
        this.email= userDto.email;
        this.address= userDto.address;
        this.dob=userDto.dob;
        this.password= userDto.password;
    }

    public UserModel() {
    }
}
