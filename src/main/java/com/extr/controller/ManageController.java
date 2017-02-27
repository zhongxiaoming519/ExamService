package com.extr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.extr.domain.manage.ExamChannel;
import com.extr.domain.user.User;
import com.extr.domain.userperiod.UserPeriod;
import com.extr.persistence.UserMapper;
import com.extr.service.ManageService;
import com.extr.service.UserPeriodService;
import com.extr.service.UserService;
import com.extr.util.StandardPasswordEncoderForSha1;

@Controller
public class ManageController {
	
	@Autowired
	private ManageService manageService;
	@Autowired
	private UserPeriodService userPeriodService;
	@Autowired
	private  UserMapper userMapper;
	//获取超级管理员的管理树结构
	@RequestMapping(value = "/manage/createManageTree", method = RequestMethod.POST)
	public void createManageTree(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			JSONObject jsonObj =  manageService.getManageTreeList();
			List<ExamChannel> systemList = (List) jsonObj.get("system");
			List<ExamChannel> contentList = (List) jsonObj.get("content");
			List<ExamChannel> userList = (List) jsonObj.get("user");
			List<ExamChannel> studentList = (List) jsonObj.get("student");
			List<ExamChannel> examList = (List) jsonObj.get("exam");
			List<ExamChannel> dataList = (List) jsonObj.get("data");
			
			JSONObject dataJson = new JSONObject();
			dataJson.put("system", systemList);
			dataJson.put("content", contentList);
			dataJson.put("user", userList);
			dataJson.put("student", studentList);
			dataJson.put("exam", examList);
			dataJson.put("data", dataList);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", dataJson);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//查询用户列表
	@RequestMapping(value = "/manage/getUserList", method = RequestMethod.GET)
	public void getUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			int uid = 0;
			String username = null;
			String live = null;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("uid")) {
					uid = (Integer) jsonObj.get("uid");
				}
				if (jsonObj.has("username")) {
					username = (String) jsonObj.get("username");
				}
				if (jsonObj.has("live")) {
					live = (String) jsonObj.get("username");
				}
				
			}
			
			List<User> list = manageService.getUserList(uid, username, live);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", list);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	
	//创建用户
	@RequestMapping(value = "/manage/createUser", method = RequestMethod.POST)
	public void createUser(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			/**
			 * 请求的content-type需要设置为：
			 * headers = {"Content-type": "application/x-www-form-urlencoded"}
			 * 否则页面会报错
			 */
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			String userName = jsonObj.getString("username");
			String passWord = jsonObj.getString("password");
			String passwordQuestion = jsonObj.getString("passwordQuestion");
			String passwordAnswer = jsonObj.getString("passwordAnswer");
			String realName = jsonObj.getString("name");
			String gender = jsonObj.getString("gender");
			String telephone = jsonObj.getString("phone");
			String mobile = jsonObj.getString("cellphone");
			String mail = jsonObj.getString("email");
			String idCard = jsonObj.getString("zjhm");
			Date birthday = null;
			//获取生日
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayStr = jsonObj.getString("birthday");
			if(birthdayStr.equals("")){
				birthday = format.parse("1970-01-01");
			}else{
				birthday = format.parse(birthdayStr);
			}
			
			
			String province = (String) jsonObj.get("province");
			String manageUninCode = (String) jsonObj.get("unit");
			String postAddress = (String) jsonObj.get("addr");
			String postCode = (String) jsonObj.get("postcode");
			String mark = (String) jsonObj.get("remark");
			String roleStr = jsonObj.getString("role");
			int roleId = Integer.parseInt(roleStr);
			
			String signSpecial = jsonObj.getString("signSpecial");
			int gid =  Integer.parseInt(roleStr);
			String live = jsonObj.getString("live");
			String zjhm = jsonObj.getString("zjhm");
			//获取 add_date   int类型
			long time = new java.util.Date().getTime();
			//String add_dat_str = time + "";
			
			int add_date = (int) (System.currentTimeMillis()/1000);
			
			//获取 reg_date   int类型
			String reg_date_str = jsonObj.getString("regDate");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date rd = sdf.parse(reg_date_str);
			int reg_date = Integer.parseInt((rd.getTime() + "").substring(0, 10));
			int reg_end = reg_date+380*24*60*60;
			int uid = jsonObj.getInt("uid");
			User user = new User();
			user.setLive(live);
			user.setGid(gid);
			user.setUsername(userName);
			PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
			String resultPassword = passwordEncoder.encode(passWord);
			user.setPassword(resultPassword);
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
			user.setRegDate(reg_date);
			user.setSignSpecial(signSpecial);
			user.setUid(uid);
			user.setDuration(32832000);
			user.setSignClass("三");
			user.setUserType(jsonObj.getString("userType"));
			user.setState(jsonObj.getString("state"));
			user.setRegEnd(reg_end);
			user.setLastactivity(add_date);
			user.setZjhm(zjhm);
			user.setRemark(mark);
			User u = userMapper.getUserByName(userName);
			if(u!=null&&(!u.getUsername().equalsIgnoreCase("")||u.getUsername()!=null)){
				JSONObject retJson = new JSONObject();
				retJson.put("data", user.getName());
				retJson.put("errcode", -1);
				retJson.put("errmsg", "username: "+user.getName()+" 已经存在！");
				
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			}else{
				manageService.createUser(user);
//				System.out.println(user.getId());
				
				//新增用户角色表   exam_r_user_role
				manageService.insertUserRole(user.getId(), roleId);
				
				//新增user_period
				UserPeriod usd = new UserPeriod();
			    usd.setState("y");
			    usd.setStartdate(reg_date);
			    usd.setEnddate(reg_end);
			    usd.setUid(user.getId());     
			    userPeriodService.insertUserPeriod(usd);
				JSONObject retJson = new JSONObject();
				retJson.put("data", user.getId());
				retJson.put("errcode", 0);
				retJson.put("errmsg", "success");
				
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//删除用户
	@RequestMapping(value = "/manage/deleteUser", method = RequestMethod.POST)
	public void deleteUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
//			String parameter = request.getParameter("parameter");
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int userId = (Integer) jsonObj.get("userid");
			
			manageService.deleteUser(userId);
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
			
	}
	
	
}
