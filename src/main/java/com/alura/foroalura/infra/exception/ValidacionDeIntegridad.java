package com.alura.foroalura.infra.exception;

public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String mensaje) {
        super(mensaje);
    }
}
