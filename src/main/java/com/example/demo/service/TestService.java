package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    /**
     *
     * @param name el nombre del usuario
     * @return Hola {name}
     */
    public String getHolaMundo(String name) {
        return "Hola " + name;
    }

    /**
     *
     * @return todas las personas directamente desde la base de datos
     */
    public List<Person> getAllPeopleBd() {
        return personRepository.findAll();
    }

    /**
     *
     * @return todos los usuarios, después de pasar por un mapper
     */
    public List<PersonDTO> getAllPeople() {
        return personMapper.personToPersonDTO(personRepository.findAll());
    }

    /**
     * Devuelve un usuario, ojo, aqui se observa una clase que se llama Optional, esta clase controla nulos
     * @param id el id del usuario
     * @return el usuario por id
     * @throws PersonNotFoundException esto lanza una excepcion personalizada, que hace que se muestre un 404 en
     * PersonNotFoundExceptionHandler
     */
    public PersonDTO getPersonById(Integer id) {
        Optional<Person> personOptional = personRepository.findById(id);
        Person person = personOptional.orElseThrow(()-> new PersonNotFoundException("No se encuentra usuario con id " + id));
        return personMapper.personToPersonDTO(person);
    }
}
