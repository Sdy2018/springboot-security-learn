package com.adeng1024.myExceptionHandler;

import com.adeng1024.customException.CustomException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = CustomException.class)
    public String customException(CustomException c){
        System.out.println(c.getMessage());
        return "-----";
    }
}
