package br.biblioteca.livros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements SecurityServiceInterface {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private UserDetailService userDetailService;

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();

		System.out.println("passou aqui");
		System.out.println(userDetails.toString());

		if (userDetails == null) {
			System.out.println("não achou");
		}
		
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	@Override
	public UserDetails findLoggedInUser() {
				
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				
		if (userDetails instanceof UserDetails) {			
			return (UserDetails) userDetails;			
		}

		return null;
	}

	@Override
	public void login(String username, String password) {
		try {
			UserDetails userDetails = userDetailService.loadUserByUsername(username);
				
			UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails,
					password, userDetails.getAuthorities());

			Authentication auth = authManager.authenticate(userPassAuthToken);

			if (auth.isAuthenticated()) {
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

		} catch (Exception e) {
			throw new RuntimeException();

		}

	}

}
