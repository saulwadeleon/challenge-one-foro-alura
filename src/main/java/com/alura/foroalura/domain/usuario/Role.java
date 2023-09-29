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
