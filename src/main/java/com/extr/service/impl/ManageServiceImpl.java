package com.extr.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.extr.domain.manage.ExamChannel;
import com.extr.domain.user.User;
import com.extr.persistence.ManageMapper;
import com.extr.service.ManageService;

@Service
public class ManageServiceImpl implements ManageService{
	
	@Autowired
	public ManageMapper manageMapper;
	
	@Override
	public JSONObject getManageTreeList() {
		JSONObject jsonObj = new JSONObject();
//		# 系统设置
//		SELECT * FROM web_channel WHERE deep=1 ORDER BY ord;
		List<ExamChannel> systemList = manageMapper.selectExamChannelList(1, null);
		jsonObj.put("system", systemList);
//		#用户		
		List<ExamChannel> userList = manageMapper.selectExamChannelList(2, "0-1");
		jsonObj.put("user", userList);
//		# 内容维护
//		SELECT * FROM web_channel WHERE deep=2 AND path='0-2' ORDER BY ord;
		List<ExamChannel> contentList = manageMapper.selectExamChannelList(2, "0-2");
		jsonObj.put("content", contentList);
//		# 学员维护
//		SELECT * FROM web_channel WHERE deep=2 AND path='0-3' ORDER BY ord;
		List<ExamChannel> studentList = manageMapper.selectExamChannelList(2, "0-3");
		jsonObj.put("student", studentList);
//		# 试卷维护
//		SELECT * FROM web_channel WHERE deep=2 AND path='0-4' ORDER BY ord;
		List<ExamChannel> examList = manageMapper.selectExamChannelList(2, "0-4");
		jsonObj.put("exam", examList);
//		# 数据维护
//		SELECT * FROM web_channel WHERE deep=2 AND path='0-10' ORDER BY ord;
		List<ExamChannel> dataList = manageMapper.selectExamChannelList(2, "0-10");
		jsonObj.put("data", dataList);
		
		return jsonObj;
	}
	
	@Override
	public List<User> getUserList(int uid, String username, String live) {
		return manageMapper.selectUserList(uid, username, live);
	}

	@Override
	@Transactional
	public int createUser(User user) {
		return manageMapper.insertUser(user);
	}
	
	@Override
	@Transactional
	public void insertUserRole(int userId, int roleId) {
		manageMapper.insertUserRole(userId, roleId);
	}
	
	@Override
	public void deleteUser(int userId) {
		manageMapper.deleteUser(userId);
	}

	

	
	
	

}
