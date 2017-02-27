package com.extr.persistence;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.edu.EssayQuestions;
import com.extr.domain.edu.ExamUserExam;
import com.extr.domain.exam.CaseQuestion;
import com.extr.domain.exam.CaseSubQuestion;
import com.extr.domain.exam.ChapterTest;
import com.extr.domain.exam.ChoiceQuestion;
import com.extr.domain.exam.ColumnContent;
import com.extr.domain.exam.EssayContent;
import com.extr.domain.exam.EssayQuestion;
import com.extr.domain.exam.ExamItem;
import com.extr.domain.exam.ExamManagement;
import com.extr.domain.exam.QuestionInfo;
import com.extr.domain.exam.TestType;
import com.extr.util.Page;

public interface ExamMapper {
	
	/*
	 * 试卷维护：章节测试
	 * Return List<ChapterTest>
	 */
	List<ChapterTest> selectChapterTestList(
			@Param("cate") int cate,
			@Param("page") Page<ChapterTest> page,
			@Param("username") String username,@Param("course") String course,@Param("chapter") String chapter);
	/*
	 * 试卷维护：结业考试列表查询
	 * Return List<Serializable>
	 */
	List<Serializable> getGraduationExamList();
	/*
	 * 试卷维护：修改结业考试记录有效值
	 */
	void updateGraduationExamEnabledById(@Param("id") int id, @Param("enabled") int enabled);
	/*
	 * 试卷维护：管理试卷列表查询
	 */
	List<ExamManagement> getExamManagementList(@Param("examTypesId") int examTypesId);
	/*
	 * 试卷维护：修改管理试卷记录：是否有效、是否设为结业考试、是否设为模拟考试
	 */
	void updateExamManagementById(
			@Param("id") int id, 
			@Param("enabled") int enabled, 
			@Param("is_real") int is_real, 
			@Param("is_mock") int is_mock);
	/*
	 * 试卷维护：添加试卷
	 */
	int createExamByTestTypeId(ExamManagement em);
	
	int getChapterTestListCount(@Param("cate") int cate,@Param("username") String username,@Param("course") String course,@Param("chapter") String chapter);
	
	int createTestTypes(TestType testType);
	void createQuestionInfo(QuestionInfo questionInfo);
	
	List<ChoiceQuestion> getChoiceQuestions(@Param("id") int id);
	void deleteChoiceQuestionById(int id);
	void updateChoiceQuestion(ChoiceQuestion cq);
	
	 List<QuestionInfo> getExamStaticByTestTypeId(int id);
	void insertChoiceQuestion(ChoiceQuestion cq);
	void insertCaseQuestion(CaseQuestion cq);
	void updateCaseQuestion(CaseQuestion cq);
	void deleteCaseQuestion(int id);
	List<CaseQuestion> getCaseQuestions(int suitId);
	void deleteCaseSubQuestion (int id);
	List<CaseSubQuestion> getSubCaseQuestions(int suitId);
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
	List<ColumnContent> getTestColumn(@Param("path") String path);
	
	List<ColumnContent> getFinalColumn(@Param("path") String path);
	ExamItem getTestScore(@Param("userid") String userid,@Param("cpath") String cpath);
	ExamItem getFinalScore(@Param("userid") String userid,@Param("finaltestid") String finaltestid);
	void deleteSuite(int id);
}
