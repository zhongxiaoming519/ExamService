package com.extr.domain.edu;

import java.io.Serializable;

public class EssayQuestions implements Serializable {

	/**
	 * 模拟考试：问答题模型 子集
	 */
	private static final long serialVersionUID = -1101192467954819605L;
	
	private int id;			//int
	private int suite_id;	//int
	private int order_no;	//int
	private String text;	//varchar
	private String answer;	//varchar
	private int score;		//int
	
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
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
	
}
