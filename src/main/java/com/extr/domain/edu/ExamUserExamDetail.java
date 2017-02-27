package com.extr.domain.edu;

import java.io.Serializable;

public class ExamUserExamDetail implements Serializable {

	/**
	 * 远程教育平台：作业与考试：章节测试：创建试卷对应的试题模型   exam_user_exam_detail
	 */
	private static final long serialVersionUID = 7974174026894823488L;
	private int id;				//int
	private int iid;			//int
	private int ord;			//int
	private String sub_type;	//varchar
	private String sub_title;	//varchar
	private String sub_op1;		//varchar
	private String sub_op2;		//varchar
	private String sub_op3;		//varchar
	private String sub_op4;		//varchar
	private String sub_answer;	//varchar
	private double sub_score;	//decimal
	private String answer;		//varchar
	private String sub_remark;	//text
	private String tip;			//varchar
	
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
	public String getSub_type() {
		return sub_type;
	}
	public void setSub_type(String sub_type) {
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
