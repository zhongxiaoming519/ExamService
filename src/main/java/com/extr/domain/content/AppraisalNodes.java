package com.extr.domain.content;

import java.io.Serializable;

public class AppraisalNodes implements Serializable {

	/**
	 * 内容维护：鉴定辅导：借点设置表模型
	 */
	private static final long serialVersionUID = -8366912174912662331L;
	private int id;
	private int iid;
	private int ord;
	private String path;
	private int deep;
	private String title;
	private String ppt_url;
	private String video_url;
	private String text;
	private String video_url1;
	private String attachment;
	
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
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	

}
