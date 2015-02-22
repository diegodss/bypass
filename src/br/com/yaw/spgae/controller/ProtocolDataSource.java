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

import br.com.yaw.spgae.model.Protocol;


public class ProtocolDataSource implements Serializable, DataSource<Protocol> {
	
	private static Logger log = Logger.getLogger(ProtocolDataSource.class);
	
	private Map<Long, Protocol> data = new LinkedHashMap<Long, Protocol>();
	
	@Override
	public void add(Protocol m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
		updateSession();
	}
	
	@Override
	public void update(Protocol m) {
		add(m);
	}
	
	@Override
	public void remove(Protocol m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Protocol> collection) {
		log.debug("Sincronizando datasource de protocols...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Protocol m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Protocol> getAll() {
		return new ArrayList<Protocol>(data.values());
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
