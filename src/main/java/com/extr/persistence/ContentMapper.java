package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.content.Appraisal;
import com.extr.domain.content.AppraisalNodes;
import com.extr.domain.content.ClassChapter;
import com.extr.domain.content.ClassManagement;
import com.extr.domain.content.ModuleManagement;
import com.extr.domain.content.ProfessionalManagement;
import com.extr.domain.content.QuestionsManagement;
import com.extr.util.Page;

public interface ContentMapper {
	
	/**
	 * 获取专业管理表查询
	 * 
	 * @return List<ProfessionalManagement>
	 */
	List<ProfessionalManagement> selectProfessionalManagement();
	
	/**
	 * 查询专业管理列表 相同  module 的 Ord 最大值
	 * 
	 * @return List<ProfessionalManagement>
	 */
	int selectMaxOrdByModule(@Param("module") String module);
	
	/**
	 * 新增专业管理表查询
	 * 
	 * @return GeneratedKeys
	 */
	int insertProfessionalManagement(ProfessionalManagement pm);
	/**
	 * 修改专业管理表查询
	 * 
	 */
	int updateProfessionalManagementById(ProfessionalManagement pm);
	/**
	 * 删除专业管理表查询
	 * 
	 */
	void deleteProfessionalManagementById(@Param("id") int pmid);
	
	
	/**
	 * 课程管理查询列表
	 * @return List<ClassManagement>
	 */
	List<ClassManagement> selectClassManagementList(ClassManagement cm);
	/**
	 * 新增课程管理
	 */
	int insertClassManagement(ClassManagement cm);
	/**
	 * 删除课程管理
	 */
	void deleteClassManagementById(int id);
	/**
	 * 章节设置列表
	 */
	List<ClassChapter> selectClassChapterList(@Param("iid") int iid, @Param("cate") String cate);
	/**
	 * 删除章节记录
	 */
	void deleteClassChapterById(int id);
	/**
	 * 题库管理列表查询
	 */
	List<QuestionsManagement> selectQuestionsManagementList(
			@Param("cid") int cid, @Param("title") String title, 
			@Param("cpath") String cpath, @Param("cate") String cate,
			@Param("type") String type,@Param("Page") Page<QuestionsManagement> page);
	
	/***
	 * 题库中题目总数
	 * @return
	 */
	int getQuestionsCounts(@Param("cid") int cid, @Param("title") String title, 
			@Param("cpath") String cpath, @Param("cate") String cate,
			@Param("type") String type);
	
	/**
	 * 新增题库管理表记录
	 */
	int insertQuestionsManagement(QuestionsManagement qm);
	/**
	 * 删除题库管理表记录
	 */
	void deleteQuestionsManagementById(@Param("id") int id);
	/**
	 * 鉴定辅助列表查询
	 */
	List<Appraisal> selectAppraisalList(
			@Param("no") String no, @Param("title") String title, 
			@Param("state") String state,@Param("path") String path);
	/**
	 * 新增鉴定辅助记录
	 */
	int insertAppraisal(Appraisal as);
	/**
	 * 修改鉴定辅助记录
	 */
	void updateAppraisalById(Appraisal as);
	/**
	 * 删除鉴定辅助记录
	 */
	void deleteAppraisalById(int id);
	/**
	 * 鉴定辅助：节点设置列表查询
	 */
	List<AppraisalNodes> selectAppraisalNodesList(int iid);
	/**
	 * 鉴定辅助：新增节点设置记录
	 */
	int insertAppraisalNodes(AppraisalNodes an);
	/**
	 * 节点设置表顺序号查询；取当前表里最大值
	 */
	int getAppraisalNodesMaxOrd();
	/**
	 * 鉴定辅助：修改节点设置记录
	 */
	void updateAppraisalNodesById(AppraisalNodes an);
	/**
	 * 鉴定辅助：删除节点设置记录
	 */
	void deleteAppraisalNodesById(int id);
	
	
	
	
	
	
	//模块管理
	List<ModuleManagement> selectModuleManagementList();
	
	void updateModule(ModuleManagement mm);
	
	/**
	 * 试听课程列表查询
	 */
	List<ClassChapter> getTryClassList();

	void insertClasschapter(ClassChapter cc);

	void updateClasschapter(ClassChapter cc);

	void updateQuestionsManagement(QuestionsManagement qm);

	void updateClassManagement(ClassManagement cm);
}
