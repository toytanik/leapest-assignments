package com.edcast.interwiev.frauddetection.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private String details;
}

