package com.alura.foroalura.repository;

import com.alura.foroalura.domain.respuesta.Respuesta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Este repositorio proporciona métodos para acceder y buscar respuestas en la
 * base de datos en función del tópico al que están relacionadas. Estos métodos
 * facilitan la recuperación de respuestas asociadas a un tópico específico en
 * la base de datos.
 * 
 * @author Saúl Wade León
 * @version 1.3
 */
@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    // Este repositorio hereda métodos CRUD básicos de JpaRepository

    // Este método busca respuestas relacionadas con un tópico específico por si Id
    List<Respuesta> findByTopicoId(Long topicoId);

}
