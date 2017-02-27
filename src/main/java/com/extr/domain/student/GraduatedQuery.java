package com.extr.domain.student;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GraduatedQuery implements Serializable {
	
	/**
	 * 结业查询列表模型
	 */
	private static final long serialVersionUID = -8862696158698499876L;
	
	private int id;
	private String username;
	private String name;
	private String title;
	private int is_graduated;
	private int graduate_date;
	private String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getGraduate_date() {
		return graduate_date;
	}
	public void setGraduate_date(int graduate_date) {
		this.graduate_date = graduate_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getIs_graduated() {
		return is_graduated;
	}
	public void setIs_graduated(int is_graduated) {
		this.is_graduated = is_graduated;
	}

	
	

}
