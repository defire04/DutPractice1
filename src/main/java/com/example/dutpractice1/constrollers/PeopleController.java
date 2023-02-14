package com.example.dutpractice1.constrollers;

import com.example.dutpractice1.dto.PersonInfoDTO;
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

    public final DTOController dtoController;
    public PeopleController(PeopleService peopleService, AuthService registrationService, ModelMapper modelMapper, DTOController dtoController) {
        this.peopleService = peopleService;
        this.registrationService = registrationService;
        this.dtoController = dtoController;
    }

    @GetMapping("/users")
    public List<PersonInfoDTO> showAll() {
        return peopleService.findAll().stream().map(dtoController::convertToPersonInfoDTO).collect(Collectors.toList());
    }

    @GetMapping("/userInfo")
    public Person show() {
        return getCurrentUser();
    }

    @PostMapping("/create")
    public PersonInfoDTO create(@RequestBody @Valid Person person) {
        registrationService.register(person);
        return dtoController.convertToPersonInfoDTO(person);
    }

    @PatchMapping("/update/{id}")
    public PersonInfoDTO update(@RequestBody @Valid Person person, @PathVariable("id") int id) {
        peopleService.update(id, person);
        return dtoController.convertToPersonInfoDTO(person);
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


}
