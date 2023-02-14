package com.example.dutpractice1.repositories;

import com.example.dutpractice1.models.Person;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUsername(String username);

    Optional<Person> findByEmail(String email);

    Boolean existsByEmail(String email);
}