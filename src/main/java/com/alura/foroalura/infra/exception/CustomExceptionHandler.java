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

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // Manejar ForbiddenException
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorResponse handleForbiddenException(ForbiddenException ex) {
        // Crea una instancia de ErrorResponse o cualquier clase que desees para el
        // cuerpo de la respuesta
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorMessage("Acceso prohibido: " + ex.getMessage());
        return (ErrorResponse) errorResponse;
    }

    // Manejar EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrorMessage("Recurso no encontrado: " + ex.getMessage());
        return new ResponseEntity<CustomErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Manejar UsernameAlreadyExistsException
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorMessage("El nombre de usuario ya está en uso: " + ex.getMessage());
        return (ErrorResponse) errorResponse;
    }

    @ExceptionHandler(InvalidRoleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleInvalidRoleException(InvalidRoleException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse();
        errorResponse.setErrorMessage("El rolo de usuario proporcionado no es válido: " + ex.getMessage());
        return (ErrorResponse) errorResponse;
    }

}
