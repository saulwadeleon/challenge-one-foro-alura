package com.alura.foroalura.service;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.domain.topico.DatosActualizacionTopico;
import com.alura.foroalura.domain.topico.DatosRegistroTopico;
import com.alura.foroalura.domain.topico.StatusTopico;
import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.domain.topico.Topico.TopicoBuilder;
import com.alura.foroalura.domain.usuario.Usuario;
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
 * @author Saúl Wade León
 * @version 1.3
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

    /**
     * crearTopico permite crear un nuevo tópico. Recibe un objeto
     * DatosRegistroTopico que contiene los datos necesarios para crear el tópico.
     * El método realiza las siguientes acciones:
     * Obtiene al autor y al curso relacionados utilizando los nombres
     * proporcionados en datosRegistroTopico.
     * Utiliza un patrón Builder para crear el tópico con los datos proporcionados.
     * Establece el estado del tópico como "NO_RESPONDIDO" (esto se puede ajustar
     * según tus necesidades).
     * 
     * @param datosRegistroTopico
     * @return
     */
    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {

        Usuario autor = usuarioService.obtenerUsuarioPorNombre(datosRegistroTopico.autor());
        Curso curso = cursoService.obtenerCursoPorNombre(datosRegistroTopico.curso());

        Topico nuevoTopico = new TopicoBuilder()
                .titulo(datosRegistroTopico.titulo())
                .mensaje(datosRegistroTopico.mensaje())
                .curso(curso)
                .autor(autor)
                .estatus(StatusTopico.NO_RESPONDIDO) // Se puede ajustar el estatus predeterminado
                .build();

        return topicoRepository.save(nuevoTopico);
    }

    /**
     * obtenerTodosLosTopicos devuelve una lista de todos los tópicos almacenados en
     * la base de datos. Utiliza el método findAll() proporcionado por el
     * repositorio.
     * 
     * @return
     */
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    /**
     * obtenerTopicoPorId permite obtener un tópico específico por su identificador
     * (id). Utiliza el método findById(id) del repositorio y retorna el tópico si
     * se encuentra; de lo contrario, retorna null.
     * 
     * @param id
     * @return
     */
    public Topico obtenerTopicoPorId(Long id) {
        return topicoRepository.findById(id).orElse(null);
    }

    /**
     * actualizarTopico actualiza un tópico existente por su identificador (id).
     * Primero, obtiene el tópico existente utilizando obtenerTopicoPorId(id).
     * Luego, actualiza los campos relevantes del tópico existente con los datos de
     * actualización proporcionados. Finalmente, utiliza el repositorio para guardar
     * el tópico actualizado.
     * 
     * @param id
     * @param datosActualizacionTopico
     * @return
     */
    public Topico actualizarTopico(Long id, DatosActualizacionTopico datosActualizacionTopico) {

        Topico topicoExistente = obtenerTopicoPorId(id);
        if (topicoExistente != null) {
            Usuario autor = usuarioService.obtenerUsuarioPorNombre(datosActualizacionTopico.autor());
            Curso curso = cursoService.obtenerCursoPorNombre(datosActualizacionTopico.curso());

            topicoExistente.setMensaje(datosActualizacionTopico.mensaje());
            topicoExistente.setAutor(autor);
            topicoExistente.setCurso(curso);
            topicoExistente.setEstatus(datosActualizacionTopico.estatus());
            topicoExistente.setFecha(LocalDateTime.now());

            return topicoRepository.save(topicoExistente);
        }
        return null;
    }

    /**
     * eliminarTopico permite eliminar un tópico por su identificador (id).
     * 
     * @param id
     */
    public void eliminarTopico(Long id) {
        topicoRepository.deleteById(id);
    }

    /**
     * existeTopicoConTituloYMensaje verifica si existe un tópico en la base de
     * datos con un título y mensaje específicos, definido en las reglas del
     * negocio. Retorna true si existe y false en caso contrario.
     * 
     * @param titulo
     * @param mensaje
     * @return
     */
    public boolean existeTopicoConTituloYMensaje(String titulo, String mensaje) {
        return topicoRepository.existsByTituloAndMensaje(titulo, mensaje);
    }

    /**
     * existsById verifica si existe un tópico en la base de datos con el
     * identificador proporcionado (id). Retorna true si existe y false en caso
     * contrario.
     * 
     * @param id
     * @return
     */
    public boolean existsById(Long id) {
        return topicoRepository.existsById(id);
    }

    /**
     * obtenerTopicoPorTitulo permite obtener un tópico por su título.
     * 
     * @param topico
     * @return
     */
    public Topico obtenerTopicoPorTitulo(String topico) {
        return topicoRepository.findByTitulo(topico);
    }
}