package com.extr.domain.exam;

import java.io.Serializable;

public class ExamManagement implements Serializable {

	/**
	 * 试卷维护：结业考试：管理试卷表模型 exam_suites
	 */
	private static final long serialVersionUID = -3729491594844843050L;

	private int id;	
	private String name;	//试卷名称
	private int test_type_id;	//试卷类型表【exam_types】的主键id
	private int enabled;	//是否有效；  0无效   1有效
	private int is_real;	//是否为结业考试试卷     0否    1是
	private int is_mock;	//是否为模考试卷       0否    1是
	
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
