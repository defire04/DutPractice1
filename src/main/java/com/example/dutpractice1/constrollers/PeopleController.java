package com.example.dutpractice1.constrollers;

import com.example.dutpractice1.dto.PersonInfoDTO;
import com.example.dutpractice1.dto.PersonLoginDTO;
import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.security.PersonDetails;
import com.example.dutpractice1.services.PeopleService;


import com.example.dutpractice1.services.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping()
public class PeopleController {
    public final PeopleService peopleService;
    public final AuthService registrationService;

    public PeopleController(PeopleService peopleService, AuthService registrationService) {
        this.peopleService = peopleService;
        this.registrationService = registrationService;
    }

    @GetMapping("/users")
    public List<PersonInfoDTO> showAll() {
        return peopleService.findAll().stream().map(this::convertToPersonInfoDTO).collect(Collectors.toList());
    }

    @GetMapping("/userInfo")
    public Person show() {
        return getCurrentUser();
    }

    @PostMapping("/create")
    public Person create(@RequestBody @Valid Person person) {
        registrationService.register(person);
        return person;
    }

    @PatchMapping("/update/{id}")
    public Person update(@RequestBody @Valid Person person, @PathVariable("id") int id) {
        peopleService.update(id, person);
        return person;
    }

    @GetMapping("/admin")
    public String admin() {
        return getCurrentUser().getUsername() + " you are admin!";
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        peopleService.delete(id);
    }

    private Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }

    private PersonInfoDTO convertToPersonInfoDTO(Person person){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(person, PersonInfoDTO.class);
    }
}
