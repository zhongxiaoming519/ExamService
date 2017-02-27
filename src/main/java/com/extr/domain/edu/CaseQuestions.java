package com.extr.domain.edu;

import java.io.Serializable;
import java.util.List;

public class CaseQuestions implements Serializable {

	/**
	 * 案例分析题模型  case_questions
	 */
	private static final long serialVersionUID = -41719556883575353L;
	
	private int id;			//int
	private int suite_id;	//int
	private int order_no;	//int
	private String text;	//varchar
	private List<CaseSubQuestions> caseSubQuestions;
	
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<CaseSubQuestions> getCaseSubQuestions() {
		return caseSubQuestions;
	}
	public void setCaseSubQuestions(List<CaseSubQuestions> caseSubQuestions) {
		this.caseSubQuestions = caseSubQuestions;
	}
	
	

	
	
	
	
}
