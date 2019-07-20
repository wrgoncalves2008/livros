package br.biblioteca.livros.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.entidades.Autor;
import br.biblioteca.livros.entidades.Livro;
import br.biblioteca.livros.service.AutorService;
import br.biblioteca.livros.service.LivroService;

@Controller
@RequestMapping("/livros")
public class LivroController {

//	@Autowired
//	private LivroRepositorio livroRepositorio;

	@Autowired
	private LivroService livroService;

	@Autowired
	private AutorService autorService;

	@GetMapping("/index")
	public ModelAndView index() {
		System.out.println("Listagem de livros");

		Iterable<Livro> listaLivros = livroService.listaLivros();

		return new ModelAndView("/livros/index", "listaDeLivros", listaLivros);
	}

	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Livro livro) {
		ModelAndView modelAndView = new ModelAndView("livros/livro");
		Iterable<Autor> autores = autorService.listaAutores();
		modelAndView.addObject("autores", autores);
		return modelAndView;
	}

	@PostMapping(value = "/gravar")
	public ModelAndView gravar(@ModelAttribute("livro") @Valid Livro livro, BindingResult bindingresult) {

		if (bindingresult.hasErrors()) {
			System.out.println("Deu erro para gravar o livro");
			return new ModelAndView("/livros/livro");
		}

		livroService.save(livro);

		return new ModelAndView("redirect:/livros/index");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		System.out.println("Alterando o livro " + id);

		ModelAndView modelAndView = new ModelAndView("livros/livro");
		Iterable<Autor> autores = autorService.listaAutores();
		modelAndView.addObject("autores", autores);

		Livro livro = livroService.getLivro(id);

		modelAndView.addObject("livro", livro);
		return modelAndView;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		System.out.println("Excluindo o livro " + id);
		livroService.delete(id);
		return new ModelAndView("redirect:/livros/index");
	}

	@GetMapping("/getlivros")
	public ModelAndView getAutores() {

		Iterable<Livro> listaLivros = livroService.listaLivros();

		System.out.println("Vai listar os livros:");
		for (Livro livro : listaLivros) {
			System.out.println(livro.getNome());
		}

		return new ModelAndView("index");

	}

}
