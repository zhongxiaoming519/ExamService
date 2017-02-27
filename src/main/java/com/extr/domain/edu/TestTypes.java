package com.extr.domain.edu;

import java.io.Serializable;

public class TestTypes implements Serializable {

	/**
	 * 模拟考试：考试类型模型 test_types
	 */
	private static final long serialVersionUID = -8145447510174129367L;
	
	private int id;			//int
	private String name;	//	varchar
	private int duration;	//int
	private int major_id;	//int
	private int enabled;	//tinyint
	private String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getMajor_id() {
		return major_id;
	}
	public void setMajor_id(int major_id) {
		this.major_id = major_id;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	

}
