package com.alura.foroalura.domain.topico;

import java.time.LocalDateTime;

/**
 * Esta clase se utiliza para encapsular los datos que se deben mostrar al
 * listar un tópico en el sistema, proporcionando una vista resumida de la
 * información del tópico.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosInfoTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        StatusTopico estatus,
        String autor,
        String curso) {

    /**
     * Se define un constructor adicional que toma un objeto Topico como parámetro.
     * Este constructor permite crear una instancia de DatosInfoTopico a partir de
     * un tópico existente. Extrae los valores del tópico y los utiliza para
     * inicializar los campos correspondientes de DatosInfoTopico.
     * 
     * @param topico
     */
    public DatosInfoTopico(Topico topico) {
        this(topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre());

    }
}
