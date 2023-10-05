package com.alura.foroalura.domain.topico;

/**
 * La clase DatosActualizaciónTopico es un registro que se utiliza para
 * representar los datos necesarios para actualizar un tópico en el sistema.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
public record DatosActualizacionTopico(
                String mensaje,
                String autor,
                String curso,
                StatusTopico estatus) {

        /**
         * Se define un constructor adicional que toma un objeto Topico actualizado como
         * parámetro. Este constructor permite crear una instancia de
         * DatosActualizaciónTopico a partir de un tópico existente. Extrae los valores
         * del tópico actualizado y los utiliza para inicializar los campos
         * correspondientes de DatosActualizaciónTopico.
         * 
         * @param topicoActualizado
         */
        public DatosActualizacionTopico(Topico topicoActualizado) {
                this(
                                topicoActualizado.getMensaje(),
                                topicoActualizado.getAutor().getNombre(),
                                topicoActualizado.getCurso().getNombre(),
                                topicoActualizado.getEstatus());
        }

}
