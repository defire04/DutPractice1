package com.example.dutpractice1.dto.person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonLoginDTO extends PersonDTO{
    @NotEmpty(message = "Password should be not empty!")
    @Size(min = 8, message = "Password must be more than 8 characters!")
    private String password;

    public PersonLoginDTO() {
    }

    public PersonLoginDTO(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
