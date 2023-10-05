package com.alura.foroalura.domain.curso;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotación para mapear la entidad 'Curso' a una tabla llamada 'curso' en la base de datos.
@Table(name = "curso")
// Anotación para marcar esta clase como una entidad JPA.
@Entity(name = "Curso")
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
// Utilizada para generar el método equals y hashCode basado en el campo 'id'.
@EqualsAndHashCode(of = "id")
/**
 * 
 * la clase Curso representa la entidad de un curso con sus atributos básicos
 * y proporciona métodos para administrar y actualizar esos datos.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public class Curso {

	// Marca este campo como la clave primaria de la entidad.
	@Id
	// Especifica que el valor de 'id' se generará automáticamente utilizando una
	// estrategia de identidad.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre_curso")
	private String nombre;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	public Curso(String nombre) {
		this.nombre = nombre;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
