package br.biblioteca.livros.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityServiceInterface {

	String findLoggedInUsername();

	UserDetails findLoggedInUser();

	void login(String username, String password);

}
