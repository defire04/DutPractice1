package com.example.dutpractice1.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name should be not empty!")
    @Size(min = 2, max = 100, message = "Username should be between 2 and 100 characters")
    private String username;
    @NotEmpty(message = "Password should be not empty!")
    @Size(min = 8, message = "Password must be more than 8 characters!")
    private String password;

    public PersonDTO() {
    }

    public PersonDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
