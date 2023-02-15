package com.example.dutpractice1.dto.person;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public abstract class PersonDTO {
    @Email
    @NotEmpty(message = "Email should be not empty!")
    public String email;
}
