package com.extr.domain.edu;

import java.io.Serializable;
import java.util.Date;

public class FinalTests implements Serializable {

	/**
	 * 模拟考试&结业考试：交卷   final_tests
	 */
	private static final long serialVersionUID = -1246669113986100850L;
	
	private int id;				//int
	private int user_id;		//int
	private int test_type_id;	//int
	private int test_suite_id;	//int
	private int finished;		//tinyint
	private Date start_time;	//datetime
	private Date end_time;		//datetime
	private double score;		//分数
	private int question_count;	//答题累计
	private int mock_test;		//是否为模拟考试     0否    1是
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getTest_type_id() {
		return test_type_id;
	}
	public void setTest_type_id(int test_type_id) {
		this.test_type_id = test_type_id;
	}
	public int getTest_suite_id() {
		return test_suite_id;
	}
	public void setTest_suite_id(int test_suite_id) {
		this.test_suite_id = test_suite_id;
	}
	public int getFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getQuestion_count() {
		return question_count;
	}
	public void setQuestion_count(int question_count) {
		this.question_count = question_count;
	}
	public int getMock_test() {
		return mock_test;
	}
	public void setMock_test(int mock_test) {
		this.mock_test = mock_test;
	}
	
	
	
	
}
