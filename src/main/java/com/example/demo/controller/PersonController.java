package com.example.demo.controller;

import com.example.demo.api.PersonApi;
import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.request.PersonRequestDTO;
import com.example.demo.model.Person;
import com.example.demo.service.ITestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementa la interfaz {@link PersonApi}, generada a partir del contrato
 * OpenAPI (src/main/resources/openapi/openapi.yaml). Los mapeos de ruta,
 * @PathVariable, @RequestBody y @Valid viven en la interfaz generada; aquí solo
 * está la lógica de cada endpoint.
 */
@RestController
public class PersonController implements PersonApi {

    final ITestService testService;

    public PersonController(ITestService testService) {
        this.testService = testService;
    }

    /**
     * Hola mundo basico sobre la ruta localhost:8080/
     */
    @Override
    public ResponseEntity<String> holaMundo() {
        return new ResponseEntity<>("Hola mundo", HttpStatus.OK);
    }

    /**
     * Hola mundo, pero pasando nombre por ruta de parametro: por ejemplo:
     * localhost:8080/name/adrian
     * Llama a un servicio, que es la parte lógica de la aplicación
     * @return esta vez devuelve un ResponseEntity, que es basico en peticiones web en vez de string
     */
    @Override
    public ResponseEntity<String> holaResponseEntity(String name){
        return new ResponseEntity<>(testService.getHolaMundo(name), HttpStatus.OK);
    }

    /**
     * @return Devuelve un JSON con todos los usuarios, este comportamiento es erroneo ya que se obtiene directamente
     * el objeto sin mappearlo a UN DTO (Data Transfer Object) asi que devuelve toda la información del usuario incluida
     * la sensible
     * (Esto también puede hacer que se genere un JSON infinito si tiene relaciones, asi que ojo)
     */
    @Override
    public ResponseEntity<List<Person>> getAllPeopleFromDB() {
        return new ResponseEntity<>(testService.getAllPeopleBd(), HttpStatus.OK);
    }

    /**
     * @return Devuelve un JSON con todos los usuarios quitando el ID y el DNI (por ejemplo)
     */
    @Override
    public ResponseEntity<List<PersonDTO>> getAllPeople() {
        return new ResponseEntity<>(testService.getAllPeople(), HttpStatus.OK);
    }


    /**
     * @return devuelve un usuario por ID, solo un usuario
     */
    @Override
    public ResponseEntity<PersonDTO> getById(Integer id){
        return new ResponseEntity<>(testService.getPersonById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> newUser(PersonRequestDTO personRequestDTO) {
        testService.insertOne(personRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PersonDTO>> getParentsByChildId(Integer childId) {
        return new ResponseEntity<>(testService.getParents(childId), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("path", request.getRequestURI());
        Map<String, String> detallesErrores = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            detallesErrores.put(fieldName, errorMessage);
        });
        response.put("errores", detallesErrores);
        return ResponseEntity.badRequest().body(response);
    }
}
