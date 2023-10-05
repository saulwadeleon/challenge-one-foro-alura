package com.alura.foroalura.repository;

import com.alura.foroalura.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Este repositorio proporciona métodos para acceder y buscar cursos en la base
 * de datos en función de su nombre, categoría y la combinación de ambos. Estos
 * métodos facilitan la recuperación de datos relacionados con los cursos en la
 * base de datos.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    /**
     * findByNombre permite buscar cursos por su nombre. Spring Data JPA generará
     * automáticamente una consulta SQL basada en el nombre del método y el
     * parámetro proporcionado nombre, que representa el nombre del curso. Esto
     * devolverá un solo curso que coincida exactamente con el nombre proporcionado.
     * 
     * @param nombre
     * @return
     */
    Curso findByNombre(String nombre);

    /**
     * findByCategoriaId permite buscar cursos por el ID de la categoría a la que
     * pertenecen. Spring Data JPA generará automáticamente la consulta SQL
     * necesaria para buscar cursos que estén asociados a una categoría específica,
     * identificada por categoriaId. Esto devolverá una lista de cursos que
     * pertenecen a la categoría con el ID especificado.
     * 
     * @param categoriaId
     * @return
     */
    List<Curso> findByCategoriaId(Long categoriaId);

    /**
     * findByNombreAndCategoriaId permite buscar cursos por nombre y categoría.
     * Spring Data JPA generará automáticamente una consulta SQL que busca cursos
     * que coincidan tanto en nombre como en categoría. Puedes usar este método para
     * buscar cursos específicos que tengan un nombre dado y pertenezcan a una
     * categoría específica.
     * 
     * @param nombre
     * @param categoriaId
     * @return
     */
    List<Curso> findByNombreAndCategoriaId(String nombre, Long categoriaId);

    /**
     * findByNombreContaining permite buscar cursos cuyo nombre contenga una cadena
     * específica representada por fragmentoNombre. Spring Data JPA generará la
     * consulta SQL correspondiente para realizar esta búsqueda y devolverá una
     * lista de cursos que coincidan con el fragmento proporcionado en el nombre del
     * curso.
     * 
     * @param fragmentoNombre
     * @return
     */
    List<Curso> findByNombreContaining(String fragmentoNombre);

}
