package com.extr.service;

import java.io.Serializable;
import java.util.List;

import com.extr.domain.edu.EssayQuestions;
import com.extr.domain.edu.ExamUserExam;
import com.extr.domain.exam.CaseQuestion;
import com.extr.domain.exam.CaseSubQuestion;
import com.extr.domain.exam.ChapterTest;
import com.extr.domain.exam.ChoiceQuestion;
import com.extr.domain.exam.EssayContent;
import com.extr.domain.exam.EssayQuestion;
import com.extr.domain.exam.ExamManagement;
import com.extr.domain.exam.QuestionInfo;
import com.extr.domain.exam.TestType;
import com.extr.util.Page;

public interface ExamService {
	
	/*
	 * 试卷维护：章节测试
	 * Return List<ChapterTest>
	 */
	List<ChapterTest> getChapterTestList(int cate, Page<ChapterTest> page, String username,String course,String chapter);
	
	/***
	 * 章节测试总数
	 * @param cate
	 * @param username
	 * @return
	 */
	int getChapterTestListCount(int cate,String username,String course,String chapter);
	/*
	 * 试卷维护：结业考试列表查询
	 * Return List<Serializable>
	 */
	List<Serializable> getGraduationExamList();
	/*
	 * 试卷维护：修改结业考试记录有效值
	 */
	void updateGraduationExamEnabledById(int id, int enabled);
	/*
	 * 试卷维护：管理试卷列表查询
	 */
	List<ExamManagement> getExamManagementList(int examTypesId);
	/*
	 * 试卷维护：修改管理试卷记录：是否有效、是否设为结业考试、是否设为模拟考试
	 */
	void updateExamManagementById(int id, int enabled, int is_real, int is_mock);
	/*
	 * 试卷维护：添加试卷
	 */
	int createExamByTestTypeId(ExamManagement em);
	
	int createTestTypes(TestType testType);
	
	void createQuestionInfo(QuestionInfo questionInfo);
	
	List<ChoiceQuestion> getChoiceQuestions(int id);
	
	void deleteChoiceQuestionById(int id);
	void updateChoiceQuestion(ChoiceQuestion cq);
	
	List<QuestionInfo> getExamStaticByTestTypeId(int id);
	
	void insertChoiceQuestion(ChoiceQuestion cq);

	void insertCaseQuestion(CaseQuestion cq);

	void updateCaseQuestion(CaseQuestion cq);

	void deleteCaseQuestion(int id);

	List<CaseQuestion> getCaseQuestions(int suitId);

	List<CaseSubQuestion> getCaseSubQuestions(int suitId);

	void updateSubCaseQuestion(CaseSubQuestion cq);

	void insertSubChoiceQuestion(CaseSubQuestion cq);

	void deleteSubCaseQuestionByidxAndCaseQuestionId(CaseSubQuestion cq);

	List<EssayQuestions> getEssayQuestionBySuiteid(int id);

	EssayContent getEssayContentBySuiteid(int id);

	void insertEssayContent(EssayContent cq);

	void updateEssayContent(EssayContent cq);

	void updateEssayQuestionByid(EssayQuestion cq);

	void deleteEssayQuestionByid(int id);

	void insertEssayQuestion(EssayQuestion cq);

	void insertChapterRocord(ExamUserExam eue);
	
	void deteleSuiteById(int id);
}
