package com.alura.foroalura.repository;

import com.alura.foroalura.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Método para buscar cursos por nombre
    Curso findByNombre(String nombre);

    // Método para buscar cursos por categoría
    List<Curso> findByCategoriaId(Long categoriaId);

    // Método para buscar cursos por nombre y categoría
    List<Curso> findByNombreAndCategoriaId(String nombre, Long categoriaId);

    // Método para buscar cursos por nombre que contenga una cadena específica
    List<Curso> findByNombreContaining(String fragmentoNombre);

}
