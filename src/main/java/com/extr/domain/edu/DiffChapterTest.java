package com.extr.domain.edu;

import java.io.Serializable;

public class DiffChapterTest implements Serializable {

	/**
	 * 分解测试 试卷模型	exam_user_exam_detail
	 */
	private static final long serialVersionUID = 4401543919720128006L;
	
	private String no;		//试卷编号
	private int id;			//分解测试id
	private int iid;		//exam_user_exam_detail的id
	private int ord;
	private int sub_type;
	private String sub_title;//考题
	private String sub_op1;
	private String sub_op2;
	private String sub_op3;
	private String sub_op4;
	private String sub_answer;
	private double sub_score;
	private String answer;
	private String sub_remark;
	private String tip;
	private String username;
	private int  time_limit;
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTime_used() {
		return time_used;
	}
	public void setTime_used(int time_used) {
		this.time_used = time_used;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int score;
	private int time_used;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public int getSub_type() {
		return sub_type;
	}
	public void setSub_type(int sub_type) {
		this.sub_type = sub_type;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getSub_op1() {
		return sub_op1;
	}
	public void setSub_op1(String sub_op1) {
		this.sub_op1 = sub_op1;
	}
	public String getSub_op2() {
		return sub_op2;
	}
	public void setSub_op2(String sub_op2) {
		this.sub_op2 = sub_op2;
	}
	public String getSub_op3() {
		return sub_op3;
	}
	public void setSub_op3(String sub_op3) {
		this.sub_op3 = sub_op3;
	}
	public String getSub_op4() {
		return sub_op4;
	}
	public void setSub_op4(String sub_op4) {
		this.sub_op4 = sub_op4;
	}
	public String getSub_answer() {
		return sub_answer;
	}
	public void setSub_answer(String sub_answer) {
		this.sub_answer = sub_answer;
	}
	public double getSub_score() {
		return sub_score;
	}
	public void setSub_score(double sub_score) {
		this.sub_score = sub_score;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSub_remark() {
		return sub_remark;
	}
	public void setSub_remark(String sub_remark) {
		this.sub_remark = sub_remark;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
	
	

}
