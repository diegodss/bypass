package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;


import br.com.yaw.spgae.model.Client;

@Repository
public class ClientDAOObjectify implements Serializable, ClientDAO {

	@Override
	public Client save(Client client) {
		ofy().save().entity(client).now();
		return client;
	}
	
	@Override
	public List<Client> getAll() {
		return ofy().load().type(Client.class).list();
	}
	
	@Override
	public Boolean remove(Client client) {
		ofy().delete().entity(client).now();
		return true;
	}
	
	@Override
	public Client findById(Long id) {
		Key<Client> k = Key.create(Client.class, id);
		return ofy().load().key(k).get();
	}
	
}
