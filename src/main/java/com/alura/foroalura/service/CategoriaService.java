package com.alura.foroalura.service;

import com.alura.foroalura.domain.curso.Categoria;
import com.alura.foroalura.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoriaService proporciona métodos para realizar operaciones CRUD (Crear,
 * Leer, Actualizar y Eliminar) en la entidad Categoria, lo que permite
 * gestionar las categorías a la aplicación. Se utiliza este servicio para
 * crear, obtener, actualizar y eliminar categorías de manera efectiva.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Service
public class CategoriaService {

    @Autowired
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * crearCategoria permite crear una nueva categoría. Recibe un objeto Categoria
     * como parámetro y utiliza el repositorio
     * 
     * @param categoria
     * @return
     */
    public Categoria crearCategoria(Categoria categoria) {
        // Agregar lógica de validación o reglas de negocio si es necesario
        return categoriaRepository.save(categoria);
    }

    /**
     * obtenerTodasLasCategorías devuelve una lista de todas las categorías
     * almacenadas en la base de datos. Utiliza el método findAll() proporcionado
     * por el repositorio para obtener todas las categorías.
     * 
     * @return
     */
    public List<Categoria> obtenerTodasLasCategorias() {
        return categoriaRepository.findAll();
    }

    /**
     * obtenerCategoriaPorId permite obtener una categoría específica por su
     * identificador (id). Utiliza el método findById(id) del repositorio y retorna
     * la categoría si se encuentra; de lo contrario, retorna null.
     * 
     * @param id
     * @return
     */
    public Categoria obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    /**
     * actualizarCategoria actualiza una categoría existente por su identificador
     * (id). Primero, obtiene la categoría existente utilizando
     * obtenerCategoriaPorId(id). Luego, actualiza los campos relevantes de la
     * categoría existente con los datos de la categoría actualizada. Finalmente,
     * utiliza el repositorio para guardar la categoría actualizada.
     * 
     * @param id
     * @param categoriaActualizada
     * @return
     */
    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizada) {
        Categoria categoriaExistente = obtenerCategoriaPorId(id);
        if (categoriaExistente != null) {
            // Actualizar los campos relevantes de la categoría existente con los datos de
            // la categoría actualizada
            categoriaExistente.setDescripcion(categoriaActualizada.getDescripcion());
            return categoriaRepository.save(categoriaExistente);
        }
        return null; // Retornar null si la categoría no se encuentra
    }

    /**
     * eliminarCategoria permite eliminar una categoría por su identificador (id).
     * Utiliza el método deleteById(id) del repositorio para eliminar la categoría
     * de la base de datos.
     * 
     * @param id
     */
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
