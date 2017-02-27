package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.manage.ExamChannel;
import com.extr.domain.user.User;
import com.extr.util.Page;

public interface ManageMapper {
	
	/**
	 * 查询全部的管理树结构
	 * @param deep,path
	 * @return List<ExamChannel>
	 */
	List<ExamChannel> selectExamChannelList(@Param("deep") int deep, @Param("path") String path);
	/**
	 * 查询用户列表
	 * @param uid,username,live
	 * @return List<User>
	 */
	List<User> selectUserList(@Param("uid") int uid, @Param("username") String username, @Param("live") String live);
	/**
	 * 创建用户
	 * @param user
	 * @return GeneratedKeys
	 */
	int insertUser(User user);
	/**
	 * 新增用户角色表
	 * @param userId,roleId
	 */
	void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
	/**
	 * 删除用户
	 * @param userId
	 */
	void deleteUser(int userId);
	
}
