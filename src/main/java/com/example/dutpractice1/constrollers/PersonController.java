package com.example.dutpractice1.constrollers;

import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.security.PersonDetails;
import com.example.dutpractice1.services.PeopleService;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping()
public class PersonController {
    public final PeopleService peopleService;

    public PersonController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/users")
    public List<Person> getPeople() {
        return peopleService.findAll();
    }

    @GetMapping("/userInfo")
    public Person getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

    @PostMapping("/create")
    public Person create(@RequestBody @Valid Person person) {

        System.out.println("================================");
        peopleService.save(person);
        return person;
    }
}
