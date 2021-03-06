/*     */ package com.smarthome.imcp.action.xing;
/*     */ 
/*     */ import com.smarthome.dock.server.util.StaticUtil;
/*     */ import com.smarthome.imcp.action.AbstractAction;
/*     */ import com.smarthome.imcp.common.Md5;
/*     */ import com.smarthome.imcp.controller.RequestJson;
		  import com.smarthome.imcp.dao.model.bo.BoFloor;
		  import com.smarthome.imcp.dao.model.bo.BoRoom;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUsers;
		  import com.smarthome.imcp.service.bo.BoFloorServiceIface;
		  import com.smarthome.imcp.service.bo.BoRoomServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*     */ import com.smarthome.imcp.util.ClearIpUtil;
/*     */ import com.smarthome.imcp.util.EmailUtils;
		  import com.smarthome.imcp.util.FloorUtil;
		  import com.smarthome.imcp.util.RoomUtil;
/*     */ import com.smarthome.imcp.util.SendMsgUtil;
/*     */ import com.smarthome.imcp.util.TokeUtil;
/*     */ import com.smarthome.imcp.util.UserUtil;
/*     */ import com.smarthome.imcp.util.ValidatorUtil;
		  import com.smarthome.imcp.util.androidAndIOS.Demo;
/*     */ import java.io.InputStream;
/*     */ import java.io.Serializable;
		  import java.text.SimpleDateFormat;
		  import java.util.ArrayList;
/*     */ import java.util.Date;
import java.util.Enumeration;
/*     */ import java.util.HashMap;
		  import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Timer;
/*     */ import java.util.TimerTask;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.struts2.ServletActionContext;
/*     */ import org.apache.struts2.convention.annotation.Action;
/*     */ import org.apache.struts2.convention.annotation.Namespace;
/*     */ import org.apache.struts2.convention.annotation.ParentPackage;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ @ParentPackage("auth-check-package")
/*     */ @Namespace("/appLogin")
/*     */ public class AppLogin_Action extends AbstractAction
/*     */ {
/*  72 */   private static Logger logger = LoggerFactory.getLogger(AppLogin_Action.class);
/*     */   public static final String SECTET = "12345";
/* 726 */   private String userPhone = "";
/*     */ 
/* 733 */   private String userPwd = "";
/*     */ 
/* 740 */   private String userEmail = "";
/*     */ 
/* 747 */   private String versionType = "";
/*     */ 
/* 754 */   private String CID = "";
/*     */ 
/* 761 */   private String phoneType = "";
/*     */ 
/* 768 */   private String code = "";

			//2-5 设备标记  友盟单播推送需要的参数
            private String devicetoken = "";

/*     */ 
/*     */   @Autowired
/*     */   private BoUserssServiceIface<BoUsers, Serializable> boUserService;
			@Autowired
			private BoFloorServiceIface<BoFloor, Serializable> boFloorService;
			@Autowired
			private BoRoomServiceIface<BoRoom, Serializable> boRoomService;
/* 778 */   private RequestJson requestJson = new RequestJson();

/*     */ 
/*     */   public Boolean isRal(String timestamp, String nonce, String sign, String access_Token, String userCode, String interfaceName)
/*     */   {
/*  88 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  89 */     String ip = request.getRemoteAddr();
/*  90 */     String str = "";
/*  91 */     Md5 md5 = new Md5();
/*  92 */     boolean isRel = true;
/*     */ 
/*  94 */     str = str + "access_token=";
/*  95 */     str = str + access_Token;
/*  96 */     System.err.println("access_Token " + access_Token);
/*  97 */     str = str + "&nonce=";
/*  98 */     str = str + nonce;
/*  99 */     System.err.println("nonce " + nonce);
/* 100 */     str = str + "&timestamp=";
/* 101 */     str = str + timestamp;
/* 102 */     System.err.println("timestamp " + timestamp);
/* 103 */     str = str + "&userCode=";
/* 104 */     str = str + userCode;
/* 105 */     System.err.println("userCode " + userCode);
/* 106 */     str = str + "12345";
/* 107 */     String service_sign = md5.getMD5ofStr(str).toLowerCase();
/* 108 */     System.err.println("客户端" + sign);
/* 109 */     System.err.println("服务器" + service_sign);
/* 110 */     if (service_sign.equals(sign)) {
/* 111 */       return Boolean.valueOf(isRel);
/*     */     }
/* 113 */     logger.error("ip： " + ip + "   " + "接口昵称：" + interfaceName + " 验签验证不通过");
/* 114 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */   @Action(value="find_email_pwd", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String find_email_pwd()
/*     */     throws Exception
/*     */   {
/* 127 */     this.requestJson = new RequestJson();
/* 128 */     Map map = new HashMap();
/* 129 */     Md5 md5 = new Md5();
/*     */     try {
/* 131 */       if (StringUtils.isEmpty(this.userEmail)) {
/* 132 */         this.requestJson = new RequestJson(false, "请输入邮箱", map);
/* 133 */         return "success";
/* 134 */       }if (StringUtils.isEmpty(this.userPwd)) {
/* 135 */         this.requestJson = new RequestJson(false, "请输入新密码", map);
/* 136 */         return "success";
/* 137 */       }if (StringUtils.isEmpty(this.code)) {
/* 138 */         this.requestJson = new RequestJson(false, "请输入邮箱验证码", map);
/* 139 */         return "success";
/*     */       }
				logger.info("StaticUtil.msg_email_code.get(this.userEmail)="+StaticUtil.msg_email_code.get(this.userEmail));
/* 141 */       if ((StaticUtil.msg_email_code.get(this.userEmail) == null) || 
/* 143 */         (StringUtils.isEmpty(StaticUtil.msg_email_code.get(this.userEmail)
/* 143 */         .toString()))) {
/* 144 */         this.requestJson = new RequestJson(false, "验证码已失效,请重新获取", map);
/* 145 */         return "success";
/*     */       }
/*     */ 		logger.info("str="+StaticUtil.msg_email_code.get(this.userEmail).toString()+",this.code="+this.code);
/* 148 */       String[] str = StaticUtil.msg_email_code.get(this.userEmail).toString()
/* 149 */         .split(",");
/*     */ 
/* 151 */       if (!this.code.equals(str[0])) {
/* 152 */         this.requestJson = new RequestJson(false, "验证码输入错误,请重新输入", map);
/* 153 */         return "success";
/*     */       }
/*     */ 
/* 157 */       BoUsers user = this.boUserService.findByUserEmail(this.userEmail);
/* 158 */       if (user == null) {
/* 159 */         this.requestJson = new RequestJson(false, "该邮箱未绑定过账号", map);
/* 160 */         return "success";
/*     */       }
/*     */ 
/* 164 */       user.setUserPwd(md5.getMD5ofStr(this.userPwd));
/* 165 */       BoUsers update = (BoUsers)this.boUserService.update(user);
/* 166 */       if (update != null)
/*     */       {
/* 168 */         this.requestJson = new RequestJson(true, "修改成功", map);
/* 169 */         return "success";
/*     */       }
/* 171 */       this.requestJson = new RequestJson(false, "修改失败", map);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 176 */       logger.info("error" + e.getMessage());
/* 177 */       e.printStackTrace();
/* 178 */       this.requestJson.setData(map);
/* 179 */       this.requestJson.setMessage("服务器发生异常");
/* 180 */       this.requestJson.setSuccess(false);
/*     */     }
/* 182 */     return "success";
/*     */   }
/*     */ 
/*     */   @Action(value="emailVcode", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String emailVcode()
/*     */     throws Exception
/*     */   {
	          logger.info("发送邮箱验证码");
/* 194 */     this.requestJson = new RequestJson();
/* 195 */     Properties prop = new Properties();
/* 196 */     InputStream in = EmailUtils.in();
/* 197 */     prop.load(in);
/* 198 */     Map map = new HashMap();
/*     */     try {
/* 200 */       if (StringUtils.isEmpty(this.userEmail)) {
/* 201 */         this.requestJson = new RequestJson(false, "请输入邮箱", map);
/* 202 */       } else if (!ValidatorUtil.isEmail(this.userEmail)) {
/* 203 */         this.requestJson = new RequestJson(false, "邮箱格式不正确", map);
/*     */       } else {
/* 205 */         BoUsers email = this.boUserService.findByUserEmail(this.userEmail);
                  System.out.println("findUser By email:"+email);
/*     */         String sd;
/* 207 */         if (email == null)
/* 208 */           sd = "未绑定账号";
/*     */         else {
/* 210 */           sd = "绑定账号:" + email.getUserPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
/*     */         }
/* 212 */         String vcode = "";
/* 213 */         for (int i = 0; i < 6; i++)
/* 214 */           vcode = vcode + (int)(Math.random() * 9.0D);
/* 215 */         if (this.versionType.equals("1")) {
/* 216 */             String centent = "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
/* 223 */             this.userEmail + " 邮箱," + sd + "</td>" + "<tr " + 3 + "><td>" + "验证码为:" + vcode + " 请在10分钟内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
/* 224 */             EmailUtils.sendMail(prop.getProperty("fromEmail"), this.userEmail, prop.getProperty("emailName"), prop.getProperty("emailPassword"), "易家智联密码找回", centent);
					  StaticUtil.msg_email_code.put(this.userEmail, vcode + "," + new Date().getTime());
/* 227 */           this.requestJson = new RequestJson(true, "发送成功", map);
/* 228 */         } else if (this.versionType.equals("2")) {
/* 229 */             String centent = "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
/* 236 */             this.userEmail + " " + sd + "</td>" + "<tr " + 3 + "><td>" + "验证码为:" + vcode + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
/* 237 */             EmailUtils.sendMail(prop.getProperty("fromEmail"), this.userEmail, prop.getProperty("emailName"), prop.getProperty("emailPassword"), "爱波瑞密码找回", centent);
					  StaticUtil.msg_email_code.put(this.userEmail, vcode + "," + new Date().getTime());
/* 240 */           this.requestJson = new RequestJson(true, "发送成功", map);
/* 241 */         } else if (!this.versionType.equals("3"))
/*     */         {
/* 243 */           if (this.versionType.equals("4")) {
/* 244 */               String centent = "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
/* 251 */               this.userEmail + " " + sd + "</td>" + "<tr " + 3 + "><td>" + "验证码为:" + vcode + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
/* 252 */               EmailUtils.sendMail(prop.getProperty("fromEmail"), this.userEmail, prop.getProperty("emailName"), prop.getProperty("emailPassword"), "思创科技密码找回", centent);
						StaticUtil.msg_email_code.put(this.userEmail, vcode + "," + new Date().getTime());
/* 255 */             this.requestJson = new RequestJson(true, "发送成功", map);
/* 256 */           } else if (this.versionType.equals("5")) {
/* 257 */               String centent = "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
/* 264 */               this.userEmail + " " + sd + "</td>" + "<tr " + 3 + "><td>" + "验证码为:" + vcode + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
/* 265 */               EmailUtils.sendMail(prop.getProperty("fromEmail"), this.userEmail, prop.getProperty("emailName"), prop.getProperty("emailPassword"), "峰庭密码找回", centent);
						StaticUtil.msg_email_code
/* 267 */               	.put(this.userEmail, vcode + "," + new Date().getTime());
/* 268 */             this.requestJson = new RequestJson(true, "发送成功", map);
/* 269 */           } else if (this.versionType.equals("6")) {
/* 270 */               String centent = "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
/* 277 */               this.userEmail + " " + sd + "</td>" + "<tr " + 3 + "><td>" + "验证码为:" + vcode + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
					    EmailUtils.sendMail(prop.getProperty("fromEmail"), this.userEmail, prop.getProperty("emailName"), prop.getProperty("emailPassword"), "麦宝密码找回", centent);
//					    MailUtil.send_Htmlmail(this.userEmail, centent);
					    StaticUtil.msg_email_code.put(this.userEmail, vcode + "," + new Date().getTime());
/* 280 */             this.requestJson = new RequestJson(true, "发送成功", map);
/* 281 */           } else if (this.versionType.equals("7")) {
/* 282 */               String centent = "<h2>尊敬的用户：</h2><style type=\"text/css\">.common-table{-moz-user-select: none;width:35em;border:0;table-layout : fixed;border-top:0px solid #dedfe1;border-right:0px solid #dedfe1;}/*header*/.common-table thead td,.common-table thead th{    height:23px;   background-color:#e4e8ea;   text-align:center;   border-left:1px solid #dedfe1;}.common-table thead th, .common-table tbody th{padding-left:7px;padding-right:7px;width:15px;text-align:center;}.common-table tbody td,  .common-table tbody th{    height:25px!important;border-bottom:0px solid #dedfe1;border-left:0px solid #dedfe1;cursor:default;word-break: break-all;-moz-outline-style: none;_padding-right:7px;text-align:center;}</style><table class=\"common-table\"><thead><tr><td width=\"100\">" + 
/* 289 */               this.userEmail + " " + sd + "</td>" + "<tr " + 3 + "><td>" + "验证码为:" + vcode + "请在60秒内使用" + "</td>" + "</tbody>" + "<tr " + 3 + "><td>" + "若非本人账号获取到验证码请勿去使用app修改密码,若查到,责任自负" + "</td>" + "</tbody>" + "</table>";
/* 290 */               EmailUtils.sendMail(prop.getProperty("fromEmail"), this.userEmail, prop.getProperty("emailName"), prop.getProperty("emailPassword"), "乐沃密码找回", centent);
						StaticUtil.msg_email_code.put(this.userEmail, vcode + "," + new Date().getTime());
/* 292 */               this.requestJson = new RequestJson(true, "发送成功", map);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 298 */       logger.info("error" + e.getMessage());
/* 299 */       e.printStackTrace();
/* 300 */       this.requestJson.setData(map);
/* 301 */       this.requestJson.setMessage("服务器发生异常");
/* 302 */       this.requestJson.setSuccess(false);
/*     */     }
/* 304 */     return "success";
/*     */   }
			//新版本app 找回密码，配合php 
/*     */ 	@Action(value="find_pwd1", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String find_pwd1()
/*     */     throws Exception
/*     */   {
/* 316 */     this.requestJson = new RequestJson();
/* 317 */     @SuppressWarnings("rawtypes")
              Map map = new HashMap();
/* 318 */     Md5 md5 = new Md5();
			  //6-19
			  logger.info("app_php找回密码");
			  HttpServletRequest request = ServletActionContext.getRequest();
			  String salt="";
			  Enumeration pNames=request.getParameterNames();
			  while(pNames.hasMoreElements()){//根据传过来的信息 定位到设备，将设备的授权标识保存到表BoHostDevice中
			      String name=(String)pNames.nextElement();
			      logger.info("name:"+name);
			      String value=request.getParameter(name);
			      logger.info("value:"+value);
			      if("salt".equals(name)) {
			    	  salt=value;
			      }
			  }
			  logger.info("salt:"+salt);
			//end
/*     */     try {
/* 320 */       if (StringUtils.isEmpty(this.userPhone)) {
/* 321 */         this.requestJson = new RequestJson(false, "请输入手机号码", map);
/* 322 */         return "success";
/* 323 */       }
				if (StringUtils.isEmpty(this.userPwd)) {
/* 324 */         this.requestJson = new RequestJson(false, "请输入新密码", map);
/* 325 */         return "success";
/* 326 */       }
				//6-19号注释，app配合php验证，不借助java的验证码
//				if (StringUtils.isEmpty(this.code)) {
///* 327 */         this.requestJson = new RequestJson(false, "请输入短信验证码", map);
///* 328 */         return "success";
///*     */       }
/* 330 */       BoUsers user = this.boUserService.findByUserPhone(this.userPhone);
/* 331 */       if (user == null) {
/* 332 */         this.requestJson = new RequestJson(false, "该手机号码未注册", map);
/* 333 */         return "success";
/*     */       }
/*     */ 		  //6-19号注释，app配合php验证
///* 336 */       if ((StaticUtil.msg_code.get(this.userPhone) == null) || 
///* 338 */         (StringUtils.isEmpty(StaticUtil.msg_code.get(this.userPhone)
///* 338 */         .toString()))) {
///* 339 */         this.requestJson = new RequestJson(false, "验证码已失效,请重新获取", map);
///* 340 */         return "success";
///*     */       }
/*     */ 		
///* 343 */       String[] str = StaticUtil.msg_code.get(this.userPhone).toString()
///* 344 */         .split(",");
///*     */ 
///* 346 */       if (!this.code.equals(str[0])) {
///* 347 */         this.requestJson = new RequestJson(false, "验证码不正确", map);
///* 348 */         return "success";
///*     */       }
				//6-19
				if(salt != null) {
					String phpPasswd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
					user.setSalt(salt);
					user.setPhpPasswd(phpPasswd);
				}
				//end
/* 350 */       user.setUserPwd(md5.getMD5ofStr(this.userPwd));
/* 351 */       BoUsers update = (BoUsers)this.boUserService.update(user);
/* 352 */       if (update != null) {
/* 353 */         this.requestJson = new RequestJson(true, "修改成功", map);
/* 354 */         return "success";
/*     */       }
/* 356 */       this.requestJson = new RequestJson(false, "修改失败", map);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 361 */       logger.info("error" + e.getMessage());
/* 362 */       e.printStackTrace();
/* 363 */       this.requestJson.setData(map);
/* 364 */       this.requestJson.setMessage("服务器发生异常");
/* 365 */       this.requestJson.setSuccess(false);
/*     */     }
/* 367 */     return "success";
/*     */   }
			//老版本app 找回密码
/*     */   @Action(value="find_pwd", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String find_pwd()
/*     */     throws Exception
/*     */   {
/* 316 */     this.requestJson = new RequestJson();
/* 317 */     @SuppressWarnings("rawtypes")
              Map map = new HashMap();
/* 318 */     Md5 md5 = new Md5();
/*     */     try {
/* 320 */       if (StringUtils.isEmpty(this.userPhone)) {
/* 321 */         this.requestJson = new RequestJson(false, "请输入手机号码", map);
/* 322 */         return "success";
/* 323 */       }
				if (StringUtils.isEmpty(this.userPwd)) {
/* 324 */         this.requestJson = new RequestJson(false, "请输入新密码", map);
/* 325 */         return "success";
/* 326 */       }
				if (StringUtils.isEmpty(this.code)) {
/* 327 */         this.requestJson = new RequestJson(false, "请输入短信验证码", map);
/* 328 */         return "success";
/*     */       }
/* 330 */       BoUsers user = this.boUserService.findByUserPhone(this.userPhone);
/* 331 */       if (user == null) {
/* 332 */         this.requestJson = new RequestJson(false, "该手机号码未注册", map);
/* 333 */         return "success";
/*     */       }
/*     */ 
/* 336 */       if ((StaticUtil.msg_code.get(this.userPhone) == null) || 
/* 338 */         (StringUtils.isEmpty(StaticUtil.msg_code.get(this.userPhone)
/* 338 */         .toString()))) {
/* 339 */         this.requestJson = new RequestJson(false, "验证码已失效,请重新获取", map);
/* 340 */         return "success";
/*     */       }
/* 343 */       String[] str = StaticUtil.msg_code.get(this.userPhone).toString()
/* 344 */         .split(",");
/*     */ 
/* 346 */       if (!this.code.equals(str[0])) {
/* 347 */         this.requestJson = new RequestJson(false, "验证码不正确", map);
/* 348 */         return "success";
/*     */       }
/* 350 */       user.setUserPwd(md5.getMD5ofStr(this.userPwd));
/* 351 */       BoUsers update = (BoUsers)this.boUserService.update(user);
/* 352 */       if (update != null) {
/* 353 */         this.requestJson = new RequestJson(true, "修改成功", map);
/* 354 */         return "success";
/*     */       }
/* 356 */       this.requestJson = new RequestJson(false, "修改失败", map);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 361 */       logger.info("error" + e.getMessage());
/* 362 */       e.printStackTrace();
/* 363 */       this.requestJson.setData(map);
/* 364 */       this.requestJson.setMessage("服务器发生异常");
/* 365 */       this.requestJson.setSuccess(false);
/*     */     }
/* 367 */     return "success";
/*     */   }
/*     */ 
/*     */   @Action(value="phoneVcode", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String phoneVcode()
/*     */     throws Exception
/*     */   {
/* 379 */     this.requestJson = new RequestJson();
/* 380 */     @SuppressWarnings("rawtypes")
			  Map map = new HashMap();
/*     */     try {
/* 382 */       if (StringUtils.isEmpty(this.userPhone)) {
/* 383 */         this.requestJson = new RequestJson(false, "请输入手机号码", map);
/* 384 */       } else if (!ValidatorUtil.IsMobile(this.userPhone)) {
/* 385 */         this.requestJson = new RequestJson(false, "手机号码格式不正确", map);
/*     */       } else {
/* 387 */         String vcode = "";
/* 388 */         for (int i = 0; i < 6; i++)
/* 389 */           vcode = vcode + (int)(Math.random() * 9.0D);
                  System.out.println("0");
/* 390 */         if (this.versionType.equals("1")) {
	                System.out.println("this.versionType==1");
/* 391 */           SendMsgUtil.sendMsg(this.userPhone, "尊敬的用户，您的验证码为 : " + vcode);
                    System.out.println("vcode 1===="+vcode);
/* 392 */           StaticUtil.msg_code
/* 393 */             .put(this.userPhone, vcode + "," + new Date().getTime());
/* 394 */           this.requestJson = new RequestJson(true, "发送成功", map);
/* 395 */         } else if (this.versionType.equals("2")) {
					System.out.println("this.versionType==2");
/* 396 */           SendMsgUtil.aiBoRuisendMsg(this.userPhone, "【爱波瑞科技】尊敬的用户，您的验证码为 : " + vcode);
                    System.out.println("vcode 2===="+vcode);
/* 397 */           StaticUtil.msg_code
/* 398 */             .put(this.userPhone, vcode + "," + new Date().getTime());
/* 399 */           this.requestJson = new RequestJson(true, "发送成功", map);
/* 400 */         } else if (!this.versionType.equals("3"))
/*     */         {
/* 402 */           if (this.versionType.equals("this.versionType==4")) {
	                  System.out.println("4");
/* 403 */             SendMsgUtil.siChuangSendMsg(this.userPhone, "【思创智能】尊敬的用户，您的验证码为 :" + vcode);
                      System.out.println("vcode 4===="+vcode);
/* 404 */             StaticUtil.msg_code
/* 405 */               .put(this.userPhone, vcode + "," + new Date().getTime());
/* 406 */             this.requestJson = new RequestJson(true, "发送成功", map);
/* 407 */           } else if (this.versionType.equals("5")) {
	                  System.out.println("this.versionType==5");
/* 408 */             SendMsgUtil.fengTingSendMsg(this.userPhone, "【峰庭智能】尊敬的用户，您的验证码为 :" + vcode);
                      System.out.println("vcode 5===="+vcode);
/* 409 */             StaticUtil.msg_code
/* 410 */               .put(this.userPhone, vcode + "," + new Date().getTime());
/* 411 */             this.requestJson = new RequestJson(true, "发送成功", map);
/* 412 */           } else if (this.versionType.equals("6")) {
	                  System.out.println("this.versionType==6");
	                  System.out.println("vcode 6===="+vcode);
/* 413 */             SendMsgUtil.maiBaoSendMsg(this.userPhone, "【智能屋】尊敬的用户，您的验证码为 :" + vcode);
/* 414 */             StaticUtil.msg_code
/* 415 */               .put(this.userPhone, vcode + "," + new Date().getTime());
/* 416 */             this.requestJson = new RequestJson(true, "发送成功", map);
/* 417 */           } else if (this.versionType.equals("7")) {
	                  System.out.println("7");
/* 418 */             SendMsgUtil.lavo(this.userPhone, "【乐沃智能】尊敬的用户，您的验证码为 : " + vcode);
                      System.out.println("vcode 7===="+vcode);
/* 419 */             StaticUtil.msg_code
/* 420 */               .put(this.userPhone, vcode + "," + new Date().getTime());
/* 421 */             this.requestJson = new RequestJson(true, "发送成功", map);
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (Exception e) {
	            System.out.println("Exception:"+e);//xception:java.lang.ClassCastException: java.util.HashMap$KeySet cannot be cast to java.util.List
/* 426 */       logger.info("error" + e.getMessage());//errorjava.util.HashMap$KeySet cannot be cast to java.util.List
/* 427 */       this.requestJson.setData(map);
/* 428 */       this.requestJson.setMessage("服务器发生异常");
/* 429 */       this.requestJson.setSuccess(false);
/*     */     }
/* 431 */     return "success";
/*     */   }

/*     */   @SuppressWarnings("unchecked")
            @Action(value="appUserLogin", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String appUserLogin()
/*     */     throws Exception
/*     */   {
/* 445 */     Md5 md5 = new Md5();
/* 446 */     this.requestJson = new RequestJson();
/* 447 */     @SuppressWarnings("rawtypes")
			  Map userInfoMap = new HashMap();
			  //6-15
			  String salt="";
			  HttpServletRequest request = ServletActionContext.getRequest();			
			  Enumeration pNames=request.getParameterNames();
			  logger.info("app登录接口......");
			  while(pNames.hasMoreElements()){//根据传过来的信息 定位到设备，将设备的授权标识保存到表BoHostDevice中
				  String name=(String)pNames.nextElement();
				  logger.info("name:"+name);
				  String value=request.getParameter(name);
				  logger.info("value:"+value);
				  if(name.equals("salt")) {
					  salt=value;
				  }
			  }
//			  logger.info("salt:"+salt);
			//end
/*     */     try {
/* 449 */       if (StringUtils.isEmpty(this.userPhone)) {
/* 450 */         this.requestJson = new RequestJson(false, "请输入手机号码", userInfoMap);
/* 451 */       } else if (StringUtils.isEmpty(this.userPwd)) {
/* 452 */         this.requestJson = new RequestJson(false, "请输入密码", userInfoMap);
/*     */       } else {
/* 454 */         BoUsers users = this.boUserService.findByUserPhonePwd(this.userPhone, md5.getMD5ofStr(this.userPwd));
				  if(users == null && salt != null) {
					  users=this.boUserService.findByUserPhonePhpPwd(this.userPhone, md5.getMD5ofStr(this.userPwd+salt).toLowerCase());
				  }
				  
/* 455 */         String generateTokeCode = TokeUtil.generateTokeCode();
/* 456 */         String generateTokeCodes = TokeUtil.generateTokeCodes();
/* 457 */         Long accessTokenTime = Long.valueOf(1800L);
/* 458 */         Long refreshTokenTime = Long.valueOf(2592000L);
/* 459 */         Long accessTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + accessTokenTime.longValue());
/* 460 */         Long refreshTokenTime_o = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + refreshTokenTime.longValue());
/* 461 */         if (users != null) {
/* 462 */           users.setAccessToken(generateTokeCode);
/* 463 */           users.setRefreshToken(generateTokeCodes);
/* 464 */           users.setAccessTokenTime(accessTokenTime_o+"");
/* 465 */           users.setRefreshTokenTime(refreshTokenTime_o+"");
/* 466 */           users.setCid(this.CID);
//					logger.info("CID >>>"+this.CID);
//					logger.info("this.phoneType==="+Integer.parseInt(this.phoneType));
					int pt=Integer.parseInt(this.phoneType);
///* 467 */           users.setPhoneType(Integer.parseInt(this.phoneType));
					users.setPhoneType(pt);
/* 468 */           users.setVersionType(this.versionType);
					
					//2018-3-7: 首先将旧设备的token存入userAddr中，待另一台设备登录时（判断是否相同）       若不同则将旧的userAddr被新的devicetoken替换
//                    logger.info("新的deviceToken==="+this.devicetoken);
//					logger.info("旧手机的deviceToken==="+users.getUserAddr());
//					logger.info("UserAddr==="+(users.getUserAddr().equals("") || users.getUserAge().equals("")));
//					if(users.getUserAddr() == "") {//字符串不能这么比较，改用equals方法
//					logger.info("users.getUserAddr()="+users.getUserAddr()+",是否等于null="+(null==users.getUserAddr()));
//					logger.info("是否为空："+("".equals(users.getUserAddr()) || "".equals(users.getUserAge()))+","+( users.getUserAddr()==null || users.getUserAge()==null));
//					logger.info("devicetoken:"+this.devicetoken);
					//userAddr或userAge为空或null，说明是用户第一次登录，不执行离线推送功能
					logger.info("login.action devicetoken:"+this.devicetoken);
					logger.info("login.action userAddr:"+users.getUserAddr());
					if("".equals(users.getUserAddr()) || "".equals(users.getUserAge())) {
						users.setUserAddr(this.devicetoken);
						users.setUserAge(this.phoneType+"");//2018-3-15 将这台设备的设备类型存在 无用字段 UserAge中
					}else if( null==users.getUserAddr() || null==users.getUserAge()) {//还存在部分UserAddr字段为null,,,,UserAddr和UserAge没有赋值前默认是Null的 把要比较的放前面可以避免空指针  null==,"".equels("")
						users.setUserAddr(this.devicetoken);
						users.setUserAge(this.phoneType+"");
					}else{
						logger.info("是否是同一台设备："+this.devicetoken.equals(users.getUserAddr()));
						if(!this.devicetoken.equals(users.getUserAddr())) {//当不同时判定为有新设备连入 -----》发送离线通知						
							//2-5 友盟推送
							Demo ymPush = new Demo();
							//+上线时间 
							Date currentTime = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String dateString = formatter.format(currentTime);
							int PrePhoneType=Integer.parseInt(users.getUserAge());
							int NowPhoneType=Integer.parseInt(this.phoneType);
//							logger.info("旧设备类型>>"+PrePhoneType);
//							logger.info("新设备类型>>"+NowPhoneType);
							Map<String,String> map1=new HashMap<String,String>();
							map1.put("title", "离线通知");
							map1.put("subtitle", "");
							map1.put("body", "另一台设备正在登录,您在"+dateString+"被迫下线");
							//取出旧设备的phoneType，根据旧设备的phoneType选择安卓推送还是IOS推送
							if(NowPhoneType == 1) {
								if(PrePhoneType == 1) {//苹果设备 挤掉  苹果设备
									logger.info("苹果设备 挤掉  苹果设备，userAddr="+users.getUserAddr());
									ymPush.sendIOSUnicast(users.getUserAddr(),map1,"appoffline");
								}else {//苹果设备 挤掉 安卓设备，给安卓设备发送 离线通知
									logger.info("苹果设备挤掉安卓设备");
									ymPush.sendAndroidUnicast(users.getUserAddr(),"离线通知","另一台设备正在登录,您在"+dateString+"被迫下线");	
								}
							}else {
								if(PrePhoneType == 0) {
									logger.info("安卓设备挤掉安卓设备");
									ymPush.sendAndroidUnicast(users.getUserAddr(),"离线通知","另一台设备正在登录,您在"+dateString+"被迫下线");	
								}else {
									logger.info("安卓设备挤掉苹果设备");
									ymPush.sendIOSUnicast(users.getUserAddr(),map1,"appoffline");
								}
							}
							//end
							users.setUserAddr(this.devicetoken);   	
							users.setUserDevicetoken(this.devicetoken);//当前登录的设备token
//						END
						}
					}
                    //end
/* 469 */           BoUsers update = (BoUsers)this.boUserService.update(users);
/* 470 */           userInfoMap.put("accessToken", update.getAccessToken());
/* 471 */           userInfoMap.put("refreshToken", update.getRefreshToken());
/* 472 */           if (users.getLogoAccountType().equals("M"))
/* 473 */             userInfoMap.put("userCode", users.getUserCode() + "," + users.getUserPhone());//LogoAccountType=M时 传 本身的userCode
/*     */           else {
					  userInfoMap.put("userCode", users.getAuthorizationUserCode() + "," + users.getUserPhone());
/*     */           }
/* 477 */           userInfoMap.put("logoAccountType", users.getLogoAccountType());
/* 478 */           userInfoMap.put("accountOperationType", users.getAccountOperationType());
/* 479 */           userInfoMap.put("userPhone", users.getUserPhone());
					//如果是一般用户，则isFirst直接赋值1，直接进入主界面==通过authorizationUserCode判断，不为空则直接进入主界面   2018-3-16实现
                    if(!update.getAuthorizationUserCode().equals("")) {//实现 被授权者直接进入主界面，不用进入房间管理页面的判断
                    	userInfoMap.put("isFirst", Boolean.valueOf(true));
                    }else {
                    	userInfoMap.put("isFirst", users.getIsFirst());	
                    }
                    //6-20
                    userInfoMap.put("isUpdate", users.getIsupdate());
                    logger.info("isUpdate...."+users.getIsupdate());
                    //6-20
                    try {
                    	userInfoMap.put("oldPhone", users.getOldPhone());
                    	logger.info("oldPhone...."+users.getOldPhone());
					} catch (Exception e) {
						logger.info("您没有oldPhone字段");
					}
/* 481 */           userInfoMap.put("whetherSetPwd", users.getWhetherSetPwd());
					//添加初始的楼层、房间信息  2018/1/3
					BoFloor floor=this.boFloorService.findByUserCode(users.getUserCode());
					logger.info("floor:"+floor);
					if(floor != null) {
						String floorName=floor.getFloorName();//空指针 已解决（floor!=null）
						logger.info("floorName:"+floorName);
						userInfoMap.put("floorName", floorName);
						List<BoRoom> rooms=this.boRoomService.getAllListByUserCode(users.getUserCode());
						List list_room = new ArrayList();
						for (BoRoom boRoom : rooms) {
							Map map = new HashMap();
							BoFloor findByFloorCode = this.boFloorService.findByFloorCode(boRoom.getFloorCode()); 
							map.put("roomCode", boRoom.getRoomCode().toString());
							map.put("roomName", boRoom.getRoomName().toString());
							map.put("floorCode", boRoom.getFloorCode().toString());
							list_room.add(map);
						}
						userInfoMap.put("roomInfo", list_room);
						//////////////////////////////////////////////////楼层、房间添加默认值END///////////////////////////////////////////////////////
					}
/* 482 */           String fluoriteAccessToken = users.getFluoriteAccessToken();
/*     */           String EZTOKEN;
////                  2-5 友盟推送
//					Demo ymPush = new Demo();
////					logger.info("phoneType>>"+users.getPhoneType());
//					//+上线时间 
//					Date currentTime = new Date();
//					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//					String dateString = formatter.format(currentTime);
//					if(users.getPhoneType() == 1) {//0 代表 安卓;1代表 ios
//						Map<String,String> map1=new HashMap<String,String>();
//						map1.put("title", "上线通知");
//						map1.put("subtitle", "");
//						map1.put("body", "您的设备在"+dateString+"成功登录");
//						ymPush.sendIOSUnicast(this.devicetoken,map1,"applogin");
//					}else {
//						ymPush.sendAndroidUnicast(this.devicetoken,"上线通知","您的设备在"+dateString+"成功登录");
//					}
//					END
/* 484 */           if (fluoriteAccessToken.equals(""))
/* 485 */             EZTOKEN = "NO_BUNDING";
/*     */           else {
/* 487 */             EZTOKEN = fluoriteAccessToken;
/*     */           }
/* 489 */           userInfoMap.put("Eztoken", EZTOKEN);
/* 490 */           userInfoMap.put("ez_token", EZTOKEN);
/* 491 */           String city2 = users.getCity();
/*     */           String city3;
/* 493 */           if (city2.equals("")) {
/* 494 */             city3 = "杭州市";
/*     */           } else {
/* 496 */             String[] split = city2.split(",");
/* 497 */             city3 = split[1];
/*     */           }
/* 499 */           userInfoMap.put("city", city3);
/* 500 */           this.requestJson.setData(userInfoMap);
/* 501 */           this.requestJson.setMessage("");
/* 502 */           this.requestJson.setSuccess(true);
/*     */         } else {
/* 504 */           this.requestJson.setData(userInfoMap);
/* 505 */           this.requestJson.setMessage("手机号或密码不正确");
/* 506 */           this.requestJson.setSuccess(false);
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/* 511 */       e.printStackTrace();
/* 512 */       logger.info("error" + e.getMessage());
/* 513 */       this.requestJson.setData(userInfoMap);
/* 514 */       this.requestJson.setMessage("服务器发生异常");
/* 515 */       this.requestJson.setSuccess(false);
/*     */     }
/* 517 */     return "success";
/*     */   }
/*     */ 
/*     */   @SuppressWarnings("rawtypes")
			@Action(value="appUserRegister", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String appUserRegister()
/*     */     throws Exception
/*     */   {//2-1把 邮箱 设置为可选字段
/* 530 */     Md5 md5 = new Md5();
/* 531 */     this.requestJson = new RequestJson();
/* 532 */     HttpServletRequest request = ServletActionContext.getRequest();
			  Enumeration pNames=request.getParameterNames();
			  String salt="";
			  while(pNames.hasMoreElements()){//根据传过来的信息 定位到设备，将设备的授权标识保存到表BoHostDevice中
				  String name=(String)pNames.nextElement();
				  if(name.equals("salt")) {
					  salt=request.getParameter(name);
				  }
			  }
			  logger.info("salt:"+salt);
/* 534 */     Map map = new HashMap();
/*     */     try {
/* 536 */       if (StringUtils.isEmpty(this.userPhone)) {
/* 537 */         this.requestJson = new RequestJson(false, "请输入手机号码", map);
/* 538 */       } else if (StringUtils.isEmpty(this.userPwd)) {
/* 539 */         this.requestJson = new RequestJson(false, "请输入密码", map);
/* 540 */       } else if (!StringUtils.isEmpty(this.userEmail)) {//邮箱 非空 （若注册页面邮箱设为可选，这是安卓的工作）
/* 541 */         if (!ValidatorUtil.isEmail(this.userEmail)) {
/* 542 */           this.requestJson = new RequestJson(false, "邮箱格式不正确", map);
/*     */         } else {
/* 544 */           BoUsers users = this.boUserService.findByUserPhone(this.userPhone);
/* 545 */           if (users == null)
/*     */           {
/*     */             String emailS;
/* 547 */             if (StringUtils.isEmpty(this.userEmail))
/* 548 */               emailS = "空";
/*     */             else
/* 550 */               emailS = this.userEmail;
/* 551 */             BoUsers email = this.boUserService.findByUserEmail(emailS);
/* 552 */             if (email == null) {//用户不存在   则创建一个新用户
/* 553 */               final String remoteAddr = request.getRemoteAddr();
/* 554 */               long time = new Date().getTime();
/* 555 */               int count = 0;
/* 556 */               String[] ips = (String[])StaticUtil.IP.get(remoteAddr);
/* 557 */               if (ips == null) {
/* 558 */                 BoUsers user = UserUtil.save(this.userPhone, md5.getMD5ofStr(this.userPwd), this.userEmail);
						  //6-15
					 	  if(salt != null) {
					 		  String phpPwd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
					 		  user.setPhpPasswd(phpPwd);
					 		  user.setSalt(salt);
					 	  }
/* 559 */                 BoUsers save = (BoUsers)this.boUserService.save(user);
/* 560 */                 if (save != null) {
/* 561 */                   count++;
/* 562 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
/* 563 */                   this.requestJson.setData(map);
/* 564 */                   this.requestJson.setMessage("注册成功");
/* 565 */                   this.requestJson.setSuccess(true);
							//注册成功时 默认添加一个楼层和四个房间
							BoFloor floor=FloorUtil.save(save.getUserCode());
							BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
							//String userCode,String floorName,String floorCode,String roomName	
							System.out.println("楼层名称："+saveF.getFloorName());
							String userCode=saveF.getUserCode();
							String floorName=saveF.getFloorName();
//							String floorName="我的家";
							String floorCode=saveF.getFloorCode();
							BoRoom room1=RoomUtil.save(userCode,floorName,floorCode,"客厅");
							BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
							BoRoom room2=RoomUtil.save(userCode,floorName,floorCode,"卧室");
							BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
							BoRoom room3=RoomUtil.save(userCode,floorName,floorCode,"厨房");
							BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
							BoRoom room4=RoomUtil.save(userCode,floorName,floorCode,"卫生间");
							BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
/*     */                 }
/*     */ 
/*     */               }
/* 569 */               else if (time - Long.valueOf(ips[1]).longValue() <= 86400000L) {
/* 570 */                 if (Integer.valueOf(ips[2]).intValue() < 4)
/*     */                 {
/* 572 */                   BoUsers user = UserUtil.save(this.userPhone, md5.getMD5ofStr(this.userPwd), this.userEmail);
							//6-15
							 if(salt != null) {
								  String phpPwd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
								  user.setPhpPasswd(phpPwd);
								  user.setSalt(salt);
							 }
/* 573 */                   BoUsers save = (BoUsers)this.boUserService.save(user);
/* 574 */                   if (save != null) {
/* 575 */                     StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, Long.valueOf(ips[1])+"", (Integer.valueOf(ips[2]).intValue() + 1)+"" });
///* 576 */                     this.requestJson.setData(map);
///* 577 */                     this.requestJson.setMessage("注册成功");
///* 578 */                     this.requestJson.setSuccess(true);
                              //2-6
							  count++;
///* 562 */                     StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
/* 563 */                     this.requestJson.setData(map);
/* 564 */                     this.requestJson.setMessage("注册成功");
/* 565 */                     this.requestJson.setSuccess(true);
							  //注册成功时 默认添加一个楼层和四个房间
							  BoFloor floor=FloorUtil.save(save.getUserCode());
							  BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
							  //String userCode,String floorName,String floorCode,String roomName	
							  System.out.println("楼层名称："+saveF.getFloorName());
							  String userCode=saveF.getUserCode();
							  String floorName=saveF.getFloorName();
//							  String floorName="我的家";
							  String floorCode=saveF.getFloorCode();
							  BoRoom room1=RoomUtil.save(userCode,floorName,floorCode,"客厅");
							  BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
							  BoRoom room2=RoomUtil.save(userCode,floorName,floorCode,"卧室");
							  BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
							  BoRoom room3=RoomUtil.save(userCode,floorName,floorCode,"厨房");
							  BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
							  BoRoom room4=RoomUtil.save(userCode,floorName,floorCode,"卫生间");
							  BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
/*     */                   }
/* 580 */                 } else if (Integer.valueOf(ips[2]).intValue() == 4) {
/* 581 */                   Timer timer = new Timer();
/*     */                   try {
/* 583 */                     timer.schedule(new TimerTask() {
/*     */                       public void run() {
/* 585 */                         ClearIpUtil.ip = remoteAddr;
/* 586 */                         ClearIpUtil.clearMap();
/*     */                       }
/*     */                     }
/*     */                     , 86400000L);
/*     */                   } catch (NullPointerException e) {
/* 590 */                     logger.info(e.getMessage());
/*     */                   }
/*     */ 
/* 593 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, Long.valueOf(ips[1])+"", (Integer.valueOf(ips[2]).intValue() + 1)+"" });
/* 594 */                   this.requestJson.setData(map);
/* 595 */                   this.requestJson.setMessage("当前ip请求频繁");
/* 596 */                   this.requestJson.setSuccess(false);
/*     */                 } else {
/* 598 */                   this.requestJson.setData(map);
/* 599 */                   this.requestJson.setMessage("当前ip请求频繁");
/* 600 */                   this.requestJson.setSuccess(false);
/*     */                 }
/*     */               } else {
/* 603 */                 BoUsers user = UserUtil.save(this.userPhone, md5.getMD5ofStr(this.userPwd), this.userEmail);
							//6-15
						  if(salt != null) {
							  String phpPwd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
							  user.setPhpPasswd(phpPwd);
							  user.setSalt(salt);
						  }
/* 604 */                 BoUsers save = (BoUsers)this.boUserService.save(user);
/* 605 */                 if (save != null) {	
///* 606 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
///* 607 */                   this.requestJson.setData(map);
///* 608 */                   this.requestJson.setMessage("注册成功");
///* 609 */                   this.requestJson.setSuccess(true);
							count++;
/* 562 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
/* 563 */                   this.requestJson.setData(map);
/* 564 */                   this.requestJson.setMessage("注册成功");
/* 565 */                   this.requestJson.setSuccess(true);
							//注册成功时 默认添加一个楼层和四个房间
							BoFloor floor=FloorUtil.save(save.getUserCode());
							BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
							//String userCode,String floorName,String floorCode,String roomName	
							System.out.println("楼层名称："+saveF.getFloorName());
							String userCode=saveF.getUserCode();
							String floorName=saveF.getFloorName();
//							String floorName="我的家";
							String floorCode=saveF.getFloorCode();
							BoRoom room1=RoomUtil.save(userCode,floorName,floorCode,"客厅");
							BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
							BoRoom room2=RoomUtil.save(userCode,floorName,floorCode,"卧室");
							BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
							BoRoom room3=RoomUtil.save(userCode,floorName,floorCode,"厨房");
							BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
							BoRoom room4=RoomUtil.save(userCode,floorName,floorCode,"卫生间");
							BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
/*     */                 }
/*     */               }
/*     */             }
/*     */             else
/*     */             {
/* 615 */               this.requestJson.setData(map);
/* 616 */               this.requestJson.setMessage("该邮箱已被其他账号绑定,请重新填写");
/* 617 */               this.requestJson.setSuccess(false);
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 622 */             this.requestJson.setData(map);
/* 623 */             this.requestJson.setMessage("该账号已注册,请去登录");
/* 624 */             this.requestJson.setSuccess(false);
/*     */           }
/*     */         }
/*     */       } else {//邮箱为空等情况 
/* 628 */         BoUsers users = this.boUserService.findByUserPhone(this.userPhone);
/* 629 */         if (users == null)
/*     */         {
/*     */           String emailS;
/* 631 */           if (StringUtils.isEmpty(this.userEmail))
/* 632 */             emailS = "空";
/*     */           else
/* 634 */             emailS = this.userEmail;
/* 635 */           BoUsers email = this.boUserService.findByUserEmail(emailS);
/* 636 */           if (email == null) {
/* 637 */             final String remoteAddr = request.getRemoteAddr();
/* 638 */             long time = new Date().getTime();
/* 639 */             int count = 0;
/* 640 */             String[] ips = (String[])StaticUtil.IP.get(remoteAddr);
/* 641 */             if (ips == null) {
/* 642 */               BoUsers user = UserUtil.save(this.userPhone, md5.getMD5ofStr(this.userPwd), this.userEmail);
						 if(salt != null) {
							  String phpPwd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
							  user.setPhpPasswd(phpPwd);
							  user.setSalt(salt);
						 }
/* 643 */               BoUsers save = (BoUsers)this.boUserService.save(user);
/* 644 */               if (save != null) {
						//new 2-1
						count++;
					    StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
					    this.requestJson.setData(map);
					    this.requestJson.setMessage("注册成功");
					    this.requestJson.setSuccess(true);
					    //注册成功时 默认添加一个楼层和四个房间
						BoFloor floor=FloorUtil.save(save.getUserCode());
						BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
						//String userCode,String floorName,String floorCode,String roomName	
						System.out.println("楼层名称："+saveF.getFloorName());
						String userCode=saveF.getUserCode();
						String floorName=saveF.getFloorName();
						String floorCode=saveF.getFloorCode();
						BoRoom room1=RoomUtil.save(userCode,floorName,floorCode,"客厅");
						BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
						BoRoom room2=RoomUtil.save(userCode,floorName,floorCode,"卧室");
						BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
						BoRoom room3=RoomUtil.save(userCode,floorName,floorCode,"厨房");
						BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
						BoRoom room4=RoomUtil.save(userCode,floorName,floorCode,"卫生间");
						BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
						//END
///* 645 */                 count++;
///* 646 */                 StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
///* 647 */                 this.requestJson.setData(map);
///* 648 */                 this.requestJson.setMessage("注册成功");
///* 649 */                 this.requestJson.setSuccess(true);
/*     */               }
/*     */ 
/*     */             }
/* 653 */             else if (time - Long.valueOf(ips[1]).longValue() <= 86400000L) {
/* 654 */               if (Integer.valueOf(ips[2]).intValue() < 4)
/*     */               {
/* 656 */                 BoUsers user = UserUtil.save(this.userPhone, md5.getMD5ofStr(this.userPwd), this.userEmail);
						//6-15
						 if(salt != null) {
							  String phpPwd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
							  user.setPhpPasswd(phpPwd);
							  user.setSalt(salt);
						 }
/* 657 */                 BoUsers save = (BoUsers)this.boUserService.save(user);
/* 658 */                 if (save != null) {
/* 659 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, Long.valueOf(ips[1])+"", (Integer.valueOf(ips[2]).intValue() + 1)+"" });
///* 660 */                   this.requestJson.setData(map);
///* 661 */                   this.requestJson.setMessage("注册成功");
///* 662 */                   this.requestJson.setSuccess(true);
							//2-6初始化楼层
							count++;
///* 562 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
/* 563 */                   this.requestJson.setData(map);
/* 564 */                   this.requestJson.setMessage("注册成功");
/* 565 */                   this.requestJson.setSuccess(true);
							//注册成功时 默认添加一个楼层和四个房间
							BoFloor floor=FloorUtil.save(save.getUserCode());
							BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
							//String userCode,String floorName,String floorCode,String roomName	
							System.out.println("楼层名称："+saveF.getFloorName());
							String userCode=saveF.getUserCode();
							String floorName=saveF.getFloorName();
//							String floorName="我的家";
							String floorCode=saveF.getFloorCode();
							BoRoom room1=RoomUtil.save(userCode,floorName,floorCode,"客厅");
							BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
							BoRoom room2=RoomUtil.save(userCode,floorName,floorCode,"卧室");
							BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
							BoRoom room3=RoomUtil.save(userCode,floorName,floorCode,"厨房");
							BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
							BoRoom room4=RoomUtil.save(userCode,floorName,floorCode,"卫生间");
							BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
/*     */                 }
/* 664 */               } else if (Integer.valueOf(ips[2]).intValue() == 4) {
/* 665 */                 Timer timer = new Timer();
/*     */                 try {
/* 667 */                   timer.schedule(new TimerTask() {
/*     */                     public void run() {
/* 669 */                       ClearIpUtil.ip = remoteAddr;
/* 670 */                       ClearIpUtil.clearMap();
/*     */                     }
/*     */                   }
/*     */                   , 86400000L);
/*     */                 } catch (NullPointerException e) {
/* 674 */                   logger.info(e.getMessage());
/*     */                 }
/*     */ 
/* 677 */                 StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, Long.valueOf(ips[1])+"", (Integer.valueOf(ips[2]).intValue() + 1)+"" });
/* 678 */                 this.requestJson.setData(map);
/* 679 */                 this.requestJson.setMessage("当前ip请求频繁");
/* 680 */                 this.requestJson.setSuccess(false);
/*     */               } else {
/* 682 */                 this.requestJson.setData(map);
/* 683 */                 this.requestJson.setMessage("当前ip请求频繁");
/* 684 */                 this.requestJson.setSuccess(false);
/*     */               }
/*     */             } else {
/* 687 */               BoUsers user = UserUtil.save(this.userPhone, md5.getMD5ofStr(this.userPwd), this.userEmail);
						//6-15
						 if(salt != null) {
							  String phpPwd=md5.getMD5ofStr(this.userPwd+salt).toLowerCase();
							  user.setPhpPasswd(phpPwd);
							  user.setSalt(salt);
						 }
/* 688 */               BoUsers save = (BoUsers)this.boUserService.save(user);
/* 689 */               if (save != null) {
///* 690 */                 StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
///* 691 */                 this.requestJson.setData(map);
///* 692 */                 this.requestJson.setMessage("注册成功");
///* 693 */                 this.requestJson.setSuccess(true);
                          //2-6 初始化楼层
							count++;
/* 562 */                   StaticUtil.IP.put(remoteAddr, new String[] { remoteAddr, time+"", count+"" });
/* 563 */                   this.requestJson.setData(map);
/* 564 */                   this.requestJson.setMessage("注册成功");
/* 565 */                   this.requestJson.setSuccess(true);
							//注册成功时 默认添加一个楼层和四个房间
							BoFloor floor=FloorUtil.save(save.getUserCode());
							BoFloor saveF=(BoFloor)this.boFloorService.save(floor);
							//String userCode,String floorName,String floorCode,String roomName	
							System.out.println("楼层名称："+saveF.getFloorName());
							String userCode=saveF.getUserCode();
							String floorName=saveF.getFloorName();
//							String floorName="我的家";
							String floorCode=saveF.getFloorCode();
							BoRoom room1=RoomUtil.save(userCode,floorName,floorCode,"客厅");
							BoRoom saveR1=(BoRoom)this.boRoomService.save(room1);
							BoRoom room2=RoomUtil.save(userCode,floorName,floorCode,"卧室");
							BoRoom saveR2=(BoRoom)this.boRoomService.save(room2);
							BoRoom room3=RoomUtil.save(userCode,floorName,floorCode,"厨房");
							BoRoom saveR3=(BoRoom)this.boRoomService.save(room3);
							BoRoom room4=RoomUtil.save(userCode,floorName,floorCode,"卫生间");
							BoRoom saveR4=(BoRoom)this.boRoomService.save(room4);
/*     */               }
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 699 */             this.requestJson.setData(map);
/* 700 */             this.requestJson.setMessage("该邮箱已被其他账号绑定,请重新填写");
/* 701 */             this.requestJson.setSuccess(false);
/*     */           }
/*     */         }
/*     */         else
/*     */         {
/* 706 */           this.requestJson.setData(map);
/* 707 */           this.requestJson.setMessage("该账号已注册,请去登录");
/* 708 */           this.requestJson.setSuccess(false);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 715 */       logger.info("error" + e.getMessage());
/* 716 */       this.requestJson.setData(null);
/* 717 */       this.requestJson.setMessage("服务器发生异常");
/* 718 */       this.requestJson.setSuccess(false);
/*     */     }
/*     */ 
/* 721 */     return "success";
/*     */   }
/*     */ 
/*     */   public String getUserPhone()
/*     */   {
/* 728 */     return this.userPhone;
/*     */   }
/*     */   public void setUserPhone(String userPhone) {
/* 731 */     this.userPhone = userPhone;
/*     */   }
/*     */ 
/*     */   public String getUserPwd() {
/* 735 */     return this.userPwd;
/*     */   }
/*     */   public void setUserPwd(String userPwd) {
/* 738 */     this.userPwd = userPwd;
/*     */   }
/*     */ 
/*     */   public String getUserEmail() {
/* 742 */     return this.userEmail;
/*     */   }
/*     */   public void setUserEmail(String userEmail) {
/* 745 */     this.userEmail = userEmail;
/*     */   }
/*     */ 
/*     */   public String getVersionType() {
/* 749 */     return this.versionType;
/*     */   }
/*     */   public void setVersionType(String versionType) {
/* 752 */     this.versionType = versionType;
/*     */   }
/*     */ 
/*     */   public String getCID() {
/* 756 */     return this.CID;
/*     */   }
/*     */   public void setCID(String cID) {
/* 759 */     this.CID = cID;
/*     */   }
/*     */ 
/*     */   public String getPhoneType() {
/* 763 */     return this.phoneType;
/*     */   }
/*     */   public void setPhoneType(String phoneType) {
/* 766 */     this.phoneType = phoneType;
/*     */   }
/*     */ 
/*     */   public String getCode() {
/* 770 */     return this.code;
/*     */   }
/*     */   public void setCode(String code) {
/* 773 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public RequestJson getRequestJson()
/*     */   {
/* 780 */     return this.requestJson;
/*     */   }
/*     */   public void setRequestJson(RequestJson requestJson) {
/* 783 */     this.requestJson = requestJson;
/*     */   }
			public String getDevicetoken() {
				return devicetoken;
			}
			public void setDevicetoken(String devicetoken) {
				this.devicetoken = devicetoken;
			}
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.AppLogin_Action
 * JD-Core Version:    0.6.2
 */