package com.example.dutpractice1.constrollers;

import com.example.dutpractice1.dto.PersonInfoDTO;
import com.example.dutpractice1.dto.PersonRegistrationDTO;
import com.example.dutpractice1.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;

@Controller
public class DTOController {
    private final ModelMapper modelMapper;

    public DTOController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonInfoDTO convertToPersonInfoDTO(Person person){
        return modelMapper.map(person, PersonInfoDTO.class);
    }

    public Person convertPersonRegistrationDTOToPerson(PersonRegistrationDTO personRegistrationDTO){
        return modelMapper.map(personRegistrationDTO, Person.class);
    }


}
