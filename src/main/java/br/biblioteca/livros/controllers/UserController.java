package br.biblioteca.livros.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.entidades.Roles;
import br.biblioteca.livros.entidades.User;
import br.biblioteca.livros.service.RoleService;
import br.biblioteca.livros.service.SecurityService;
import br.biblioteca.livros.service.UserService;
import br.biblioteca.livros.validators.LoginValidator;
import br.biblioteca.livros.validators.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private LoginValidator loginValidator;

	@Autowired
	private UserValidator userValidator;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("user/login", "userForm", new User());
	}

	@PostMapping("/authentication")
	public ModelAndView authentication(@ModelAttribute("userForm") User userform, BindingResult br, Model model) {
	
		userform.setNome( userform.getNome().toLowerCase() );
		userform.setSenha( userform.getSenha().toLowerCase() );
		
		loginValidator.validate(userform, br);
		
		if (br.hasErrors()) {
			return new ModelAndView("user/login", "userForm", new User());
		}

		try {
			securityService.login(userform.getNome(), userform.getSenha());
			
			return new ModelAndView("redirect:/");
		} catch (Exception E) {
			return new ModelAndView("redirect:/user/login", "userform", new User());
		}
	}

	@GetMapping("/index")
	public ModelAndView index() {
		System.out.println("Acessando usuários");

		Iterable<User> listaUsers = userService.listaUsuarios();
		return new ModelAndView("user/index", "listaUsers", listaUsers);
	}

	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView viewUser = new ModelAndView("user/register");
		viewUser.addObject("userForm", new User());
		Iterable<Roles> roles = roleService.listaRoles();
		viewUser.addObject("roles", roles);

		return viewUser;
	}

	@PostMapping("/registration")
	public ModelAndView registration(@ModelAttribute("userForm") User userForm, BindingResult br, Model model) {
		
		String username = null;
		
		if (securityService.findLoggedInUser() != null)
		{
			username = securityService.findLoggedInUser().getUsername();		
		}
		
		userValidator.validate(userForm, br);

		if (br.hasErrors()) {
			
			if ((username != null) && (userService.findByUsername(username).getRole().getNome().equals("ROLE_ADMIN")))
			{	
				return new ModelAndView("redirect:/user/index");
			}			
			else
			{
				return new ModelAndView("redirect:/user/login");	
			}
		}
		
		String password = userForm.getSenha();
		
		userService.save(userForm);

		try {
			if (username == null)
			{				
				securityService.login(userForm.getNome(), password);				
				return new ModelAndView("redirect:/");
			}		
			
			return new ModelAndView("redirect:/user/index");
			
		} catch (Exception e) {
			
			
			
			if (userService.findByUsername(username).getRole().getNome().equals("ROLE_ADMIN"))
			{	
				return new ModelAndView("redirect:/user/index");
			}
			else
			{
				return new ModelAndView("redirect:/user/login");	
			}

		}
	}

	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();

		if (session != null) {
			session.invalidate();
		}

		return new ModelAndView("redirect:/user/login");

	}

	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		System.out.println("Excluindo o usuário " + id);

		userService.delete(id);
		return new ModelAndView("redirect:/user/index");
	}

	@PostMapping(value = "/gravar")
	public ModelAndView gravar(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingresult) {

		String username = securityService.findLoggedInUser().getUsername();
		
		if (bindingresult.hasErrors()) {
			System.out.println("Deu erro pra gravar o usuário");
			
			if (userService.findByUsername(username).getRole().getNome().equals("ROLE_ADMIN"))
			{	
				System.out.println("Admin");
				return new ModelAndView("redirect:/user/index");
			}
			else
			{
				System.out.println("basic");
				return new ModelAndView("redirect:/");
			}
		}

		userService.save(userForm);
		
		if (userService.findByUsername(username).getRole().getNome().equals("ROLE_ADMIN"))
		{	
			System.out.println("Admin");
			return new ModelAndView("redirect:/user/index");
		}
		else
		{
			System.out.println("basic");
			return new ModelAndView("redirect:/");
		}
	}

	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		System.out.println("Alterando o cadastro do usuário " + id);

		ModelAndView viewUser = new ModelAndView("user/alterar");

		Iterable<Roles> roles = roleService.listaRoles();
		viewUser.addObject("roles", roles);

		User user = userService.getUser(id);
		user.setSenha("");

		viewUser.addObject("userForm", user);

		return viewUser;
	}
	
	@GetMapping("/alterarusuario")
	public ModelAndView alterarUsuario()
	{
		String username = securityService.findLoggedInUser().getUsername();		
			
		return alterar( userService.findUserIDByUsername( username ) );				
	}
	

}
