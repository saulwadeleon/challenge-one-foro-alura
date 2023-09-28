package com.alura.foroalura.infra.security;

/**
 * DatosJWTToken es un registro de datos que representa un token JWT. Los
 * registros de datos son clases inmutables y se generan automáticamente métodos
 * como equals(), hashCode(), toString(), etc., basados en el campo jWTToken.
 * Esto facilita la creación y el uso de objetos DatosJWTToken inmutables para
 * representar tokens JWT en su aplicación.
 */
public record DatosJWTToken(String jWTToken) {

    // Crear instancias de DatosJWTToken de la siguiente manera:
    // DatosJWTToken token = new DatosJWTToken("mi_token_jwt");

    // Acceder al campo jWTToken de la siguiente manera:
    // String tokenString = token.jWTToken();

}
