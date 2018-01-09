/*    */ package com.smarthome.imcp.controller;
/*    */ 
		 import com.smarthome.imcp.common.GlobalMethod;
		 import com.smarthome.imcp.common.MailUtil;
/*    */ import com.smarthome.imcp.common.Md5;
import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoDevice;
		 import com.smarthome.imcp.dao.model.bo.BoHostDevice;
         import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
         import com.smarthome.imcp.dao.model.bo.BoUsers;
		 import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*    */ import com.smarthome.imcp.dao.model.system.SysUser;
		 import com.smarthome.imcp.exception.BusinessException;
/*    */ import com.smarthome.imcp.secur.CurrentUser;
		 import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
		 import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
		 import com.smarthome.imcp.service.bo.BoInfraredPartServiceIface;
		 import com.smarthome.imcp.service.bo.BoUsersValidationServiceIface;
		 import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*    */ import com.smarthome.imcp.service.secur.SecurServiceIface;
/*    */ import com.smarthome.imcp.service.system.SysUserServiceIface;
		 import com.sun.star.sync.SyncAction;
		 import java.io.IOException;
/*    */ import java.io.Serializable;
		 import java.util.ArrayList;
		 import java.util.HashMap;
		 import java.util.List;
		 import java.util.Map;
		 import javax.mail.MessagingException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
//邮箱发送验证码
//		import org.apache.commons.mail.EmailException;
//		import org.apache.commons.mail.HtmlEmail;
         //短信验证码需要的包
		 import org.apache.commons.httpclient.HttpClient;
		 import org.apache.commons.httpclient.HttpException;
		 import org.apache.commons.httpclient.NameValuePair;  
		 import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.dom4j.Document;  
		 import org.dom4j.DocumentException;  
		 import org.dom4j.DocumentHelper;  
		 import org.dom4j.Element; 
/*    */ 
		 @Controller
/*    */ public class LoginController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SecurServiceIface securService;
/*    */ 
/*    */   @Autowired
/*    */   private SysUserServiceIface<SysUser, Serializable> sysUserService;

		   @Autowired
/*    */   private BoUserssServiceIface<BoUsers, Serializable> boUserssService;
           
		  @Autowired
          private BoUsersValidationServiceIface<BoUsersValidation, Serializable> boUsersValidationService;
/*    */
		  @Autowired
		  private BoDeviceServiceIface <BoDevice, Serializable> boDeviceService;
		  
		  @Autowired
		  private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
		  
		  @Autowired
		  private BoInfraredPartServiceIface<BoInfraredPart, Serializable> boInfraredPartService;
		  
//           private RequestJson requestJson;
           //new 短信验证码
           private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";  
           
		  
/*    */   @RequestMapping({"login.do"})
//           @RequestMapping(value={"login.do"},produces="application/json;charset=UTF-8")
		   @ResponseBody
		   public int login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd, HttpServletRequest request)
   		{   
		    int UserID=0;
			 System.out.println("loginName="+loginName+",loginPwd="+loginPwd);
//			 Md5 md5 = new Md5();
//			 System.out.println("loginPwd=== 63A9F0EA7BB98050796B649E85481845="+md5.getMD5ofStr(loginPwd));
//			 SysUser sysUser = (SysUser)this.sysUserService.checkUser(loginName, md5.getMD5ofStr(loginPwd));//加密方式-32位大  
			//先给输入的明文密码加密，然后再与数据库表取出来的数据比较
		    String hashAlgorithmName = "MD5";
			Object credentials = loginPwd;
			Object salt = ByteSource.Util.bytes(loginName);;
			int hashIterations = 1024;
			Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
			String userPwd=result.toString();
			////////////////////////////////shiro加密结束/////////////////////////////////////////////////
			//认证
			Subject currentUser = SecurityUtils.getSubject();
			System.out.println("currentUser:"+currentUser);
			if (!currentUser.isAuthenticated()) {//判断是不是已经认证的
		    	// 把用户名和密码封装为 UsernamePasswordToken 对象
//				System.out.println("userPwd:"+userPwd);
		        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);//明文密码
		        System.out.println("token:"+token);
		        // rememberme
		        token.setRememberMe(true);
		        try {
		        	System.out.println("1. " + token.hashCode());
		        	// 执行登录. 
		            currentUser.login(token);
//		            return 1;
		        } 
		        // ... catch more exceptions here (maybe custom ones specific to your application?
		        // 所有认证时异常的父类. 
		        catch (AuthenticationException ae) {
		            //unexpected condition?  error?
		        	System.out.println("登录失败: " + ae.getMessage());
		        	return -1;
		        }
		    }
			//授权  1)设计用户表、角色表、用户角色中间表、权限表和角色权限中间表； 2）实体类的映射表 ； 3）修改注册密码的加密方式（shiro加密）
			//say who they are:
	        //print their identifying principal (in this case, a username):
			System.out.println("----> User [" + currentUser.getPrincipal() + "] logged in successfully.");//null

	        //test a role:
	        // 测试是否有某一个角色. 调用 Subject 的 hasRole 方法. 
	        if (currentUser.hasRole("admin")) {
	        	System.out.println("----> You have the admin role !");
	        } else {
	        	System.out.println("----> You just have user role.");//here
	        }

	        // 测试用户是否具备某一个行为. 
	        if (currentUser.isPermitted("Create")) {
	        	System.out.println("----> You are permitted to delete. " +"Here are the keys - have fun!");
	        } else {
	        	System.out.println("Sorry, you aren't allowed to delete");//here
	        }

	        //all done - log out!
	        // 执行登出. 调用 Subject 的 Logout() 方法. 
	        System.out.println("---->" + currentUser.isAuthenticated());	//true        
//	        currentUser.logout();	        
//	        System.out.println("---->" + currentUser.isAuthenticated());    //false
//	        System.exit(0);

			SysUser sysUser = (SysUser)this.sysUserService.checkUser(loginName, userPwd);//shiro加密方式 
            try {
            	 UserID=sysUser.getUserId();
//            	 System.out.println("UserID:"+UserID);
			 } catch (Exception e) {
			    System.out.println("用户不存在！");
			 }
             if (GlobalMethod.isNullorEmpty(sysUser)) {
	           System.out.println("登录名或密码不正确......");
//       	   return new ResultJson("登陆名或密码不对！", "300");
	           return 0;
             }
             String errorCode = this.securService.doCheckUser(sysUser);
             System.out.println("errorCode:"+errorCode);
             if (errorCode != null) {
            	 if ("NO_POLIT".equals(errorCode))
            		 System.out.println("未配置栏目权限......");
//         		return new ResultJson("未配置栏目权限，请与管理员联系！", "300");
				return -1;
             }else {
            	 System.out.println("sysUser:"+sysUser);
            	 CurrentUser currentUser01 = this.securService.createCurrentUser(sysUser);
 
            	 request.getSession().setAttribute("USER_INFO", currentUser01);
//              System.out.println(new ResultJson("登陆成功！"));//com.smarthome.imcp.controller.ResultJson@3893a0e7
//      		return new ResultJson("登陆成功！");
               	return UserID;
             }
   		 }

           @RequestMapping({"logout.do"})
           public String logout(HttpServletRequest request) {
	         System.out.println("行，我退出！");
	         request.getSession().removeAttribute("USER_INFO");
	         request.getSession().removeAttribute("SESSION_ID");
	         return "../login";          //==login.do
           }

		   @RequestMapping({"register.do"})
		   @ResponseBody
		   public Object register(@RequestParam("userName") String userName,@RequestParam("userPwd") String userPwd,@RequestParam("userPhone") String userPhone,@RequestParam("email") String email,HttpServletRequest request) {
			   System.out.println("新用户注册！");
//			   Md5 md5 = new Md5();
			   SysUser sysuser=new SysUser();
			   sysuser.setLoginName(userName);
			   System.out.println("shiro加密方式...");
			   //shiro加密部分
			    String hashAlgorithmName = "MD5";
				Object credentials = userPwd;
				Object salt = ByteSource.Util.bytes(userName);;
				int hashIterations = 1024;
				Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
				String userPwd1=result.toString();
				System.out.println("shiro加密后 password:"+userPwd1);
				/////////////////////////////////////////////加密结束/////////////////////////////////////
			   
			    sysuser.setLoginPwd(userPwd1);
			    sysuser.setUserPhone(userPhone);
			    sysuser.setEmail(email);
//			    System.out.println(md5.getMD5ofStr(userPwd));
			    SysUser sysuser01=this.sysUserService.save(sysuser);//哪个是保存操作:CommonsDaoImpl.java文件中的save（）方法
			   
//			    System.out.println("sysuser01=="+sysuser01);
			    if(sysuser01 !=null) {
				   System.out.println("注册成功，即将跳转到登录页面...");
				   return "success";
			    }else {
				   System.out.println("注册失败，请重新操作");
				   return "fail";
			    }
			   
			 }
		   
		   @RequestMapping({"phoneCode.do"})
		   @ResponseBody
		   public Map<String, String> getPhoneCode(@RequestParam("userPhone") String userPhone,HttpServletRequest request) {//返回密码（未加密）和验证码，然后放到隐藏的表单中，用于做后续比较
			   System.out.println("获取手机验证码！");
			   if (this.sysUserService.findByUserPhone(userPhone) !=0) {//如果不为0，发送验证码
				   Map<String,String> map=new HashMap<String,String>();
				   int UserID=this.sysUserService.findByUserPhone(userPhone);
				   map.put("UserID", UserID+"");
				   
				   HttpClient client = new HttpClient();  
			       PostMethod method = new PostMethod(Url);  
			  
			       client.getParams().setContentCharset("UTF-8");  
			       method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");  
			  
			       int mobile_code = (int) ((Math.random() * 9 + 1) * 100000); //验证码服务端生成 
			       System.out.println("mobileCode:"+mobile_code);
			  
			       String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");  
			  
			       NameValuePair[] data = { // 提交短信  
			                new NameValuePair("account", "C33889087"),   //APIID
			                new NameValuePair("password", "34152fcd4071c77fca4b0cf01e92f756"), // 查看密码请登录用户中心->验证码、通知短信->帐户及签名设置->APIKEY  
			                // new NameValuePair("password",  
			                // util.StringUtil.MD5Encode("密码")),  
			                new NameValuePair("mobile", "17779605699"),
			                new NameValuePair("content", content), };  
			        method.setRequestBody(data);  
			  
			        try {  
			            client.executeMethod(method);  
			  
			            String SubmitResult = method.getResponseBodyAsString();  
			  
			            // System.out.println(SubmitResult);  
			  
			            Document doc = DocumentHelper.parseText(SubmitResult);  
			            Element root = doc.getRootElement();  
			  
			            String code = root.elementText("code");  
			            String msg = root.elementText("msg");  
			            String smsid = root.elementText("smsid");  
			  
			            System.out.println("code:"+code);  
			            System.out.println("msg:"+msg);  
			            System.out.println("smsid:"+smsid);  
			  
			            if ("2".equals(code)) {  
			                System.out.println("短信提交成功");  
			                map.put("mobile_code", mobile_code+"");
			                return map;
			            }  
			  
			        } catch (HttpException e) {  
			            e.printStackTrace();  
			        } catch (IOException e) {   
			            e.printStackTrace();  
			        } catch (DocumentException e) {   
			            e.printStackTrace();  
			        }
			   }
			  return null; 
		   }
		   
		   @RequestMapping({"emailCode.do"})
		   @ResponseBody
		   public Map<String,String> geteEailCode(@RequestParam("email") String email,HttpServletRequest request) {
			   System.out.println("获取邮箱验证码！");
			   if (this.sysUserService.findByUserEmail(email) !=0) {//如果不为0，发送验证码
				   Map<String,String> map=new HashMap<String,String>();
				   int UserID = this.sysUserService.findByUserEmail(email);
				   map.put("UserID",UserID+"");
				   
				   int email_code = (int) ((Math.random() * 9 + 1) * 100000); //验证码服务端生成
				   String text="您的验证码是：" + email_code + "。请不要把验证码泄露给其他人。";
				   
				   MailUtil mail=new MailUtil();
				   try {
					   mail.send_mail(email, text);
					   System.out.println("邮件发送成功...");
					   map.put("email_code", email_code+"");
				   } catch (MessagingException e) {
					   e.printStackTrace();
					   return null;
				   }
				   return map;
			   } 
				return null;
			}
		   
		   @RequestMapping({"findByTelOrEmail.do"})
		   @ResponseBody
		   public String findByTelOrEmail(@RequestParam("UserID") String UserID,@RequestParam("loginPwd") String loginPwd,HttpServletRequest request) {
			   System.out.println("忘记密码-找回密码-手机|邮箱找回！");
			   Md5 md5 = new Md5();
			   int UserID1=Integer.parseInt(UserID);
			   SysUser sysUser = (SysUser)this.sysUserService.findByKey(UserID1);
			   if (sysUser == null) {
				   return "error";
			   }else {
				   sysUser.setLoginPwd(md5.getMD5ofStr(loginPwd));
				   SysUser sysuser01=this.sysUserService.update(sysUser);
				   if(sysuser01 !=null) {
					   System.out.println("修改成功...");
					   return "success";
				   }else {
					   System.out.println("修改失败，请重新操作");
					   return "error";
				   }
			   }  
			}	      
		   
		   
		   @RequestMapping({"getSysUser.do"})
		   @ResponseBody
//		   public String getSysUser(@RequestParam("loginName") String loginName,@RequestParam("loginPwd") String loginPwd,HttpServletRequest request) {
		   public String getSysUser(@RequestParam("UserID") int UserID,HttpServletRequest request) {
			   System.out.println("获得当前用户名！");
//			   Md5 md5 = new Md5();
//			   String loginName=request.getParameter("loginName");//null 后台无法直接获取，可能要前台传过来（这里不能捕获URL中的参数）
//			   String loginPwd=request.getParameter("loginPwd"); //null
			   System.out.println("UserID="+UserID);
			   
//			   SysUser sysUser = (SysUser)this.sysUserService.checkUser(loginName, md5.getMD5ofStr(loginPwd));
			   SysUser sysUser = (SysUser)this.sysUserService.findByKey(UserID);
			   String result="";
			   try {
				   result=sysUser.getUserName();
			} catch (Exception e) {
				System.out.println("用户别名不存在！");
			}
			   
			   return result;
		   }
		   
		   
		   @RequestMapping({"findUserInfo.do"})
		   @ResponseBody
		   public Map<String, String> findUserInfo(@RequestParam("USER_ID") int USER_ID,HttpServletRequest request) {//这个得怎么返回？？？map  
			   System.out.println("查找个人信息！");
			   SysUser sysuser=this.sysUserService.findByKey(USER_ID);//找到用户
//			   System.out.println("sysuser="+sysuser);
			   String loginName=sysuser.getLoginName();
			   String username=sysuser.getUserName();
			   String phone=sysuser.getUserPhone();
			   String email=sysuser.getEmail();
			   
			   Map<String, String> map = new HashMap<String, String>();
			   map.put("用户名", loginName);
			   map.put("别名", username);
			   map.put("手机", phone);
			   map.put("邮箱", email);
			   return map;//返回的不包括密码
		   }
		   
		   @RequestMapping({"modifyUserInfo.do"})
		   @ResponseBody
		   public String modifyUserInfo(@RequestParam("USER_ID") String USER_ID,@RequestParam("loginName") String loginName,@RequestParam("userName") String userName,@RequestParam("userPhone") String userPhone,@RequestParam("email") String email,HttpServletRequest request) {//这个得怎么返回？？？map  
			   System.out.println("编辑个人信息！");
			   int userId=Integer.parseInt(USER_ID);;
			   SysUser sysuser=this.sysUserService.findByKey(userId);//找到用户
//			   System.out.println("sysuser="+sysuser);
			   sysuser.setLoginName(loginName);
			   sysuser.setUserName(userName);
			   sysuser.setUserPhone(userPhone);
			   sysuser.setEmail(email);
			   
			   SysUser sysuser01=this.sysUserService.update(sysuser);
			   if(sysuser01 !=null) {
				   System.out.println("修改成功...");
				   return "success";
			   }else {
				   System.out.println("修改失败，请重新操作");
				   return "error";
			   }
		   }
		   
		   @RequestMapping({"modifyPwd.do"})
		   @ResponseBody 
		   public String modifyPwd(@RequestParam("USER_ID") int USER_ID,@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, HttpServletRequest request) {
			   String result="fail";
			   //找到用户
			   SysUser sysuser=this.sysUserService.findByKey(USER_ID);
			   //取出密码
			   String pwd=sysuser.getLoginPwd();
			   System.out.println("取出来的密码："+pwd);
			   Md5 md5 = new Md5();
			   //对传过来的旧密码进行MD5加密
			   String loginPwd=md5.getMD5ofStr(oldpassword);
			   System.out.println(loginPwd.equals(pwd));
			   //加密后的旧密码与取出来的对比，若正确则修改新密码
			   if(loginPwd.equals(pwd)) {
				   System.out.println("修改密码...");
//				   sysuser.setLoginPwd(md5.getMD5ofStr(newpassword));
				   this.sysUserService.update(sysuser);
				   result="success";
			   }else {
				   System.out.println("旧密码输入有误");
			   }
			   return result;
		   }
		   
		   //用户管理模块-全部用户
//		   @RequestMapping({"showUsers.do"})
//		   public String showUsers(Model model) {
////		        ModelAndView mad = new ModelAndView("redirect:index.jsp");
////		        List<BoUsers> varList = this.boUserssService.findAllBoUsers();//[Ljava.lang.Object; cannot be cast to com.smarthome.imcp.dao.model.bo.BoUsers
//		        List<BoUsers> varList = this.boUserssService.getAllList();
//			   //用于测试的增强for语句
//			   for (BoUsers list : varList) {
//				   System.out.println("phoneType:"+list.getPhoneType());
//		        }	
//			   model.addAttribute("varList", varList);
//		       return "../index";
//		    } 
//		   @RequestMapping({"showUsers.do"})
//		   @ResponseBody
//		   public Map<String,Object> showUsers() {
////		        ModelAndView mad = new ModelAndView("index.jsp");
////		        List<BoUsers> varList = this.boUserssService.findAllBoUsers();//[Ljava.lang.Object; cannot be cast to com.smarthome.imcp.dao.model.bo.BoUsers
//		       Map<String,Object> map=new HashMap<String,Object>() ;
//			   List<BoUsers> varList = this.boUserssService.getAllList();
//			   //用于测试的增强for语句
//			   for (BoUsers list : varList) {
//				   System.out.println("phoneType:"+list.getPhoneType());
//		        }	
//			   map.put("varList", varList);
//		       return map;
//		    }
		   @RequestMapping({"showUsers.do"})
		   @ResponseBody
		   public List<BoUsers> showUsers() {
			   System.out.println("分页操作 -首页");
			   List varList=new ArrayList<BoUsers>();
			   List<BoUsers> bousers = this.boUserssService.getAllList();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=bousers.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }					
			   
			   for(int i=0;i<bousers.size();i++) {
				   if(i>=0 && i<=9) {
					   BoUsers user=new BoUsers();
					   user.setUserId(bousers.get(i).getUserId());
					   System.out.println(bousers.get(i).getUserId());
					   user.setUserName(bousers.get(i).getUserName());
					   user.setUserSex(bousers.get(i).getUserSex());
					   user.setUserPhone(bousers.get(i).getUserPhone());
					   user.setPhoneType(bousers.get(i).getPhoneType());
					   user.setVersionType(bousers.get(i).getVersionType());
					   user.setSignature(bousers.get(i).getSignature());
					   user.setCity(bousers.get(i).getCity());
					   varList.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   varList.add(page);
		       return varList;
		    }

		   
		   @RequestMapping({"findByTel.do"})//再页面上是以json的形式显示
		   @ResponseBody
		   public List<BoUsers> findByTel(@RequestParam("userPhone") String userPhone) {
			   List<BoUsers> varList=new ArrayList<BoUsers>();
			   BoUsers bousers = this.boUserssService.findByUserPhone(userPhone);
//			   System.out.println("bousers:"+bousers);
			   
			   BoUsers user=new BoUsers();
			   user.setUserId(bousers.getUserId());
			   System.out.println(bousers.getUserId());
			   user.setUserName(bousers.getUserName());
			   user.setUserSex(bousers.getUserSex());
			   user.setUserPhone(bousers.getUserPhone());
			   user.setPhoneType(bousers.getPhoneType());
			   user.setVersionType(bousers.getVersionType());
			   user.setSignature(bousers.getSignature());
			   user.setCity(bousers.getCity());
			   varList.add(user);
			   
		       return varList;
//		                    下面只能取一个
//			   List<BoUsers> varList=new ArrayList<BoUsers>();
//			   BoUsers bouser = this.boUserssService.findByUserPhone(userPhone); 
//			   BoUsers user=new BoUsers();
//			   user.setUserName(bouser.getUserName());
//			   user.setUserSex(bouser.getUserSex());
//			   user.setUserPhone(bouser.getUserPhone());
//			   user.setPhoneType(bouser.getPhoneType());
//			   user.setVersionType(bouser.getVersionType());
//			   user.setSignature(bouser.getSignature());
//			   user.setCity(bouser.getCity());
//			   varList.add(user);
//		       return varList;
		    }
		   
		   //分页
		   
		   @RequestMapping({"findByIndex.do"})//再页面上是以json的形式显示
		   @ResponseBody
		   public List<BoUsers> findByIndex(@RequestParam("index") int index) {
			   System.out.println("分页操作");
			   List varList=new ArrayList<BoUsers>();
			   List<BoUsers> bousers = this.boUserssService.getAllList();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=bousers.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;//只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }					
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   System.out.println("totalPages:"+totalPages);
			   for(int i=0;i<bousers.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   System.out.println("现在的i是 "+i);
					   BoUsers user=new BoUsers();
					   user.setUserId(bousers.get(i).getUserId());
					   System.out.println(bousers.get(i).getUserId());
					   user.setUserName(bousers.get(i).getUserName());
					   user.setUserSex(bousers.get(i).getUserSex());
					   user.setUserPhone(bousers.get(i).getUserPhone());
					   user.setPhoneType(bousers.get(i).getPhoneType());
					   user.setVersionType(bousers.get(i).getVersionType());
					   user.setSignature(bousers.get(i).getSignature());
					   user.setCity(bousers.get(i).getCity());
					   varList.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   varList.add(page);
		       return varList;
		    }
		   
		 //用户管理模块-登录验证码
		   @RequestMapping({"showTelValidations.do"})
		   @ResponseBody
		   public List<BoUsersValidation> showTelValidations() {//id，userPhone，verificationCode
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList<BoUsersValidation>();
		        List<BoUsersValidation> validations = this.boUsersValidationService.findPhoneValidations();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=validations.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }					
			   
			   for(int i=0;i<validations.size();i++) {
				   if(i>=0 && i<=9) {
					   BoUsersValidation user=new BoUsersValidation();
					    user.setId(validations.get(i).getId());
//					    System.out.println(validation.getId());
					    user.setUserPhone(validations.get(i).getUserPhone());
//					    System.out.println(validations.get(i).getUserPhone());
					    user.setVerificationCode(validations.get(i).getVerificationCode());
					    list.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findValidationByIndex.do"})
		   @ResponseBody
		   public List<BoUsersValidation> findValidationByIndex(@RequestParam("index") int index) {//id，userPhone，verificationCode
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList<BoUsersValidation>();
		        List<BoUsersValidation> validations = this.boUsersValidationService.findPhoneValidations();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=validations.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }				
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }					
			   for(int i=0;i<validations.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   BoUsersValidation user=new BoUsersValidation();
					    user.setId(validations.get(i).getId());
//					    System.out.println(validation.getId());
					    user.setUserPhone(validations.get(i).getUserPhone());
//					    System.out.println(validations.get(i).getUserPhone());
					    user.setVerificationCode(validations.get(i).getVerificationCode());
					    list.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"addTelValidation.do"})
		   @ResponseBody
		   public String addTelValidation(@RequestParam("userPhone") String userPhone,@RequestParam("verificationCode") String verificationCode) {
//			   System.out.println("come in...");
			   BoUsersValidation boValidation=new BoUsersValidation();
			   boValidation.setUserPhone(userPhone);
			   boValidation.setVerificationCode(verificationCode);
			   
			   BoUsersValidation boValidation1 = this.boUsersValidationService.save(boValidation); //使用系统的save
			   String result="";
			   if(boValidation1 != null) {
				   result="success";
			   }else {
				   result="fail";
			   }
		        return result;
		    }
		   //修改
		   @RequestMapping({"modifyTelValidation.do"})
		   @ResponseBody
		   public String modifyTelValidation(@RequestParam("id") int id,@RequestParam("userPhone") String userPhone,@RequestParam("verificationCode") String verificationCode) {
			    BoUsersValidation boV=this.boUsersValidationService.findByKey(id);//找到该条记录
//		        String result = this.sysUserService.modifyValidation(userPhone,verificationCode); 
			    System.out.println("boV:"+boV);
		        if (boV == null) {
					   return "error";
				   }else {
					   boV.setUserPhone(userPhone);
					   boV.setVerificationCode(verificationCode);
					   this.boUsersValidationService.update(boV);
					   return "success";
				   }
		    }
//		   public ModelAndView modifyTelValidation(@RequestParam("id") int id,@RequestParam("userPhone") String userPhone,@RequestParam("verificationCode") String verificationCode) {
//			    BoUsersValidation boV=this.boUsersValidationService.findByKey(id);//找到该条记录
////		        String result = this.sysUserService.modifyValidation(userPhone,verificationCode); 
//		        if (boV == null) {
//					   return null;
//				   }else {
//					   ModelAndView mav=new ModelAndView();
//					   boV.setUserPhone(userPhone);
//					   boV.setVerificationCode(verificationCode);
//					   this.boUsersValidationService.update(boV);
//					   BoUsersValidation boV1=new BoUsersValidation();
//					   mav.addObject("boV",boV1);
//					   return mav;
//				   }
//		    }
		 //删除
		 @RequestMapping({"delTelValidation.do"})
		 @ResponseBody
		 public String delTelValidation(@RequestParam("id") int id) {
			  String id1=id+"";
			  try {
				  this.boUsersValidationService.deleteByKey(id1);//删除该条记录
			} catch (Exception e) {
				return "fail";
			}
              return "success";
		}
		   //登录验证码 导出到Excel
		   @RequestMapping({"ValidationtoExcel.do"})
		   @ResponseBody
		   public String ValidationtoExcel() {
			    List<BoUsersValidation> list = this.boUsersValidationService.findPhoneValidations();
			    int num=0; 
		        String result = this.boUsersValidationService.toExcel(num,list); 
		        return result;
		    }
		   @RequestMapping({"findValidationByTel.do"})
		   @ResponseBody
		   public List<BoUsersValidation> findValidationByTel(@RequestParam("userPhone") String userPhone) {
			   List<BoUsersValidation> list=new ArrayList<BoUsersValidation>();
		        BoUsersValidation validation = this.boUsersValidationService.findByUserPhone(userPhone); 
//		        System.out.println("validations:"+validations);
		        BoUsersValidation user=new BoUsersValidation();
			    user.setId(validation.getId());
//				    System.out.println(validation.getId());
			    user.setUserPhone(validation.getUserPhone());
//				    System.out.println(validation.getUserPhone());
			    user.setVerificationCode(validation.getVerificationCode());
			    list.add(user);
			    
		        return list;
//			   BoUsersValidation varList = this.boUsersValidationService.findByUserPhone(userPhone); 
//		       return varList;
		    }
		   
		 //主机管理模块-全部主机
		   @RequestMapping({"showHostDevices.do"})
		   @ResponseBody
		   public List<BoDevice> showHostDevices() {//deviceCode,status,type
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList<BoDevice>();
		       List<BoDevice> bodevices = this.boDeviceService.getAllHostDevices();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=bodevices.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
//			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<bodevices.size();i++) {
				   if(i>=0 && i<=9) {
					   BoDevice user=new BoDevice();
			        	user.setDeviceId(bodevices.get(i).getDeviceId());
					    user.setDeviceCode(bodevices.get(i).getDeviceCode());
//					    System.out.println("deviceCode:"+bodevices.get(i).getDeviceCode());
					    user.setStatus(bodevices.get(i).getStatus());
//					    System.out.println("status:"+bodevices.get(i).getStatus());
					    user.setType(bodevices.get(i).getType());
//					    System.out.println("type:"+bodevice.getType());
					    list.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   //取出在线人数和总人数
		   @RequestMapping({"showNum.do"})
		   @ResponseBody
		   public Map<String,String> showNum() {//deviceCode,status,type
			   Map map=new HashMap();
			   List<BoDevice> bodevices = this.boDeviceService.getAllHostDevices();
			   //全局变量
			   int total=0,online=0;
			   for(BoDevice bodevice:bodevices) {
				   //局部变量
				   total++;
				   if(bodevice.getStatus()==1) {
					   online++;
				   }
			   }
//			   int total=2077;
//			   int online=255;
			   map.put("total",total);
			   map.put("online",online); 
		        return map;
		    } 
		   
		   @RequestMapping({"findDevicesByIndex.do"})
		   @ResponseBody
		   public List<BoDevice> findDevicesByIndex(@RequestParam("index") int index) {//deviceCode,status,type
			   System.out.println("分页操作 ");
			   List list=new ArrayList<BoDevice>();
		       List<BoDevice> bodevices = this.boDeviceService.getAllHostDevices();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=bodevices.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   System.out.println("totalCount:"+totalCount);
			   System.out.println("totalPages:"+totalPages);
//			   System.out.println("startRow:"+startRow);
//			   System.out.println("endRow:"+endRow);
			   for(int i=0;i<bodevices.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   BoDevice user=new BoDevice();
			        	user.setDeviceId(bodevices.get(i).getDeviceId());
					    user.setDeviceCode(bodevices.get(i).getDeviceCode());
//					    System.out.println("deviceCode:"+bodevices.get(i).getDeviceCode());
					    user.setStatus(bodevices.get(i).getStatus());
//					    System.out.println("status:"+bodevices.get(i).getStatus());
					    user.setType(bodevices.get(i).getType());
//					    System.out.println("type:"+bodevice.getType());
					    list.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findByDevicecode.do"})
		   @ResponseBody
		   public List<BoDevice> findByDevicecode(@RequestParam("deviceCode") String deviceCode) {
			   List<BoDevice> list=new ArrayList<BoDevice>();
		       List<BoDevice> bodevices = this.boDeviceService.findByDCode(deviceCode); 
		        for(BoDevice bodevice:bodevices) {//
		        	BoDevice user=new BoDevice();
		        	user.setDeviceId(bodevice.getDeviceId());
				    user.setDeviceCode(bodevice.getDeviceCode());
				    System.out.println("deviceCode:"+bodevice.getDeviceCode());
				    user.setStatus(bodevice.getStatus());
				    System.out.println("status:"+bodevice.getStatus());
				    user.setType(bodevice.getType());
				    System.out.println("type:"+bodevice.getType());
				    list.add(user);
				   }
		        return list;
		    }
		   @RequestMapping({"findByStatus.do"})
		   @ResponseBody
		   public List<BoDevice> findByStatus(@RequestParam("status") int status) {
			   /*System.out.println("hhe");*/
//			   System.out.println("status:"+status);
			   List<BoDevice> list=new ArrayList<BoDevice>();
		       List<BoDevice> bodevices = this.boDeviceService.findByStatus(status); 
		       if(bodevices !=null) {
		    	   for(BoDevice bodevice:bodevices) {//
			        	BoDevice user=new BoDevice();
			        	user.setDeviceId(bodevice.getDeviceId());
					    user.setDeviceCode(bodevice.getDeviceCode());
					    System.out.println("deviceCode:"+bodevice.getDeviceCode());
					    user.setStatus(bodevice.getStatus());
					    System.out.println("status:"+bodevice.getStatus());
					    user.setType(bodevice.getType());
					    System.out.println("type:"+bodevice.getType());
					    list.add(user);
					   } 
		       }
		        
		       return list;
		    }
		   
		   @RequestMapping({"addHostDevice.do"})
		   @ResponseBody
		   public String addDevice(@RequestParam("deviceCode") String deviceCode,@RequestParam("type") String type) {
			   BoDevice boDevice=new BoDevice();
			   boDevice.setDeviceCode(deviceCode);
			   boDevice.setType(type);
			   boDevice.setStatus(0);
			   
			   BoDevice boDevice1 = this.boDeviceService.save(boDevice); //使用系统的save
			   String result="";
			   if(boDevice1 != null) {
				   result="success";
			   }else {
				   result="fail";
			   }
		        return result;
		    }
		   //修改
		   @RequestMapping({"modifyHostDevice.do"})
		   @ResponseBody
		   public String modifyHostDevice(@RequestParam("deviceCode") String deviceCode,@RequestParam("type") String type) {
			   List<BoDevice> boDs=this.boDeviceService.findByDCode(deviceCode);//找到该条记录
			   BoDevice boD=boDs.get(0);
		        if (boD == null) {
					   return "error";
				   }else {
					   boD.setDeviceCode(deviceCode);;//DeviceCode是另一个表的
					   boD.setType(type);
					   this.boDeviceService.update(boD);
					   return "success";
				   }
		    } 
		   //从Excel导入
		   @RequestMapping({"ExcelToHostDevice.do"})
		   @ResponseBody
		   public String ExcelToHostDevice(@RequestParam("filepath") String filepath) {
//			   System.out.println("into controller...");
			   int choiceTo=0; 
		       String result="fail";
		       try {
				result = this.boDeviceService.saveExcel(choiceTo,filepath);
		       } catch (Exception e) {
		    	   e.printStackTrace();
		       } 
		       return result;
		    }
		 //主机管理模块-用户绑定与主机解绑
		   @RequestMapping({"showHostD.do"})
		   @ResponseBody
		   public List<BoHostDevice> showHostD() {
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList();
		       List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();		  //直接把这结果传给前台 出现Cannot call sendError() after the response has been committed     
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boHosts.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
//			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=0 && i<=9) {
					   Map map=new HashMap();
			        	int id=boHosts.get(i).getId();
			        	String deviceCode=boHosts.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boHosts.get(i).getBoUsers().getUserPhone();
			        	String nick_name=boHosts.get(i).getNickName();
			        	
			        	int DEVICE_ID=boHosts.get(i).getBoDevice().getDeviceId();
			        	int USER_ID=boHosts.get(i).getBoUsers().getUserId();
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("nick_name",nick_name);
			        	map.put("DEVICE_ID", DEVICE_ID);
			        	map.put("USER_ID", USER_ID);
			        	list.add(map);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findudeviceByIndex.do"})
		   @ResponseBody
		   public List<BoHostDevice> findudeviceByIndex(@RequestParam("index") int index) {
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList();
		       List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();		  //直接把这结果传给前台 出现Cannot call sendError() after the response has been committed     
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boHosts.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   System.out.println("totalCount:"+totalCount);
			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=0 && i<=9) {
					   Map map=new HashMap();
			        	int id=boHosts.get(i).getId();
			        	String deviceCode=boHosts.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boHosts.get(i).getBoUsers().getUserPhone();
			        	String nick_name=boHosts.get(i).getNickName();
			        	
			        	int DEVICE_ID=boHosts.get(i).getBoDevice().getDeviceId();
			        	int USER_ID=boHosts.get(i).getBoUsers().getUserId();
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("nick_name",nick_name);
			        	map.put("DEVICE_ID", DEVICE_ID);
			        	map.put("USER_ID", USER_ID);
			        	list.add(map);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findHostByDevicecode.do"})
		   @ResponseBody
		   public List<BoHostDevice> findHostByDevicecode(@RequestParam("deviceCode") String deviceCode) {
			   List list=new ArrayList();
//		       List<BoHostDevice> boHosts = this.boHostDeviceService.getDeviceByAddress(deviceAddress); 
			   List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();
	    	   for(BoHostDevice bohost:boHosts) {//
	    		   System.out.println(bohost.getBoDevice().getDeviceCode().equals(deviceCode));
	    		   if(bohost.getBoDevice().getDeviceCode().equals(deviceCode)) {//解决从BoUsers表  查询  BoHostDevice表 的问题,,此处不能用等号比较
	    			   Map map=new HashMap();
			        	int id=bohost.getId();
			        	String deviceCode1=bohost.getBoDevice().getDeviceCode();
			        	String userPhone=bohost.getBoUsers().getUserPhone();
			        	String nick_name=bohost.getNickName();
			        	
			        	int DEVICE_ID=bohost.getBoDevice().getDeviceId();
			        	int USER_ID=bohost.getBoUsers().getUserId();
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode1);
			        	map.put("userPhone",userPhone);
			        	map.put("nick_name",nick_name);
			        	map.put("DEVICE_ID", DEVICE_ID);
			        	map.put("USER_ID", USER_ID);
			        	list.add(map);
	    		   }
		        } 
		        return list;
		    }
		   //删除主机（解绑） 和device和boUsers解绑
		   @RequestMapping({"delHost.do"})
		   @ResponseBody
		   public String delHost(@RequestParam("id") int id,@RequestParam("DEVICE_ID") int DEVICE_ID,@RequestParam("USER_ID") int USER_ID) {
				System.out.println("ssssssssssssss");  
			   String id1=id+"";
				  try {
					  this.boHostDeviceService.deleteByKey(id1);//删除该条记录
				} catch (Exception e) {
					return "fail";
				}
	              return "success";
			}
		 //设备管理模块-全部设备（字段中有其他的表）
		   @RequestMapping({"showDevices.do"})
		   @ResponseBody
		   public List<BoHostDevice> showDevices() {//有问题   deviceCode,userPhone,deviceAddress
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList();
		       List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();		  //直接把这结果传给前台 出现Cannot call sendError() after the response has been committed
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boHosts.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
//			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=0 && i<=9) {
					   Map map=new HashMap();
			        	int id=boHosts.get(i).getId();
			        	String deviceCode=boHosts.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boHosts.get(i).getBoUsers().getUserPhone();
			        	String deviceAddress=boHosts.get(i).getDeviceAddress();
//			        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("deviceAddress",deviceAddress);
			        	list.add(map);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findHostByIndex.do"})
		   @ResponseBody
		   public List<BoHostDevice> findHostByIndex(@RequestParam("index") int index) {
			   System.out.println("分页操作 ");
			   List list=new ArrayList();
		       List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();		  //直接把这结果传给前台 出现Cannot call sendError() after the response has been committed
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boHosts.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   System.out.println("totalCount:"+totalCount);
			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   Map map=new HashMap();
			        	int id=boHosts.get(i).getId();
			        	String deviceCode=boHosts.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boHosts.get(i).getBoUsers().getUserPhone();
			        	String deviceAddress=boHosts.get(i).getDeviceAddress();
//			        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("deviceAddress",deviceAddress);
			        	list.add(map);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findHostByAddr.do"})
		   @ResponseBody
		   public List<BoHostDevice> findHostByAddr(@RequestParam("deviceAddress") String deviceAddress) {
			   List list=new ArrayList();
//		       List<BoHostDevice> boHosts = this.boHostDeviceService.getDeviceByAddress(deviceAddress); 
			   List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();
	    	   for(BoHostDevice bohost:boHosts) {//
	    		   if(bohost.getDeviceAddress().equals(deviceAddress)) {//解决从BoUsers表  查询  BoHostDevice表 的问题,,此处不能用等号比较
//	    			   System.out.println("coming...");
	    			   Map map=new HashMap();
			        	int id=bohost.getId();
			        	String deviceCode=bohost.getBoDevice().getDeviceCode();
			        	String userPhone1=bohost.getBoUsers().getUserPhone();
			        	String deviceAddress1=bohost.getDeviceAddress();
//				        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone1);
			        	map.put("deviceAddress",deviceAddress1);
			        	list.add(map);
	    		   }
		        } 
		        return list;
		    }
		   @RequestMapping({"findHostByTel.do"})//从BoUsers表 到 BoHostDevice表
		   @ResponseBody
		   public List<BoHostDevice> findHostByTel(@RequestParam("userPhone") String userPhone) {
			   List list=new ArrayList();
//		       List<BoHostDevice> boHosts = this.boHostDeviceService.findHostByUserPhone(userPhone);//BoHostDevice中不存在userPhone字段
			   List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();
//               System.out.println("userPhone:"+userPhone);
	    	   for(BoHostDevice bohost:boHosts) {//
//	    		   System.out.println("bohost.getBoUsers().getUserPhone():"+bohost.getBoUsers().getUserPhone());
//	    		   System.out.println(bohost.getBoUsers().getUserPhone().equals(userPhone));
	    		   if(bohost.getBoUsers().getUserPhone().equals(userPhone)) {//解决从BoUsers表  查询  BoHostDevice表 的问题,,此处不能用等号比较
//	    			   System.out.println("coming...");
	    			   Map map=new HashMap();
			        	int id=bohost.getId();
			        	String deviceCode=bohost.getBoDevice().getDeviceCode();
			        	String userPhone1=bohost.getBoUsers().getUserPhone();
			        	String deviceAddress=bohost.getDeviceAddress();
//				        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone1);
			        	map.put("deviceAddress",deviceAddress);
			        	list.add(map);
	    		   }
		        } 
//	    	   System.out.println("list:"+list); 
		        return list;
		    }
		   //删除设备
		   @RequestMapping({"delDevices.do"})
		   @ResponseBody
		   public String delDevices(@RequestParam("id") int id) {
				  String id1=id+"";
				  
				  try {
					  this.boHostDeviceService.deleteByKey(id1);//删除该条记录
				} catch (Exception e) {
					return "fail";
				}
	              return "success";
			}
		   
		 //设备管理模块-红外转发器参数
		   @RequestMapping({"showDevicesRed.do"})
		   @ResponseBody
		   public List<BoInfraredPart> showDevicesRed() {
			   System.out.println("分页操作 -首页");
			   List list=new ArrayList<BoInfraredPart>();
		       List<BoInfraredPart> boHosts = this.boInfraredPartService.getAllInfraredPart();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boHosts.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
//			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=0 && i<=9) {
					   BoInfraredPart user=new BoInfraredPart();
					    user.setId(boHosts.get(i).getId());
//					    System.out.println("id:"+bohost.getId());
					    user.setDeviceAddress(boHosts.get(i).getDeviceAddress());
//					    System.out.println("deviceAddress:"+boHosts.get(i).getDeviceAddress());
					    user.setValidationCode(boHosts.get(i).getValidationCode());
					    list.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findredByIndex.do"})
		   @ResponseBody
		   public List<BoInfraredPart> findredByIndex(@RequestParam("index") int index) {
			   System.out.println("分页操作 ");
			   List list=new ArrayList<BoInfraredPart>();
		       List<BoInfraredPart> boHosts = this.boInfraredPartService.getAllInfraredPart();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boHosts.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   System.out.println("totalCount:"+totalCount);
			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   BoInfraredPart user=new BoInfraredPart();
					    user.setId(boHosts.get(i).getId());
//					    System.out.println("id:"+bohost.getId());
					    user.setDeviceAddress(boHosts.get(i).getDeviceAddress());
//					    System.out.println("deviceAddress:"+boHosts.get(i).getDeviceAddress());
					    user.setValidationCode(boHosts.get(i).getValidationCode());
					    list.add(user);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   @RequestMapping({"findRedByAddr.do"})
		   @ResponseBody
		   public List<BoInfraredPart> findRedByAddr(@RequestParam("deviceAddress") String deviceAddress) {
			   System.out.println("findRedByAddr method");
			   List<BoInfraredPart> list=new ArrayList<BoInfraredPart>();
		       BoInfraredPart boHost = this.boInfraredPartService.find(deviceAddress);
//		       System.out.println("boHosts:"+boHosts);
		       if(boHost !=null) {
	    		   BoInfraredPart user=new BoInfraredPart();
				    user.setId(boHost.getId());
//					    System.out.println(boHost.getId());
				    user.setDeviceAddress(boHost.getDeviceAddress());
//					    System.out.println(boHost.getDeviceAddress());
				    user.setValidationCode(boHost.getValidationCode());
				    list.add(user);

		       }
		        return list;
		    }
		   //修改（红外转发器参数）
		   @RequestMapping({"modifyDeviceRed.do"})
		   @ResponseBody
		   public String modifyDeviceRed(@RequestParam("id") int id,@RequestParam("deviceAddress") String deviceAddress,@RequestParam("validationCode") String validationCode) {
//			   System.out.println("come in");
			   String result="fail";
			   BoInfraredPart bohost=this.boInfraredPartService.find(deviceAddress);
//				   System.out.println("come in  in");
				   bohost.setDeviceAddress(deviceAddress);
				   bohost.setValidationCode(validationCode);
				   
				   BoInfraredPart bohost1 = this.boInfraredPartService.update(bohost); //使用系统的save
				   if(bohost1 != null) {
					   result="success";
				   }
		        return result;
		    }
		   
		   //添加（红外转发器参数）  `bo_infrared_part表
		   @RequestMapping({"addDeviceRed.do"})//加进去的无法显示
		   @ResponseBody
		   public String addDeviceRed(@RequestParam("deviceAddress") String deviceAddress,@RequestParam("validationCode") String validationCode) {
			   BoInfraredPart bohost=new BoInfraredPart();
			   bohost.setDeviceAddress(deviceAddress);
			   bohost.setValidationCode(validationCode);
			   
			   BoInfraredPart bohost1 = this.boInfraredPartService.save(bohost); //使用系统的save
			   String result="";
			   if(bohost1 != null) {
				   result="success";
			   }else {
				   result="fail";
			   }
		        return result;
		    }
		   //删除设备
		   @RequestMapping({"delDeviceRed.do"})
		   @ResponseBody
		   public String delDeviceRed(@RequestParam("id") int id) {
				  String id1=id+"";
				  try {
					  this.boInfraredPartService.deleteByKey(id1);//删除该条记录
				} catch (Exception e) {
					return "fail";
				}
	              return "success";
			}
		 //从Excel导入
		   @RequestMapping({"RedtoExcelToRed.do"})
		   @ResponseBody
		   public String RedtoExcelToRed(@RequestParam("filepath") String filepath) {
//			   System.out.println("into controller...");
			   String result="fail";
			   int choiceTo=1; 
		       try {
				   result = this.boInfraredPartService.saveExcel(choiceTo,filepath); 
		       } catch (Exception e) {
		    	   e.printStackTrace();
		       } 
		       return result;			   
		    } 
		 }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.LoginController
 * JD-Core Version:    0.6.2
 */