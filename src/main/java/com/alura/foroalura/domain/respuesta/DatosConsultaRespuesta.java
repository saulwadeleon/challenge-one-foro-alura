package com.alura.foroalura.domain.respuesta;

import java.time.LocalDateTime;

import com.alura.foroalura.domain.topico.StatusTopico;

/**
 * La clase DatosConsultaRespuesta es una clase de registro que se utiliza para
 * representar los datos de consulta de una respuesta a un topico en el sistema.
 * Esta clase agrupa varios campos relacionados con una respuesta y su tópico
 * asociado, así como la información del autor de la respuesta.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosConsultaRespuesta(
        String mensaje,
        String titulo,
        String mensajeTopico,
        LocalDateTime fecha,
        StatusTopico estatus,
        int numRespuestasActual,
        String nombre,
        String apellido,
        String email,
        String username,
        String role,
        boolean solucion) {

    public DatosConsultaRespuesta(Respuesta respuesta) {
        this(respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getTopico().getMensaje(),
                respuesta.getTopico().getFecha(),
                respuesta.getTopico().getEstatus(),
                respuesta.getTopico().getNumRespuestas(),
                respuesta.getAutor().getNombre(),
                respuesta.getAutor().getApellido(),
                respuesta.getAutor().getEmail(),
                respuesta.getAutor().getUsername(),
                respuesta.getAutor().getRoleName(),
                respuesta.getSolucion());
    }

}
