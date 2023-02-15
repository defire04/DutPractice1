package com.example.dutpractice1.constrollers;


import com.example.dutpractice1.dto.person.PersonInfoDTO;
import com.example.dutpractice1.dto.person.PersonLoginDTO;
import com.example.dutpractice1.dto.person.PersonRegistrationDTO;
import com.example.dutpractice1.services.AuthService;
import com.example.dutpractice1.services.UserMapperService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService registrationService;
    public final UserMapperService userMapperService;

    public AuthController( AuthService registrationService, UserMapperService userMapperService) {

        this.registrationService = registrationService;
        this.userMapperService = userMapperService;
    }



    @PostMapping("/login")
    public PersonLoginDTO loginPage(@RequestBody @Valid PersonLoginDTO personLoginDTO){
        return registrationService.login(personLoginDTO);
    }

    @PostMapping("/registration")
    public PersonInfoDTO performRegistration(@RequestBody @Valid PersonRegistrationDTO personRegistrationDTO) {
        return registrationService.register(personRegistrationDTO);
    }
}
