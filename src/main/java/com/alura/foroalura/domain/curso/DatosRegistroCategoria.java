package com.alura.foroalura.domain.curso;

import jakarta.validation.constraints.NotBlank;

/**
 * La clase DatosRegistroCategoria es una clase de registro que representa los
 * datos necesarios para registrar una categoría en el sistema. Esta clase tiene
 * un solo campo llamado descripcion, que debe proporcionarse como mínimo para
 * crear una categoría.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosRegistroCategoria(
        @NotBlank(message = "La descripción de la categoría es obligatoria") String descripcion) {
}
