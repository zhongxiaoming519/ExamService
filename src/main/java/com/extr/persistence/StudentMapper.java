package com.extr.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.extr.domain.content.QuestionsManagement;
import com.extr.domain.student.Duration;
import com.extr.domain.student.GraduatedQuery;
import com.extr.domain.student.StudentApply;
import com.extr.domain.student.StudentTestRecord;
import com.extr.domain.user.User;
import com.extr.util.Page;

public interface StudentMapper {
	
	/**
	 * 学员维护：批量报名列表查询
	 * @param id 
	 * @param username
	 * @return List<User>
	 */
	List<User> selectBatchSignUpList(@Param("id") int id, @Param("username") String username, @Param("state") String state,@Param("Page") Page page,@Param("name")String name,@Param("unit")String unit,@Param("last_modify")int last_modify,@Param("province")String province,@Param("special")String special);
	/**
	 * 学员维护：新增批量报名列表记录
	 * @param User user
	 * @return int
	 */
	int insertBatchSignUp(User user);
	/**
	 * 学员维护：删除批量报名列表记录
	 * @param User user
	 * @return int
	 */
	void deleteBatchSignUp(int id);
	
	/**
	 * 学员管理：习题记录列表查询
	 * @param User user
	 * @return int
	 */
	List<StudentTestRecord> getStudentTestRecordList(@Param("userId") int userId, @Param("cate") String cate);
	
	/**
	 * 申请处理：列表查询
	 * @param username, applystate, state, approve
	 * @return List<StudentApply>
	 */
	List<StudentApply> getStudentApplyList(
			@Param("username") String username, 
			@Param("applystate") String applystate, 
			@Param("state") String state, 
			@Param("approve") int approve);
	/**
	 * 申请处理：删除申请记录
	 * @param id
	 */
	void deleteStudentApplyById(@Param("userId") int userId);
	
	/**
	 * 学员维护：结业查询列表记录
	 * @param page 
	 * @param
	 * @return List<GraduatedQuery>
	 */
	List<GraduatedQuery> getGraduatedList(
			@Param("id") int id, @Param("username") String username, 
			@Param("name") String name, @Param("graduated") int graduated, 
			@Param("unit") String unit, @Param("pmid") String pmid, Page<QuestionsManagement> page);
	
	
	/**
	 * 学员维护：延期管理列表查询
	 * @param
	 * @return List<GraduatedQuery>
	 */
	List<Duration> getDurationList(
			@Param("id") int id, @Param("username") String username, 
			@Param("name") String name, @Param("isDuration") int isDuration, 
			@Param("unit") String unit, @Param("pmid") int pmid,@Param("state") String state,@Param("page") Page<QuestionsManagement> page);
	
	
	int getUsersCounts(@Param("id") int id, @Param("username") String username, @Param("state") String state,@Param("name")String name,@Param("unit")String unit,@Param("last_modify")int last_modify,@Param("province")String province,@Param("special")String special);
	
	int getGraduatedCounts(@Param("id") int id, @Param("username") String username, 
			@Param("name") String name, @Param("graduated") int graduated, 
			@Param("unit") String unit, @Param("pmid") String pmid);
	int getDurationCounts(@Param("id") int id, @Param("username") String username, 
			@Param("name") String name, @Param("isDuration") int isDuration, 
			@Param("unit") String unit, @Param("pmid") int pmid, @Param("state") String state);
	List<User> selectBatchSignUpList(@Param("id") int id, @Param("username") String username,  @Param("state") String state);
	List<StudentApply> getStudentApplyList(@Param("username") String username, @Param("applystate") String applystate,
			@Param("state") String state, @Param("approve") int approve,@Param("page") Page<StudentApply> page);
	void updateStudentApplyList(@Param("id") int id, @Param("approve") int approve, @Param("comment") String comment);
	void deleteStudentApply(@Param("id") int id);
	List<StudentTestRecord> getStudentFinalTestRecordList(int userId);
	List<User> getUserByStates();
}
