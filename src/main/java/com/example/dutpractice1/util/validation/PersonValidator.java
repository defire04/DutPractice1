package com.example.dutpractice1.util.validation;
//
//
//import com.example.dutpractice1.dto.person.PersonRegistrationDTO;
//import com.example.dutpractice1.models.Person;
//import com.example.dutpractice1.services.PeopleService;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.Validator;
//
//@Component
//public class PersonValidator implements Validator {
//
//    private final PeopleService peopleService;
//
//    public PersonValidator(PeopleService peopleService) {
//        this.peopleService = peopleService;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Person.class.equals(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
////        Person person = (Person) target;
//        PersonRegistrationDTO personRegistrationDTO = (PersonRegistrationDTO) target;
//
//        if(peopleService.findByEmail(personRegistrationDTO.getEmail()).isPresent()){
//            errors.rejectValue("email", "", "Email should be uniq!");
//        }
//    }
//}

import com.example.dutpractice1.dto.person.PersonDTO;
import com.example.dutpractice1.dto.person.PersonLoginDTO;
import com.example.dutpractice1.dto.person.PersonRegistrationDTO;
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
        if(personDTO.getClass() == PersonLoginDTO.class && !peopleService.existsByEmail(personDTO.email)){
            throw new PersonNotFoundException("User with this email not found!");
        } else if (personDTO.getClass() == PersonRegistrationDTO.class && peopleService.existsByEmail(personDTO.email)) {
            throw new PersonIsExistException("Email should be uniq!");
        }

    }
}


