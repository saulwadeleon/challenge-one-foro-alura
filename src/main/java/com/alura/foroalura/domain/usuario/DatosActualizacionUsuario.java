package com.alura.foroalura.domain.usuario;

/**
 * Esta clase se utiliza para encapsular los datos que se pueden utilizar para
 * actualizar un usuario existente en el sistema.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
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
