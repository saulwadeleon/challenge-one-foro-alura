package com.alura.foroalura.infra.exception;

/**
 * UsernameAlreadyExistsException es una excepción personalizada utilizada para
 * representar errores relacionados con la duplicación de nombres de usuario en
 * la aplicación y proporciona información sobre la razón de la duplicación del
 * nombre de usuario.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
