package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ManageExceptionHandler {

    @ExceptionHandler(value = { CollegueNonTrouverException.class })
    protected ResponseEntity<Object> handleConflict(CollegueNonTrouverException ex, WebRequest request) {
        String bodyOfResponse = "aucun collegue trouve";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(value = { CollegueInvalideException.class })
    protected ResponseEntity<Object> handleConflict(CollegueInvalideException ex, WebRequest request) {
        String bodyOfResponse = "Collegue invalide";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

}
