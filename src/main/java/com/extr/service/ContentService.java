package com.extr.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.extr.domain.content.Appraisal;
import com.extr.domain.content.AppraisalNodes;
import com.extr.domain.content.ClassChapter;
import com.extr.domain.content.ClassManagement;
import com.extr.domain.content.ModuleManagement;
import com.extr.domain.content.ProfessionalManagement;
import com.extr.domain.content.QuestionsManagement;
import com.extr.util.Page;

public interface ContentService {
	
	//获取专业管理表查询
	JSONObject getProfessionalManagementList();
	
	//查询专业管理列表 相同  module 的 Ord 最大值
	int getMaxOrdByModule(String module);
	
	//新增专业管理
	int insertProfessionalManagement(ProfessionalManagement pm);
	
	//修改专业管理
	void updateProfessionalManagementById(ProfessionalManagement pm);
	
	//删除专业管理
	void deleteProfessionalManagementById(int pmid);
	
	
	//  课程管理      //////////////////////////////////////////////////
	
	//课程管理查询列表
	List<ClassManagement> getClassManagementList(ClassManagement cm);
	//新增课程管理
	int insertClassManagement(ClassManagement cm);
	void updateClassManagement(ClassManagement cm);
	//删除课程管理
	void deleteClassManagementById(int id);
	//章节设置列表
	List<ClassChapter> getClassChapterList(int iid, String cate);
	
	void insertClasschapter(ClassChapter cc);
	//删除章节记录
	void deleteClassChapterById(int id);
	
	//题库管理列表
	List<QuestionsManagement> getQuestionsManagementList(int cid, String title, String cpath, String cate, String type, Page<QuestionsManagement> page);
	//分页查询总数
	int getQuestionsCounts(int cid, String title, String cpath, String cate, String type);
	//新增题库管理表记录
	int insertQuestionsManagement(QuestionsManagement qm);
	//删除题库管理表记录
	void deleteQuestionsManagementById(int id);
	
	//鉴定辅助列表
	List<Appraisal> getAppraisalList(String no, String title, String state, String path);
	//新增鉴定辅助记录
	int insertAppraisal(Appraisal as);
	//修改鉴定辅助记录
	void updateAppraisalById(Appraisal as);
	//删除鉴定辅助记录
	void deleteAppraisalById(int id);
	//鉴定辅导：节点设置列表查询
	List<AppraisalNodes> getAppraisalNodesList(int iid);
	//鉴定辅导：新增节点设置记录
	int insertAppraisalNodes(AppraisalNodes an);
	//节点设置表顺序号查询；取当前表里最大值
	int getAppraisalNodesMaxOrd();
	//鉴定辅导：修改节点设置记录
	void updateAppraisalNodesById(AppraisalNodes an);
	//鉴定辅导：删除节点设置记录
	void deleteAppraisalNodesById(int id);
	
	//模块管理
	List<ModuleManagement> getModuleManagementList();
	
	void updateModule(ModuleManagement mm);
	
	
	//试听课程列表查询
	List<ClassChapter> getTryClassList();

	void updateClasschapter(ClassChapter cc);

	void updateQuestionsManagement(QuestionsManagement qm);
	
}
