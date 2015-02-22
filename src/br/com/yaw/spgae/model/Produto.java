package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Produto implements Serializable {

	@Id
	private Long id;
	
	@NotNull @Size(min=5, max=200)
	private String nome;
	
	private String descricao;
	
	private String imagem;
	private String usernome;
	private String userimagem;
	
	
	private Long userid;
	

	
	public Produto() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	public Long getUserid() {
		return userid;
	}
		
	public void setImagem( String imagem) {
		this.imagem = imagem;
		
	}
	public String getImagem() {
		return imagem;
	}
	
	public void setUsernome(String usernome){
		
		this.usernome = usernome;
	}
	
	public String getUsernome(){
		
		return usernome;
	}
	
	public void setUserimagem( String userimagem) {
		
		this.userimagem = userimagem;
	}
	
	public String getUserimagem(){
		return userimagem;
		
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Produto outro = (Produto) obj;
		boolean equal = (id != null && id.equals(outro.id)) 
				|| (nome != null && nome.equals(outro.nome))
				|| (descricao != null && descricao.equals(outro.descricao));
		return equal;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (nome == null ? 0 : nome.hashCode());
		hash = (31 * hash) + (descricao == null ? 0 : descricao.hashCode());
		
		return hash;
	}

}
