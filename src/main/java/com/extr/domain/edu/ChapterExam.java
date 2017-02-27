package com.extr.domain.edu;

import java.io.Serializable;

public class ChapterExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7667610938860858069L;
	
	private int chapterId;		//章节id
	private String chapterTitle;//章节名称
	private int courseId;		//课程id
	private String cpath;		//章节路径
	private double maxScore;	//当前章节测试的最高分
	private int duration; //考试时长
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterTitle() {
		return chapterTitle;
	}
	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCpath() {
		return cpath;
	}
	public void setCpath(String cpath) {
		this.cpath = cpath;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	
	
	
}
