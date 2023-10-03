package com.alura.foroalura.domain.curso;

import jakarta.validation.constraints.NotBlank;

// Clase de registro que representa los datos para registrar un topico.
public record DatosRegistroCategoria(
                @NotBlank(message = "La descripción de la categoría es obligatoria") String descripcion) {
}
