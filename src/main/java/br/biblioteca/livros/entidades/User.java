package br.biblioteca.livros.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -7448684629765431244L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 4, max = 50)
	private String nome;

	@NotNull
	@Size(min = 4)
	private String senha;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "role_id")
	private Roles role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(final Roles role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [username=" + nome + ", password=" + senha + ", roles=" + role.getNome() + "]";
	}
}
