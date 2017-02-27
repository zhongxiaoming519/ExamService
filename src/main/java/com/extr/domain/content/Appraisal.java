package com.extr.domain.content;

import java.io.Serializable;

public class Appraisal implements Serializable {

	/**
	 * 鉴定辅助表模型
	 */
	private static final long serialVersionUID = -8647420697199581151L;
	
	private int id;
	private int uid;
	private String no;
	private String path;
	private String title;
	private int add_date;
	private String state;
	
	public String getPpt_url() {
		return ppt_url;
	}
	public void setPpt_url(String ppt_url) {
		this.ppt_url = ppt_url;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	private String ppt_url;
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	private String video_url;
	private String text;
	public int getOrd() {
		return ord;
	}
	public void setOrd(int ord) {
		this.ord = ord;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	public String getVideo_url1() {
		return video_url1;
	}
	public void setVideo_url1(String video_url1) {
		this.video_url1 = video_url1;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	private int ord;
	private int deep;
	private String video_url1;
	private String attachment;
	private int iid;
	
	public int getIid() {
		return iid;
	}
	public void setIid(int iid) {
		this.iid = iid;
	}
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAdd_date() {
		return add_date;
	}
	public void setAdd_date(int add_date) {
		this.add_date = add_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
