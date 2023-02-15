package com.example.dutpractice1.dto.responce;

import java.util.List;

public class ErrorDTO {
    private List<String> errorMessage;
    private String exceptionName;

    public ErrorDTO() {
    }

    public ErrorDTO(List<String> errorMessage, String exceptionName) {
        this.errorMessage = errorMessage;
        this.exceptionName = exceptionName;
    }

    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

}
