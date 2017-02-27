package com.extr.domain.student;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duration implements Serializable {
	
	/**
	 * 延期管理列表模型
	 */
	private static final long serialVersionUID = 631764746134319501L;
	
	private int id;
	public int getReg_date() {
		return reg_date;
	}
	public void setReg_date(int reg_date) {
		this.reg_date = reg_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private int reg_date;
	private int reg_end;
	public int getReg_end() {
		return reg_end;
	}
	public void setReg_end(int reg_end) {
		this.reg_end = reg_end;
	}
	private String state;
	private String username;
	private String name;
	private String title;
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getExtend_duration() {
		return extend_duration;
	}
	public void setExtend_duration(int extend_duration) {
		this.extend_duration = extend_duration;
	}
	public int getPause_duration() {
		return pause_duration;
	}
	public void setPause_duration(int pause_duration) {
		this.pause_duration = pause_duration;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int duration;
	private int extend_duration;
	private int pause_duration;
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
	
	

}
