package com.alura.foroalura.domain.respuesta;

import java.time.LocalDateTime;

import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotación para mapear la entidad 'Respuesta' a una tabla llamada 'respuesta' en la base de datos.
@Table(name = "respuesta")
// Anotación para marcar esta clase como una entidad JPA.
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
// Utilizada para generar el método equals y hashCode basado en el campo 'id'.
@EqualsAndHashCode(of = "id")
public class Respuesta {

	// Anotación para especificar que este campo es la clave primaria y que se
	// generará automáticamente.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "mensaje_respuesta", columnDefinition = "TEXT")
	private String mensaje;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topicoId", referencedColumnName = "id")
	private Topico topico;

	@Column(name = "fecha_respuesta")
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autorId", referencedColumnName = "id")
	private Usuario autor;

	@Column(name = "solucion")
	private Boolean solucion = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public LocalDateTime getfechaCreacion() {
		return fechaCreacion;
	}

	public void setfechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Boolean getSolucion() {
		return solucion;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}

}
