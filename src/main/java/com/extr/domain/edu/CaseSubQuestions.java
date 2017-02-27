package com.extr.domain.edu;

import java.io.Serializable;

public class CaseSubQuestions implements Serializable {

	/**
	 * 案例分析题 子集模型  case_sub_questions
	 */
	private static final long serialVersionUID = -2639659632017730632L;
	
	private int case_question_id;	//int
	private int idx;				//int
	private String text;			//varchar
	private String a;				//varchar
	private String b;				//varchar
	private String c;				//varchar
	private String d;				//varchar
	private String answer;			//varchar
	private int type_id;			//int
	
	public int getCase_question_id() {
		return case_question_id;
	}
	public void setCase_question_id(int case_question_id) {
		this.case_question_id = case_question_id;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
	
	
	
}
