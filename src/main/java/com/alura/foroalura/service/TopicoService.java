package com.alura.foroalura.service;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.domain.topico.DatosActualizacionTopico;
import com.alura.foroalura.domain.topico.DatosRegistroTopico;
import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.infra.exception.RegistroDuplicadoException;
import com.alura.foroalura.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * La clase TopicoService proporciona una capa de servicio que encapsula la
 * lógica de negocio relacionada con la entidad Topico. Esta clase contiene
 * métodos para crear, leer, actualizar y eliminar tópicos. También se encarga
 * de realizar operaciones adicionales si es necesario, como validaciones antes
 * de guardar un tópico o realizar actualizaciones en un tópico existente.
 * 
 */
@Service
public class TopicoService {

    @Autowired
    private final TopicoRepository topicoRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final CursoService cursoService;

    public TopicoService(TopicoRepository topicoRepository, UsuarioService usuarioService, CursoService cursoService) {
        this.topicoRepository = topicoRepository;
        this.usuarioService = usuarioService;
        this.cursoService = cursoService;
    }

    // Método para crear un nuevo tópico
    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        // Validar que no existan registros duplicados con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datosRegistroTopico.titulo(),
                datosRegistroTopico.mensaje())) {
            throw new RegistroDuplicadoException("Ya existe un tópico con el mismo título y mensaje.");
        }

        // Obtener el autor y curso basado en los datos proporcionados
        Usuario autor = usuarioService.obtenerUsuarioPorNombre(datosRegistroTopico.autor());
        Curso curso = cursoService.obtenerCursoPorNombre(datosRegistroTopico.curso());

        // Crear el nuevo tópico
        Topico nuevoTopico = new Topico(datosRegistroTopico, curso, autor);

        // Topico nuevoTopico = new Topico(datosRegistroTopico.titulo(),
        // datosRegistroTopico.mensaje(), curso,
        // autor);

        return topicoRepository.save(nuevoTopico);
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
    public Topico actualizarTopico(Long id, DatosActualizacionTopico topicoActualizado) {
        Topico topicoExistente = obtenerTopicoPorId(id);
        if (topicoExistente != null) {
            // Obtener el autor y curso basado en los datos proporcionados
            Usuario autor = usuarioService.obtenerUsuarioPorNombre(topicoActualizado.autor());
            Curso curso = cursoService.obtenerCursoPorNombre(topicoActualizado.curso());

            // Actualizar los campos relevantes del tópico existente con los datos del
            // tópico actualizado
            topicoExistente.setMensaje(topicoActualizado.mensaje());
            topicoExistente.setAutor(autor);
            topicoExistente.setCurso(curso);
            topicoExistente.setEstatus(topicoActualizado.estatus());
            topicoExistente.setFecha(LocalDateTime.now());

            return topicoRepository.save(topicoExistente);
        }
        return null; // Retornar null si el tópico no se encuentra
    }

    // Método para eliminar un tópico por su ID
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }
}
