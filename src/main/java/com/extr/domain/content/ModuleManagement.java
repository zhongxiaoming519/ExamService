package com.extr.domain.content;

import java.io.Serializable;

import org.springframework.core.serializer.Serializer;

import com.sun.star.util.SearchFlags;

public class ModuleManagement implements Serializable {
	
	/**
	 * 内容维护：模块管理列表模型
	 */
	private static final long serialVersionUID = -566352561467799898L;
	
	private String name;
	private int id;
	private String paths;
	private String module;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaths() {
		return paths;
	}
	public void setPaths(String paths) {
		this.paths = paths;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}

}
