package br.biblioteca.livros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import br.biblioteca.livros.entidades.Livro;

public interface LivroRepositorio  extends JpaRepository<Livro, Long> {


}
