package com.alura.foroalura.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * La clase DatosRegistroRespuesta es una clase de registro que se utiliza para
 * representar los datos necesarios para registrar una nueva respuesta en el
 * sistema. Esta clase agrupa los campos esenciales que deben proporcionarse al
 * crear una respuesta.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosRegistroRespuesta(
        @NotBlank(message = "El mensaje de la respuesta es obligatorio") String mensaje,
        @NotBlank(message = "El topico de la respuesta es obligatorio") String titulo,
        @NotBlank(message = "El autor de la respuesta es obligatorio") String autor,
        @NotNull(message = "El estatus de solucion es obligatorio") Boolean solucion) {

}
