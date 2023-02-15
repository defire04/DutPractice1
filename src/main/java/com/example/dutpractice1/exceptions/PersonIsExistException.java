package com.example.dutpractice1.exceptions;

public class PersonIsExistException extends RuntimeException {
    public PersonIsExistException(String message) {
        super(message);
    }
}
