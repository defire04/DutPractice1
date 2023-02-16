package com.example.dutpractice1.dto.person;

import com.example.dutpractice1.util.annotation.Password;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonRegisteredByAdminDTO extends PersonDTO implements IPersonRegistration{
    @NotEmpty(message = "Name should be not empty!")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String username;

    @Min(value = 1900, message = "Year of birth cant be more than 1900! It's unreal!")
    private int yearOfBirth;

    @NotEmpty(message = "Password should be not empty!")
    @Password
    private String password;

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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
