package br.com.yaw.spgae.dao;
import java.util.List;

import br.com.yaw.spgae.model.Client;


public interface ClientDAO {

	Client save(Client client);
	
	List<Client> getAll();
	
	Boolean remove(Client client);
	
	Client findById(Long id);

}
