package br.biblioteca.livros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.entidades.Roles;

public interface RoleRepositorio extends JpaRepository<Roles, Long> {

}
