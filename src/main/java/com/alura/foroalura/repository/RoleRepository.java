package com.alura.foroalura.repository;

import com.alura.foroalura.domain.usuario.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RoleRepository proporciona métodos para interactuar con la entidad Role en la
 * base de datos, incluyendo operaciones como verificar la existencia de un rol
 * por nombre y buscar un rol por su nombre.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByNombreRole(String role);

    Role findByNombreRole(String role);

}
