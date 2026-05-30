package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.request.PersonRequestDTO;
import com.example.demo.exception.PersonNotFoundException;
import com.example.demo.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ITestService {
    /**
     *
     * @param name el nombre del usuario
     * @return Hola {name}
     */
    String getHolaMundo(String name);

    /**
     *
     * @return todas las personas directamente desde la base de datos
     */
    List<Person> getAllPeopleBd();

    /**
     *
     * @return todos los usuarios, después de pasar por un mapper
     */
    List<PersonDTO> getAllPeople();

    /**
     * Devuelve un usuario, ojo, aqui se observa una clase que se llama Optional, esta clase controla nulos
     * @param id el id del usuario
     * @return el usuario por id
     * @throws PersonNotFoundException esto lanza una excepcion personalizada, que hace que se muestre un 404 en
     * PersonNotFoundExceptionHandler
     */
    PersonDTO getPersonById(Integer id);

    void insertOne(PersonRequestDTO personRequestDTO);
}
