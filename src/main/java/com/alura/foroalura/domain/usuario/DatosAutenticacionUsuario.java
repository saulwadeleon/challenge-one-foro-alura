package com.alura.foroalura.domain.usuario;

/**
 * 
 * DatosAutenticacionUsuario se utiliza para representar un conjunto de datos
 * que contiene un nombre de usuario (login) y una contraseña (password). Dado
 * que es un registro, proporciona métodos automáticos como equals, hashCode y
 * toString basados en los componentes, y es inmutable, lo que significa que una
 * vez que se crea una instancia, sus valores no pueden modificarse.
 * 
 */
public record DatosAutenticacionUsuario(String username, String password) {
}
