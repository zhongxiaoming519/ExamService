package com.extr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.extr.domain.content.QuestionsManagement;
import com.extr.domain.student.Duration;
import com.extr.domain.student.GraduatedQuery;
import com.extr.domain.student.StudentApply;
import com.extr.domain.student.StudentTestRecord;
import com.extr.domain.user.UCenterMember;
import com.extr.domain.user.User;
import com.extr.domain.userperiod.UserPeriod;
import com.extr.persistence.StudentMapper;
import com.extr.persistence.UserMapper;
import com.extr.service.ContentService;
import com.extr.service.ManageService;
import com.extr.service.StudentService;
import com.extr.service.UserPeriodService;
import com.extr.service.UserService;
import com.extr.util.Page;
import com.extr.util.ReadExcel;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private ManageService manageService;
	@Autowired
	public UserMapper userMapper;
	@Autowired
	private UserPeriodService userPeriodService;
	@Autowired
	private UserService userService;
	@Autowired
	private ContentService contentService;
	@Override
	public List<User> getBatchSignUpList(int id, String username,String state,Page page,String name,String unit,int last_modify,String province,String special) {
		return studentMapper.selectBatchSignUpList(id, username,state, page,name,unit,last_modify,province,special);
	}

	@Override
	public int insertBatchSignUp(User user) {
		return studentMapper.insertBatchSignUp(user);
	}

	@Override
	public void deleteBatchSignUp(int id) {
		studentMapper.deleteBatchSignUp(id);
	}
	
	@Override
	public List<StudentTestRecord> getStudentTestRecordList(int userId, String cate) {
		return studentMapper.getStudentTestRecordList(userId, cate);
	}
	
	
	@Override
	public List<StudentApply> getStudentApplyList(String username, String applystate, String state, int approve) {
		return studentMapper.getStudentApplyList(username, applystate, state, approve);
	}
	
	@Override
	public void deleteStudentApplyById(int userId) {
		studentMapper.deleteStudentApplyById(userId);
	}
	
	@Override
	public List<GraduatedQuery> getGraduatedList(int id, String username, String name,
			int graduated, String unit, int pmid,Page<QuestionsManagement> page) {
		return studentMapper.getGraduatedList(id, username, name, graduated, unit, String.valueOf(pmid),page);
	}

	@Override
	public List<Duration> getDurationList(int id, String username, String name,
			int isDuration, String unit, int pmid,String state,Page<QuestionsManagement> page) {
		return studentMapper.getDurationList(id, username, name, isDuration, unit, pmid,state,page);
	}
	@Override	
	public int getUsersCounts(int id,String username,String state,String name,String unit,int last_modify,String province,String special){
		return studentMapper.getUsersCounts(id, username, state, name,unit,last_modify,province,special);
	}

	@Override
	public int getGraduatedCounts(int id, String username, String name,
			int graduated, String unit, int pmid) {
		return studentMapper.getGraduatedCounts(id, username, name,graduated, unit,String.valueOf(pmid));

	}

	@Override
	public int getDurationCounts(int id, String username, String name,
			int isDuration, String unit, int pmid,String state) {
		return studentMapper.getDurationCounts(id, username, name,
				isDuration, unit, pmid,state);
	}

	@Override
	public List<User> getBatchSignUpList(int id, String username, String state) {
		return studentMapper.selectBatchSignUpList(id, username,state);
		
	}

	@Override
	public List<StudentApply> getStudentApplyList(String username,
			String applystate, String state, int approve,
			Page<StudentApply> page) {
		return studentMapper.getStudentApplyList(username, applystate, state, approve,page);
	}

	@Override
	public void updateStudentApplyById(int id,int approve,String comment) {
		studentMapper.updateStudentApplyList(id, approve,comment);
		
	}

	@Override
	public void deleteStudentApply(int id) {
		studentMapper.deleteStudentApply(id);
		
	}
	@Override
	public int batchImport(String name,MultipartFile file,String special,String userid,int duration) {

        //处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //获得解析excel方法
        List<User> userList=readExcel.getExcelInfo(name,file);
        
        //把excel信息添加到数据库中
        //List<UserLogin> LoginList=new ArrayList<UserLogin>();
        int num = 0;
        duration = duration*24*60*60;
        for(User user:userList){
        	//判断用户名是否存在，如存在则更新，
        	
        	 User us = userMapper.getUserByName(user.getUsername());
        	 if(us!=null&&us.getDuration() > 0){
        		 String specialori[] = us.getSignSpecial().split(",");
        		 String specialnew[] = special.split(",");
        		 String spe = getDistinct(specialori, specialnew);
        		 if(duration !=0){
        			 user.setRegEnd(user.getRegDate()+duration);
        		 }
        		 us.setSignSpecial(spe);
        		 userService.updateUser(us, null);
        	 }else{
        		 if(duration !=0){
        			 user.setRegEnd(user.getRegDate()+duration);
        		 }
        		 user.setSignSpecial(special);
        		 user.setUid(Integer.parseInt(userid));
        		 manageService.createUser(user);
            	 manageService.insertUserRole(user.getId(), 13);
            	 UserPeriod usd = new UserPeriod();
     		    usd.setState("y");
     		    usd.setStartdate(user.getRegDate());

     		    usd.setEnddate(user.getRegEnd());
     		    usd.setUid(user.getId());     
     		    userPeriodService.insertUserPeriod(usd);
        	 }
        	 num++;
        }
        //return userLoginDao.saveBatch(LoginList);//添加登录信息
		return num;
    }

	private String getDistinct(String special1[],String special2[]) {
		List<String> list = new java.util.ArrayList<String>();
		for (int i = 0; i < special1.length; i++) {
			if (!list.contains(special1[i])) {// 如果list数组不包括num[i]中的值的话，就返回true。
				list.add(special1[i]); // 在list数组中加入num[i]的值。已经过滤过。
			}
		}
		for (int i = 0; i < special2.length; i++) {
			if (!list.contains(special2[i])) {// 如果list数组不包括num[i]中的值的话，就返回true。
				list.add(special2[i]); // 在list数组中加入num[i]的值。已经过滤过。
			}
		}
		
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < list.toArray(new String[0]).length; i++){
			sb. append(list.toArray(new String[0])[i]);
		}
		
		return sb.toString(); // toArray（数组）方法返回数组。并要指定Integer类型。new
											// integer[o]的空间大小不用考虑。因为如果list中的长度大于0（你integer的长度），toArray方法会分配一个具有指定数组的运行时类型和此列表大小的新数组。
	}

	@Override
	public List<StudentTestRecord> getStudentFinalTestRecordList(int userId) {
		// TODO Auto-generated method stub
		return studentMapper.getStudentFinalTestRecordList(userId);
	}

	@Override
	public List<User> getUserByStates() {
		// TODO Auto-generated method stub
		return studentMapper.getUserByStates();
	}

	@Override
	public int batchquestionsImport(String name, MultipartFile file,
			String signspecial, String signspecial2, String chapterid) {
	      //处理EXCEL
        ReadExcel readExcel=new ReadExcel();
        //获得解析excel方法
        List<QuestionsManagement> questionsList=readExcel.getExamExcelInfo(name,file);
        int num = 0;
        for(QuestionsManagement question:questionsList){
        	//判断用户名是否存在，如存在则更新，
        	
        	question.setCid(Integer.parseInt(signspecial2));
        	question.setCpath(chapterid);
        	question.setRemark(" ");
        	question.setZl(" ");
        	contentService.insertQuestionsManagement(question);
        	 
        	 num++;
        }
		return num;
	}

	@Override
	public void deleteSameNameUser() {
		// TODO Auto-generated method stub
		userMapper.deleteSameNameUser();
	}
	
	public void importUsers(){
		userMapper.importUsers();
	}

	@Override
	public List<UCenterMember> queryUcenterMember() {
		// TODO Auto-generated method stub
		return userMapper.queryUcenterMember();
	}

	@Override
	public void insertmemberfields(UCenterMember um) {
		userMapper.insertmemberfields(um);
		
	}

	@Override
	public void insertcommon_member(UCenterMember um) {
		userMapper.insertcommon_member(um);
		
	}

	@Override
	public void insertcommon_member_status(UCenterMember um) {
		userMapper.insertcommon_member_status(um);
		
	}

	@Override
	public void insertcommon_member_profile(UCenterMember um) {
		userMapper.insertcommon_member_profile(um);
		
	}

	@Override
	public void insertcommon_member_field_forum(UCenterMember um) {
		userMapper.insertcommon_member_field_forum(um);
		
	}

	@Override
	public void insertcommon_member_field_home(UCenterMember um) {
		userMapper.insertcommon_member_field_home(um);
		
	}

	@Override
	public void insertcommon_member_count(UCenterMember um) {
		userMapper.insertcommon_member_count(um);
		
	}
}
