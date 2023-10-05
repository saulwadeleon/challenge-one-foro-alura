package com.alura.foroalura.domain.respuesta;

/**
 * La clase DatosActualizacionRespuesta es una clase de registro que se utiliza
 * para representar los datos de actualización de una respuesta en el sistema.
 * Esta clase tiene dos campos: mensaje y solucion. Además, proporciona un
 * constructor adicional que permite crear una instancia de la clase a partir de
 * una instancia existente de la clase Respuesta.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosActualizacionRespuesta(String mensaje, boolean solucion) {
    public DatosActualizacionRespuesta(Respuesta respuestaActualizada) {
        this(respuestaActualizada.getMensaje(), respuestaActualizada.getSolucion());
    }

}
