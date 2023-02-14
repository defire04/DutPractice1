package com.example.dutpractice1.util;


import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(peopleService.findByUsername(person.getUsername()).isPresent()){
            errors.rejectValue("username", "", "A person with that username already exists!");
        }

        if(peopleService.findByEmail(person.getEmail()).isPresent()){
            errors.rejectValue("email", "", "Email should be uniq!");
        }
    }
}
