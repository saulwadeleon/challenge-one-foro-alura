package com.alura.foroalura.controller;

import com.alura.foroalura.domain.topico.Topico;
import com.alura.foroalura.service.TopicoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Topico> crearTopico(@RequestBody Topico topico) {
        Topico nuevoTopico = topicoService.crearTopico(topico);
        return new ResponseEntity<>(nuevoTopico, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los tópicos
    @GetMapping
    @Operation(summary = "Obtener una lista con todos los tópicos")
    public ResponseEntity<List<Topico>> obtenerTodosLosTopicos() {
        List<Topico> topicos = topicoService.obtenerTodosLosTopicos();
        return new ResponseEntity<>(topicos, HttpStatus.OK);
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
    @Operation(summary = "Actualizar un tópico por su ID")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody Topico topicoActualizado) {
        Topico topico = topicoService.actualizarTopico(id, topicoActualizado);
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
