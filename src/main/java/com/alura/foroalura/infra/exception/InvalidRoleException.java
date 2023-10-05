package com.alura.foroalura.infra.exception;

/**
 * InvalidRoleException es una excepción personalizada utilizada para
 * representar errores relacionados con roles de usuario inválidos en la
 * aplicación y proporciona información sobre la razón de la invalidez del rol
 * proporcionado.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String message) {
        super(message);
    }
}
