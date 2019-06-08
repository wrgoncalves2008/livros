package br.biblioteca.livros.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.entidades.Livro;

@Controller
@RequestMapping("/livros")
public class LivroController {
	
	@GetMapping("/list")
	public ModelAndView list() {
		System.out.println("Listagem de livros");
		return new ModelAndView("/livros/list");
	}

	@GetMapping("/novo")
	public ModelAndView novo() {
		System.out.println("Novo Livro");
		return new ModelAndView("/livros/livro");
	}
	
	@PostMapping(value  = "/gravar")
	public ModelAndView gravar(Livro livro) {
		System.out.println("Gravando livro");
		return new ModelAndView("redirect:/livros/list");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		System.out.println("Alterando o livro " + id);
		return new ModelAndView("redirect:/livros/list");
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		System.out.println("Excluindo o livro " + id);
		return new ModelAndView("redirect:/livros/list");
	}
	
}
