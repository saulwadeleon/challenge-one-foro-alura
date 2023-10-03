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

// Anotación para mapear la entidad 'Topico' a una tabla llamada 'topico' en la base de datos.
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id", referencedColumnName = "id")
	private Usuario autor;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curso_id", referencedColumnName = "id")
	private Curso curso;

	@Column(name = "estatus_topico")
	@Enumerated(EnumType.STRING)
	private StatusTopico estatus;

	public Topico(DatosRegistroTopico datosRegistroTopico, Curso curso, Usuario autor) {
		this.titulo = datosRegistroTopico.titulo();
		this.mensaje = datosRegistroTopico.mensaje();
		this.curso = curso;
		this.autor = autor;
		this.estatus = datosRegistroTopico.estatus();
	}

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

	/*
	 * public List<Respuesta> getRespuestas() {
	 * return respuestas;
	 * }
	 * 
	 * public void setRespuestas(List<Respuesta> respuestas) {
	 * this.respuestas = respuestas;
	 * }
	 */

}
