package com.extr.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.extr.domain.content.QuestionsManagement;
import com.extr.domain.student.Duration;
import com.extr.domain.student.GraduatedQuery;
import com.extr.domain.student.StudentApply;
import com.extr.domain.student.StudentTestRecord;
import com.extr.domain.user.UCenterMember;
import com.extr.domain.user.User;
import com.extr.util.Page;


public interface StudentService {
	
	/**
	 * 学员维护：批量报名列表查询
	 * @param id 
	 * @param username
	 * @return List<User>
	 */
	List<User> getBatchSignUpList(int id, String username,String state,Page page,String name,String unit,int last_modify,String province,String special);
	List<User> getBatchSignUpList(int id, String username,String state);
	/**
	 * 学员维护：新增批量报名列表记录
	 * @param User user
	 * @return int
	 */
	int insertBatchSignUp(User user);
	/**
	 * 学员维护：删除批量报名列表记录
	 */
	void deleteBatchSignUp(int id);
	
	/**
	 * 学员管理：习题记录列表查询
	 * @param int userId, String cate
	 * @return List<StudentTestRecord>
	 */
	List<StudentTestRecord> getStudentTestRecordList(int userId, String cate);
	
	List<StudentTestRecord> getStudentFinalTestRecordList(int userId);
	
	/**
	 * 申请处理：列表查询
	 * @param username, applystate, state, approve
	 * @return List<StudentApply>
	 */
	List<StudentApply> getStudentApplyList(String username, String applystate, String state, int approve);
	List<StudentApply> getStudentApplyList(String username, String applystate, String state, int approve,Page<StudentApply> page);
	/**
	 * 申请处理：删除申请记录
	 * @param id
	 */
	void deleteStudentApplyById(int userId);
	void updateStudentApplyById(int id,int approve,String comment);
	
	void deleteStudentApply(int id);
	
	/**
	 * 学员维护：结业查询列表记录
	 * @param
	 * @return List<GraduatedQuery>
	 */
	List<GraduatedQuery> getGraduatedList(int id, String username, String name, int graduated, String unit, int pmid,Page<QuestionsManagement> page);
	
	/**
	 * 学员维护：延期管理列表查询
	 * @param
	 * @return List<GraduatedQuery>
	 */
	List<Duration> getDurationList(int id, String username, String name, int isDuration, String unit, int pmid,String state,Page<QuestionsManagement> page);
	int getDurationCounts(int id, String username, String name, int isDuration, String unit, int pmid,String state);
	
	int getUsersCounts(int id,String username,String state,String name,String unit,int last_modify,String province,String special);
	
	int getGraduatedCounts(int id, String username, String name, int graduated, String unit, int pmid);
	
	int batchImport(String name, MultipartFile file, String signspecial,String id,int duration);
	
	
	List<User> getUserByStates();
	int batchquestionsImport(String name, MultipartFile file,
			String signspecial, String signspecial2, String chapterid);
	
	void deleteSameNameUser();
	void importUsers();
	List<UCenterMember> queryUcenterMember();
	
	void insertmemberfields(UCenterMember um);
	void insertcommon_member(UCenterMember um);
	void insertcommon_member_status(UCenterMember um);
	void insertcommon_member_profile(UCenterMember um);
	void insertcommon_member_field_forum(UCenterMember um);
	void insertcommon_member_field_home(UCenterMember um);
	void insertcommon_member_count(UCenterMember um);
	
}	
