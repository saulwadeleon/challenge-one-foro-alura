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

/**
 * Esta clase es un controlador de Spring que maneja las solicitudes
 * relacionadas con los cursos en la aplicación. Proporciona endpoints para
 * crear, obtener, actualizar y eliminar cursos.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
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
    /**
     * Este método maneja las solicitudes POST en la ruta "/cursos" y se encarga de
     * crear un nuevo curso.
     * 
     * @param curso
     * @return
     */
    public ResponseEntity<Curso> crearCurso(@Valid @RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.crearCurso(curso);
        return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los cursos
    @GetMapping
    @Operation(summary = "Obtener el listado de todos los cursos")
    /**
     * Este método maneja las solicitudes GET en la ruta "/cursos" y se encarga de
     * obtener todos los cursos disponibles.
     * 
     * @return
     */
    public ResponseEntity<List<Curso>> obtenerTodosLosCursos() {
        List<Curso> cursos = cursoService.obtenerTodosLosCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    // Endpoint para obtener un curso por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un curso por su ID")
    /**
     * Este método maneja las solicitudes GET en la ruta "/cursos/{id}" y se encarga
     * de obtener un curso por su ID.
     * 
     * @param id
     * @return
     */
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
    /**
     * Este método maneja las solicitudes PUT en la ruta "/cursos/{id}" y se encarga
     * de actualizar un curso por su ID.
     * 
     * @param id
     * @param cursoActualizado
     * @return
     */
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
    /**
     * Este método maneja las solicitudes DELETE en la ruta "/cursos/{id}" y se
     * encarga de eliminar un curso por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<String> eliminarCurso(@PathVariable Long id) {
        if (cursoService.existsById(id)) {
            cursoService.eliminarCurso(id);
            return new ResponseEntity<>("El curso fue eliminado exitosamente", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("El curso que intenta eliminar no existe", HttpStatus.NOT_FOUND);
        }
    }
}