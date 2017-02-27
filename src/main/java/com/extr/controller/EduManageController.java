package com.extr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.extr.domain.ResponseJson;
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
import com.extr.domain.exam.GraduationExam;
import com.extr.domain.exam.QuestionInfo;
import com.extr.domain.student.StudentApply;
import com.extr.service.EduManageService;
import com.extr.service.ExamService;
import com.extr.domain.exam.Answer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class EduManageController {
	
	/*
	 * 远程教育平台接口
	 * 
	 * 获取主菜单模块 web_user_channel
	 * 
	 */
	@Autowired
	private ExamService examService;
	@Autowired
	private EduManageService eduManageService;
	
	
	//远程教育平台：主菜单列表
	@RequestMapping(value = "/edu/getMainMenuList", method = RequestMethod.POST)
	public void getMainMenuList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			
			List<EduMainMenu> list = eduManageService.getMainMenuList();
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
	
	
	
	//远程教育平台：作业与考试列表   编号 课程名称  章节测试   单科考试
	@RequestMapping(value = "/edu/getWorkAndExamList", method = RequestMethod.POST)
	public void getWorkAndExamList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = -1;	//用户id
			userId = jsonObj.getInt("userId");
			String path = "";
			path = jsonObj.getString("path");
			int cate = jsonObj.getInt("cate");
			List<WorkAndExam> list = eduManageService.getWorkAndExamList(userId,path,cate);
			
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
	
	//远程教育平台：记录课程学习进程信息
		@RequestMapping(value = "/edu/insertWebUserCourse", method = RequestMethod.POST)
		public void insertWebUserCourse(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "parameter", required = true) String parameter) {
			
		}
	
	//远程教育平台：作业与考试：章节测试列表查询
	@RequestMapping(value = "/edu/getChapterExamList", method = RequestMethod.POST)
	public void getChapterExamList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = -1;	//用户id
			int cate = -1;		//章节类型     1课程；2习题测试；3无；4单科考试    
			int cid = -1;		//课程id
			if (jsonObj.has("userId")) {
				userId = jsonObj.getInt("userId");
			}
			
			cid = jsonObj.getInt("cid");
			cate = jsonObj.getInt("cate");
			
			List<ChapterExam> list = eduManageService.getChapterExamList(userId, cate, cid);
			
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
	
	
	
	@RequestMapping(value = "/edu/getChapterExamResult", method = RequestMethod.POST)
	public void getChapterExamResult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = -1;	//用户id
			int cate = -1;		//章节类型     1课程；2习题测试；3无；4单科考试    
			String cids = null;		//课程id
			if (jsonObj.has("userId")) {
				userId = jsonObj.getInt("userId");
			}
			
			cids = jsonObj.getString("cids");
			cate = jsonObj.getInt("cate");
			List<String> cidlist = Arrays.asList(cids.split(","));
			List<ChapterExam> list = eduManageService.getChapterExamResult(userId, cate, cidlist);
			
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
	//远程教育平台：作业与考试：章节测试：分节测试【开始新的答题】
	@RequestMapping(value = "/edu/getDiffChapterTestList", method = RequestMethod.POST)
	public void getDiffChapterTestList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			
			//获取测试试卷
			int userId = -1;	//用户id
			int cid = -1;		//课程id
			int cate = -1;		//章节类型	2习题联系；  3单科考试
			String cpath = null;
			Boolean isdk =false;
			userId = jsonObj.getInt("userId");
			cid = jsonObj.getInt("cid");
			cate = jsonObj.getInt("cate");
			isdk = jsonObj.getBoolean("isdk");
			if (jsonObj.has("cpath")) {
				cpath = jsonObj.getString("cpath");
			}

			//创建分解测试    从web_exam抽题
			JSONObject retJson = eduManageService.createDiffChapterTest(userId, cid, cate, cpath,isdk);
			
			ResponseJson rj = new ResponseJson(retJson, 0, "success");
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
	//远程教育平台：作业与考试：章节测试：分节测试【开始新的答题】
	@RequestMapping(value = "/edu/getChapterTestListById", method = RequestMethod.POST)
	public void getChapterTestListById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		JSONObject jsonObj = JSONObject.fromObject(parameter);		
		//获取测试试卷
		String no = null;
		if (jsonObj.has("no")) {
			no = jsonObj.getString("no");
		}
		
		List<DiffChapterTest> list = eduManageService.getChapterTestListById(no);
		
		ResponseJson rj = new ResponseJson(list, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	//作业与考试：章节测试||单科考试：交卷
	@RequestMapping(value = "/edu/submitDiffChapterTest", method = RequestMethod.POST)
	public void submitDiffChapterTest(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = -1;	//用户id
			String no = null;	//考卷编号
			double score = -1;	//分数
			userId = jsonObj.getInt("userId");
			no = jsonObj.getString("no");
			score = jsonObj.getDouble("score");
			
			ExamUserExam eue = new ExamUserExam();
			eue.setUid(String.valueOf(userId));
			eue.setNo(no);
			eue.setScore(score);
			eduManageService.submitDiffChapterTest(eue);
			
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
	//根据结果更新web_user_exam_detail中各题的答案
	@RequestMapping(value = "/edu/updateExamResult", method = RequestMethod.POST)
	public void updateExamResult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		JSONArray answers;
		answers = jsonObj.getJSONArray("answers");
		
		List<Answer> list = (List<Answer>)JSONArray.toList(answers, Answer.class);  
		for(int i =0;i<list.size();i++){
			eduManageService.updateExamAnswer(list.get(i));
		}
		
		ResponseJson rj = new ResponseJson(null, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//模拟考试&结业考试：查询 模拟考试  考试类型
	@RequestMapping(value = "/edu/getMockExamTypeList", method = RequestMethod.POST)
	public void getMockExamTypeList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = -1;	//用户id
			userId = jsonObj.getInt("userId");
			
			List<TestTypes> list = eduManageService.getMockExamTypeList(userId);
			
			   for(int i = 0; i < list.size(); i++)  
		        {  
				   TestTypes obj =   (TestTypes)list.get(i);  
		          
		          List<QuestionInfo> list1 = examService.getExamStaticByTestTypeId(obj.getId());
		          String $desc = "";
		          for(int j = 0; j < list1.size(); j++)
		          {
		              
		              if(list1.get(j).getType_no() ==1){
		            	  $desc+="1-"+list1.get(j).getCount_no()+"-"+list1.get(j).getScore()+"-"+list1.get(j).getRatio()+"|";
		              }
		                 
		              if(list1.get(j).getType_no() ==2){
		            	  $desc+="2-"+list1.get(j).getCount_no()+"-"+list1.get(j).getScore()+"-"+list1.get(j).getRatio()+"|";
		              }
		              
		              if(list1.get(j).getType_no() ==3){
		            	  $desc+="3-"+list1.get(j).getCount_no()+"-"+list1.get(j).getScore()+"-"+list1.get(j).getRatio()+"|";
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
	
	//查询是否考试及格
	@RequestMapping(value = "/edu/getMockExamResult", method = RequestMethod.POST)
	public void getMockExamResult(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try{
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		String userId = "";	//用户id
		userId = jsonObj.getString("userId");
		String special = jsonObj.getString("special");
		
			List<Score> list = eduManageService.getScores(userId, special);
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
	
	
	//模拟考试&结业考试：获取模拟考试试题
	@RequestMapping(value = "/edu/getMockOrRealExamList", method = RequestMethod.POST)
	public void getMockOrRealExamList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			
			//获取测试试卷
			int testTypeId = -1;	//考试类型	
			int enabled = -1;		//是否有效	0否    1是
			int is_mock = -1;		//是否为模拟考试     0否    1是
			int is_real = -1;		//是否为结业考试	 0否   1是
			testTypeId = jsonObj.getInt("testTypeId");
			enabled = jsonObj.getInt("enabled");
			if (jsonObj.has("is_mock")) {
				is_mock = jsonObj.getInt("is_mock");
			}
			if (jsonObj.has("is_real")) {
				is_real = jsonObj.getInt("is_real");
			}
				
			//查询模拟考试试卷
			List<ExamSuites> suitesList = eduManageService.getExamSuites(testTypeId, enabled, is_real, is_mock);
			int suitesId = -1;
			if (suitesList.size() > 1) {
				Random random = new Random();
				 int radInt = random.nextInt(suitesList.size());
				 suitesId = suitesList.get(radInt).getId();
			} else {
				suitesId = suitesList.get(0).getId();
			}
			
			JSONObject retJson = eduManageService.getMockOrRealExamList(suitesId);
			
			ResponseJson rj = new ResponseJson(retJson, 0, "success");
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
	
	
	//模拟考试&结业考试：交卷
	@RequestMapping(value = "/edu/submitMockOrRealExam", method = RequestMethod.POST)
	public void submitMockOrRealExam(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			
			//获取测试试卷
			int userId = -1;		//用户id
			int testTypeId = -1;	//考试类型	
			int testSuitesId = -1;	//考卷类型
			int finished = -1;		//是否完成    0否    1是
			String startTime = null;//开始时间
			String endTime = null;	//结束时间
			double score = 0;		//分数
			int questionCount = -1; //答题数累计
			int mock = -1; 			//是否为模拟考试    0否   1是
			String submitTime = null; //交卷时间
			String answer = null;	//当前用户填写的答案
			
			userId = jsonObj.getInt("userId");
			testTypeId = jsonObj.getInt("testTypeId");
			testSuitesId = jsonObj.getInt("testSuitesId");
			finished = jsonObj.getInt("finished");
			startTime = jsonObj.getString("startTime");
			endTime = jsonObj.getString("endTime");
			score = jsonObj.getDouble("score");
			questionCount = jsonObj.getInt("questionCount");
			mock = jsonObj.getInt("mock");
			submitTime = jsonObj.getString("submitTime");
			answer = jsonObj.getString("answer");
			
			//保存个人交卷记录
			FinalTests ft = new FinalTests();
			ft.setUser_id(userId);
			ft.setTest_type_id(testTypeId);
			ft.setTest_suite_id(testSuitesId);
			ft.setFinished(finished);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date st = sdf.parse(startTime);
			ft.setStart_time(st);
			Date et = sdf.parse(endTime);
			ft.setEnd_time(et);
			ft.setScore(score);
			ft.setQuestion_count(questionCount);
			ft.setMock_test(mock);
			eduManageService.insertFinalTests(ft);
			
			//保存个人交卷时间
			FinalAnswerSheets fas = new FinalAnswerSheets();
			fas.setFinal_test_id(ft.getId());
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date sbt = sdf.parse(submitTime);
			fas.setSubmit_time(sbt);
			eduManageService.insertFinalAnswerSheets(fas);
			
			//保存个人交卷答案
			JSONObject answerJson = JSONObject.fromObject(answer);
			List<FinalAnswers> afList = new ArrayList<FinalAnswers>();
			for (Iterator iter = answerJson.keys(); iter.hasNext();) {
				FinalAnswers af = new FinalAnswers();
				af.setFinal_answer_sheet_id(fas.getId());
				Integer key = Integer.parseInt((String) iter.next());
				String keyStr = key.toString();
				af.setIdx(key);
				af.setQuestion_id(key);
				String value = answerJson.getString(keyStr);
				af.setAnswer_text(value);
				afList.add(af);
			}
			
			eduManageService.insertFinalAnswers(afList);
			
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
	
	
	//学业申请：个人申请记录查询
	@RequestMapping(value = "/edu/getMyApplyList", method = RequestMethod.POST)
	public void getMyApplyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = jsonObj.getInt("userId");
			
			List<StudentApply> list = eduManageService.getMyApplyList(userId);
			
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
	
	
	//学业申请：提交申请
	@RequestMapping(value = "/edu/submitApply", method = RequestMethod.POST)
	public void submitApply(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = jsonObj.getInt("userId");
			String applystate = jsonObj.getString("applystate");	//申请状态      x休学；y延学；h恢复
			String startdateStr = jsonObj.getString("startdate");	//开始时间
			String enddateStr = jsonObj.getString("enddate");	//结束时间
			StudentApply sa = new StudentApply();
			//获取开始时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date st = sdf.parse(startdateStr);
			int startdate = Integer.parseInt((st.getTime() + "").substring(0, 10));
			//获取结束时间
			if(!enddateStr.equalsIgnoreCase( " 00:00:00")){
				Date ed = sdf.parse(enddateStr);
				int enddate = Integer.parseInt((ed.getTime() + "").substring(0, 10));
				sa.setEnddate(enddate);
			}else{
				sa.setEnddate(startdate);
			}
			
			//获取申请时间
			//获取 add_date   int类型
			long time = new java.util.Date().getTime();
			String applydatestr = time + "";
			int applydate = Integer.parseInt(applydatestr.substring(0, 10));
			
			
			sa.setApplystate(applystate);
			sa.setStartdate(startdate);
			
			sa.setApplydate(applydate);
			sa.setApprove(0);
			sa.setUserid(userId);
			eduManageService.submitApply(sa);
			
			ResponseJson rj = new ResponseJson(sa.getId(), 0, "success");
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
	
	//查询结业考试分数
		@RequestMapping(value = "/edu/getFinalScore", method = RequestMethod.POST)
		public void getFinalScore(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "parameter", required = true) String parameter) {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = jsonObj.getInt("userId");
			
			List<FinalTest> list = eduManageService.getFinalScore(String.valueOf(userId));
							
			ResponseJson rj = new ResponseJson(list, 0, "success");
			PrintWriter printer;
			try {
				printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	
	
	
	

}
