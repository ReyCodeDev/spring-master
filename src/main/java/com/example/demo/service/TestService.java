package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.request.PersonRequestDTO;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService implements ITestService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Autowired
    public TestService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public String getHolaMundo(String name) {
        return "Hola " + name;
    }

    public List<Person> getAllPeopleBd() {
        return personRepository.findAll();
    }

    public List<PersonDTO> getAllPeople() {
        return personMapper.personToPersonDTO(personRepository.findAll());
    }

    public PersonDTO getPersonById(Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        Person person = personOptional.orElseThrow(()-> new PersonNotFoundException("No se encuentra usuario con id " + id));
        return personMapper.personToPersonDTO(person);
    }

    public void insertOne(PersonRequestDTO personRequestDTO) {
        Person person = personMapper.personRequestDTOToPerson(personRequestDTO);
        personRepository.save(person);
    }
}
