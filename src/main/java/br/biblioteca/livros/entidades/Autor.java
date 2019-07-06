package br.biblioteca.livros.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor implements Serializable {
	
	private static final long serialVersionUID = -2931921441183751443L;
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@Column(name = "NOME" , nullable=false , length = 120)
	private String nome;
	
	@OneToMany(mappedBy = "autor")
	private List<Livro> livros = new ArrayList<>();
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Livro> getLivros () {
        return livros;
    }
	
	public void setLivros (final List<Livro> livros) {
        this.livros = livros;
    }
	
	@Override
    public String toString() {
        return "Autor [id=" + Id + ", nome=" + nome + "]";
    }

}
