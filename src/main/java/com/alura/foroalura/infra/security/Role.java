package com.alura.foroalura.infra.security;

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

// Define la tabla de base de datos a la que se asignará esta entidad.
@Table(name = "role")
// Define que esta clase es una entidad de persistencia.
@Entity(name = "Role")
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
    // Especifica que el valor de 'id' se generará automáticamente utilizando una
    // estrategia de identidad.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre_role")
    private String nombre_role;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre_role;
    }

    public void setNombre(String bombreRole) {
        this.nombre_role = bombreRole;
    }

}
