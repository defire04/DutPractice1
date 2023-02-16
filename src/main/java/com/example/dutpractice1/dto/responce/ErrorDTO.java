package com.example.dutpractice1.dto.responce;

import java.util.List;

public class ErrorDTO {
    private String errorMessage;
    private String exceptionName;

    public ErrorDTO() {
    }

    public ErrorDTO( String exceptionName, String errorMessage) {
        this.exceptionName = exceptionName;
        this.errorMessage = errorMessage;

    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

}
