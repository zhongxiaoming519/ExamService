package com.extr.domain.content;

import java.io.Serializable;

public class ProfessionalManagement implements Serializable {
	
	
	/**
	 * 内容维护模块：专业管理表模型 【exam_path】
	 */
	private static final long serialVersionUID = 4990060519627083882L;
	private int id;
	private String path;
	private int deep;
	private int ord;
	private String module;
	private String title;
	private int valid;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getValid() {
		return valid;
	}
	public void setValid(int valid) {
		this.valid = valid;
	}
	
	
	
}
