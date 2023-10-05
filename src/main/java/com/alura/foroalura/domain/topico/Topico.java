package com.alura.foroalura.domain.topico;

import java.time.LocalDateTime;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Esta clase se utiliza para representar un tópico en el sistema del foro
 * Alura. Cada instancia de esta clase representa un tópico con sus atributos y
 * métodos asociados, con campos que incluyen título, mensaje, fecha de
 * creación, autor, curso, estado y número de respuestas. Esta clase se utiliza
 * para administrar y mantener información sobre los tópicos en el sistema.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
// Anotación para mapear la entidad 'Topico' a una tabla llamada 'topico' en la
// base de datos.
@Table(name = "topico")
// Anotación para marcar esta clase como una entidad JPA.
@Entity(name = "Topico")
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
// Utilizada para generar el método equals y hashCode basado en el campo 'id'.
@EqualsAndHashCode(of = "id")
public class Topico {

	// Anotación para especificar que este campo es la clave primaria y que se
	// generará automáticamente.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "titulo_topico")
	private String titulo;

	@Column(name = "mensaje_topico", columnDefinition = "TEXT")
	private String mensaje;

	@Column(name = "fecha_creacion")
	private LocalDateTime fecha = LocalDateTime.now();

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "autor_id", referencedColumnName = "id")
	private Usuario autor;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "curso_id", referencedColumnName = "id")
	private Curso curso;

	@Column(name = "estatus_topico")
	@Enumerated(EnumType.STRING)
	private StatusTopico estatus;

	@Column(name = "num_respuestas") // Nuevo campo para el número de respuestas
	private int numRespuestas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fechaCreacion) {
		this.fecha = fechaCreacion;
	}

	public StatusTopico getEstatus() {
		return estatus;
	}

	public void setEstatus(StatusTopico estatus) {
		this.estatus = estatus;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getNumRespuestas() {
		return numRespuestas;
	}

	public void setNumRespuestas(int numRespuestas) {
		this.numRespuestas = numRespuestas;
	}

	/**
	 * TopicoBuilder: Esta es una clase interna utilizada para construir objetos
	 * Topico de manera fluida. Permite establecer los valores de los campos de un
	 * tópico de forma encadenada.
	 */
	public static class TopicoBuilder {
		private String titulo;
		private String mensaje;
		private LocalDateTime fecha;
		private Curso curso;
		private Usuario autor;
		private StatusTopico estatus;
		private int numRespuestas; // Nuevo campo para el número de respuestas

		public TopicoBuilder titulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public TopicoBuilder mensaje(String mensaje) {
			this.mensaje = mensaje;
			return this;
		}

		public TopicoBuilder curso(Curso curso) {
			this.curso = curso;
			return this;
		}

		public TopicoBuilder autor(Usuario autor) {
			this.autor = autor;
			return this;
		}

		public TopicoBuilder estatus(StatusTopico estatus) {
			this.estatus = estatus;
			return this;
		}

		// Nuevo método para establecer el número de respuestas
		public TopicoBuilder numRespuestas(int numeroRespuestas) {
			this.numRespuestas = numeroRespuestas;
			return this;
		}

		// Método para construir un objeto Topico
		public Topico build() {
			// Aquí se construye y retorna la instancia de Topico con los datos establecidos
			Topico topico = new Topico();
			topico.setTitulo(this.titulo);
			topico.setMensaje(this.mensaje);
			topico.setFecha(this.fecha);
			topico.setCurso(this.curso);
			topico.setAutor(this.autor);
			topico.setEstatus(this.estatus);
			// Establecer el número de respuestas
			topico.setNumRespuestas(this.numRespuestas);

			return topico;
		}
	}

}
