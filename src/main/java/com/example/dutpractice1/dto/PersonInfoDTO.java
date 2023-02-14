package com.example.dutpractice1.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonInfoDTO {
    @NotEmpty(message = "Name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String username;


    @Min(value = 1900, message = "Year of birth cant be more than 1900! It's unreal!")
    private int yearOfBirth;

    @Email
    @NotEmpty(message = "Email should be not empty!")
    private String email;

    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
