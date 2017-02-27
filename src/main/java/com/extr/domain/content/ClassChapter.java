package com.extr.domain.content;

import java.io.Serializable;

public class ClassChapter implements Serializable {
	
	
	/**
	 * 课程管理-->章节设置模型
	 */
	private static final long serialVersionUID = -8966553703080036780L;
	private int id;
	private int iid;
	private int ord;
	private String path;
	private int deep;
	private String title;
	private String ppt_url;
	private String video_url;
	private String text;
	private String video_url1="";
	private String cate;
	private String exam_set;
	private int duration;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPpt_url() {
		return ppt_url;
	}
	public void setPpt_url(String ppt_url) {
		this.ppt_url = ppt_url;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getVideo_url1() {
		return video_url1;
	}
	public void setVideo_url1(String video_url1) {
		this.video_url1 = video_url1;
	}
	public String getCate() {
		return cate;
	}
	public void setCate(String cate) {
		this.cate = cate;
	}
	public String getExam_set() {
		return exam_set;
	}
	public void setExam_set(String exam_set) {
		this.exam_set = exam_set;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}
