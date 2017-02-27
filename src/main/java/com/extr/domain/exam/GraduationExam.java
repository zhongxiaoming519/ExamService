package com.extr.domain.exam;

import java.io.Serializable;

public class GraduationExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4501996693587397621L;
	
	private int id;	 		//主键id
	private String name;	//结业考试类型名称
	private int duration;	//时长（分钟）
	private int major_id;	//专业
	private int enabled;	//是否有效
	private String specialName;//专业名称
	private String detail;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getSpecialName() {
		return specialName;
	}
	public void setSpecialName(String specialName) {
		this.specialName = specialName;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	


}
