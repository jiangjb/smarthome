/*    */ package com.smarthome.imcp.controller;

		 import com.smarthome.imcp.common.GlobalMethod;
		 import com.smarthome.imcp.common.MailUtil;
/*    */ import com.smarthome.imcp.common.Md5;
		 import com.smarthome.imcp.common.Page;
		 import com.smarthome.imcp.dao.model.bo.BoDevice;
import com.smarthome.imcp.dao.model.bo.BoDeviceState;
import com.smarthome.imcp.dao.model.bo.BoFloor;
import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.dao.model.bo.BoInfraredButtons;
import com.smarthome.imcp.dao.model.bo.BoInfraredLearnControlMap;
import com.smarthome.imcp.dao.model.bo.BoInfraredPart;
import com.smarthome.imcp.dao.model.bo.BoModel;
import com.smarthome.imcp.dao.model.bo.BoModelInfo;
import com.smarthome.imcp.dao.model.bo.BoRoom;
import com.smarthome.imcp.dao.model.bo.BoSensor;
import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUserDevices;
		 import com.smarthome.imcp.dao.model.bo.BoUsers;
		 import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*    */ import com.smarthome.imcp.dao.model.system.SysUser;
/*    */ import com.smarthome.imcp.secur.CurrentUser;
		 import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
import com.smarthome.imcp.service.bo.BoDeviceStateServiceIface;
import com.smarthome.imcp.service.bo.BoFloorServiceIface;
import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
import com.smarthome.imcp.service.bo.BoInfraredButtonsServiceIface;
import com.smarthome.imcp.service.bo.BoInfraredLearnControlMapServiceIface;
import com.smarthome.imcp.service.bo.BoInfraredPartServiceIface;
import com.smarthome.imcp.service.bo.BoModelInfoServiceIface;
import com.smarthome.imcp.service.bo.BoModelServiceIface;
import com.smarthome.imcp.service.bo.BoRoomServiceIface;
import com.smarthome.imcp.service.bo.BoSensorServiceIface;
import com.smarthome.imcp.service.bo.BoUserDevicesServiceIface;
		 import com.smarthome.imcp.service.bo.BoUsersValidationServiceIface;
		 import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*    */ import com.smarthome.imcp.service.secur.SecurServiceIface;
/*    */ import com.smarthome.imcp.service.system.SysUserServiceIface;
import com.smarthome.imcp.util.FloorUtil;
import com.smarthome.imcp.util.RoomUtil;
import com.smarthome.imcp.util.UserUtil;

import java.io.IOException;
/*    */ import java.io.Serializable;
		 import java.util.ArrayList;
		 import java.util.Date;
		 import java.util.HashMap;
		 import java.util.List;
		 import java.util.Map;
		 import javax.mail.MessagingException;
/*    */ import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
		 import org.slf4j.Logger;
		 import org.slf4j.LoggerFactory; 
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
		  
		  @Autowired
		  private BoUserDevicesServiceIface<BoUserDevices, Serializable> BoUserDevicesService;
		  
		  @Autowired
		  private BoModelServiceIface<BoModel, Serializable> boModelService;
		  
		  @Autowired
		  private BoModelInfoServiceIface<BoModelInfo, Serializable> boModelInfoServicess;
		  
		  @Autowired
		  private BoRoomServiceIface<BoRoom, Serializable> boRoomService;//5-11
		  
		  @Autowired
		  private BoUserDevicesServiceIface<BoUserDevices, Serializable> boUserDevicesServicess;//5-11
		  
		  @Autowired
		  private BoUserssServiceIface<BoUsers, Serializable> boUserServicess;//5-11
		  
		  @Autowired
		   private BoFloorServiceIface<BoFloor, Serializable> boFloorService;//5-11
		  
		  @Autowired
		  private BoInfraredButtonsServiceIface<BoInfraredButtons, Serializable> boInfraredButtonsService;//5-12
		  
		  @Autowired
		  private BoDeviceStateServiceIface<BoDeviceState, Serializable> boDeviceStateService;//5-13
		  
		  @Autowired
		  private BoSensorServiceIface<BoSensor, Serializable> boSensorService;//5-13
		  
		  @Autowired
		  private BoInfraredLearnControlMapServiceIface<BoInfraredLearnControlMap, Serializable> boInfraredLearnControlMapService;//5-13
		  
//           private RequestJson requestJson;
           //new 短信验证码
           private static String Url = "http://106.ihuyi.com/webservice/sms.php?method=Submit";  
           
           private static Logger logger = LoggerFactory.getLogger(LoginController.class);
           
		  
/*    */   @RequestMapping({"login.do"})
//           @RequestMapping(value={"login.do"},produces="application/json;charset=UTF-8")
		   @ResponseBody
		   public int login(@RequestParam("loginName") String loginName, @RequestParam("loginPwd") String loginPwd, HttpServletRequest request)
   		{   
			HttpSession session = request.getSession();//4-27

		    int UserID=0;
//			 System.out.println("loginName="+loginName+",loginPwd="+loginPwd);
			String userPwd=shiroEncryption(loginPwd);
			////////////////////////////////shiro加密结束/////////////////////////////////////////////////
			//认证
			Subject currentUser = SecurityUtils.getSubject();
			System.out.println("currentUser:"+currentUser);
			if (!currentUser.isAuthenticated()) {//判断是不是已经认证的,没验证过的执行如下的操作
				System.out.println("111111111没被授权");
		    	// 把用户名和密码封装为 UsernamePasswordToken 对象
		        UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);//明文密码
//		        System.out.println("token:"+token);
		        // rememberme
		        token.setRememberMe(true);
		        try {
		        	System.out.println("1. " + token.hashCode());
		        	// 执行登录. 
		            currentUser.login(token);// Argument for byte conversion cannot be null.
		        } 
		        // ... catch more exceptions here (maybe custom ones specific to your application?
		        // 所有认证时异常的父类. 
		        catch (AuthenticationException ae) {
		            //unexpected condition?  error?
		        	System.out.println("登录失败: " + ae.getMessage());
		        	return 0;
		        }
		    }
			//授权  1)设计用户表、角色表、用户角色中间表、权限表和角色权限中间表； 2）实体类的映射表 ； 3）修改注册密码的加密方式（shiro加密）
			//say who they are:
	        //print their identifying principal (in this case, a username):
			System.out.println("----> User [" + currentUser.getPrincipal() + "] logged in successfully.");//null

			
	        //test a role:
	        // 测试是否有某一个角色. 调用 Subject 的 hasRole 方法. 
	        if (currentUser.hasRole("admin")) {//管理员
	        	session.setAttribute("role", "admin");
	        	System.out.println("----> You have the admin role !");
	        } else if(currentUser.hasRole("user")){//一般用户
	        	session.setAttribute("role", "user");
	        	System.out.println("----> You just have user role.");
	        }else {//房东、经销商或代理商
	        	session.setAttribute("role", "buyer");
	        	System.out.println("----> 你可能是一个代理商、经销商或房东.");
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
            	 System.out.println("UserID:"+UserID);
			 } catch (Exception e) {
			    System.out.println("用户不存在！");
			 }
             if (GlobalMethod.isNullorEmpty(sysUser)) {
	           System.out.println("登录名或密码不正确......");
	           return 0;
             }
             String errorCode = this.securService.doCheckUser(sysUser);
             System.out.println("errorCode:"+errorCode);
             if (errorCode != null) {
            	 if ("NO_POLIT".equals(errorCode))
            		 System.out.println("未配置栏目权限......");
				return -1;
             }else {
            	 System.out.println("sysUser:"+sysUser);
            	 CurrentUser currentUser01 = this.securService.createCurrentUser(sysUser);
 
            	 request.getSession().setAttribute("USER_INFO", currentUser01);
               	return UserID;
             }
   		 }

           @RequestMapping({"logout.do"})
           public String logout(HttpServletRequest request) {
	         System.out.println("行，我退出！");
	         request.getSession().removeAttribute("role");//清空session信息  
	         request.getSession().removeAttribute("userPhone");//清空session信息
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
//			    String hashAlgorithmName = "MD5";
//				Object credentials = userPwd;
////				Object salt = ByteSource.Util.bytes(userName);
//				Object salt = ByteSource.Util.bytes("username");
//				int hashIterations = 1024;
//				Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//				String userPwd1=result.toString();
				String userPwd1=shiroEncryption(userPwd);
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
		   public Map<String,String> getEmailCode(@RequestParam("email") String email,HttpServletRequest request) {
			   System.out.println("获取邮箱验证码!!!!!");
			   if (this.sysUserService.findByUserEmail(email) !=0) {//如果不为0，发送验证码
				   Map<String,String> map=new HashMap<String,String>();
				   int UserID = this.sysUserService.findByUserEmail(email);
				   map.put("UserID",UserID+"");
				   
				   int email_code = (int) ((Math.random() * 9 + 1) * 100000); //验证码服务端生成
				   String text="您的验证码是：" + email_code + "。请不要把验证码泄露给其他人。";
				   System.out.println("text>"+text);
				   
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
			   System.out.println("忘记密码-找回密码-手机|邮箱找回！");//验证码通过后便不在继续起作用
			   Md5 md5 = new Md5();
			   int UserID1=Integer.parseInt(UserID);
			   SysUser sysUser = (SysUser)this.sysUserService.findByKey(UserID1);
			   if (sysUser == null) {
				   return "error";
			   }else {
				   //shiro加密
				   sysUser.setLoginPwd(shiroEncryption(loginPwd));
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
		   
		   
		   /*
		    * 2018/2/26 
		    */
		   @RequestMapping({"homePage.do"})
		   @ResponseBody
		   public List<BoUserDevices> homePage(HttpServletRequest request) {
			   System.out.println("获取初始化的数据!");   
//			   String result="0";//失败用0表示
			   List varList=new ArrayList();
			   
			   List<BoUserDevices> boUserDevices=(List<BoUserDevices>)this.BoUserDevicesService.find();
			 //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boUserDevices.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }
			   for(int i=0;i<boUserDevices.size();i++) {
				   if(i>=0 && i<=9) {  
					   Map map=new HashMap();
					   int id=boUserDevices.get(i).getUserDeviceId();
//					   System.out.println("id:"+id);
					   String USER_NAME=boUserDevices.get(i).getBoUsers().getUserName();
//					   System.out.println("USER_NAME:"+USER_NAME);
					   String USER_PHONE=boUserDevices.get(i).getBoUsers().getUserPhone();
					   String DEVICE_CODE=boUserDevices.get(i).getBoDevice().getDeviceCode();
//					   System.out.println("DEVICE_CODE:"+DEVICE_CODE);
					   Date MNT_CREATOR_DATE=boUserDevices.get(i).getBoDevice().getMntCreatorDate();
//					   System.out.println("MNT_CREATOR_DATE:"+MNT_CREATOR_DATE);
					   Date MNT_UPDATED_DATE=boUserDevices.get(i).getBoDevice().getMntUpdatedDate();
//					   System.out.println("MNT_UPDATED_DATE:"+MNT_UPDATED_DATE);
					   String HOST_STATUS=boUserDevices.get(i).getBoDevice().getHostStatus();
					   String SIGNATURE=boUserDevices.get(i).getBoUsers().getSignature();
					   
					   map.put("id", id);
					   map.put("USER_NAME", USER_NAME);
			           map.put("USER_PHONE",USER_PHONE);
			           map.put("DEVICE_CODE",DEVICE_CODE);
			           map.put("MNT_CREATOR_DATE",MNT_CREATOR_DATE);
			           map.put("MNT_UPDATED_DATE", MNT_UPDATED_DATE);
			           map.put("HOST_STATUS", HOST_STATUS);
			           map.put("SIGNATURE", SIGNATURE);
			           varList.add(map);
				   }
//				   request.setAttribute("varList", varList);
//				   model.addAttribute("varList", varList);  
//				   result="1";//成功用1表示
			   }
//			   System.out.println("varList:"+varList);
			 //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   varList.add(page);
			   return varList;
		   }
		   /*
		    * 通过手机号查找房东的主页,包含分页操作  2018-5-2
		    * */
		   @RequestMapping({"buyerHomePage.do"})
		   @ResponseBody
		   public List<BoUserDevices> buyerHomePage(HttpServletRequest request,@RequestParam("userPhone") String userPhone) {
			   System.out.println("房东获取初始化的数据!");   
			   HttpSession session = request.getSession();
//			   String result="0";//失败用0表示
			   List varList=new ArrayList();
			   
			   List<BoUserDevices> boUserDevices=(List<BoUserDevices>)this.BoUserDevicesService.find();
			 //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boUserDevices.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }
			   int j=0,count=0;
			   for(int i=0;i<boUserDevices.size();i++) {
				   int id=boUserDevices.get(i).getUserDeviceId();
//					   System.out.println("id:"+id);
				   String USER_NAME=boUserDevices.get(i).getBoUsers().getUserName();
//					   System.out.println("USER_NAME:"+USER_NAME);
				   String USER_PHONE=boUserDevices.get(i).getBoUsers().getUserPhone();
				   String DEVICE_CODE=boUserDevices.get(i).getBoDevice().getDeviceCode();
//					   System.out.println("DEVICE_CODE:"+DEVICE_CODE);
				   Date MNT_CREATOR_DATE=boUserDevices.get(i).getBoDevice().getMntCreatorDate();
//					   System.out.println("MNT_CREATOR_DATE:"+MNT_CREATOR_DATE);
				   Date MNT_UPDATED_DATE=boUserDevices.get(i).getBoDevice().getMntUpdatedDate();
//					   System.out.println("MNT_UPDATED_DATE:"+MNT_UPDATED_DATE);
				   String HOST_STATUS=boUserDevices.get(i).getBoDevice().getHostStatus();
				   String SIGNATURE=boUserDevices.get(i).getBoUsers().getSignature();
				   //计算符合条件的总条数，然后得出总页数
				   if(userPhone.equals(USER_PHONE)) {
					   count++;
				   }
				   
				   if(userPhone.equals(USER_PHONE)) {
					   session.setAttribute("userPhone", userPhone);
					   j++;
					   Map map=new HashMap();
					   map.put("id", id);
					   map.put("USER_NAME", USER_NAME);
					   map.put("USER_PHONE",USER_PHONE);
//					   System.out.println("DEVICE_CODE:"+DEVICE_CODE);
					   map.put("DEVICE_CODE",DEVICE_CODE);
					   map.put("MNT_CREATOR_DATE",MNT_CREATOR_DATE);
					   map.put("MNT_UPDATED_DATE", MNT_UPDATED_DATE);
					   map.put("HOST_STATUS", HOST_STATUS);
					   map.put("SIGNATURE", SIGNATURE);
					   varList.add(map);
					   if(j>10) {
						   break;
					   }
				   }
			   }
			   if(count%pageSize!=0) {
				   totalPages=count/pageSize+1;
			   }else {
				   totalPages=count/pageSize;
			   }
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   varList.add(page);
			   System.out.println("varList:"+varList);
			   return varList;
		   }
		   
		   
		   @RequestMapping({"findUserDevicesByIndex.do"})
		   @ResponseBody
		   public List<BoUserDevices> findUserDevicesByIndex(@RequestParam("index") int index) { 			  			 
			   List varList=new ArrayList<BoUsers>();
			   List<BoUserDevices> boUserDevices=(List<BoUserDevices>)this.BoUserDevicesService.find();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boUserDevices.size();
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
			   for(int i=0;i<boUserDevices.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   Map map=new HashMap();
					   int id=boUserDevices.get(i).getUserDeviceId();
//					   System.out.println("id:"+id);
					   String USER_NAME=boUserDevices.get(i).getBoUsers().getUserName();
//					   System.out.println("USER_NAME:"+USER_NAME);
					   String USER_PHONE=boUserDevices.get(i).getBoUsers().getUserPhone();
					   String DEVICE_CODE=boUserDevices.get(i).getBoDevice().getDeviceCode();
//					   System.out.println("DEVICE_CODE:"+DEVICE_CODE);
					   Date MNT_CREATOR_DATE=boUserDevices.get(i).getBoDevice().getMntCreatorDate();
//					   System.out.println("MNT_CREATOR_DATE:"+MNT_CREATOR_DATE);
					   Date MNT_UPDATED_DATE=boUserDevices.get(i).getBoDevice().getMntUpdatedDate();
//					   System.out.println("MNT_UPDATED_DATE:"+MNT_UPDATED_DATE);
					   String HOST_STATUS=boUserDevices.get(i).getBoDevice().getHostStatus();
					   String SIGNATURE=boUserDevices.get(i).getBoUsers().getSignature();
					   
					   map.put("id", id);
					   map.put("USER_NAME", USER_NAME);
			           map.put("USER_PHONE",USER_PHONE);
			           map.put("DEVICE_CODE",DEVICE_CODE);
			           map.put("MNT_CREATOR_DATE",MNT_CREATOR_DATE);
			           map.put("MNT_UPDATED_DATE", MNT_UPDATED_DATE);
			           map.put("HOST_STATUS", HOST_STATUS);
			           map.put("SIGNATURE", SIGNATURE);
			           varList.add(map);
				   }
			   }
//			   System.out.println("varList:"+varList);
			 //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   varList.add(page);
			   return varList;
		   }
		   
		   @RequestMapping({"modifyBZ.do"})
		   @ResponseBody
		   public String modifyBZ(@RequestParam("signature") String signature,@RequestParam("id") String id) {//BoUserDevices id>BoUsers id
			   System.out.println("编辑备注！");
			   String result="error";
			   int id00=Integer.parseInt(id);//String 转 int
			   BoUserDevices boD=this.BoUserDevicesService.findByKey(id00);
//			   logger.info("boD:"+boD);//null
			   if(boD != null) {
				   int UserId=boD.getBoUsers().getUserId();
//				   logger.info("UserId:"+UserId);
				   BoUsers boUser=this.boUserssService.findByKey(UserId);
//				   logger.info("boUser:"+boUser);
				   boUser.setSignature(signature);
				   
				   BoUsers boUser01=this.boUserssService.update(boUser);
				   
				   if(boUser01 !=null) {
					   System.out.println("修改成功...");
					   result="success";
				   }else {
					   System.out.println("修改失败，请重新操作");
				   }
			   }
			   return result;
		   }
		   /*
		    * 模糊查找：通过昵称、手机号或用户名查找
		    * */
		   @RequestMapping({"findhomePageByNPC.do"})
		   @ResponseBody
		   public List<BoUserDevices> findhomePageByNPC(@RequestParam("npc") String npc,@RequestParam("status") int status,@RequestParam("index") int index){//ntc=userName+userPhone+deviceCode
			   List varList=new ArrayList<BoUsers>();
			   List<BoUserDevices> boUserDevices=(List<BoUserDevices>)this.BoUserDevicesService.find();
			   List<BoUserDevices> boUserDevicess=new ArrayList<BoUserDevices>();//用于存放满足条件的结果集
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int pageSize=page.getPageSize();
			   int totalPages=1;		   		   
			   int j=0;
			   //这个用来计算符合条件的数目 4-25
			   int count=0;
			   for(int i=0;i<boUserDevices.size();i++) {
				   String USER_NAME=boUserDevices.get(i).getBoUsers().getUserName();
				   String USER_PHONE=boUserDevices.get(i).getBoUsers().getUserPhone();
				   System.out.println(USER_PHONE);
				   String DEVICE_CODE=boUserDevices.get(i).getBoDevice().getDeviceCode();
				   if(npc.equals(USER_NAME) || npc.equals(USER_PHONE) || npc.equals(DEVICE_CODE)) {
					   boUserDevicess.add(boUserDevices.get(i));
					   count++;
				   }
			   }
//			   System.out.println("count:"+count);
			   int totalCount=count;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }
			   System.out.println("totalPages:"+totalPages);
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   //END
			   if(count > 0) {
				   for(int i=startRow;i<=endRow;i++) {
					   int id=boUserDevicess.get(i).getUserDeviceId();
					   String USER_NAME=boUserDevicess.get(i).getBoUsers().getUserName();
					   String USER_PHONE=boUserDevicess.get(i).getBoUsers().getUserPhone();
					   String DEVICE_CODE=boUserDevicess.get(i).getBoDevice().getDeviceCode();
//				   System.out.println("DEVICE_CODE:"+DEVICE_CODE);
					   Date MNT_CREATOR_DATE=boUserDevicess.get(i).getBoDevice().getMntCreatorDate();
					   Date MNT_UPDATED_DATE=boUserDevicess.get(i).getBoDevice().getMntUpdatedDate();
					   String HOST_STATUS=boUserDevicess.get(i).getBoDevice().getHostStatus();
					   String SIGNATURE=boUserDevicess.get(i).getBoUsers().getSignature();
					   
//				   if(npc.equals(USER_NAME) || npc.equals(USER_PHONE) || npc.equals(DEVICE_CODE)) {//关键：这里模糊匹配昵称、手机号或者主机序列号
					   j++;
					   Map map=new HashMap();
					   map.put("id", id);
					   map.put("USER_NAME", USER_NAME);
					   map.put("USER_PHONE",USER_PHONE);
					   System.out.println(USER_PHONE);
					   map.put("DEVICE_CODE",DEVICE_CODE);
					   map.put("MNT_CREATOR_DATE",MNT_CREATOR_DATE);
					   map.put("MNT_UPDATED_DATE", MNT_UPDATED_DATE);
					   map.put("HOST_STATUS", HOST_STATUS);
					   map.put("SIGNATURE", SIGNATURE);
					   varList.add(map);
					   
					   //存在数据时输入,且在第一次时执行
					   if(j==1) {
						   page.setCurrentPage(index);
						   page.setTotalPages(totalPages);
						   varList.add(page);
					   }
//				   }
				   }
			   }
//			   System.out.println("varList:"+varList);
			   return varList;
		   }
		   /*
		    * 通过状态查找,包含分页操作  2018-4-25
		    * */
		   @RequestMapping({"findhomePageByStatus.do"})
		   @ResponseBody
		   public List<BoUserDevices> findhomePageByStatus(@RequestParam("status") int status,@RequestParam("index") int index) {//status》主机》特定状态的首页数据    
			   List varList=new ArrayList<BoUsers>();
			   List<BoUserDevices> boUserDevices=null;
			   if(status == 2) {
				   boUserDevices=(List<BoUserDevices>)this.BoUserDevicesService.find();
			   }else {
				   boUserDevices=(List<BoUserDevices>)this.BoUserDevicesService.findByStatus(status);
			   }
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boUserDevices.size();//这个也是符合条件的数据总条数
			   System.out.println("totalCount:"+totalCount);
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }
			   System.out.println("totalPages:"+totalPages);
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
			   System.out.println("startRow:"+startRow);
			   System.out.println("endRow:"+endRow);
			   int j=0;//用于判断是不是第一次，第一次设置Page对象，其余的就省略该操作
			   for(int i=startRow;i<=endRow;i++) {//无论是“全部”、“在线”或者“离线”都可以用这个
				   int id=boUserDevices.get(i).getUserDeviceId();
				   String USER_NAME=boUserDevices.get(i).getBoUsers().getUserName();
				   String USER_PHONE=boUserDevices.get(i).getBoUsers().getUserPhone();
				   String DEVICE_CODE=boUserDevices.get(i).getBoDevice().getDeviceCode();
//					   System.out.println("DEVICE_CODE:"+DEVICE_CODE);
				   Date MNT_CREATOR_DATE=boUserDevices.get(i).getBoDevice().getMntCreatorDate();
				   Date MNT_UPDATED_DATE=boUserDevices.get(i).getBoDevice().getMntUpdatedDate();
				   String HOST_STATUS=boUserDevices.get(i).getBoDevice().getHostStatus();
				   String SIGNATURE=boUserDevices.get(i).getBoUsers().getSignature();
				   j++;
				   Map map=new HashMap();
				   map.put("id", id);
				   map.put("USER_NAME", USER_NAME);
				   map.put("USER_PHONE",USER_PHONE);
//				   System.out.println("1或0:"+USER_PHONE);
				   map.put("DEVICE_CODE",DEVICE_CODE);
				   map.put("MNT_CREATOR_DATE",MNT_CREATOR_DATE);
				   map.put("MNT_UPDATED_DATE", MNT_UPDATED_DATE);
				   map.put("HOST_STATUS", HOST_STATUS);
				   map.put("SIGNATURE", SIGNATURE);
				   varList.add(map);
				 //存在数据时输入,且在第一次时执行
				   if(j==1) {
					   page.setCurrentPage(index);
					   page.setTotalPages(totalPages);
					   varList.add(page);
				   }
			   }
//			   System.out.println("varList:"+varList);
			   return varList;
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
		   public Map<String, String> findUserInfo(@RequestParam("USER_ID") int USER_ID,HttpServletRequest request) {
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
		   public String modifyUserInfo(@RequestParam("USER_ID") String USER_ID,@RequestParam("loginName") String loginName,@RequestParam("loginPwd") String loginPwd ,@RequestParam("userName") String userName,@RequestParam("userPhone") String userPhone,@RequestParam("email") String email,HttpServletRequest request) {//这个得怎么返回？？？map  
			   System.out.println("编辑个人信息！");
			   int userId=Integer.parseInt(USER_ID);
			   SysUser sysuser=this.sysUserService.findByKey(userId);//找到用户
			 //对传过来的旧密码进行shiro加密
//			   String hashAlgorithmName = "MD5";
//			   Object credentials = loginPwd;
////			   Object salt = ByteSource.Util.bytes(loginName);
//			   Object salt = ByteSource.Util.bytes("username");
//			   int hashIterations = 1024;
//			   Object userPwd1 = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//			   String loginPwd1=userPwd1.toString();
			   String loginPwd1=shiroEncryption(loginPwd);
			   
			   
//			   System.out.println("sysuser="+sysuser);
			   sysuser.setLoginName(loginName);
			   sysuser.setLoginPwd(loginPwd1);
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
			   if(sysuser != null) {
				   //取出密码
				   String pwd=sysuser.getLoginPwd();
				   System.out.println("从数据库中取出来的密码："+pwd);
				   String userName=sysuser.getLoginName();//用户名
				   System.out.println("userName:"+userName);
				   //对传过来的旧密码进行shiro加密
//				   String hashAlgorithmName = "MD5";
//				   Object credentials = oldpassword;
////				   Object salt = ByteSource.Util.bytes(userName);
//				   Object salt = ByteSource.Util.bytes("username");
//				   int hashIterations = 1024;
//				   Object userPwd1 = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
//				   String loginPwd=userPwd1.toString();
				   String loginPwd=shiroEncryption(oldpassword);
				   System.out.println("shiro加密后密码："+loginPwd);
				   System.out.println("旧密码和取出来的密码对比："+loginPwd.equals(pwd));
				   //加密后的旧密码与取出来的对比，若正确则修改新密码
				   if(loginPwd.equals(pwd)) {
					   System.out.println("修改密码...");
//					   Object credentials01 = newpassword;
//					   Object userPwd2 = new SimpleHash(hashAlgorithmName, credentials01, salt, hashIterations);
//					   String loginPwd2=userPwd2.toString();
					   String loginPwd2=shiroEncryption(newpassword);
					   System.out.println("新密码加密后："+loginPwd2);
					   sysuser.setLoginPwd(loginPwd2);
					   this.sysUserService.update(sysuser);
					   result="success";
				   }else {
					   System.out.println("旧密码输入有误");
				   }
			   }else {
				   System.out.println("异常状况");
			   }
			   return result;		   
		   }
		   
		   /*
		    * 用户管理模块-全部用户【管理员】
		    */
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
		   
		   @RequestMapping({"addUser.do"})
		   @ResponseBody
		   public String addUser(@RequestParam("name") String name,@RequestParam("userPhone") String userPhone,@RequestParam("email") String email,@RequestParam("password") String password) {
//			   System.out.println("name:"+name+",email:"+email+",password:"+password+",userPhone:"+userPhone);
			   String result="success";
			   Md5 md5=new Md5();
			   BoUsers user = UserUtil.save(userPhone, md5.getMD5ofStr(password), email);
			   user.setUserName(name);
			   BoUsers save=this.boUserssService.save(user);//save可以用于更新bo_users表
			   if(save == null) {
				   result="fail";
			   }
		       return result;
		    }
		   
		   /*
		    * 用户管理模块-全部用户【房东】 5-3
		    */
		   @RequestMapping({"findUsersByPhone.do"})
		   @ResponseBody
		   public List<BoUsers> findUsersByPhone(@RequestParam("userPhone") String userPhone) {
			   List varList=new ArrayList<BoUsers>();
			   BoUsers bouser = this.boUserssService.findByUserPhone(userPhone);//找到主账户
			   String userCode=bouser.getUserCode();
			   List<BoUsers> bousers=this.boUserssService.getAllList();
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=bousers.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   int count=0;
			   for(int i=0;i<bousers.size();i++) {
				   if(userCode.equals(bousers.get(i).getAuthorizationUserCode())) {
					   count++;
				   }
			   }
			   System.out.println("count:"+count);
			   if(count%pageSize !=0){
				   totalPages = count/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = count/pageSize;
			   }
			   int j=0;
			   for(int i=0;i<bousers.size();i++) {
				   if(userCode.equals(bousers.get(i).getAuthorizationUserCode())) {
					   j++;
					   BoUsers user=new BoUsers();
					   user.setUserId(bousers.get(i).getUserId());
//					   System.out.println(bousers.get(i).getUserId());
					   user.setUserName(bousers.get(i).getUserName());
					   user.setUserSex(bousers.get(i).getUserSex());
					   user.setUserPhone(bousers.get(i).getUserPhone());
					   user.setPhoneType(bousers.get(i).getPhoneType());
					   user.setVersionType(bousers.get(i).getVersionType());
					   user.setSignature(bousers.get(i).getSignature());
					   user.setCity(bousers.get(i).getCity());
					   varList.add(user);
					   if(j>10) {
						   break;
					   }
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   varList.add(page);
		       return varList;
		    }
		   
		   @RequestMapping({"findByTel.do"})//再页面上是以json的形式显示                     4-26针对没有找到时的情况做了处理
		   @ResponseBody
		   public List<BoUsers> findByTel(@RequestParam("userPhone") String userPhone) {
			   List<BoUsers> varList=new ArrayList<BoUsers>();
			   BoUsers bousers = this.boUserssService.findByUserPhone(userPhone);
			   if(bousers != null) {
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
			   }
			   System.out.println("varList:"+varList);
		       return varList;
		    }
		   
		   
		   /*
		    * 分页显示全部用户
		    */
		   @RequestMapping({"findByIndex.do"})//再页面上是以json的形式显示
		   @ResponseBody
		   public List<BoUsers> findByIndex(@RequestParam("index") int index) {
			   List varList=new ArrayList<BoUsers>();
			   List<BoUsers> bousers=this.boUserssService.getAllList();
			   
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   int totalCount=bousers.size();
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
//			   System.out.println("totalPages:"+totalPages);
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
		   
		   @RequestMapping({"changeAccount.do"})
		   @ResponseBody
		   public String changeAccount(@RequestParam("oldPhone") String oldPhone,@RequestParam("newPhone") String newPhone) {
			   String result="success";
			   Md5 md5 = new Md5();
			   //找到新号码对应的用户，取出密码
			   BoUsers newTel=this.boUserssService.findByUserPhone(newPhone);
			   String pwd="";
			   String userCode="";
			   String headPic="";
			   String userName="";
			   String signature="";
			   String sex="";
			   String mail="";
			   if(newTel != null) {
//					logger.info("newTel!=null");
					pwd=newTel.getUserPwd();
					userCode=newTel.getUserCode();
					headPic=newTel.getHeadPic();
					userName=newTel.getUserName();
					signature=newTel.getSignature();
					sex=newTel.getUserSex();
					mail=newTel.getUserEmail();
				}
			   BoUsers oldTel=this.boUserssService.findByUserPhone(oldPhone);
			   String oldPWD=oldTel.getUserPwd();
			   String oldHeadPic=oldTel.getHeadPic();
			   String oldUserName=oldTel.getUserName();
			   String oldSignature=oldTel.getSignature();
			   String oldSex=oldTel.getUserSex();
			   String oldMail=oldTel.getUserEmail();
			   if(newTel != null) {
				   int userId=newTel.getUserId();
				   //找到旧号码对应的用户,将该用户的号码和密码换成新用户的
				   if(oldTel != null) {
					   oldTel.setUserPhone(newPhone);
					   oldTel.setUserPwd(pwd);
					   oldTel.setHeadPic(headPic);
					   oldTel.setUserName(userName);
					   oldTel.setSignature(signature);
					   oldTel.setUserSex(sex);
					   oldTel.setUserEmail(mail);
				   }
				   BoUsers update=this.boUserssService.update(oldTel);//此时新旧两个账号的号码都是新的号码，新账号应该初始化（存放旧号码）==最终目的：老账号-新号码，删除旧账号，注册账号
					BoUsers newTel01=this.boUserssService.findByKey(userId);
					if(update != null) {
//						logger.info("update!=null");
						//不过不排除新用户加了情景模式  得先检查是否有情景模式，然后删除新用户
						List<BoModel> boModels=this.boModelService.getListBy(userCode);
//						logger.info("boModels:"+boModels);
						   for(BoModel boModel:boModels) {
							   List<BoModelInfo> boModelInfos=this.boModelInfoServicess.getBy(userCode, boModel.getModelId());
							   for(BoModelInfo boModelInfo:boModelInfos) {
								   this.boModelInfoServicess.delete(boModelInfo);
							   }
							   this.boModelService.delete(boModel);
						   }
						//若新账号已经绑定主机等（有关联表）>删除设备及主机
						if(! "".equals(newTel.getAuthorizationUserCode())) {//次账户
//							logger.info("此账户设备删除");
							List<BoRoom> borooms=this.boRoomService.getAllListByUserCode(newTel.getAuthorizationUserCode());//找到授权者对应的所有房间
							for(BoRoom boroom:borooms) {
								String rCode=boroom.getRoomCode();
//								logger.info("要删除设备的房间："+boroom.getRoomName());
								List<BoHostDevice> bhs=this.boHostDeviceService.getroomCode(userCode,rCode);//被授权者userCode+授权者的房间roomCode
								if(bhs.size()>0) {
									for(BoHostDevice bh:bhs) {
										this.boHostDeviceService.delete(bh);
									}				
								}
							}
						}else {//主账号
//							logger.info("主账户设备删除");
							List<BoRoom> borooms=this.boRoomService.getAllListByUserCode(userCode);//找到授权者对应的所有房间
							for(BoRoom boroom:borooms) {
								String rCode=boroom.getRoomCode();
//								logger.info("要删除设备的房间："+boroom.getRoomName());
								List<BoHostDevice> bhs=this.boHostDeviceService.getroomCode(userCode,rCode);//被授权者userCode+授权者的房间roomCode
								if(bhs.size()>0) {
									for(BoHostDevice bh:bhs) {
										this.boHostDeviceService.delete(bh);
									}				
								}
							}
						}  
						//删除用户和主机的关联表
						List<BoUserDevices> boUserDevices=this.boUserDevicesServicess.getBy(userCode);
//						logger.info("boUserDevices:"+boUserDevices);
						if(boUserDevices.size() > 0) {
							for(BoUserDevices boUserDevice:boUserDevices) {
								this.boUserDevicesServicess.delete(boUserDevice);
							}
						}
						//删除新账号
						BoUsers del=this.boUserssService.delete(newTel01);
						   //注册新账号（放入老账号的手机号、密码以及头像等信息）
						   BoUsers user = UserUtil.save(oldPhone, oldPWD, "");
						   user.setHeadPic(oldHeadPic);
						   user.setUserName(oldUserName);
						   user.setSignature(oldSignature);
						   user.setUserSex(oldSex);
						   user.setUserEmail(oldMail);
//					        this.boUserServicess.update(save);
						   BoUsers save = (BoUsers)this.boUserServicess.save(user);
						   if(save != null) {
								//注册成功时 默认添加一个楼层和四个房间
								BoFloor floor=FloorUtil.save(save.getUserCode());
								BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
								//String userCode,String floorName,String floorCode,String roomName	
//								System.out.println("楼层名称："+saveF.getFloorName());
								String uCode=saveF.getUserCode();
								String floorName=saveF.getFloorName();
//								String floorName="我的家";
								String floorCode=saveF.getFloorCode();
								BoRoom room1=RoomUtil.save(uCode,floorName,floorCode,"客厅");
								BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
								BoRoom room2=RoomUtil.save(uCode,floorName,floorCode,"卧室");
								BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
								BoRoom room3=RoomUtil.save(uCode,floorName,floorCode,"厨房");
								BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
								BoRoom room4=RoomUtil.save(uCode,floorName,floorCode,"卫生间");
								BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
							}else {
								result="fail";
							}
					}else {
						result="fail";
					}
			   }else {//新用户未注册
				 //将该用户的号码和密码换成新用户的
					oldTel.setUserPhone(newPhone);
					oldTel.setUserPwd(md5.getMD5ofStr("888888"));//初始密码：888888
					oldTel.setHeadPic(headPic);
					oldTel.setUserName(userName);
					oldTel.setSignature(signature);
					oldTel.setUserSex(sex);
					oldTel.setUserEmail(mail);
					BoUsers update=this.boUserssService.update(oldTel);
//					logger.info("update=="+update);
					if(update==null) {
						result="fail";
					}else {
						//注册（把旧账号的号码、密码和头像放进去）
						BoUsers user = UserUtil.save(oldPhone, oldPWD, "");
						user.setHeadPic(oldHeadPic);
						user.setUserName(oldUserName);
						user.setSignature(oldSignature);
						user.setUserSex(oldSex);
						user.setUserEmail(oldMail);
						BoUsers save = (BoUsers)this.boUserServicess.save(user);
						if(save != null) {
							//注册成功时 默认添加一个楼层和四个房间
							BoFloor floor=FloorUtil.save(save.getUserCode());
							BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
							//String userCode,String floorName,String floorCode,String roomName	
							System.out.println("楼层名称："+saveF.getFloorName());
							String uCode=saveF.getUserCode();
							String floorName=saveF.getFloorName();
//						String floorName="我的家";
							String floorCode=saveF.getFloorCode();
							BoRoom room1=RoomUtil.save(uCode,floorName,floorCode,"客厅");
							BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
							BoRoom room2=RoomUtil.save(uCode,floorName,floorCode,"卧室");
							BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
							BoRoom room3=RoomUtil.save(uCode,floorName,floorCode,"厨房");
							BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
							BoRoom room4=RoomUtil.save(uCode,floorName,floorCode,"卫生间");
							BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
						}else {
							result="fail";
						}
					}
			   }
			   
			   return result;
		   }
		   /*
		    * 删除用户
		    */
		   @RequestMapping({"delUser.do"})
		   @ResponseBody
		   public String delUser(@RequestParam("id") String id) {
			   String result="success";
			   int ID=Integer.parseInt(id);
			   BoUsers boUser=this.boUserssService.findByKey(ID);
			   if(boUser != null) {
				   //若是有外键关联，先清除那些数据
				 //1) 得先检查是否有情景模式以及情景模式下是否有设备，有则删除（先删除BoModelInfo,然后是BoModel）
					List<BoModel> boModels=this.boModelService.getListBy(boUser.getUserCode());
					if("".equals(boUser.getAuthorizationUserCode())) {//主账户
						for(BoModel boModel:boModels) {
							List<BoModelInfo> boModelInfos=this.boModelInfoServicess.getBy(boUser.getUserCode(), boModel.getModelId());
							for(BoModelInfo boModelInfo:boModelInfos) {
								this.boModelInfoServicess.delete(boModelInfo);
							}
							this.boModelService.delete(boModel);
						}
						//2)删除主账户下的设备
						List<BoRoom> borooms=this.boRoomService.getAllListByUserCode(boUser.getUserCode());//找到授权者对应的所有房间
						for(BoRoom boroom:borooms) {
							String rCode=boroom.getRoomCode();
							List<BoHostDevice> bhs=this.boHostDeviceService.getroomCode(boUser.getUserCode(),rCode);//被授权者userCode+授权者的房间roomCode
							if(bhs.size()>0) {
								for(BoHostDevice bh:bhs) {
									this.boHostDeviceService.delete(bh);
								}				
							}
						}
						//3）删除没有room的设备 这方法属于异常处理
						List<BoHostDevice> bhs=this.boHostDeviceService.getUserCode(boUser.getUserCode());
						for(BoHostDevice bh:bhs) {
							this.boHostDeviceService.delete(bh);
						}
						//4）删除bo_device_state关联数据
						List<BoDeviceState> boDeviceStates=this.boDeviceStateService.getByuserCode(boUser.getUserCode());
						for(BoDeviceState boDeviceState:boDeviceStates) {
							this.boDeviceStateService.delete(boDeviceState);
						}
						//5)删除用户和主机的关联表（bo_user_devices）
						
						List<BoUserDevices> boUserDevices=this.boUserDevicesServicess.getBy(boUser.getUserCode());
						if(boUserDevices.size() > 0) {
							for(BoUserDevices boUserDevice:boUserDevices) {
								//清理bo_device_state关联表数据,删除用户和主机的关联表
								List<BoDeviceState> boDeviceStates1=this.boDeviceStateService.getListBy(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoDeviceState boDeviceState:boDeviceStates1) {
									this.boDeviceStateService.delete(boDeviceState);
								}
								//清理bo_infrared_buttons表格的关联数据
								List<BoInfraredButtons> boInfraredButtons=this.boInfraredButtonsService.getListBys(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoInfraredButtons boInfraredButton:boInfraredButtons) {
									this.boInfraredButtonsService.delete(boInfraredButton);
								}
								//清理bo_infrared_learn_control_map
								List<BoInfraredLearnControlMap> boInfraredLearnControlMaps=this.boInfraredLearnControlMapService.infraredLearnControlMapList(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoInfraredLearnControlMap boInfraredLearnControlMap:boInfraredLearnControlMaps) {
									this.boInfraredLearnControlMapService.delete(boInfraredLearnControlMap);
								}
								//清理bo_sensor关联数据
								List<BoSensor> boSensors=this.boSensorService.gets(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoSensor boSensor:boSensors) {
									this.boSensorService.delete(boSensor);
								}
								this.boUserDevicesServicess.delete(boUserDevice);
							}
						}
					}else {
						//次账户
						for(BoModel boModel:boModels) {
							List<BoModelInfo> boModelInfos=this.boModelInfoServicess.getBy(boUser.getAuthorizationUserCode(), boModel.getModelId());
							for(BoModelInfo boModelInfo:boModelInfos) {
								this.boModelInfoServicess.delete(boModelInfo);
							}
							this.boModelService.delete(boModel);
						}
						//2)删除次账户下的设备
						List<BoRoom> borooms=this.boRoomService.getAllListByUserCode(boUser.getAuthorizationUserCode());//找到授权者对应的所有房间
						for(BoRoom boroom:borooms) {
							String rCode=boroom.getRoomCode();
//							logger.info("要删除设备的房间："+boroom.getRoomName());
							List<BoHostDevice> bhs=this.boHostDeviceService.getroomCode(boUser.getUserCode(),rCode);//被授权者userCode+授权者的房间roomCode
							if(bhs.size()>0) {
								for(BoHostDevice bh:bhs) {
									this.boHostDeviceService.delete(bh);
								}				
							}
						}
						//3)次账户自己的设备（次账户解绑后也有可能有自己的设备）
						List<BoRoom> borooms1=this.boRoomService.getAllListByUserCode(boUser.getUserCode());//找到授权者对应的所有房间
						for(BoRoom boroom:borooms1) {
							String rCode=boroom.getRoomCode();
//							logger.info("要删除设备的房间："+boroom.getRoomName());
							List<BoHostDevice> bhs=this.boHostDeviceService.getroomCode(boUser.getUserCode(),rCode);//被授权者userCode+授权者的房间roomCode
							if(bhs.size()>0) {
								for(BoHostDevice bh:bhs) {
									this.boHostDeviceService.delete(bh);
								}				
							}
						}
						//4）删除没有room的设备 这方法属于异常处理
						List<BoHostDevice> bhs=this.boHostDeviceService.getUserCode(boUser.getUserCode());
						for(BoHostDevice bh:bhs) {
							this.boHostDeviceService.delete(bh);
						}
						//5）清理bo_device_state关联表数据,删除用户和主机的关联表
						List<BoDeviceState> boDeviceStates=this.boDeviceStateService.getByuserCode(boUser.getUserCode());
						for(BoDeviceState boDeviceState:boDeviceStates) {
							this.boDeviceStateService.delete(boDeviceState);
						}
						
						List<BoUserDevices> boUserDevices=this.boUserDevicesServicess.getBy(boUser.getUserCode());
						if(boUserDevices.size() > 0) {
							for(BoUserDevices boUserDevice:boUserDevices) {
								//清理bo_device_state关联表数据,删除用户和主机的关联表
								List<BoDeviceState> boDeviceStates1=this.boDeviceStateService.getListBy(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoDeviceState boDeviceState:boDeviceStates1) {
									this.boDeviceStateService.delete(boDeviceState);
								}
								//清理bo_infrared_buttons表格的关联数据
								List<BoInfraredButtons> boInfraredButtons=this.boInfraredButtonsService.getListBys(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoInfraredButtons boInfraredButton:boInfraredButtons) {
									this.boInfraredButtonsService.delete(boInfraredButton);
								}
								//清理bo_infrared_learn_control_map
								List<BoInfraredLearnControlMap> boInfraredLearnControlMaps=this.boInfraredLearnControlMapService.infraredLearnControlMapList(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoInfraredLearnControlMap boInfraredLearnControlMap:boInfraredLearnControlMaps) {
									this.boInfraredLearnControlMapService.delete(boInfraredLearnControlMap);
								}
								//清理bo_sensor关联数据
								List<BoSensor> boSensors=this.boSensorService.gets(boUser.getUserCode(), boUserDevice.getBoDevice().getDeviceCode());
								for(BoSensor boSensor:boSensors) {
									this.boSensorService.delete(boSensor);
								}
								this.boUserDevicesServicess.delete(boUserDevice);
							}
						}
					}
				   //删除该用户
				   this.boUserssService.delete(boUser);
			   }else {
				   result="fail";
			   }
			   return result;
		   }
		   
		   /*
		    * 用户管理模块-登录验证码
		    */
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
		   
		   /*
		    * 修改
		    */
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

	   /*
	    * 删除
	    */
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
		 
		 /*
		  * 登录验证码 导出到Excel
		  */
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
		        if(validation != null) {
		        	list.add(validation);
		        }
		        return list;
		    }
		   
		   /*
		    * 主机管理模块-全部主机[管理员]
		    */
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
			   System.out.println("totalPages:"+totalPages);
			   
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
		   
		   /*
		    * 主机管理模块-全部主机[房东]
		    */
		   
		   @RequestMapping({"findHostDevicesByPhone.do"})
		   @ResponseBody
		   public List<BoDevice> findHostDevicesByPhone(@RequestParam("userPhone") String userPhone) {//deviceCode,status,type
			   List list=new ArrayList<BoDevice>();
			   BoUsers bouser = this.boUserssService.findByUserPhone(userPhone);//找到主账户
			   String userCode=bouser.getUserCode();
			   List<BoUserDevices> buds=this.BoUserDevicesService.getListByDeviceCodes(userCode);
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=buds.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
			   System.out.println("totalPages:"+totalPages+",totalCount:"+totalCount);
			   for(BoUserDevices boUserDevice:buds) {
					   BoDevice user=new BoDevice();
			        	user.setDeviceId(boUserDevice.getBoDevice().getDeviceId());
					    user.setDeviceCode(boUserDevice.getBoDevice().getDeviceCode());
					    System.out.println("deviceCode:"+boUserDevice.getBoDevice().getDeviceCode());
					    user.setStatus(boUserDevice.getBoDevice().getStatus());
					    user.setType(boUserDevice.getBoDevice().getType());
					    list.add(user);
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   /*
		    * 取出在线人数和总人数
		    */
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
		   
		   /*
		    * 分页操作 应和status有关
		    */
		   @RequestMapping({"findDevicesByIndex.do"})
		   @ResponseBody
		   public List<BoDevice> findDevicesByIndex(@RequestParam("index") int index,@RequestParam("status") int status,HttpServletRequest request) {//index+status
			   List list=new ArrayList<BoDevice>();
			   HttpSession session = request.getSession();
			   String phone=(String) session.getAttribute("userPhone");
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int pageSize=page.getPageSize();
			   int totalPages=1,totalCount=0;
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   
			   if(phone == null) {//管理员
				   List<BoDevice> bodevices=new ArrayList();
				   if(status == 2) {
					   bodevices = this.boDeviceService.getAllHostDevices();  
				   }else {
					   bodevices = this.boDeviceService.findByStatus(status); 
				   }
				   totalCount=bodevices.size();
				   if(totalCount%pageSize !=0){
					   totalPages = totalCount/pageSize+1;           //只要有小数都+1
				   }else {
					   totalPages = totalCount/pageSize;
				   }	
				   if(index == totalPages) {
					   endRow=totalCount-1;
				   }else {
					   endRow=index*pageSize - 1;
				   }
				   for(int i=0;i<bodevices.size();i++) {
					   if(i>=startRow && i<=endRow) {
						   BoDevice user=new BoDevice();
						   user.setDeviceId(bodevices.get(i).getDeviceId());
						   user.setDeviceCode(bodevices.get(i).getDeviceCode());
						   user.setStatus(bodevices.get(i).getStatus());
						   user.setType(bodevices.get(i).getType());
						   list.add(user);
					   }
				   }
			   }else{//房东
				   BoUsers bouser = this.boUserssService.findByUserPhone(phone);
				   String userCode=bouser.getUserCode();
				   if(status == 2) {
					   List<BoUserDevices> bodevices = this.BoUserDevicesService.getListByDeviceCodes(userCode);
					   totalCount=bodevices.size();
					   if(totalCount%pageSize !=0){
						   totalPages = totalCount/pageSize+1;           //只要有小数都+1
					   }else {
						   totalPages = totalCount/pageSize;
					   }	
					   System.out.println("totalPages:"+totalPages+",totalCount:"+totalCount);
					   if(index == totalPages) {
						   endRow=totalCount-1;
					   }else {
						   endRow=index*pageSize - 1;
					   }
					   for(int i=0;i<bodevices.size();i++) {
						   if(i>=startRow && i<=endRow) {
							   BoDevice user=new BoDevice();
							   user.setDeviceId(bodevices.get(i).getBoDevice().getDeviceId());
							   user.setDeviceCode(bodevices.get(i).getBoDevice().getDeviceCode());
//						    System.out.println("deviceCode:"+bodevices.get(i).getBoDevice().getDeviceCode());
							   user.setStatus(bodevices.get(i).getBoDevice().getStatus());
							   user.setType(bodevices.get(i).getBoDevice().getType());
							   list.add(user);
						   }
					   }
				   }else {
					   List<BoDevice> bodevicess = this.boDeviceService.findByStatus(status); 
					   totalCount=bodevicess.size();
					   if(totalCount%pageSize !=0){
						   totalPages = totalCount/pageSize+1;           //只要有小数都+1
					   }else {
						   totalPages = totalCount/pageSize;
					   }	
					   System.out.println("totalPages:"+totalPages+",totalCount:"+totalCount);
					   if(index == totalPages) {
						   endRow=totalCount-1;
					   }else {
						   endRow=index*pageSize - 1;
					   }
					   for(int i=0;i<bodevicess.size();i++) {
						   if(i>=startRow && i<=endRow) {
							   BoDevice user=new BoDevice();
							   user.setDeviceId(bodevicess.get(i).getDeviceId());
							   user.setDeviceCode(bodevicess.get(i).getDeviceCode());
//						    System.out.println("deviceCode:"+bodevicess.getBoDevice().getDeviceCode());
							   user.setStatus(bodevicess.get(i).getStatus());
							   user.setType(bodevicess.get(i).getType());
							   list.add(user);
						   }
					   }
				   }
			   }
//			   System.out.println("totalCount:"+totalCount);
//			   System.out.println("totalPages:"+totalPages);
//			   System.out.println("startRow:"+startRow);
//			   System.out.println("endRow:"+endRow);
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
		       if(bodevices != null) {
		    	   for(BoDevice bodevice:bodevices) {
		    		   BoDevice user=new BoDevice();
			        	user.setDeviceId(bodevice.getDeviceId());
					    user.setDeviceCode(bodevice.getDeviceCode());
					    user.setStatus(bodevice.getStatus());
					    user.setType(bodevice.getType());
					    list.add(user);
		    	   }
		       }
		       System.out.println("list:"+list);
		        return list;
		    }
		   @RequestMapping({"findByStatus.do"})
		   @ResponseBody
		   public List<BoDevice> findByStatus(@RequestParam("status") int status) {//加 分页处理 2018/1/16
			   /*System.out.println("hhe");*/
//			   System.out.println("status:"+status);
			   System.out.println("分页操作 -ByStatus");
			   List list=new ArrayList();
		       List<BoDevice> bodevices = this.boDeviceService.findByStatus(status); 
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
			   System.out.println("totalPages:"+totalPages);
			   if(bodevices !=null) {
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
			   }
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
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
		 //主机管理模块-用户绑定与主机解绑【管理员】    关联bo_user_devices表
		   @RequestMapping({"showUserDevices.do"})
		   @ResponseBody
		   public List<BoUserDevices> showUserDevices() {
			   List list=new ArrayList();
		       List<BoUserDevices> boUserDevices = this.BoUserDevicesService.find();//找到所有主机
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boUserDevices.size();
			   int pageSize=page.getPageSize();
			   int totalPages=1;
			   if(totalCount%pageSize !=0){
				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
			   }else {
				   totalPages = totalCount/pageSize;
			   }	
//			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boUserDevices.size();i++) {
				   if(i>=0 && i<=9) {
					   Map map=new HashMap();
			        	int id=boUserDevices.get(i).getUserDeviceId();
			        	String deviceCode=boUserDevices.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boUserDevices.get(i).getBoUsers().getUserPhone();
			        	String nick_name=boUserDevices.get(i).getNickName();
			        	
			        	int DEVICE_ID=boUserDevices.get(i).getBoDevice().getDeviceId();
			        	int USER_ID=boUserDevices.get(i).getBoUsers().getUserId();
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
		   
		 //主机管理模块-用户绑定与主机解绑【房东】
		   @RequestMapping({"findHostDByPhone.do"})
		   @ResponseBody
		   public List<BoHostDevice> findHostDByPhone(@RequestParam("userPhone") String userPhone) {
			   List list=new ArrayList();
			   BoUsers bouser = this.boUserssService.findByUserPhone(userPhone);//找到主账户
			   String userCode=bouser.getUserCode();
			   List<BoHostDevice> boHosts=this.boHostDeviceService.getUserCode(userCode);
			   
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
			        	String nick_name=boHosts.get(i).getNickName();
			        	
			        	int DEVICE_ID=boHosts.get(i).getBoDevice().getDeviceId();
			        	int USER_ID=boHosts.get(i).getBoUsers().getUserId();
			        	String deviceAddress=boHosts.get(i).getDeviceAddress();
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("nick_name",nick_name);
			        	map.put("DEVICE_ID", DEVICE_ID);
			        	map.put("deviceAddress",deviceAddress);
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
		   public List<BoUserDevices> findudeviceByIndex(@RequestParam("index") int index,HttpServletRequest request) {//分页考虑是管理员还是房东
			   HttpSession session = request.getSession();
			   String phone=(String) session.getAttribute("userPhone");
//			   System.out.println("userPhone:"+phone);
			   List list=new ArrayList();
			   
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int pageSize=page.getPageSize();
			   int totalPages=1,totalCount=0;
			   List<BoUserDevices> boUserDevices=new ArrayList();
			   if(phone == null) {
//				   System.out.println("null");
				   boUserDevices = this.BoUserDevicesService.find();		  //直接把这结果传给前台 出现Cannot call sendError() after the response has been committed     
				   //Page类
				   totalCount=boUserDevices.size();
				   if(totalCount%pageSize !=0){
					   totalPages = totalCount/pageSize+1;           //只要有小数都+1
				   }else {
					   totalPages = totalCount/pageSize;
				   }	
			   }else {
//				   System.out.println("not null");
				   BoUsers bouser = this.boUserssService.findByUserPhone(phone);
				   String userCode=bouser.getUserCode();
				   boUserDevices=this.BoUserDevicesService.getBy(userCode);
				   totalCount=boUserDevices.size();
				   if(totalCount%pageSize !=0){
					   totalPages = totalCount/pageSize+1;           //只要有小数都+1
				   }else {
					   totalPages = totalCount/pageSize;
				   }
			   }
			   
			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
			   int endRow;
			   if(index == totalPages) {
				   endRow=totalCount-1;
			   }else {
				   endRow=index*pageSize - 1;
			   }
//			   System.out.println("startRow:"+startRow);
//			   System.out.println("endRow:"+endRow);
//			   System.out.println("totalCount:"+totalCount);
//			   System.out.println("totalPages:"+totalPages);
			   
			   for(int i=0;i<boUserDevices.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   Map map=new HashMap();
			        	int id=boUserDevices.get(i).getUserDeviceId();
			        	String deviceCode=boUserDevices.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boUserDevices.get(i).getBoUsers().getUserPhone();
			        	String nick_name=boUserDevices.get(i).getNickName();
			        	
			        	int DEVICE_ID=boUserDevices.get(i).getBoDevice().getDeviceId();
			        	int USER_ID=boUserDevices.get(i).getBoUsers().getUserId();
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
		   /*
		    * new 5-7 根据deviceCode找到主机
		    */
		   @RequestMapping({"findUDsByDevicecode.do"})
		   @ResponseBody
		   public List<BoUserDevices> findUDsByDevicecode(@RequestParam("deviceCode") String deviceCode,@RequestParam("index") int index) {
			   List list=new ArrayList();
			   List<BoUserDevices> boUserDevices=new ArrayList();
			   if("".equals(deviceCode)) {
				   boUserDevices = this.BoUserDevicesService.find();
			   }else {
				   boUserDevices = this.BoUserDevicesService.getListByDeviceCode(deviceCode);	
			   }
			   //Page类
			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
			   int totalCount=boUserDevices.size();
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
//			   System.out.println("totalCount:"+totalCount);
//			   System.out.println("startRow:"+startRow);
//			   System.out.println("endRow:"+endRow);
//			   System.out.println("totalPages:"+totalPages);
			   int j=0;
			   if(boUserDevices.size() > 0) {
				   for(int i=startRow;i<=endRow;i++) {
					   j++;
					   int id=boUserDevices.get(i).getUserDeviceId();
					   String deviceCode01=boUserDevices.get(i).getBoDevice().getDeviceCode();
					   String userPhone=boUserDevices.get(i).getBoUsers().getUserPhone();
					   String nick_name=boUserDevices.get(i).getNickName();
					   int DEVICE_ID=boUserDevices.get(i).getBoDevice().getDeviceId();
					   int USER_ID=boUserDevices.get(i).getBoUsers().getUserId();
					   Map map=new HashMap();
					   map.put("id", id);
					   map.put("deviceCode",deviceCode);
					   map.put("userPhone",userPhone);
					   System.out.println("userPhone:"+userPhone);
					   map.put("nick_name",nick_name);
					   System.out.println("nick_name:"+nick_name);
					   map.put("DEVICE_ID", DEVICE_ID);
					   map.put("USER_ID", USER_ID);
					   list.add(map);
					   //存在数据时输入,且在第一次时执行
					   if(j==1) {
						   page.setCurrentPage(index);
						   page.setTotalPages(totalPages);
						   list.add(page);
					   }
				   }
			   }
		        return list;
		    }
		   
		   /*
		    * 根据deviceCode查找设备
		    */
//		   @RequestMapping({"findHostByDevicecode.do"})
//		   @ResponseBody
//		   public List<BoHostDevice> findHostByDevicecode(@RequestParam("deviceCode") String deviceCode,@RequestParam("index") int index) {
//			   List list=new ArrayList();
//			   List<BoHostDevice> boHosts=null;
//			   if("".equals(deviceCode)) {
//				   boHosts = this.boHostDeviceService.getAllHostD();
//			   }else {
//				   boHosts = this.boHostDeviceService.getListBy(deviceCode);	
//			   }
//			   //Page类
//			   Page page=new Page();//假设给定：总条数 11条；总页数 2页；开始行数是 10；结束行是10；
//			   int totalCount=boHosts.size();
//			   int pageSize=page.getPageSize();
//			   int totalPages=1;
//			   if(totalCount%pageSize !=0){
//				   totalPages = totalCount/pageSize+1;           //只要有小数都+1
//			   }else {
//				   totalPages = totalCount/pageSize;
//			   }
//			   int startRow=(index-1)*pageSize;//数据库表中的行数 （从0开始）
//			   int endRow;
//			   if(index == totalPages) {
//				   endRow=totalCount-1;
//			   }else {
//				   endRow=index*pageSize - 1;
//			   }
////			   System.out.println("totalCount:"+totalCount);
////			   System.out.println("startRow:"+startRow);
////			   System.out.println("endRow:"+endRow);
////			   System.out.println("totalPages:"+totalPages);
//			   int j=0;
//			   if(boHosts.size() > 0) {
//				   for(int i=startRow;i<=endRow;i++) {
//					   j++;
//					   int id=boHosts.get(i).getId();
//					   String deviceCode01=boHosts.get(i).getBoDevice().getDeviceCode();
//					   String userPhone=boHosts.get(i).getBoUsers().getUserPhone();
//					   String nick_name=boHosts.get(i).getNickName();
//					   int DEVICE_ID=boHosts.get(i).getBoDevice().getDeviceId();
//					   int USER_ID=boHosts.get(i).getBoUsers().getUserId();
//					   Map map=new HashMap();
//					   map.put("id", id);
//					   map.put("deviceCode",deviceCode);
//					   map.put("userPhone",userPhone);
//					   System.out.println("userPhone:"+userPhone);
//					   map.put("nick_name",nick_name);
//					   System.out.println("nick_name:"+nick_name);
//					   map.put("DEVICE_ID", DEVICE_ID);
//					   map.put("USER_ID", USER_ID);
//					   list.add(map);
//					   //存在数据时输入,且在第一次时执行
//					   if(j==1) {
//						   page.setCurrentPage(index);
//						   page.setTotalPages(totalPages);
//						   list.add(page);
//					   }
//				   }
//			   }
//		        return list;
//		    }
		   
		   /**
		    * 删除主机（解绑） 和device和boUsers解绑
		    * @param id
		    * @param DEVICE_ID
		    * @param USER_ID
		    * @return
		    */
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
			        	String nickName=boHosts.get(i).getNickName();
//			        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("deviceAddress",deviceAddress);
			        	map.put("nickName",nickName);
			        	list.add(map);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   /*
		    * 分页查找设备
		    */
		   @RequestMapping({"findHostByIndex.do"})
		   @ResponseBody
		   public List<BoHostDevice> findHostByIndex(@RequestParam("index") int index,@RequestParam("addrOtel") String addrOtel,HttpServletRequest request) {
			   List list=new ArrayList();
			   List<BoHostDevice> boHosts=new ArrayList();
			   HttpSession session = request.getSession();
			   String phone=(String) session.getAttribute("userPhone");
			   if(phone == null) {
				   if("".equals(addrOtel)) {//搜索框中不为空，此时执行的分页是基于搜索的
					   boHosts = this.boHostDeviceService.getAllHostD();		  //直接把这结果传给前台 出现Cannot call sendError() after the response has been committed
				   }else {
					   List<BoHostDevice> boHostss=this.boHostDeviceService.getAllHostD();
					   for(int i=0;i<boHostss.size();i++) {
						   if(addrOtel.equals(boHostss.get(i).getBoDevice().getDeviceCode()) || addrOtel.equals(boHostss.get(i).getDeviceAddress()) || addrOtel.equals(boHostss.get(i).getBoUsers().getUserPhone())) {
							   boHosts.add(boHostss.get(i));
						   }
					   }
				   }
				   
			   }else{
				   BoUsers bouser = this.boUserssService.findByUserPhone(phone);
				   String userCode=bouser.getUserCode();
				   boHosts=this.boHostDeviceService.getUserCode(userCode);
			   }
//			   System.out.println("boHost:"+boHosts);
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
//			   System.out.println("totalCount:"+totalCount);
//			   System.out.println("totalPages:"+totalPages);
			   for(int i=0;i<boHosts.size();i++) {
				   if(i>=startRow && i<=endRow) {
					   Map map=new HashMap();
			        	int id=boHosts.get(i).getId();
			        	String deviceCode=boHosts.get(i).getBoDevice().getDeviceCode();
			        	String userPhone=boHosts.get(i).getBoUsers().getUserPhone();
			        	String deviceAddress=boHosts.get(i).getDeviceAddress();
			        	String nickName=boHosts.get(i).getNickName();
//			        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone);
			        	map.put("deviceAddress",deviceAddress);
			        	map.put("nickName",nickName);
			        	list.add(map);
				   }
			   }
			   //用于向前台传递的数据  for分页
			   page.setCurrentPage(index);
			   page.setTotalPages(totalPages);
			   list.add(page);
		       return list;
		    }
		   
		   /*
		    * 通过地址码或手机号 模糊查询
		    */
		   @RequestMapping({"findHostByAddrOrTel.do"})
		   @ResponseBody
		   public List<BoHostDevice> findHostByAddrOrTel(@RequestParam("AddrOrTel") String AddrOrTel) {
			   List list=new ArrayList();
			   List<BoHostDevice> boHosts = this.boHostDeviceService.getAllHostD();
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
			   int j=0;
			   for(int i=0;i<boHosts.size();i++) {
				   if(AddrOrTel.equals(boHosts.get(i).getBoDevice().getDeviceCode()) || AddrOrTel.equals(boHosts.get(i).getDeviceAddress()) || AddrOrTel.equals(boHosts.get(i).getBoUsers().getUserPhone())) {
					   j++;
					   Map map=new HashMap();
			        	int id=boHosts.get(i).getId();
			        	String deviceCode=boHosts.get(i).getBoDevice().getDeviceCode();
			        	String userPhone1=boHosts.get(i).getBoUsers().getUserPhone();
			        	String deviceAddress1=boHosts.get(i).getDeviceAddress();
			        	String nickName=boHosts.get(i).getNickName();
//				        	System.out.println("id:"+id);
			        	map.put("id", id);
			        	map.put("deviceCode",deviceCode);
			        	map.put("userPhone",userPhone1);
			        	map.put("deviceAddress",deviceAddress1);
			        	map.put("nickName",nickName);
			        	list.add(map);
			        	if(j>=10) {break;}
				   }
			   }
//	    	   System.out.println("list:"+list);
	    	 //用于向前台传递的数据  for分页
			   page.setCurrentPage(1);
			   page.setTotalPages(totalPages);
			   list.add(page);
		        return list;
		    }
		   
		   /*
		    * 删除设备
		    */
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
		   
		   /*
		    * 设备管理模块-红外转发器参数
		    */
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
		   
		      
//       用于Junit测试
         public void hello(String str) {
      	   System.out.println("hello"+str);
         }
         public  String shiroEncryption(String password) {
        	//shiro加密
        	String hashAlgorithmName = "MD5";
 			Object credentials = password;
 			Object salt = ByteSource.Util.bytes("username");//loginName在找回密码中用不了，所以把这个去了.固定
 			int hashIterations = 1024;
 			Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
 			String userPwd=result.toString();
 			return userPwd;
         }
         public static void main(String[] args) {
        	 LoginController lg=new LoginController();
        	 lg.shiroEncryption("guest");//94c47c216d1ff3410de83390d437d1f1
        	 System.out.println("密码》"+lg.shiroEncryption("guest"));
		}
	}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.LoginController
 * JD-Core Version:    0.6.2
 */