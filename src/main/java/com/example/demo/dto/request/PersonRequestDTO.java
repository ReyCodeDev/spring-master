package com.example.demo.dto.request;


import com.example.demo.validators.annotations.ValidDni;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * Esto es un DTO, es una clase que se genera para hacer de intermediario entre la entrada/salida y los modelos de base de datos
 * desde java 16 existe un tipo de clase que se llama record, es lo mismo que hacer:
 * public class PersonDTO {
 * private final String name;
 * private final String email;
 * // y añadirle el getter correspondiente
 * }
 * Son objetos inmutables, que quiere decir? que no se puede crear una funcion setter
 *
 * @param name
 * @param email
 */
public record PersonRequestDTO(
        @NotBlank(message = "Como porongas el usuario no va a tener nombre")
        String name,
        @Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
                message = "El formato del correo electrónico no es válido"
        )
        String email,
        @ValidDni(message = "Aqui no queremos indocumentados")
        String dni) {

}
