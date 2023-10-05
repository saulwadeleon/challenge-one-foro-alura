package com.alura.foroalura.repository;

import java.util.List;

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
 * @author Saúl Wade León
 * @version 1.3
 */
// Extiende JpaRepository y trabaja con la entidad 'Usuario'.
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * findByUsername busca un objeto Usuario por su nombre de usuario (username) en
     * la base de datos. Si encuentra un registro con el nombre de usuario
     * especificado, retorna un objeto UserDetails correspondiente; de lo contrario,
     * retorna null.
     * 
     * @param username
     * @return
     */
    UserDetails findByUsername(String username);

    /**
     * existsByUsername verifica si existe un registro en la base de datos con el
     * nombre de usuario especificado. Retorna true si existe un usuario con ese
     * nombre y false en caso contrario. Es útil para validar si un
     * nombre de usuario ya está en uso.
     * 
     * @param username
     * @return
     */
    boolean existsByUsername(String username);

    /**
     * findByNombre busca un objeto Usuario por su nombre en la base de
     * datos. Si encuentra un registro con el nombre especificado, retorna el objeto
     * Usuario correspondiente; de lo contrario, retorna null.
     * 
     * @param autor
     * @return
     */
    Usuario findByNombre(String autor);

    /**
     * findByActivo busca una lista de objetos Usuario en la base de datos que
     * tengan el estado de "activo" especificado. Retorna una lista de usuarios
     * activos. Es útil para buscar y filtrar usuarios activos en la aplicación.
     * 
     * @param activo
     * @return
     */
    List<Usuario> findByActivo(boolean activo);

}
