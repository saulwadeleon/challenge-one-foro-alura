package com.alura.foroalura.repository;

import com.alura.foroalura.domain.curso.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Este repositorio proporciona métodos para acceder a la entidad Categoria y
 * realizar operaciones de búsqueda basadas en la descripción de la categoría,
 * ya sea coincidiendo exactamente con una descripción dada o conteniendo una
 * cadena específica en la descripción. Estos métodos facilitan el acceso y la
 * búsqueda de datos relacionados con las categorías de los cursos en la base de
 * datos.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método para buscar categorias por descripcion
    List<Categoria> findByDescripcion(String descripcion);

    // Método para buscar categorias por descripcion que contenga una cadena
    // específica
    List<Categoria> findByDescripcionContaining(String fragmentoDescripcion);
}
