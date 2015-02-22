package br.com.yaw.spgae.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.googlecode.objectify.Key;


import br.com.yaw.spgae.model.Service;

@Repository
public class ServiceDAOObjectify implements Serializable, ServiceDAO{

	@Override
	public Service save(Service service) {
		ofy().save().entity(service).now();
		return service;
	}
	
	@Override
	public List<Service> getAll() {
		return ofy().load().type(Service.class).list();
	}
	
	@Override
	public Boolean remove(Service service) {
		ofy().delete().entity(service).now();
		return true;
	}
	
	@Override
	public Service findById(Long id) {
		Key<Service> k = Key.create(Service.class, id);
		return ofy().load().key(k).get();
	}
	
}
