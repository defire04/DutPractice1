package com.example.dutpractice1.util.validation;

import com.example.dutpractice1.dto.person.*;
import com.example.dutpractice1.exceptions.PersonIsExistException;
import com.example.dutpractice1.exceptions.PersonNotFoundException;
import com.example.dutpractice1.services.PeopleService;
import org.springframework.stereotype.Component;

@Component
public class PersonValidator{

    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    public void validate(PersonDTO personDTO ){
        if(personDTO.getClass() == PersonLoginDTO.class && !peopleService.existsByEmail(personDTO.getEmail())){
            throw new PersonNotFoundException("User with this email not found!");
        } else if ((personDTO instanceof IPersonRegistration) && peopleService.existsByEmail(personDTO.getEmail())) {
            throw new PersonIsExistException("Email should be uniq!");
        }
    }
}


