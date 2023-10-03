package com.alura.foroalura.domain.topico;

// Clase de registro que representa los datos para actualizar un topico.
public record DatosActualizacionTopico(
                String mensaje,
                String autor,
                String curso,
                StatusTopico estatus) {

        public DatosActualizacionTopico(Topico topicoActualizado) {
                this(
                                topicoActualizado.getMensaje(),
                                topicoActualizado.getAutor().getNombre(),
                                topicoActualizado.getCurso().getNombre(),
                                topicoActualizado.getEstatus());
        }

}
