package com.alura.foroalura.domain.topico;

import java.time.LocalDateTime;

// Clase de registro que representa los datos para listar un topico.
public record DatosInfoTopico(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        StatusTopico estatus,
        String autor,
        String curso) {

    public DatosInfoTopico(Topico topico) {
        this(topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha(),
                topico.getEstatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre());

    }
}
