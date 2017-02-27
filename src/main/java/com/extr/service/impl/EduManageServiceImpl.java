package com.extr.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.extr.persistence.EduManageMapper;
import com.extr.service.EduManageService;

@Service
public class EduManageServiceImpl implements EduManageService {
	
	@Autowired
	private EduManageMapper eduManageMapper;
	
	@Override
	public List<EduMainMenu> getMainMenuList() {
		return eduManageMapper.getMainMenuList();
	}

	@Override
	public List<WorkAndExam> getWorkAndExamList(int userId,String path,int cate) {
		return eduManageMapper.getWorkAndExamList(userId,path,cate);
	}

	@Override
	public List<ChapterExam> getChapterExamList(int userId, int cate, int cid) {
		return eduManageMapper.getChapterExamList(userId, cate, cid);
	}

	@Override
	public List<DiffChapterTest> getDiffChapterTestList(int userId, int cid, int cate, int iid, String no) {
		return eduManageMapper.getDiffChapterTestList(userId, cid, cate, iid, no);
	}

	@Override
	public JSONObject createDiffChapterTest(int userId, int cid,
			int cate, String cpath, Boolean isdk) {
		//抽题 exam_exam 表, 查询 exam_course_chapter 表的分数
		//抽取单选、多选
		List<ExtractExam> list = null;
		if(isdk ==true){
			list = eduManageMapper.extractExamDK(Integer.parseInt(cpath.split("-")[1]));
		}else{
			list = eduManageMapper.extractExam(cid, cpath) ;
		}
		
		if(cpath==null){
			cpath = "-1";
		}
		
		Random random = new Random();
		List<ExamUserExamDetail> singleSelectList = new ArrayList<ExamUserExamDetail>();
		List<ExamUserExamDetail> multiSelectList = new ArrayList<ExamUserExamDetail>();
		JSONObject retJson = new JSONObject();
		int time_limit = 0;
		int ord = 1;
		int totalScore = 0;
		String[] ssplitArray;
		String[] msplitArray;
		List<Integer> randomArr = new ArrayList<Integer>();
		for (ExtractExam obj: list) {
			if(obj !=null){
				//总分满100分则停止
				int idx = 0;
					idx =random.nextInt(list.size());
					idx = getindex(randomArr, idx, list.size());
				obj = list.get(idx);
				//获取当前题目的分数
				String[] splitList = obj.getExam_set().split("\\|");
				ssplitArray = splitList[0].split(",");//dan
				//String[] msplitArray = splitList[1].split(",");//duo
				double sscore = Double.parseDouble(ssplitArray[2]) / Double.parseDouble(ssplitArray[1]);
				//double mscore = Double.parseDouble(msplitArray[2]) / Double.parseDouble(msplitArray[1]);
				
				if (singleSelectList.size() == Double.parseDouble(ssplitArray[1].trim())) {
					break;
				}
				//随机抽题
			
				totalScore += sscore;
				if (time_limit == 0) {
					time_limit = obj.getDuration();
				}
				ExamUserExamDetail eed = new ExamUserExamDetail();
				eed.setOrd(ord);
				eed.setSub_type(ssplitArray[0]);
				eed.setSub_title(obj.getTitle());
				eed.setSub_op1(obj.getOp1());
				eed.setSub_op2(obj.getOp2());
				eed.setSub_op3(obj.getOp3());
				eed.setSub_op4(obj.getOp4());
				eed.setSub_answer(obj.getAnswer());
				eed.setAnswer(obj.getAnswer());
				eed.setSub_score(sscore);
				eed.setSub_remark(" ");
				if (obj.getType() == 2 && eed!=null) {
					singleSelectList.add(eed);	
				}
//				else if (obj.getType() == 3) {
//					multiSelectList.add(eed);
//				}
				
				ord++;
			}

		}
		List<Integer> randomArr1 = new ArrayList<Integer>();
		//多选
		for (ExtractExam obj: list) {
			//总分满100分则停止
//			int idx = random.nextInt(list.size());
//			obj = list.get(idx);
			
			int idx = 0;
			idx =random.nextInt(list.size());
			idx = getindex(randomArr1, idx, list.size());
		obj = list.get(idx);
			
			//获取当前题目的分数
			String[] splitList = obj.getExam_set().split("\\|");
			//String[] ssplitArray = splitList[0].split(",");//dan
			msplitArray = splitList[1].split(",");//duo
			double sscore = Double.parseDouble(msplitArray[2]) / Double.parseDouble(msplitArray[1]);
			//double mscore = Double.parseDouble(msplitArray[2]) / Double.parseDouble(msplitArray[1]);
			
			if (multiSelectList.size() >= Double.parseDouble(msplitArray[1].trim())) {
				break;
			}
			//随机抽题
		
			totalScore += sscore;
			if (time_limit == 0) {
				time_limit = obj.getDuration();
			}
			ExamUserExamDetail eed = new ExamUserExamDetail();
			eed.setOrd(ord);
			eed.setSub_type(msplitArray[0]);
			eed.setSub_title(obj.getTitle());
			eed.setSub_op1(obj.getOp1());
			eed.setSub_op2(obj.getOp2());
			eed.setSub_op3(obj.getOp3());
			eed.setSub_op4(obj.getOp4());
			eed.setSub_answer(obj.getAnswer());
			eed.setAnswer(obj.getAnswer());
			eed.setSub_score(sscore);
			eed.setSub_remark(" ");
			if (obj.getType() == 3&&eed!=null) {
				multiSelectList.add(eed);	
			}
//			else if (obj.getType() == 3) {
//				multiSelectList.add(eed);
//			}
			
			ord++;
		}
		
		
		//创建试卷表
		ExamUserExam eue = new ExamUserExam();
		eue.setFlag("y");
		eue.setCate("cs");
		eue.setUid(String.valueOf(userId));
		eue.setCid(cid);
		eue.setCpath(cpath);
		//生成考卷编号名称
		SimpleDateFormat matter1=new SimpleDateFormat("yyyyMMddHHmmss");
		String no = "cs" + matter1.format(new Date()) + userId;
		eue.setNo(no);
		eue.setTime_limit(time_limit);
		eue.setTime_used(0);
		eue.setScore(0);
		long time = new java.util.Date().getTime();
		String createTimeStr = time + "";
		int add_date = Integer.parseInt(createTimeStr.substring(0, 10));
		eue.setAdd_date(add_date);
		eue.setTime_start(0);
		//新增试卷
		eduManageMapper.insertExamUserExam(eue);
		int iid = eue.getId();
		//查询刚刚新增的试卷
		ExamUserExam queryEue = eduManageMapper.selectExamUserExamById(eue.getId());
		
		List<ExamUserExamDetail> insertList = new ArrayList<ExamUserExamDetail>();
		for (ExamUserExamDetail obj: singleSelectList) {
			obj.setIid(iid);
			insertList.add(obj);
		}
		for (ExamUserExamDetail obj: multiSelectList) {
			obj.setIid(iid);
			insertList.add(obj);
		}
		//新增试题表
		eduManageMapper.insertExamUserExamDetail(insertList);
		retJson.put("exam", eue);
		retJson.put("single", singleSelectList);
		retJson.put("multi", multiSelectList);
		return retJson;
	}
	
	private int getindex(List<Integer> list, int index,int size){
		Random random = new Random();
		int result = 0;
		index = random.nextInt(size);
		if(!list.contains(index)){
			list.add(index); 
			result = index;
		}else{
			result = getindex(list, index, size);
		}
		return result;
	}
	
	@Override
	public void submitDiffChapterTest(ExamUserExam eue) {
		eduManageMapper.submitDiffChapterTest(eue);
	}
	
	@Override
	public List<TestTypes> getMockExamTypeList(int userId) {
		return eduManageMapper.getMockExamTypeList(userId);
	}

	@Override
	public List<ExamSuites> getExamSuites(int testTypeId, int enabled,int is_real, int is_mock) {
		return eduManageMapper.getExamSuites(testTypeId, enabled, is_real, is_mock);
	}
	
	@Override
	public JSONObject getMockOrRealExamList(int suitesId) {
		JSONObject retJson = new JSONObject();
		//获取选择提
		List<ChoiceQuestions> choiceList = eduManageMapper.selectChoiceQuestions(suitesId);
		//获取案例分析题
		List<CaseQuestions> caseList = eduManageMapper.selectCaseQuestions(suitesId);
		//获取案例分析题 子集
		for (CaseQuestions obj: caseList) {
			int case_question_id = obj.getId();
			List<CaseSubQuestions> caseSubQuestions = eduManageMapper.selectCaseSubQuestions(case_question_id);
			obj.setCaseSubQuestions(caseSubQuestions);
		}
		//获取问答题
		EssayContents essayContents = eduManageMapper.selectEssayContents(suitesId);
		if (null != essayContents) {
			//获取问答题 子集
			List<EssayQuestions> essayQuestions = eduManageMapper.selectEssayQuestions(suitesId);
			essayContents.setEssayQuestions(essayQuestions);
		}
		
		//装载返回值
		retJson.put("ChoiceQuestions", choiceList);
		retJson.put("CaseQuestions", caseList);
		retJson.put("EssayQuestions", essayContents);
		return retJson;
	}

	@Override
	public int insertFinalTests(FinalTests ft) {
		return eduManageMapper.insertFinalTests(ft);
	}

	@Override
	public int insertFinalAnswerSheets(FinalAnswerSheets fas) {
		return eduManageMapper.insertFinalAnswerSheets(fas);
	}

	@Override
	public void insertFinalAnswers(List<FinalAnswers> afList) {
		eduManageMapper.insertFinalAnswers(afList);
	}

	@Override
	public List<StudentApply> getMyApplyList(int userId) {
		return eduManageMapper.getMyApplyList(userId);
	}

	@Override
	public int submitApply(StudentApply sa) {
		return eduManageMapper.submitApply(sa);
	}

	@Override
	public List<ChapterExam> getChapterExamResult(int userId, int cate,
			List<String> cids) {
		return eduManageMapper.getChapterExamResult(userId, cate, cids);
	}

	@Override
	public List<DiffChapterTest> getChapterTestListById(String no) {
		// TODO Auto-generated method stub
		return eduManageMapper.getChapterTestListById(no);
	}

	@Override
	public int insertWebUserCourse(WorkAndExam workAndExam) {
		// TODO Auto-generated method stub
		return eduManageMapper.insertWebUserCourse(workAndExam);
	}

	@Override
	public List<FinalTest> getFinalScore(String userid) {
		
		return eduManageMapper.getFinalScore(userid);
	}

	@Override
	public List<Score> getScores(String userId, String special) {
		// TODO Auto-generated method stub
		return eduManageMapper.getScores(userId, special);
	}

	@Override
	public void updateExamAnswer(Answer answer) {
		eduManageMapper.updateExamAnswer(answer);
		
	}
	
	
	
	
	
	
}
