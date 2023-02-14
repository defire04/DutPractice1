package com.example.dutpractice1.constrollers;


import com.example.dutpractice1.dto.PersonInfoDTO;
import com.example.dutpractice1.dto.PersonLoginDTO;
import com.example.dutpractice1.dto.PersonRegistrationDTO;
import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.services.AuthService;
import com.example.dutpractice1.util.PersonValidator;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Controller
@RequestMapping("auth")
@RestController
public class AuthController {

    private final PersonValidator personValidator;
    private final AuthService registrationService;
    public final DTOController dtoController;

    public AuthController(PersonValidator personValidator, AuthService registrationService, ModelMapper modelMapper, DTOController dtoController) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;

        this.dtoController = dtoController;
    }

    @PostMapping("/login")
    public PersonLoginDTO loginPage(@RequestBody @Valid PersonLoginDTO personLoginDTO){
        return registrationService.login(personLoginDTO);
    }

    @PostMapping("/registration")
    public PersonInfoDTO performRegistration(@RequestBody @Valid PersonRegistrationDTO personRegistrationDTO,
                                      BindingResult bindingResult) {
        Person person = dtoController.convertPersonRegistrationDTOToPerson(personRegistrationDTO);
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            throw new RuntimeException("Invalid data");
        }
        registrationService.register(person);
        return dtoController.convertToPersonInfoDTO(person);
    }



}
