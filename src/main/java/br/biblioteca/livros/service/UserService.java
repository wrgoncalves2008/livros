package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.entidades.User;
import br.biblioteca.livros.repositorio.UserRepositorio;

@Service
public class UserService {

	@Autowired
	UserRepositorio userRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordCrypt;

	public List<User> listaUsuarios() {
		return userRepositorio.findAll();
	}

	public void save(User user) {
		user.setSenha(passwordCrypt.encode(user.getSenha()));
		userRepositorio.save(user);
	}

	public User getUser(long id) {
		return userRepositorio.findById(id).get();
	}

	public void delete(long id) {
		userRepositorio.deleteById(id);
	}

	public User findByUsername(String username) {

		User result = null;

		for (User user : listaUsuarios()) {
			if (user.getNome().equals(username)) {
				result = user;
			}
		}

		return result;
	}

}
