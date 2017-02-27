package com.extr.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.extr.domain.ResponseJson;
import com.extr.domain.content.Appraisal;
import com.extr.domain.content.AppraisalNodes;
import com.extr.domain.content.ClassChapter;
import com.extr.domain.content.ClassManagement;
import com.extr.domain.content.ModuleManagement;
import com.extr.domain.content.ProfessionalManagement;
import com.extr.domain.content.QuestionsManagement;
import com.extr.domain.user.Role;
import com.extr.service.ContentService;
import com.extr.util.Page;
import com.extr.util.ResultTransfer;


@Controller
public class ContentController {
	
	/*
	 * 内容维护模块控制器
	 * 
	 * 专业管理
	 * 咨询管理
	 * 课程管理
	 * 题库管理
	 * 鉴定辅导
	 * 模块管理
	 * 试听课程（此模块没做）
	 * 
	 */
	
	
	
	@Autowired
	private ContentService contentService;
	
	//获取专业管理表查询
	@RequestMapping(value = "/content/getProfessionalManagementList", method = RequestMethod.POST)
	public void getProfessionalManagementList(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			
			JSONObject jsonObj =  contentService.getProfessionalManagementList();
			List<ProfessionalManagement> list = (List<ProfessionalManagement>) jsonObj.get("data");
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", list);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString()); 
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//新增专业管理表查询
	@RequestMapping(value = "/content/insertProfessionalManagement", method = RequestMethod.POST)
	public void insertProfessionalManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			String path = "0";
			String title = (String) jsonObj.get("title");
			String module = (String) jsonObj.get("module");
			int valid = (Integer) jsonObj.get("valid");
			int deep = 1;
			
			int ord = contentService.getMaxOrdByModule(module);
			ord += 1;
			
			ProfessionalManagement pm = new ProfessionalManagement();
			pm.setPath(path);
			pm.setTitle(title);
			pm.setModule(module);
			pm.setValid(valid);
			pm.setDeep(deep);
			pm.setOrd(ord);
			contentService.insertProfessionalManagement(pm);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", pm.getId());
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//修改专业管理记录
	@RequestMapping(value = "/content/updateProfessionalManagementById", method = RequestMethod.POST)
	public void updateProfessionalManagementById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			String path = "0";
			String title = (String) jsonObj.get("title");
			String module = (String) jsonObj.get("module");
			int id =  (Integer) jsonObj.get("id");
			int valid = (Integer) jsonObj.get("valid");
			int ord = jsonObj.getInt("ord");
			int deep = 1;
			
			ProfessionalManagement pm = new ProfessionalManagement();
			pm.setPath(path);
			pm.setTitle(title);
			pm.setModule(module);
			pm.setValid(valid);
			pm.setDeep(deep);
			pm.setOrd(ord);
			pm.setId(id);
			contentService.updateProfessionalManagementById(pm);
			
			ResponseJson rj = new ResponseJson(null, 0, "success");
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//删除专业管理记录
	@RequestMapping(value = "/content/deleteProfessionalManagement", method = RequestMethod.POST)
	public void deleteProfessionalManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int pmid = (Integer) jsonObj.get("pmid");
			contentService.deleteProfessionalManagementById(pmid);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	///////////////////////////////////////////// 咨询管理  ///////////////////////////////////////////////////////////
	
	//咨询管理
	
	
	///////////////////////////////////////////// 课程管理  ///////////////////////////////////////////////////////////
	
	//获取课程管理表查询
	@RequestMapping(value = "/content/getClassManagementList", method = RequestMethod.POST)
	public void getClassManagementList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			String no = null;
			String title = null;
			String path = null;
			ClassManagement cm = new ClassManagement();
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("no")) {
					no = (String) jsonObj.get("no");
					no = no.trim();
					cm.setNo(no);
				}
				if (jsonObj.has("title")) {
					title = (String) jsonObj.get("title");
					title = title.trim();
					cm.setTitle(title);
				}
				if (jsonObj.has("path")) {
					path = (String) jsonObj.get("path");
					path = path.trim();
					cm.setTitle(path);
				}
			}
			List<ClassManagement> list = contentService.getClassManagementList(cm);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", list);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//新增课程管理
	@RequestMapping(value = "/content/insertClassManagement", method = RequestMethod.POST)
	public void insertClassManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int uid = (Integer) jsonObj.get("uid");
			String state = (String) jsonObj.get("state");
			String no = (String) jsonObj.get("no");
			String path = (String) jsonObj.get("path");
			String title = (String) jsonObj.get("title");
			int xueshi = (Integer) jsonObj.get("xueshi");
			double xuefen = Double.parseDouble(jsonObj.get("xuefen").toString());
			int date_limit = (Integer)jsonObj.getInt("date_limit");
			double cost = Double.parseDouble(jsonObj.get("cost").toString());
			String text = (String) jsonObj.get("text");
			long time = new java.util.Date().getTime();
			String createTimeStr = time + "";
			int add_date = Integer.parseInt(createTimeStr.substring(0, 10));
			
			ClassManagement cm = new ClassManagement();
			cm.setState(state);
			cm.setNo(no);
			cm.setPath(path);
			cm.setTitle(title);
			cm.setXueshi(xueshi);
			cm.setXuefen(xuefen);
			cm.setDate_limit(date_limit);
			cm.setCost(cost);
			cm.setText(text);
			cm.setAdd_date(add_date);
			cm.setUid(uid);
			
			contentService.insertClassManagement(cm);
			
			JSONObject retJson = new JSONObject();
			System.out.println(cm.getId());
			retJson.put("data", cm.getId());
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//更新课程管理
	@RequestMapping(value = "/content/updateClassManagement", method = RequestMethod.POST)
	public void updateClassManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = (Integer) jsonObj.get("id");
			int uid = (Integer) jsonObj.get("uid");
			String state = (String) jsonObj.get("state");
			String no = (String) jsonObj.get("no");
			String path = (String) jsonObj.get("path");
			String title = (String) jsonObj.get("title");
			int xueshi = (Integer) jsonObj.get("xueshi");
			double xuefen = Double.parseDouble(jsonObj.get("xuefen").toString());
			int date_limit = (Integer)jsonObj.getInt("date_limit");
			double cost = Double.parseDouble(jsonObj.get("cost").toString());
			String text = (String) jsonObj.get("text");
			long time = new java.util.Date().getTime();
			String createTimeStr = time + "";
			int add_date = Integer.parseInt(createTimeStr.substring(0, 10));
			
			ClassManagement cm = new ClassManagement();
			cm.setState(state);
			cm.setNo(no);
			cm.setPath(path);
			cm.setTitle(title);
			cm.setXueshi(xueshi);
			cm.setXuefen(xuefen);
			cm.setDate_limit(date_limit);
			cm.setCost(cost);
			cm.setText(text);
			cm.setAdd_date(add_date);
			cm.setUid(uid);
			cm.setId(id);
			contentService.updateClassManagement(cm);
			
			JSONObject retJson = new JSONObject();
			System.out.println(cm.getId());
			retJson.put("data", cm.getId());
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	//删除课程管理
	@RequestMapping(value = "/content/deleteClassManagement", method = RequestMethod.POST)
	public void deleteClassManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = (Integer) jsonObj.get("id");
			
			contentService.deleteClassManagementById(id);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//章节设置列表
	@RequestMapping(value = "/content/getClassChapterList", method = RequestMethod.POST)
	public void getClassChapterList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int iid = -1;
			if(jsonObj.has("iid")&&!jsonObj.getString("iid").equalsIgnoreCase("")){
				iid = jsonObj.getInt("iid");
			}
			
			String cate = jsonObj.getString("cate");
			
			List<ClassChapter> list = contentService.getClassChapterList(iid, cate);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", list);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//新增章节记录
	@RequestMapping(value = "/content/insertClassChapter", method = RequestMethod.POST)
	public void insertClassChapter(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		
		
		 int iid = -1;
		 if (jsonObj.has("iid")) {
			 iid = jsonObj.getInt("iid");
		 }
				
		 int ord = -1; 
		 if (jsonObj.has("ord")) {
			 ord = jsonObj.getInt("ord");
		 }
				 
		 String path = null;
		 if (jsonObj.has("path")) {
			 path = jsonObj.getString("path");
		 }
				
		 int deep = -1;
		 if(jsonObj.has("deep")){
			 deep = jsonObj.getInt("deep");
		 }
				 
		 String title = null;
		 if(jsonObj.has("title")){
			 title = jsonObj.getString("title");
		 }
				 
		 String ppt_url = "";
		 if(jsonObj.has("ppt_url")){
			 ppt_url = jsonObj.getString("ppt_url");
		 }
		 
				 
		 String video_url = "";
		 if(jsonObj.has("video_url")){
			 video_url = jsonObj.getString("video_url");
		 }
				
		 String text = "";
		 if(jsonObj.has("text")){
			 text = jsonObj.getString("text");
		 }
				 
		 
		 String cate= "";
		 if(jsonObj.has("cate")){
			 cate = jsonObj.getString("cate");
		 }
				 
		 String exam_set="2,,|3,,";
		 if(jsonObj.has("exam_set")){
			 exam_set =  jsonObj.getString("exam_set");
		 }
				
		 int duration= -1;
		 if(jsonObj.has("duration")){
			 duration = jsonObj.getInt("duration");
		 }
				 
		 
		 ClassChapter cc = new ClassChapter();
		 
		 cc.setIid(iid);
		 cc.setOrd(ord);
		 cc.setPath(path);
		 cc.setDeep(deep);
		 cc.setTitle(title);
		 cc.setPpt_url(ppt_url);
		 cc.setVideo_url(video_url);
		 cc.setText(text);
		 cc.setCate(cate);
		 cc.setExam_set(exam_set);
		 cc.setDuration(duration);
		 contentService.insertClasschapter(cc);
		 
		 JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
		}catch(Exception e){
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/content/updateClassChapter", method = RequestMethod.POST)
	public void updateClassChapter(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			
			 int id = jsonObj.getInt("id");
			 int iid = jsonObj.getInt("iid");
			 int ord = jsonObj.getInt("ord");
			 String path = jsonObj.getString("path");
			 int deep = jsonObj.getInt("deep");
			 String title = jsonObj.getString("title");
			 String ppt_url ="";
			 if(jsonObj.has("ppt_url")){
				 ppt_url= jsonObj.getString("ppt_url");
			 }
					 
			 String video_url = "";
			 if(jsonObj.has("video_url")){
				 video_url = jsonObj.getString("video_url");
			 }
					
			 String text = "";
			 if(jsonObj.has("text")){
				 text = jsonObj.getString("text");
			 }
			 
			 
			 String cate= jsonObj.getString("cate");
			 String exam_set= jsonObj.getString("exam_set");
			 int duration=jsonObj.getInt("duration");
			 
			 ClassChapter cc = new ClassChapter();
			 cc.setId(id);
			 cc.setIid(iid);
			 cc.setOrd(ord);
			 cc.setPath(path);
			 cc.setDeep(deep);
			 cc.setTitle(title);
			 cc.setPpt_url(ppt_url);
			 cc.setVideo_url(video_url);
			 cc.setText(text);
			 cc.setCate(cate);
			 cc.setExam_set(exam_set);
			 cc.setDuration(duration);
			 contentService.updateClasschapter(cc);
			 
			 JSONObject retJson = new JSONObject();
				retJson.put("data", "");
				retJson.put("errcode", 0);
				retJson.put("errmsg", "success");
				
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			}catch(Exception e){
				e.printStackTrace();
				JSONObject retJson = new JSONObject();
				retJson.put("data", "");
				retJson.put("errcode", -1);
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				retJson.put("errmsg", sw.toString());
				try {
					PrintWriter printer = response.getWriter();
					printer.write(retJson.toString());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		
	}
	//删除章节记录
	@RequestMapping(value = "/content/deleteClassChapter", method = RequestMethod.POST)
	public void deleteClassChapter(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			
			contentService.deleteClassChapterById(id);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//获取题库管理表查询
	@RequestMapping(value = "/content/getQuestionsManagementList", method = RequestMethod.POST)
	public void getQuestionsManagementList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			int cid = -1;
			String title = null;
			String cpath = null;
			String cate = "y";
			String type = null;
			int pageindex = -1;
			int pagesize = -1;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);

				if (jsonObj.has("cid")&&!jsonObj.getString("cid").equalsIgnoreCase("")) {	
					cid = jsonObj.getInt("cid"); //课程id
				}
				if (jsonObj.has("title")) {
					title = jsonObj.getString("title"); //题目
					if(title.equalsIgnoreCase("")){
						title = null;
					}
				}
				if (jsonObj.has("cpath")) {
					cpath = jsonObj.getString("cpath"); //章节
					if(cpath.equalsIgnoreCase("")){
						cpath = null;
					}
				}
				if (jsonObj.has("cate")) {
					cate = jsonObj.getString("cate"); //是否有效   y是   n否
					if(cate.equalsIgnoreCase("")){
						cate = null;
					}
				}
				if (jsonObj.has("type")) {
					type = jsonObj.getString("type"); //题型   2单选    3多选
					if(type.equalsIgnoreCase("")){
						type = null;
					}
				}
				
				if(jsonObj.has("index")){
					pageindex = jsonObj.getInt("index"); //分页页码
				}
				if(jsonObj.has("pageSize")){
					pagesize = jsonObj.getInt("pageSize"); //分页页码
				}
			
			}
			int total = contentService.getQuestionsCounts(cid, title, cpath, cate, type);
			Page<QuestionsManagement> page = new Page<QuestionsManagement>();
			page.setPageNo(pageindex);
			page.setPageSize(pagesize);
			page.setTotalRecord(total);
			//根据参数条件查询
			List<QuestionsManagement> list = contentService.getQuestionsManagementList(cid, title, cpath, cate, type, page);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", list);
			retJson.put("total", total);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//新增题库管理表记录
	@RequestMapping(value = "/content/insertQuestionsManagement", method = RequestMethod.POST)
	public void insertQuestionsManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			
			int uid = jsonObj.getInt("uid");			//专业管理表的 ord
			String cate = jsonObj.getString("cate");	//是否有效     y是    n否
			int cid = jsonObj.getInt("cid");		//所属课程
			int ccid = 0;
			String type = jsonObj.getString("type");	//题型   2单选     3多选
			String title = jsonObj.getString("title");	//题目名称
			String op1 = jsonObj.getString("op1");		//选项1
			String op2 = jsonObj.getString("op2");		//选项2
			String op3 = jsonObj.getString("op3");		//选项3
			String op4 = jsonObj.getString("op4");		//选项4
			String answer = jsonObj.getString("answer");//答案
			int add_date = (int) (System.currentTimeMillis()/1000);
			double score = 0;	//分数
			String remark = jsonObj.getString("remark");//解题注释
			String cpath = jsonObj.getString("cpath");	//所属章节
			String zl = "1";	//默认
			int anli_id = 0;	//？？
			
			QuestionsManagement qm = new QuestionsManagement();
			qm.setUid(uid);
			qm.setCate(cate);
			qm.setCid(cid);
			qm.setCcid(ccid);
			qm.setType(type);
			qm.setTitle(title);
			qm.setOp1(op1);
			qm.setOp2(op2);
			qm.setOp3(op3);
			qm.setOp4(op4);
			qm.setAnswer(answer);
			qm.setAdd_date(add_date);
			qm.setScore(score);
			qm.setRemark(remark);
			qm.setCpath(cpath);
			qm.setZl(zl);
			qm.setAnli_id(anli_id);
			
			contentService.insertQuestionsManagement(qm);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", qm.getId());
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	@RequestMapping(value = "/content/updateQuestionsManagement", method = RequestMethod.POST)
	public void updateQuestionsManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			int uid = jsonObj.getInt("uid");			//专业管理表的 ord
			String cate = jsonObj.getString("cate");	//是否有效     y是    n否
			int cid = jsonObj.getInt("cid");		//所属课程
			int ccid = 0;
			String type = jsonObj.getString("type");	//题型   2单选     3多选
			String title = jsonObj.getString("title");	//题目名称
			String op1 = jsonObj.getString("op1");		//选项1
			String op2 = jsonObj.getString("op2");		//选项2
			String op3 = jsonObj.getString("op3");		//选项3
			String op4 = jsonObj.getString("op4");		//选项4
			String answer = jsonObj.getString("answer");//答案
			int add_date = 0;
			double score = 0;	//分数
			String remark = jsonObj.getString("remark");//解题注释
			String cpath = jsonObj.getString("cpath");	//所属章节
			String zl = "1";	//默认
			int anli_id = 0;	//？？
			
			QuestionsManagement qm = new QuestionsManagement();
			qm.setId(id);
			qm.setUid(uid);
			qm.setCate(cate);
			qm.setCid(cid);
			qm.setCcid(ccid);
			qm.setType(type);
			qm.setTitle(title);
			qm.setOp1(op1);
			qm.setOp2(op2);
			qm.setOp3(op3);
			qm.setOp4(op4);
			qm.setAnswer(answer);
			qm.setAdd_date(add_date);
			qm.setScore(score);
			qm.setRemark(remark);
			qm.setCpath(cpath);
			qm.setZl(zl);
			qm.setAnli_id(anli_id);
			
			contentService.updateQuestionsManagement(qm);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", qm.getId());
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	//删除题库管理表记录
	@RequestMapping(value = "/content/deleteQuestionsManagement", method = RequestMethod.POST)
	public void deleteQuestionsManagement(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			contentService.deleteQuestionsManagementById(id);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	
	//鉴定辅导列表查询
	@RequestMapping(value = "/content/getAppraisalList", method = RequestMethod.POST)
	public void getAppraisalList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		
		try {
			String no = null;
			String title = null;
			String state = null;
			String path =null;
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
				if (jsonObj.has("no")) {
					no = jsonObj.getString("no");	//编号
				}
				if (jsonObj.has("title")) {
					title = jsonObj.getString("title");//标题
				}
				if (jsonObj.has("state")) {
					state = jsonObj.getString("state");//是否有效    y有效     n无效
				}
				if (jsonObj.has("path")) {
					path = jsonObj.getString("path");	//编号
				}
			}
			
			List<Appraisal> list = contentService.getAppraisalList(no, title, state,path);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", list);
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//新增鉴定辅助记录
	@RequestMapping(value = "/content/insertAppraisal", method = RequestMethod.POST)
	public void insertAppraisal(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int uid = jsonObj.getInt("uid");
			String no = jsonObj.getString("no");
			String path = jsonObj.getString("path");
			String title = jsonObj.getString("title");
			String timeStr = new Date().getTime() + "";
			int add_date = Integer.parseInt(timeStr.substring(0, 10)); 
			String state = jsonObj.getString("state");
			
			Appraisal as = new Appraisal();
			as.setUid(uid);
			as.setNo(no);
			as.setPath(path);
			as.setTitle(title);
			as.setAdd_date(add_date);
			as.setState(state);
			
			contentService.insertAppraisal(as);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", as.getId());
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	//修改鉴定辅助记录
	@RequestMapping(value = "/content/updateAppraisalById", method = RequestMethod.POST)
	public void updateAppraisalById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			int uid = jsonObj.getInt("uid");
			String no = jsonObj.getString("no");
			String path = jsonObj.getString("path");
			String title = jsonObj.getString("title");
			String timeStr = new Date().getTime() + "";
			int add_date = Integer.parseInt(timeStr.substring(0, 10)); 
			String state = jsonObj.getString("state");
			
			Appraisal as = new Appraisal();
			as.setId(id);
			as.setUid(uid);
			as.setNo(no);
			as.setPath(path);
			as.setTitle(title);
			as.setAdd_date(add_date);
			as.setState(state);
			
			contentService.updateAppraisalById(as);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	//删除鉴定辅助记录
	@RequestMapping(value = "/content/deleteAppraisalById", method = RequestMethod.POST)
	public void deleteAppraisalById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			
			contentService.deleteAppraisalById(id);
			
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", 0);
			retJson.put("errmsg", "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(retJson.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject retJson = new JSONObject();
			retJson.put("data", "");
			retJson.put("errcode", -1);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			retJson.put("errmsg", sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(retJson.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//鉴定辅导：节点设置
	@RequestMapping(value = "/content/getAppraisalNodesList", method = RequestMethod.POST)
	public void getAppraisalNodesList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int iid = jsonObj.getInt("iid");	//鉴定辅助表记录的id
			
			List<AppraisalNodes> list = contentService.getAppraisalNodesList(iid);
			
			ResponseJson rj = new ResponseJson(list, 0, "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//新增节点设置
	@RequestMapping(value = "/content/insertAppraisalNodes", method = RequestMethod.POST)
	public void insertAppraisalNodes(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int iid = jsonObj.getInt("iid");	//鉴定辅助表记录的id
			int ord = contentService.getAppraisalNodesMaxOrd();	//顺序号；取当前库表里的最大值
			String path = jsonObj.getString("path");	//默认"0"
			int deep = jsonObj.getInt("deep");			//默认1
			String title = jsonObj.getString("title");	//标题
			String ppt_url = jsonObj.getString("ppt_url");	//PPT
			String video_url = jsonObj.getString("video_url");	//视频
			String text = jsonObj.getString("text");	//默认空
			String video_url1 = jsonObj.getString("video_url1");	//视频1
			String attachment = jsonObj.getString("attachment");	//默认空
			
			AppraisalNodes an = new AppraisalNodes();
			an.setIid(iid);
			an.setOrd(ord);
			an.setPath(path);
			an.setDeep(deep);
			an.setTitle(title);
			an.setPpt_url(ppt_url);
			an.setVideo_url(video_url);
			an.setText(text);
			an.setVideo_url1(video_url1);
			an.setAttachment(attachment);
			
			contentService.insertAppraisalNodes(an);
			
			ResponseJson rj = new ResponseJson(an.getId(), 0, "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//修改节点设置
	@RequestMapping(value = "/content/updateAppraisalNodes", method = RequestMethod.POST)
	public void updateAppraisalNodes(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			int iid = jsonObj.getInt("iid");	//鉴定辅助表记录的id
			int ord = jsonObj.getInt("ord");	//顺序号；取当前库表里的最大值
			String path = jsonObj.getString("path");	//默认"0"
			int deep = jsonObj.getInt("deep");			//默认1
			String title = jsonObj.getString("title");	//标题
			String ppt_url = jsonObj.getString("ppt_url");	//PPT
			String video_url = jsonObj.getString("video_url");	//视频
			String text = jsonObj.getString("text");	//默认空
			String video_url1 = jsonObj.getString("video_url1");	//视频1
			String attachment = jsonObj.getString("attachment");	//默认空
			
			AppraisalNodes an = new AppraisalNodes();
			an.setId(id);
			an.setIid(iid);
			an.setOrd(ord);
			an.setPath(path);
			an.setDeep(deep);
			an.setTitle(title);
			an.setPpt_url(ppt_url);
			an.setVideo_url(video_url);
			an.setText(text);
			an.setVideo_url1(video_url1);
			an.setAttachment(attachment);
			
			contentService.updateAppraisalNodesById(an);
			
			ResponseJson rj = new ResponseJson(an.getId(), 0, "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	
	
	//删除节点设置
	@RequestMapping(value = "/content/deleteAppraisalNodes", method = RequestMethod.POST)
	public void deleteAppraisalNodesById(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) {
		
		try {
			JSONObject jsonObj = JSONObject.fromObject(parameter);
			int id = jsonObj.getInt("id");
			
			contentService.deleteAppraisalNodesById(id);
			
			ResponseJson rj = new ResponseJson(null, 0, "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//模块管理
	@RequestMapping(value = "/content/getmodulelist", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer<List> getModuleList() {
		List<ModuleManagement>  mm = null;
		ResultTransfer<List> rt = new ResultTransfer<List>();
		try{
			mm = contentService.getModuleManagementList();
		}catch(Exception e){
			rt.seterrcode(-1);
		}
			
		rt.setData(mm);
		rt.seterrcode(0);	
		return rt;
	}

	//更新模块
	@RequestMapping(value = "/content/updatemodule/{id}/{priv}", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer updateModule(@PathVariable int id, @PathVariable String priv) {
		ResultTransfer rt = new ResultTransfer();
		ModuleManagement mm = new ModuleManagement();
		mm.setId(id);
		mm.setPaths(priv);
		try{
			contentService.updateModule(mm);
		}catch(Exception e){
			rt.seterrcode(-1);
		}
		rt.seterrcode(0);	
		return rt;
	}
	//试听课程列表查询
	@RequestMapping(value = "/content/getTryClassList", method = RequestMethod.POST)
	public void getTryClassList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter) {
		try {
			if (parameter != null) {
				JSONObject jsonObj = JSONObject.fromObject(parameter);
			}
			
			//SELECT * FROM exam_course_chapter WHERE cate="9"
			//cate = "9" 试听课程章节
			List<ClassChapter> list = contentService.getTryClassList();
			
			ResponseJson rj = new ResponseJson(list, 0, "success");
			
			PrintWriter printer = response.getWriter();
			printer.write(rj.getResponseStr());
			
		} catch (Exception e) {
			e.printStackTrace();
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			ResponseJson rj = new ResponseJson(null, -1, sw.toString());
			try {
				PrintWriter printer = response.getWriter();
				printer.write(rj.getResponseStr());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	

}
