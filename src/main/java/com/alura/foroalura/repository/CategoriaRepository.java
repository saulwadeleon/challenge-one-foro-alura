package com.alura.foroalura.repository;

import com.alura.foroalura.domain.curso.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método para buscar categorias por descripcion
    List<Categoria> findByDescripcion(String descripcion);

    // Método para buscar categorias por descripcion que contenga una cadena
    // específica
    List<Categoria> findByDescripcionContaining(String fragmentoDescripcion);
}
