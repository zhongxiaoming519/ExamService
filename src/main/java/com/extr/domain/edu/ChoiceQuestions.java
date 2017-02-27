package com.extr.domain.edu;

import java.io.Serializable;

public class ChoiceQuestions implements Serializable {

	/**
	 * 模拟考试：选择题模型
	 */
	private static final long serialVersionUID = 4586627662789802384L;
	
	private int id;			//int
	private int suite_id;	//int
	private int order_no;	//int
	private int type_no;	//int
	private String text;	//varchar
	private String a;		//varchar
	private String b;		//varchar
	private String c;		//varchar
	private String d;		//varchar
	private String answer;	//varchar
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSuite_id() {
		return suite_id;
	}
	public void setSuite_id(int suite_id) {
		this.suite_id = suite_id;
	}
	public int getOrder_no() {
		return order_no;
	}
	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}
	public int getType_no() {
		return type_no;
	}
	public void setType_no(int type_no) {
		this.type_no = type_no;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	

}
