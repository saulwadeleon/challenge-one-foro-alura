package com.alura.foroalura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * La clase DatosRegistroTopico es un registro que se utiliza para representar
 * los datos necesarios para registrar un nuevo tópico en el sistema.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosRegistroTopico(
                @NotBlank(message = "El titulo del topico es obligatorio") String titulo,
                @NotBlank(message = "El mensaje del topico es obligatorio") String mensaje,
                @NotBlank(message = "El nombre del autor es obligatorio") String autor,
                @NotBlank(message = "El nombre del curso es obligatorio") String curso,
                @NotNull(message = "El estatus del topico es obligatorio") StatusTopico estatus) {
}
