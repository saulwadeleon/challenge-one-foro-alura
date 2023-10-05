package com.alura.foroalura.controller;

import com.alura.foroalura.domain.curso.Categoria;
import com.alura.foroalura.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * Esta clase es un controlador de Spring que maneja las solicitudes
 * relacionadas con las categorías de cursos en la aplicación. Proporciona
 * endpoints para crear, obtener, actualizar y eliminar categorías.
 * 
 * @author Saúl Wade León
 * @version 1.3
 * 
 */
@RestController
@RequestMapping("/categorias")
@SecurityRequirement(name = "bearer-key")
public class CategoriaController {

    @Autowired
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Endpoint para crear una nueva categoría
    @PostMapping
    @Transactional
    @Operation(summary = "Crear una nuevo categoria")
    /**
     * Este método maneja las solicitudes POST en la ruta "/categorias" y se encarga
     * de crear una nueva categoría de curso.
     * 
     * @param categoria
     * @return
     */
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las categorías
    @GetMapping
    @Operation(summary = "Obtener todas las categorías")
    /**
     * Este método maneja las solicitudes GET en la ruta "/categorias" y se encarga
     * de obtener todas las categorías de cursos.
     * 
     * @return
     */
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Endpoint para obtener una categoría por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por su ID")
    /**
     * Este método maneja las solicitudes GET en la ruta "/categorias/{id}" y se
     * encarga de obtener una categoría por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id) {
        Categoria categoria = categoriaService.obtenerCategoriaPorId(id);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para actualizar una categoría por su ID
    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar una categoría por su ID")
    /**
     * Este método maneja las solicitudes PUT en la ruta "/categorias/{id}" y se
     * encarga de actualizar una categoría por su ID.
     * 
     * @param id
     * @param categoriaActualizada
     * @return
     */
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id,
            @Valid @RequestBody Categoria categoriaActualizada) {
        Categoria categoria = categoriaService.actualizarCategoria(id, categoriaActualizada);
        if (categoria != null) {
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar una categoría por su ID
    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar una categoría por su ID")
    /**
     * Este método maneja las solicitudes DELETE en la ruta "/categorias/{id}" y se
     * encarga de eliminar una categoría por su ID.
     * 
     * @param id
     * @return
     */
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
