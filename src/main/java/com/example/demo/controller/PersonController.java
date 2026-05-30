package com.example.demo.controller;

import com.example.demo.dto.PersonDTO;
import com.example.demo.dto.request.PersonRequestDTO;
import com.example.demo.model.Person;
import com.example.demo.service.ITestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class PersonController {

    final ITestService testService;

    public PersonController(ITestService testService) {
        this.testService = testService;
    }

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

    @PostMapping("/")
    public ResponseEntity<?> newUser(@Valid @RequestBody PersonRequestDTO personRequestDTO) {
        testService.insertOne(personRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", Instant.now().toString());
        response.put("status", 400);
        response.put("error", "Bad Request");
        response.put("path", request.getRequestURI()); // Captura la ruta dinámica automáticamente
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
