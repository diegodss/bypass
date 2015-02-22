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

import br.com.yaw.spgae.model.Service;


public class ServiceDataSource implements Serializable, DataSource<Service> {
	
	private static Logger log = Logger.getLogger(ServiceDataSource.class);
	
	private Map<Long, Service> data = new LinkedHashMap<Long, Service>();
	
	@Override
	public void add(Service m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
		updateSession();
	}
	
	@Override
	public void update(Service m) {
		add(m);
	}
	
	@Override
	public void remove(Service m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Service> collection) {
		log.debug("Sincronizando datasource de services...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Service m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Service> getAll() {
		return new ArrayList<Service>(data.values());
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
