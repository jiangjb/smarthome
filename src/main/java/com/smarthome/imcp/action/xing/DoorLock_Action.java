/*      */ package com.smarthome.imcp.action.xing;
/*      */ 
/*      */ import com.smarthome.dock.server.helper.PacketProcessHelper;
/*      */ import com.smarthome.dock.server.util.StaticUtil;
/*      */ import com.smarthome.imcp.action.AbstractAction;
/*      */ import com.smarthome.imcp.common.Md5;
/*      */ import com.smarthome.imcp.common.Page;
/*      */ import com.smarthome.imcp.controller.RequestJson;
/*      */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoFingerprintMembers;
/*      */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
/*      */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUnlockingPushRecord;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*      */ import com.smarthome.imcp.service.bo.BoFingerprintMembersServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoLockPasswordManageServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoLockVerdictServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUnlockingPushRecordServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*      */ import com.smarthome.imcp.service.system.FileServiceIface;
/*      */ import com.smarthome.imcp.util.AES;
/*      */ import com.smarthome.imcp.util.StaticUtils;
/*      */ import com.smarthome.imcp.util.UuidUtil;
/*      */ import java.io.File;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import org.apache.commons.lang3.StringUtils;
/*      */ import org.apache.struts2.ServletActionContext;
/*      */ import org.apache.struts2.convention.annotation.Action;
/*      */ import org.apache.struts2.convention.annotation.Namespace;
/*      */ import org.apache.struts2.convention.annotation.ParentPackage;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ 
/*      */ @ParentPackage("auth-check-package")
/*      */ @Namespace("/doorlock")
/*      */ public class DoorLock_Action extends AbstractAction
/*      */ {
/*   68 */   private static Logger logger = LoggerFactory.getLogger(DoorLock_Action.class);
/*   69 */   private static Map<String, Integer> user_num = new HashMap();
/*      */   public static final String SECTET = "12345";
/* 2374 */   private String userCode = "";
/*      */ 
/* 2382 */   private String userPhone = "";
/*      */ 
/* 2390 */   private String userPwd = "";
/*      */ 
/* 2398 */   private String lockAddress = "";
/*      */ 
/* 2405 */   private String deviceAddress = "";
/*      */ 
/* 2412 */   private String lockPwd = "";
/*      */ 
/* 2419 */   private String lockType = "";
/*      */ 
/* 2426 */   private String lockOfTimes = "";
/*      */ 
/* 2433 */   private String adminPwd = "";
/*      */ 
/* 2440 */   private String startTime = "";
/*      */ 
/* 2447 */   private String endTime = "";
/*      */ 
/* 2454 */   private String pushSet = "";
/*      */ 
/* 2461 */   private String fingerprintMembersId = "";
/*      */ 
/* 2468 */   private String membersName = "";
/*      */ 
/* 2477 */   private Integer pageNum = Integer.valueOf(1); private Integer pageSize = Integer.valueOf(50);
/*      */   private String orderField;
/*      */   private String orderDirection;
/*      */   private File fileupload;
/*      */   private String fileuploadFileName;
/*      */   private String fileuploadContentType;
/*      */ 
/*      */   @Autowired
/*      */   private PacketProcessHelper packetProcessHelper;
/*      */ 
/*      */   @Autowired
/*      */   private BoUserssServiceIface<BoUsers, Serializable> boUserServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/*      */ 
/*      */   @Autowired
/*      */   private BoFingerprintMembersServiceIface<BoFingerprintMembers, Serializable> boFingerprintMembersService;
/*      */ 
/*      */   @Autowired
/*      */   private BoUnlockingPushRecordServiceIface<BoUnlockingPushRecord, Serializable> boUnlockingPushRecordServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoLockPasswordManageServiceIface<BoLockPasswordManage, Serializable> boLockPasswordManageServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoLockVerdictServiceIface<BoLockVerdict, Serializable> boLockVerdictService;
/*      */ 
/*      */   @Autowired
/*      */   private FileServiceIface<Object, Serializable> fileService;
/* 2543 */   private RequestJson requestJson = new RequestJson();
/*      */ 
/*      */   public Boolean isRal(String timestamp, String nonce, String sign, String access_Token, String userCode, String interfaceName)
/*      */   {
/*   85 */     HttpServletRequest request = ServletActionContext.getRequest();
/*   86 */     String ip = request.getRemoteAddr();
/*   87 */     String str = "";
/*   88 */     Md5 md5 = new Md5();
/*   89 */     boolean isRel = true;
/*      */ 
/*   91 */     str = str + "access_token=";
/*   92 */     str = str + access_Token;
/*   93 */     System.err.println("access_Token " + access_Token);
/*   94 */     str = str + "&nonce=";
/*   95 */     str = str + nonce;
/*   96 */     System.err.println("nonce " + nonce);
/*   97 */     str = str + "&timestamp=";
/*   98 */     str = str + timestamp;
/*   99 */     System.err.println("timestamp " + timestamp);
/*  100 */     str = str + "&userCode=";
/*  101 */     str = str + userCode;
/*  102 */     System.err.println("userCode " + userCode);
/*  103 */     str = str + "12345";
/*  104 */     String service_sign = md5.getMD5ofStr(str).toLowerCase();
/*  105 */     System.err.println("客户端" + sign);
/*  106 */     System.err.println("服务器" + service_sign);
/*  107 */     if (service_sign.equals(sign)) {
/*  108 */       return Boolean.valueOf(isRel);
/*      */     }
/*  110 */     logger.error("ip： " + ip + "   " + "接口昵称：" + interfaceName + " 验签验证不通过");
/*  111 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */   public void packNum(String userCode)
/*      */   {
/*  120 */     if (user_num.get(userCode) == null)
/*  121 */       user_num.put(userCode, Integer.valueOf(0));
/*      */     else
/*  123 */       user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*      */   }
/*      */ 
/*      */   @Action(value="initLockLogin", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String initLockLogin()
/*      */     throws InterruptedException
/*      */   {
/*  138 */     this.requestJson = new RequestJson();
/*  139 */     this.requestJson = new RequestJson();
/*  140 */     Md5 md5 = new Md5();
/*  141 */     Map map = new HashMap();
/*  142 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  143 */     String header = request.getHeader("timestamp");
/*  144 */     String header2 = request.getHeader("nonce");
/*  145 */     String header3 = request.getHeader("sign");
/*  146 */     String header4 = request.getHeader("access_token");
/*  147 */     String userCode = request.getHeader("userCode");
/*  148 */     Boolean ral = isRal(header, header2, header3, header4, userCode, "查询房间设备状态");
/*  149 */     if (ral.booleanValue()) {
/*  150 */       System.err.println("验证通过");
/*      */       try {
/*  152 */         BoUsers users = this.boUserServicess.findByUserPhonePwd(this.userPhone, md5.getMD5ofStr(this.userPwd));
/*  153 */         if (users != null) {
/*  154 */           this.requestJson.setData(map);
/*  155 */           this.requestJson.setMessage("密码正确");
/*  156 */           this.requestJson.setSuccess(true);
/*      */         } else {
/*  158 */           this.requestJson.setData(map);
/*  159 */           this.requestJson.setMessage("密码不正确");
/*  160 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       catch (Exception e) {
/*  164 */         logger.info("error" + e.getMessage());
/*  165 */         this.requestJson.setData(map);
/*  166 */         this.requestJson.setMessage("服务器发生异常");
/*  167 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*      */     else {
/*  171 */       System.err.println("验证不通过");
/*  172 */       this.requestJson.setData(map);
/*  173 */       this.requestJson.setMessage("验证不通过");
/*  174 */       this.requestJson.setSuccess(false);
/*      */     }
/*      */ 
/*  177 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="lockPwdDelete", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String lockPwdDelete()
/*      */     throws InterruptedException
/*      */   {
/*  189 */     this.requestJson = new RequestJson();
/*  190 */     Map map = new HashMap();
/*  191 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  192 */     Md5 md5 = new Md5();
/*  193 */     String header = request.getHeader("timestamp");
/*  194 */     String header2 = request.getHeader("nonce");
/*  195 */     String header3 = request.getHeader("sign");
/*  196 */     String access_token = request.getHeader("access_token");
/*  197 */     String userCode = request.getHeader("userCode");
/*  198 */     String userPhone = request.getHeader("userPhone");
/*  199 */     String language = request.getHeader("Accept-Language");
/*  200 */     if (userCode.contains(",")) {
/*  201 */       String[] userCode2 = userCode.split(",");
/*  202 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  203 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  204 */       Boolean ral = isRal(header, header2, header3, access_token, userCode, "临时密码删除");
/*  205 */       if (ral.booleanValue()) {
/*  206 */         System.err.println("验证通过");
/*  207 */         Long accessToken = Long.valueOf(header);
/*  208 */         if ((phone == null) || (boUsers == null)) {
/*  209 */           this.requestJson.setData(map);
/*  210 */           this.requestJson.setMessage("Invalid_User");
/*  211 */           this.requestJson.setSuccess(true);
/*      */         }
/*  213 */         else if (access_token.equals(phone.getAccessToken())) {
/*  214 */           if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  216 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/*  217 */               if (boHostDevice == null) {
/*  218 */                 this.requestJson.setData(map);
/*  219 */                 this.requestJson.setMessage("没有找到");
/*  220 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/*  222 */                 BoLockPasswordManage lockPasswordManage = this.boLockPasswordManageServicess.findBylockAddress(userCode2[0].trim().toString(), this.lockAddress, Integer.valueOf(65535));
/*  223 */                 if (lockPasswordManage == null) {
/*  224 */                   this.requestJson.setData(map);
/*  225 */                   this.requestJson.setMessage("没有临时密码,请先设置个临时密码");
/*  226 */                   this.requestJson.setSuccess(false);
/*      */                 } else {
/*      */                   try {
/*  229 */                     packNum(userCode2[0].trim().toString());
/*  230 */                     String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + 65535 + "," + this.adminPwd;
/*  231 */                     byte[] Delete = DeleteString.getBytes();
/*  232 */                     System.err.println(new String(Delete));
/*  233 */                     this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Delete);
/*  234 */                     StaticUtil.PWDDELETE.put(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress, userCode2[0].trim().toString());
/*      */ 
/*  236 */                     Thread.sleep(1500L);
/*  237 */                     Object object = StaticUtil.PWDDELETES.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*  238 */                     String delete = "";
/*  239 */                     if (object != null) {
/*  240 */                       delete = (String) object;
/*  241 */                       if (delete.equals("OK")) {
/*  242 */                         this.boLockPasswordManageServicess.delete(lockPasswordManage);
/*  243 */                         this.requestJson.setData(map);
/*  244 */                         this.requestJson.setMessage("操作成功");
/*  245 */                         this.requestJson.setSuccess(true);
/*      */                       } else {
/*  247 */                         this.requestJson.setData(map);
/*  248 */                         this.requestJson.setMessage("操作失败,管理员密码错误");
/*  249 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     } else {
/*  252 */                       this.requestJson.setData(map);
/*  253 */                       this.requestJson.setMessage("网络超时,通讯失败");
/*  254 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } finally {
/*  257 */                     StaticUtil.PWDDELETE.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*  258 */                     StaticUtil.PWDDELETES.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/*  264 */               if (language.equals("zh-cn")) {
/*  265 */                 logger.error(e.getMessage());
/*  266 */                 this.requestJson.setData(map);
/*  267 */                 this.requestJson.setMessage("服务器发生异常");
/*  268 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/*  270 */                 logger.error(e.getMessage());
/*  271 */                 this.requestJson.setData(map);
/*  272 */                 this.requestJson.setMessage("server exception occurs");
/*  273 */                 this.requestJson.setSuccess(false);
/*      */               }
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  279 */             System.err.println("AToken时间戳超时了");
/*  280 */             this.requestJson.setData(map);
/*  281 */             this.requestJson.setMessage("超时了");
/*  282 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  285 */           System.err.println("AToken超时了");
/*  286 */           this.requestJson.setData(map);
/*  287 */           this.requestJson.setMessage("超时了");
/*  288 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  292 */         System.err.println("验证不通过");
/*  293 */         this.requestJson.setData(map);
/*  294 */         this.requestJson.setMessage("验证不通过");
/*  295 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  298 */       Boolean ral = isRal(header, header2, header3, access_token, userCode, "临时密码删除");
/*  299 */       if (ral.booleanValue()) {
/*  300 */         System.err.println("验证通过");
/*  301 */         Long accessToken = Long.valueOf(header);
/*  302 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  303 */         if (boUsers == null) {
/*  304 */           this.requestJson.setData(map);
/*  305 */           this.requestJson.setMessage("Invalid_User");
/*  306 */           this.requestJson.setSuccess(true);
/*      */         }
/*  308 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  310 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/*  311 */             if (boHostDevice == null) {
/*  312 */               this.requestJson.setData(map);
/*  313 */               this.requestJson.setMessage("没有找到");
/*  314 */               this.requestJson.setSuccess(true);
/*      */             } else {
/*  316 */               BoLockPasswordManage lockPasswordManage = this.boLockPasswordManageServicess.findBylockAddress(userCode, this.lockAddress, Integer.valueOf(65535));
/*  317 */               if (lockPasswordManage == null) {
/*  318 */                 this.requestJson.setData(map);
/*  319 */                 this.requestJson.setMessage("没有临时密码,请先设置个临时密码");
/*  320 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/*      */                 try {
/*  323 */                   packNum(userCode);
/*  324 */                   String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(userCode) + "," + this.lockAddress + "," + 65535 + "," + this.adminPwd;
/*  325 */                   byte[] Delete = DeleteString.getBytes();
/*  326 */                   System.err.println(new String(Delete));
/*  327 */                   this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Delete);
/*  328 */                   StaticUtil.PWDDELETE.put(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress, userCode);
/*      */ 
/*  330 */                   Thread.sleep(1500L);
/*  331 */                   Object object = StaticUtil.PWDDELETES.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*  332 */                   String delete = "";
/*  333 */                   if (object != null) {
/*  334 */                     delete = (String) object;
/*  335 */                     if (delete.equals("OK")) {
/*  336 */                       this.boLockPasswordManageServicess.delete(lockPasswordManage);
/*  337 */                       this.requestJson.setData(map);
/*  338 */                       this.requestJson.setMessage("操作成功");
/*  339 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/*  341 */                       this.requestJson.setData(map);
/*  342 */                       this.requestJson.setMessage("操作失败,管理员密码错误");
/*  343 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/*  346 */                     this.requestJson.setData(map);
/*  347 */                     this.requestJson.setMessage("网络超时,通讯失败");
/*  348 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/*  351 */                   StaticUtil.PWDDELETE.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*  352 */                   StaticUtil.PWDDELETES.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/*  358 */             if (language.equals("zh-cn")) {
/*  359 */               logger.error(e.getMessage());
/*  360 */               this.requestJson.setData(map);
/*  361 */               this.requestJson.setMessage("服务器发生异常");
/*  362 */               this.requestJson.setSuccess(false);
/*      */             } else {
/*  364 */               logger.error(e.getMessage());
/*  365 */               this.requestJson.setData(map);
/*  366 */               this.requestJson.setMessage("server exception occurs");
/*  367 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  373 */           this.requestJson.setData(map);
/*  374 */           this.requestJson.setMessage("超时了");
/*  375 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  379 */         System.err.println("验证不通过");
/*  380 */         this.requestJson.setData(map);
/*  381 */         this.requestJson.setMessage("验证不通过");
/*  382 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  385 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="updateFingerprintName", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String updateFingerprintName()
/*      */   {
/*  396 */     this.requestJson = new RequestJson();
/*  397 */     Map map = new HashMap();
/*  398 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  399 */     String str = "";
/*  400 */     Md5 md5 = new Md5();
/*  401 */     String header = request.getHeader("timestamp");
/*  402 */     String header2 = request.getHeader("nonce");
/*  403 */     String header3 = request.getHeader("sign");
/*  404 */     String access_token = request.getHeader("access_token");
/*  405 */     String userCode = request.getHeader("userCode");
/*  406 */     String userPhone = request.getHeader("userPhone");
/*  407 */     String language = request.getHeader("Accept-Language");
/*  408 */     if (userCode.contains(",")) {
/*  409 */       String[] userCode2 = userCode.split(",");
/*  410 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  411 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  412 */       Boolean ral = isRal(header, header2, header3, access_token, userCode, "指纹成员修改昵称");
/*  413 */       if (ral.booleanValue()) {
/*  414 */         System.err.println("验证通过");
/*  415 */         Long accessToken = Long.valueOf(header);
/*  416 */         if ((phone == null) || (boUsers == null)) {
/*  417 */           this.requestJson.setData(map);
/*  418 */           this.requestJson.setMessage("Invalid_User");
/*  419 */           this.requestJson.setSuccess(true);
/*      */         }
/*  421 */         else if (access_token.equals(phone.getAccessToken())) {
/*  422 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  424 */               BoFingerprintMembers findFingerprint = this.boFingerprintMembersService.findFingerprint(this.fingerprintMembersId);
/*  425 */               if (findFingerprint == null) {
/*  426 */                 this.requestJson.setData(map);
/*  427 */                 this.requestJson.setMessage("没有该成员");
/*  428 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/*  430 */                 findFingerprint.setMembersName(this.membersName);
/*  431 */                 BoFingerprintMembers update = (BoFingerprintMembers)this.boFingerprintMembersService.update(findFingerprint);
/*  432 */                 if (update != null) {
/*  433 */                   this.requestJson.setData(map);
/*  434 */                   this.requestJson.setMessage("修改成功");
/*  435 */                   this.requestJson.setSuccess(true);
/*      */                 } else {
/*  437 */                   this.requestJson.setData(map);
/*  438 */                   this.requestJson.setMessage("修改失败");
/*  439 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/*  444 */               logger.error(e.getMessage());
/*  445 */               this.requestJson.setData(map);
/*  446 */               this.requestJson.setMessage("服务器发生异常");
/*  447 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/*  452 */             System.err.println("AToken时间戳超时了");
/*  453 */             this.requestJson.setData(map);
/*  454 */             this.requestJson.setMessage("超时了");
/*  455 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  458 */           System.err.println("AToken超时了");
/*  459 */           this.requestJson.setData(map);
/*  460 */           this.requestJson.setMessage("超时了");
/*  461 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  465 */         System.err.println("验证不通过");
/*  466 */         this.requestJson.setData(map);
/*  467 */         this.requestJson.setMessage("验证不通过");
/*  468 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  471 */       Boolean ral = isRal(header, header2, header3, access_token, userCode, "指纹成员修改昵称");
/*  472 */       if (ral.booleanValue()) {
/*  473 */         System.err.println("验证通过");
/*  474 */         Long accessToken = Long.valueOf(header);
/*  475 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  476 */         if (boUsers == null) {
/*  477 */           this.requestJson.setData(map);
/*  478 */           this.requestJson.setMessage("Invalid_User");
/*  479 */           this.requestJson.setSuccess(true);
/*      */         }
/*  482 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  484 */             BoFingerprintMembers findFingerprint = this.boFingerprintMembersService.findFingerprint(this.fingerprintMembersId);
/*  485 */             if (findFingerprint == null) {
/*  486 */               this.requestJson.setData(map);
/*  487 */               this.requestJson.setMessage("没有该成员");
/*  488 */               this.requestJson.setSuccess(true);
/*      */             } else {
/*  490 */               findFingerprint.setMembersName(this.membersName);
/*  491 */               BoFingerprintMembers update = (BoFingerprintMembers)this.boFingerprintMembersService.update(findFingerprint);
/*  492 */               if (update != null) {
/*  493 */                 this.requestJson.setData(map);
/*  494 */                 this.requestJson.setMessage("修改成功");
/*  495 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/*  497 */                 this.requestJson.setData(map);
/*  498 */                 this.requestJson.setMessage("修改失败");
/*  499 */                 this.requestJson.setSuccess(false);
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/*  504 */             logger.error(e.getMessage());
/*  505 */             this.requestJson.setData(map);
/*  506 */             this.requestJson.setMessage("服务器发生异常");
/*  507 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         }
/*      */         else
/*      */         {
/*  512 */           this.requestJson.setData(map);
/*  513 */           this.requestJson.setMessage("超时了");
/*  514 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  518 */         System.err.println("验证不通过");
/*  519 */         this.requestJson.setData(map);
/*  520 */         this.requestJson.setMessage("验证不通过");
/*  521 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  524 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="fingerprintHeadpic", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String fileUploads()
/*      */   {
/*  535 */     this.requestJson = new RequestJson();
/*  536 */     Map map = new HashMap();
/*  537 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  538 */     String str = "";
/*  539 */     Md5 md5 = new Md5();
/*  540 */     String header = request.getHeader("timestamp");
/*  541 */     String header2 = request.getHeader("nonce");
/*  542 */     String header3 = request.getHeader("sign");
/*  543 */     String access_token = request.getHeader("access_token");
/*  544 */     String userCode = request.getHeader("userCode");
/*  545 */     String userPhone = request.getHeader("userPhone");
/*  546 */     if (userCode.contains(",")) {
/*  547 */       String[] userCode2 = userCode.split(",");
/*  548 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  549 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  550 */       Boolean ral = isRal(header, header2, header3, access_token, userCode, "指纹成员头像上传");
/*  551 */       if (ral.booleanValue()) {
/*  552 */         System.err.println("验证通过");
/*  553 */         Long accessToken = Long.valueOf(header);
/*  554 */         if ((phone == null) || (boUsers == null)) {
/*  555 */           this.requestJson.setData(map);
/*  556 */           this.requestJson.setMessage("Invalid_User");
/*  557 */           this.requestJson.setSuccess(true);
/*      */         }
/*  559 */         else if (access_token.equals(phone.getAccessToken())) {
/*  560 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  562 */               this.requestJson = new RequestJson();
/*      */ 
/*  564 */               BoFingerprintMembers findFingerprint = this.boFingerprintMembersService.findFingerprint(this.fingerprintMembersId);
/*  565 */               String dir = "uploads/fingerprintHeadpic";
/*  566 */               System.err.println("<>>>>>>>>??? " + this.fileupload);
/*  567 */               String filePath = this.fileService.saveToDir(this.fileupload, 
/*  568 */                 userCode2[0].trim().toString() + "head" + UuidUtil.get32UUID() + ".jpg", dir);
/*  569 */               findFingerprint.setMembersHeadpic(filePath);
/*  570 */               BoFingerprintMembers update = (BoFingerprintMembers)this.boFingerprintMembersService.update(findFingerprint);
/*  571 */               map.put("membersHeadpic", update.getMembersHeadpic());
/*  572 */               this.requestJson.setData(map);
/*  573 */               this.requestJson.setMessage("头像上传成功");
/*  574 */               this.requestJson.setSuccess(true);
/*  575 */               return "success";
/*      */             } catch (Exception e) {
/*  577 */               this.requestJson.setData(map);
/*  578 */               this.requestJson.setMessage("头像上传失败");
/*  579 */               this.requestJson.setSuccess(false);
/*  580 */               e.printStackTrace();
/*  581 */               return "success";
/*      */             }
/*      */           }
/*  584 */           System.err.println("AToken时间戳超时了");
/*  585 */           this.requestJson.setData(map);
/*  586 */           this.requestJson.setMessage("超时了");
/*  587 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */         else {
/*  590 */           System.err.println("AToken超时了");
/*  591 */           this.requestJson.setData(map);
/*  592 */           this.requestJson.setMessage("超时了");
/*  593 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  597 */         System.err.println("验证不通过");
/*  598 */         this.requestJson.setData(map);
/*  599 */         this.requestJson.setMessage("验证不通过");
/*  600 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  603 */       Boolean ral = isRal(header, header2, header3, access_token, userCode, "指纹成员头像上传");
/*  604 */       if (ral.booleanValue()) {
/*  605 */         System.err.println("验证通过");
/*  606 */         Long accessToken = Long.valueOf(header);
/*  607 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  608 */         if (boUsers == null) {
/*  609 */           this.requestJson.setData(map);
/*  610 */           this.requestJson.setMessage("Invalid_User");
/*  611 */           this.requestJson.setSuccess(true);
/*      */         } else {
/*  613 */           if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  615 */               this.requestJson = new RequestJson();
/*      */ 
/*  617 */               BoFingerprintMembers findFingerprint = this.boFingerprintMembersService.findFingerprint(this.fingerprintMembersId);
/*  618 */               String dir = "uploads/fingerprintHeadpic";
/*  619 */               System.err.println("<>>>>>>>>??? " + this.fileupload);
/*  620 */               String filePath = this.fileService.saveToDir(this.fileupload, 
/*  621 */                 userCode + "head" + UuidUtil.get32UUID() + ".jpg", dir);
/*  622 */               findFingerprint.setMembersHeadpic(filePath);
/*  623 */               BoFingerprintMembers update = (BoFingerprintMembers)this.boFingerprintMembersService.update(findFingerprint);
/*  624 */               map.put("membersHeadpic", update.getMembersHeadpic());
/*  625 */               this.requestJson.setData(map);
/*  626 */               this.requestJson.setMessage("头像上传成功");
/*  627 */               this.requestJson.setSuccess(true);
/*  628 */               return "success";
/*      */             } catch (Exception e) {
/*  630 */               this.requestJson.setData(map);
/*  631 */               this.requestJson.setMessage("头像上传失败");
/*  632 */               this.requestJson.setSuccess(false);
/*  633 */               e.printStackTrace();
/*  634 */               return "success";
/*      */             }
/*      */           }
/*  637 */           this.requestJson.setData(map);
/*  638 */           this.requestJson.setMessage("超时了");
/*  639 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  643 */         System.err.println("验证不通过");
/*  644 */         this.requestJson.setData(map);
/*  645 */         this.requestJson.setMessage("验证不通过");
/*  646 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  649 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="gainFingerprintMembers", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String gainFingerprintMembers()
/*      */     throws InterruptedException
/*      */   {
/*  659 */     this.requestJson = new RequestJson();
/*  660 */     Map map = new HashMap();
/*  661 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  662 */     String ip = request.getRemoteAddr();
/*  663 */     String timestamp = request.getHeader("timestamp");
/*  664 */     String nonce = request.getHeader("nonce");
/*  665 */     String sign = request.getHeader("sign");
/*  666 */     String access_token = request.getHeader("access_token");
/*  667 */     String userCode = request.getHeader("userCode");
/*      */     List<BoFingerprintMembers> list;
/*  668 */     if (userCode.contains(",")) {
/*  669 */       String[] userCode2 = userCode.split(",");
/*  670 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  671 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  672 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "成员列表");
/*  673 */       if (ral.booleanValue()) {
/*  674 */         System.err.println("验证通过");
/*  675 */         Long accessToken = Long.valueOf(timestamp);
/*  676 */         if ((phone == null) || (boUsers == null)) {
/*  677 */           this.requestJson.setData(map);
/*  678 */           this.requestJson.setMessage("Invalid_User");
/*  679 */           this.requestJson.setSuccess(true);
/*      */         }
/*  681 */         else if (access_token.equals(phone.getAccessToken())) {
/*  682 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  684 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/*  685 */               if (boHostDevice == null) {
/*  686 */                 this.requestJson.setData(map);
/*  687 */                 this.requestJson.setMessage("没有找到");
/*  688 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/*  690 */                 Thread.sleep(2000L);
/*  691 */                 packNum(userCode2[0].trim().toString());
/*  692 */                 String strss = "ZIGBEE_LOCK-NUM-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress;
/*  693 */                 byte[] bss = strss.getBytes();
/*  694 */                 System.err.println(new String(bss));
/*  695 */                 this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/*      */ 
/*  697 */                 Thread.sleep(2000L);
/*  698 */                 list = this.boFingerprintMembersService.get(this.lockAddress);
/*  699 */                 if (list.size() <= 0) {
/*  700 */                   this.requestJson.setData(map);
/*  701 */                   this.requestJson.setMessage("没有找到成员");
/*  702 */                   this.requestJson.setSuccess(false);
/*      */                 } else {
/*  704 */                   List voLists = new ArrayList();
/*  705 */                   Map fingerprintMembers = null;
/*      */ 
/*  707 */                   for (BoFingerprintMembers boFingerprintMembers : list) {
/*  708 */                     fingerprintMembers = new HashMap();
/*  709 */                     fingerprintMembers.put("fingerprintMembersId", boFingerprintMembers.getFingerprintMembersId());
/*  710 */                     fingerprintMembers.put("subscript", boFingerprintMembers.getFingerprintSubscript());
/*  711 */                     fingerprintMembers.put("lockAddress", boFingerprintMembers.getLockAddress());
/*  712 */                     fingerprintMembers.put("membersHeadpic", boFingerprintMembers.getMembersHeadpic());
/*  713 */                     fingerprintMembers.put("membersName", boFingerprintMembers.getMembersName());
/*  714 */                     voLists.add(fingerprintMembers);
/*      */                   }
/*  716 */                   this.requestJson.setData(voLists);
/*  717 */                   this.requestJson.setSuccess(true);
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/*  727 */               logger.error(e.getMessage());
/*  728 */               this.requestJson.setData(map);
/*  729 */               this.requestJson.setMessage("服务器发生异常");
/*  730 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/*  733 */             System.err.println("AToken时间戳超时了");
/*  734 */             this.requestJson.setData(map);
/*  735 */             this.requestJson.setMessage("超时了");
/*  736 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  739 */           System.err.println("AToken超时了");
/*  740 */           this.requestJson.setData(map);
/*  741 */           this.requestJson.setMessage("超时了");
/*  742 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  746 */         System.err.println("验证不通过");
/*  747 */         this.requestJson.setData(map);
/*  748 */         this.requestJson.setMessage("验证不通过");
/*  749 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  752 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "成员列表");
/*  753 */       if (ral.booleanValue()) {
/*  754 */         System.err.println("验证通过");
/*  755 */         Long accessToken = Long.valueOf(timestamp);
/*  756 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  757 */         if (boUsers == null) {
/*  758 */           this.requestJson.setData(map);
/*  759 */           this.requestJson.setMessage("Invalid_User");
/*  760 */           this.requestJson.setSuccess(true);
/*      */         }
/*  762 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  764 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/*  765 */             if (boHostDevice == null) {
/*  766 */               this.requestJson.setData(map);
/*  767 */               this.requestJson.setMessage("没有找到");
/*  768 */               this.requestJson.setSuccess(true);
/*      */             } else {
/*  770 */               list = this.boFingerprintMembersService.get(this.lockAddress);
/*  771 */               if (list.size() <= 0) {
/*  772 */                 this.requestJson.setData(map);
/*  773 */                 this.requestJson.setMessage("没有找到成员");
/*  774 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/*  776 */                 List voLists = new ArrayList();
/*  777 */                 Map fingerprintMembers = null;
/*      */ 
/*  779 */                 for (BoFingerprintMembers boFingerprintMembers : list) {
/*  780 */                   fingerprintMembers = new HashMap();
/*  781 */                   fingerprintMembers.put("fingerprintMembersId", boFingerprintMembers.getFingerprintMembersId());
/*  782 */                   fingerprintMembers.put("subscript", boFingerprintMembers.getFingerprintSubscript());
/*  783 */                   fingerprintMembers.put("lockAddress", boFingerprintMembers.getLockAddress());
/*  784 */                   fingerprintMembers.put("membersHeadpic", boFingerprintMembers.getMembersHeadpic());
/*  785 */                   fingerprintMembers.put("membersName", boFingerprintMembers.getMembersName());
/*  786 */                   voLists.add(fingerprintMembers);
/*      */                 }
/*  788 */                 this.requestJson.setData(voLists);
/*  789 */                 this.requestJson.setSuccess(true);
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/*  799 */             logger.error(e.getMessage());
/*  800 */             this.requestJson.setData(map);
/*  801 */             this.requestJson.setMessage("服务器发生异常");
/*  802 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  805 */           this.requestJson.setData(map);
/*  806 */           this.requestJson.setMessage("超时了");
/*  807 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  812 */         System.err.println("验证不通过");
/*  813 */         this.requestJson.setData(map);
/*  814 */         this.requestJson.setMessage("验证不通过");
/*  815 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  818 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="pushRecord", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String pushRecord()
/*      */     throws InterruptedException
/*      */   {
/*  829 */     this.requestJson = new RequestJson();
/*  830 */     Map map = new HashMap();
/*  831 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  832 */     String ip = request.getRemoteAddr();
/*  833 */     String timestamp = request.getHeader("timestamp");
/*  834 */     String nonce = request.getHeader("nonce");
/*  835 */     String sign = request.getHeader("sign");
/*  836 */     String access_token = request.getHeader("access_token");
/*  837 */     String userCode = request.getHeader("userCode");
/*      */     List voList;
/*  838 */     if (userCode.contains(",")) {
/*  839 */       String[] userCode2 = userCode.split(",");
/*  840 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  841 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  842 */       Page page = new Page();
/*  843 */       page.setPageSize(this.pageSize.intValue());
/*  844 */       page.setPageNum(this.pageNum.intValue());
/*      */ 
/*  846 */       if (StringUtils.isNotEmpty(this.orderField)) {
/*  847 */         page.setOrderField(this.orderField);
/*      */       }
/*      */ 
/*  850 */       if (StringUtils.isNotEmpty(this.orderDirection)) {
/*  851 */         page.setOrderDirection(this.orderDirection);
/*      */       }
/*  853 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "开锁推送记录");
/*  854 */       if (ral.booleanValue()) {
/*  855 */         System.err.println("验证通过");
/*  856 */         Long accessToken = Long.valueOf(timestamp);
/*  857 */         if ((phone == null) || (boUsers == null)) {
/*  858 */           this.requestJson.setData(map);
/*  859 */           this.requestJson.setMessage("Invalid_User");
/*  860 */           this.requestJson.setSuccess(true);
/*      */         }
/*  862 */         else if (access_token.equals(phone.getAccessToken())) {
/*  863 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  865 */               List<BoUnlockingPushRecord> record = this.boUnlockingPushRecordServicess.getRecord(userCode2[0].trim().toString(), page);
/*  866 */               if (record.size() <= 0) {
/*  867 */                 this.requestJson.setData(map);
/*  868 */                 this.requestJson.setMessage("没有找到报警记录");
/*  869 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/*  871 */                 voList = new ArrayList();
/*  872 */                 Map unlockingPushRecord = null;
/*      */                 try {
/*  874 */                   for (BoUnlockingPushRecord boUnlockingPushRecord : record) {
/*  875 */                     unlockingPushRecord = new HashMap();
/*  876 */                     unlockingPushRecord.put("methodsStatus", boUnlockingPushRecord.getMethodsStatus());
/*  877 */                     unlockingPushRecord.put("alarmPhoneType", boUnlockingPushRecord.getAlarmPhoneType());
/*  878 */                     unlockingPushRecord.put("reportDate", boUnlockingPushRecord.getReportDate());
/*  879 */                     voList.add(unlockingPushRecord);
/*      */                   }
/*  881 */                   this.requestJson.setData(voList);
/*  882 */                   this.requestJson.setSuccess(true);
/*      */                 } finally {
/*  884 */                   voList.remove(map);
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/*  889 */               logger.error(e.getMessage());
/*  890 */               this.requestJson.setData(map);
/*  891 */               this.requestJson.setMessage("服务器发生异常");
/*  892 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/*  895 */             System.err.println("AToken时间戳超时了");
/*  896 */             this.requestJson.setData(map);
/*  897 */             this.requestJson.setMessage("超时了");
/*  898 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  901 */           System.err.println("AToken超时了");
/*  902 */           this.requestJson.setData(map);
/*  903 */           this.requestJson.setMessage("超时了");
/*  904 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  908 */         System.err.println("验证不通过");
/*  909 */         this.requestJson.setData(map);
/*  910 */         this.requestJson.setMessage("验证不通过");
/*  911 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  914 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "开锁推送记录");
/*  915 */       if (ral.booleanValue()) {
/*  916 */         System.err.println("验证通过");
/*  917 */         Long accessToken = Long.valueOf(timestamp);
/*  918 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  919 */         if (boUsers == null) {
/*  920 */           this.requestJson.setData(map);
/*  921 */           this.requestJson.setMessage("Invalid_User");
/*  922 */           this.requestJson.setSuccess(true);
/*      */         }
/*  924 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  926 */             List<BoUnlockingPushRecord> record = this.boUnlockingPushRecordServicess.getRecord(userCode, this.page);
/*  927 */             if (record.size() <= 0) {
/*  928 */               this.requestJson.setData(map);
/*  929 */               this.requestJson.setMessage("没有找到报警记录");
/*  930 */               this.requestJson.setSuccess(false);
/*      */             } else {
/*  932 */               voList = new ArrayList();
/*  933 */               Map unlockingPushRecord = null;
/*      */               try {
/*  935 */                 for (BoUnlockingPushRecord boUnlockingPushRecord : record) {
/*  936 */                   unlockingPushRecord = new HashMap();
/*  937 */                   unlockingPushRecord.put("methodsStatus", boUnlockingPushRecord.getMethodsStatus());
/*  938 */                   unlockingPushRecord.put("alarmPhoneType", boUnlockingPushRecord.getAlarmPhoneType());
/*  939 */                   unlockingPushRecord.put("reportDate", boUnlockingPushRecord.getReportDate());
/*  940 */                   voList.add(unlockingPushRecord);
/*      */                 }
/*  942 */                 this.requestJson.setData(voList);
/*  943 */                 this.requestJson.setSuccess(true);
/*      */               } finally {
/*  945 */                 voList.remove(map);
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/*  950 */             logger.error(e.getMessage());
/*  951 */             this.requestJson.setData(map);
/*  952 */             this.requestJson.setMessage("服务器发生异常");
/*  953 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  956 */           this.requestJson.setData(map);
/*  957 */           this.requestJson.setMessage("超时了");
/*  958 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  963 */         System.err.println("验证不通过");
/*  964 */         this.requestJson.setData(map);
/*  965 */         this.requestJson.setMessage("验证不通过");
/*  966 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  969 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="pushSet", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String pushSet()
/*      */     throws InterruptedException
/*      */   {
/*  979 */     this.requestJson = new RequestJson();
/*  980 */     Map map = new HashMap();
/*  981 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  982 */     String ip = request.getRemoteAddr();
/*  983 */     String timestamp = request.getHeader("timestamp");
/*  984 */     String nonce = request.getHeader("nonce");
/*  985 */     String sign = request.getHeader("sign");
/*  986 */     String access_token = request.getHeader("access_token");
/*  987 */     String userCode = request.getHeader("userCode");
/*  988 */     if (userCode.contains(",")) {
/*  989 */       String[] userCode2 = userCode.split(",");
/*  990 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  991 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  992 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "推送设置");
/*  993 */       if (ral.booleanValue()) {
/*  994 */         System.err.println("验证通过");
/*  995 */         Long accessToken = Long.valueOf(timestamp);
/*  996 */         if ((phone == null) || (boUsers == null)) {
/*  997 */           this.requestJson.setData(map);
/*  998 */           this.requestJson.setMessage("Invalid_User");
/*  999 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1001 */         else if (access_token.equals(phone.getAccessToken())) {
/* 1002 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 1004 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/* 1005 */               if (boHostDevice == null) {
/* 1006 */                 this.requestJson.setData(map);
/* 1007 */                 this.requestJson.setMessage("没有找到");
/* 1008 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/* 1010 */                 boHostDevice.setPushSet(this.pushSet);
/* 1011 */                 BoHostDevice update = (BoHostDevice)this.boHostDeviceService.update(boHostDevice);
/* 1012 */                 if (update != null) {
/* 1013 */                   this.requestJson.setData(map);
/* 1014 */                   this.requestJson.setMessage("设置成功");
/* 1015 */                   this.requestJson.setSuccess(true);
/*      */                 } else {
/* 1017 */                   this.requestJson.setData(map);
/* 1018 */                   this.requestJson.setMessage("设置失败");
/* 1019 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/* 1024 */               logger.error(e.getMessage());
/* 1025 */               this.requestJson.setData(map);
/* 1026 */               this.requestJson.setMessage("服务器发生异常");
/* 1027 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 1030 */             System.err.println("AToken时间戳超时了");
/* 1031 */             this.requestJson.setData(map);
/* 1032 */             this.requestJson.setMessage("超时了");
/* 1033 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1036 */           System.err.println("AToken超时了");
/* 1037 */           this.requestJson.setData(map);
/* 1038 */           this.requestJson.setMessage("超时了");
/* 1039 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1043 */         System.err.println("验证不通过");
/* 1044 */         this.requestJson.setData(map);
/* 1045 */         this.requestJson.setMessage("验证不通过");
/* 1046 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1049 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "推送设置");
/* 1050 */       if (ral.booleanValue()) {
/* 1051 */         System.err.println("验证通过");
/* 1052 */         Long accessToken = Long.valueOf(timestamp);
/* 1053 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1054 */         if (boUsers == null) {
/* 1055 */           this.requestJson.setData(map);
/* 1056 */           this.requestJson.setMessage("Invalid_User");
/* 1057 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1059 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 1061 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/* 1062 */             if (boHostDevice == null) {
/* 1063 */               this.requestJson.setData(map);
/* 1064 */               this.requestJson.setMessage("没有找到");
/* 1065 */               this.requestJson.setSuccess(true);
/*      */             } else {
/* 1067 */               boHostDevice.setPushSet(this.pushSet);
/* 1068 */               BoHostDevice update = (BoHostDevice)this.boHostDeviceService.update(boHostDevice);
/* 1069 */               if (update != null) {
/* 1070 */                 this.requestJson.setData(map);
/* 1071 */                 this.requestJson.setMessage("设置成功");
/* 1072 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/* 1074 */                 this.requestJson.setData(map);
/* 1075 */                 this.requestJson.setMessage("设置失败");
/* 1076 */                 this.requestJson.setSuccess(false);
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/* 1081 */             logger.error(e.getMessage());
/* 1082 */             this.requestJson.setData(map);
/* 1083 */             this.requestJson.setMessage("服务器发生异常");
/* 1084 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1087 */           this.requestJson.setData(map);
/* 1088 */           this.requestJson.setMessage("超时了");
/* 1089 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1094 */         System.err.println("验证不通过");
/* 1095 */         this.requestJson.setData(map);
/* 1096 */         this.requestJson.setMessage("验证不通过");
/* 1097 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1100 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="gainParams", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String gainParams()
/*      */     throws InterruptedException
/*      */   {
/* 1111 */     this.requestJson = new RequestJson();
/* 1112 */     Map map = new HashMap();
/* 1113 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 1114 */     String ip = request.getRemoteAddr();
/* 1115 */     String timestamp = request.getHeader("timestamp");
/* 1116 */     String nonce = request.getHeader("nonce");
/* 1117 */     String sign = request.getHeader("sign");
/* 1118 */     String access_token = request.getHeader("access_token");
/* 1119 */     String userCode = request.getHeader("userCode");
/* 1120 */     if (userCode.contains(",")) {
/* 1121 */       String[] userCode2 = userCode.split(",");
/* 1122 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 1123 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 1124 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取电量和推送设置");
/* 1125 */       if (ral.booleanValue()) {
/* 1126 */         System.err.println("验证通过");
/* 1127 */         Long accessToken = Long.valueOf(timestamp);
/* 1128 */         if ((phone == null) || (boUsers == null)) {
/* 1129 */           this.requestJson.setData(map);
/* 1130 */           this.requestJson.setMessage("Invalid_User");
/* 1131 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1133 */         else if (access_token.equals(phone.getAccessToken())) {
/* 1134 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 1136 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/* 1137 */               if (boHostDevice == null) {
/* 1138 */                 this.requestJson.setData(map);
/* 1139 */                 this.requestJson.setMessage("没有找到");
/* 1140 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/* 1142 */                 packNum(userCode2[0].trim().toString());
/* 1143 */                 String SetString = "ZIGBEE_LOCK-CHECK-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress;
/* 1144 */                 byte[] Set = SetString.getBytes();
/* 1145 */                 System.err.println(new String(Set));
/* 1146 */                 StaticUtil.ELECTRIC.put(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress, new String[] { "OK", "100", "0" });
/* 1147 */                 this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1148 */                 Thread.sleep(1500L);
/*      */                 try {
/* 1150 */                   Object[] objects = (Object[])StaticUtil.ELECTRIC.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1151 */                   String electric = "";
/* 1152 */                   if (objects != null) {
/* 1153 */                     BoLockVerdict lock = this.boLockVerdictService.findLock(this.lockAddress);
/* 1154 */                     electric = objects[0].toString();
/* 1155 */                     if (electric.equals("OK")) {
/* 1156 */                       map.put("electric", objects[1].toString());
/* 1157 */                       map.put("whetherLocked", objects[2].toString());
/* 1158 */                       map.put("pushSet", boHostDevice.getPushSet().toString());
/* 1159 */                       map.put("status", lock.getStatus());
/* 1160 */                       this.requestJson.setData(map);
/* 1161 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1163 */                       this.requestJson.setData(map);
/* 1164 */                       this.requestJson.setMessage("网络超时,查询失败");
/* 1165 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/* 1168 */                     this.requestJson.setData(map);
/* 1169 */                     this.requestJson.setMessage("网络超时,通讯失败");
/* 1170 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/* 1173 */                   StaticUtil.ELECTRIC.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/* 1178 */               logger.error(e.getMessage());
/* 1179 */               this.requestJson.setData(map);
/* 1180 */               this.requestJson.setMessage("服务器发生异常");
/* 1181 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 1184 */             System.err.println("AToken时间戳超时了");
/* 1185 */             this.requestJson.setData(map);
/* 1186 */             this.requestJson.setMessage("超时了");
/* 1187 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1190 */           System.err.println("AToken超时了");
/* 1191 */           this.requestJson.setData(map);
/* 1192 */           this.requestJson.setMessage("超时了");
/* 1193 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1197 */         System.err.println("验证不通过");
/* 1198 */         this.requestJson.setData(map);
/* 1199 */         this.requestJson.setMessage("验证不通过");
/* 1200 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1203 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取电量和推送设置");
/* 1204 */       if (ral.booleanValue()) {
/* 1205 */         System.err.println("验证通过");
/* 1206 */         Long accessToken = Long.valueOf(timestamp);
/* 1207 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1208 */         if (boUsers == null) {
/* 1209 */           this.requestJson.setData(map);
/* 1210 */           this.requestJson.setMessage("Invalid_User");
/* 1211 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1213 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 1215 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/* 1216 */             if (boHostDevice == null) {
/* 1217 */               this.requestJson.setData(map);
/* 1218 */               this.requestJson.setMessage("没有找到");
/* 1219 */               this.requestJson.setSuccess(true);
/*      */             } else {
/* 1221 */               packNum(userCode);
/* 1222 */               String SetString = "ZIGBEE_LOCK-CHECK-" + user_num.get(userCode) + "," + this.lockAddress;
/* 1223 */               byte[] Set = SetString.getBytes();
/* 1224 */               System.err.println(new String(Set));
/* 1225 */               StaticUtil.ELECTRIC.put(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress, new String[] { "OK", "100", "0" });
/* 1226 */               this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1227 */               Thread.sleep(1500L);
/*      */               try {
/* 1229 */                 Object[] objects = (Object[])StaticUtil.ELECTRIC.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1230 */                 String electric = "";
/* 1231 */                 if (objects != null) {
/* 1232 */                   BoLockVerdict lock = this.boLockVerdictService.findLock(userCode, this.lockAddress);
/* 1233 */                   electric = objects[0].toString();
/* 1234 */                   if (electric.equals("OK")) {
/* 1235 */                     map.put("electric", objects[1].toString());
/* 1236 */                     map.put("whetherLocked", objects[2].toString());
/* 1237 */                     map.put("pushSet", boHostDevice.getPushSet().toString());
/* 1238 */                     map.put("status", lock.getStatus());
/* 1239 */                     this.requestJson.setData(map);
/* 1240 */                     this.requestJson.setSuccess(true);
/*      */                   } else {
/* 1242 */                     this.requestJson.setData(map);
/* 1243 */                     this.requestJson.setMessage("网络超时,查询失败");
/* 1244 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } else {
/* 1247 */                   this.requestJson.setData(map);
/* 1248 */                   this.requestJson.setMessage("网络超时,通讯失败");
/* 1249 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */               } finally {
/* 1252 */                 StaticUtil.ELECTRIC.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/* 1257 */             logger.error(e.getMessage());
/* 1258 */             this.requestJson.setData(map);
/* 1259 */             this.requestJson.setMessage("服务器发生异常");
/* 1260 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1263 */           this.requestJson.setData(map);
/* 1264 */           this.requestJson.setMessage("超时了");
/* 1265 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1270 */         System.err.println("验证不通过");
/* 1271 */         this.requestJson.setData(map);
/* 1272 */         this.requestJson.setMessage("验证不通过");
/* 1273 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1276 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="longTermPassList", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String longTermPassList()
/*      */     throws InterruptedException
/*      */   {
/* 1287 */     this.requestJson = new RequestJson();
/* 1288 */     Map map = new HashMap();
/* 1289 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 1290 */     String ip = request.getRemoteAddr();
/* 1291 */     String timestamp = request.getHeader("timestamp");
/* 1292 */     String nonce = request.getHeader("nonce");
/* 1293 */     String sign = request.getHeader("sign");
/* 1294 */     String access_token = request.getHeader("access_token");
/* 1295 */     String userCode = request.getHeader("userCode");
/*      */     List voList;
/* 1296 */     if (userCode.contains(",")) {
/* 1297 */       String[] userCode2 = userCode.split(",");
/* 1298 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 1299 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 1300 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "长期密码或临时密码列表");
/* 1301 */       if (ral.booleanValue()) {
/* 1302 */         System.err.println("验证通过");
/* 1303 */         Long accessToken = Long.valueOf(timestamp);
/* 1304 */         if ((phone == null) || (boUsers == null)) {
/* 1305 */           this.requestJson.setData(map);
/* 1306 */           this.requestJson.setMessage("Invalid_User");
/* 1307 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1309 */         else if (access_token.equals(phone.getAccessToken())) {
/* 1310 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 1312 */               List<BoLockPasswordManage> lock = this.boLockPasswordManageServicess.getLock(userCode2[0].trim().toString(), this.lockAddress, Integer.valueOf(this.lockType));
/* 1313 */               if (lock.size() <= 0) {
/* 1314 */                 if (this.lockType.equals("1")) {
/* 1315 */                   this.requestJson.setData(map);
/* 1316 */                   this.requestJson.setMessage("没有长期密码列表");
/* 1317 */                   this.requestJson.setSuccess(false);
/*      */                 } else {
/* 1319 */                   this.requestJson.setData(map);
/* 1320 */                   this.requestJson.setMessage("没有临时密码列表");
/* 1321 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */               } else {
/* 1324 */                 voList = new ArrayList();
/* 1325 */                 for (BoLockPasswordManage boLockPasswordManage : lock) {
/* 1326 */                   Map lockPasswordManageMap = new HashMap();
/* 1327 */                   if (boLockPasswordManage.getLockType().intValue() == 1) {
/* 1328 */                     lockPasswordManageMap.put("lockPwd", boLockPasswordManage.getLockPwd());
/* 1329 */                     lockPasswordManageMap.put("lockOfTimes", "");
/* 1330 */                     lockPasswordManageMap.put("startTime", "");
/* 1331 */                     lockPasswordManageMap.put("endTime", "");
/*      */                   } else {
/* 1333 */                     lockPasswordManageMap.put("lockPwd", boLockPasswordManage.getLockPwd());
/* 1334 */                     lockPasswordManageMap.put("lockOfTimes", boLockPasswordManage.getLockOfTimes().toString());
/* 1335 */                     lockPasswordManageMap.put("startTime", boLockPasswordManage.getStartTime());
/* 1336 */                     lockPasswordManageMap.put("endTime", boLockPasswordManage.getEndTime());
/*      */                   }
/* 1338 */                   voList.add(lockPasswordManageMap);
/*      */                 }
/* 1340 */                 this.requestJson.setData(voList);
/* 1341 */                 this.requestJson.setSuccess(true);
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/* 1345 */               logger.error(e.getMessage());
/* 1346 */               this.requestJson.setData(map);
/* 1347 */               this.requestJson.setMessage("服务器发生异常");
/* 1348 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 1351 */             System.err.println("AToken时间戳超时了");
/* 1352 */             this.requestJson.setData(map);
/* 1353 */             this.requestJson.setMessage("超时了");
/* 1354 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1357 */           System.err.println("AToken超时了");
/* 1358 */           this.requestJson.setData(map);
/* 1359 */           this.requestJson.setMessage("超时了");
/* 1360 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1364 */         System.err.println("验证不通过");
/* 1365 */         this.requestJson.setData(map);
/* 1366 */         this.requestJson.setMessage("验证不通过");
/* 1367 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1370 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "长期密码或临时密码列表");
/* 1371 */       if (ral.booleanValue()) {
/* 1372 */         System.err.println("验证通过");
/* 1373 */         Long accessToken = Long.valueOf(timestamp);
/* 1374 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1375 */         if (boUsers == null) {
/* 1376 */           this.requestJson.setData(map);
/* 1377 */           this.requestJson.setMessage("Invalid_User");
/* 1378 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1380 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 1382 */             List<BoLockPasswordManage> lock = this.boLockPasswordManageServicess.getLock(userCode, this.lockAddress, Integer.valueOf(this.lockType));
/* 1383 */             if (lock.size() <= 0) {
/* 1384 */               if (this.lockType.equals("1")) {
/* 1385 */                 this.requestJson.setData(map);
/* 1386 */                 this.requestJson.setMessage("没有长期密码列表");
/* 1387 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/* 1389 */                 this.requestJson.setData(map);
/* 1390 */                 this.requestJson.setMessage("没有临时密码列表");
/* 1391 */                 this.requestJson.setSuccess(false);
/*      */               }
/*      */             } else {
/* 1394 */               voList = new ArrayList();
/* 1395 */               for (BoLockPasswordManage boLockPasswordManage : lock) {
/* 1396 */                 Map lockPasswordManageMap = new HashMap();
/* 1397 */                 if (boLockPasswordManage.getLockType().intValue() == 1) {
/* 1398 */                   lockPasswordManageMap.put("lockPwd", boLockPasswordManage.getLockPwd());
/* 1399 */                   lockPasswordManageMap.put("lockOfTimes", "");
/* 1400 */                   lockPasswordManageMap.put("startTime", "");
/* 1401 */                   lockPasswordManageMap.put("endTime", "");
/*      */                 } else {
/* 1403 */                   lockPasswordManageMap.put("lockPwd", boLockPasswordManage.getLockPwd());
/* 1404 */                   lockPasswordManageMap.put("lockOfTimes", boLockPasswordManage.getLockOfTimes().toString());
/* 1405 */                   lockPasswordManageMap.put("startTime", boLockPasswordManage.getStartTime());
/* 1406 */                   lockPasswordManageMap.put("endTime", boLockPasswordManage.getEndTime());
/*      */                 }
/* 1408 */                 voList.add(lockPasswordManageMap);
/*      */               }
/* 1410 */               this.requestJson.setData(voList);
/* 1411 */               this.requestJson.setSuccess(true);
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/* 1415 */             logger.error(e.getMessage());
/* 1416 */             this.requestJson.setData(map);
/* 1417 */             this.requestJson.setMessage("服务器发生异常");
/* 1418 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1421 */           this.requestJson.setData(map);
/* 1422 */           this.requestJson.setMessage("超时了");
/* 1423 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1428 */         System.err.println("验证不通过");
/* 1429 */         this.requestJson.setData(map);
/* 1430 */         this.requestJson.setMessage("验证不通过");
/* 1431 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1434 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="remotePasswordSet", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String remotePasswordSet()
/*      */     throws InterruptedException
/*      */   {
/* 1444 */     this.requestJson = new RequestJson();
/* 1445 */     Map map = new HashMap();
/* 1446 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 1447 */     String ip = request.getRemoteAddr();
/* 1448 */     String timestamp = request.getHeader("timestamp");
/* 1449 */     String nonce = request.getHeader("nonce");
/* 1450 */     String sign = request.getHeader("sign");
/* 1451 */     String access_token = request.getHeader("access_token");
/* 1452 */     String userCode = request.getHeader("userCode");
/* 1453 */     if (userCode.contains(",")) {
/* 1454 */       String[] userCode2 = userCode.split(",");
/* 1455 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 1456 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 1457 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "远程密码设置");
/* 1458 */       if (ral.booleanValue()) {
/* 1459 */         System.err.println("验证通过");
/* 1460 */         Long accessToken = Long.valueOf(timestamp);
/* 1461 */         if ((phone == null) || (boUsers == null)) {
/* 1462 */           this.requestJson.setData(map);
/* 1463 */           this.requestJson.setMessage("Invalid_User");
/* 1464 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1466 */         else if (access_token.equals(phone.getAccessToken())) {
/* 1467 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 1469 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/* 1470 */               if (boHostDevice == null) {
/* 1471 */                 this.requestJson.setData(map);
/* 1472 */                 this.requestJson.setMessage("没有找到");
/* 1473 */                 this.requestJson.setSuccess(true);
/*      */               }
/* 1475 */               else if (this.lockType.equals("1")) {
/* 1476 */                 BoLockPasswordManage manage = this.boLockPasswordManageServicess.findBylockAddress(userCode2[0].trim().toString(), this.lockAddress, Integer.valueOf(this.lockType));
/* 1477 */                 if (manage == null) {
/* 1478 */                   packNum(userCode2[0].trim().toString());
/* 1479 */                   String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockType + "," + 0 + "," + this.adminPwd + "," + this.lockPwd + "," + 0;
/* 1480 */                   byte[] Set = SetString.getBytes();
/* 1481 */                   System.err.println(new String(Set));
/* 1482 */                   this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1483 */                   Thread.sleep(1500L);
/* 1484 */                   Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1485 */                   String userCodes = "";
/* 1486 */                   if (object != null) {
/* 1487 */                     userCodes = (String) object;
/* 1488 */                     if (userCodes.equals("OK")) {
/* 1489 */                       BoLockPasswordManage LockPwdManage = new BoLockPasswordManage();
/* 1490 */                       LockPwdManage.setBoDevice(boHostDevice.getBoDevice());
/* 1491 */                       LockPwdManage.setBoUsers(boHostDevice.getBoUsers());
/* 1492 */                       LockPwdManage.setLockOfTimes(Integer.valueOf(255));
/* 1493 */                       LockPwdManage.setLockType(Integer.valueOf(this.lockType));
/* 1494 */                       LockPwdManage.setStartTime("");
/* 1495 */                       LockPwdManage.setEndTime("");
/* 1496 */                       LockPwdManage.setLockAddress(this.lockAddress);
/* 1497 */                       LockPwdManage.setAdminPwd("");
/* 1498 */                       LockPwdManage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1499 */                       this.boLockPasswordManageServicess.save(LockPwdManage);
/* 1500 */                       StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1501 */                       this.requestJson.setData(map);
/* 1502 */                       this.requestJson.setMessage("设置成功");
/* 1503 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1505 */                       this.requestJson.setData(map);
/* 1506 */                       this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1507 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/* 1510 */                     this.requestJson.setData(map);
/* 1511 */                     this.requestJson.setMessage("网络超时,通讯失败");
/* 1512 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 }
/*      */                 else {
/* 1516 */                   packNum(userCode2[0].trim().toString());
/* 1517 */                   String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockType + "," + 0 + "," + this.adminPwd + "," + this.lockPwd + "," + 0;
/* 1518 */                   byte[] Set = SetString.getBytes();
/* 1519 */                   System.err.println(new String(Set));
/* 1520 */                   this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1521 */                   Thread.sleep(1500L);
/* 1522 */                   Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1523 */                   String userCodes = "";
/* 1524 */                   if (object != null) {
/* 1525 */                     userCodes = (String) object;
/* 1526 */                     if (userCodes.equals("OK")) {
/* 1527 */                       manage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1528 */                       this.boLockPasswordManageServicess.update(manage);
/* 1529 */                       StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1530 */                       this.requestJson.setData(map);
/* 1531 */                       this.requestJson.setMessage("设置成功");
/* 1532 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1534 */                       this.requestJson.setData(map);
/* 1535 */                       this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1536 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/* 1539 */                     this.requestJson.setData(map);
/* 1540 */                     this.requestJson.setMessage("网络超时,通讯失败");
/* 1541 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 }
/*      */ 
/*      */               }
/* 1546 */               else if (this.lockType.equals("65535")) {
/* 1547 */                 Date currentTime = new Date();
/* 1548 */                 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 1549 */                 String dateString = formatter.format(currentTime);
/* 1550 */                 BoLockPasswordManage manage = this.boLockPasswordManageServicess.findBylockAddress(userCode2[0].trim().toString(), this.lockAddress, Integer.valueOf(this.lockType));
/* 1551 */                 if (manage == null) {
/* 1552 */                   Date date = formatter.parse(dateString);
/* 1553 */                   long systemTime = date.getTime();
/* 1554 */                   Date dates = formatter.parse(this.startTime);
/* 1555 */                   long appStartTime = dates.getTime();
/* 1556 */                   Date datesEndTime = formatter.parse(this.endTime);
/* 1557 */                   long appEndTime = datesEndTime.getTime();
/* 1558 */                   if (systemTime == appEndTime) {
/* 1559 */                     this.requestJson.setData(map);
/* 1560 */                     this.requestJson.setMessage("结束时间必须大于当前时间");
/* 1561 */                     this.requestJson.setSuccess(false);
/*      */                   } else {
/* 1563 */                     packNum(userCode2[0].trim().toString());
/* 1564 */                     long interval = (appEndTime - currentTime.getTime()) / 1000L;
/* 1565 */                     String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockType + "," + this.lockOfTimes + "," + this.adminPwd + "," + this.lockPwd + "," + interval;
/* 1566 */                     byte[] Set = SetString.getBytes();
/* 1567 */                     System.err.println(new String(Set));
/* 1568 */                     this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1569 */                     Thread.sleep(3000L);
/*      */                     try {
/* 1571 */                       Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1572 */                       String userCodes = "";
/* 1573 */                       if (object != null) {
/* 1574 */                         userCodes = (String) object;
/* 1575 */                         if (userCodes.equals("OK")) {
/* 1576 */                           if (systemTime >= appStartTime) {
/* 1577 */                             BoLockPasswordManage LockPwdManage = new BoLockPasswordManage();
/* 1578 */                             LockPwdManage.setBoDevice(boHostDevice.getBoDevice());
/* 1579 */                             LockPwdManage.setBoUsers(boHostDevice.getBoUsers());
/* 1580 */                             LockPwdManage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1581 */                             LockPwdManage.setLockType(Integer.valueOf(this.lockType));
/* 1582 */                             LockPwdManage.setStartTime(this.startTime);
/* 1583 */                             LockPwdManage.setEndTime(this.endTime);
/* 1584 */                             LockPwdManage.setLockAddress(this.lockAddress);
/* 1585 */                             LockPwdManage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1586 */                             LockPwdManage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1587 */                             this.boLockPasswordManageServicess.save(LockPwdManage);
/* 1588 */                             this.requestJson.setData(map);
/* 1589 */                             this.requestJson.setMessage("设置成功");
/* 1590 */                             this.requestJson.setSuccess(true);
/*      */                           } else {
/* 1592 */                             packNum(userCode2[0].trim().toString());
/* 1593 */                             String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockType + "," + this.adminPwd;
/* 1594 */                             byte[] Delete = DeleteString.getBytes();
/* 1595 */                             System.err.println(new String(Delete));
/* 1596 */                             this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Delete);
/* 1597 */                             Thread.sleep(1500L);
/* 1598 */                             BoLockPasswordManage LockPwdManage = new BoLockPasswordManage();
/* 1599 */                             LockPwdManage.setBoDevice(boHostDevice.getBoDevice());
/* 1600 */                             LockPwdManage.setBoUsers(boHostDevice.getBoUsers());
/* 1601 */                             LockPwdManage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1602 */                             LockPwdManage.setLockType(Integer.valueOf(this.lockType));
/* 1603 */                             LockPwdManage.setStartTime(this.startTime);
/* 1604 */                             LockPwdManage.setEndTime(this.endTime);
/* 1605 */                             LockPwdManage.setLockAddress(this.lockAddress);
/* 1606 */                             LockPwdManage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1607 */                             LockPwdManage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1608 */                             this.boLockPasswordManageServicess.save(LockPwdManage);
/* 1609 */                             this.requestJson.setData(map);
/* 1610 */                             this.requestJson.setMessage("设置成功");
/* 1611 */                             this.requestJson.setSuccess(true);
/*      */                           }
/*      */                         }
/*      */                         else
/*      */                         {
/* 1616 */                           this.requestJson.setData(map);
/* 1617 */                           this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1618 */                           this.requestJson.setSuccess(false);
/*      */                         }
/*      */                       } else {
/* 1621 */                         this.requestJson.setData(map);
/* 1622 */                         this.requestJson.setMessage("网络超时,通讯失败");
/* 1623 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     } finally {
/* 1626 */                       StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                     }
/*      */                   }
/*      */ 
/*      */                 }
/*      */                 else
/*      */                 {
/* 1633 */                   Date date = formatter.parse(dateString);
/* 1634 */                   long systemTime = date.getTime();
/* 1635 */                   Date dates = formatter.parse(this.startTime);
/* 1636 */                   long appStartTime = dates.getTime();
/* 1637 */                   Date datesEndTime = formatter.parse(this.endTime);
/* 1638 */                   long appEndTime = datesEndTime.getTime();
/* 1639 */                   if (systemTime == appEndTime) {
/* 1640 */                     this.requestJson.setData(map);
/* 1641 */                     this.requestJson.setMessage("结束时间必须大于当前时间");
/* 1642 */                     this.requestJson.setSuccess(false);
/*      */                   } else {
/* 1644 */                     packNum(userCode2[0].trim().toString());
/* 1645 */                     long interval = (appEndTime - currentTime.getTime()) / 1000L;
/* 1646 */                     String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockType + "," + this.lockOfTimes + "," + this.adminPwd + "," + this.lockPwd + "," + interval;
/* 1647 */                     byte[] Set = SetString.getBytes();
/* 1648 */                     System.err.println(new String(Set));
/* 1649 */                     this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1650 */                     Thread.sleep(1500L);
/*      */                     try {
/* 1652 */                       Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1653 */                       String userCodes = "";
/* 1654 */                       if (object != null) {
/* 1655 */                         userCodes = (String) object;
/* 1656 */                         if (userCodes.equals("OK")) {
/* 1657 */                           System.err.println("系统当前时间 " + systemTime);
/* 1658 */                           System.err.println("app开始时间 " + appStartTime);
/* 1659 */                           System.err.println("app结束时间 " + appEndTime);
/* 1660 */                           if (systemTime >= appStartTime) {
/* 1661 */                             manage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1662 */                             manage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1663 */                             manage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1664 */                             manage.setStartTime(this.startTime);
/* 1665 */                             manage.setEndTime(this.endTime);
/* 1666 */                             this.boLockPasswordManageServicess.update(manage);
/* 1667 */                             this.requestJson.setData(map);
/* 1668 */                             this.requestJson.setMessage("设置成功");
/* 1669 */                             this.requestJson.setSuccess(true);
/*      */                           } else {
/* 1671 */                             packNum(userCode2[0].trim().toString());
/* 1672 */                             String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockType + "," + this.adminPwd;
/* 1673 */                             byte[] Delete = DeleteString.getBytes();
/* 1674 */                             System.err.println(new String(Delete));
/* 1675 */                             this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Delete);
/* 1676 */                             Thread.sleep(1500L);
/* 1677 */                             manage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1678 */                             manage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1679 */                             manage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1680 */                             manage.setStartTime(this.startTime);
/* 1681 */                             manage.setEndTime(this.endTime);
/* 1682 */                             this.boLockPasswordManageServicess.update(manage);
/* 1683 */                             this.requestJson.setData(map);
/* 1684 */                             this.requestJson.setMessage("设置成功");
/* 1685 */                             this.requestJson.setSuccess(true);
/*      */                           }
/*      */                         } else {
/* 1688 */                           this.requestJson.setData(map);
/* 1689 */                           this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1690 */                           this.requestJson.setSuccess(false);
/*      */                         }
/*      */                       } else {
/* 1693 */                         this.requestJson.setData(map);
/* 1694 */                         this.requestJson.setMessage("网络超时,通讯失败");
/* 1695 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     } finally {
/* 1698 */                       StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                     }
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/* 1706 */               logger.error(e.getMessage());
/* 1707 */               this.requestJson.setData(map);
/* 1708 */               this.requestJson.setMessage("服务器发生异常");
/* 1709 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 1712 */             System.err.println("AToken时间戳超时了");
/* 1713 */             this.requestJson.setData(map);
/* 1714 */             this.requestJson.setMessage("超时了");
/* 1715 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1718 */           System.err.println("AToken超时了");
/* 1719 */           this.requestJson.setData(map);
/* 1720 */           this.requestJson.setMessage("超时了");
/* 1721 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1725 */         System.err.println("验证不通过");
/* 1726 */         this.requestJson.setData(map);
/* 1727 */         this.requestJson.setMessage("验证不通过");
/* 1728 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1731 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "远程密码设置");
/* 1732 */       if (ral.booleanValue()) {
/* 1733 */         System.err.println("验证通过");
/* 1734 */         Long accessToken = Long.valueOf(timestamp);
/* 1735 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1736 */         if (boUsers == null) {
/* 1737 */           this.requestJson.setData(map);
/* 1738 */           this.requestJson.setMessage("Invalid_User");
/* 1739 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1741 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 1743 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/* 1744 */             if (boHostDevice == null) {
/* 1745 */               this.requestJson.setData(map);
/* 1746 */               this.requestJson.setMessage("没有找到");
/* 1747 */               this.requestJson.setSuccess(true);
/*      */             }
/* 1749 */             else if (this.lockType.equals("1")) {
/* 1750 */               BoLockPasswordManage manage = this.boLockPasswordManageServicess.findBylockAddress(userCode, this.lockAddress, Integer.valueOf(this.lockType));
/* 1751 */               if (manage == null) {
/* 1752 */                 packNum(userCode);
/* 1753 */                 String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockType + "," + 0 + "," + this.adminPwd + "," + this.lockPwd + "," + 0;
/* 1754 */                 byte[] Set = SetString.getBytes();
/* 1755 */                 System.err.println(new String(Set));
/* 1756 */                 this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1757 */                 Thread.sleep(1500L);
/*      */                 try {
/* 1759 */                   Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1760 */                   String userCodes = "";
/* 1761 */                   if (object != null) {
/* 1762 */                     userCodes = (String) object;
/* 1763 */                     if (userCodes.equals("OK")) {
/* 1764 */                       BoLockPasswordManage LockPwdManage = new BoLockPasswordManage();
/* 1765 */                       LockPwdManage.setBoDevice(boHostDevice.getBoDevice());
/* 1766 */                       LockPwdManage.setBoUsers(boHostDevice.getBoUsers());
/* 1767 */                       LockPwdManage.setLockOfTimes(Integer.valueOf(255));
/* 1768 */                       LockPwdManage.setLockType(Integer.valueOf(this.lockType));
/* 1769 */                       LockPwdManage.setStartTime("");
/* 1770 */                       LockPwdManage.setEndTime("");
/* 1771 */                       LockPwdManage.setLockAddress(this.lockAddress);
/* 1772 */                       LockPwdManage.setAdminPwd("");
/* 1773 */                       LockPwdManage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1774 */                       this.boLockPasswordManageServicess.save(LockPwdManage);
/* 1775 */                       this.requestJson.setData(map);
/* 1776 */                       this.requestJson.setMessage("设置成功");
/* 1777 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1779 */                       this.requestJson.setData(map);
/* 1780 */                       this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1781 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/* 1784 */                     this.requestJson.setData(map);
/* 1785 */                     this.requestJson.setMessage("网络超时,通讯失败");
/* 1786 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/* 1789 */                   StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                 }
/*      */               } else {
/* 1792 */                 packNum(userCode);
/* 1793 */                 String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockType + "," + 0 + "," + this.adminPwd + "," + this.lockPwd + "," + 0;
/* 1794 */                 byte[] Set = SetString.getBytes();
/* 1795 */                 System.err.println(new String(Set));
/* 1796 */                 this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1797 */                 Thread.sleep(1500L);
/*      */                 try {
/* 1799 */                   Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1800 */                   String userCodes = "";
/* 1801 */                   if (object != null) {
/* 1802 */                     userCodes = (String) object;
/* 1803 */                     if (userCodes.equals("OK")) {
/* 1804 */                       manage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1805 */                       this.boLockPasswordManageServicess.update(manage);
/* 1806 */                       this.requestJson.setData(map);
/* 1807 */                       this.requestJson.setMessage("设置成功");
/* 1808 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1810 */                       this.requestJson.setData(map);
/* 1811 */                       this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1812 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/* 1815 */                     this.requestJson.setData(map);
/* 1816 */                     this.requestJson.setMessage("网络超时,通讯失败");
/* 1817 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/* 1820 */                   StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                 }
/*      */               }
/*      */             }
/* 1824 */             else if (this.lockType.equals("65535")) {
/* 1825 */               Date currentTime = new Date();
/* 1826 */               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 1827 */               String dateString = formatter.format(currentTime);
/* 1828 */               BoLockPasswordManage manage = this.boLockPasswordManageServicess.findBylockAddress(userCode, this.lockAddress, Integer.valueOf(this.lockType));
/* 1829 */               if (manage == null) {
/* 1830 */                 Date date = formatter.parse(dateString);
/* 1831 */                 long systemTime = date.getTime();
/* 1832 */                 Date dates = formatter.parse(this.startTime);
/* 1833 */                 long appStartTime = dates.getTime();
/* 1834 */                 Date datesEndTime = formatter.parse(this.endTime);
/* 1835 */                 long appEndTime = datesEndTime.getTime();
/* 1836 */                 if (systemTime == appEndTime) {
/* 1837 */                   this.requestJson.setData(map);
/* 1838 */                   this.requestJson.setMessage("结束时间必须大于当前时间");
/* 1839 */                   this.requestJson.setSuccess(false);
/*      */                 } else {
/* 1841 */                   packNum(userCode);
/* 1842 */                   long interval = (appEndTime - currentTime.getTime()) / 1000L;
/* 1843 */                   String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockType + "," + this.lockOfTimes + "," + this.adminPwd + "," + this.lockPwd + "," + interval;
/* 1844 */                   byte[] Set = SetString.getBytes();
/* 1845 */                   System.err.println(new String(Set));
/* 1846 */                   this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1847 */                   Thread.sleep(1500L);
/*      */                   try {
/* 1849 */                     Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1850 */                     String userCodes = "";
/* 1851 */                     if (object != null) {
/* 1852 */                       userCodes = (String) object;
/* 1853 */                       if (userCodes.equals("OK")) {
/* 1854 */                         if (systemTime >= appStartTime) {
/* 1855 */                           BoLockPasswordManage LockPwdManage = new BoLockPasswordManage();
/* 1856 */                           LockPwdManage.setBoDevice(boHostDevice.getBoDevice());
/* 1857 */                           LockPwdManage.setBoUsers(boHostDevice.getBoUsers());
/* 1858 */                           LockPwdManage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1859 */                           LockPwdManage.setLockType(Integer.valueOf(this.lockType));
/* 1860 */                           LockPwdManage.setStartTime(this.startTime);
/* 1861 */                           LockPwdManage.setEndTime(this.endTime);
/* 1862 */                           LockPwdManage.setLockAddress(this.lockAddress);
/* 1863 */                           LockPwdManage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1864 */                           LockPwdManage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1865 */                           this.boLockPasswordManageServicess.save(LockPwdManage);
/* 1866 */                           this.requestJson.setData(map);
/* 1867 */                           this.requestJson.setMessage("设置成功");
/* 1868 */                           this.requestJson.setSuccess(true);
/*      */                         } else {
/* 1870 */                           packNum(userCode);
/* 1871 */                           String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockType + "," + this.adminPwd;
/* 1872 */                           byte[] Delete = DeleteString.getBytes();
/* 1873 */                           System.err.println(new String(Delete));
/* 1874 */                           this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Delete);
/* 1875 */                           Thread.sleep(1500L);
/* 1876 */                           BoLockPasswordManage LockPwdManage = new BoLockPasswordManage();
/* 1877 */                           LockPwdManage.setBoDevice(boHostDevice.getBoDevice());
/* 1878 */                           LockPwdManage.setBoUsers(boHostDevice.getBoUsers());
/* 1879 */                           LockPwdManage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1880 */                           LockPwdManage.setLockType(Integer.valueOf(this.lockType));
/* 1881 */                           LockPwdManage.setStartTime(this.startTime);
/* 1882 */                           LockPwdManage.setEndTime(this.endTime);
/* 1883 */                           LockPwdManage.setLockAddress(this.lockAddress);
/* 1884 */                           LockPwdManage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1885 */                           LockPwdManage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1886 */                           this.boLockPasswordManageServicess.save(LockPwdManage);
/* 1887 */                           this.requestJson.setData(map);
/* 1888 */                           this.requestJson.setMessage("设置成功");
/* 1889 */                           this.requestJson.setSuccess(true);
/*      */                         }
/*      */                       }
/*      */                       else
/*      */                       {
/* 1894 */                         this.requestJson.setData(map);
/* 1895 */                         this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1896 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     } else {
/* 1899 */                       this.requestJson.setData(map);
/* 1900 */                       this.requestJson.setMessage("网络超时,通讯失败");
/* 1901 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } finally {
/* 1904 */                     StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                   }
/*      */                 }
/*      */               } else {
/* 1908 */                 Date date = formatter.parse(dateString);
/* 1909 */                 long systemTime = date.getTime();
/* 1910 */                 Date dates = formatter.parse(this.startTime);
/* 1911 */                 long appStartTime = dates.getTime();
/* 1912 */                 Date datesEndTime = formatter.parse(this.endTime);
/* 1913 */                 long appEndTime = datesEndTime.getTime();
/* 1914 */                 if (systemTime == appEndTime) {
/* 1915 */                   this.requestJson.setData(map);
/* 1916 */                   this.requestJson.setMessage("结束时间必须大于当前时间");
/* 1917 */                   this.requestJson.setSuccess(false);
/*      */                 } else {
/* 1919 */                   packNum(userCode);
/* 1920 */                   long interval = (appEndTime - currentTime.getTime()) / 1000L;
/* 1921 */                   String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockType + "," + this.lockOfTimes + "," + this.adminPwd + "," + this.lockPwd + "," + interval;
/* 1922 */                   byte[] Set = SetString.getBytes();
/* 1923 */                   System.err.println(new String(Set));
/* 1924 */                   this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Set);
/* 1925 */                   Thread.sleep(1500L);
/*      */                   try {
/* 1927 */                     Object object = StaticUtil.LOCKSET.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 1928 */                     String userCodes = "";
/* 1929 */                     if (object != null) {
/* 1930 */                       userCodes = (String) object;
/* 1931 */                       if (userCodes.equals("OK")) {
/* 1932 */                         if (systemTime >= appStartTime) {
/* 1933 */                           manage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1934 */                           manage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1935 */                           manage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1936 */                           manage.setStartTime(this.startTime);
/* 1937 */                           manage.setEndTime(this.endTime);
/* 1938 */                           this.boLockPasswordManageServicess.update(manage);
/* 1939 */                           this.requestJson.setData(map);
/* 1940 */                           this.requestJson.setMessage("设置成功");
/* 1941 */                           this.requestJson.setSuccess(true);
/*      */                         } else {
/* 1943 */                           packNum(userCode);
/* 1944 */                           String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockType + "," + this.adminPwd;
/* 1945 */                           byte[] Delete = DeleteString.getBytes();
/* 1946 */                           System.err.println(new String(Delete));
/* 1947 */                           this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Delete);
/* 1948 */                           Thread.sleep(1500L);
/* 1949 */                           manage.setLockOfTimes(Integer.valueOf(this.lockOfTimes));
/* 1950 */                           manage.setAdminPwd(AES.encrypt(this.adminPwd));
/* 1951 */                           manage.setLockPwd(AES.encrypt(this.lockPwd));
/* 1952 */                           manage.setStartTime(this.startTime);
/* 1953 */                           manage.setEndTime(this.endTime);
/* 1954 */                           this.boLockPasswordManageServicess.update(manage);
/* 1955 */                           this.requestJson.setData(map);
/* 1956 */                           this.requestJson.setMessage("设置成功");
/* 1957 */                           this.requestJson.setSuccess(true);
/*      */                         }
/*      */                       } else {
/* 1960 */                         this.requestJson.setData(map);
/* 1961 */                         this.requestJson.setMessage("设置失败,管理员密码错误");
/* 1962 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     } else {
/* 1965 */                       this.requestJson.setData(map);
/* 1966 */                       this.requestJson.setMessage("网络超时,通讯失败");
/* 1967 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } finally {
/* 1970 */                     StaticUtil.LOCKSET.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/* 1978 */             logger.error(e.getMessage());
/* 1979 */             this.requestJson.setData(map);
/* 1980 */             this.requestJson.setMessage("服务器发生异常");
/* 1981 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1984 */           this.requestJson.setData(map);
/* 1985 */           this.requestJson.setMessage("超时了");
/* 1986 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1991 */         System.err.println("验证不通过");
/* 1992 */         this.requestJson.setData(map);
/* 1993 */         this.requestJson.setMessage("验证不通过");
/* 1994 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1997 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="verifyLock", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String verifyLock()
/*      */     throws InterruptedException
/*      */   {
/* 2006 */     this.requestJson = new RequestJson();
/* 2007 */     Map map = new HashMap();
/* 2008 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 2009 */     String ip = request.getRemoteAddr();
/* 2010 */     String timestamp = request.getHeader("timestamp");
/* 2011 */     String nonce = request.getHeader("nonce");
/* 2012 */     String sign = request.getHeader("sign");
/* 2013 */     String access_token = request.getHeader("access_token");
/* 2014 */     String userCode = request.getHeader("userCode");
/* 2015 */     if (userCode.contains(",")) {
/* 2016 */       String[] userCode2 = userCode.split(",");
/* 2017 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 2018 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 2019 */       String ua = request.getHeader("User-Agent");
/* 2020 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "远程控制开锁");
/* 2021 */       if (ral.booleanValue()) {
/* 2022 */         System.err.println("验证通过");
/* 2023 */         Long accessToken = Long.valueOf(timestamp);
/* 2024 */         if ((phone == null) || (boUsers == null)) {
/* 2025 */           this.requestJson.setData(map);
/* 2026 */           this.requestJson.setMessage("Invalid_User");
/* 2027 */           this.requestJson.setSuccess(true);
/*      */         }
/* 2029 */         else if (access_token.equals(phone.getAccessToken())) {
/* 2030 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 2032 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/* 2033 */               if (boHostDevice == null) {
/* 2034 */                 this.requestJson.setData(map);
/* 2035 */                 this.requestJson.setMessage("没有找到");
/* 2036 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/* 2038 */                 BoLockVerdict lockVerdicts = this.boLockVerdictService.findLock(this.lockAddress);
/* 2039 */                 if (lockVerdicts != null)
/* 2040 */                   if (Integer.valueOf(lockVerdicts.getStatus()).intValue() > 4) {
/* 2041 */                     this.requestJson.setData(map);
/* 2042 */                     this.requestJson.setMessage("连续输错5次，APP远程开启锁定10分钟");
/* 2043 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                   else {
/* 2046 */                     this.requestJson.setData(map);
/* 2047 */                     this.requestJson.setMessage("可以触发接口");
/* 2048 */                     this.requestJson.setSuccess(true);
/*      */                   }
/*      */               }
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/* 2054 */               logger.error(e.getMessage());
/* 2055 */               this.requestJson.setData(map);
/* 2056 */               this.requestJson.setMessage("服务器发生异常");
/* 2057 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 2060 */             System.err.println("AToken时间戳超时了");
/* 2061 */             this.requestJson.setData(map);
/* 2062 */             this.requestJson.setMessage("超时了");
/* 2063 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 2066 */           System.err.println("AToken超时了");
/* 2067 */           this.requestJson.setData(map);
/* 2068 */           this.requestJson.setMessage("超时了");
/* 2069 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 2073 */         System.err.println("验证不通过");
/*      */ 
/* 2075 */         this.requestJson.setData(map);
/* 2076 */         this.requestJson.setMessage("验证不通过");
/* 2077 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 2080 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "远程控制开锁");
/* 2081 */       if (ral.booleanValue()) {
/* 2082 */         System.err.println("验证通过");
/* 2083 */         Long accessToken = Long.valueOf(timestamp);
/* 2084 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 2085 */         if (boUsers == null) {
/* 2086 */           this.requestJson.setData(map);
/* 2087 */           this.requestJson.setMessage("Invalid_User");
/* 2088 */           this.requestJson.setSuccess(true);
/*      */         }
/* 2090 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 2092 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/* 2093 */             if (boHostDevice == null) {
/* 2094 */               this.requestJson.setData(map);
/* 2095 */               this.requestJson.setMessage("没有找到");
/* 2096 */               this.requestJson.setSuccess(true);
/*      */             } else {
/* 2098 */               BoLockVerdict lockVerdicts = this.boLockVerdictService.findLock(userCode, this.lockAddress);
/* 2099 */               if (lockVerdicts != null)
/* 2100 */                 if (Integer.valueOf(lockVerdicts.getStatus()).intValue() > 4) {
/* 2101 */                   this.requestJson.setData(map);
/* 2102 */                   this.requestJson.setMessage("连续输错5次，APP远程开启锁定10分钟");
/* 2103 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */                 else {
/* 2106 */                   this.requestJson.setData(map);
/* 2107 */                   this.requestJson.setMessage("可以触发接口");
/* 2108 */                   this.requestJson.setSuccess(true);
/*      */                 }
/*      */             }
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/* 2114 */             logger.error(e.getMessage());
/* 2115 */             this.requestJson.setData(map);
/* 2116 */             this.requestJson.setMessage("服务器发生异常");
/* 2117 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 2120 */           this.requestJson.setData(map);
/* 2121 */           this.requestJson.setMessage("超时了");
/* 2122 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2127 */         System.err.println("验证不通过");
/*      */ 
/* 2129 */         this.requestJson.setData(map);
/* 2130 */         this.requestJson.setMessage("验证不通过");
/* 2131 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 2134 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="lockControl", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String lockControl()
/*      */     throws InterruptedException
/*      */   {
/* 2143 */     this.requestJson = new RequestJson();
/* 2144 */     Map map = new HashMap();
/* 2145 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 2146 */     String ip = request.getRemoteAddr();
/* 2147 */     String timestamp = request.getHeader("timestamp");
/* 2148 */     String nonce = request.getHeader("nonce");
/* 2149 */     String sign = request.getHeader("sign");
/* 2150 */     String access_token = request.getHeader("access_token");
/* 2151 */     String userCode = request.getHeader("userCode");
/* 2152 */     if (userCode.contains(",")) {
/* 2153 */       String[] userCode2 = userCode.split(",");
/* 2154 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 2155 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 2156 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "远程控制开锁");
/* 2157 */       if (ral.booleanValue()) {
/* 2158 */         System.err.println("验证通过");
/* 2159 */         Long accessToken = Long.valueOf(timestamp);
/*      */ 
/* 2161 */         if ((phone == null) || (boUsers == null)) {
/* 2162 */           this.requestJson.setData(map);
/* 2163 */           this.requestJson.setMessage("Invalid_User");
/* 2164 */           this.requestJson.setSuccess(true);
/*      */         }
/* 2166 */         else if (access_token.equals(phone.getAccessToken())) {
/* 2167 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 2169 */               packNum(userCode2[0].trim().toString());
/* 2170 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.lockAddress);
/* 2171 */               if (boHostDevice == null) {
/* 2172 */                 this.requestJson.setData(map);
/* 2173 */                 this.requestJson.setMessage("没有找到");
/* 2174 */                 this.requestJson.setSuccess(true);
/*      */               }
/* 2176 */               else if (boHostDevice.getBoDevice().getStatus().intValue() == 0) {
/* 2177 */                 this.requestJson.setData(map);
/* 2178 */                 this.requestJson.setMessage("主机处于离线状态");
/* 2179 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/* 2181 */                 BoLockVerdict lockVerdicts1 = this.boLockVerdictService.findLock(this.lockAddress);
/* 2182 */                 if (lockVerdicts1 != null) {
/* 2183 */                   String SendString = "ZIGBEE_LOCK-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + this.lockAddress + "," + this.lockPwd;
/* 2184 */                   byte[] Send = SendString.getBytes();
/* 2185 */                   System.err.println(new String(Send));
/*      */ 
/* 2187 */                   this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Send);
/* 2188 */                   Thread.sleep(3000L);
/*      */                   try {
/* 2190 */                     Object object = StaticUtil.LOCKCONTROL.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 2191 */                     String lockControl = "";
/* 2192 */                     if (object != null) {
/* 2193 */                       lockControl = (String) object;
/* 2194 */                       if (lockControl.equals("OK")) {
/* 2195 */                         StaticUtils.locks.put(this.lockAddress, Integer.valueOf(0));
/* 2196 */                         lockVerdicts1.setStatus("0");
/* 2197 */                         this.boLockVerdictService.update(lockVerdicts1);
/* 2198 */                         this.requestJson.setData(map);
/* 2199 */                         this.requestJson.setMessage("操作成功");
/* 2200 */                         this.requestJson.setSuccess(true);
/* 2201 */                       } else if (lockControl.equals("BACKLOCK")) {
/* 2202 */                         this.requestJson.setData(map);
/* 2203 */                         this.requestJson.setMessage("操作失败,门反锁");
/* 2204 */                         this.requestJson.setSuccess(false);
/*      */                       } else {
/* 2206 */                         StaticUtils.lockNum(this.lockAddress);
/* 2207 */                         Integer integer = (Integer)StaticUtils.locks.get(this.lockAddress);
/* 2208 */                         System.err.println("lock.get(lockAddress); " + StaticUtils.locks.get(this.lockAddress));
/* 2209 */                         if (integer.intValue() == 5) {
/* 2210 */                           Long lockTime = Long.valueOf((int)(System.currentTimeMillis() / 1000L));
/* 2211 */                           Long lockTimes = Long.valueOf(180L);
/* 2212 */                           Long unlockTimes = Long.valueOf(lockTime.longValue() + lockTimes.longValue());
/* 2213 */                           lockVerdicts1.setStatus(integer+"");
/* 2214 */                           lockVerdicts1.setLockTimes(lockTime+"");
/* 2215 */                           lockVerdicts1.setUnlockTimes(unlockTimes+"");
/* 2216 */                           this.boLockVerdictService.update(lockVerdicts1);
/*      */                         } else {
/* 2218 */                           lockVerdicts1.setStatus(integer+"");
/* 2219 */                           this.boLockVerdictService.update(lockVerdicts1);
/*      */                         }
/* 2221 */                         this.requestJson.setData(map);
/* 2222 */                         this.requestJson.setMessage("操作失败,密码错误");
/* 2223 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     } else {
/* 2226 */                       this.requestJson.setData(map);
/* 2227 */                       this.requestJson.setMessage("网络超时,通讯失败");
/* 2228 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } finally {
/* 2231 */                     StaticUtil.LOCKCONTROL.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/* 2238 */               logger.error(e.getMessage());
/* 2239 */               this.requestJson.setData(map);
/* 2240 */               this.requestJson.setMessage("服务器发生异常");
/* 2241 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 2244 */             System.err.println("AToken时间戳超时了");
/* 2245 */             this.requestJson.setData(map);
/* 2246 */             this.requestJson.setMessage("超时了");
/* 2247 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 2250 */           System.err.println("AToken超时了");
/* 2251 */           this.requestJson.setData(map);
/* 2252 */           this.requestJson.setMessage("超时了");
/* 2253 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 2257 */         System.err.println("验证不通过");
/* 2258 */         this.requestJson.setData(map);
/* 2259 */         this.requestJson.setMessage("验证不通过");
/* 2260 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 2263 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "远程控制开锁");
/* 2264 */       if (ral.booleanValue()) {
/* 2265 */         System.err.println("验证通过");
/* 2266 */         Long accessToken = Long.valueOf(timestamp);
/* 2267 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 2268 */         if (boUsers == null) {
/* 2269 */           this.requestJson.setData(map);
/* 2270 */           this.requestJson.setMessage("Invalid_User");
/* 2271 */           this.requestJson.setSuccess(true);
/*      */         }
/* 2273 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 2275 */             packNum(userCode);
/* 2276 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.lockAddress);
/* 2277 */             if (boHostDevice == null) {
/* 2278 */               this.requestJson.setData(map);
/* 2279 */               this.requestJson.setMessage("没有找到");
/* 2280 */               this.requestJson.setSuccess(true);
/*      */             } else {
/* 2282 */               BoLockVerdict lockVerdicts1 = this.boLockVerdictService.findLock(userCode, this.lockAddress);
/* 2283 */               if (lockVerdicts1 != null) {
/* 2284 */                 String SendString = "ZIGBEE_LOCK-SEND-" + user_num.get(userCode) + "," + this.lockAddress + "," + this.lockPwd;
/* 2285 */                 byte[] Send = SendString.getBytes();
/* 2286 */                 System.err.println(new String(Send));
/*      */ 
/* 2288 */                 this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), Send);
/* 2289 */                 Thread.sleep(3000L);
/*      */                 try {
/* 2291 */                   Object object = StaticUtil.LOCKCONTROL.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/* 2292 */                   String lockControl = "";
/* 2293 */                   if (object != null) {
/* 2294 */                     lockControl = (String) object;
/* 2295 */                     if (lockControl.equals("OK")) {
/* 2296 */                       StaticUtils.locks.put(this.lockAddress, Integer.valueOf(0));
/* 2297 */                       lockVerdicts1.setStatus("0");
/* 2298 */                       this.boLockVerdictService.update(lockVerdicts1);
/* 2299 */                       this.requestJson.setData(map);
/* 2300 */                       this.requestJson.setMessage("操作成功");
/* 2301 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 2303 */                       StaticUtils.lockNum(this.lockAddress);
/* 2304 */                       Integer integer = (Integer)StaticUtils.locks.get(this.lockAddress);
/* 2305 */                       System.err.println("lock.get(lockAddress); " + StaticUtils.locks.get(this.lockAddress));
/* 2306 */                       if (integer.intValue() == 5) {
/* 2307 */                         Long lockTime = Long.valueOf((int)(System.currentTimeMillis() / 1000L));
/* 2308 */                         Long lockTimes = Long.valueOf(180L);
/* 2309 */                         Long unlockTimes = Long.valueOf(lockTime.longValue() + lockTimes.longValue());
/* 2310 */                         lockVerdicts1.setStatus(integer+"");
/* 2311 */                         lockVerdicts1.setLockTimes(lockTime+"");
/* 2312 */                         lockVerdicts1.setUnlockTimes(unlockTimes+"");
/* 2313 */                         this.boLockVerdictService.update(lockVerdicts1);
/*      */                       } else {
/* 2315 */                         lockVerdicts1.setStatus(integer+"");
/* 2316 */                         this.boLockVerdictService.update(lockVerdicts1);
/*      */                       }
/* 2318 */                       this.requestJson.setData(map);
/* 2319 */                       this.requestJson.setMessage("操作失败,密码错误");
/* 2320 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } else {
/* 2323 */                     this.requestJson.setData(map);
/* 2324 */                     this.requestJson.setMessage("网络超时,通讯失败");
/* 2325 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/* 2328 */                   StaticUtil.LOCKCONTROL.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.lockAddress);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/* 2334 */             logger.error(e.getMessage());
/* 2335 */             this.requestJson.setData(map);
/* 2336 */             this.requestJson.setMessage("服务器发生异常");
/* 2337 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 2340 */           this.requestJson.setData(map);
/* 2341 */           this.requestJson.setMessage("超时了");
/* 2342 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 2347 */         System.err.println("验证不通过");
/* 2348 */         this.requestJson.setData(map);
/* 2349 */         this.requestJson.setMessage("验证不通过");
/* 2350 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 2353 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="test", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String Test()
/*      */   {
/* 2360 */     this.requestJson = new RequestJson();
/* 2361 */     Map map = new HashMap();
/* 2362 */     map.put("jsdhjsa", "1");
/* 2363 */     this.requestJson.setData(map);
/* 2364 */     this.requestJson.setMessage("测试");
/* 2365 */     this.requestJson.setSuccess(true);
/* 2366 */     return "success";
/*      */   }
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/*      */   }
/*      */ 
/*      */   public String getUserCode()
/*      */   {
/* 2376 */     return this.userCode;
/*      */   }
/*      */   public void setUserCode(String userCode) {
/* 2379 */     this.userCode = userCode;
/*      */   }
/*      */ 
/*      */   public String getUserPhone()
/*      */   {
/* 2384 */     return this.userPhone;
/*      */   }
/*      */   public void setUserPhone(String userPhone) {
/* 2387 */     this.userPhone = userPhone;
/*      */   }
/*      */ 
/*      */   public String getUserPwd()
/*      */   {
/* 2392 */     return this.userPwd;
/*      */   }
/*      */   public void setUserPwd(String userPwd) {
/* 2395 */     this.userPwd = userPwd;
/*      */   }
/*      */ 
/*      */   public String getLockAddress()
/*      */   {
/* 2400 */     return this.lockAddress;
/*      */   }
/*      */   public void setLockAddress(String lockAddress) {
/* 2403 */     this.lockAddress = lockAddress;
/*      */   }
/*      */ 
/*      */   public String getDeviceAddress() {
/* 2407 */     return this.deviceAddress;
/*      */   }
/*      */   public void setDeviceAddress(String deviceAddress) {
/* 2410 */     this.deviceAddress = deviceAddress;
/*      */   }
/*      */ 
/*      */   public String getLockPwd() {
/* 2414 */     return this.lockPwd;
/*      */   }
/*      */   public void setLockPwd(String lockPwd) {
/* 2417 */     this.lockPwd = lockPwd;
/*      */   }
/*      */ 
/*      */   public String getLockType() {
/* 2421 */     return this.lockType;
/*      */   }
/*      */   public void setLockType(String lockType) {
/* 2424 */     this.lockType = lockType;
/*      */   }
/*      */ 
/*      */   public String getLockOfTimes() {
/* 2428 */     return this.lockOfTimes;
/*      */   }
/*      */   public void setLockOfTimes(String lockOfTimes) {
/* 2431 */     this.lockOfTimes = lockOfTimes;
/*      */   }
/*      */ 
/*      */   public String getAdminPwd() {
/* 2435 */     return this.adminPwd;
/*      */   }
/*      */   public void setAdminPwd(String adminPwd) {
/* 2438 */     this.adminPwd = adminPwd;
/*      */   }
/*      */ 
/*      */   public String getStartTime() {
/* 2442 */     return this.startTime;
/*      */   }
/*      */   public void setStartTime(String startTime) {
/* 2445 */     this.startTime = startTime;
/*      */   }
/*      */ 
/*      */   public String getEndTime() {
/* 2449 */     return this.endTime;
/*      */   }
/*      */   public void setEndTime(String endTime) {
/* 2452 */     this.endTime = endTime;
/*      */   }
/*      */ 
/*      */   public String getPushSet() {
/* 2456 */     return this.pushSet;
/*      */   }
/*      */   public void setPushSet(String pushSet) {
/* 2459 */     this.pushSet = pushSet;
/*      */   }
/*      */ 
/*      */   public String getFingerprintMembersId() {
/* 2463 */     return this.fingerprintMembersId;
/*      */   }
/*      */   public void setFingerprintMembersId(String fingerprintMembersId) {
/* 2466 */     this.fingerprintMembersId = fingerprintMembersId;
/*      */   }
/*      */ 
/*      */   public String getMembersName() {
/* 2470 */     return this.membersName;
/*      */   }
/*      */   public void setMembersName(String membersName) {
/* 2473 */     this.membersName = membersName;
/*      */   }
/*      */ 
/*      */   public int getPageNum()
/*      */   {
/* 2479 */     return this.pageNum.intValue();
/*      */   }
/*      */   public void setPageNum(Integer pageNum) {
/* 2482 */     this.pageNum = pageNum;
/*      */   }
/*      */   public int getPageSize() {
/* 2485 */     return this.pageSize.intValue();
/*      */   }
/*      */   public void setPageSize(Integer pageSize) {
/* 2488 */     this.pageSize = pageSize;
/*      */   }
/*      */ 
/*      */   public String getOrderField() {
/* 2492 */     return this.orderField;
/*      */   }
/*      */   public void setOrderField(String orderField) {
/* 2495 */     this.orderField = orderField;
/*      */   }
/*      */ 
/*      */   public String getOrderDirection() {
/* 2499 */     return this.orderDirection;
/*      */   }
/*      */   public void setOrderDirection(String orderDirection) {
/* 2502 */     this.orderDirection = orderDirection;
/*      */   }
/*      */ 
/*      */   public File getFileupload() {
/* 2506 */     return this.fileupload;
/*      */   }
/*      */   public void setFileupload(File fileupload) {
/* 2509 */     this.fileupload = fileupload;
/*      */   }
/*      */ 
/*      */   public String getFileuploadFileName() {
/* 2513 */     return this.fileuploadFileName;
/*      */   }
/*      */   public void setFileuploadFileName(String fileuploadFileName) {
/* 2516 */     this.fileuploadFileName = fileuploadFileName;
/*      */   }
/*      */ 
/*      */   public String getFileuploadContentType() {
/* 2520 */     return this.fileuploadContentType;
/*      */   }
/*      */   public void setFileuploadContentType(String fileuploadContentType) {
/* 2523 */     this.fileuploadContentType = fileuploadContentType;
/*      */   }
/*      */ 
/*      */   public RequestJson getRequestJson()
/*      */   {
/* 2547 */     return this.requestJson;
/*      */   }
/*      */ 
/*      */   public void setRequestJson(RequestJson requestJson)
/*      */   {
/* 2552 */     this.requestJson = requestJson;
/*      */   }
/*      */ 
/*      */   public PacketProcessHelper getPacketProcessHelper()
/*      */   {
/* 2558 */     return this.packetProcessHelper;
/*      */   }
/*      */ 
/*      */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper)
/*      */   {
/* 2564 */     this.packetProcessHelper = packetProcessHelper;
/*      */   }
/*      */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.DoorLock_Action
 * JD-Core Version:    0.6.2
 */