package com.extr.controller;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.extr.verify.VerifyLicense;
import com.extr.verify.LicenseManagerHolder;
import com.extr.controller.domain.Message;

import com.extr.domain.user.Role;
import com.extr.domain.user.User;
import com.extr.domain.userperiod.UserApply;
import com.extr.domain.userperiod.UserPeriod;
import com.extr.security.UserInfo;

import com.extr.service.UserPeriodService;
import com.extr.service.UserService;
import com.extr.util.Page;
import com.extr.util.PagingUtil;
import com.extr.util.ResultTransfer;
import com.extr.util.StandardPasswordEncoderForSha1;
import com.extr.util.TokenTransfer;
import com.extr.util.TokenUtils;
import com.extr.util.UserTransfer;
@Controller
public class UserController {

	public static final String SUCCESS_MESSAGE = "success";
	public static final String ERROR_MESSAGE = "failed";

	@Autowired
	private UserService userService;
	@Autowired
	private UserPeriodService userPeriodService;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	@Autowired
	public UserDetailsService userDetailsService;


	
	@RequestMapping(value = "/authenticate/{username}/{password}", method = RequestMethod.POST)
	
	public @ResponseBody ResultTransfer<TokenTransfer> authenticate(@PathVariable String username, @PathVariable String password)
	{
		ResultTransfer<TokenTransfer> rt =new ResultTransfer<TokenTransfer>();
//		VerifyLicense vLicense = new VerifyLicense();
//		//鑾峰彇鍙傛暟
//		vLicense.setParam("./param.properties");
//		//鐢熸垚璇佷功
//		if(vLicense.verify()){
			String sh1Password = password ;
			PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
			String result = passwordEncoder.encode(sh1Password);
			UserInfo userDetails = (UserInfo) this.userDetailsService.loadUserByUsername(username);
			
			if(!passwordEncoder.matches(userDetails.getPassword(), result) || "0".equals(userDetails.getEnabled()) || userDetails == null){
				//System.out.println("用户名或密码错误！");
				throw new AuthenticationServiceException("用户名或密码错误！");
			}
			
			if(userDetails.getUser().getGid()==1 && userDetails.getUser().getLive().equalsIgnoreCase("n") ){
				throw new AuthenticationServiceException("用户没有激活！");
			}
			
			Authentication authentication = null;
			
			try{
				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(username, password);
				authentication = this.authManager.authenticate(authenticationToken);
			}catch(Exception ex){
				
				rt.setData(new TokenTransfer("ERROR"));
				rt.seterrcode(-1);
				return  rt;
			}
				
			
			SecurityContextHolder.getContext().setAuthentication(authentication);

			/*
			 * Reload user as password of authentication principal will be null after authorization and
			 * password is needed for token generation
			 */
			rt.setData(new TokenTransfer(TokenUtils.createToken(userDetails)));
			rt.seterrcode(0);
			return rt;
//		}else{
//				rt.setData(new TokenTransfer("ERROR"));
//				rt.seterrcode(-2);
//				return  rt;
//			
//		}
		
	
	}

	@RequestMapping(value = "/userauthority", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer<UserTransfer> getUser() throws Exception
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		ResultTransfer<UserTransfer> rt =new ResultTransfer<UserTransfer>();
		
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			
			Map<String, Boolean> roles = new HashMap<String, Boolean>();
			roles.put("noneauthority", Boolean.TRUE);
			rt.seterrcode(-1);
			rt.setData(new UserTransfer("anonymousUser", roles,null,null,0,0));
		}
		
		try{
			UserInfo userDetails = (UserInfo) principal;
			User user = new User();
			user.setId(userDetails.getUser().getId());
			if(System.currentTimeMillis()/1000>userDetails.getUser().getRegEnd()&&!userDetails.getUser().getState().equals("x")&&!userDetails.getUser().getState().equals("j")){
				
				user.setState("c");
				userDetails.getUser().setState("c");
				
			} 
			user.setLastactivity((int) (System.currentTimeMillis()/1000));

			int expireDays = 0;
			if(userDetails.getUser().getState().equalsIgnoreCase("z")){
				expireDays = (int) ((System.currentTimeMillis()/1000 -userDetails.getUser().getLastactivity())/34560);
				//user.setLastactivity((int) (System.currentTimeMillis()/1000));
				user.setState("y");
			}
			
			userService.updateUser(user, null);
			rt.seterrcode(0);
			rt.setData(new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails), userDetails.getUser(),userDetails.getRolesName(),userDetails.getUser().getGid(),expireDays));
		}catch (Exception e){
			rt.seterrcode(-1);
			rt.setData(null);
		}
		

		return rt;
	}
	
	private Map<String, Boolean> createRoleMap(UserDetails userDetails)
	{
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}
	
	
	//角色列表
	@RequestMapping(value = "/rolelist/{index}", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer<List> getUserGroup(@PathVariable int index) throws Exception{
//		userService.get
		ResultTransfer<List> rt =new ResultTransfer<List>();

		Page<Role> page = new Page<Role>();
		page.setPageNo(index);
		page.setPageSize(200);
		
		List<Role> listrole = userService.getAllRoleList();
		rt.setData(listrole);
		rt.seterrcode(0);
		
		return rt;
	}
	
	//所有管理员
	
	@RequestMapping(value = "/roleadminlist/{index}/{roleid}", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer<List> getAdminList(@PathVariable int index, @PathVariable int roleid) throws Exception{
		
		
		ResultTransfer<List> rt =new ResultTransfer<List>();
		Page<User> page = new Page<User>();
		page.setPageNo(index);
		page.setPageSize(20);
		
		List<User> listrole = userService.getUserListByRoleId(roleid,page);
		rt.setData(listrole);
		rt.seterrcode(0);
		
		return rt;
		
	}
	@RequestMapping(value = "/insertrole", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer insertRole(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) throws Exception{
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		Role role = new Role();
		role.setAdd_date((int) (System.currentTimeMillis()/1000));
		role.setAuthority(jsonObj.getString("authority"));
		role.setName(jsonObj.getString("name"));
		role.setPriv(jsonObj.getString("priv"));
		role.setCode(jsonObj.getString("code"));
	
		userService.insertRole(role);
		ResultTransfer rt =new ResultTransfer();
		rt.seterrcode(0);
		
		return rt;
		
	}
	
	@RequestMapping(value = "/updaterole", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer updateRole(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = true) String parameter) throws Exception{
		JSONObject jsonObj = JSONObject.fromObject(parameter);
		int id = jsonObj.getInt("id");
		Role role = new Role();
		role.setId(id);
		role.setAdd_date((int)( System.currentTimeMillis()/1000));
		role.setAuthority(jsonObj.getString("authority"));
		role.setName(jsonObj.getString("name"));
		role.setPriv(jsonObj.getString("priv"));
		role.setCode(jsonObj.getString("code"));
	
		userService.updateRole(role);
		ResultTransfer rt =new ResultTransfer();
		rt.seterrcode(0);
		
		return rt;
		
	}
	
	
	
	@RequestMapping(value = "/deleterole/{id}", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer deleteRole(@PathVariable int id) throws Exception{
		userService.deleteRole(id);
		
		ResultTransfer rt = new ResultTransfer();
		rt.seterrcode(0);
		return rt;
		
	}
	
	@RequestMapping(value = "/getauthoritylist/{id}", method = RequestMethod.POST)
	public @ResponseBody ResultTransfer getauthoritylist(@PathVariable int id) throws Exception{
		Role role = userService.getauthoritylist(id);
		ResultTransfer rt = new ResultTransfer();
		rt.setData(role);
		rt.seterrcode(0);
		return rt;
		
	}
	
	@RequestMapping(value = "/authenticate/changepassword", method = RequestMethod.POST)
			
	public @ResponseBody ResultTransfer changePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);

		ResultTransfer rt = new ResultTransfer();
		String username = null;
		String password = null;
		try{
			User user = (User) JSONObject.toBean(jsonObj,User.class);  
			password = user.getPassword();
			if(password!=null&&!password.equals("")){
				PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
				String resultPassword = passwordEncoder.encode(password);
				user.setPassword(resultPassword);
			}else{
				user.setPassword(null);
			}
	
			user.setUsername(user.getUsername());
			String userName = jsonObj.getString("username");
			
//			if(password!=null&&!password.equals("")){
//				PasswordEncoder passwordEncoder = new StandardPasswordEncoderForSha1();
//				
//				password = jsonObj.getString("password");
//				password = passwordEncoder.encode(password);
//			}
			

			String realName = jsonObj.getString("name");
			String gender = jsonObj.getString("gender");
			String telephone = jsonObj.getString("phone");
			String mobile = jsonObj.getString("cellphone");
			String mail = jsonObj.getString("email");
			String idCard = jsonObj.getString("zjhm");
			Date birthday = null;
			//获取生日
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayStr = jsonObj.getString("birthday");
			birthday = format.parse(birthdayStr);
			int uid = jsonObj.getInt("uid");
			String province = (String) jsonObj.get("province");
			String manageUninCode = (String) jsonObj.get("unit");
			String postAddress = (String) jsonObj.get("addr");
			String postCode = (String) jsonObj.get("postcode");
			String mark = (String) jsonObj.get("remark");
			String roleStr = jsonObj.getString("gid");
			int roleId = Integer.parseInt(roleStr);
			String signSpecial = jsonObj.getString("signSpecial");
			int gid =  Integer.parseInt(roleStr);
			String live = jsonObj.getString("live");
			user.setSignSpecial(signSpecial);
			//user.setPassword(password);
			user.setUsername(userName);
			user.setZjhm(idCard);
			user.setName(realName);
			user.setGender(gender);
			user.setPhone(telephone);
			user.setCellphone(mobile);
			user.setEmail(mail);
			user.setBirthday(birthday);
			user.setProvince(province);
			user.setUnit(manageUninCode);
			user.setAddr(postAddress);
			user.setPostcode(postCode);
			user.setUid(uid);
			user.setLive(live);
			user.setGid(gid);
			userService.updateUser(user, null);
			userService.updateRUserRole(user.getId(), user.getGid());
			rt.seterrcode(0);
		}catch(Exception e){
			e.printStackTrace();
			rt.seterrcode(-1);
		}
			
		return rt;
	}
	
	
	@RequestMapping(value = "/authenticate/updateuser", method = RequestMethod.POST)
	
	public @ResponseBody ResultTransfer updateuser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);

		ResultTransfer rt = new ResultTransfer();
		String username = null;
		String password = null;
		try{
			User user = (User) JSONObject.toBean(jsonObj,User.class);  

			String userName = jsonObj.getString("username");
			String passWord = jsonObj.getString("password");

			String realName = jsonObj.getString("name");
			String gender = jsonObj.getString("gender");
			String telephone = jsonObj.getString("phone");
			String mobile = jsonObj.getString("cellphone");
			String mail = jsonObj.getString("email");
			String idCard = jsonObj.getString("zjhm");
			Date birthday = null;
			//获取生日
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayStr = jsonObj.getString("birthday");
			birthday = format.parse(birthdayStr);
			
			String province = (String) jsonObj.get("province");
			String manageUninCode = (String) jsonObj.get("unit");
			String postAddress = (String) jsonObj.get("addr");
			String postCode = (String) jsonObj.get("postcode");
			String mark = (String) jsonObj.get("remark");
			String roleStr = jsonObj.getString("gid");
			
			
			int roleId = Integer.parseInt(roleStr);
		
			user.setUsername(userName);
			user.setName(realName);
			user.setGender(gender);
			user.setPhone(telephone);
			user.setCellphone(mobile);
			user.setEmail(mail);
			user.setBirthday(birthday);
			user.setProvince(province);
			user.setUnit(manageUninCode);
			user.setAddr(postAddress);
			user.setPostcode(postCode);

			
			userService.updateUser(user, null);
			userService.updateRUserRole(user.getId(), user.getGid());
			rt.seterrcode(0);
		}catch(Exception e){
			e.printStackTrace();
			rt.seterrcode(-1);
		}
			
		return rt;
	}
	
	@RequestMapping(value = "/user/changusersate", method = RequestMethod.POST)
	public  @ResponseBody ResultTransfer inituserperiod(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter){
		JSONObject jsonObj = JSONObject.fromObject(parameter);

		ResultTransfer rt = new ResultTransfer();
		try {
		User user = (User) JSONObject.toBean(jsonObj,User.class);  
		int id = jsonObj.getInt("id");
		String userName = jsonObj.getString("username");
		String passWord = jsonObj.getString("password");

		String realName = jsonObj.getString("name");
		String gender = jsonObj.getString("gender");
		String telephone = jsonObj.getString("phone");
		String mobile = jsonObj.getString("cellphone");
		String mail = jsonObj.getString("email");
		String idCard = jsonObj.getString("zjhm");
		int regDate = jsonObj.getInt("regDate");
		int regEnd = jsonObj.getInt("regEnd");
		Date birthday = null;
		//获取生日
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayStr = jsonObj.getString("birthday");
		
			birthday = format.parse(birthdayStr);
		
		
		String province = (String) jsonObj.get("province");
		String manageUninCode = (String) jsonObj.get("unit");
		String postAddress = (String) jsonObj.get("addr");
		String postCode = (String) jsonObj.get("postcode");
		String mark = (String) jsonObj.get("remark");
		String roleStr = jsonObj.getString("gid");

		int roleId = Integer.parseInt(roleStr);
	
		user.setId(id);
		user.setUsername(userName);
		user.setName(realName);
		user.setGender(gender);
		user.setPhone(telephone);
		user.setCellphone(mobile);
		user.setEmail(mail);
		user.setBirthday(birthday);
		user.setProvince(province);
		user.setUnit(manageUninCode);
		user.setAddr(postAddress);
		user.setPostcode(postCode);
		user.setRegDate(regDate);
		user.setRegEnd(regEnd);
		UserPeriod userPeriod = new UserPeriod();
		int qStartDate = jsonObj.has("qStartDate")?jsonObj.getInt("qStartDate"):0;
		int qEndDate =jsonObj.has("qEndDate")? jsonObj.getInt("qEndDate"):0;
		userPeriod.setStartdate(qStartDate);
		userPeriod.setEnddate(qEndDate);
		userPeriod.setState(user.getState());
		userPeriod.setUid(user.getId());
	
		switch(jsonObj.getString("state")){
			case "y":
				
				
				userService.updateUser(user, null);
				
				init_user_period(user,user.getRegDate()+user.getDuration());
				update_user_state_no_period_accordingly(user.getUid(),"y");
//				userPeriodService.deleteUserPeriod(user.getId());
//				userPeriodService.insertUserPeriod(userPeriod);
				break;
			case "x":
//				int qStartDate1 = jsonObj.getInt("qStartDate");
//				int qEndDate1 = jsonObj.getInt("qEndDate");
//				//开始时间,结束时间
//				userPeriod.setStartdate(qStartDate1);
//				userPeriod.setEnddate(qStartDate1);
//				userPeriod.setState(user.getState());
//				userPeriod.setUid(user.getId());
				UserPeriod ud = userPeriodService.getUserPeriod(userPeriod);
//				System.out.println("----------------------"+System.currentTimeMillis()/1000);
				if(ud!=null){
					UserApply userApply = new UserApply();
					userApply.setApplydate((int) (System.currentTimeMillis()/1000));
					userApply.setApplystate("x");
					int approve = jsonObj.getInt("approve");
					userApply.setApprove(0);
					userApply.setComment("管理员操作的休学");
					userApply.setEnddate(qEndDate);
					userApply.setStartdate(qStartDate);
					userApply.setUserid(user.getId());
					userPeriodService.insertUserApply(userApply);
					/////////////////////////////////////
					UserApply ua1 = userPeriodService.getUserApplyById(userApply);
					int applyId = userApply.getId();
					UserApply userApply1 = new UserApply();
					userApply1.setId(applyId);
					userApply1.setStartdate(-1);
					userApply1.setEnddate(-1);
					userApply1.setUserid(-1);
					UserApply ua = userPeriodService.getUserApplyById(userApply1);
					if(ua.getApprove() > 0){
						rt.setData("当前申请已经被处理，无法被再次处理！");
						rt.seterrcode(-1);
						return rt;
					}else{
						//if(ua.getApprove() ==1){
							//pause(user.getId(),qStartDate, qEndDate,user.getState());
						//}
						
						UserPeriod userPeriodx = new UserPeriod();
						userPeriodx.setStartdate(qStartDate);
						userPeriodx.setUid(user.getId());
						userPeriodx.setEnddate(qEndDate);
						userPeriodx.setState("x");
						UserPeriod up = userPeriodService.getUserPeriod(userPeriodx);
						if(up!=null&&up.getStartdate()>0){
							 int $end_date = up.getEnddate();
					         String $state = up.getState();
					         int $span = qEndDate - qStartDate;
					         int $end_date2 = $end_date+$span;
					         UserPeriod userP = new UserPeriod();
					         userP.setUid(user.getId());
					         userP.setStartdate(qStartDate + $span);
					         userP.setEnddate(qEndDate + $span);
					         userP.setSpan($span);
					        
					         userPeriodService.updateUserPeriod(userP);
					         UserPeriod userP1 = new UserPeriod();
					         userP1.setEnddate(qStartDate);
					         userP1.setId(up.getId());
					         userPeriodService.updateUserPeriod(userP1);
					         UserPeriod userP2 = new UserPeriod();
					         userP2.setUid(user.getId());
					         userP2.setStartdate(qStartDate);
					         userP2.setEnddate(qEndDate);
					         userP2.setStartdate(qStartDate);
					         userP2.setState("x");
					         userPeriodService.insertUserPeriod(userP2);
					         
					         UserPeriod userP3 = new UserPeriod();
					         userP3.setUid(user.getId());
					         userP3.setStartdate(qEndDate);
					         userP3.setEnddate($end_date2);
					         userP3.setState(up.getState());
					         userPeriodService.insertUserPeriod(userP3);
					         
					         update_user_state_no_period_accordingly(user.getId(), "x");
					         User userx = new User();
					         //userx.setUid(user.getId());
					         long num = System.currentTimeMillis()/1000;
					         if(num >= qStartDate && num <= qStartDate + 24*60*60){
					        	 user.setState("x");
					         }
					         userx.setId(user.getId());
					         update_user_valid_period(userx);
						}
						
						
						
							ua.setApprove(1);
							userPeriodService.updateUserApply(ua);
					}
					
				}else{
					rt.setData("休学时间必须在有效期内！");
					rt.seterrcode(-1);
					return rt;
				}
				
				break;
			case "q":
				userService.updateUser(user, null);
				break;
			case "t":
				userService.updateUser(user, null);
				break;
			case "j":
				userService.updateUser(user, null);
				break;
			case "e":
				UserApply userApply = new UserApply();
				userApply.setApplydate((int) (System.currentTimeMillis()/1000));
				userApply.setApplystate("e");
				
				userApply.setApprove(0);
				userApply.setComment("管理员操作的延学");
				userApply.setEnddate(qEndDate);
				userApply.setStartdate(qStartDate);
				userApply.setUserid(user.getId());
				userPeriodService.insertUserApply(userApply);
				
				UserApply ua1 = userPeriodService.getUserApplyById(userApply);
				
				int applyId = userApply.getId();
				UserApply userApply1 = new UserApply();
				userApply1.setId(applyId);
				userApply1.setStartdate(-1);
				userApply1.setEnddate(-1);
				userApply1.setUserid(-1);
				UserApply ua = userPeriodService.getUserApplyById(userApply1);
				
				   UserPeriod usd2 = new UserPeriod();
			         usd2.setState("e");
			         usd2.setStartdate(qStartDate);
			         usd2.setEnddate(qEndDate);
			         usd2.setUid(user.getId());
			         userPeriodService.insertUserPeriod(usd2);
			         user.setState("c");
			         long num = System.currentTimeMillis()/1000;
			         if(num >= qStartDate && num <= qStartDate + 24*60*60){
			        	 user.setState("e");
			         }
			         userService.updateUser(user, null);
			         update_user_valid_period(user);
			         
			         ua.setApprove(1);
						userPeriodService.updateUserApply(ua);
				break;
			case "h":
				
				UserApply userApply4 = new UserApply();
				userApply4.setApplydate((int) (System.currentTimeMillis()/1000));
				userApply4.setApplystate("h");
				
				userApply4.setApprove(0);
				userApply4.setComment("管理员操作的复学");
				userApply4.setEnddate(qEndDate);
				userApply4.setStartdate(-1);
				userApply4.setUserid(user.getId());
				  UserPeriod usdh = (UserPeriod)userPeriod.clone();
				  usdh.setUid(user.getId());
				  usdh.setEnddate((int) (System.currentTimeMillis()/1000));
				  usdh.setStartdate((int) (System.currentTimeMillis()/1000));
				  usdh.setState("x");
				if(userPeriodService.getPauseNumber(usdh)>0){
					userPeriodService.insertUserApply(userApply4);
				}
				else{
					rt.seterrcode(-1);
					rt.setData("复学时间必须位于某段已生效的休学时段内");
					return rt;
				}
		
				
				UserPeriod userP5 = new UserPeriod();
				userP5.setStartdate(qStartDate);
				userP5.setUid(user.getId());
				userP5.setEnddate(qEndDate);
				userP5.setState("x");
				UserPeriod up6 = userPeriodService.getUserPeriodcount(userP5);
				if(up6!=null && up6.getStartdate() > 0){
					int id1 = up6.getId();
					int $end_date = up6.getEnddate();
					int $span = $end_date-qEndDate;
					UserPeriod up7 = new UserPeriod();
					up7.setEnddate(qEndDate);
					up7.setSpan(0);
					up7.setId(id1);
					userPeriodService.updateUserPeriod(up7);
					UserPeriod up8 = new UserPeriod();
					up8.setSpan($span);
					up8.setUid(user.getId());
					up8.setStartdate(qEndDate);
					userPeriodService.updateUserPeriod1(up8);
					 update_user_state_no_period_accordingly(user.getId(), "y");
			         User user1 = new User();
			         //user1.setUid(user.getId());
			         long num1 = System.currentTimeMillis()/1000;
			         if(num1 >= qStartDate && num1 <= qStartDate + 24*60*60){
			        	 user1.setState("y");
			         }
			         user1.setId(user.getId());
			         update_user_valid_period(user1);
			         
			         userApply4.setApprove(1);
						userPeriodService.updateUserApply(userApply4);
				}
				break;
				
			
		}
		rt.seterrcode(0);
		
		} catch (ParseException e) {
			
			e.printStackTrace();
			rt.seterrcode(-1);
		}
		
		return rt;
		
		
	}
	
	
	
	@RequestMapping(value = "/user/changvalidperiod", method = RequestMethod.POST)
	public  @ResponseBody ResultTransfer changvalidperiod(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "parameter", required = false) String parameter){
		
		JSONObject jsonObj = JSONObject.fromObject(parameter);

		ResultTransfer rt = new ResultTransfer();
	
		User user = (User) JSONObject.toBean(jsonObj,User.class);  
		int regDate = 0;
		int duration = 0;
		if(user.getRegDate()==0){
			regDate= (int) (System.currentTimeMillis()/1000);
			duration = user.getQStartDate() - regDate;
			user.setRegDate(regDate);
			user.setDuration(duration);
			
		}else{
			regDate = user.getRegDate();
			int end = user.getRegEnd();
			duration =  end - regDate;
			user.setDuration(duration);
			
		}
		userService.updateUser(user, null);
		
		init_user_period(user, regDate+duration);
		
		update_user_state_no_period_accordingly(user.getId(),user.getState());
		rt.seterrcode(0);
		return rt;
		
	}
	
	/***
	 * 休学
	 * @param userPeriod
	 * @return
	 */
	private String pause(int userid, int startdate, int enddate,String applystate){
		
//		UserPeriod ud = userPeriodService.getUserPeriod(userPeriod);
//		if(ud != null){
//		    int id = ud.getId();
//	         //int start_date = userPeriod.getStartdate();
//	         int end_date = endDate;
//	         String state = ud.getState();
//	         int span = endDate - startDate;
//	         int end_date2= end_date+span;
//	         userPeriod.setStartdate(startDate + span);
//	         userPeriod.setEnddate(endDate);
//	         UserPeriod usd = (UserPeriod)userPeriod.clone();
//	         usd.setId(-1);
//	         userPeriodService.updateUserPeriod(usd);
//	         UserPeriod usd1 = (UserPeriod)userPeriod.clone();
//	         usd1.setUid(-1);
//	         usd1.setStartdate(-1);
//	         usd1.setEnddate(end_date);
//	         usd1.setState(null);
//	         userPeriodService.updateUserPeriod(usd1);
//	        
//	         UserPeriod usd2 = (UserPeriod)userPeriod.clone();
//	         usd2.setState("x");
//	         usd2.setStartdate(startDate);
//	         usd2.setEnddate(end_date);
//	         
//	         userPeriodService.insertUserPeriod(usd2);
//	         
//	         UserPeriod usd3 = (UserPeriod)userPeriod.clone();
//	         usd3.setStartdate(end_date);
//	         usd3.setEnddate(end_date2);
//	        
//	         usd3.setState(state);
//	         userPeriodService.insertUserPeriod(usd3);
//	         
//	         update_user_state_no_period_accordingly(userPeriod.getUid(),changestate);
//		}
//		
//     
//		return null;
		
		
		//休学
		UserPeriod userPeriod = new UserPeriod();
		userPeriod.setStartdate(startdate);
		userPeriod.setUid(userid);
		userPeriod.setEnddate(enddate);
		userPeriod.setState(applystate);
		UserPeriod up = userPeriodService.getUserPeriod(userPeriod);
		if(up.getStartdate()>0){
			 int $end_date = up.getEnddate();
	         String $state = up.getState();
	         int $span = enddate - startdate;
	         int $end_date2 = $end_date+$span;
	         UserPeriod userP = new UserPeriod();
	         userP.setUid(userid);
	         userP.setStartdate(startdate + $span);
	         userP.setEnddate(enddate + $span);
	         userP.setSpan($span);
	        
	         userPeriodService.updateUserPeriod(userP);
	         UserPeriod userP1 = new UserPeriod();
	         userP1.setEnddate(startdate);
	         userP1.setId(up.getId());
	         userPeriodService.updateUserPeriod(userP1);
	         UserPeriod userP2 = new UserPeriod();
	         userP2.setUid(userid);
	         userP2.setStartdate(startdate);
	         userP2.setEnddate(enddate);
	         userP2.setStartdate(startdate);
	         userP2.setState(applystate);
	         userPeriodService.insertUserPeriod(userP2);
	         
	         UserPeriod userP3 = new UserPeriod();
	         userP3.setUid(userid);
	         userP3.setStartdate(enddate);
	         userP3.setEnddate($end_date2);
	         userP3.setState(up.getState());
	         userPeriodService.insertUserPeriod(userP3);
	         
	         update_user_state_no_period_accordingly(userid, applystate);
	         User user = new User();
	         user.setUid(userid);

	         update_user_valid_period(user);
		}
		
		return null;
	}
	/*
     * 根据计算出来的学员状态更新数据库中学员状态字段
     */
	
    public void update_user_state_no_period_accordingly(int user_id, String changestate) {
   	
//    	User user = userService.getUserById(user_id);
// //   	String lastState = user.getState();
////    	String currentState = query_user_real_state(user);
////    	 if (!lastState.equalsIgnoreCase(currentState)) {
//    		 user.setState(changestate);
//             userService.updateUser(user, null);
////         }
//    	 
//    	 update_user_valid_period(user);
     	User user = userService.getUserById(user_id);
    	String lastState = user.getState();
    	String currentState = query_user_real_state(user);
    	 if (!lastState.equalsIgnoreCase(currentState)) {
    		 user.setState(changestate);
             userService.updateUser(user, null);
         }
    	 
    	 update_user_valid_period(user);
    }
    
    private String query_user_real_state(User user)
    {
        int curr_span = (int) (System.currentTimeMillis()/1000);

        if (user.getState().equalsIgnoreCase("q")) {
            //退学用户
            return "q";
        } else {

            if (curr_span <user.getRegDate() || user.getDuration() == 0) {
                // 还没到有效时间，未开通学员
                return "w";
            } else {
                int userid = user.getId();
                
                UserPeriod usd = new UserPeriod();
                usd.setUid(userid);
                usd.setStartdate(curr_span);
                usd.setEnddate(curr_span);               
                UserPeriod ud = userPeriodService.getUserPeriod(usd);
                
                if(ud!=null)
                {
                    if(ud.getState().equalsIgnoreCase("y"))
                    {
                        
                        if (curr_span - user.getLastactivity()> 90 * 86400) 
                            return "z";
                       
                        if(user.getIsTempUser() == 0) 
                            return "y";
                        else 
                            return "t";
                    }
                    return ud.getState();
                }
                return "c";
            }
        }
    }
    
    public void update_user_valid_period(User user)
    {
        // 更新缓存在reg_end字段中的有效时间
        int reg_end = userPeriodService.getValidPeriodEnd(user.getId());
        if(reg_end>0)
        {
        	user.setRegEnd(reg_end);
        	userService.updateUser(user, null);
        }
      
    }
    
    public void init_user_period(User user, int endDate){
    	UserPeriod userPeriod = new UserPeriod();
		
		userPeriod.setStartdate(user.getRegDate());
		userPeriod.setEnddate(endDate);
		userPeriod.setState("y");
		userPeriod.setUid(user.getId());
    	
    	
    	userPeriodService.deleteUserPeriod(user.getId());
    	userPeriodService.insertUserPeriod(userPeriod);
    	UserApply ua = new UserApply();
    	ua.setUserid(user.getId());
    	ua.setApprove(1);
    	List<UserApply> ualist = userPeriodService.getUserApplyList(ua);
    	for(int i =0; i<ualist.size(); i ++ ){
    		UserApply userapply = (UserApply)ualist.get(i);
    		int applystartDate = userapply.getStartdate();
    		int applyendDate = userapply.getEnddate();
    		int applyuserid = userapply.getUserid();
    		switch(userapply.getApplystate()){
    			case "x":
    				pause(user.getId(),applystartDate,applyendDate,"x");
    				break;
    			case "h":
    				
    				UserApply userApply4 = new UserApply();
    				userApply4.setApplydate((int) (System.currentTimeMillis()/1000));
    				userApply4.setApplystate("h");
    				
    				userApply4.setApprove(0);
    				userApply4.setComment("管理员操作的复学");
    				userApply4.setEnddate(user.getQEndDate());
    				userApply4.setStartdate(-1);
    				userApply4.setUserid(user.getId());
    				  UserPeriod usdh = (UserPeriod)userPeriod.clone();
    				  usdh.setUid(user.getId());
    				  usdh.setEnddate((int) (System.currentTimeMillis()/1000));
    				  usdh.setStartdate((int) (System.currentTimeMillis()/1000));
    				  usdh.setState("x");
    				if(userPeriodService.getPauseNumber(usdh)>0){
    					userPeriodService.insertUserApply(userApply4);
    				}
    			
    				
    				int applyId1 = userApply4.getId();
    				UserApply userApply11 = new UserApply();
    				userApply11.setId(applyId1);
    				userApply11.setStartdate(-1);
    				userApply11.setEnddate(-1);
    				userApply11.setUserid(-1);
    				UserApply ua3 = userPeriodService.getUserApplyById(userApply11);
    				
    				   UserPeriod usd5 = new UserPeriod();
    			         usd5.setState("x");
    			         usd5.setStartdate(ua3.getEnddate());
    			         usd5.setEnddate(ua3.getEnddate());
    			         
    			         //userPeriod.setUid(user.getId());
    			         UserPeriod udd = userPeriodService.getUserPeriod(usd5);
    			         UserPeriod usd6 = (UserPeriod) usd5.clone();
    			         usd6.setStartdate(-1);
    			         userPeriodService.updateUserPeriod(usd6);
    			         UserPeriod usd7 = (UserPeriod) usd5.clone();
    			         int span = usd7.getEnddate() - usd7.getStartdate();
    			         usd7.setStartdate(usd7.getStartdate()-span);
    			         usd7.setEnddate(usd7.getEnddate()-span);
    			         userService.updateUser(user, null);
    			         update_user_valid_period(user);
    				
    			         
    			         
    			         ua3.setApprove(1);
    						userPeriodService.updateUserApply(ua3);
    				
    				break;
    				
    			case "e":
    				
    				UserApply userApply = new UserApply();
    				userApply.setApplydate((int) (System.currentTimeMillis()/1000));
    				userApply.setApplystate("e");
    				
    				userApply.setApprove(0);
    				userApply.setComment("管理员操作的延学");
    				userApply.setEnddate(user.getQEndDate());
    				userApply.setStartdate(user.getQStartDate());
    				userApply.setUserid(user.getId());
    				userPeriodService.insertUserApply(userApply);
    				
    				UserApply ua1 = userPeriodService.getUserApplyById(userApply);
    				
    				int applyId = userApply.getId();
    				UserApply userApply1 = new UserApply();
    				userApply1.setId(applyId);
    				userApply1.setStartdate(-1);
    				userApply1.setEnddate(-1);
    				userApply1.setUserid(-1);
    				UserApply uapply = userPeriodService.getUserApplyById(userApply1);
    				
    				   UserPeriod usd2 = new UserPeriod();
    			         usd2.setState("e");
    			         usd2.setStartdate(user.getQStartDate());
    			         usd2.setEnddate(user.getQEndDate());
    			         usd2.setUid(user.getId());
    			         
    			         UserPeriod usd9 = new UserPeriod();
    			         usd9.setState("x");
    			  
    			         usd9.setUid(user.getId());
    			         
    			         userPeriodService.updateUserPeriod(usd9);
    			         userPeriodService.insertUserPeriod(usd2);
    				
    			         userService.updateUser(user, null);
    			         
    			         update_user_valid_period(user);
    			         
    			         uapply.setApprove(1);
    						userPeriodService.updateUserApply(uapply);
    				
    				break;
    		}
    		
    
    	}
    }
    
}
