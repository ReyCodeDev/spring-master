package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class PersonController {

    @Autowired
    TestService testService;

    /**
     * Hola mundo basico sobre la ruta localhost:8080/
     */
    @GetMapping("/")
    public String holaMundo() {
        return "Hola mundo";
    }

    /**
     * Hola mundo, pero pasando nombre por ruta de parametro: por ejemplo:
     * localhost:8080/name/adrian
     * Llama a un servicio, que es la parte lógica de la aplicación
     * @return esta vez devuelve un ResponseEntity, que es basico en peticiones web en vez de string
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<String> holaResponseEntity(@PathVariable String name){
        return new ResponseEntity<>(testService.getHolaMundo(name), HttpStatus.OK);
    }

    /**
     * @return Devuelve un JSON con todos los usuarios, este comportamiento es erroneo ya que se obtiene directamente
     * el objeto sin mappearlo a UN DTO (Data Transfer Object) asi que devuelve toda la información del usuario incluida
     * la sensible
     */
    @GetMapping("/all-db")
    public ResponseEntity<List<Person>> getAllPeopleFromDB() {
        return new ResponseEntity<>(testService.getAllPeopleBd(), HttpStatus.OK);
    }

    /**
     * @return Devuelve un JSON con todos los usuarios quitando el ID y el DNI (por ejemplo)
     */
    @GetMapping("/all")
    public ResponseEntity<List<PersonDTO>> getAllPeople() {
        return new ResponseEntity<>(testService.getAllPeople(), HttpStatus.OK);
    }


    /**
     * @return devuelve un usuario por ID, solo un usuario
     */
    @GetMapping("by-id/{id}")
    public ResponseEntity<PersonDTO> holaResponseEntity(@PathVariable Integer id){
        return new ResponseEntity<>(testService.getPersonById(id), HttpStatus.OK);
    }
}
