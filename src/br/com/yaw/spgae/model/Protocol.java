package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Protocol implements Serializable {

	@Id
	private Long id;
	
	@NotNull @Size(min=2, max=200)
	private String nome;	

	public Protocol() {
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Protocol outro = (Protocol) obj;
		boolean equal = (id != null && id.equals(outro.id)) 
				|| (nome != null && nome.equals(outro.nome)) ;
		return equal;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (nome == null ? 0 : nome.hashCode());
		
		return hash;
	}

}
