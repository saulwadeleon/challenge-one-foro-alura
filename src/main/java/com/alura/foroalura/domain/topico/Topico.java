package com.alura.foroalura.domain.topico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.domain.respuesta.Respuesta;
import com.alura.foroalura.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotación para mapear la entidad 'Topico' a una tabla llamada 'topico' en la base de datos.
@Table(name = "topico")
// Anotación para marcar esta clase como una entidad JPA.
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
// Utilizada para generar el método equals y hashCode basado en el campo 'id'.
@EqualsAndHashCode(of = "id")
public class Topico {

	// Anotación para especificar que este campo es la clave primaria y que se
	// generará automáticamente.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_topico")
	private Long id;

	@Column(name = "titulo_topico")
	private String titulo;

	@Column(name = "mensaje_topico")
	private String mensaje;

	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@Column(name = "autor_topicoId")
	private Usuario autor;

	@Column(name = "curso_Id")
	private Curso curso;

	@Column(name = "estatus_topico")
	private StatusTopico status = StatusTopico.NO_RESPONDIDO;

	private List<Respuesta> respuestas = new ArrayList<>();

	public Topico(String titulo, String mensaje, Curso curso) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.curso = curso;
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

	public LocalDateTime getfechaCreacion() {
		return fechaCreacion;
	}

	public void setfechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public void setStatus(StatusTopico status) {
		this.status = status;
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

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}
