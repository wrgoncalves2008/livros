package br.biblioteca.livros.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.biblioteca.livros.entidades.User;
import br.biblioteca.livros.service.UserService;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {

		System.out.println("UserValidator - Validate");

		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty");
		if (user.getNome().length() < 4 || user.getNome().length() > 32) {
			errors.rejectValue("nome", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getNome()) != null) {
			errors.rejectValue("nome", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "NotEmpty");
		if (user.getSenha().length() < 4 || user.getSenha().length() > 32) {
			errors.rejectValue("senha", "Size.userForm.password");
		}

		if (!user.getSenha().equals(user.getSenha())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}

	}
}