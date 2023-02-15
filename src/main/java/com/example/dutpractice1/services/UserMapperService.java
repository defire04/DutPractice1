package com.example.dutpractice1.services;

import com.example.dutpractice1.dto.person.PersonInfoDTO;
import com.example.dutpractice1.dto.person.PersonRegistrationDTO;
import com.example.dutpractice1.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserMapperService {
    private final ModelMapper modelMapper;

    public UserMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonInfoDTO convertToPersonInfoDTO(Person person){
        return modelMapper.map(person, PersonInfoDTO.class);
    }

    public Person convertPersonRegistrationDTOToPerson(PersonRegistrationDTO personRegistrationDTO){
        return modelMapper.map(personRegistrationDTO, Person.class);
    }
}
