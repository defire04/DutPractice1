package com.example.dutpractice1.services;

import com.example.dutpractice1.dto.person.PersonDTO;
import com.example.dutpractice1.dto.person.PersonInfoDTO;
import com.example.dutpractice1.dto.person.PersonLoginDTO;
import com.example.dutpractice1.exceptions.PersonNotFoundException;
import com.example.dutpractice1.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMapperService {
    private final ModelMapper modelMapper;
    private final PeopleService peopleService;

    public UserMapperService(ModelMapper modelMapper, PeopleService peopleService) {
        this.modelMapper = modelMapper;
        this.peopleService = peopleService;
    }

    public PersonInfoDTO convertToPersonInfoDTO(Person person){
        return modelMapper.map(person, PersonInfoDTO.class);
    }

    public Person convertPersonDTOToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO, Person.class);
    }

    public PersonInfoDTO convertPersonLoginDTOToPersonInfoDTO(PersonLoginDTO personLoginDTO) {
        Optional<Person> person = peopleService.findByEmail(personLoginDTO.getEmail());

        if(person.isEmpty()){
            throw new PersonNotFoundException("Person not found!");
        }
        return convertToPersonInfoDTO(person.get());
    }
}
