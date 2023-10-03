package com.alura.foroalura.controller;

import com.alura.foroalura.domain.topico.DatosActualizacionTopico;
import com.alura.foroalura.domain.topico.DatosInfoTopico;
import com.alura.foroalura.domain.topico.DatosRegistroTopico;
import com.alura.foroalura.domain.topico.Topico;
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
    public ResponseEntity<Topico> crearTopico(@Validated @RequestBody DatosRegistroTopico datosRegistroTopico) {
        Topico nuevoTopico = topicoService.crearTopico(datosRegistroTopico);
        return new ResponseEntity<>(nuevoTopico, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los tópicos
    @GetMapping
    @Operation(summary = "Obtener una lista con todos los tópicos")
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
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
