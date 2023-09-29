package com.alura.foroalura.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotación para mapear la entidad 'Topico' a una tabla llamada 'topico' en la base de datos.
@Table(name = "curso")
// Anotación para marcar esta clase como una entidad JPA.
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
// Utilizada para generar el método equals y hashCode basado en el campo 'id'.
@EqualsAndHashCode(of = "id")
/**
 * 
 * la clase Topico representa la entidad de un topico con sus atributos básicos
 * y proporciona métodos para administrar y actualizar esos datos. Esta clase es
 * esencial en un sistema del foro.
 * 
 */
public class Curso {

	// Marca este campo como la clave primaria de la entidad.
	@Id
	// Especifica que el valor de 'id' se generará automáticamente utilizando una
	// estrategia de identidad.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	private String nombre;
	private Long categoriaID;

	public Curso(String nombre, Long categoriaID) {
		this.nombre = nombre;
		this.categoriaID = categoriaID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getCategoriaId() {
		return categoriaID;
	}

}
