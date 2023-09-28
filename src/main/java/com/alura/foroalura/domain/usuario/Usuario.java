package com.alura.foroalura.domain.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alura.foroalura.infra.security.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Define la tabla de base de datos a la que se asignará esta entidad.
@Table(name = "usuario")
// Define que esta clase es una entidad de persistencia.
@Entity(name = "Usuario")
// Genera automáticamente métodos getters para todos los campos de la clase.
@Getter
// Genera un constructor sin argumentos.
@NoArgsConstructor
// Genera un constructor que acepta todos los campos como argumentos.
@AllArgsConstructor
// Genera automáticamente los métodos equals y hashCode basados en el campo
// 'id'.
@EqualsAndHashCode(of = "id")
/**
 * 
 * Esta clase representa un usuario en una aplicación Spring Security.
 * Implementa la interfaz UserDetails para proporcionar información necesaria
 * para la autenticación y la autorización. Además, utiliza anotaciones de
 * persistencia para mapear la entidad a una tabla de base de datos y aprovecha
 * las capacidades de Lombok para reducir la cantidad de código boilerplate
 * necesario.
 * 
 */
public class Usuario implements UserDetails {

	// Marca este campo como la clave primaria de la entidad.
	@Id
	// Especifica que el valor de 'id' se generará automáticamente utilizando una
	// estrategia de identidad.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	@Column(name = "email")
	private String email;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id")
	private Role role;

	private List<Role> roles;

	private boolean activo;

	// Constructor que acepta un nombre de usuario y una contraseña como argumentos.
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Constructor que acepta un String como nombre de usuario
	public Usuario(String username) {
		this.username = username;
		// Inicializa otros atributos si es necesario
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Devuelve una colección de roles o autoridades del usuario.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();

		// Agrega el rol a la lista de autoridades
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNombre()));
		return authorities;
	}

	// Devuelve la contraseña del usuario.
	@Override
	public String getPassword() {
		return password;
	}

	// Devuelve el nombre de usuario del usuario.
	@Override
	public String getUsername() {
		return username;
	}

	// Indica si la cuenta del usuario no ha expirado (siempre devuelve true).
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// Indica si la cuenta del usuario no está bloqueada (siempre devuelve true).
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// Indica si las credenciales del usuario no han expirado (siempre devuelve
	// true).
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// Indica si la cuenta del usuario está habilitada (siempre devuelve true).
	@Override
	public boolean isEnabled() {
		return true;
	}

	public String[] getRole() {
		return new String[] { "ROLE_" + role.getNombre() };
	}

}
