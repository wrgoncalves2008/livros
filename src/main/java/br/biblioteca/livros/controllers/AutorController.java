package br.biblioteca.livros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.entidades.Autor;
import br.biblioteca.livros.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {

//	@Autowired
//	private AutorRepositorio autorrepositorio;

	@Autowired
	private AutorService autorService;

	@GetMapping("/list")
	public ModelAndView list() {
		System.out.println("Listando os autores");

		Iterable<Autor> listaAutores = autorService.listaAutores();

		return new ModelAndView("/autor/list", "listaDeAutores", listaAutores);
	}

	@GetMapping("/novo")
	public ModelAndView novo() {
		System.out.println("Cadastrando novo autor");

		Iterable<Autor> listaAutores = autorService.listaAutores();

		return new ModelAndView("/autor/autor", "listaDeAutores", listaAutores);
	}

	@PostMapping("/gravar")
	public ModelAndView gravar(Autor autor) {
		System.out.println("Gravando novo autor " + autor.getNome());
		return new ModelAndView("redirect:/autor/list");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		System.out.println("Alterando o cadastro do autor id " + id);
		return new ModelAndView("redirect:/autor/list");
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		System.out.println("Excluindo o cadastro do autor id " + id);
		return new ModelAndView("redirect:/autor/list");
	}

	@GetMapping("/getautores")
	public ModelAndView getAutores() {
		System.out.println("Vai listar os autores:");
		for (Autor autor : autorService.listaAutores()) {
			System.out.println(autor.getNome());
		}

		return new ModelAndView("index");

	}

}
