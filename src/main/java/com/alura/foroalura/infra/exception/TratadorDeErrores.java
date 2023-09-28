package com.alura.foroalura.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * 
 * TratadorDeErrores se utiliza para manejar excepciones específicas
 * (EntityNotFoundException y MethodArgumentNotValidException) y proporcionar
 * respuestas HTTP adecuadas en función del tipo de excepción que se lance. Esto
 * es útil para personalizar las respuestas de error en una aplicación Spring.
 * 
 */
@RestControllerAdvice
public class TratadorDeErrores {

    // Este método maneja excepciones del tipo EntityNotFoundException.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarError404(EntityNotFoundException e) {
        String mensaje = "Recurso no encontrado: " + e.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
    }

    // Este método maneja excepciones del tipo MethodArgumentNotValidException.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarError400(MethodArgumentNotValidException e) {
        // Convierte los errores de validación en una lista de objetos
        // DatosErrorValidacion.
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        // Responde con un ResponseEntity con estado HTTP 400 (Bad Request) que incluye
        // los detalles de los errores de validación en el cuerpo.
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> errorHandlerValidacionesIntegridad(ConflictException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> errorHandlerValidacionesDeNegocio(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity<?> HandlerValidacionesDeNegocio(ValidacionDeIntegridad e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> tratarErrorIdInvalido(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("ID inválido: " + e.getMessage());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<?> handlePropertyReferenceException(PropertyReferenceException e) {
        String mensajeError = "Propiedad no encontrada en la entidad: " + e.getPropertyName();
        return ResponseEntity.badRequest().body(mensajeError);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> manejarTokenExpirado(TokenExpiredException ex) {
        String mensaje = "El token JWT ha expirado. Debes iniciar sesión nuevamente.";
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(mensaje);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> manejarNullPointerException(NullPointerException ex) {
        String mensaje = "Se ha producido un error interno en el servidor.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
    }

    // Esta es una clase interna privada que se utiliza para representar los
    // detalles de un error de validación.
    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
