package com.alura.foroalura.service;

import com.alura.foroalura.domain.respuesta.Respuesta;
import com.alura.foroalura.repository.RespuestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespuestaService {

    @Autowired
    private final RespuestaRepository respuestaRepository;

    public RespuestaService(RespuestaRepository respuestaRepository) {
        this.respuestaRepository = respuestaRepository;
    }

    // Método para crear una nueva respuesta
    public Respuesta crearRespuesta(Respuesta respuesta) {
        // Puedes realizar validaciones u operaciones adicionales aquí antes de guardar
        // la respuesta en la base de datos.
        return respuestaRepository.save(respuesta);
    }

    // Método para obtener todas las respuestas
    public List<Respuesta> obtenerTodasLasRespuestas() {
        return respuestaRepository.findAll();
    }

    // Método para obtener una respuesta por su ID
    public Respuesta obtenerRespuestaPorId(Long id) {
        return respuestaRepository.findById(id).orElse(null);
    }

    // Método para actualizar una respuesta por su ID
    public Respuesta actualizarRespuesta(Long id, Respuesta respuestaActualizada) {
        Respuesta respuestaExistente = obtenerRespuestaPorId(id);
        if (respuestaExistente != null) {
            // Actualizar los campos relevantes de la respuesta existente con los datos de
            // la respuesta actualizada
            respuestaExistente.setMensaje(respuestaActualizada.getMensaje());
            respuestaExistente.setSolucion(respuestaActualizada.getSolucion());
            // Puedes realizar más actualizaciones aquí según tus necesidades.

            return respuestaRepository.save(respuestaExistente);
        }
        return null; // Retornar null si la respuesta no se encuentra
    }

    // Método para eliminar una respuesta por su ID
    public void eliminarRespuesta(Long id) {
        respuestaRepository.deleteById(id);
    }
}
