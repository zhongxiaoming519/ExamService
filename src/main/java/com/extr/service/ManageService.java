package com.extr.service;

import java.util.List;

import net.sf.json.JSONObject;

import com.extr.domain.manage.ExamChannel;
import com.extr.domain.user.User;

public interface ManageService {
	
	//获取超级管理员的管理树结构
	JSONObject getManageTreeList();
	
	//查询用户列表
	List<User> getUserList(int uid, String username, String live);
	
	//创建用户
	int createUser(User user);
	
	//新增用户角色表
	void insertUserRole(int userId, int roleId);
	
	//删除用户
	void deleteUser(int userId);
	
	

}
