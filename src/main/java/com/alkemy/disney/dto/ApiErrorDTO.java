package com.alkemy.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiErrorDTO <T> extends Object{

    private HttpStatus status;

    private String message;

    private T errors;
}
