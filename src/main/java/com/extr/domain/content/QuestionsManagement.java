package com.extr.domain.content;

import java.io.Serializable;

public class QuestionsManagement implements Serializable {
	
	
	/**
	 * 题库管理列表模型
	 */
	private static final long serialVersionUID = -3661756673961662961L;
	
	private int id;
	private int uid;
	private String cate;
	private int cid;
	private int ccid;
	private String type;
	private String title;
	private String op1;
	private String op2;
	private String op3;
	private String op4;
	private String answer;
	private int add_date;
	private double score;
	private String remark;
	private String cpath;
	private String zl;
	private int anli_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCcid() {
		return ccid;
	}
	public void setCcid(int ccid) {
		this.ccid = ccid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getAdd_date() {
		return add_date;
	}
	public void setAdd_date(int add_date) {
		this.add_date = add_date;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCpath() {
		return cpath;
	}
	public void setCpath(String cpath) {
		this.cpath = cpath;
	}
	public String getZl() {
		return zl;
	}
	public void setZl(String zl) {
		this.zl = zl;
	}
	public int getAnli_id() {
		return anli_id;
	}
	public void setAnli_id(int anli_id) {
		this.anli_id = anli_id;
	}
	
	
	

}
