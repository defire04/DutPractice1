package com.example.dutpractice1.config;

import com.example.dutpractice1.dto.responce.ErrorDTO;
import com.example.dutpractice1.exceptions.PersonIsExistException;
import com.example.dutpractice1.exceptions.PersonNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            PersonNotFoundException.class,
            PersonIsExistException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorDTO handleConflict(RuntimeException ex) {
        return new ErrorDTO(Collections.singletonList(ex.getMessage()), ex.getClass().getSimpleName());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
}
