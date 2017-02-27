package com.extr.domain.manage;

import java.io.Serializable;

public class ExamChannel implements Serializable {
	
	
	/**
	 *  教育平台后台管理树模型
	 */
	private static final long serialVersionUID = 2855245741716414570L;
	private int id;
	private String path;
	private int deep;
	private int ord;
	private String is_path;
	private String title;
	private String down_split;
	private String module;
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
	public String getIs_path() {
		return is_path;
	}
	public void setIs_path(String is_path) {
		this.is_path = is_path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDown_split() {
		return down_split;
	}
	public void setDown_split(String down_split) {
		this.down_split = down_split;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
