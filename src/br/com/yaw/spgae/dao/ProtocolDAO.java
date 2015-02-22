package br.com.yaw.spgae.dao;
import java.util.List;

import br.com.yaw.spgae.model.Protocol;


public interface ProtocolDAO {

	Protocol save(Protocol protocol);
	
	List<Protocol> getAll();
	
	Boolean remove(Protocol protocol);
	
	Protocol findById(Long id);

}
