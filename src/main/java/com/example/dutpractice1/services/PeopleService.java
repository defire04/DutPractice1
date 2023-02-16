package com.example.dutpractice1.services;


import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.repositories.PeopleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    public PeopleService(PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }

        public boolean existsByEmail(String email) {
        return peopleRepository.existsByEmail(email);
    }
}
