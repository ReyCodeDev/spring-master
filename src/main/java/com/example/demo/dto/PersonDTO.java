package com.example.demo.dto;

/**
 * Esto es un DTO, es una clase que se genera para hacer de intermediario entre la entrada/salida y los modelos de base de datos
 * desde java 16 existe un tipo de clase que se llama record, es lo mismo que hacer:
 * public class PersonDTO {
 *     private final String name;
 *     private final String email;
 *     // y añadirle el getter correspondiente
 * }
 * Son objetos inmutables, que quiere decir? que no se puede crear una funcion setter
 * @param name
 * @param email
 */
public record PersonDTO(String name, String email) {

}
