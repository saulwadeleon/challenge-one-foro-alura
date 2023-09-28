package com.alura.foroalura.repository;

import com.alura.foroalura.domain.curso.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Método para buscar categorias por nombre
    List<Categoria> findByNombre(String nombre);

    // Método para buscar categorias por nombre que contenga una cadena específica
    List<Categoria> findByNombreContaining(String fragmentoNombre);
}
