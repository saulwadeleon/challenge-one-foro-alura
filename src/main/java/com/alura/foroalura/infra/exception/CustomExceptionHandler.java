package com.alura.foroalura.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import jakarta.persistence.EntityNotFoundException;

/**
 * La clase CustomExceptionHandler proporciona un manejo de excepciones
 * personalizado para diferentes situaciones en la aplicación. Cada
 * método @ExceptionHandler se encarga de manejar una excepción específica y
 * devuelve una respuesta de error adecuada con detalles sobre el error. Esto
 * ayuda a mejorar la respuesta a errores en la aplicación y proporciona
 * información útil al cliente sobre lo que salió mal.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // Manejar ForbiddenException
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    /**
     * El método handleForbiddenException maneja la excepción ForbiddenException,
     * que se lanza cuando se produce un acceso prohibido. Este método devuelve una
     * respuesta de error con un código de estado HTTP 403 (Forbidden) y un mensaje
     * personalizado.
     * 
     * @param ex
     * @return
     */
    public ErrorResponse handleForbiddenException(ForbiddenException ex) {
        // Crea una instancia de ErrorResponse o cualquier clase que desees para el
        // cuerpo de la respuesta
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorMessage("Acceso prohibido: " + ex.getMessage());
        return (ErrorResponse) errorResponse;
    }

    // Manejar EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    /**
     * El método handleEntityNotFoundException maneja la excepción
     * EntityNotFoundException, que se lanza cuando no se encuentra una entidad en
     * la base de datos. Este método crea una instancia de CustomErrorResponse con
     * un código de estado HTTP 404 (Not Found) y un mensaje personalizado.
     * 
     * @param ex
     * @return
     */
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrorMessage("Recurso no encontrado: " + ex.getMessage());
        return new ResponseEntity<CustomErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    /**
     * El método handleUsernameAlreadyExistsException maneja la excepción
     * UsernameAlreadyExistsException, que se lanza cuando se intenta registrar un
     * nombre de usuario que ya está en uso. Devuelve una respuesta de error con un
     * código de estado HTTP 400 (Bad Request) y un mensaje personalizado.
     * 
     * @param ex
     * @return
     */
    public ErrorResponse handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorMessage("El nombre de usuario ya está en uso: " + ex.getMessage());
        return (ErrorResponse) errorResponse;
    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    /**
     * El método handleInvalidRoleException maneja la excepción
     * InvalidRoleException, que se lanza cuando se proporciona un rol de usuario no
     * válido. Devuelve una respuesta de error con un código de estado HTTP 400 (Bad
     * Request) y un mensaje personalizado.
     * 
     * @param ex
     * @return
     */
    public ErrorResponse handleInvalidRoleException(InvalidRoleException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorMessage("El role de usuario proporcionado no es válido: " + ex.getMessage());
        return (ErrorResponse) errorResponse;
    }

}
