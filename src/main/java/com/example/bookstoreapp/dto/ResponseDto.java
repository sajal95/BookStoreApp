package com.example.bookstoreapp.dto;

import lombok.Getter;

@Getter
public class ResponseDto {

    public String message;
    public Object data;

    public ResponseDto(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}