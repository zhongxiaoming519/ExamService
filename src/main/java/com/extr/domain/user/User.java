package com.extr.domain.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User
  implements Serializable
{
  
  private int roleId;
  private String roleName;
  private String creator;
  private int id;
  private String live;
  private int gid;
  private String username;
  private String password;
  private String passwordQuestion;
  private String passwordAnswer;
  private String name;
  private String gender;
  private String phone;
  private String email;
  private String cellphone;
  private String zjlx;
  private String zjhm;
  private String city;
  private String addr;
  private String postcode;
  private Date birthday;
  private String remark;
  private int add_Date;
  private int regDate;
  private int lastactivity;
  private int uid;
  private String state;
  private String userType;
  private String province;
  private String education;
  private String special;
  private String signSpecial;
  private String signClass;
  private String haveClass;
  private int haveClassDate;
  private String aspid;
  private int duration;
  private int isExtendUser;
  private int lastModify;
  private int isTempUser;
  private int graduateDate;
  private int regEnd;
  private String unit;
  private int isGraduated;
  private int title;
  private int qStartDate;
  private int qEndDate;
  private List<Role> roleListStack;
  private int span;
  public int getSpan() {
	return span;
}

public void setSpan(int span) {
	this.span = span;
}

public String getRoleName()
  {
    return this.roleName;
  }
  
  public void setRoleName(String roleName)
  {
    this.roleName = roleName;
  }
  
  public int getRoleId()
  {
    return this.roleId;
  }
  
  public void setRoleId(int roleId)
  {
    this.roleId = roleId;
  }
  
  public String getCreator()
  {
    return this.creator;
  }
  
  public void setCreator(String creator)
  {
    this.creator = creator;
  }
  
  public static long getSerialversionuid()
  {
    return 2866441053387084227L;
  }
  
  public int getQStartDate()
  {
    return this.qStartDate;
  }
  
  public void setQStartDate(int qStartDate)
  {
    this.qStartDate = qStartDate;
  }
  
  public int getQEndDate()
  {
    return this.qEndDate;
  }
  
  public void setQEndDate(int qEndDate)
  {
    this.qEndDate = qEndDate;
  }
  
  public int getTitle()
  {
    return this.title;
  }
  
  public void setTitle(int title)
  {
    this.title = title;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getLive()
  {
    return this.live;
  }
  
  public void setLive(String live)
  {
    this.live = live;
  }
  
  public int getGid()
  {
    return this.gid;
  }
  
  public void setGid(int gid)
  {
    this.gid = gid;
  }
  
  public String getUsername()
  {
    return this.username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getPasswordQuestion()
  {
    return this.passwordQuestion;
  }
  
  public void setPasswordQuestion(String passwordQuestion)
  {
    this.passwordQuestion = passwordQuestion;
  }
  
  public String getPasswordAnswer()
  {
    return this.passwordAnswer;
  }
  
  public void setPasswordAnswer(String passwordAnswer)
  {
    this.passwordAnswer = passwordAnswer;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getGender()
  {
    return this.gender;
  }
  
  public void setGender(String gender)
  {
    this.gender = gender;
  }
  
  public String getPhone()
  {
    return this.phone;
  }
  
  public void setPhone(String phone)
  {
    this.phone = phone;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getCellphone()
  {
    return this.cellphone;
  }
  
  public void setCellphone(String cellphone)
  {
    this.cellphone = cellphone;
  }
  
  public String getZjlx()
  {
    return this.zjlx;
  }
  
  public void setZjlx(String zjlx)
  {
    this.zjlx = zjlx;
  }
  
  public String getZjhm()
  {
    return this.zjhm;
  }
  
  public void setZjhm(String zjhm)
  {
    this.zjhm = zjhm;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getAddr()
  {
    return this.addr;
  }
  
  public void setAddr(String addr)
  {
    this.addr = addr;
  }
  
  public String getPostcode()
  {
    return this.postcode;
  }
  
  public void setPostcode(String postcode)
  {
    this.postcode = postcode;
  }
  
  public Date getBirthday()
  {
    return this.birthday;
  }
  
  public void setBirthday(Date birthday)
  {
    this.birthday = birthday;
  }
  
  public String getRemark()
  {
    return this.remark;
  }
  
  public void setRemark(String remark)
  {
    this.remark = remark;
  }
  
  public int getAdd_Date()
  {
    return this.add_Date;
  }
  
  public void setAdd_Date(int add_Date)
  {
    this.add_Date = add_Date;
  }
  
  public int getRegDate()
  {
    return this.regDate;
  }
  
  public void setRegDate(int regDate)
  {
    this.regDate = regDate;
  }
  
  public int getLastactivity()
  {
    return this.lastactivity;
  }
  
  public void setLastactivity(int lastactivity)
  {
    this.lastactivity = lastactivity;
  }
  
  public int getUid()
  {
    return this.uid;
  }
  
  public void setUid(int uid)
  {
    this.uid = uid;
  }
  
  public String getState()
  {
    return this.state;
  }
  
  public void setState(String state)
  {
    this.state = state;
  }
  
  public String getUserType()
  {
    return this.userType;
  }
  
  public void setUserType(String userType)
  {
    this.userType = userType;
  }
  
  public String getProvince()
  {
    return this.province;
  }
  
  public void setProvince(String province)
  {
    this.province = province;
  }
  
  public String getEducation()
  {
    return this.education;
  }
  
  public void setEducation(String education)
  {
    this.education = education;
  }
  
  public String getSpecial()
  {
    return this.special;
  }
  
  public void setSpecial(String special)
  {
    this.special = special;
  }
  
  public String getSignSpecial()
  {
    return this.signSpecial;
  }
  
  public void setSignSpecial(String signSpecial)
  {
    this.signSpecial = signSpecial;
  }
  
  public String getSignClass()
  {
    return this.signClass;
  }
  
  public void setSignClass(String signClass)
  {
    this.signClass = signClass;
  }
  
  public String getHaveClass()
  {
    return this.haveClass;
  }
  
  public void setHaveClass(String haveClass)
  {
    this.haveClass = haveClass;
  }
  
  public int getHaveClassDate()
  {
    return this.haveClassDate;
  }
  
  public void setHaveClassDate(int haveClassDate)
  {
    this.haveClassDate = haveClassDate;
  }
  
  public String getAspid()
  {
    return this.aspid;
  }
  
  public void setAspid(String aspid)
  {
    this.aspid = aspid;
  }
  
  public int getDuration()
  {
    return this.duration;
  }
  
  public void setDuration(int duration)
  {
    this.duration = duration;
  }
  
  public int getIsExtendUser()
  {
    return this.isExtendUser;
  }
  
  public void setIsExtendUser(int isExtendUser)
  {
    this.isExtendUser = isExtendUser;
  }
  
  public int getLastModify()
  {
    return this.lastModify;
  }
  
  public void setLastModify(int lastModify)
  {
    this.lastModify = lastModify;
  }
  
  public int getIsTempUser()
  {
    return this.isTempUser;
  }
  
  public void setIsTempUser(int isTempUser)
  {
    this.isTempUser = isTempUser;
  }
  
  public int getGraduateDate()
  {
    return this.graduateDate;
  }
  
  public void setGraduateDate(int graduateDate)
  {
    this.graduateDate = graduateDate;
  }
  
  public int getRegEnd()
  {
    return this.regEnd;
  }
  
  public void setRegEnd(int regEnd)
  {
    this.regEnd = regEnd;
  }
  
  public String getUnit()
  {
    return this.unit;
  }
  
  public void setUnit(String unit)
  {
    this.unit = unit;
  }
  
  public int getIsGraduated()
  {
    return this.isGraduated;
  }
  
  public void setIsGraduated(int isGraduated)
  {
    this.isGraduated = isGraduated;
  }
  
  public List<Role> getRoleListStack()
  {
    return this.roleListStack;
  }
  
  public void setRoleListStack(List<Role> roleListStack)
  {
    this.roleListStack = roleListStack;
  }
}
