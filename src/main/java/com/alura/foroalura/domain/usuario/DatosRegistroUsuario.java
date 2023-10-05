package com.alura.foroalura.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * La clase DatosRegistroUsuario encapsula los datos esenciales requeridos para
 * registrar a un nuevo usuario en el sistema, incluyendo su nombre, apellido,
 * dirección de correo electrónico, nombre de usuario, contraseña y rol. Los
 * campos están validados para garantizar que cumplan con ciertos requisitos,
 * como la presencia de datos y el formato de correo electrónico válido.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosRegistroUsuario(
                @NotBlank(message = "El nombre es obligatorio") String nombre,
                @NotBlank(message = "El apellido es obligatorio") String apellido,
                @NotBlank(message = "El email es obligatorio") @Email(message = "El formato de email es inválido") String email,
                @NotBlank(message = "El nombre de usuario es obligatorio") String username,
                @NotBlank(message = "El password de usuario es obligatorio") String password,
                @NotBlank(message = "El role de usuario es obligatorio") String role) {
}
