package com.extr.domain.edu;

import java.io.Serializable;
import java.util.List;

public class EssayContents implements Serializable {

	/**
	 * 模拟考试：问答题模型
	 */
	private static final long serialVersionUID = 4309387877838406233L;
	
	private int id;			//int
	private int suite_id;	//int
	private String text;	//varchar
	private List<EssayQuestions> essayQuestions;
	
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<EssayQuestions> getEssayQuestions() {
		return essayQuestions;
	}
	public void setEssayQuestions(List<EssayQuestions> essayQuestions) {
		this.essayQuestions = essayQuestions;
	}
	
	

}
