package com.alura.foroalura.domain.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * La clase Role se utiliza para representar roles de usuario en el sistema y se
 * mapea a una tabla en la base de datos. Cada rol tiene un identificador único
 * (id) y un nombre de rol (nombreRole). Esta clase facilita la gestión de roles
 * de usuario en la aplicación.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
// Define que esta clase es una entidad de persistencia.
@Entity(name = "Role")
// Define la tabla de base de datos a la que se asignará esta entidad.
@Table(name = "role")
// Genera automáticamente métodos getters para todos los campos de la clase.
@Getter
// Genera un constructor sin argumentos.
@NoArgsConstructor
// Genera un constructor que acepta todos los campos como argumentos.
@AllArgsConstructor
// Genera automáticamente los métodos equals y hashCode basados en el campo
// 'id'.
@EqualsAndHashCode(of = "id")
public class Role {

    // Marca este campo como la clave primaria de la entidad.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_role", unique = true)
    private String nombreRole;

    public Role(String nombreRole) {
        this.nombreRole = nombreRole;
    }

    public Role(Long roleId) {
        this.id = roleId;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombreRole;
    }

    public void setNombre(String nombreRole) {
        this.nombreRole = nombreRole;
    }

    public void setId(Long roleId) {
        this.id = roleId;
    }

}
