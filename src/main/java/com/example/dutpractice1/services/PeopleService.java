package com.example.dutpractice1.services;


import com.example.dutpractice1.models.Person;
import com.example.dutpractice1.repositories.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
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
        peopleRepository.save(updatedPerson);
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> findByUsername(String username){
        return peopleRepository.findByUsername(username);
    }

    public Optional<Person> findByEmail(String email){
        return peopleRepository.findByEmail(email);
    }
}
