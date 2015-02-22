package br.com.yaw.spgae.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.yaw.spgae.model.Client;


public class ClientDataSource implements Serializable, DataSource<Client> {
	
	private static Logger log = Logger.getLogger(ClientDataSource.class);
	
	private Map<Long, Client> data = new LinkedHashMap<Long, Client>();
	
	@Override
	public void add(Client m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
		updateSession();
	}
	
	@Override
	public void update(Client m) {
		add(m);
	}
	
	@Override
	public void remove(Client m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Client> collection) {
		log.debug("Sincronizando datasource de clients...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Client m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Client> getAll() {
		return new ArrayList<Client>(data.values());
	}
	
	@Override
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
	/**
	 * Pede ao App Engine para atualizar a sessão.
	 */
	private void updateSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		session.setAttribute("forceGaeSessionSerialization", System.currentTimeMillis());
	}

}
