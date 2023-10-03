package com.alura.foroalura.service;

import com.alura.foroalura.domain.curso.Curso;
import com.alura.foroalura.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Método para crear un nuevo curso
    public Curso crearCurso(Curso curso) {
        // Agregar lógica de validación o reglas de negocio si es necesario
        return cursoRepository.save(curso);
    }

    // Método para obtener todos los cursos
    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    // Método para obtener un curso por su ID
    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    // Método para actualizar un curso por su ID
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

    // Método para eliminar un curso por su ID
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso obtenerCursoPorNombre(String curso) {
        return cursoRepository.findByNombre(curso);
    }
}
