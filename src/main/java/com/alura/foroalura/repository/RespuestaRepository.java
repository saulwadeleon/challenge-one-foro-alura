package com.alura.foroalura.repository;

import com.alura.foroalura.domain.respuesta.Respuesta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
    // Este repositorio hereda métodos CRUD básicos de JpaRepository

    // Este método busca respuestas relacionadas con un tópico específico por si Id
    List<Respuesta> findByTopicoId(Long topicoId);
}
