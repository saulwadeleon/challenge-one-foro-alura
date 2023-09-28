package com.alura.foroalura.controller;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.service.CursoService;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Endpoint para crear un nuevo curso
    @PostMapping
    @Transactional
    @Operation(summary = "Crear un nuevo curso")
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.crearCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los cursos
    @GetMapping
    @Operation(summary = "Obtener el listado de todos los cursos")
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        List<Curso> cursos = cursoService.obtenerTodosLosCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    // Endpoint para obtener un curso por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por su ID")
    public ResponseEntity<Curso> obtenerCursoPorId(@PathVariable Long id) {
        Curso curso = cursoService.obtenerCursoPorId(id);
        if (curso != null) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar un curso por su ID
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar un curso por su ID")
    public ResponseEntity<Curso> actualizarCurso(@PathVariable Long id, @Valid @RequestBody Curso cursoActualizado) {
        Curso curso = cursoService.actualizarCurso(id, cursoActualizado);
        if (curso != null) {
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un curso por su ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un curso por su ID")
    public ResponseEntity<Void> eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}