package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;


import br.com.yaw.spgae.model.Produto;

@Repository
public class ProdutoDAOObjectify implements Serializable, ProdutoDAO {

	@Override
	public Produto save(Produto produto) {
		ofy().save().entity(produto).now();
		return produto;
	}
	
	@Override
	public List<Produto> getAll() {
		return ofy().load().type(Produto.class).list();
	}
	
	@Override
	public Boolean remove(Produto produto) {
		ofy().delete().entity(produto).now();
		return true;
	}
	
	@Override
	public Produto findById(Long id) {
		Key<Produto> k = Key.create(Produto.class, id);
		return ofy().load().key(k).get();
	}
	
}
