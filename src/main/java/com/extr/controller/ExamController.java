package com.extr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.extr.domain.ResponseJson;
import com.extr.domain.edu.EssayQuestions;
import com.extr.domain.edu.ExamUserExam;
import com.extr.domain.exam.CaseQuestion;
import com.extr.domain.exam.CaseSubQuestion;
import com.extr.domain.exam.ChapterTest;
import com.extr.domain.exam.ChoiceQuestion;
import com.extr.domain.exam.EssayContent;
import com.extr.domain.exam.EssayQuestion;
import com.extr.domain.exam.ExamManagement;
import com.extr.domain.exam.GraduationExam;
import com.extr.domain.exam.QuestionInfo;
import com.extr.domain.exam.TestType;
import com.extr.domain.user.User;

import com.extr.persistence.UserMapper;
import com.extr.service.ExamService;
import com.extr.service.UserService;
import com.extr.util.Page;
import com.extr.util.ResultTransfer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ExamController {
	
	/*
	 * 章节测试
	 * 结业考试
	 */
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	public UserMapper userMapper;
	//章节测试
	@RequestMapping(value = "/exam/getChapterTestList", method = RequestMethod.POST)
	public void getChapterTestList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			int pageNo = 0;
			int pageSize =50;
			int cate = 2; //章节类型；  2：测试
			String username = null;	//用户名
			String course = null;//课程
			String chapter = null;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("index")) {
					pageNo = jsonObj.getInt("index");
				}
				if (jsonObj.has("username")) {
					username = jsonObj.getString("username");
					username = username.trim();
				}	
				if (jsonObj.has("course")) {
					course = jsonObj.getString("course");
					course = course.trim();
				}
				if (jsonObj.has("chapter")) {
					chapter = jsonObj.getString("chapter");
					chapter = chapter.trim();
				}
				if (jsonObj.has("pageSize")) {
					pageSize = jsonObj.getInt("pageSize");
				}
			}
			
			// 分页
			Page<ChapterTest> page = new Page<ChapterTest>();
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			int total = examService.getChapterTestListCount(cate, username,course,chapter);
			page.setTotalPage(total);
			List<ChapterTest> list = examService.getChapterTestList(cate, page, username,course,chapter);
			ResponseJson rj = new ResponseJson(list, 0, "success",total);
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//结业考试列表  exam_types
	@RequestMapping(value = "/exam/getGraduationExamList", method = RequestMethod.POST)
	public void getGraduationExamList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			
			List<Serializable> list = examService.getGraduationExamList();
			
			
		   for(int i = 0; i < list.size(); i++)  
	        {  
			   GraduationExam obj =   (GraduationExam)list.get(i);  
	          
	          List<QuestionInfo> list1 = examService.getExamStaticByTestTypeId(obj.getId());
	          String $desc = "";
	          for(int j = 0; j < list1.size(); j++)
	          {
	              
	              if(list1.get(j).getType_no() ==1){
	            	  $desc+="选择题"+list1.get(j).getCount_no()+"题,共"+list1.get(j).getScore()+"分;";
	              }
	                 
	              if(list1.get(j).getType_no() ==2){
	            	  $desc+="选案例分析题"+list1.get(j).getCount_no()+"题,共"+list1.get(j).getScore()+"分;";
	              }
	              
	              if(list1.get(j).getType_no() ==3){
	            	  $desc+="问答题"+list1.get(j).getCount_no()+"题,共"+list1.get(j).getScore()+"分;";
	              }

	          }
	          
	          obj.setDetail($desc);
	          
	        } 
			
			
			ResponseJson rj = new ResponseJson(list, 0, "success");
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//修改结业考试列表有效值
	@RequestMapping(value = "/exam/updateGraduationExamEnabledById", method = RequestMethod.POST)
	public void updateGraduationExamEnabledById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			int id = -1;
			int enabled = -1;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("id")) {
					id = jsonObj.getInt("id");
				}
				if (jsonObj.has("enabled")) {
					enabled = jsonObj.getInt("enabled");
				}
			}
			
			examService.updateGraduationExamEnabledById(id, enabled);
			ResponseJson rj = new ResponseJson(null, 0, "success");
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//管理试卷列表查询
	@RequestMapping(value = "/exam/getExamManagementList", method = RequestMethod.POST)
	public void getExamManagementList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			int examTypesId = -1;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("examTypesId")) {
					examTypesId = jsonObj.getInt("examTypesId");
				}
			}
			
			List<ExamManagement> list = examService.getExamManagementList(examTypesId);
			ResponseJson rj = new ResponseJson(list, 0, "success");
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//试卷维护：修改管理试卷记录：是否有效、是否设为结业考试、是否设为模拟考试
	@RequestMapping(value = "/exam/updateExamManagementById", method = RequestMethod.POST)
	public void updateExamManagementById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			int id = -1;
			int enabled = -1;
			int is_real = -1;
			int is_mock = -1;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("id")) {
					id = jsonObj.getInt("id");
				}
				if (jsonObj.has("enabled")) {
					enabled = jsonObj.getInt("enabled");
				}
				if (jsonObj.has("is_real")) {
					is_real = jsonObj.getInt("is_real");
				}
				if (jsonObj.has("is_mock")) {
					is_mock = jsonObj.getInt("is_mock");
				}
			}
			
			examService.updateExamManagementById(id, enabled, is_real, is_mock);
			ResponseJson rj = new ResponseJson(null, 0, "success");
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//添加试卷
	@RequestMapping(value = "/exam/createExamByTestTypeId", method = RequestMethod.POST)
	public void createExam(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			String name = "";
			int test_type_id = -1;
			int enabled = 0;
			int is_real = 0;
			int is_mock = 0;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("name")) {
					name = jsonObj.getString("name");
				}
				if (jsonObj.has("test_type_id")) {
					test_type_id = jsonObj.getInt("test_type_id");
				} else {
					ResponseJson rj = new ResponseJson(null, -1, "没有选择所属的试卷类型");
					PrintWriter printer = response.getWriter();
					printer.write(rj.getResponseStr());
				}
				if (jsonObj.has("enabled")) {
					enabled = jsonObj.getInt("enabled");
				}
				if (jsonObj.has("is_real")) {
					is_real = jsonObj.getInt("is_real");
				}
				if (jsonObj.has("is_mock")) {
					is_mock = jsonObj.getInt("is_mock");
				}
			}
			ExamManagement em = new ExamManagement();
			em.setName(name);
			em.setTest_type_id(test_type_id);
			em.setEnabled(enabled);
			em.setIs_real(is_real);
			em.setIs_mock(is_mock);
			
			examService.createExamByTestTypeId(em);
			ResponseJson rj = new ResponseJson(em.getId(), 0, "success");
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//删除试卷
	@RequestMapping(value = "/exam/deleteSuiteById", method = RequestMethod.POST)
	public void deleteSuiteById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		int id = -1;
		if (parameter != null) {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			if (jsonObj.has("id")) {
				id = jsonObj.getInt("id");
			}		
		}
		
		examService.deteleSuiteById(id);
		ResponseJson rj = new ResponseJson(id, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//添加考试类型
		@RequestMapping(value = "/exam/createTestType", method = RequestMethod.POST)
	public void createTestType(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
			int choice_number = 0;
			int case_number = 0;
			int essay_number = 0;
			int duration = 0;
			int major_id = 0;
			double choice_ratio = 0;
			double case_ratio = 0;
			double essay_ratio = 0;
			String name = null;
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			
			TestType ty = new TestType();
			if (jsonObj.has("duration")) {
				duration = jsonObj.getInt("duration");	
				ty.setDuration(duration);
			}
			if (jsonObj.has("name")) {
				name = jsonObj.getString("name");
				ty.setName(name);
			}
			if (jsonObj.has("major_id")) {
				major_id = jsonObj.getInt("major_id");	
				ty.setMajor_id(major_id);
			}
			if (jsonObj.has("choice_number")) {
				choice_number = jsonObj.getInt("choice_number");				
			}
			if (jsonObj.has("case_number")) {
				case_number = jsonObj.getInt("case_number");				
			}
			if (jsonObj.has("essay_number")) {
				essay_number = jsonObj.getInt("essay_number");				
			}
			if (jsonObj.has("choice_ratio")) {
				choice_ratio = jsonObj.getDouble("choice_ratio");				
			}	
			if (jsonObj.has("case_ratio")) {
				case_ratio = jsonObj.getDouble("case_ratio");				
			}
			if (jsonObj.has("essay_ratio")) {
				essay_ratio = jsonObj.getDouble("essay_ratio");				
			}
			int id = examService.createTestTypes(ty);
			int index = 0;
			//choice_number
			QuestionInfo questionInfo = new  QuestionInfo();
			questionInfo.setScore((int)(choice_ratio+case_ratio+essay_ratio));
			questionInfo.setTest_type_id(ty.getId());
			if (jsonObj.has("choice_number")) {
				choice_number = jsonObj.getInt("choice_number");
				
				questionInfo.setIdx(index);
				questionInfo.setType_no(1);
				questionInfo.setCount_no(choice_number);
				questionInfo.setRatio(1);
				examService.createQuestionInfo(questionInfo);
				index++;
			}
			
			if (jsonObj.has("case_number")) {
				case_number = jsonObj.getInt("case_number");
				
				questionInfo.setIdx(index);
				questionInfo.setType_no(2);
				questionInfo.setCount_no(case_number);
				questionInfo.setRatio(1);
				examService.createQuestionInfo(questionInfo);
				index++;
			}
			
			if (jsonObj.has("essay_number")) {
				essay_number = jsonObj.getInt("essay_number");
				
				questionInfo.setIdx(index);
				questionInfo.setType_no(3);
				questionInfo.setCount_no(essay_number);
				questionInfo.setRatio(1);
				examService.createQuestionInfo(questionInfo);
				index++;
			}
		
			ResponseJson rj = new ResponseJson(id, 0, "success");
			PrintWriter printer;
			try {
				printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
		
		//添加考试试题
	@RequestMapping(value = "/exam/addChoiceQuestion", method = RequestMethod.POST)
	public void addChoiceQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){		
		
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int suitId = jsonObj.getInt("id");
		
	}
	@RequestMapping(value = "/exam/getChoiceQuestionsBySuitId", method = RequestMethod.POST)
	public void getChoiceQuestions(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int suitId = jsonObj.getInt("id");
		List<ChoiceQuestion> cqlist = examService.getChoiceQuestions(suitId);
		ResponseJson rj = new ResponseJson(cqlist, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@RequestMapping(value = "/exam/deleteChoiceQuestionsById", method = RequestMethod.POST)
	public void deleteChoiceQuestionsById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int suitId = jsonObj.getInt("id");
		examService.deleteChoiceQuestionById(suitId);
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	@RequestMapping(value = "/exam/updateChoiceQuestion", method = RequestMethod.POST)
	public void updateChoiceQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
	
	
		ChoiceQuestion cq = (ChoiceQuestion) JSONObject.toBean(jsonObj,ChoiceQuestion.class);  
		
		
		examService.updateChoiceQuestion(cq);
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	

	
	
	@RequestMapping(value = "/exam/getExamStaticByTestTypeId", method = RequestMethod.POST)
	public void getExamStaticByTestTypeId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		
		int id = jsonObj.getInt("examTypesId");
		
		List<QuestionInfo> list = examService.getExamStaticByTestTypeId(id);
		ResponseJson rj = new ResponseJson(list, 0, "success");
		try {
		PrintWriter printer;	
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/exam/InsertChoiceQuestion", method = RequestMethod.POST)
	public void insertChoiceQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		ChoiceQuestion cq = (ChoiceQuestion) JSONObject.toBean(jsonObj,ChoiceQuestion.class);  
		examService.insertChoiceQuestion(cq);
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	
	@RequestMapping(value = "/exam/getCaseQuestionsBySuitId", method = RequestMethod.POST)
	public void getCaseQuestions(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int suitId = jsonObj.getInt("id");
		List<CaseQuestion> cqlist = examService.getCaseQuestions(suitId);
		ResponseJson rj = new ResponseJson(cqlist, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value = "/exam/insertCaseQuestion", method = RequestMethod.POST)
	public void insertCaseQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		CaseQuestion cq = (CaseQuestion) JSONObject.toBean(jsonObj,CaseQuestion.class);  
		examService.insertCaseQuestion(cq);
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	

	@RequestMapping(value = "/exam/updateCaseQuestion", method = RequestMethod.POST)
	public void updateCaseQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		CaseQuestion cq = (CaseQuestion) JSONObject.toBean(jsonObj,CaseQuestion.class);  
		examService.updateCaseQuestion(cq);
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
	@RequestMapping(value = "/exam/deleteCaseQuestion", method = RequestMethod.POST)
	public void deleteCaseQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int id = jsonObj.getInt("id"); 
		examService.deleteCaseQuestion(id);
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value = "/exam/getSubCaseQuestions", method = RequestMethod.POST)
	public void getSubCaseQuestions(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int suitId = jsonObj.getInt("id");
		List<CaseSubQuestion> cqlist = examService.getCaseSubQuestions(suitId);
		ResponseJson rj = new ResponseJson(cqlist, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(value = "/exam/insertSubChoiceQuestion", method = RequestMethod.POST)
	public void insertSubChoiceQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		CaseSubQuestion cq = (CaseSubQuestion) JSONObject.toBean(jsonObj,CaseSubQuestion.class); 
		
		examService.insertSubChoiceQuestion(cq);
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		
	}
	
	@RequestMapping(value = "/exam/updateSubCaseQuestion", method = RequestMethod.POST)
	public void updateSubCaseQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		CaseSubQuestion cq = (CaseSubQuestion) JSONObject.toBean(jsonObj,CaseSubQuestion.class); 
		
		examService.updateSubCaseQuestion(cq);
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@RequestMapping(value = "/exam/deleteSubCaseQuestionByidxAndCaseQuestionId", method = RequestMethod.POST)
	public void deleteSubCaseQuestionByidxAndCaseQuestionId(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		CaseSubQuestion cq = (CaseSubQuestion) JSONObject.toBean(jsonObj,CaseSubQuestion.class); 
		
		examService.deleteSubCaseQuestionByidxAndCaseQuestionId(cq);
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	
	@RequestMapping(value = "/exam/getEssayQuestionBySuiteid", method = RequestMethod.POST)
	public void getEssayQuestionBySuiteid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int id = jsonObj.getInt("id");
		
		List<EssayQuestions> eq = examService.getEssayQuestionBySuiteid(id);
		
		ResponseJson rj = new ResponseJson(eq, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@RequestMapping(value = "/exam/getEssayContentBySuiteid", method = RequestMethod.POST)
	public void getEssayContentBySuiteid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){

			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			
			EssayContent eq = examService.getEssayContentBySuiteid(id);
			
			ResponseJson rj = new ResponseJson(eq, 0, "success");
			PrintWriter printer;
			try {
				printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
	
	@RequestMapping(value = "/exam/insertEssayContent", method = RequestMethod.POST)
	public void insertEssayContent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){

			JSONObject jsonObj = JSONObject.fromObject(parameter);
			EssayContent cq = (EssayContent) JSONObject.toBean(jsonObj,EssayContent.class); 
			
			examService.insertEssayContent(cq);
			
			ResponseJson rj = new ResponseJson(null, 0, "success");
			PrintWriter printer;
			try {
				printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
	
	
	@RequestMapping(value = "/exam/updateEssayContent", method = RequestMethod.POST)
	public void updateEssayContent(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){

			JSONObject jsonObj = JSONObject.fromObject(parameter);
			EssayContent cq = (EssayContent) JSONObject.toBean(jsonObj,EssayContent.class); 
			
			examService.updateEssayContent(cq);
			
			ResponseJson rj = new ResponseJson(null, 0, "success");
			PrintWriter printer;
			try {
				printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
	
	
	
	
	@RequestMapping(value = "/exam/updateEssayQuestionByid", method = RequestMethod.POST)
	public void updateEssayQuestionByid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		EssayQuestion cq = (EssayQuestion) JSONObject.toBean(jsonObj,EssayQuestion.class); 
		
		examService.updateEssayQuestionByid(cq);
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	
	@RequestMapping(value = "/exam/deleteEssayQuestionByid", method = RequestMethod.POST)
	public void deleteEssayQuestionByid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int id = jsonObj.getInt("id");
		examService.deleteEssayQuestionByid(id);
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	@RequestMapping(value = "/exam/insertEssayQuestion", method = RequestMethod.POST)
	public void insertEssayQuestion(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		
		EssayQuestion cq = (EssayQuestion) JSONObject.toBean(jsonObj,EssayQuestion.class); 
		
		examService.insertEssayQuestion(cq);
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	@RequestMapping(value = "/exam/insertChapterRocord", method = RequestMethod.POST)
	public void insertChapterRocord(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		ExamUserExam eue = (ExamUserExam) JSONObject.toBean(jsonObj,ExamUserExam.class); 
		
		boolean isdanke = jsonObj.getBoolean("isDanke");
		
		//创建试卷表
		//ExamUserExam eue = new ExamUserExam();
		eue.setFlag("y");
		eue.setCate("cs");
//		eue.setUid(userId);
//		eue.setCid(cid);
//		eue.setCpath(cpath);
		//生成考卷编号名称
		
		SimpleDateFormat matter1=new SimpleDateFormat("yyyyMMddHHmmss");
		String no = "cs" + matter1.format(new Date()) + eue.getUid();
		if(isdanke == true){
			no = "dk_"+no;
		}
		eue.setNo(no);
		//eue.setTime_limit(time_limit);
		eue.setTime_used(0);
		eue.setScore(eue.getScore());
		long time = new java.util.Date().getTime();
		String createTimeStr = time + "";
		int add_date = (int) (System.currentTimeMillis()/1000);
		eue.setAdd_date(add_date);
		eue.setTime_start(0);
		
		User user = userMapper.getUserByName(String.valueOf(eue.getUid()));
		ResponseJson rj = null;
		if(user == null ||user.getId() ==0 ){
			rj = new ResponseJson("请填写正确的用户名", -1, "success");
		}else{
			eue.setUid(String.valueOf(user.getId()));
			examService.insertChapterRocord(eue);
			rj = new ResponseJson(null, 0, "success");
		}
		
		
		
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
