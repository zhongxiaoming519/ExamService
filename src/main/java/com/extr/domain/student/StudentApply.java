package com.extr.domain.student;

import java.io.Serializable;

public class StudentApply implements Serializable {
	
	/**
	 * 学员管理：申请处理表模型  web_user_apply
	 */
	private static final long serialVersionUID = -7583257068454687297L;
	private int id;				//int
	private String applystate;	//varchar
	private int startdate;		//int
	private int enddate;		//int
	private int applydate;		//int
	private int approve;		//int
	private int userid;			//int
	private String comment;		//varchar
	private String state;		//申请状态
	private String username;
	private int userId = 0;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String name;

	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApplystate() {
		return applystate;
	}
	public void setApplystate(String applystate) {
		this.applystate = applystate;
	}
	public int getStartdate() {
		return startdate;
	}
	public void setStartdate(int startdate) {
		this.startdate = startdate;
	}
	public int getEnddate() {
		return enddate;
	}
	public void setEnddate(int enddate) {
		this.enddate = enddate;
	}
	public int getApplydate() {
		return applydate;
	}
	public void setApplydate(int applydate) {
		this.applydate = applydate;
	}
	public int getApprove() {
		return approve;
	}
	public void setApprove(int approve) {
		this.approve = approve;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	
	
	
	
}
