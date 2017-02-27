package com.extr.domain.edu;

import java.io.Serializable;
import java.util.Date;

public class ExamUserExam implements Serializable {

	/**
	 * 远程教育平台：作业与考试：章节测试：创建试卷模型  exam_user_exam表
	 */
	private static final long serialVersionUID = 8034244828313828919L;
	
	private int id;
	private String flag;
	private String cate;
	private String uid;			//用户id
	private int cid;			//课程id
	private String cpath;		//章节
	private String no;			//考卷编号 随机
	private int time_limit;		//考试时间
	private int time_used;		//实际答卷时间
	private double score;		//当前考卷总分
	private int add_date;		//当前记录创建时间
	private int time_start;	//开始时间
	private boolean isDanke;
	public boolean isDanke() {
		return isDanke;
	}
	public void setDanke(boolean isDanke) {
		this.isDanke = isDanke;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
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
		this.time_limit = time_limit;
	}
	public int getTime_used() {
		return time_used;
	}
	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getAdd_date() {
		return add_date;
	}
	public void setAdd_date(int add_date) {
		this.add_date = add_date;
	}
	public int getTime_start() {
		return time_start;
	}
	public void setTime_start(int time_start) {
		this.time_start = time_start;
	}
	
	
	
	

}
