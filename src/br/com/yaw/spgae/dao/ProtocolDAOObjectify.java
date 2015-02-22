package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;


import br.com.yaw.spgae.model.Protocol;

@Repository
public class ProtocolDAOObjectify implements Serializable, ProtocolDAO {

	@Override
	public Protocol save(Protocol protocol) {
		ofy().save().entity(protocol).now();
		return protocol;
	}
	
	@Override
	public List<Protocol> getAll() {
		return ofy().load().type(Protocol.class).list();
	}
	
	@Override
	public Boolean remove(Protocol protocol) {
		ofy().delete().entity(protocol).now();
		return true;
	}
	
	@Override
	public Protocol findById(Long id) {
		Key<Protocol> k = Key.create(Protocol.class, id);
		return ofy().load().key(k).get();
	}
	
}
