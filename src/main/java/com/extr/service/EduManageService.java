package com.extr.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.extr.domain.edu.ChapterExam;
import com.extr.domain.edu.DiffChapterTest;
import com.extr.domain.edu.EduMainMenu;
import com.extr.domain.edu.ExamSuites;
import com.extr.domain.edu.ExamUserExam;
import com.extr.domain.edu.FinalAnswerSheets;
import com.extr.domain.edu.FinalAnswers;
import com.extr.domain.edu.FinalTest;
import com.extr.domain.edu.FinalTests;
import com.extr.domain.edu.Score;
import com.extr.domain.edu.TestTypes;
import com.extr.domain.edu.WorkAndExam;
import com.extr.domain.exam.Answer;
import com.extr.domain.student.StudentApply;

public interface EduManageService {
	
	/*
	 * 获取主菜单模块
	 * Return List<EduMainMenu>
	 */
	List<EduMainMenu> getMainMenuList();
	/*
	 * 远程教育平台：作业与考试列表   编号 课程名称  章节测试   单科考试
	 * Return List<WorkAndExam>
	 */
	List<WorkAndExam> getWorkAndExamList(int userId,String path, int cate);
	/*
	 * 远程教育平台：作业与考试：章节测试列表查询
	 * Return List<ChapterExam>
	 */
	List<ChapterExam> getChapterExamList(int userId, int cate, int cid);
	/*
	 * 远程教育平台：作业与考试：章节测试：分节测试【开始新的答题】
	 * Return List<DiffChapterTest>
	 */
	List<DiffChapterTest> getDiffChapterTestList(int userId, int cid, int cate, int iid, String no);
	
	
	List<DiffChapterTest> getChapterTestListById(String no);
	/*
	 * 远程教育平台：作业与考试：章节测试：创建试卷
	 * Return List<DiffChapterTest>
	 */
	JSONObject createDiffChapterTest(int userId, int cid, int cate, String cpath,Boolean isdk);
	/*
	 * 作业与考试：章节测试||单科考试：交卷
	 * Return List<DiffChapterTest>
	 */
	void submitDiffChapterTest(ExamUserExam eue);
	/*
	 * 远程教育平台：模拟考试：考试类型列表
	 * Return List<TestTypes>
	 */
	List<TestTypes> getMockExamTypeList(int userId);
	
	List<Score> getScores(String userId, String special);
	/*
	 * 远程教育平台：模拟考试：查询模拟考试试卷
	 * Return List<ExamSuites>
	 */
	List<ExamSuites> getExamSuites(int testTypeId, int enabled, int is_real, int is_mock);
	/*
	 * 远程教育平台：模拟考试：获取模拟考试试题
	 * Return JSONObject
	 */
	JSONObject getMockOrRealExamList(int suitesId);
	/*
	 * 模拟考试&结业考试：保存个人交卷记录
	 * Return int
	 */
	int insertFinalTests(FinalTests ft);
	/*
	 * 模拟考试&结业考试：保存个人交卷时间
	 * Return int
	 */
	int insertFinalAnswerSheets(FinalAnswerSheets fas);
	/*
	 * 模拟考试&结业考试：保存个人交卷答案
	 */
	void insertFinalAnswers(List<FinalAnswers> afList);
	
	/*
	 * 学业申请：个人列表查询
	 * Return List<StudentApply>
	 */
	List<StudentApply> getMyApplyList(int userId);
	/*
	 * 学业申请：提交申请
	 * Return int
	 */
	int submitApply(StudentApply sa);
	
	List<ChapterExam> getChapterExamResult(int userId, int cate, List<String> cids);
	
	int insertWebUserCourse(WorkAndExam workAndExam);
	
	List<FinalTest> getFinalScore(String userid);
	
	void updateExamAnswer(Answer answer);
}
