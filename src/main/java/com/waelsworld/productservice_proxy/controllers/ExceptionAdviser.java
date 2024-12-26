package com.waelsworld.productservice_proxy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdviser {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        // here exception e can be logged or any corrective action can be taken
        //  for now just printing the stack trace
        // todo: add logging mechanism
        e.printStackTrace();
        // A human-readable message can be passed from here, and not the exception
        return new ResponseEntity<>("Oops!! Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
