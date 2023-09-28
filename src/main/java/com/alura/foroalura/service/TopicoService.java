package com.alura.foroalura.service;

import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private final TopicoRepository topicoRepository;

    public TopicoService(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    // Método para crear un nuevo tópico
    public Topico crearTopico(Topico topico) {
        // Puedes realizar validaciones u operaciones adicionales aquí antes de guardar
        // el tópico en la base de datos.
        return topicoRepository.save(topico);
    }

    // Método para obtener todos los tópicos
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    // Método para obtener un tópico por su ID
    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id).orElse(null);
    }

    // Método para actualizar un tópico por su ID
    public Topico actualizarTopico(Long id, Topico topicoActualizado) {
        Topico topicoExistente = obtenerTopicoPorId(id);
        if (topicoExistente != null) {
            // Actualizar los campos relevantes del tópico existente con los datos del
            // tópico actualizado
            topicoExistente.setTitulo(topicoActualizado.getTitulo());
            topicoExistente.setMensaje(topicoActualizado.getMensaje());
            // Puedes realizar más actualizaciones aquí según tus necesidades.

            return topicoRepository.save(topicoExistente);
        }
        return null; // Retornar null si el tópico no se encuentra
    }

    // Método para eliminar un tópico por su ID
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
