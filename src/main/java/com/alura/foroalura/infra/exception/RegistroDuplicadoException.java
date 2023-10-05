package com.alura.foroalura.infra.exception;

/**
 * RegistroDuplicadoException es una excepción personalizada utilizada para
 * representar errores relacionados con registros duplicados en la aplicación y
 * proporciona información sobre la razón de la duplicación del registro.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public class RegistroDuplicadoException extends RuntimeException {
    public RegistroDuplicadoException(String message) {
        super(message);
    }
}
