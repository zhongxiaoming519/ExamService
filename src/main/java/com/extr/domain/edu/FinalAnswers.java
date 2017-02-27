package com.extr.domain.edu;

import java.io.Serializable;

public class FinalAnswers implements Serializable {

	/**
	 * 模拟考试&结业考试：交卷   final_answers
	 */
	private static final long serialVersionUID = 3100349705323483700L;
	
	private int final_answer_sheet_id;	//int
	private int idx;					//int
	private int question_id;			//int
	private String answer_text;			//varchar
	
	public int getFinal_answer_sheet_id() {
		return final_answer_sheet_id;
	}
	public void setFinal_answer_sheet_id(int final_answer_sheet_id) {
		this.final_answer_sheet_id = final_answer_sheet_id;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getAnswer_text() {
		return answer_text;
	}
	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}
	
	

}
