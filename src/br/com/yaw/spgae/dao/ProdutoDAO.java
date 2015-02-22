package br.com.yaw.spgae.dao;
import java.util.List;

import br.com.yaw.spgae.model.Produto;


public interface ProdutoDAO {

	Produto save(Produto produto);
	
	List<Produto> getAll();
	
	Boolean remove(Produto produto);
	
	Produto findById(Long id);

}
