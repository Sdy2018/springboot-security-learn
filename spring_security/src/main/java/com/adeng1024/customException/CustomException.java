package com.adeng1024.customException;

public class CustomException extends RuntimeException {
    private int code;
    private String message;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
