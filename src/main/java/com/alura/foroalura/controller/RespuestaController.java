package com.alura.foroalura.controller;

import com.alura.foroalura.domain.respuesta.Respuesta;
import com.alura.foroalura.service.RespuestaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Respuesta> crearRespuesta(@RequestBody Respuesta respuesta) {
        Respuesta nuevaRespuesta = respuestaService.crearRespuesta(respuesta);
        return new ResponseEntity<>(nuevaRespuesta, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las respuestas
    @GetMapping
    @Operation(summary = "Obtener una lista con todas las respuestas")
    public ResponseEntity<List<Respuesta>> obtenerTodasLasRespuestas() {
        List<Respuesta> respuestas = respuestaService.obtenerTodasLasRespuestas();
        return new ResponseEntity<>(respuestas, HttpStatus.OK);
    }

    // Endpoint para obtener una respuesta por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una respuesta por su ID")
    public ResponseEntity<Respuesta> obtenerRespuestaPorId(@PathVariable Long id) {
        Respuesta respuesta = respuestaService.obtenerRespuestaPorId(id);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar una respuesta por su ID
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar una respuesta por su ID")
    public ResponseEntity<Respuesta> actualizarRespuesta(@PathVariable Long id,
            @RequestBody Respuesta respuestaActualizada) {
        Respuesta respuesta = respuestaService.actualizarRespuesta(id, respuestaActualizada);
        if (respuesta != null) {
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una respuesta por su ID
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar una respuesta por su ID")
    public ResponseEntity<Void> eliminarRespuesta(@PathVariable Long id) {
        respuestaService.eliminarRespuesta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
