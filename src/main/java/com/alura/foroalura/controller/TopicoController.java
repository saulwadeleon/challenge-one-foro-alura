package com.alura.foroalura.controller;

import com.alura.foroalura.domain.topico.*;
import com.alura.foroalura.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta clase es un controlador de Spring que maneja las solicitudes
 * relacionadas con los tópicos en la aplicación del foro. Proporciona endpoints
 * para crear, obtener, actualizar y eliminar tópicos, así como para consultar
 * todos los tópicos disponibles.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // Endpoint para crear un nuevo tópico
    @PostMapping
    @Transactional
    @Operation(summary = "Crear un nuevo tópico")
    /**
     * Este método maneja las solicitudes POST en la ruta "/topicos"
     * y se encarga de crear un nuevo tópico.
     * 
     * @param datosRegistroTopico
     * @return
     */
    public ResponseEntity<Topico> crearTopico(@Validated @RequestBody DatosRegistroTopico datosRegistroTopico) {
        // Validar que el título y mensaje sean únicos antes de crear el tópico
        if (topicoService.existeTopicoConTituloYMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            Topico nuevoTopico = topicoService.crearTopico(datosRegistroTopico);
            return new ResponseEntity<>(nuevoTopico, HttpStatus.CREATED);
        }
    }

    // Endpoint para obtener todos los tópicos
    @GetMapping
    @Operation(summary = "Obtener una lista con todos los tópicos")
    /**
     * Este método maneja las solicitudes GET en la ruta "/topicos" y se encarga de
     * obtener todos los tópicos disponibles.
     * 
     * @return
     */
    public ResponseEntity<List<DatosInfoTopico>> obtenerTodosLosTopicos() {
        List<Topico> topicos = topicoService.obtenerTodosLosTopicos();
        List<DatosInfoTopico> topicosInfo = topicos.stream()
                .map(DatosInfoTopico::new) // Convierte Topico a DatosInfoTopico
                .collect(Collectors.toList());

        return new ResponseEntity<>(topicosInfo, HttpStatus.OK);
    }

    // Endpoint para obtener un tópico por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un tópico por su ID")
    /**
     * Este método maneja las solicitudes GET en la ruta "/topicos/{id}"
     * y se encarga de obtener un tópico por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<Topico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.obtenerTopicoPorId(id);
        if (topico != null) {
            return new ResponseEntity<>(topico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar un tópico por su ID
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar un topico por su ID")
    /**
     * Este método maneja las solicitudes PUT en la ruta "/topicos/{id}"
     * y se encarga de actualizar un tópico por su ID.
     * 
     * @param id
     * @param datosActualizacionTopico
     * @return
     */
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id,
            @RequestBody DatosActualizacionTopico datosActualizacionTopico) {
        Topico topico = topicoService.actualizarTopico(id, datosActualizacionTopico);
        if (topico != null) {
            return new ResponseEntity<>(topico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un tópico por su ID
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar un tópico por su ID")
    /**
     * Este método maneja las solicitudes DELETE en la ruta "/topicos"
     * y se encarga de eliminar un tópico por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<String> eliminarTopico(@PathVariable Long id) {
        if (topicoService.existsById(id)) {
            topicoService.eliminarTopico(id);
            return new ResponseEntity<>("El topico fue eliminado exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("El topico que intenta eliminar no existe", HttpStatus.NOT_FOUND);
        }
    }
}
