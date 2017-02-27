package com.extr.domain.edu;

import java.io.Serializable;

import com.sun.star.util.Date;

public class ExtractExam implements Serializable {

	/**
	 * 远程教育平台：作业与考试：章节测试：抽取试卷考题的模型
	 */
	private static final long serialVersionUID = -3371331906482776240L;
	
	private int cid;		//课程id
	private int type;		//题目类型    2单选    3多选
	private String title;	//题目名称
	private String op1;		//选项1
	private String op2;		//选项2
	private String op3;		//选项3
	private String op4;		//选项4
	private String answer;	//答案
	private Date add_date;	//创建时间
	private String cpath;	//章节路径
	private String exam_set;//当前题目分数
	private int duration;	//考题时间
	private double score;
	
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getAdd_date() {
		return add_date;
	}
	public void setAdd_date(Date add_date) {
		this.add_date = add_date;
	}
	public String getCpath() {
		return cpath;
	}
	public void setCpath(String cpath) {
		this.cpath = cpath;
	}
	public String getExam_set() {
		return exam_set;
	}
	public void setExam_set(String exam_set) {
		this.exam_set = exam_set;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	

}
