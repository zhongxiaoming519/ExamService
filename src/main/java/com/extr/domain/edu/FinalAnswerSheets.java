package com.extr.domain.edu;

import java.io.Serializable;
import java.util.Date;

public class FinalAnswerSheets implements Serializable {

	/**
	 * 模拟考试&结业考试：交卷   final_answer_sheets
	 */
	private static final long serialVersionUID = -5725620520277747844L;
	
	private int id;				//int
	private int final_test_id;	//int
	private Date submit_time;	//datetime
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFinal_test_id() {
		return final_test_id;
	}
	public void setFinal_test_id(int final_test_id) {
		this.final_test_id = final_test_id;
	}
	public Date getSubmit_time() {
		return submit_time;
	}
	public void setSubmit_time(Date submit_time) {
		this.submit_time = submit_time;
	}
	
	

}
