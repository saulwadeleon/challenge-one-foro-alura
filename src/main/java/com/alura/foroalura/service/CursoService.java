package com.alura.foroalura.service;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CursoService proporciona métodos para realizar operaciones CRUD (Crear, Leer,
 * Actualizar y Eliminar) en la entidad Curso, lo que permite gestionar los
 * cursos en la aplicación. Se utiliza este servicio para crear, obtener,
 * actualizar y eliminar cursos de manera efectiva, así como verificar la
 * existencia de cursos por su identificador.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Service
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    /**
     * crearCurso permite crear un nuevo curso. Recibe un objeto Curso como
     * parámetro y utiliza el repositorio (cursoRepository) para guardar el curso en
     * la base de datos. Puedes agregar lógica de validación o reglas de negocio
     * aquí antes de guardar el curso.
     * 
     * @param curso
     * @return
     */
    public Curso crearCurso(Curso curso) {
        // Agregar lógica de validación o reglas de negocio si es necesario
        return cursoRepository.save(curso);
    }

    /**
     * obtenerTodosLosCurso devuelve una lista de todos los cursos almacenados en la
     * base de datos. Utiliza el método findAll() proporcionado por el repositorio
     * para obtener todos los cursos.
     * 
     * @return
     */
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    /**
     * obtenerCursoPorId permite obtener un curso específico por su identificador
     * (id). Utiliza el método findById(id) del repositorio y retorna el curso si se
     * encuentra; de lo contrario, retorna null.
     * 
     * @param id
     * @return
     */
    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    /**
     * actualizarCurso actualiza un curso existente por su identificador (id).
     * Primero, obtiene el curso existente utilizando obtenerCursoPorId(id). Luego,
     * actualiza los campos relevantes del curso existente con los datos del curso
     * actualizado. Finalmente, utiliza el repositorio para guardar el curso
     * actualizado.
     * 
     * @param id
     * @param cursoActualizado
     * @return
     */
    public Curso actualizarCurso(Long id, Curso cursoActualizado) {
        Curso cursoExistente = obtenerCursoPorId(id);
        if (cursoExistente != null) {
            // Actualizar los campos relevantes del curso existente con los datos del curso
            // actualizado
            cursoExistente.setNombre(cursoActualizado.getNombre());

            return cursoRepository.save(cursoExistente);
        }
        return null; // Retornar null si el curso no se encuentra
    }

    /**
     * eliminarCurso permite eliminar un curso por su identificador (id). Utiliza el
     * método deleteById(id) del repositorio para eliminar el curso de la base de
     * datos.
     * 
     * @param id
     */
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    /**
     * obtenerCursoPorNombre permite obtener un curso por su nombre. Utiliza el
     * método findByNombre(nombre) del repositorio para buscar el curso por su
     * nombre y lo retorna si se encuentra.
     * 
     * @param curso
     * @return
     */
    public Curso obtenerCursoPorNombre(String curso) {
        return cursoRepository.findByNombre(curso);
    }

    /**
     * existsById verifica si existe un curso en la base de datos con el
     * identificador proporcionado (id). Retorna true si existe y false en caso
     * contrario.
     * 
     * @param id
     * @return
     */
    public boolean existsById(Long id) {
        return cursoRepository.existsById(id);
    }
}
