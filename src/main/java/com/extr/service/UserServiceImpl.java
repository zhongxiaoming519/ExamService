package com.extr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.extr.domain.exam.ExamItem;
import com.extr.domain.user.Role;
import com.extr.domain.user.User;
import com.extr.domain.userperiod.UserApply;
import com.extr.persistence.UserMapper;

import com.extr.util.Page;

/**
 * @author Ocelot
 * @date 2014年6月8日 下午8:21:31
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserMapper userMapper;

	@Override
	@Transactional
	public int addUser(User user) {
		try {
			int userId = -1;
			userMapper.insertUser(user);
			userId = user.getId();
			if (user.getRoleListStack() == null)
				userMapper.grantUserRole(userId, 3);
			else
				userMapper.grantUserRole(user.getId(), user.getRoleListStack().get(0).getId());
			
			return userId;
		} catch (Exception e) {
			if(e.getClass().getName().equals("org.springframework.dao.DuplicateKeyException"))
				throw new RuntimeException("duplicate-username");
			else 
				throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	@Transactional
	public int addAdmin(User user) {
		try {
			int userId = -1;
			userMapper.insertUser(user);
			userId = user.getId();
			if (user.getRoleListStack() == null)
				userMapper.grantUserRole(userId, 1);
			else
				userMapper.grantUserRole(user.getId(), user.getRoleListStack().get(0).getId());
			
			return userId;
		} catch (Exception e) {
			if(e.getClass().getName().equals("org.springframework.dao.DuplicateKeyException"))
				throw new RuntimeException("duplicate-username");
			else 
				throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<User> getUserListByRoleId(int roleId,Page<User> page) {

		List<User> userList = userMapper.getUserListByRoleId(roleId, page);
		return userList;
	}

	@Override
	public void updateUser(User user, String oldPassword) {

		userMapper.updateUser(user, oldPassword);
	}

	@Override
	public User getUserById(int user_id) {

		return userMapper.getUserById(user_id);
	}

	@Override
	public void disableUser(int user_id) {

	}

	@Override
	public List<Role> getAllRoleList() {
		
		return userMapper.getAllRoleList();
	}

	@Override
	public void insertRole(Role role) {
		 userMapper.insertRole(role);
	}
	
	public void deleteRole(int id){
		 userMapper.deleteRole(id);
	}

	@Override
	public Role getauthoritylist(int id) {
		
		return userMapper.getauthoritylist(id);
	}

	@Override
	public void updateRUserRole(int userid, int roleid) {
		userMapper.updateRUserRole(userid, roleid);
		
	}

	@Override
	public void updateRole(Role role) {
		userMapper.updateRole(role);
		
	}

	@Override
	public List<User> getUserList(String s_state,String s_province,String s_username,String s_name,String s_mayor,String s_unit,int s_modify,int s_from, int s_to, int isgraduate) {
		
		return userMapper.getUserList(s_state,s_province,s_username,s_name,s_mayor,s_unit,s_modify,s_from,  s_to, isgraduate);
	}

	@Override
	public ExamItem getUserExamFinal(String userid, String finaltestid) {
		// TODO Auto-generated method stub
		return userMapper.getUserExamFinal(userid, finaltestid);
	}


}
