package com.example.dutpractice1.constrollers;


import com.example.dutpractice1.dto.PersonLoginDTO;
import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.services.AuthService;
import com.example.dutpractice1.util.PersonValidator;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@Controller
@RequestMapping("auth")
@RestController
public class AuthController {

    private final PersonValidator personValidator;
    private final AuthService registrationService;

    public AuthController(PersonValidator personValidator, AuthService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

//    @GetMapping("/login")
//    public String loginPage(){
//        return "auth/login";
//    }

    @PostMapping("/login")
    public PersonLoginDTO loginPage(@RequestBody @Valid PersonLoginDTO personLoginDTO){
        return registrationService.login(personLoginDTO);
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "/auth/registration";
        }

        registrationService.register(person);
        return "redirect:/auth/login";
    }

}
