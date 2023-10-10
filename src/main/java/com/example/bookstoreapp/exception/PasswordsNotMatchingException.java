package com.example.bookstoreapp.exception;

public class PasswordsNotMatchingException extends RuntimeException{

    public PasswordsNotMatchingException(String message){
        super(message);
    }
}
