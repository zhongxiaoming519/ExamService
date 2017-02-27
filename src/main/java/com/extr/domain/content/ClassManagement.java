package com.extr.domain.content;

import java.io.Serializable;

public class ClassManagement implements Serializable {
	
	
	/**
	 * 课程管理列表模型
	 */
	private static final long serialVersionUID = 377902576494463112L;
	private int id;
	private int uid;
	private int pid;//课程id
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String state;
	private String no;
	private String path;
	private String pmTitle; //专业管理名称
	private String title;	//课程名称
	private int xueshi;
	private double xuefen;
	private int date_limit;
	private double cost;
	private String text;
	private int add_date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getXueshi() {
		return xueshi;
	}
	public void setXueshi(int xueshi) {
		this.xueshi = xueshi;
	}
	public int getDate_limit() {
		return date_limit;
	}
	public void setDate_limit(int date_limit) {
		this.date_limit = date_limit;
	}
	public double getXuefen() {
		return xuefen;
	}
	public void setXuefen(double xuefen) {
		this.xuefen = xuefen;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getAdd_date() {
		return add_date;
	}
	public void setAdd_date(int add_date) {
		this.add_date = add_date;
	}
	public String getPmTitle() {
		return pmTitle;
	}
	public void setPmTitle(String pmTitle) {
		this.pmTitle = pmTitle;
	}
	

}
