package com.extr.domain.exam;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChapterTest {
	
	private int id;
	private String flag;
	private String cate;
	private int uid;
	private int cid;
	private String cpath;
	private String no;
	private int time_limit;
	private int time_used;
	private double score;
	private int  add_date;
	public int getAdd_date() {
		return add_date;
	}
	public void setAdd_date(int add_date) {
		this.add_date = add_date;
	}
	private Date time_start;
	private String chapter;
	private String course;
	private String state;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
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
	public String getCpath() {
		return cpath;
	}
	public void setCpath(String cpath) {
		this.cpath = cpath;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		Date t = new Date(time_limit);
		this.time_limit = t.getMinutes();
	}
	public int getTime_used() {
		return time_used;
	}
	public void setTime_used(int time_used) {
		Date t = new Date(time_used);
		this.time_used = t.getMinutes();
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}

	public String getTime_start() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (time_start != null) {
			return sdf.format(time_start.getTime());
		} else {
			return null;
		}
	}
	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}
	
	

}
