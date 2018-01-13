package br.com.exemplo.titulo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.exemplo.titulo.model.Titulo;

public interface Titulos extends JpaRepository<Titulo, Long> {

}
