package com.example.dutpractice1.services;

import com.example.dutpractice1.dto.person.*;
import com.example.dutpractice1.exceptions.PersonIsExistException;
import com.example.dutpractice1.exceptions.PersonNotFoundException;
import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.repositories.PeopleRepository;

import com.example.dutpractice1.util.validation.PersonValidator;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final PeopleService peopleService;
    private final UserMapperService userMapperService;
    private final PersonValidator personValidator;

    public AuthService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, PeopleService peopleService, UserMapperService userMapperService, PersonValidator personValidator) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.peopleService = peopleService;
        this.userMapperService = userMapperService;
        this.personValidator = personValidator;
    }

    @Transactional
    public PersonInfoDTO register(PersonDTO personRegistration) {

        personValidator.validate(personRegistration);

        Person person = userMapperService.convertPersonDTOToPerson(personRegistration);
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        if (person.getRole() == null){
            person.setRole("ROLE_USER");
        }
        peopleRepository.save(person);

        return userMapperService.convertToPersonInfoDTO(person);
    }

    public PersonLoginDTO login(PersonLoginDTO personLoginDTO){
        personValidator.validate(personLoginDTO);

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                personLoginDTO.getEmail(), personLoginDTO.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        return personLoginDTO;
    }
}
