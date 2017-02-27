package com.extr.domain.student;

import java.io.Serializable;

public class StudentTestRecord implements Serializable {

	/**
	 * 学员管理：习题记录模型
	 */
	private static final long serialVersionUID = -3804512685927186240L;
	private String proTitle;		//专业名称
	private String courseTitle;		//课程名称
	private String chapterTitle;	//章节名称
	private int id;					//int
	private String flag;			//是否有效
	private String cate;			//??
	private String uid;				//userId
	private int cid;				//课程id
	private String cpath;			//章节
	private String no;				//试卷编号
	private int time_limit;			//测试时间
	private int time_used;			//使用时间
	private double score;			//分数
	private int add_date;			//创建时间
	private int time_start;			//测试开始时间
	
	private String add_time;
	
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getProTitle() {
		return proTitle;
	}
	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getChapterTitle() {
		return chapterTitle;
	}
	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
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
