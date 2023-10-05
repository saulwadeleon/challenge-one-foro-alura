package com.alura.foroalura.infra.exception;

/**
 * ForbiddenException es una excepción personalizada utilizada para representar
 * errores de acceso prohibido en la aplicación y proporciona información sobre
 * la razón de la denegación de acceso.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
