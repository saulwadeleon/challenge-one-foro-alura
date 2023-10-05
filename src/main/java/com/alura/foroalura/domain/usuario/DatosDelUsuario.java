package com.alura.foroalura.domain.usuario;

/**
 * La clase DatosDelUsuario representa los datos de un usuario en el sistema.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
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
