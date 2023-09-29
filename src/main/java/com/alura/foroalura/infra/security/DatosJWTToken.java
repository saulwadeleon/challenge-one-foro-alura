package com.alura.foroalura.infra.security;

/**
 * 
 * Es una clase de registro, que se generan automáticamente métodos como
 * equals(), hashCode(), toString(), etc., basados en el campo jWTToken. Esto
 * facilita la creación y el uso de objetos DatosJWTToken inmutables para
 * representar tokens JWT en su aplicación.
 * 
 */
public record DatosJWTToken(String jWTToken) {

}
