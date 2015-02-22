package br.com.yaw.spgae.dao;
import java.util.List;

import br.com.yaw.spgae.model.Service;


public interface ServiceDAO {

	Service save(Service service);
	
	List<Service> getAll();
	
	Boolean remove(Service service);
	
	Service findById(Long id);

}
