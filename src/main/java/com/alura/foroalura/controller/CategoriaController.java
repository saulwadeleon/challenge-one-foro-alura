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
    public ResponseEntity<Categoria> crearCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // Endpoint para obtener todas las categorías
    @GetMapping
    @Operation(summary = "Obtener todas las categorías")
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        List<Categoria> categorias = categoriaService.obtenerTodasLasCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // Endpoint para obtener una categoría por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoría por su ID")
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
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
