package com.example.demo.mapper;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.request.PersonRequestDTO;
import com.example.demo.model.Person;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDTO personToPersonDTO(Person person);
    List<PersonDTO> personToPersonDTO(List<Person> person);
    Person personRequestDTOToPerson(PersonRequestDTO personRequestDTO);
}
