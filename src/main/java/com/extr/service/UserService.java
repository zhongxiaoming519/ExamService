package com.extr.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.exam.ExamItem;
import com.extr.domain.user.Role;
import com.extr.domain.user.User;
import com.extr.domain.userperiod.UserApply;

import com.extr.util.Page;

/**
 * @author Ocelot
 * @date 2014年6月8日 下午5:52:55
 */
public interface UserService {

	int addUser(User user);
	
	int addAdmin(User user);

	public List<User> getUserListByRoleId(int roleId,Page<User> page);
	
	public void updateUser(User user,String oldPassword);
	public void updateRUserRole(int userid,int roleid);
	
	public User getUserById(int user_id);
	
	public void disableUser(int user_id);
	
	public List<Role> getAllRoleList();
	
	public void insertRole(Role role);
	
	public void deleteRole(int id);

	public  Role getauthoritylist(int id);

	void updateRole(Role role);
	
	public List<User> getUserList(String s_state,String s_province,String s_username,String s_name,String s_mayor,String s_unit,int s_modify,int s_from, int s_to, int isgraduate);
	
	ExamItem getUserExamFinal(String userid,String finaltestid);
}
