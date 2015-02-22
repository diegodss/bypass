package br.com.yaw.spgae.model;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Service  implements Serializable {

	@Id
	private Long id;
	
/*	private Long clientid;
	private Long protocolid;
*/	

	private String clientid;
	private String protocolid;

	private String url;	
	private String app;
	private String user;	
	private String password;	
	private String obs;

	public Service() {
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getProtocolid() {
		return protocolid;
	}
	public void setProtocolid(String protocolid) {
		this.protocolid = protocolid;
	}
	

	/*public Long getClientid() {
		return clientid;
	}
	public void setClientid(Long clientid) {
		this.clientid = clientid;
	}

	public Long getProtocolid() {
		return protocolid;
	}
	public void setProtocolid(Long protocolid) {
		this.protocolid = protocolid;
	}

	*/
	public String geturl() {
		return url;
	}

	
	public void seturl(String url) {
		this.url = url;
	}

	public void setapp( String app) {
		this.app = app;
		
	}
	public String getapp() {
		return app;
	}
	public void setUser( String user) {
		this.user = user;
	}
	public String getUser (){
		return user;		
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	public void setObs(String obs){
		this.obs = obs;
	}
	public String getObs(){
		return obs;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		
		if ((obj == null) || (obj.getClass() != this.getClass())) {
			return false;
		}
		
		Service outro = (Service) obj;
		boolean equal = (id != null && id.equals(outro.id)) 
				|| (url != null && url.equals(outro.url)) ;
		return equal;
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		
		hash = (31 * hash) + (id == null ? 0 : id.intValue());
		hash = (31 * hash) + (url == null ? 0 : url.hashCode());
		
		return hash;
	}

}
