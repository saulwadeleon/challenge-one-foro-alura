package com.alura.foroalura.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotación para mapear la entidad 'Categoria' a una tabla llamada 'categoria' en la base de datos.
@Table(name = "categoria")
// Anotación para marcar esta clase como una entidad JPA.
@Entity(name = "Categoria")
@Getter
@NoArgsConstructor
@AllArgsConstructor
// Utilizada para generar el método equals y hashCode basado en el campo 'id'.
@EqualsAndHashCode(of = "id")
/**
 * 
 * la clase Categoria representa la entidad de un categoria con sus atributos
 * básicos y proporciona métodos para administrar y actualizar esos datos. Esta
 * clase es esencial para la clase Curso en el sistema del foro Alura.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public class Categoria {

    // Marca este campo como la clave primaria de la entidad.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dsc_categoria", unique = true)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
