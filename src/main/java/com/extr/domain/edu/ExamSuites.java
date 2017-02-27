package com.extr.domain.edu;

import java.io.Serializable;

public class ExamSuites implements Serializable {

	/**
	 * 模拟考试试卷模型   exam_suites
	 */
	private static final long serialVersionUID = 2309616294887135437L;
	
	private String examType;
	private int id;
	private String name;
	private int test_type_id;
	private int enabled;
	private int is_real;
	private int is_mock;
	
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTest_type_id() {
		return test_type_id;
	}
	public void setTest_type_id(int test_type_id) {
		this.test_type_id = test_type_id;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getIs_real() {
		return is_real;
	}
	public void setIs_real(int is_real) {
		this.is_real = is_real;
	}
	public int getIs_mock() {
		return is_mock;
	}
	public void setIs_mock(int is_mock) {
		this.is_mock = is_mock;
	}
	
	

}
