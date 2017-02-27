package com.extr.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.extr.domain.ResponseJson;
import com.extr.domain.content.QuestionsManagement;
import com.extr.domain.edu.TestTypes;
import com.extr.domain.exam.ExamItem;
import com.extr.domain.student.Duration;
import com.extr.domain.student.GraduatedQuery;
import com.extr.domain.student.StudentApply;
import com.extr.domain.student.StudentTestRecord;
import com.extr.domain.user.UCenterMember;
import com.extr.domain.user.User;
import com.extr.domain.userperiod.UserApply;
import com.extr.domain.userperiod.UserPeriod;
import com.extr.service.EduManageService;
import com.extr.service.StudentService;
import com.extr.service.UserPeriodService;
import com.extr.service.UserService;
import com.extr.util.Page;

@Controller
public class StudentController {

	/*
	 * 时间跟踪
	 * 批量报名
	 * 学员管理
	 * 学员成绩管理
	 * 申请处理
	 * 结业查询
	 * 延期管理
	 */
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserPeriodService userPeriodService;
	
	@Autowired
	private EduManageService eduManageService;
	/*
	 * 批量报名列表查询
	 * @param parameter
	 * @return response JSON
	 */
	@RequestMapping(value = "/student/getBatchSignUpList", method = RequestMethod.POST)
	public void getBatchSignUpList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			int id = -1;
			String username = "";
			String state = "";
			String name = "";
			String unit = "";
			int last_modify = -1;
			String province ="";
			String special = "";
			int pageindex = -1;
			int pagesize = -1;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("username")) {
					username = jsonObj.getString("username");
					username = username.trim();
				}
				if(jsonObj.has("name")){
					name =  jsonObj.getString("name");
					name = name.trim();
				}
				if(jsonObj.has("unit")){
					unit = jsonObj.getString("unit");
				}
				if(jsonObj.has("last_modify")){
					last_modify = jsonObj.getInt("last_modify");
				}
				if(jsonObj.has("province")){
					province = jsonObj.getString("province");
				}
				if(jsonObj.has("special")){
					special = jsonObj.getString("special");
				}
			
				if (jsonObj.has("state")) {
					state = jsonObj.getString("state");
				}
				if (jsonObj.has("id")) {
					id = jsonObj.getInt("id");
				}
				if(jsonObj.has("index")){
					pageindex = jsonObj.getInt("index"); //分页页码
				}
				if(jsonObj.has("pageSize")){
					pagesize = jsonObj.getInt("pageSize"); //分页页码
				}
			}
			
			int total = studentService.getUsersCounts(id,username,state,name,unit,last_modify,province,special);
			Page<QuestionsManagement> page = new Page<QuestionsManagement>();
			page.setPageNo(pageindex);
			page.setPageSize(pagesize);
			page.setTotalRecord(total);
			List<User> list = null;
			if(pageindex ==-1){
				list = studentService.getBatchSignUpList(id, username,state);
			}else{
				list = studentService.getBatchSignUpList(id, username,state,page,name,unit,last_modify,province,special);
			}
			
			
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
	
	/*
	 * 新增批量报名列表记录
	 * @param parameter
	 * @return response JSON
	 */
	@RequestMapping(value = "/student/insertBatchSignUp", method = RequestMethod.POST)
	public void insertBatchSignUp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			String userName = jsonObj.getString("username");
			String passWord = jsonObj.getString("password");
			String passwordQuestion = jsonObj.getString("passwordquestion");
			String passwordAnswer = jsonObj.getString("passwordanswer");
			String realName = jsonObj.getString("realname");
			String gender = jsonObj.getString("gender");
			String telephone = jsonObj.getString("telephone");
			String mobile = jsonObj.getString("mobile");
			String mail = jsonObj.getString("mail");
			String idCard = jsonObj.getString("idcard");
			Date birthday = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayStr = jsonObj.getString("birthday");
			birthday = format.parse(birthdayStr);
			
			String province = (String) jsonObj.get("province");
			String manageUninCode = (String) jsonObj.get("manageunincode");
			String postAddress = (String) jsonObj.get("postaddress");
			String postCode = (String) jsonObj.get("postcode");
			String mark = (String) jsonObj.get("mark");
			String roleStr = jsonObj.getString("role");
			String signSpecial = jsonObj.getString("signSpecial");
			int roleId = Integer.parseInt(roleStr);
			//获取 add_date   int类型
			long time = new java.util.Date().getTime();
			String add_dat_str = time + "";
			int add_date = Integer.parseInt(add_dat_str.substring(0, 10));
			
			User user = new User();
			user.setUsername(userName);
			user.setPassword(passWord);
			user.setPasswordQuestion(passwordQuestion);
			user.setPasswordAnswer(passwordAnswer);
			user.setName(realName);
			user.setGender(gender);
			user.setPhone(telephone);
			user.setCellphone(mobile);
			user.setEmail(mail);
			user.setBirthday(birthday);
			user.setProvince(province);
			user.setUnit(manageUninCode);
			user.setAddr(postAddress);
			user.setPostcode(postCode);
			user.setAdd_Date(add_date);
			user.setSignSpecial(signSpecial);
			studentService.insertBatchSignUp(user);
			ResponseJson rj = new ResponseJson(user.getId(), 0, "success");
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
	
	
	/*
	 * 删除批量报名列表记录
	 * @param parameter
	 * @return response JSON
	 */
	@RequestMapping(value = "/student/deleteBatchSignUp", method = RequestMethod.POST)
	public void deleteBatchSignUp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			
			studentService.deleteBatchSignUp(id);
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
	
	
	//学员管理：习题记录 ||考试记录
	@RequestMapping(value = "/student/getStudentTestRecordList", method = RequestMethod.POST)
	public void getStudentTestRecordList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = jsonObj.getInt("userId");		//用户id；
			String cate = jsonObj.getString("cate");	//章节类型；     2习题；   4单科考试；
			
			List<StudentTestRecord> list = studentService.getStudentTestRecordList(userId, cate);
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
	
	@RequestMapping(value = "/student/getStudentFinalTestRecordList", method = RequestMethod.POST)
	public void getStudentFinalTestRecordList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = jsonObj.getInt("userId");		//用户id；
			//String cate = jsonObj.getString("cate");	//章节类型；     2习题；   4单科考试；
			
			List<StudentTestRecord> list = studentService.getStudentFinalTestRecordList(userId);
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
	
	//申请处理：列表查询
	@RequestMapping(value = "/student/getStudentApplyList", method = RequestMethod.POST)
	public void getStudentApplyList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			String username = null;
			String applystate = null;
			String state = null;
			int approve = -1;
			int pageindex = -1;
			int pagesize = -1;
			if(jsonObj.has("username")){
				username = jsonObj.getString("username");		//用户id；
			}
			if(jsonObj.has("applystate")){
				applystate = jsonObj.getString("applystate");		//申请；x休学； y延学； h恢复；
			}
			if(jsonObj.has("state")){
				state = jsonObj.getString("state");		//状态	x休学； y延学； h恢复；
			}
			if(jsonObj.has("approve")){
				approve = jsonObj.getInt("approve");		//审核	0待审核   1通过   2反对
			}
			if(jsonObj.has("index")){
				pageindex = jsonObj.getInt("index"); //分页页码
			}
			if(jsonObj.has("pageSize")){
				pagesize = jsonObj.getInt("pageSize"); //分页页码
			}
			List<StudentApply> list1 = studentService.getStudentApplyList(username, applystate, state, approve);
			int total =list1.size();
			Page<StudentApply> page = new Page<StudentApply>();
			page.setPageNo(pageindex);
			page.setPageSize(pagesize);
			page.setTotalRecord(total);
			
			
			List<StudentApply> list = studentService.getStudentApplyList(username, applystate, state, approve,page);
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
	
	
	//申请处理：删除申请处理记录
	@RequestMapping(value = "/student/deleteStudentApplyById", method = RequestMethod.POST)
	public void deleteStudentApplyById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = jsonObj.getInt("id");		//申请记录id；
			
			studentService.deleteStudentApplyById(userId);
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
	
	@RequestMapping(value = "/student/updateStudentApplyById", method = RequestMethod.POST)
	public void updateStrudentApplyById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter){
	try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");		//申请记录id；
			int approve = jsonObj.getInt("approve");
			int username =  jsonObj.getInt("username");
			String state = jsonObj.getString("applystate");
			
			if(approve ==1){
				User user = new User();
				user.setUsername(String.valueOf(username));
				user.setState(state);
				user.setPassword(null);
				user.setSignSpecial(null);
				userService.updateUser(user, null);
			}
			
			studentService.updateStudentApplyById(id,approve,"");
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
	
	
	@RequestMapping(value = "/student/dealtudentApplyById", method = RequestMethod.POST)
	public void approveStudentApplyById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");		//申请记录id；
			int approve = jsonObj.getInt("approve");	
			String applystate = jsonObj.getString("applystate");
			int startdate = jsonObj.getInt("startdate");
			int enddate = jsonObj.getInt("enddate");
			int userid =  jsonObj.getInt("userid");
			String info = jsonObj.getString("addedinfo");
			studentService.updateStudentApplyById(id,approve,info);
			if(approve ==1){
				switch(applystate){
				case "x":
					//休学
					UserPeriod userPeriod = new UserPeriod();
					userPeriod.setStartdate(startdate);
					userPeriod.setUid(userid);
					userPeriod.setEnddate(enddate);
					userPeriod.setState(applystate);
					UserPeriod up = userPeriodService.getUserPeriod(userPeriod);
					if(up!=null&&up.getStartdate()>0){
						 int $end_date = up.getEnddate();
				         String $state = up.getState();
				         int $span = enddate - startdate;
				         int $end_date2 = $end_date+$span;
				         UserPeriod userP = new UserPeriod();
				         userP.setUid(userid);
				         userP.setStartdate(startdate + $span);
				         userP.setEnddate(enddate + $span);
				         userP.setSpan($span);
				        
				         userPeriodService.updateUserPeriod(userP);
				         UserPeriod userP1 = new UserPeriod();
				         userP1.setEnddate(startdate);
				         userP1.setId(up.getId());
				         userPeriodService.updateUserPeriod(userP1);
				         UserPeriod userP2 = new UserPeriod();
				         userP2.setUid(userid);
				         userP2.setStartdate(startdate);
				         userP2.setEnddate(enddate);
				         userP2.setStartdate(startdate);
				         userP2.setState(applystate);
				         userPeriodService.insertUserPeriod(userP2);
				         
				         UserPeriod userP3 = new UserPeriod();
				         userP3.setUid(userid);
				         userP3.setStartdate(enddate);
				         userP3.setEnddate($end_date2);
				         userP3.setState(up.getState());
				         userPeriodService.insertUserPeriod(userP3);
				         
				         update_user_state_no_period_accordingly(userid, applystate);
				         User user = new User();
				         //user.setUid(userid);
				         //如果申请当天生效则马上开通
				         long num = System.currentTimeMillis()/1000;
				         if(num >= startdate && num <= startdate + 24*60*60){
				        	 user.setState(applystate);
				        	 user.setState("y");
				         }
				         
				         
				         user.setId(userid);
				         update_user_valid_period(user);
					}
					break;
				case "e":
//					UserPeriod userP4 = new UserPeriod();
//			         userP4.setUid(userid);
//			         userP4.setStartdate(enddate);
//			         userP4.setEnddate(enddate);
//			         userP4.setState("e");
//			         userPeriodService.insertUserPeriod(userP4);
//			         update_user_state_no_period_accordingly(userid, applystate);
			         User user = new User();
			         user.setUid(userid);
			         user.setId(userid);
//			         update_user_valid_period(user);
					
					//UserApply ua1 = userPeriodService.getUserApplyById(userApply);
    				
    				int applyId = id;
    				UserApply userApply1 = new UserApply();
    				userApply1.setId(applyId);
    				userApply1.setStartdate(-1);
    				userApply1.setEnddate(-1);
    				userApply1.setUserid(-1);
    				UserApply uapply = userPeriodService.getUserApplyById(userApply1);
    				
    				   UserPeriod usd2 = new UserPeriod();
    			         usd2.setState("e");
    			         usd2.setStartdate(startdate);
    			         usd2.setEnddate(enddate);
    			         usd2.setUid(userid);
    			         
//    			         UserPeriod usd9 = new UserPeriod();
//    			        
//    			  
//    			         usd9.setUid(userid);
//    			         
//    			         UserApply userApply = new UserApply();
//    			         userApply.setUserid(userid);
//    			         List<UserApply> lsu = userPeriodService.getUserApplyList(userApply);
//    			         if(lsu.size() <=1){
//    			        	 usd9.setState("y");
//    			         }else{
//    			        	 for(int i=0; i<lsu.size(); i++){
//    			        		 if(lsu.get(i).getApplystate().equalsIgnoreCase("x")){
//    			        			 UserPeriod usd11 = new UserPeriod();
//    			        			 usd11.setUid(userid);
//    			        			 List<UserPeriod> upds = userPeriodService.getUserPeriod1(usd11);
//    			        			 for(int j=0;j<upds.size();j++){
//    			        				 if(upds.get(j).getState().equalsIgnoreCase("x") && upds.get(j).getStartdate()!=lsu.get(i).getStartdate()&&upds.get(j).getEnddate()!=lsu.get(i).getEnddate()){
//    			        					 usd9.setState("y");
//    			        				 }else{
//    			        					 usd9.setState(upds.get(j).getState());
//    			        				 }
//    			        			 }
//    			        		 }
//    			        	 }
//    			         }
//    			         
//    			         
//    			         userPeriodService.updateUserPeriod(usd9);
    			         userPeriodService.insertUserPeriod(usd2);
    			         //user.setState("e");
    			         //如果申请当天生效则马上开通
				         long num = System.currentTimeMillis()/1000;
				         if(num >= startdate && num <= startdate + 24*60*60){
				        	 user.setState(applystate);
				         }
    			         userService.updateUser(user, null);
    			         
    			         update_user_valid_period(user);
    			         
    			         uapply.setApprove(1);
    						userPeriodService.updateUserApply(uapply);
					
					
					break;
				case "h":
					UserPeriod userP5 = new UserPeriod();
					userP5.setStartdate(startdate);
					userP5.setUid(userid);
					userP5.setEnddate(enddate);
					userP5.setState("x");
					UserPeriod up6 = userPeriodService.getUserPeriodcount(userP5);
					if(up6!=null && up6.getStartdate() > 0){
						int id1 = up6.getId();
						int $end_date = up6.getEnddate();
						int $span = $end_date-enddate;
						UserPeriod up7 = new UserPeriod();
						up7.setEnddate(enddate);
						up7.setSpan(0);
						up7.setId(id1);
						userPeriodService.updateUserPeriod(up7);
						UserPeriod up8 = new UserPeriod();
						up8.setSpan($span);
						up8.setUid(userid);
						up8.setStartdate(enddate);
						userPeriodService.updateUserPeriod1(up8);
						 update_user_state_no_period_accordingly(userid, applystate);
				         User user1 = new User();
				         //user1.setUid(userid);
				         user1.setId(userid);
				         //如果申请当天生效则马上开通
				         long num1 = System.currentTimeMillis()/1000;
				         if(num1 >= startdate && num1 <= startdate + 24*60*60){
				        	 user1.setState(applystate);
				         }
				         update_user_valid_period(user1);
					}
					break;
			}
			}
			
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
	
	@RequestMapping(value = "/student/getStateInfo", method = RequestMethod.POST)
	public void getStateInfo(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int userid = jsonObj.getInt("userid");		//申请记录id；
		UserPeriod up8 = new UserPeriod();
		
		up8.setUid(userid);
		
		List<UserPeriod> up = userPeriodService.getUserPeriod1(up8);
		
		ResponseJson rj = new ResponseJson(up, 0, "success");
		PrintWriter printer;
		try {
			printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    public void update_user_state_no_period_accordingly(int user_id, String changestate) {
       	
    	User user = userService.getUserById(user_id);
    	String lastState = user.getState();
    	String currentState = query_user_real_state(user);
    	if(changestate.equalsIgnoreCase("h")){
    		changestate = "y";
    	}
    	 if (!lastState.equalsIgnoreCase(currentState)) {
    		 //user.setState(changestate);
             userService.updateUser(user, null);
         }
    
    	 //user.setState(changestate);
    	 update_user_valid_period(user);
    	 
    }

    public void update_user_valid_period(User user)
    {
        // 更新缓存在reg_end字段中的有效时间
        int reg_end = userPeriodService.getValidPeriodEnd(user.getId());
        if(reg_end>0)
        {
        	user.setRegEnd(reg_end);
        	userService.updateUser(user, null);
        }
      
    }
    
    private String query_user_real_state(User user)
    {
        int curr_span = (int) (System.currentTimeMillis()/1000);

        if (user.getState().equalsIgnoreCase("q")) {
            //退学用户
            return "q";
        } else {

            if (curr_span <user.getRegDate() || user.getDuration() == 0) {
                // 还没到有效时间，未开通学员
                return "w";
            } else {
                int userid = user.getId();
                
                UserPeriod usd = new UserPeriod();
                usd.setUid(userid);
                usd.setStartdate(curr_span);
                usd.setEnddate(curr_span);               
                UserPeriod ud = userPeriodService.getUserPeriod(usd);
                
                if(ud!=null)
                {
                    if(ud.getState().equalsIgnoreCase("y"))
                    {
                        
                        if (curr_span - user.getLastactivity()> 90 * 86400) 
                            return "z";
                       
                        if(user.getIsTempUser() == 0) 
                            return "y";
                        else 
                            return "t";
                    }
                    return ud.getState();
                }
                return "c";
            }
        }
    }
    
	/*
	 * 结业查询列表
	 * @param parameter
	 * @return response JSON
	 */
	@RequestMapping(value = "/student/getGraduatedList", method = RequestMethod.POST)
	public void getGraduatedList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			
			int id = -1;			//userId
			String username = null;	//用户名
			String name = null;		//姓名
			int graduated = -1;		//是否结业   0未结业    1已结业
			String unit = null;		//单位编号
			int pmid = -1;			//专业管理表（exam_path）id
			int pageindex = -1;
			int pagesize = -1;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("id")) {
					id = jsonObj.getInt("id");
				}
				if (jsonObj.has("username")) {
					username = jsonObj.getString("username");
					username = username.trim();
				}
				if (jsonObj.has("name")) {
					name = jsonObj.getString("name");
					name = name.trim();
				}
				if (jsonObj.has("graduated")) {
					graduated = jsonObj.getInt("graduated");
				}
				if (jsonObj.has("unit")) {
					unit = jsonObj.getString("unit");
				}
				if (jsonObj.has("pmid")) {
					pmid = jsonObj.getInt("pmid");
				}
				if(jsonObj.has("index")){
					pageindex = jsonObj.getInt("index"); //分页页码
				}
				if(jsonObj.has("pageSize")){
					pagesize = jsonObj.getInt("pageSize"); //分页页码
				}
			}
			
			int total = studentService.getGraduatedCounts(id, username, name, graduated, unit, pmid);
			Page<QuestionsManagement> page = new Page<QuestionsManagement>();
			page.setPageNo(pageindex);
			page.setPageSize(pagesize);
			page.setTotalRecord(total);
			
			List<GraduatedQuery> list = studentService.getGraduatedList(id, username, name, graduated, unit, pmid, page);
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
	
	
	/*
	 * 延期管理列表查询
	 * @param parameter
	 * @return response JSON
	 */
	@RequestMapping(value = "/student/getDurationList", method = RequestMethod.POST)
	public void getDurationList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			
			int id = -1;			//userId
			String username = null;	//用户名
			String name = null;		//姓名
			String unit = null;		//单位编号
			String state ="e"; 
			int isDuration = -1; //是否延期    0 否   1 是
			int pmid = -1;			//专业管理表（exam_path）id
			int pageindex = -1;
			int pagesize = -1;
			
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("id")) {
					id = jsonObj.getInt("id");
				}
				if (jsonObj.has("username")) {
					username = jsonObj.getString("username");
					username = username.trim();
				}
				if (jsonObj.has("name")) {
					name = jsonObj.getString("name");
					name = name.trim();
				}
				if (jsonObj.has("isDuration")) {
					isDuration = jsonObj.getInt("isDuration");
				}
				if (jsonObj.has("unit")) {
					unit = jsonObj.getString("unit");
				}
				if (jsonObj.has("pmid")) {
					pmid = jsonObj.getInt("pmid");
				}
				if(jsonObj.has("index")){
					pageindex = jsonObj.getInt("index"); //分页页码
				}
				if(jsonObj.has("pageSize")){
					pagesize = jsonObj.getInt("pageSize"); //分页页码
				}
				if (jsonObj.has("state")) {
					state = jsonObj.getString("state");
					state = state.trim();
				}
			}
			
			int total = studentService.getDurationCounts(id, username, name, isDuration, unit, pmid,state);
			Page<QuestionsManagement> page = new Page<QuestionsManagement>();
			page.setPageNo(pageindex);
			page.setPageSize(pagesize);
			page.setTotalRecord(total);
			
			List<Duration> list = studentService.getDurationList(id, username, name, isDuration, unit, pmid,state,page);
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
	
	
	  @RequestMapping(value = "/student/batchimport", method = RequestMethod.POST)
	    public ModelAndView batchimport(@RequestParam("filename") MultipartFile file,HttpServletRequest request,
	    		HttpServletResponse response,@RequestParam(value = "parameter", required = false) String parameter) throws Exception{
		  JSONObject jsonObj = JSONObject.fromObject(parameter);
		  String signspecial = "";
		  String userid = "";
		  int duration = 0;
			if (jsonObj.has("special")) {
				signspecial = jsonObj.getString("special");
				signspecial = signspecial.trim();
			}
			if (jsonObj.has("userid")) {
				userid = jsonObj.getString("userid");
				userid = userid.trim();
			}
			if (jsonObj.has("duration")) {
				duration = jsonObj.getInt("duration");
				
			}
			ModelAndView modelAndView = new ModelAndView();  
	        modelAndView.addObject("name", "xxx");  
	        modelAndView.setViewName("/success");  
	        
	        //判断文件名是否为空
	        if(file==null) return null;
	        
	        //获取文件名
	        String name=file.getOriginalFilename();
	        
	        //判断文件大小、即名称
	        long size=file.getSize();
	        if(name==null || ("").equals(name) && size==0) return null;
	        
	        //把文件转换成字节流形式
			//InputStream in = file.getInputStream();
			//int i=userLoginService.batchImport(name,file);
			int j=studentService.batchImport(name,file,signspecial,userid,duration);
			if(j>0){
			     String Msg ="批量导入EXCEL成功,导入"+j+"条！";
			     request.getSession().setAttribute("msg",Msg);    
			}else{
			     String Msg ="批量导入EXCEL失败！";
			     request.getSession().setAttribute("msg",Msg);
			} 
	        return modelAndView;
	    }
	  
	  @RequestMapping(value = "/student/batchquestionsimport", method = RequestMethod.POST)
	    public ModelAndView batchquestionsimport(@RequestParam("filename") MultipartFile file,HttpServletRequest request,
	    		HttpServletResponse response,@RequestParam(value = "parameter", required = false) String parameter) throws Exception{
		  JSONObject jsonObj = JSONObject.fromObject(parameter);
			ModelAndView modelAndView = new ModelAndView();  
	        modelAndView.addObject("name", "xxx");  
		  String signspecial = "";
		  String chapterid = "";
		   //判断文件名是否为空
	        if(file==null) return null;
	        
	        //获取文件名
	        String name=file.getOriginalFilename();
	        
	        //判断文件大小、即名称
	        long size=file.getSize();
	        if(name==null || ("").equals(name) && size==0) return null;
	        
			if (jsonObj.has("special")) {
				signspecial = jsonObj.getString("special");
				signspecial = signspecial.trim();
			}
			
			if (jsonObj.has("chapterid")) {
				chapterid = jsonObj.getString("chapterid");
				signspecial = signspecial.trim();
			}
			int j=studentService.batchquestionsImport(name,file,signspecial,signspecial,chapterid);
			if(j>0){
			     String Msg ="批量导入EXCEL成功,导入"+j+"条！";
			     request.getSession().setAttribute("msg",Msg);    
			}else{
			     String Msg ="批量导入EXCEL失败！";
			     request.getSession().setAttribute("msg",Msg);
			} 
	        modelAndView.setViewName("/success");  
		  
		  return modelAndView;
	  }
	  
	  @RequestMapping(value = "/student/importusers", method = RequestMethod.POST)
	  public void importusers(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "parameter", required = false) String parameter) {
		  
		  //删除用户名重复的学员
		  studentService.deleteSameNameUser();
		  //导入数据
		  studentService.importUsers();
		  
		  //跟新discuz数据表
		  
		  
		  
		  List<UCenterMember> list = studentService.queryUcenterMember();
		  
		  for(UCenterMember um:list){
			  studentService.insertmemberfields( um);

			  studentService.insertcommon_member( um);

			  studentService.insertcommon_member_status( um);

			  studentService.insertcommon_member_profile( um);

			  studentService.insertcommon_member_field_forum( um);

			  studentService.insertcommon_member_field_home( um);

			  studentService.insertcommon_member_count( um);
		  }
		  
		  ///update `pre_ucenter_members` set password=md5(password) where uid > 1;
		  
		  try {
			  ResponseJson rj = new ResponseJson(null, 0, "success",0);
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  @RequestMapping(value = "/admin/imgupload", method = RequestMethod.POST)
	    public ModelAndView saveDriver(@RequestParam("filename") MultipartFile file,HttpServletRequest request,
	    		HttpServletResponse response,@RequestParam(value = "parameter", required = false) String parameter) {
		//判断文件名是否为空
	        if(file==null) return null;
	        
	        ModelAndView modelAndView = new ModelAndView();  

	        //获取文件名
	        String name=file.getOriginalFilename();
	        
	        //判断文件大小、即名称
	        long size=file.getSize();
	        
	        String fileName = file.getOriginalFilename();
	        
	        try {
              
                saveFile(fileName, file);
 
            } catch (Exception e) {
            	 modelAndView.setViewName("/fail");  
            }
	        modelAndView.setViewName("/success");  
	        modelAndView.addObject("url","D:/tomcat/apache-tomcat-7.0.39/webapps/driverPic" );  
	        return modelAndView;
	  }
	
	  private void saveFile(String newFileName, MultipartFile filedata) {
	        // TODO Auto-generated method stub
	        // 根据配置文件获取服务器图片存放路径
	        String picDir = "D:/tomcat/apache-tomcat-7.0.39/webapps/driverPic";

	        String saveFilePath = picDir;
	 
	        /* 构建文件目录 */
	        File fileDir = new File(saveFilePath);
	        if (!fileDir.exists()) {
	            fileDir.mkdirs();
	        }
	 
	        try {
	            FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
	                    + newFileName);
	            // 写入文件
	            out.write(filedata.getBytes());
	            out.flush();
	            out.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	    }
	  
	  
		@RequestMapping(value = "/student/batchupdate", method = RequestMethod.POST)
		public void batchupdate(HttpServletRequest request, HttpServletResponse response,
				@RequestParam(value = "parameter", required = false) String parameter) {
			
			List<User> listuser = studentService.getUserByStates();
			
			for(int i=0 ; i< listuser.size(); i++){
				User user = listuser.get(i);
				String[] special = user.getSignSpecial().split(",");
				
				List<TestTypes> listtesttype = new ArrayList<TestTypes>();
						
						for(int k =0 ;k<special.length; k++){
							if(isInteger(special[k])){
								listtesttype.addAll(eduManageService.getMockExamTypeList(Integer.parseInt( special[k])));
							}
							
						}
						//eduManageService.getMockExamTypeList(Integer.parseInt( user.getSignSpecial()));
				
				for(int j=0;j<listtesttype.size(); j++){
					ExamItem examitem = userService.getUserExamFinal(String.valueOf(user.getId()),String.valueOf(listtesttype.get(j).getId()));
					if(examitem!=null){
						user.setState("j");
						user.setIsGraduated(1);
						user.setGraduateDate((int)(StrToDate(examitem.getStartTime()).getTime()/1000));
						userService.updateUser(user, null);
					}else{
						//user.setState(query_user_real_state(user));
						user.setIsGraduated(0);
						user.setGraduateDate(0);
						userService.updateUser(user, null);
					}
				}
				
				
			}
		
			
			
			
			
			
			ResponseJson rj = new ResponseJson(new Object(), 0, "success",0);
			
			
			
			PrintWriter printer;
			try {
				printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public boolean isInteger(String value) {
			  try {
			   Integer.parseInt(value);
			   return true;
			  } catch (NumberFormatException e) {
			   return false;
			  }
			 }
		
		/**
		* 字符串转换成日期
		* @param str
		* @return date
		*/
		public  Date StrToDate(String str) {
		  
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   Date date = null;
		   try {
		    date = format.parse(str);
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   return date;
		}

	
	
}
