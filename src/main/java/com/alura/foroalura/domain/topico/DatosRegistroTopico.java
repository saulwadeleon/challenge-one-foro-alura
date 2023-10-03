package com.alura.foroalura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Clase de registro que representa los datos para registrar un topico.
public record DatosRegistroTopico(
        @NotBlank(message = "El titulo del topico es obligatorio") String titulo,
        @NotBlank(message = "El mensaje del topico es obligatorio") String mensaje,
        @NotBlank(message = "El nombre del autor es obligatorio") String autor,
        @NotBlank(message = "El nombre del curso es obligatorio") String curso,
        @NotNull(message = "El estatus del topico es obligatorio") StatusTopico estatus) {
}
