package com.example.restaurant.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    ErrorResponse(String message){
        this.message=message;
    }
    private String message;
    private long timestamp = System.currentTimeMillis();
}