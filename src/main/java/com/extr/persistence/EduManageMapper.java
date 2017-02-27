package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.edu.CaseQuestions;
import com.extr.domain.edu.CaseSubQuestions;
import com.extr.domain.edu.ChapterExam;
import com.extr.domain.edu.ChoiceQuestions;
import com.extr.domain.edu.DiffChapterTest;
import com.extr.domain.edu.EduMainMenu;
import com.extr.domain.edu.EssayContents;
import com.extr.domain.edu.EssayQuestions;
import com.extr.domain.edu.ExamSuites;
import com.extr.domain.edu.ExamUserExam;
import com.extr.domain.edu.ExamUserExamDetail;
import com.extr.domain.edu.ExtractExam;
import com.extr.domain.edu.FinalAnswerSheets;
import com.extr.domain.edu.FinalAnswers;
import com.extr.domain.edu.FinalTest;
import com.extr.domain.edu.FinalTests;
import com.extr.domain.edu.Score;
import com.extr.domain.edu.TestTypes;
import com.extr.domain.edu.WorkAndExam;
import com.extr.domain.exam.Answer;
import com.extr.domain.student.StudentApply;

public interface EduManageMapper {

	/*
	 * 获取主菜单模块
	 * Return List<EduMainMenu>
	 */
	List<EduMainMenu> getMainMenuList();
	/*
	 * 远程教育平台：作业与考试列表   编号 课程名称  章节测试   单科考试
	 * Return List<WorkAndExam>
	 */
	List<WorkAndExam> getWorkAndExamList(@Param("userId") int userId, @Param("path") String path,@Param("cate") int cate);
	/*
	 * 远程教育平台：作业与考试：章节测试列表查询
	 * Return List<ChapterExam>
	 */
	List<ChapterExam> getChapterExamList(
			@Param("userId") int userId, 
			@Param("cate") int cate, 
			@Param("cid") int cid);
	/*
	 * 远程教育平台：作业与考试：章节测试：分节测试【开始新的答题】
	 * Return List<DiffChapterTest>
	 */
	List<DiffChapterTest> getDiffChapterTestList(
			@Param("userId") int userId, 
			@Param("cid") int cid, 
			@Param("cate") int cate,
			@Param("iid") int iid, 
			@Param("no") String no);
	
	/*
	 * 远程教育平台：作业与考试：章节测试：抽取考题
	 * Return List<ExtractExam>
	 */
	List<ExtractExam> extractExam(@Param("cid") int cid, @Param("cpath") String cpath);
	
	
	/*
	 * 远程教育平台：作业与考试：单科测试：抽取考题
	 * Return List<ExtractExam>
	 */
	List<ExtractExam> extractExamDK(@Param("id") int id);
	/*
	 * 远程教育平台：作业与考试：章节测试：新增考卷
	 * Return int
	 */
	int insertExamUserExam(ExamUserExam eue);
	/*
	 * 远程教育平台：作业与考试：章节测试：查询考卷
	 * Return ExamUserExam
	 */
	ExamUserExam selectExamUserExamById(@Param("id") int id);
	/*
	 * 远程教育平台：作业与考试：章节测试：新增考题
	 */
	void insertExamUserExamDetail(List<ExamUserExamDetail> insertList);
	/*
	 * 作业与考试：章节测试||单科考试：交卷
	 */
	void submitDiffChapterTest(ExamUserExam eue);
	
	/*
	 * 远程教育平台：模拟考试：考试类型列表
	 * Return List<TestTypes>
	 */
	List<TestTypes> getMockExamTypeList(@Param("userId") int userId);
	
	/*
	 * 远程教育平台：模拟考试：查询模拟考试试卷
	 * Return List<ExamSuites>
	 */
	List<ExamSuites> getExamSuites(
			@Param("testTypeId") int testTypeId, 
			@Param("enabled") int enabled, 
			@Param("is_real") int is_real,
			@Param("is_mock") int is_mock);
	/*
	 * 远程教育平台：模拟考试：获取选择题
	 * Return List<ChoiceQuestions>
	 */
	List<ChoiceQuestions> selectChoiceQuestions(@Param("suitesId") int suitesId);
	/*
	 * 远程教育平台：模拟考试：获取案例分析题
	 * Return List<CaseQuestions>
	 */
	List<CaseQuestions> selectCaseQuestions(@Param("suitesId") int suitesId);
	/*
	 * 远程教育平台：模拟考试：获取案例分析题 子集
	 * Return List<CaseSubQuestions>
	 */
	List<CaseSubQuestions> selectCaseSubQuestions(@Param("case_question_id") int case_question_id);
	/*
	 * 远程教育平台：模拟考试：获取问答题 
	 * Return List<EssayContents>
	 */
	EssayContents selectEssayContents(@Param("suitesId") int suitesId);
	/*
	 * 远程教育平台：模拟考试：获取问答题 子集
	 * Return List<EssayContents>
	 */
	List<EssayQuestions> selectEssayQuestions(@Param("suitesId") int suitesId);
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
	List<StudentApply> getMyApplyList(@Param("userId")int userId);
	/*
	 * 学业申请：提交申请
	 * Return int
	 */
	int submitApply(StudentApply sa);
	
	List<ChapterExam> getChapterExamResult(@Param("userId")int userId, @Param("cate")int cate, @Param("cids")List<String> cids);
	
	List<DiffChapterTest> getChapterTestListById(String no);
	
	int insertWebUserCourse(WorkAndExam workAndExam);
	List<FinalTest> getFinalScore(String userid);
	List<Score> getScores(@Param("userId")String userId, @Param("special")String special);
	void updateExamAnswer(Answer answer);
	
}




