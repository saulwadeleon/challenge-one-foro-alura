package com.alura.foroalura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Clase de registro que representa los datos para registrar a un usuario.
public record DatosRegistroUsuario(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El apellido es obligatorio") String apellido,
        @NotBlank(message = "El email es obligatorio") @Email(message = "El formato de email es inválido") String email,
        @NotBlank(message = "El nombre de usuario es obligatorio") String username,
        @NotBlank(message = "El password de usuario es obligatorio") String password,
        @NotBlank(message = "El role de usuario es obligatorio") String role) {
}
