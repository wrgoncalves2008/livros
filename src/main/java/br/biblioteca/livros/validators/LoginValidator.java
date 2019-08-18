package br.biblioteca.livros.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.biblioteca.livros.entidades.User;
import br.biblioteca.livros.service.UserService;

@Component
public class LoginValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "NotEmpty");

		if (userService.findByUsername(user.getNome()) == null) {
			errors.rejectValue("senha", "NotExist.userForm.senha");
		}

	}
}