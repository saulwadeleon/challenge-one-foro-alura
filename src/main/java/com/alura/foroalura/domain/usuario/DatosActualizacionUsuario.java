package com.alura.foroalura.domain.usuario;

// Un registro (record) que representa los datos para actualizar del usuario
public record DatosActualizacionUsuario(
                String nombre,
                String apellido,
                String email,
                String username,
                String password,
                String role,
                boolean activo) {

        public DatosActualizacionUsuario(Usuario usuarioActualizado) {
                this(usuarioActualizado.getNombre(),
                                usuarioActualizado.getApellido(),
                                usuarioActualizado.getEmail(),
                                usuarioActualizado.getUsername(),
                                usuarioActualizado.getPassword(),
                                usuarioActualizado.getRoleName(),
                                usuarioActualizado.isEnabled());
        }

}
