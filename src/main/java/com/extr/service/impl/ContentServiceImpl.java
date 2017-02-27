package com.extr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.extr.domain.content.Appraisal;
import com.extr.domain.content.AppraisalNodes;
import com.extr.domain.content.ClassChapter;
import com.extr.domain.content.ClassManagement;
import com.extr.domain.content.ModuleManagement;
import com.extr.domain.content.ProfessionalManagement;
import com.extr.domain.content.QuestionsManagement;
import com.extr.persistence.ContentMapper;
import com.extr.service.ContentService;
import com.extr.util.Page;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentMapper contentMapper;

	@Override
	public JSONObject getProfessionalManagementList() {
		JSONObject jsonObj = new JSONObject();
		List<ProfessionalManagement> list = contentMapper.selectProfessionalManagement();
		jsonObj.put("data", list);
		return jsonObj;
	}
	
	@Override
	public int getMaxOrdByModule(String module) {
		return contentMapper.selectMaxOrdByModule(module);
	}

	@Override
	public int insertProfessionalManagement(ProfessionalManagement pm) {
		return contentMapper.insertProfessionalManagement(pm);
	}
	
	@Override
	public void updateProfessionalManagementById(ProfessionalManagement pm) {
		contentMapper.updateProfessionalManagementById(pm);
	}

	@Override
	public void deleteProfessionalManagementById(int pmid) {
		contentMapper.deleteProfessionalManagementById(pmid);
	}

	@Override
	public List<ClassManagement> getClassManagementList(ClassManagement cm) {
		return contentMapper.selectClassManagementList(cm);
	}

	@Override
	public int insertClassManagement(ClassManagement cm) {
		return contentMapper.insertClassManagement(cm);
	}

	@Override
	public void deleteClassManagementById(int id) {
		contentMapper.deleteClassManagementById(id);
		
	}

	@Override
	public List<ClassChapter> getClassChapterList(int iid, String cate) {
		return contentMapper.selectClassChapterList(iid, cate);
	}

	@Override
	public void deleteClassChapterById(int id) {
		contentMapper.deleteClassChapterById(id);
	}

	@Override
	public List<QuestionsManagement> getQuestionsManagementList(int cid, String title, String cpath, String cate, String type, Page<QuestionsManagement> page) {
		return contentMapper.selectQuestionsManagementList(cid, title, cpath, cate, type, page);
	}
	
	@Override
	public int insertQuestionsManagement(QuestionsManagement qm) {
		return contentMapper.insertQuestionsManagement(qm);
	}
	
	@Override
	public void deleteQuestionsManagementById(int id) {
		contentMapper.deleteQuestionsManagementById(id);
	}

	@Override
	public List<ModuleManagement> getModuleManagementList() {
		return contentMapper.selectModuleManagementList();		
	}

	@Override
	public void updateModule(ModuleManagement mm) {
		contentMapper.updateModule(mm);
	}

	@Override
	public List<Appraisal> getAppraisalList(String no, String title, String state,String path) {
		return contentMapper.selectAppraisalList(no, title, state, path);
	}

	@Override
	public int insertAppraisal(Appraisal as) {
		return contentMapper.insertAppraisal(as);
	}
	
	@Override
	public void updateAppraisalById(Appraisal as) {
		contentMapper.updateAppraisalById(as);
	}
	
	@Override
	public void deleteAppraisalById(int id) {
		contentMapper.deleteAppraisalById(id);
	}

	@Override
	public List<AppraisalNodes> getAppraisalNodesList(int iid) {
		return contentMapper.selectAppraisalNodesList(iid);
	}
	
	@Override
	public int insertAppraisalNodes(AppraisalNodes an) {
		return contentMapper.insertAppraisalNodes(an);
	}
	
	@Override
	public int getAppraisalNodesMaxOrd() {
		return contentMapper.getAppraisalNodesMaxOrd();
	}
	
	@Override
	public void updateAppraisalNodesById(AppraisalNodes an) {
		contentMapper.updateAppraisalNodesById(an);
	}
	
	@Override
	public void deleteAppraisalNodesById(int id) {
		contentMapper.deleteAppraisalNodesById(id);
	}

	@Override
	public List<ClassChapter> getTryClassList() {
		return contentMapper.getTryClassList();
	}

	@Override
	public int getQuestionsCounts(int cid, String title, String cpath, String cate, String type) {
		return contentMapper.getQuestionsCounts(cid,title,cpath,cate,type);
	}

	@Override
	public void insertClasschapter(ClassChapter cc) {
		contentMapper.insertClasschapter(cc);
		
	}

	@Override
	public void updateClasschapter(ClassChapter cc) {
		contentMapper.updateClasschapter(cc);
	}

	@Override
	public void updateQuestionsManagement(QuestionsManagement qm) {
		contentMapper.updateQuestionsManagement(qm);
	}

	@Override
	public void updateClassManagement(ClassManagement cm) {
		contentMapper.updateClassManagement(cm);
		
	}

	

	

	

	

	


	

}
