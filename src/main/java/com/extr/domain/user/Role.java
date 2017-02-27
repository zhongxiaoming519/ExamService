package com.extr.domain.user;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Role implements Serializable {
	private static final long serialVersionUID = -6541723313940343320L;
	private int id;
	private String authority;
	private String name;
	private String code;

	private String priv;
	private int add_date;


	public int getAdd_date() {
		return add_date;
	}

	public void setAdd_date(int add_date) {
		this.add_date = add_date;
	}

	public String getPriv() {
		return priv;
	}

	public void setPriv(String priv) {
		this.priv = priv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
