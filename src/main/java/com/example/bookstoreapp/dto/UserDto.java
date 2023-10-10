package com.example.bookstoreapp.dto;


public class UserDto {

    public String firstName;

    public String lastName;

    public String email;

    public String address;

    public String dob;

    public String password;

    public UserDto(String firstName, String lastName, String email, String address, String dob, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.password = password;
    }
}
