package com.edcast.interwiev.frauddetection.handler;

import com.edcast.interwiev.frauddetection.exception.FraudAlreadyExist;
import com.edcast.interwiev.frauddetection.exception.FraudNotFoundException;
import com.edcast.interwiev.frauddetection.exception.IllegalInputFormatError;
import com.edcast.interwiev.frauddetection.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(FraudNotFoundException.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(FraudNotFoundException ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(FraudAlreadyExist.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(FraudAlreadyExist ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.ALREADY_REPORTED, LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.ALREADY_REPORTED);
    }
    @ExceptionHandler(IllegalInputFormatError.class)
    protected ResponseEntity<?> handleMethodArgumentNotValid(IllegalInputFormatError ex, WebRequest request) {
        ErrorDetails error = new ErrorDetails(HttpStatus.BAD_REQUEST, LocalDateTime.now(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

