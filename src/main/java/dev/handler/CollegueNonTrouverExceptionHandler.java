package dev.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import dev.exception.CollegueNonTrouverException;

@ControllerAdvice
public class CollegueNonTrouverExceptionHandler {

    @ExceptionHandler(value = { CollegueNonTrouverException.class })
    protected ResponseEntity<Object> handleConflict(CollegueNonTrouverException ex, WebRequest request) {
        String bodyOfResponse = "aucun collegue trouve";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
}
