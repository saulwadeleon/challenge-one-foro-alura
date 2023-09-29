package com.alura.foroalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.alura.foroalura.domain.usuario.Usuario;

/**
 * 
 * Esta interfaz proporciona métodos CRUD (Crear, Leer, Actualizar y Eliminar)
 * para la entidad Usuario y también agrega un método personalizado findByLogin
 * que busca un usuario por su nombre de usuario. Esto es útil para buscar
 * usuarios específicos en la base de datos, especialmente cuando se necesita
 * autenticación y autorización en una aplicación Spring Security.
 * 
 */
// Extiende JpaRepository y trabaja con la entidad 'Usuario'.
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Este método busca un usuario por su nombre de usuario (login).
    UserDetails findByUsername(String username);

}
