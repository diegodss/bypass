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

import br.com.yaw.spgae.model.Produto;


public class ProdutoDataSource implements Serializable, DataSource<Produto> {
	
	private static Logger log = Logger.getLogger(ProdutoDataSource.class);
	
	private Map<Long, Produto> data = new LinkedHashMap<Long, Produto>();
	
	@Override
	public void add(Produto m) {
		if (m != null) {
			this.data.put(m.getId(), m);
		}
		updateSession();
	}
	
	@Override
	public void update(Produto m) {
		add(m);
	}
	
	@Override
	public void remove(Produto m) {
		if (m != null) {
			this.data.remove(m.getId());
		}
		updateSession();
	}
	
	@Override
	public void synch(Collection<Produto> collection) {
		log.debug("Sincronizando datasource de produtos...");
		this.data.clear();
		if (collection == null) {
			return;
		}
		for (Produto m: collection) {
			if (m != null) {
				this.data.put(m.getId(), m);
			}
		}
		updateSession();
	}
	
	@Override
	public List<Produto> getAll() {
		return new ArrayList<Produto>(data.values());
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
