package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.entidades.Livro;
import br.biblioteca.livros.repositorio.LivroRepositorio;

@Service
public class LivroService {

	@Autowired
	LivroRepositorio repositorio;

	public List<Livro> listaLivros() {
		return repositorio.findAll();
	}

	public void delete(long id) {
		repositorio.deleteById(id);
	}

	public void save(Livro livro) {
		repositorio.save(livro);
	}

	public void merge(Livro livro) {
//		repositorio. (livro);
	}

	public Livro getLivro(long id) {
		return repositorio.findById(id).get();
	}

}
