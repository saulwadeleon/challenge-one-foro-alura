package com.alura.foroalura.domain.usuario;

public record DatosDelUsuario(
                Long id,
                String nombre,
                String apellido,
                String email,
                String username,
                String role,
                boolean activo) {

        public DatosDelUsuario(Usuario usuario) {
                this(usuario.getId(),
                                usuario.getNombre(),
                                usuario.getApellido(),
                                usuario.getEmail(),
                                usuario.getUsername(),
                                usuario.getRoleName(),
                                usuario.isEnabled());

        }

}
