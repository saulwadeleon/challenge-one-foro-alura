package com.alura.foroalura.service;

import com.alura.foroalura.domain.curso.Categoria;
import com.alura.foroalura.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Método para crear una nueva categoría
    public Categoria crearCategoria(Categoria categoria) {
        // Agregar lógica de validación o reglas de negocio si es necesario
        return categoriaRepository.save(categoria);
    }

    // Método para obtener todas las categorías
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    // Método para obtener una categoría por su ID
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    // Método para actualizar una categoría por su ID
    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Categoria categoriaExistente = obtenerCategoriaPorId(id);
        if (categoriaExistente != null) {
            // Actualizar los campos relevantes de la categoría existente con los datos de
            // la categoría actualizada
            categoriaExistente.setNombre(categoriaActualizada.getNombre());
            return categoriaRepository.save(categoriaExistente);
        }
        return null; // Retornar null si la categoría no se encuentra
    }

    // Método para eliminar una categoría por su ID
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
