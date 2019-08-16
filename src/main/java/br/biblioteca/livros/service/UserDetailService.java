package br.biblioteca.livros.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.entidades.User;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);

		Set<GrantedAuthority> grantedAuth = new HashSet<>();

		grantedAuth.add(new SimpleGrantedAuthority(user.getRole().getNome()));

		return new org.springframework.security.core.userdetails.User(user.getNome(), user.getSenha(), grantedAuth);
	}

}
