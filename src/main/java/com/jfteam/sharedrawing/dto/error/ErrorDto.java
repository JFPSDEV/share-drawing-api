package com.jfteam.sharedrawing.dto.error;

import lombok.Data;

@Data
public class ErrorDto {

    private int httpCode;
    private String message;

    public ErrorDto(int httpCode, String message) {
        this.httpCode = httpCode;
        this.message = message;
    }

}