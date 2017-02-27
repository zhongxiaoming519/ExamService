package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.exam.ExamItem;
import com.extr.domain.user.Role;
import com.extr.domain.user.UCenterMember;
import com.extr.domain.user.User;

import com.extr.util.Page;

public interface UserMapper {

	/**
	 * 添加user并返回该记录的主键
	 * 
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 更新user基本信息(包括更新password,fullname)
	 * 
	 * @param user
	 */
	public void updateUser(@Param("user") User user, @Param("oldpassword") String oldpassword);

	/**
	 * 根据ID删除某个用户的记录
	 * 
	 * @param user_id
	 */
	public void deleteUser(int user_id);

	/**
	 * 根据ID查询用户基本信息
	 * 
	 * @param user_id
	 * @return
	 */
	public User getUserById(int user_id);

	/**
	 * 根据用户名称查询用户基本信息
	 * 
	 * @param user_name
	 * @return
	 */
	public User getUserByName(String username);

	/**
	 * 获取用户列表
	 * 
	 * @return
	 */
	public List<User> getAllUserList(@Param("page") Page<User> page);

	/**
	 * 插入角色
	 * 
	 * @param role
	 * @return
	 */
	public int insertRole(Role role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 */
	public void updateRole(Role role);

	public void updateRUserRole(@Param("userid") int userid,@Param("roleid")int roleid);
	
	/**
	 * 删除角色
	 * 
	 * @param role_id
	 */
	public void deleteRole(int role_id);

	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	public List<Role> getAllRoleList();

	/**
	 * 给用户授权一种角色
	 * 
	 * 
	 * @param user_id
	 *            role_id
	 */
	public void grantUserRole(@Param("user_id") int user_id, @Param("role_id") int role_id);

	/**
	 * 查询某角色的所有用户
	 * 
	 * @param role_id
	 * @return
	 */
	public List<User> getUserListByRoleId(@Param("role_id") int role_id, @Param("page") Page<User> page);

	/**
	 * 查询用户的角色列表
	 * 
	 * @param user_id
	 * @return
	 */
	public List<Role> getRoleListByUserId(@Param("user_id") int user_id, @Param("page") Page<Role> page);

	/**
	 * 删除user的role
	 * 
	 * @param user_id
	 */
	public void deleteUserRoleByUserId(@Param("user_id") int user_id);
	/***
	 * 角色名称
	 * @param name
	 */
	public Role getauthoritylist(@Param("id") int id);

	public List<User> getUserList(@Param("s_state") String s_state, @Param("s_province") String s_province,  @Param("s_username") String s_username, @Param("s_name") String s_name, @Param("s_mayor") String s_mayor, @Param("s_unit")  String s_unit, @Param("s_modify") int s_modify, @Param("s_from")int s_from,@Param("s_to") int s_to,@Param("isgraduate")  int isgraduate);

	public ExamItem getUserExamFinal(@Param("userid") String userid,@Param("finaltestid") String finaltestid);

	public void deleteSameNameUser();

	public void importUsers();

	public List<UCenterMember> queryUcenterMember();

	public void insertmemberfields(UCenterMember um);

	public void insertcommon_member(UCenterMember um);

	public void insertcommon_member_status(UCenterMember um);

	public void insertcommon_member_profile(UCenterMember um);

	public void insertcommon_member_field_forum(UCenterMember um);

	public void insertcommon_member_field_home(UCenterMember um);

	public void insertcommon_member_count(UCenterMember um);

}
