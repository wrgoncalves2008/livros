package br.biblioteca.livros.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "livro")
public class Livro implements Serializable {
	
	private static final long serialVersionUID = 5663066928725353351L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "NOME" , length = 120 , nullable=false)	
	@NotNull
	@Size(min = 2, max = 120)
	private String nome;
	
	@Min(10)
	private Integer quantidadePaginas;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "autor_id")
    private Autor autor;
	
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
	public Integer getQuantidadePaginas() {
		return quantidadePaginas;
	}
	public void setQuantidadePaginas(Integer quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}
	
	public Autor getAutor () {
        return autor;
    }

    public void setAutor (final Autor autor) {
        this.autor = autor;
    }
    
    @Override
    public String toString () {
        final StringBuilder builder = new StringBuilder()//
                .append("Livro [")//
                .append("id=")//
                .append(id)//
                .append(",nome=\"")//
                .append(nome).append("\"")//
                .append(",quantidadePaginas=")//
                .append(quantidadePaginas)//
                .append("]");
        return builder.toString();
    }

}
