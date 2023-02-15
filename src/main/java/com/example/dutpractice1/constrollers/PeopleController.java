package com.example.dutpractice1.constrollers;

import com.example.dutpractice1.dto.person.PersonRegisteredByAdmin;
import com.example.dutpractice1.dto.person.PersonInfoDTO;
import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.security.PersonDetails;
import com.example.dutpractice1.services.PeopleService;


import com.example.dutpractice1.services.AuthService;
import com.example.dutpractice1.services.UserMapperService;
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
    public final UserMapperService userMapperService;

    public PeopleController(PeopleService peopleService, AuthService registrationService, UserMapperService userMapperService) {
        this.peopleService = peopleService;
        this.registrationService = registrationService;
        this.userMapperService = userMapperService;
    }

    @GetMapping("/users")
    public List<PersonInfoDTO> showAll() {
        return peopleService.findAll().stream().map(userMapperService::convertToPersonInfoDTO).collect(Collectors.toList());
    }

    @GetMapping("/userInfo")
    public Person show() {
        return getCurrentUser();
    }

    @PostMapping("/create")
    public PersonInfoDTO create(@RequestBody @Valid PersonRegisteredByAdmin personRegisteredByAdmin) {
        return registrationService.register(personRegisteredByAdmin);
    }

    @PatchMapping("/update/{id}")
    public PersonInfoDTO update(@RequestBody @Valid Person person, @PathVariable("id") int id) {
        peopleService.update(id, person);
        return userMapperService.convertToPersonInfoDTO(person);
    }

    @GetMapping("/admin")
    public String admin() {
        return getCurrentUser().getUsername() + " you are admin!";
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        peopleService.delete(id);
    }

    private Person getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return personDetails.getPerson();
    }
}
