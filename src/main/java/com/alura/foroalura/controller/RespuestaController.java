package com.alura.foroalura.controller;

import com.alura.foroalura.domain.respuesta.DatosActualizacionRespuesta;
import com.alura.foroalura.domain.respuesta.DatosConsultaRespuesta;
import com.alura.foroalura.domain.respuesta.DatosRegistroRespuesta;
import com.alura.foroalura.domain.respuesta.Respuesta;
import com.alura.foroalura.service.RespuestaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta clase es un controlador de Spring que maneja las solicitudes
 * relacionadas con las respuestas en la aplicación del foro. Proporciona
 * endpoints para crear, obtener, actualizar y eliminar respuestas, así como
 * para consultar respuestas por tópico.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    private final RespuestaService respuestaService;

    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    // Endpoint para crear una nueva respuesta
    @PostMapping
    @Transactional
    @Operation(summary = "Crear una nueva respuesta")
    /**
     * Este método maneja las solicitudes POST en la ruta "/respuestas" y se encarga
     * de crear una nueva respuesta.
     * 
     * @param datosRespuesta
     * @return
     */
    public ResponseEntity<Respuesta> crearRespuesta(@RequestBody @Valid DatosRegistroRespuesta datosRespuesta) {
        Respuesta respuesta = respuestaService.crearRespuesta(datosRespuesta);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las respuestas
    @GetMapping
    @Operation(summary = "Obtener una lista con todas las respuestas")
    /**
     * Este método maneja las solicitudes GET en la ruta "/respuestas"
     * y se encarga de obtener todas las respuestas.
     * 
     * @return
     */
    public ResponseEntity<List<DatosConsultaRespuesta>> obtenerTodasLasRespuestas() {
        List<Respuesta> respuestas = respuestaService.obtenerTodasLasRespuestas();
        List<DatosConsultaRespuesta> datosRespuestas = respuestas.stream()
                .map(DatosConsultaRespuesta::new) // Convierte Usuario a DatosDelUsuario
                .collect(Collectors.toList());

        return new ResponseEntity<>(datosRespuestas, HttpStatus.OK);
    }

    // Endpoint para obtener una respuesta por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una respuesta por su ID")
    /**
     * Este método maneja las solicitudes GET en la ruta "/respuestas/{id}"
     * y se encarga de obtener una respuesta por su ID.
     * 
     * @param id
     */
    public ResponseEntity<DatosConsultaRespuesta> obtenerRespuestaPorId(@PathVariable Long id) {
        Respuesta respuesta = respuestaService.obtenerRespuestaPorId(id);
        if (respuesta != null) {
            DatosConsultaRespuesta datosRespuesta = new DatosConsultaRespuesta(respuesta);
            return new ResponseEntity<>(datosRespuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener las respuesta por el ID de un tópico
    @GetMapping("/topico/{topicoId}")
    @Operation(summary = "Obtener todas las respuestas para un tópico por su ID")
    /**
     * Este método maneja las solicitudes GET en la ruta
     * "/respuestas/topico/{topicoId}" y se encarga de obtener respuestas por el ID
     * de un tópico.
     * 
     * @param topicoId
     * @return
     */
    public ResponseEntity<List<DatosConsultaRespuesta>> obtenerRespuestaPorIdTopico(@PathVariable Long topicoId) {
        List<DatosConsultaRespuesta> datosRespuestas = respuestaService.obtenerTodasLasRespuestasPorTopicoId(topicoId);
        if (!datosRespuestas.isEmpty()) {
            return new ResponseEntity<>(datosRespuestas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar una respuesta por su ID
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar una respuesta por su ID")
    /**
     * Este método maneja las solicitudes PUT en la ruta "/respuestas/{id}"
     * y se encarga de actualizar una respuesta por su ID.
     * 
     * @param id
     * @param datosActualizacionRespuesta
     * @return
     */
    public ResponseEntity<Respuesta> actualizarRespuesta(@PathVariable Long id,
            @RequestBody DatosActualizacionRespuesta datosActualizacionRespuesta) {
        Respuesta respuesta = respuestaService.actualizarRespuesta(id, datosActualizacionRespuesta);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar una respuesta por su ID")
    /**
     * Este método maneja las solicitudes DELETE en la ruta "/respuestas/{id}"
     * y se encarga de eliminar una respuesta por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<String> eliminarRespuesta(@PathVariable Long id) {
        if (respuestaService.existsById(id)) {
            respuestaService.eliminarRespuesta(id);
            return new ResponseEntity<>("La respuesta fue eliminada exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("La respuesta que intenta eliminar no existe", HttpStatus.NOT_FOUND);
        }
    }

}
