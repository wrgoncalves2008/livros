package br.biblioteca.livros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.biblioteca.livros.entidades.Autor;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {

}
