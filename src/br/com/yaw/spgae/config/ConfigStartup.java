package br.com.yaw.spgae.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import br.com.yaw.spgae.model.Mercadoria;
import br.com.yaw.spgae.model.Produto;
import br.com.yaw.spgae.model.Client;
import br.com.yaw.spgae.model.Protocol;
import br.com.yaw.spgae.model.Service;

import com.googlecode.objectify.ObjectifyService;

/**
 * Componente necessário para registrar no Objectify quais são as entidades que ele deve gerenciar.
 * 
 * <p>Código executado durante a inicialização do aplicativo web.</p> 
 * 
 * @author YaW Tecnologia
 */
public class ConfigStartup implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ObjectifyService.register(Mercadoria.class);
		ObjectifyService.register(Produto.class);
		ObjectifyService.register(Client.class);
		ObjectifyService.register(Protocol.class);
		ObjectifyService.register(Service.class);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {}
	
}
