package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CollegueInvalideExceptionHandler {

    @ExceptionHandler(value =  CollegueInvalideException.class )
    protected ResponseEntity<Object> handleConflict(CollegueInvalideException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
    }
}
