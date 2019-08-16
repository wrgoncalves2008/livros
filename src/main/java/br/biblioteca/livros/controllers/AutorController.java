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
import br.biblioteca.livros.service.AutorService;

@Controller
@RequestMapping("/autor")
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping("/index")
	public ModelAndView index() {
		System.out.println("Listando os autores");

		Iterable<Autor> listaAutores = autorService.listaAutores();

		return new ModelAndView("autor/index", "listaDeAutores", listaAutores);
	}

	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Autor autor) {
		System.out.println("Cadastrando novo autor");
		ModelAndView modelAndView = new ModelAndView("autor/autor");

		return modelAndView;
	}

	@PostMapping("/gravar")
	public ModelAndView gravar(@ModelAttribute("autor") @Valid Autor autor, BindingResult bindingresult) {
		System.out.println("Gravando novo autor " + autor.getNome());

		if (bindingresult.hasErrors()) {
			System.out.println("Deu erro para gravar os dados");
			return new ModelAndView("autor/autor");
		}

		autorService.save(autor);

		return new ModelAndView("redirect:autor/index");
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		System.out.println("Alterando o cadastro do autor id " + id);

		ModelAndView modelandview = new ModelAndView("autor/autor");

		Autor autor = autorService.getAutor(id);

		modelandview.addObject("autor", autor);

		return modelandview;
	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		System.out.println("Excluindo o cadastro do autor id " + id);

		autorService.delete(id);

		return new ModelAndView("redirect:autor/index");
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
