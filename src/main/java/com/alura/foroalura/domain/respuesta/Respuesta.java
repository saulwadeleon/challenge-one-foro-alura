package com.alura.foroalura.domain.respuesta;

import java.time.LocalDateTime;

import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.domain.usuario.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * La clase Respuesta representa una entidad en la base de datos que se utiliza
 * para almacenar información relacionada con las respuestas a los tópicos en el
 * sistema.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "mensaje_respuesta", columnDefinition = "TEXT")
	private String mensaje;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "topico_id", referencedColumnName = "id")
	private Topico topico;

	@Column(name = "fecha_respuesta")
	private LocalDateTime fechaCreacion = LocalDateTime.now();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "autor_id", referencedColumnName = "id")
	private Usuario autor;

	@Column(name = "solucion")
	private Boolean solucion = false;

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public void setSolucion(Boolean solucion) {
		this.solucion = solucion;
	}

	/**
	 * Dentro de la clase Respuesta, se define un Builder llamado RespuestaBuilder
	 * que permite construir instancias de Respuesta de manera más conveniente. El
	 * Builder tiene métodos para establecer los diferentes campos de la respuesta,
	 * incluyendo el mensaje, el tópico, el autor y si se considera una solución.
	 * Luego, el método build() crea una instancia de Respuesta con los valores
	 * especificados.
	 * 
	 */
	public static class RespuestaBuilder {
		private String mensaje;
		private Topico topico;
		private Usuario autor;
		private Boolean solucion = false;

		public RespuestaBuilder conMensaje(String mensaje) {
			this.mensaje = mensaje;
			return this;
		}

		public RespuestaBuilder conTopico(Topico topico) {
			this.topico = topico;
			return this;
		}

		public RespuestaBuilder conAutor(Usuario autor) {
			this.autor = autor;
			return this;
		}

		public RespuestaBuilder conSolucion(Boolean solucion) {
			this.solucion = solucion;
			return this;
		}

		public Respuesta build() {
			Respuesta respuesta = new Respuesta();
			respuesta.setMensaje(this.mensaje);
			respuesta.setTopico(this.topico);
			respuesta.setAutor(this.autor);
			respuesta.setSolucion(this.solucion);
			respuesta.setFechaCreacion(LocalDateTime.now()); // Generar automáticamente la fecha de creación
			return respuesta;
		}
	}
}
