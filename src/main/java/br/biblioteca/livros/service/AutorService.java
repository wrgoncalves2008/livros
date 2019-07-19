package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.entidades.Autor;
import br.biblioteca.livros.repositorio.AutorRepositorio;;

@Service
public class AutorService {

	@Autowired
	AutorRepositorio repositorio;

	public List<Autor> listaAutores() {
		return repositorio.findAll();
	}

	public void save(Autor autor) {
		repositorio.save(autor);
	}

	public Autor getAutor(long id) {
		return repositorio.findById(id).get();
	}

	public void delete(long id) {
		repositorio.deleteById(id);
	}

}
