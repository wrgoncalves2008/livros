package br.biblioteca.livros.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.entidades.User;

public interface UserRepositorio extends JpaRepository<User, Long> {

}