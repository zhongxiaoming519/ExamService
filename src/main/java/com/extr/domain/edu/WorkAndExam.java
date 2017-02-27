package com.extr.domain.edu;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkAndExam implements Serializable {

	/**
	 * 远程教育平台：作业与考试列表模型     【exam_user_course】
	 */
	private static final long serialVersionUID = -8121922543119676585L;

	private int id;				//
	private String state; 		//??
	private int uid;			//userId
	private int cid;			//课程id
	private String no;			//课程顺序
	private String title;		//课程名称
	private Date date_start;	//开始时间		
	private String path;		//
	private Date add_date;	//创建时间
	private int duration;		//延期
	private int chpid;			//??
	private int syncpoint;		//??
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate_start() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (this.date_start != null) {
			return sdf.format(this.date_start.getTime());
		} else {
			return null;
		}
	}
	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAdd_date() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (this.add_date != null) {
			return sdf.format(this.add_date.getTime());
		} else {
			return null;
		}
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	public String getDuration() {
		long days = this.duration / (1000 * 60 * 60 * 24);  
	    long hours = (this.duration % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);  
	    long minutes = (this.duration % (1000 * 60 * 60)) / (1000 * 60);  
	    long seconds = (this.duration % (1000 * 60)) / 1000;  
	    return days + " days " + hours + " hours " + minutes + " minutes "  
	            + seconds + " seconds ";  
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getChpid() {
		return chpid;
	}
	public void setChpid(int chpid) {
		this.chpid = chpid;
	}
	public int getSyncpoint() {
		return syncpoint;
	}
	public void setSyncpoint(int syncpoint) {
		this.syncpoint = syncpoint;
	}
	
	

}
