package com.extr.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.extr.persistence.ExamMapper;
import com.extr.service.ExamService;
import com.extr.util.Page;

@Service
public class ExamServiceImpl implements ExamService {
	
	@Autowired
	private ExamMapper examMapper;
	
	@Override
	public List<ChapterTest> getChapterTestList(int cate, Page<ChapterTest> page, String username,String course,String chapter) {
		return examMapper.selectChapterTestList(cate, page, username,course,chapter);
	}

	@Override
	public List<Serializable> getGraduationExamList() {
		return examMapper.getGraduationExamList();
	}

	@Override
	public void updateGraduationExamEnabledById(int id, int enabled) {
		examMapper.updateGraduationExamEnabledById(id, enabled);
	}

	@Override
	public List<ExamManagement> getExamManagementList(int examTypesId) {
		return examMapper.getExamManagementList(examTypesId);
	}

	@Override
	public void updateExamManagementById(int id, int enabled, int is_real, int is_mock) {
		examMapper.updateExamManagementById(id, enabled, is_real, is_mock);
	}

	@Override
	public int createExamByTestTypeId(ExamManagement em) {
		return examMapper.createExamByTestTypeId(em);
	}

	@Override
	public int getChapterTestListCount(int cate, String username,String course,String chapter) {
		
		return examMapper.getChapterTestListCount(cate,username,course,chapter);
	}

	@Override
	public int createTestTypes(TestType testType) {
		return examMapper.createTestTypes(testType);
	}

	@Override
	public void createQuestionInfo(QuestionInfo questionInfo) {
		 examMapper.createQuestionInfo(questionInfo);
		
	}

	@Override
	public List<ChoiceQuestion> getChoiceQuestions(int id) {
		return examMapper.getChoiceQuestions(id);
	}

	@Override
	public void deleteChoiceQuestionById(int id) {
		examMapper.deleteChoiceQuestionById(id);
	}

	@Override
	public void updateChoiceQuestion(ChoiceQuestion cq) {
		examMapper.updateChoiceQuestion(cq);
		
	}

	@Override
	public List<QuestionInfo> getExamStaticByTestTypeId(int id) {
		return examMapper.getExamStaticByTestTypeId(id);
		
	}

	@Override
	public void insertChoiceQuestion(ChoiceQuestion cq) {
		examMapper.insertChoiceQuestion(cq);
		
	}

	@Override
	public void insertCaseQuestion(CaseQuestion cq) {
		examMapper.insertCaseQuestion(cq);
		
	}

	@Override
	public void updateCaseQuestion(CaseQuestion cq) {
		examMapper.updateCaseQuestion(cq);
		
	}

	@Override
	public void deleteCaseQuestion(int id) {
		examMapper.deleteCaseSubQuestion(id);	
		examMapper.deleteCaseQuestion(id);
		
	}

	@Override
	public List<CaseQuestion> getCaseQuestions(int suitId) {
		
		return examMapper.getCaseQuestions(suitId);
	}

	@Override
	public List<CaseSubQuestion> getCaseSubQuestions(int suitId) {
		return examMapper.getSubCaseQuestions(suitId);
	}

	@Override
	public void updateSubCaseQuestion(CaseSubQuestion cq) {
		examMapper.updateSubCaseQuestion(cq);
		
	}

	@Override
	public void insertSubChoiceQuestion(CaseSubQuestion cq) {
		examMapper.insertSubChoiceQuestion(cq);
	}

	@Override
	public void deleteSubCaseQuestionByidxAndCaseQuestionId(CaseSubQuestion cq) {
		examMapper.deleteSubCaseQuestionByidxAndCaseQuestionId(cq);
	}

	@Override
	public List<EssayQuestions> getEssayQuestionBySuiteid(int id) {
		
		return examMapper.getEssayQuestionBySuiteid(id);
	}

	@Override
	public EssayContent getEssayContentBySuiteid(int id) {
		
		return examMapper.getEssayContentBySuiteid(id);
	}

	@Override
	public void insertEssayContent(EssayContent cq) {
		examMapper.insertEssayContent(cq);
	}

	@Override
	public void updateEssayContent(EssayContent cq) {
		examMapper.updateEssayContent(cq);
	}

	@Override
	public void updateEssayQuestionByid(EssayQuestion cq) {
		examMapper.updateEssayQuestionByid(cq);
	}

	@Override
	public void deleteEssayQuestionByid(int id) {
		examMapper.deleteEssayQuestionByid(id);
	}

	@Override
	public void insertEssayQuestion(EssayQuestion cq) {
		examMapper.insertEssayQuestion(cq);
	}

	@Override
	public void insertChapterRocord(ExamUserExam eue) {
		examMapper.insertChapterRocord(eue);
	}

	@Override
	public void deteleSuiteById(int id) {
		examMapper.deleteSuite(id);
		
	}



}
