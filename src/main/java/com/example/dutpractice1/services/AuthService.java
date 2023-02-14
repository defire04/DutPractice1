package com.example.dutpractice1.services;

import com.example.dutpractice1.dto.PersonLoginDTO;
import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.repositories.PeopleRepository;

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

    public AuthService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, PeopleService peopleService) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.peopleService = peopleService;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

    public PersonLoginDTO login(PersonLoginDTO personLoginDTO){
        if(!peopleService.existsByEmail(personLoginDTO.getEmail())){
            throw new RuntimeException("User not found!");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(personLoginDTO.getEmail(), personLoginDTO.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        return personLoginDTO;
    }

//    public PersonDTO login(PersonDTO personDTO){
//
//    }
}
