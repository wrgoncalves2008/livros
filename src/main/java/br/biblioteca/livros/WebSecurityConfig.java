package br.biblioteca.livros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.biblioteca.livros.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailService userDetailsService;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

		System.out.println("Entrou na rotina Configure");

		http.headers().frameOptions().disable();

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/user/registration").permitAll()
				.antMatchers(HttpMethod.POST, "/user/registration").permitAll()
				.antMatchers(HttpMethod.GET, "/user/register").permitAll().antMatchers(HttpMethod.GET, "/user/index")
				.hasRole("BASIC").antMatchers(HttpMethod.GET, "/user/index").hasRole("ADMIN").and().formLogin()
				.loginPage("/user/login").permitAll().and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configureGlobal");

		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
