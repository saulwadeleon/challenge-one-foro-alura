package com.alura.foroalura.repository;

import com.alura.foroalura.domain.topico.Topico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Lista todos los topicos que contengan cierto fragmento en el título
    List<Topico> findByTituloContaining(String fragmentoTitulo);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Topico findByTitulo(String topico);

}
