package com.extr.util;

import java.util.Map;
import com.extr.domain.user.User;

public class UserTransfer
{

	private final String name;

	private final Map<String, Boolean> roles;

	private final User user;
	int groupid = 0;
	public int getGroupid() {
		return groupid;
	}


	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}


	private String rolesname = null;

	private int days;
	public int getDays() {
		return days;
	}


	public void setDays(int days) {
		this.days = days;
	}


	public String getRolesname() {
		return rolesname;
	}


	public void setRolesname(String rolesname) {
		this.rolesname = rolesname;
	}


	public User getUser() {
		return user;
	}


	public UserTransfer(String userName, Map<String, Boolean> roles, User user,String rolesname,int gid,int days)
	{
		this.name = userName;
		this.roles = roles;
		this.user = user;
		this.rolesname = rolesname;
		this.groupid = gid;
		this.days = days;
	}


	public String getName()
	{
		return this.name;
	}


	public Map<String, Boolean> getRoles()
	{
		return this.roles;
	}

}