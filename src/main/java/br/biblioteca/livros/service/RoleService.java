package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.entidades.Roles;
import br.biblioteca.livros.repositorio.RoleRepositorio;

@Service
public class RoleService {

	@Autowired
	RoleRepositorio roleRepositorio;

	public List<Roles> listaRoles() {
		return roleRepositorio.findAll();
	}

}
