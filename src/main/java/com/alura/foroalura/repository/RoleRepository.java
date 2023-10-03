package com.alura.foroalura.repository;

import com.alura.foroalura.domain.usuario.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByNombreRole(String role);

    Role findByNombreRole(String role);

}
