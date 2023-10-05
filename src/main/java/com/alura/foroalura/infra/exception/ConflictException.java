package com.alura.foroalura.infra.exception;

/**
 * 
 * ConflictException se utiliza para lanzar excepciones específicas de conflicto
 * en la aplicación cuando sea necesario. Al extender RuntimeException, se trata
 * de una excepción no verificada, lo que significa que no es necesario
 * declararla en la firma del método o capturarla explícitamente, lo que puede
 * ser útil en situaciones donde se necesita manejar errores específicos de
 * conflicto.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
// Clase que representa una excepción de conflicto personalizada.
public class ConflictException extends RuntimeException {

    // Constructor que acepta un mensaje de error y lo pasa al constructor de la
    // superclase.
    public ConflictException(String message) {
        super(message);
    }
}
