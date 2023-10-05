package com.alura.foroalura.service;

import com.alura.foroalura.domain.respuesta.DatosActualizacionRespuesta;
import com.alura.foroalura.domain.respuesta.DatosConsultaRespuesta;
import com.alura.foroalura.domain.respuesta.DatosRegistroRespuesta;
import com.alura.foroalura.domain.respuesta.Respuesta;
import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.domain.usuario.Usuario;
import com.alura.foroalura.repository.RespuestaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * RespuestaService proporciona métodos para crear, obtener, actualizar y
 * eliminar respuestas en la entidad Respuesta. También ofrece la capacidad de
 * consultar todas las respuestas asociadas a un tópico específico. Este
 * servicio permite gestionar las respuestas en la aplicación y mantener la
 * coherencia de los datos relacionados con los tópicos y los usuarios.
 * 
 * @author Saúl Wade León
 * @version 1.3
 * 
 */
@Service
public class RespuestaService {

    @Autowired
    private final RespuestaRepository respuestaRepository;

    @Autowired
    private TopicoService topicoService;

    @Autowired
    private UsuarioService usuarioService;

    public RespuestaService(RespuestaRepository respuestaRepository, TopicoService topicoService,
            UsuarioService usuarioService) {
        this.respuestaRepository = respuestaRepository;
        this.topicoService = topicoService;
        this.usuarioService = usuarioService;
    }

    /**
     * crearRespuesta permite crear una nueva respuesta. Recibe un objeto
     * DatosRegistroRespuesta que contiene los datos necesarios para crear la
     * respuesta.
     * 
     * @param datosRespuesta
     * @return
     */
    public Respuesta crearRespuesta(DatosRegistroRespuesta datosRespuesta) {

        // Obtener el tópico y el usuario utilizando los identificadores
        Topico topico = topicoService.obtenerTopicoPorTitulo(datosRespuesta.titulo());
        Usuario autor = usuarioService.obtenerUsuarioPorNombre(datosRespuesta.autor());

        // Usaamos el patrón Builder para crear la respuesta
        Respuesta respuesta = new Respuesta.RespuestaBuilder()
                .conMensaje(datosRespuesta.mensaje())
                .conTopico(topico)
                .conAutor(autor)
                .conSolucion(datosRespuesta.solucion())
                .build();
        // Obtener el número de respuestas actual del tópico
        int numRespuestasActual = topico.getNumRespuestas();

        // Incrementar el número de respuestas actual en uno
        numRespuestasActual++;

        // Actualizar el número de respuestas del tópico
        topico.setNumRespuestas(numRespuestasActual);

        return respuestaRepository.save(respuesta);
    }

    /**
     * obtenerTodasLasRespuestas devuelve una lista de todas las respuestas
     * almacenadas en la base de datos. Utiliza el método findAll() proporcionado
     * por el repositorio.
     * 
     * @return
     */
    public List<Respuesta> obtenerTodasLasRespuestas() {
        return respuestaRepository.findAll();
    }

    /**
     * obtenerRespuestaPorId permite obtener una respuesta específica por su
     * identificador (id). Utiliza el método findById(id) del repositorio y retorna
     * la respuesta si se encuentra; de lo contrario, retorna null.
     * 
     * @param id
     * @return
     */
    public Respuesta obtenerRespuestaPorId(Long id) {
        return respuestaRepository.findById(id).orElse(null);
    }

    /**
     * ctualizarRespuesta actualiza una respuesta existente por su identificador
     * (id). Primero, obtiene la respuesta existente utilizando
     * obtenerRespuestaPorId(id). Luego, actualiza los campos relevantes de la
     * respuesta existente con los datos de la respuesta actualizada. Finalmente,
     * utiliza el repositorio para guardar la respuesta actualizada.
     * 
     * @param id
     * @param datosActualizacionRespuesta
     * @return
     */
    public Respuesta actualizarRespuesta(Long id, DatosActualizacionRespuesta datosActualizacionRespuesta) {
        Respuesta respuestaExistente = obtenerRespuestaPorId(id);
        if (respuestaExistente != null) {
            respuestaExistente.setMensaje(datosActualizacionRespuesta.mensaje());
            respuestaExistente.setSolucion(datosActualizacionRespuesta.solucion());

            return respuestaRepository.save(respuestaExistente);
        }
        return null;
    }

    /**
     * eliminarRespuesta permite eliminar una respuesta por su identificador (id).
     * También actualiza el número de respuestas actual del tópico al que pertenece
     * la respuesta, decrementando en uno.
     * 
     * @param id
     */
    public void eliminarRespuesta(Long id) {
        Topico topico = respuestaRepository.findById(id).get().getTopico();

        // Obtener el número de respuestas actual del tópico
        int numRespuestasActual = topico.getNumRespuestas();

        // Incrementar el número de respuestas actual en uno
        numRespuestasActual--;

        // Actualizar el número de respuestas del tópico
        topico.setNumRespuestas(numRespuestasActual);

        respuestaRepository.deleteById(id);
    }

    /**
     * existsById verifica si existe una respuesta en la base de datos con el
     * identificador proporcionado (id). Retorna true si existe y false en caso
     * contrario.
     * 
     * @param id
     * @return
     */
    public boolean existsById(Long id) {
        return respuestaRepository.existsById(id);
    }

    /**
     * obtenerTodasLasRespuestasPorTopicoId obtiene todas las respuestas asociadas a
     * un tópico específico por su identificador (topicoId). Utiliza el método
     * findByTopicoId(topicoId) del repositorio y transforma las respuestas en
     * objetos DatosConsultaRespuesta que contienen la información necesaria para
     * consultarlas.
     * 
     * @param topicoId
     * @return
     */
    public List<DatosConsultaRespuesta> obtenerTodasLasRespuestasPorTopicoId(Long topicoId) {
        List<Respuesta> respuestas = respuestaRepository.findByTopicoId(topicoId);
        List<DatosConsultaRespuesta> datosRespuestas = new ArrayList<>();
        for (Respuesta respuesta : respuestas) {
            DatosConsultaRespuesta datosRespuesta = new DatosConsultaRespuesta(respuesta);
            datosRespuestas.add(datosRespuesta);
        }
        return datosRespuestas;
    }

}
