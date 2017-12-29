/*      */ package com.smarthome.imcp.action.xing;
/*      */ 
/*      */ import com.smarthome.dock.server.helper.PacketProcessHelper;
/*      */ import com.smarthome.dock.server.util.StaticUtil;
/*      */ import com.smarthome.imcp.action.AbstractAction;
/*      */ import com.smarthome.imcp.common.Md5;
/*      */ import com.smarthome.imcp.controller.RequestJson;
/*      */ import com.smarthome.imcp.dao.model.bo.BoAirBindingPanel;
/*      */ import com.smarthome.imcp.dao.model.bo.BoAirTimingPerform;
/*      */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*      */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*      */ import com.smarthome.imcp.service.bo.BoAirBindingPanelServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoAirTimingPerformServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*      */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Date;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.servlet.http.HttpServletRequest;
/*      */ import net.sf.json.JSONArray;
/*      */ import net.sf.json.JSONObject;
/*      */ import org.apache.struts2.ServletActionContext;
/*      */ import org.apache.struts2.convention.annotation.Action;
/*      */ import org.apache.struts2.convention.annotation.Namespace;
/*      */ import org.apache.struts2.convention.annotation.ParentPackage;
/*      */ import org.apache.struts2.json.JSONException;
/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ 
/*      */ @ParentPackage("auth-check-package")
/*      */ @Namespace("/customization")
/*      */ public class Customization_Action extends AbstractAction
/*      */ {
/*   55 */   private static Logger logger = LoggerFactory.getLogger(Customization_Action.class);
/*   56 */   private static Map<String, Integer> user_num = new HashMap();
/*      */   public static final String lv = "12345";
/* 1750 */   private String userCode = "";
/* 1751 */   private String commandType = "";
/* 1752 */   private String timingInfo = "";
/* 1753 */   private String panelAddress = "";
/* 1754 */   private String deviceAddress = "";
/* 1755 */   private String networkMode = "";
/* 1756 */   private String controlParam = "";
/*      */ 
/*      */   @Autowired
/*      */   private PacketProcessHelper packetProcessHelper;
/*      */ 
/*      */   @Autowired
/*      */   private BoUserssServiceIface<BoUsers, Serializable> boUserServicess;
/*      */ 
/*      */   @Autowired
/*      */   private BoAirTimingPerformServiceIface<BoAirTimingPerform, Serializable> boAirTimingPerformService;
/*      */ 
/*      */   @Autowired
/*      */   private BoAirBindingPanelServiceIface<BoAirBindingPanel, Serializable> boAirBindingPanelService;
/*      */ 
/*      */   @Autowired
/*      */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/* 1834 */   private RequestJson requestJson = new RequestJson();
/*      */ 
/*      */   public Boolean isRal(String timestamp, String nonce, String sign, String access_Token, String userCode, String interfaceName)
/*      */   {
/*   71 */     HttpServletRequest request = ServletActionContext.getRequest();
/*   72 */     String ip = request.getRemoteAddr();
/*   73 */     String str = "";
/*   74 */     Md5 md5 = new Md5();
/*   75 */     boolean isRel = true;
/*      */ 
/*   77 */     str = str + "access_token=";
/*   78 */     str = str + access_Token;
/*   79 */     System.err.println("access_Token " + access_Token);
/*   80 */     str = str + "&nonce=";
/*   81 */     str = str + nonce;
/*   82 */     System.err.println("nonce " + nonce);
/*   83 */     str = str + "&timestamp=";
/*   84 */     str = str + timestamp;
/*   85 */     System.err.println("timestamp " + timestamp);
/*   86 */     str = str + "&userCode=";
/*   87 */     str = str + userCode;
/*   88 */     System.err.println("userCode " + userCode);
/*   89 */     str = str + "12345";
/*   90 */     String service_sign = md5.getMD5ofStr(str).toLowerCase();
/*   91 */     System.err.println("客户端" + sign);
/*   92 */     System.err.println("服务器" + service_sign);
/*   93 */     if (service_sign.equals(sign)) {
/*   94 */       return Boolean.valueOf(isRel);
/*      */     }
/*   96 */     logger.info("ip： " + ip + "   " + "接口昵称：" + interfaceName + " 验签验证不通过");
/*   97 */     return Boolean.valueOf(false);
/*      */   }
/*      */ 
/*      */   public void packNum(String userCode)
/*      */   {
/*  106 */     if (user_num.get(userCode) == null)
/*  107 */       user_num.put(userCode, Integer.valueOf(0));
/*      */     else
/*  109 */       user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*      */   }
/*      */ 
/*      */   @SuppressWarnings("unused")
@Action(value="panelCancelBinding", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String panelCancelBinding()
/*      */   {
/*  120 */     this.requestJson = new RequestJson();
/*  121 */     Map map = new HashMap();
/*  122 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  123 */     String ip = request.getRemoteAddr();
/*  124 */     String timestamp = request.getHeader("timestamp");
/*  125 */     String nonce = request.getHeader("nonce");
/*  126 */     String sign = request.getHeader("sign");
/*  127 */     String access_token = request.getHeader("access_token");
/*  128 */     String userCode = request.getHeader("userCode");
/*  129 */     if (userCode.contains(",")) {
/*  130 */       String[] userCode2 = userCode.split(",");
/*  131 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  132 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  133 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "取消绑定面板");
				
/*  134 */       if (ral.booleanValue()) {
/*  135 */         System.err.println("验证通过");
/*  136 */         Long accessToken = Long.valueOf(timestamp);
/*      */ 
/*  138 */         if ((phone == null) || (boUsers == null)) {
/*  139 */           this.requestJson.setData(map);
/*  140 */           this.requestJson.setMessage("没有找到该编号");
/*  141 */           this.requestJson.setSuccess(true);
/*      */         }
/*  143 */         else if (access_token.equals(phone.getAccessToken())) {
/*  144 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  146 */               BoAirBindingPanel boAirBindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  147 */               if (boAirBindingPanel == null) 
//							break label920;
							return "boAirBindingPanel == null";
/*  148 */               boAirBindingPanel.setDeviceAddress("");
/*  149 */               boAirBindingPanel.setPatternType("1");
/*  150 */               boAirBindingPanel.setLogoWhetherIsBound("N");
/*  151 */               BoAirBindingPanel update = (BoAirBindingPanel)this.boAirBindingPanelService.update(boAirBindingPanel);
/*  152 */               if (update != null) {
/*  153 */                 this.requestJson.setData(map);
/*  154 */                 this.requestJson.setMessage("取消绑定成功");
/*  155 */                 this.requestJson.setSuccess(true);
/*      */               } else {
/*  157 */                 this.requestJson.setData(map);
/*  158 */                 this.requestJson.setMessage("取消绑定失败");
/*  159 */                 this.requestJson.setSuccess(true);
/*      */               }
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/*  164 */               logger.info("error_" + e.getMessage());
/*  165 */               this.requestJson.setData(map);
/*  166 */               this.requestJson.setMessage("服务器发生异常");
/*  167 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/*  170 */             System.err.println("AToken时间戳超时了");
/*  171 */             this.requestJson.setData(map);
/*  172 */             this.requestJson.setMessage("超时了");
/*  173 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  176 */           System.err.println("AToken超时了");
/*  177 */           this.requestJson.setData(map);
/*  178 */           this.requestJson.setMessage("超时了");
/*  179 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  183 */         System.err.println("验证不通过");
/*  184 */         this.requestJson.setData(map);
/*  185 */         this.requestJson.setMessage("验证不通过");
/*  186 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  189 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "取消绑定面板");
/*  190 */       if (ral.booleanValue()) {
/*  191 */         System.err.println("验证通过");
/*  192 */         Long accessToken = Long.valueOf(timestamp);
/*  193 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  194 */         if (boUsers == null) {
/*  195 */           this.requestJson.setData(map);
/*  196 */           this.requestJson.setMessage("没有找到该编号");
/*  197 */           this.requestJson.setSuccess(true);
/*      */         }
/*  199 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  201 */             BoAirBindingPanel boAirBindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/*  202 */             if (boAirBindingPanel == null) 
//							break label920;
							return "boAirBindPanel == null";
/*  203 */             boAirBindingPanel.setDeviceAddress("");
/*  204 */             boAirBindingPanel.setPatternType("1");
/*  205 */             boAirBindingPanel.setLogoWhetherIsBound("N");
/*  206 */             BoAirBindingPanel update = (BoAirBindingPanel)this.boAirBindingPanelService.update(boAirBindingPanel);
/*  207 */             if (update != null) {
/*  208 */               this.requestJson.setData(map);
/*  209 */               this.requestJson.setMessage("取消绑定成功");
/*  210 */               this.requestJson.setSuccess(true);
/*      */             } else {
/*  212 */               this.requestJson.setData(map);
/*  213 */               this.requestJson.setMessage("取消绑定失败");
/*  214 */               this.requestJson.setSuccess(true);
/*      */             }
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/*  219 */             logger.info("error_" + e.getMessage());
/*  220 */             this.requestJson.setData(map);
/*  221 */             this.requestJson.setMessage("服务器发生异常");
/*  222 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  225 */           this.requestJson.setData(map);
/*  226 */           this.requestJson.setMessage("超时了");
/*  227 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  232 */         System.err.println("验证不通过");
/*  233 */         this.requestJson.setData(map);
/*  234 */         this.requestJson.setMessage("验证不通过");
/*  235 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  238 */     label920: return "success";
/*      */   }
/*      */ 
/*      */   @SuppressWarnings("unused")
@Action(value="panelBinding", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String panelBinding()
/*      */   {
/*  249 */     this.requestJson = new RequestJson();
/*  250 */     Map map = new HashMap();
/*  251 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  252 */     String ip = request.getRemoteAddr();
/*  253 */     String timestamp = request.getHeader("timestamp");
/*  254 */     String nonce = request.getHeader("nonce");
/*  255 */     String sign = request.getHeader("sign");
/*  256 */     String access_token = request.getHeader("access_token");
/*  257 */     String userCode = request.getHeader("userCode");
/*  258 */     if (userCode.contains(",")) {
/*  259 */       String[] userCode2 = userCode.split(",");
/*  260 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  261 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  262 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "绑定面板");
/*  263 */       if (ral.booleanValue()) {
/*  264 */         System.err.println("验证通过");
/*  265 */         Long accessToken = Long.valueOf(timestamp);
/*      */ 
/*  267 */         if ((phone == null) || (boUsers == null)) {
/*  268 */           this.requestJson.setData(map);
/*  269 */           this.requestJson.setMessage("没有找到该编号");
/*  270 */           this.requestJson.setSuccess(true);
/*      */         }
/*  272 */         else if (access_token.equals(phone.getAccessToken())) {
/*  273 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  275 */               BoAirBindingPanel boAirBindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  276 */               if (boAirBindingPanel != null) {
/*  277 */                 boAirBindingPanel.setDeviceAddress("");
/*  278 */                 boAirBindingPanel.setLogoWhetherIsBound("N");
/*  279 */                 this.boAirBindingPanelService.update(boAirBindingPanel);
/*      */                 try {
/*  281 */                   Thread.sleep(500L);
/*  282 */                   BoAirBindingPanel airBindingPanel = this.boAirBindingPanelService.findBydeviceAddress(userCode2[0].trim().toString(), this.panelAddress);
/*  283 */                   if (airBindingPanel == null) 
//								break label1326;
								return "airBindingPanel == null";
/*  284 */                   airBindingPanel.setDeviceAddress(this.deviceAddress);
/*  285 */                   airBindingPanel.setPatternType(this.networkMode);
/*  286 */                   airBindingPanel.setLogoWhetherIsBound("Y");
/*  287 */                   BoAirBindingPanel update = (BoAirBindingPanel)this.boAirBindingPanelService.update(airBindingPanel);
/*  288 */                   if (update != null) {
/*  289 */                     this.requestJson.setData(map);
/*  290 */                     this.requestJson.setMessage("绑定成功");
/*  291 */                     this.requestJson.setSuccess(true);
/*      */                   } else {
/*  293 */                     this.requestJson.setData(map);
/*  294 */                     this.requestJson.setMessage("绑定失败");
/*  295 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 }
/*      */                 catch (InterruptedException e)
/*      */                 {
/*  300 */                   e.printStackTrace();
/*      */                 }
/*      */               } else {
/*  303 */                 BoAirBindingPanel airBindingPanel = this.boAirBindingPanelService.findBydeviceAddress(userCode2[0].trim().toString(), this.panelAddress);
/*  304 */                 if (airBindingPanel != null) {
/*  305 */                   airBindingPanel.setDeviceAddress(this.deviceAddress);
/*  306 */                   airBindingPanel.setPatternType(this.networkMode);
/*  307 */                   airBindingPanel.setLogoWhetherIsBound("Y");
/*  308 */                   BoAirBindingPanel update = (BoAirBindingPanel)this.boAirBindingPanelService.update(airBindingPanel);
/*  309 */                   if (update != null) {
/*  310 */                     this.requestJson.setData(map);
/*  311 */                     this.requestJson.setMessage("绑定成功");
/*  312 */                     this.requestJson.setSuccess(true);
/*      */                   } else {
/*  314 */                     this.requestJson.setData(map);
/*  315 */                     this.requestJson.setMessage("绑定失败");
/*  316 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 }
/*      */               }
/*      */             }
/*      */             catch (Exception e) {
/*  322 */               logger.info("error_" + e.getMessage());
/*  323 */               this.requestJson.setData(map);
/*  324 */               this.requestJson.setMessage("服务器发生异常");
/*  325 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/*  328 */             System.err.println("AToken时间戳超时了");
/*  329 */             this.requestJson.setData(map);
/*  330 */             this.requestJson.setMessage("超时了");
/*  331 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  334 */           System.err.println("AToken超时了");
/*  335 */           this.requestJson.setData(map);
/*  336 */           this.requestJson.setMessage("超时了");
/*  337 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  341 */         System.err.println("验证不通过");
/*  342 */         this.requestJson.setData(map);
/*  343 */         this.requestJson.setMessage("验证不通过");
/*  344 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  347 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "绑定面板");
/*  348 */       if (ral.booleanValue()) {
/*  349 */         System.err.println("验证通过");
/*  350 */         Long accessToken = Long.valueOf(timestamp);
/*  351 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  352 */         if (boUsers == null) {
/*  353 */           this.requestJson.setData(map);
/*  354 */           this.requestJson.setMessage("没有找到该编号");
/*  355 */           this.requestJson.setSuccess(true);
/*      */         }
/*  357 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  359 */             BoAirBindingPanel boAirBindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/*  360 */             if (boAirBindingPanel != null) {
/*  361 */               boAirBindingPanel.setDeviceAddress("");
/*  362 */               boAirBindingPanel.setLogoWhetherIsBound("N");
/*  363 */               this.boAirBindingPanelService.update(boAirBindingPanel);
/*      */               try {
/*  365 */                 Thread.sleep(500L);
/*  366 */                 BoAirBindingPanel airBindingPanel = this.boAirBindingPanelService.findBydeviceAddress(userCode, this.panelAddress);
/*  367 */                 if (airBindingPanel == null) 
//								break label1326;
								return "airBindingPanel == null";
/*  368 */                 airBindingPanel.setDeviceAddress(this.deviceAddress);
/*  369 */                 airBindingPanel.setPatternType(this.networkMode);
/*  370 */                 airBindingPanel.setLogoWhetherIsBound("Y");
/*  371 */                 BoAirBindingPanel update = (BoAirBindingPanel)this.boAirBindingPanelService.update(airBindingPanel);
/*  372 */                 if (update != null) {
/*  373 */                   this.requestJson.setData(map);
/*  374 */                   this.requestJson.setMessage("绑定成功");
/*  375 */                   this.requestJson.setSuccess(true);
/*      */                 } else {
/*  377 */                   this.requestJson.setData(map);
/*  378 */                   this.requestJson.setMessage("绑定失败");
/*  379 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */               }
/*      */               catch (InterruptedException e)
/*      */               {
/*  384 */                 e.printStackTrace();
/*      */               }
/*      */             } else {
/*  387 */               BoAirBindingPanel airBindingPanel = this.boAirBindingPanelService.findBydeviceAddress(userCode, this.panelAddress);
/*  388 */               if (airBindingPanel != null) {
/*  389 */                 airBindingPanel.setDeviceAddress(this.deviceAddress);
/*  390 */                 airBindingPanel.setPatternType(this.networkMode);
/*  391 */                 airBindingPanel.setLogoWhetherIsBound("Y");
/*  392 */                 BoAirBindingPanel update = (BoAirBindingPanel)this.boAirBindingPanelService.update(airBindingPanel);
/*  393 */                 if (update != null) {
/*  394 */                   this.requestJson.setData(map);
/*  395 */                   this.requestJson.setMessage("绑定成功");
/*  396 */                   this.requestJson.setSuccess(true);
/*      */                 } else {
/*  398 */                   this.requestJson.setData(map);
/*  399 */                   this.requestJson.setMessage("绑定失败");
/*  400 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */               }
/*      */             }
/*      */           }
/*      */           catch (Exception e) {
/*  406 */             logger.info("error_" + e.getMessage());
/*  407 */             this.requestJson.setData(map);
/*  408 */             this.requestJson.setMessage("服务器发生异常");
/*  409 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  412 */           this.requestJson.setData(map);
/*  413 */           this.requestJson.setMessage("超时了");
/*  414 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  419 */         System.err.println("验证不通过");
/*  420 */         this.requestJson.setData(map);
/*  421 */         this.requestJson.setMessage("验证不通过");
/*  422 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
///*  425 */     label1326: return "success";
				return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="gainBindingPanelInfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String gainBindingPanelInfo()
/*      */   {
/*  436 */     this.requestJson = new RequestJson();
/*  437 */     Map map = new HashMap();
/*  438 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  439 */     String ip = request.getRemoteAddr();
/*  440 */     String timestamp = request.getHeader("timestamp");
/*  441 */     String nonce = request.getHeader("nonce");
/*  442 */     String sign = request.getHeader("sign");
/*  443 */     String access_token = request.getHeader("access_token");
/*  444 */     String userCode = request.getHeader("userCode");
/*      */     List voList;
///*      */     Object lists;
/*  445 */     if (userCode.contains(",")) {
/*  446 */       String[] userCode2 = userCode.split(",");
/*  447 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  448 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  449 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取绑定面板列表信息");
/*  450 */       if (ral.booleanValue()) {
/*  451 */         System.err.println("验证通过");
/*  452 */         Long accessToken = Long.valueOf(timestamp);
/*      */ 
/*  454 */         if ((phone == null) || (boUsers == null)) {
/*  455 */           this.requestJson.setData(map);
/*  456 */           this.requestJson.setMessage("没有找到该编号");
/*  457 */           this.requestJson.setSuccess(true);
/*      */         }
/*  459 */         else if (access_token.equals(phone.getAccessToken())) {
/*  460 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  462 */               List<BoAirBindingPanel> list = this.boAirBindingPanelService.getPanelAll(userCode2[0].trim().toString(), this.deviceAddress);
/*  463 */               voList = new ArrayList();
/*  464 */               for (BoAirBindingPanel boAirBindingPanel : list) {
/*  465 */                 Map maps = new HashMap();
/*  466 */                 maps.put("panelAddress", boAirBindingPanel.getPanelAddress().toString());
/*  467 */                 maps.put("deviceAddress", boAirBindingPanel.getDeviceAddress().toString());
/*  468 */                 maps.put("patternType", boAirBindingPanel.getPatternType().toString());
/*  469 */                 voList.add(maps);
/*      */               }
/*      */               String s;
///*      */               String s;
/*  472 */               if (!this.deviceAddress.equals(""))
/*  473 */                 s = "";
/*      */               else {
/*  475 */                 s = "";
/*      */               }
/*  477 */               List<BoAirBindingPanel> lists = this.boAirBindingPanelService.getPanelAll(userCode2[0].trim().toString(), s);
/*  478 */               for (BoAirBindingPanel boAirBindingPanel : lists)
/*      */               {
/*      */                 String Address;
///*      */                 String Address;
/*  480 */                 if (boAirBindingPanel.getDeviceAddress().toString().equals(""))
/*  481 */                   Address = "notIsBinding";
/*      */                 else
/*  483 */                   Address = "notIsBinding";
/*  484 */                 Map maps = new HashMap();
/*  485 */                 maps.put("panelAddress", boAirBindingPanel.getPanelAddress().toString());
/*      */ 
/*  487 */                 maps.put("deviceAddress", Address);
/*  488 */                 maps.put("patternType", boAirBindingPanel.getPatternType().toString());
/*  489 */                 voList.add(maps);
/*      */               }
/*      */ 
/*  492 */               this.requestJson.setData(voList);
/*  493 */               this.requestJson.setSuccess(true);
/*      */             }
/*      */             catch (Exception e) {
/*  496 */               logger.info("error_" + e.getMessage());
/*  497 */               this.requestJson.setData(map);
/*  498 */               this.requestJson.setMessage("服务器发生异常");
/*  499 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/*  502 */             System.err.println("AToken时间戳超时了");
/*  503 */             this.requestJson.setData(map);
/*  504 */             this.requestJson.setMessage("超时了");
/*  505 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  508 */           System.err.println("AToken超时了");
/*  509 */           this.requestJson.setData(map);
/*  510 */           this.requestJson.setMessage("超时了");
/*  511 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  515 */         System.err.println("验证不通过");
/*      */ 
/*  517 */         this.requestJson.setData(map);
/*  518 */         this.requestJson.setMessage("验证不通过");
/*  519 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  522 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取绑定面板列表信息");
/*  523 */       if (ral.booleanValue()) {
/*  524 */         System.err.println("验证通过");
/*  525 */         Long accessToken = Long.valueOf(timestamp);
/*  526 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  527 */         if (boUsers == null) {
/*  528 */           this.requestJson.setData(map);
/*  529 */           this.requestJson.setMessage("没有找到该编号");
/*  530 */           this.requestJson.setSuccess(true);
/*      */         }
/*  532 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  534 */             List<BoAirBindingPanel> list = this.boAirBindingPanelService.getPanelAll(userCode, this.deviceAddress);
/*  535 */             voList = new ArrayList();
/*  536 */             for (BoAirBindingPanel boAirBindingPanel : list) {
/*  537 */               Map maps = new HashMap();
/*  538 */               maps.put("panelAddress", boAirBindingPanel.getPanelAddress().toString());
/*  539 */               maps.put("deviceAddress", boAirBindingPanel.getDeviceAddress().toString());
/*  540 */               maps.put("patternType", boAirBindingPanel.getPatternType().toString());
/*  541 */               voList.add(maps);
/*      */             }
/*      */             String s;
///*      */             String s;
/*  544 */             if (!this.deviceAddress.equals(""))
/*  545 */               s = "";
/*      */             else {
/*  547 */               s = "";
/*      */             }
/*  549 */             List<BoAirBindingPanel> lists = this.boAirBindingPanelService.getPanelAll(userCode, s);
/*  550 */             for (lists = (List<BoAirBindingPanel>) ((JSONArray) lists).iterator(); ((Iterator)lists).hasNext(); ) { BoAirBindingPanel boAirBindingPanel = (BoAirBindingPanel)((Iterator)lists).next();
/*      */               String Address;
///*      */               String Address;
/*  552 */               if (boAirBindingPanel.getDeviceAddress().toString().equals(""))
/*  553 */                 Address = "notIsBinding";
/*      */               else
/*  555 */                 Address = "notIsBinding";
/*  556 */               Object maps = new HashMap();
/*  557 */               ((Map)maps).put("panelAddress", boAirBindingPanel.getPanelAddress().toString());
/*      */ 
/*  559 */               ((Map)maps).put("deviceAddress", Address);
/*  560 */               ((Map)maps).put("patternType", boAirBindingPanel.getPatternType().toString());
/*  561 */               voList.add(maps);
/*      */             }
/*      */ 
/*  564 */             this.requestJson.setData(voList);
/*  565 */             this.requestJson.setSuccess(true);
/*      */           }
/*      */           catch (Exception e) {
/*  568 */             logger.info("error_" + e.getMessage());
/*  569 */             this.requestJson.setData(map);
/*  570 */             this.requestJson.setMessage("服务器发生异常");
/*  571 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  574 */           this.requestJson.setData(map);
/*  575 */           this.requestJson.setMessage("超时了");
/*  576 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  581 */         System.err.println("验证不通过");
/*      */ 
/*  583 */         this.requestJson.setData(map);
/*  584 */         this.requestJson.setMessage("验证不通过");
/*  585 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  588 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="gainTimingListInfo", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String gainTimingListInfo()
/*      */     throws JSONException
/*      */   {
/*  600 */     this.requestJson = new RequestJson();
/*  601 */     Map map = new HashMap();
/*  602 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  603 */     String ip = request.getRemoteAddr();
/*  604 */     String timestamp = request.getHeader("timestamp");
/*  605 */     String nonce = request.getHeader("nonce");
/*  606 */     String sign = request.getHeader("sign");
/*  607 */     String access_token = request.getHeader("access_token");
/*  608 */     String userCode = request.getHeader("userCode");
/*      */     List voList;
/*  609 */     if (userCode.contains(",")) {
/*  610 */       String[] userCode2 = userCode.split(",");
/*  611 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  612 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  613 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取所有定时器列表信息");
/*  614 */       if (ral.booleanValue()) {
/*  615 */         System.err.println("验证通过");
/*  616 */         Long accessToken = Long.valueOf(timestamp);
/*  617 */         if ((phone == null) || (boUsers == null)) {
/*  618 */           this.requestJson.setData(map);
/*  619 */           this.requestJson.setMessage("没有找到该编号");
/*  620 */           this.requestJson.setSuccess(true);
/*      */         }
/*  622 */         else if (access_token.equals(phone.getAccessToken())) {
/*  623 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/*  625 */               BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  626 */               if (hostDevice == null) {
/*  627 */                 this.requestJson.setData(map);
/*  628 */                 this.requestJson.setMessage("没有设备");
/*  629 */                 this.requestJson.setSuccess(false);
/*      */               } else {
/*  631 */                 Map tims = new HashMap();
/*  632 */                 List<BoAirTimingPerform> allByUser = this.boAirTimingPerformService.getAllByUser(userCode2[0].trim().toString(), this.deviceAddress);
/*  633 */                 if (allByUser.size() <= 0)
/*      */                 {
/*  636 */                   BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*      */                   String networkModes;
/*      */                   String panelAddress;
///*      */                   String networkModes;
/*  637 */                   if (bindingPanel == null) {
/*  638 */                     panelAddress = "0000";
/*  639 */                     networkModes = "1";
/*      */                   }
/*      */                   else {
/*  642 */                     panelAddress = bindingPanel.getPanelAddress();
/*  643 */                     networkModes = bindingPanel.getPatternType();
/*      */                   }
/*  645 */                   packNum(userCode2[0].trim().toString());
/*  646 */                   System.err.println("userCode1 " + userCode2[0].trim().toString());
/*  647 */                   SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
/*  648 */                   String date = sDate.format(new Date());
/*  649 */                   SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
/*  650 */                   String times = sTime.format(new Date());
/*  651 */                   String[] time = date.split("-");
/*  652 */                   String[] time2 = times.split(":");
/*      */ 
/*  660 */                   tims.put(panelAddress + "_" + this.deviceAddress, new String[] { time[0], time[1], time[2], time2[0], time2[1], time2[2] });
/*  661 */                   String[] strings = (String[])tims.get(panelAddress + "_" + this.deviceAddress);
/*  662 */                   if (strings != null) {
/*  663 */                     System.err.println(strings[0] + strings[1] + strings[2] + strings[3] + strings[4] + strings[5]);
/*      */                   }
/*  665 */                   String strss = "AIR-SET_TIME-" + user_num.get(userCode2[0].trim().toString()) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + time[0] + "," + time[1] + "," + time[2] + "," + time2[0] + "," + time2[1] + "," + time2[2];
/*  666 */                   byte[] bss = strss.getBytes();
/*  667 */                   System.err.println(new String(bss));
/*  668 */                   this.packetProcessHelper.processSendDDatas(hostDevice.getBoDevice().getDeviceCode(), bss);
/*  669 */                   this.requestJson.setData(map);
/*  670 */                   this.requestJson.setMessage("没有定时器");
/*  671 */                   this.requestJson.setSuccess(false);
/*      */                 }
/*      */                 else
/*      */                 {
/*  675 */                   BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*      */                   String networkModes;
/*      */                   String panelAddress;
///*      */                   String networkModes;
/*  676 */                   if (bindingPanel == null) {
///*  677 */                     String panelAddress = "0000";
							   panelAddress = "0000";
/*  678 */                     networkModes = "1";
/*      */                   }
/*      */                   else {
/*  681 */                     panelAddress = bindingPanel.getPanelAddress();
/*  682 */                     networkModes = bindingPanel.getPatternType();
/*      */                   }
/*  684 */                   packNum(userCode2[0].trim().toString());
/*  685 */                   System.err.println("userCode2 " + userCode2[0].trim().toString());
/*  686 */                   SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
/*  687 */                   String date = sDate.format(new Date());
/*  688 */                   SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
/*  689 */                   String times = sTime.format(new Date());
/*  690 */                   String[] time = date.split("-");
/*  691 */                   String[] time2 = times.split(":");
/*      */ 
/*  699 */                   tims.put(panelAddress + "_" + this.deviceAddress, new String[] { time[0], time[1], time[2], time2[0], time2[1], time2[2] });
/*  700 */                   String[] strings = (String[])tims.get(panelAddress + "_" + this.deviceAddress);
/*  701 */                   if (strings != null) {
/*  702 */                     System.err.println(strings[0] + strings[1] + strings[2] + strings[3] + strings[4] + strings[5]);
/*      */                   }
/*  704 */                   String strss = "AIR-SET_TIME-" + user_num.get(userCode2[0].trim().toString()) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + time[0] + "," + time[1] + "," + time[2] + "," + time2[0] + "," + time2[1] + "," + time2[2];
/*  705 */                   byte[] bss = strss.getBytes();
/*  706 */                   System.err.println(new String(bss));
/*  707 */                   this.packetProcessHelper.processSendDDatas(hostDevice.getBoDevice().getDeviceCode(), bss);
/*  708 */                   voList = new ArrayList();
/*  709 */                   for (BoAirTimingPerform BoAirTimingPerform : allByUser) {
/*  710 */                     Map maps = new HashMap();
/*  711 */                     maps.put("start", BoAirTimingPerform.getStart().toString());
/*  712 */                     maps.put("time", BoAirTimingPerform.getTime().toString());
/*  713 */                     maps.put("model", BoAirTimingPerform.getModel().toString());
/*  714 */                     maps.put("temp", BoAirTimingPerform.getTemp().toString());
/*  715 */                     maps.put("fan", BoAirTimingPerform.getFan().toString());
/*  716 */                     maps.put("control", BoAirTimingPerform.getContrl().toString());
/*      */ 
/*  718 */                     String[] arrayStr = new String[0];
/*  719 */                     arrayStr = BoAirTimingPerform.getDays().split(",");
/*  720 */                     maps.put("days", arrayStr);
/*  721 */                     voList.add(maps);
/*      */                   }
/*  723 */                   this.requestJson.setData(voList);
/*  724 */                   this.requestJson.setSuccess(true);
/*      */                 }
/*      */               }
/*      */ 
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/*  731 */               logger.info("error_" + e.getMessage());
/*  732 */               this.requestJson.setData(map);
/*  733 */               this.requestJson.setMessage("服务器发生异常");
/*  734 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/*  737 */             System.err.println("AToken时间戳超时了");
/*  738 */             this.requestJson.setData(map);
/*  739 */             this.requestJson.setMessage("超时了");
/*  740 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  743 */           System.err.println("AToken超时了");
/*  744 */           this.requestJson.setData(map);
/*  745 */           this.requestJson.setMessage("超时了");
/*  746 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/*  750 */         System.err.println("验证不通过");
/*      */ 
/*  752 */         this.requestJson.setData(map);
/*  753 */         this.requestJson.setMessage("验证不通过");
/*  754 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/*  757 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取所有定时器列表信息");
/*  758 */       if (ral.booleanValue()) {
/*  759 */         System.err.println("验证通过");
/*  760 */         Long accessToken = Long.valueOf(timestamp);
/*  761 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/*  762 */         if (boUsers == null) {
/*  763 */           this.requestJson.setData(map);
/*  764 */           this.requestJson.setMessage("没有找到该编号");
/*  765 */           this.requestJson.setSuccess(true);
/*      */         }
/*  767 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/*  769 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/*  770 */             if (hostDevice == null) {
/*  771 */               this.requestJson.setData(map);
/*  772 */               this.requestJson.setMessage("没有设备");
/*  773 */               this.requestJson.setSuccess(false);
/*      */             } else {
/*  775 */               Map tims = new HashMap();
/*  776 */               List<BoAirTimingPerform> allByUser = this.boAirTimingPerformService.getAllByUser(userCode, this.deviceAddress);
/*  777 */               if (allByUser.size() <= 0)
/*      */               {
/*  780 */                 BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/*      */                 String networkModes;
/*      */                 String panelAddress;
///*      */                 String networkModes;
/*  781 */                 if (bindingPanel == null) {
///*  782 */                   String panelAddress = "0000";
							 panelAddress = "0000";
/*  783 */                   networkModes = "1";
/*      */                 }
/*      */                 else {
/*  786 */                   panelAddress = bindingPanel.getPanelAddress();
/*  787 */                   networkModes = bindingPanel.getPatternType();
/*      */                 }
/*  789 */                 packNum(userCode);
/*  790 */                 System.err.println("userCode1 " + userCode);
/*  791 */                 SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
/*  792 */                 String date = sDate.format(new Date());
/*  793 */                 SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
/*  794 */                 String times = sTime.format(new Date());
/*  795 */                 String[] time = date.split("-");
/*  796 */                 String[] time2 = times.split(":");
/*      */ 
/*  804 */                 tims.put(panelAddress + "_" + this.deviceAddress, new String[] { time[0], time[1], time[2], time2[0], time2[1], time2[2] });
/*  805 */                 String[] strings = (String[])tims.get(panelAddress + "_" + this.deviceAddress);
/*  806 */                 if (strings != null) {
/*  807 */                   System.err.println(strings[0] + strings[1] + strings[2] + strings[3] + strings[4] + strings[5]);
/*      */                 }
/*  809 */                 String strss = "AIR-SET_TIME-" + user_num.get(userCode) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + time[0] + "," + time[1] + "," + time[2] + "," + time2[0] + "," + time2[1] + "," + time2[2];
/*  810 */                 byte[] bss = strss.getBytes();
/*  811 */                 System.err.println(new String(bss));
/*  812 */                 this.packetProcessHelper.processSendDDatas(hostDevice.getBoDevice().getDeviceCode(), bss);
/*  813 */                 this.requestJson.setData(map);
/*  814 */                 this.requestJson.setMessage("没有定时器");
/*  815 */                 this.requestJson.setSuccess(false);
/*      */               }
/*      */               else
/*      */               {
/*  819 */                 BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/*      */                 String networkModes;
/*      */                 String panelAddress;
///*      */                 String networkModes;
/*  820 */                 if (bindingPanel == null) {
///*  821 */                   String panelAddress = "0000";
							 panelAddress = "0000";
/*  822 */                   networkModes = "1";
/*      */                 }
/*      */                 else {
/*  825 */                   panelAddress = bindingPanel.getPanelAddress();
/*  826 */                   networkModes = bindingPanel.getPatternType();
/*      */                 }
/*  828 */                 packNum(userCode);
/*  829 */                 System.err.println("userCode2 " + userCode);
/*  830 */                 SimpleDateFormat sDate = new SimpleDateFormat("yyyy-MM-dd");
/*  831 */                 String date = sDate.format(new Date());
/*  832 */                 SimpleDateFormat sTime = new SimpleDateFormat("HH:mm:ss");
/*  833 */                 String times = sTime.format(new Date());
/*  834 */                 String[] time = date.split("-");
/*  835 */                 String[] time2 = times.split(":");
/*      */ 
/*  843 */                 tims.put(panelAddress + "_" + this.deviceAddress, new String[] { time[0], time[1], time[2], time2[0], time2[1], time2[2] });
/*  844 */                 String[] strings = (String[])tims.get(panelAddress + "_" + this.deviceAddress);
/*  845 */                 if (strings != null) {
/*  846 */                   System.err.println(strings[0] + strings[1] + strings[2] + strings[3] + strings[4] + strings[5]);
/*      */                 }
/*  848 */                 String strss = "AIR-SET_TIME-" + user_num.get(userCode) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + time[0] + "," + time[1] + "," + time[2] + "," + time2[0] + "," + time2[1] + "," + time2[2];
/*  849 */                 byte[] bss = strss.getBytes();
/*  850 */                 System.err.println(new String(bss));
/*  851 */                 this.packetProcessHelper.processSendDDatas(hostDevice.getBoDevice().getDeviceCode(), bss);
/*  852 */                 voList = new ArrayList();
/*  853 */                 for (BoAirTimingPerform BoAirTimingPerform : allByUser) {
/*  854 */                   Map maps = new HashMap();
/*  855 */                   maps.put("start", BoAirTimingPerform.getStart().toString());
/*  856 */                   maps.put("time", BoAirTimingPerform.getTime().toString());
/*  857 */                   maps.put("model", BoAirTimingPerform.getModel().toString());
/*  858 */                   maps.put("temp", BoAirTimingPerform.getTemp().toString());
/*  859 */                   maps.put("fan", BoAirTimingPerform.getFan().toString());
/*  860 */                   maps.put("control", BoAirTimingPerform.getContrl().toString());
/*      */ 
/*  862 */                   String[] arrayStr = new String[0];
/*  863 */                   arrayStr = BoAirTimingPerform.getDays().split(",");
/*  864 */                   maps.put("days", arrayStr);
/*  865 */                   voList.add(maps);
/*      */                 }
/*  867 */                 this.requestJson.setData(voList);
/*  868 */                 this.requestJson.setSuccess(true);
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/*  875 */             logger.info("error_" + e.getMessage());
/*  876 */             this.requestJson.setData(map);
/*  877 */             this.requestJson.setMessage("服务器发生异常");
/*  878 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/*  881 */           this.requestJson.setData(map);
/*  882 */           this.requestJson.setMessage("超时了");
/*  883 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  888 */         System.err.println("验证不通过");
/*      */ 
/*  890 */         this.requestJson.setData(map);
/*  891 */         this.requestJson.setMessage("验证不通过");
/*  892 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/*  895 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="airTiming", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String AirTiming()
/*      */     throws JSONException
/*      */   {
/*  904 */     this.requestJson = new RequestJson();
/*  905 */     Map map = new HashMap();
/*  906 */     HttpServletRequest request = ServletActionContext.getRequest();
/*  907 */     String ip = request.getRemoteAddr();
/*  908 */     String timestamp = request.getHeader("timestamp");
/*  909 */     String nonce = request.getHeader("nonce");
/*  910 */     String sign = request.getHeader("sign");
/*  911 */     String access_token = request.getHeader("access_token");
/*  912 */     String userCode = request.getHeader("userCode");
/*      */     List<BoAirTimingPerform> allByUser;
/*      */     int count;
/*  913 */     if (userCode.contains(",")) {
/*  914 */       String[] userCode2 = userCode.split(",");
/*  915 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/*  916 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/*  917 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "更新所有定时器列表信息");
/*  918 */       if (ral.booleanValue()) {
/*  919 */         System.err.println("验证通过");
/*  920 */         Long accessToken = Long.valueOf(timestamp);
/*      */ 
/*  922 */         if ((phone == null) || (boUsers == null)) {
/*  923 */           this.requestJson.setData(map);
/*  924 */           this.requestJson.setMessage("没有找到该编号");
/*  925 */           this.requestJson.setSuccess(true);
/*      */         }
/*  927 */         else if (access_token.equals(phone.getAccessToken())) {
/*  928 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue())
/*      */           {
/*      */             try
/*      */             {
/*  933 */               BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*  934 */               BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*      */               String networkModes;
/*      */               String panelAddress;
///*      */               String networkModes;
/*  935 */               if (bindingPanel == null) {
///*  936 */                 String panelAddress = "0000";
						   panelAddress = "0000";
/*  937 */                 networkModes = "1";
/*      */               }
/*      */               else {
/*  940 */                 panelAddress = bindingPanel.getPanelAddress();
/*  941 */                 networkModes = bindingPanel.getPatternType();
/*      */               }
/*      */ 
/*  944 */               allByUser = this.boAirTimingPerformService.getAllByUser(userCode2[0].trim().toString(), this.deviceAddress);
/*  945 */               for (BoAirTimingPerform boAirTimingPerform : allByUser) {
/*  946 */                 this.boAirTimingPerformService.delete(boAirTimingPerform);
/*      */               }
/*  948 */               JSONArray json = JSONArray.fromObject(this.timingInfo);
/*  949 */               int alarm_Clock_Count = json.size();
/*  950 */               StringBuffer alarm_Clock_Joining_together = new StringBuffer();
/*  951 */               count = 0;
/*  952 */               for (Iterator localIterator2 = json.iterator(); localIterator2.hasNext(); ) { Object object = localIterator2.next();
/*  953 */                 BoAirTimingPerform s = new BoAirTimingPerform();
/*  954 */                 JSONObject jso = JSONObject.fromObject(object);
/*  955 */                 System.err.println("星期 " + jso.get("days").toString());
/*  956 */                 JSONArray jsons = JSONArray.fromObject(jso.get("days").toString());
/*  957 */                 System.err.println("星期数组 " + jsons.size());
/*  958 */                 StringBuffer sq = new StringBuffer();
/*  959 */                 String[] strArray = { "7", "6", "5", "4", "3", "2", "1" };
/*  960 */                 if (jsons.size() <= 0)
/*  961 */                   for (int i = 0; i < strArray.length; i++) {
/*  962 */                     if (i > 0) {
/*  963 */                       sq.append(',');
/*      */                     }
/*  965 */                     sq.append(strArray[i]);
/*      */                   }
/*      */                 else {
/*  968 */                   for (int i = 0; i < jsons.size(); i++) {
/*  969 */                     if (i > 0) {
/*  970 */                       sq.append(',');
/*      */                     }
/*  972 */                     sq.append(jsons.get(i));
/*      */                   }
/*      */                 }
/*  975 */                 System.err.println(sq.toString());
/*  976 */                 s.setBoUsers(boUsers);
/*  977 */                 s.setStart(jso.get("start").toString());
/*  978 */                 s.setContrl(jso.get("control").toString());
/*  979 */                 s.setTemp(jso.get("temp").toString());
/*  980 */                 Integer valueOf = Integer.valueOf(jso.get("model").toString());
/*      */ 
/*  982 */                 Integer sad = valueOf;
/*  983 */                 s.setModel(jso.get("model").toString());
/*  984 */                 s.setFan(jso.get("fan").toString());
/*  985 */                 s.setTime(jso.get("time").toString());
/*  986 */                 s.setDays(sq.toString());
/*  987 */                 s.setDeviceaddress(this.deviceAddress);
/*  988 */                 this.boAirTimingPerformService.save(s);
/*  989 */                 String[] arrayStr = new String[7];
/*  990 */                 String[] arrayStrs = new String[0];
/*  991 */                 arrayStrs = sq.toString().split(",");
/*      */ 
/*  993 */                 for (int i = 0; i < arrayStrs.length; i++) {
/*  994 */                   if (arrayStrs[i].equals("7"))
/*  995 */                     arrayStr[0] = "1";
/*  996 */                   else if (arrayStrs[i].equals("6"))
/*  997 */                     arrayStr[1] = "1";
/*  998 */                   else if (arrayStrs[i].equals("5"))
/*  999 */                     arrayStr[2] = "1";
/* 1000 */                   else if (arrayStrs[i].equals("4"))
/* 1001 */                     arrayStr[3] = "1";
/* 1002 */                   else if (arrayStrs[i].equals("3"))
/* 1003 */                     arrayStr[4] = "1";
/* 1004 */                   else if (arrayStrs[i].equals("2"))
/* 1005 */                     arrayStr[5] = "1";
/* 1006 */                   else if (arrayStrs[i].equals("1")) {
/* 1007 */                     arrayStr[6] = "1";
/*      */                   }
/*      */                 }
/*      */ 
/* 1011 */                 StringBuffer week_Clock_Joining_together = new StringBuffer();
/* 1012 */                 StringBuffer week_Binary_Turn_Decimalism_Joining_together = new StringBuffer();
/* 1013 */                 for (int j = 0; j < arrayStr.length; j++) {
/* 1014 */                   if (arrayStr[j] == null)
/* 1015 */                     arrayStr[j] = "0";
/*      */                   else {
/* 1017 */                     arrayStr[j] = arrayStr[j];
/*      */                   }
/*      */ 
/* 1020 */                   week_Clock_Joining_together.append(arrayStr[j]);
/*      */                 }
/* 1022 */                 packNum(userCode2[0].trim().toString());
/* 1023 */                 String string = Integer.valueOf(week_Clock_Joining_together.toString(), 2).toString();
/* 1024 */                 week_Binary_Turn_Decimalism_Joining_together.append(string);
/* 1025 */                 String[] split = jso.get("time").toString().split(":");
/*      */                 String fan;
/*      */                 String models;
/*      */                 String temp;
///*      */                 String fan;
/* 1027 */                 if (jso.get("control").toString().equals("0")) {
///* 1028 */                   String models = "0";
///* 1029 */                   String temp = "0";
							 models = "0";
							 temp = "0";
/* 1030 */                   fan = "0";
/*      */                 } else {
/* 1032 */                   models = sad+"";
/* 1033 */                   temp = jso.get("temp").toString();
/* 1034 */                   fan = jso.get("fan").toString().trim();
/*      */                 }
/* 1036 */                 String sdsad = split[0] + "," + split[1] + "," + week_Binary_Turn_Decimalism_Joining_together.toString() + "," + jso.get("control").toString() + "," + models + "," + temp + "," + fan + ",";
/* 1037 */                 if (jso.get("start").toString().equals("1")) {
/* 1038 */                   count++;
/* 1039 */                   alarm_Clock_Joining_together.append(sdsad);
/*      */                 }
/*      */               }
/* 1042 */               int lastIndexOf = 0;
/* 1043 */               if (!alarm_Clock_Joining_together.toString().equals("")) {
/* 1044 */                 lastIndexOf = alarm_Clock_Joining_together.toString().lastIndexOf(",");
/*      */               }
/* 1046 */               String alarm_Clock_Joining_together_Interception = alarm_Clock_Joining_together.toString().substring(0, lastIndexOf);
/*      */               String ss;
///*      */               String ss;
/* 1048 */               if (!alarm_Clock_Joining_together_Interception.equals(""))
/* 1049 */                 ss = alarm_Clock_Joining_together_Interception;
/*      */               else
/* 1051 */                 ss = "";
/* 1052 */               String airAlarms = "AIR-APP_ALARMS-" + user_num.get(userCode2[0].trim().toString()) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + count + "," + ss;
/* 1053 */               int lastIndexOf2 = airAlarms.lastIndexOf(",");
/* 1054 */               System.err.println(lastIndexOf2);
/*      */               String substring;
///*      */               String substring;
/* 1056 */               if ((lastIndexOf2 == 31) || (lastIndexOf2 == 32) || (lastIndexOf2 == 33) || (lastIndexOf2 == 34) || (lastIndexOf2 == 35))
/* 1057 */                 substring = airAlarms.substring(0, lastIndexOf2);
/*      */               else {
/* 1059 */                 substring = airAlarms;
/*      */               }
/*      */ 
/* 1062 */               byte[] airAlarmsB = substring.getBytes();
/* 1063 */               System.err.println(new String(airAlarmsB));
/* 1064 */               this.packetProcessHelper.processSendDDatas(hostDevice.getBoDevice().getDeviceCode(), airAlarmsB);
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/* 1068 */               logger.info("error_" + e.getMessage());
/* 1069 */               this.requestJson.setData(map);
/* 1070 */               this.requestJson.setMessage("服务器发生异常");
/* 1071 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           }
/*      */           else {
/* 1075 */             System.err.println("AToken时间戳超时了");
/* 1076 */             this.requestJson.setData(map);
/* 1077 */             this.requestJson.setMessage("超时了");
/* 1078 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1081 */           System.err.println("AToken超时了");
/* 1082 */           this.requestJson.setData(map);
/* 1083 */           this.requestJson.setMessage("超时了");
/* 1084 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1088 */         System.err.println("验证不通过");
/*      */ 
/* 1090 */         this.requestJson.setData(map);
/* 1091 */         this.requestJson.setMessage("验证不通过");
/* 1092 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1095 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "更新所有定时器列表信息");
/* 1096 */       if (ral.booleanValue()) {
/* 1097 */         System.err.println("验证通过");
/* 1098 */         Long accessToken = Long.valueOf(timestamp);
/* 1099 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1100 */         if (boUsers == null) {
/* 1101 */           this.requestJson.setData(map);
/* 1102 */           this.requestJson.setMessage("没有找到该编号");
/* 1103 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1105 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue())
/*      */         {
/*      */           try
/*      */           {
/* 1110 */             BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/* 1111 */             BoHostDevice hostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/*      */             String networkModes;
/*      */             String panelAddress;
///*      */             String networkModes;
/* 1112 */             if (bindingPanel == null) {
///* 1113 */               String panelAddress = "0000";
						 panelAddress = "0000";
/* 1114 */               networkModes = "1";
/*      */             }
/*      */             else {
/* 1117 */               panelAddress = bindingPanel.getPanelAddress();
/* 1118 */               networkModes = bindingPanel.getPatternType();
/*      */             }
/*      */ 
/* 1121 */             allByUser = this.boAirTimingPerformService.getAllByUser(userCode, this.deviceAddress);
/* 1122 */             for (BoAirTimingPerform boAirTimingPerform : allByUser) {
/* 1123 */               this.boAirTimingPerformService.delete(boAirTimingPerform);
/*      */             }
/* 1125 */             JSONArray json = JSONArray.fromObject(this.timingInfo);
/* 1126 */             int alarm_Clock_Count = json.size();
/* 1127 */             StringBuffer alarm_Clock_Joining_together = new StringBuffer();
/* 1128 */             count = 0;
/* 1129 */             for (Iterator counts = json.iterator(); counts.hasNext(); ) { Object object = counts.next();
/* 1130 */               BoAirTimingPerform s = new BoAirTimingPerform();
/* 1131 */               JSONObject jso = JSONObject.fromObject(object);
/* 1132 */               System.err.println("星期 " + jso.get("days").toString());
/* 1133 */               JSONArray jsons = JSONArray.fromObject(jso.get("days").toString());
/* 1134 */               System.err.println("星期数组 " + jsons.size());
/* 1135 */               StringBuffer sq = new StringBuffer();
/* 1136 */               String[] strArray = { "7", "6", "5", "4", "3", "2", "1" };
/* 1137 */               if (jsons.size() <= 0)
/* 1138 */                 for (int i = 0; i < strArray.length; i++) {
/* 1139 */                   if (i > 0) {
/* 1140 */                     sq.append(',');
/*      */                   }
/* 1142 */                   sq.append(strArray[i]);
/*      */                 }
/*      */               else {
/* 1145 */                 for (int i = 0; i < jsons.size(); i++) {
/* 1146 */                   if (i > 0) {
/* 1147 */                     sq.append(',');
/*      */                   }
/* 1149 */                   sq.append(jsons.get(i));
/*      */                 }
/*      */               }
/* 1152 */               System.err.println(sq.toString());
/* 1153 */               s.setBoUsers(boUsers);
/* 1154 */               s.setStart(jso.get("start").toString());
/* 1155 */               s.setContrl(jso.get("control").toString());
/* 1156 */               s.setTemp(jso.get("temp").toString());
/* 1157 */               Integer valueOf = Integer.valueOf(jso.get("model").toString());
/*      */ 
/* 1159 */               Integer sad = valueOf;
/* 1160 */               s.setModel(jso.get("model").toString());
/* 1161 */               s.setFan(jso.get("fan").toString());
/* 1162 */               s.setTime(jso.get("time").toString());
/* 1163 */               s.setDays(sq.toString());
/* 1164 */               s.setDeviceaddress(this.deviceAddress);
/* 1165 */               this.boAirTimingPerformService.save(s);
/* 1166 */               String[] arrayStr = new String[7];
/* 1167 */               String[] arrayStrs = new String[0];
/* 1168 */               arrayStrs = sq.toString().split(",");
/*      */ 
/* 1170 */               for (int i = 0; i < arrayStrs.length; i++) {
/* 1171 */                 if (arrayStrs[i].equals("7"))
/* 1172 */                   arrayStr[0] = "1";
/* 1173 */                 else if (arrayStrs[i].equals("6"))
/* 1174 */                   arrayStr[1] = "1";
/* 1175 */                 else if (arrayStrs[i].equals("5"))
/* 1176 */                   arrayStr[2] = "1";
/* 1177 */                 else if (arrayStrs[i].equals("4"))
/* 1178 */                   arrayStr[3] = "1";
/* 1179 */                 else if (arrayStrs[i].equals("3"))
/* 1180 */                   arrayStr[4] = "1";
/* 1181 */                 else if (arrayStrs[i].equals("2"))
/* 1182 */                   arrayStr[5] = "1";
/* 1183 */                 else if (arrayStrs[i].equals("1")) {
/* 1184 */                   arrayStr[6] = "1";
/*      */                 }
/*      */               }
/*      */ 
/* 1188 */               StringBuffer week_Clock_Joining_together = new StringBuffer();
/* 1189 */               StringBuffer week_Binary_Turn_Decimalism_Joining_together = new StringBuffer();
/* 1190 */               for (int j = 0; j < arrayStr.length; j++) {
/* 1191 */                 if (arrayStr[j] == null)
/* 1192 */                   arrayStr[j] = "0";
/*      */                 else {
/* 1194 */                   arrayStr[j] = arrayStr[j];
/*      */                 }
/*      */ 
/* 1197 */                 week_Clock_Joining_together.append(arrayStr[j]);
/*      */               }
/* 1199 */               packNum(userCode);
/* 1200 */               String string = Integer.valueOf(week_Clock_Joining_together.toString(), 2).toString();
/* 1201 */               week_Binary_Turn_Decimalism_Joining_together.append(string);
/* 1202 */               String[] split = jso.get("time").toString().split(":");
/*      */               String fan;
/*      */               String models;
/*      */               String temp;
///*      */               String fan;
/* 1204 */               if (jso.get("control").toString().equals("0")) {
///* 1205 */                 String models = "0";
///* 1206 */                 String temp = "0";
						   models = "0";
						   temp = "0";
/* 1207 */                 fan = "0";
/*      */               } else {
/* 1209 */                 models = sad+"";
/* 1210 */                 temp = jso.get("temp").toString();
/* 1211 */                 fan = jso.get("fan").toString().trim();
/*      */               }
/* 1213 */               String sdsad = split[0] + "," + split[1] + "," + week_Binary_Turn_Decimalism_Joining_together.toString() + "," + jso.get("control").toString() + "," + models + "," + temp + "," + fan + ",";
/* 1214 */               if (jso.get("start").toString().equals("1")) {
/* 1215 */                 count++;
/* 1216 */                 alarm_Clock_Joining_together.append(sdsad);
/*      */               }
/*      */             }
/* 1219 */             int lastIndexOf = 0;
/* 1220 */             if (!alarm_Clock_Joining_together.toString().equals("")) {
/* 1221 */               lastIndexOf = alarm_Clock_Joining_together.toString().lastIndexOf(",");
/*      */             }
/* 1223 */             String alarm_Clock_Joining_together_Interception = alarm_Clock_Joining_together.toString().substring(0, lastIndexOf);
/*      */             String ss;
///*      */             String ss;
/* 1225 */             if (!alarm_Clock_Joining_together_Interception.equals(""))
/* 1226 */               ss = alarm_Clock_Joining_together_Interception;
/*      */             else
/* 1228 */               ss = "";
/* 1229 */             String airAlarms = "AIR-APP_ALARMS-" + user_num.get(userCode) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + count + "," + ss;
/* 1230 */             int lastIndexOf2 = airAlarms.lastIndexOf(",");
/* 1231 */             System.err.println(lastIndexOf2);
/*      */             String substring;
///*      */             String substring;
/* 1233 */             if ((lastIndexOf2 == 31) || (lastIndexOf2 == 32) || (lastIndexOf2 == 33) || (lastIndexOf2 == 34) || (lastIndexOf2 == 35))
/* 1234 */               substring = airAlarms.substring(0, lastIndexOf2);
/*      */             else {
/* 1236 */               substring = airAlarms;
/*      */             }
/*      */ 
/* 1239 */             byte[] airAlarmsB = substring.getBytes();
/* 1240 */             System.err.println(new String(airAlarmsB));
/* 1241 */             this.packetProcessHelper.processSendDDatas(hostDevice.getBoDevice().getDeviceCode(), airAlarmsB);
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/* 1245 */             logger.info("error_" + e.getMessage());
/* 1246 */             this.requestJson.setData(map);
/* 1247 */             this.requestJson.setMessage("服务器发生异常");
/* 1248 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         }
/*      */         else {
/* 1252 */           this.requestJson.setData(map);
/* 1253 */           this.requestJson.setMessage("超时了");
/* 1254 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1259 */         System.err.println("验证不通过");
/*      */ 
/* 1261 */         this.requestJson.setData(map);
/* 1262 */         this.requestJson.setMessage("验证不通过");
/* 1263 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1266 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="gainParams", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String gainParams()
/*      */     throws InterruptedException
/*      */   {
/* 1275 */     this.requestJson = new RequestJson();
/* 1276 */     Map map = new HashMap();
/* 1277 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 1278 */     String ip = request.getRemoteAddr();
/* 1279 */     String timestamp = request.getHeader("timestamp");
/* 1280 */     String nonce = request.getHeader("nonce");
/* 1281 */     String sign = request.getHeader("sign");
/* 1282 */     String access_token = request.getHeader("access_token");
/* 1283 */     String userCode = request.getHeader("userCode");
/* 1284 */     if (userCode.contains(",")) {
/* 1285 */       String[] userCode2 = userCode.split(",");
/* 1286 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 1287 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 1288 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取AIR参数");
/* 1289 */       if (ral.booleanValue()) {
/* 1290 */         System.err.println("验证通过");
/* 1291 */         Long accessToken = Long.valueOf(timestamp);
/* 1292 */         if ((phone == null) || (boUsers == null)) {
/* 1293 */           this.requestJson.setData(map);
/* 1294 */           this.requestJson.setMessage("没有找到该编号");
/* 1295 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1297 */         else if (access_token.equals(phone.getAccessToken())) {
/* 1298 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 1300 */               packNum(userCode2[0].trim().toString());
/* 1301 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/* 1302 */               if (boHostDevice == null) {
/* 1303 */                 this.requestJson.setData(map);
/* 1304 */                 this.requestJson.setMessage("没有找到");
/* 1305 */                 this.requestJson.setSuccess(true);
/*      */               }
/*      */               else
/*      */               {
/* 1309 */                 BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*      */                 String networkModes;
/*      */                 String panelAddress;
///*      */                 String networkModes;
/* 1310 */                 if (bindingPanel == null) {
///* 1311 */                   String panelAddress = "0000";
							 panelAddress = "0000";
/* 1312 */                   networkModes = "1";
/*      */                 } else {
/* 1314 */                   panelAddress = bindingPanel.getPanelAddress();
/* 1315 */                   networkModes = bindingPanel.getPatternType();
/*      */                 }
/* 1317 */                 String strss = "AIR-READ-" + user_num.get(userCode2[0].trim().toString()) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes;
/* 1318 */                 byte[] bss = strss.getBytes();
/* 1319 */                 System.err.println(new String(bss));
/* 1320 */                 this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/* 1321 */                 Thread.sleep(2000L);
/*      */                 try {
/* 1323 */                   String[] array = (String[])StaticUtil.AIR_Params.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.deviceAddress);
/* 1324 */                   String model = "";
/* 1325 */                   String temp = "";
/* 1326 */                   String fan = "";
/* 1327 */                   String environment_temperature = "";
/* 1328 */                   if (array == null) {
/* 1329 */                     this.requestJson.setData(map);
/* 1330 */                     this.requestJson.setMessage("查询失败");
/* 1331 */                     this.requestJson.setSuccess(false);
/*      */                   }
/* 1333 */                   else if (array != null) {
/* 1334 */                     model = array[0];
/* 1335 */                     temp = array[1];
/* 1336 */                     fan = array[3];
/* 1337 */                     environment_temperature = array[2];
/* 1338 */                     map.put("model", model);
/* 1339 */                     map.put("temp", temp);
/* 1340 */                     map.put("fan", fan);
/* 1341 */                     map.put("et", environment_temperature);
/* 1342 */                     this.requestJson.setData(map);
/* 1343 */                     this.requestJson.setSuccess(true);
/*      */                   }
/*      */                 }
/*      */                 finally {
/* 1347 */                   StaticUtil.AIR_Params.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.deviceAddress);
/*      */                 }
/*      */ 
/*      */               }
/*      */ 
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/* 1356 */               logger.info("error_" + e.getMessage());
/* 1357 */               this.requestJson.setData(map);
/* 1358 */               this.requestJson.setMessage("服务器发生异常");
/* 1359 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 1362 */             System.err.println("AToken时间戳超时了");
/* 1363 */             this.requestJson.setData(map);
/* 1364 */             this.requestJson.setMessage("超时了");
/* 1365 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1368 */           System.err.println("AToken超时了");
/* 1369 */           this.requestJson.setData(map);
/* 1370 */           this.requestJson.setMessage("超时了");
/* 1371 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1375 */         System.err.println("验证不通过");
/*      */ 
/* 1377 */         this.requestJson.setData(map);
/* 1378 */         this.requestJson.setMessage("验证不通过");
/* 1379 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1382 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "获取AIR参数");
/* 1383 */       if (ral.booleanValue()) {
/* 1384 */         System.err.println("验证通过");
/* 1385 */         Long accessToken = Long.valueOf(timestamp);
/* 1386 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1387 */         if (boUsers == null) {
/* 1388 */           this.requestJson.setData(map);
/* 1389 */           this.requestJson.setMessage("没有找到该编号");
/* 1390 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1392 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 1394 */             packNum(userCode);
/* 1395 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/* 1396 */             if (boHostDevice == null) {
/* 1397 */               this.requestJson.setData(map);
/* 1398 */               this.requestJson.setMessage("没有找到");
/* 1399 */               this.requestJson.setSuccess(true);
/*      */             }
/*      */             else
/*      */             {
/* 1403 */               BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/*      */               String networkModes;
/*      */               String panelAddress;
///*      */               String networkModes;
/* 1404 */               if (bindingPanel == null) {
///* 1405 */                 String panelAddress = "0000";
						   panelAddress = "0000";
/* 1406 */                 networkModes = "1";
/*      */               } else {
/* 1408 */                 panelAddress = bindingPanel.getPanelAddress();
/* 1409 */                 networkModes = bindingPanel.getPatternType();
/*      */               }
/* 1411 */               String strss = "AIR-READ-" + user_num.get(userCode) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes;
/* 1412 */               byte[] bss = strss.getBytes();
/* 1413 */               System.err.println(new String(bss));
/* 1414 */               this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/* 1415 */               Thread.sleep(2000L);
/*      */               try {
/* 1417 */                 String[] array = (String[])StaticUtil.AIR_Params.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.deviceAddress);
/* 1418 */                 String model = "";
/* 1419 */                 String temp = "";
/* 1420 */                 String fan = "";
/* 1421 */                 String environment_temperature = "";
/* 1422 */                 if (array == null) {
/* 1423 */                   this.requestJson.setData(map);
/* 1424 */                   this.requestJson.setMessage("查询失败");
/* 1425 */                   this.requestJson.setSuccess(false);
/*      */                 }
/* 1427 */                 else if (array != null) {
/* 1428 */                   model = array[0];
/* 1429 */                   temp = array[1];
/* 1430 */                   fan = array[3];
/* 1431 */                   environment_temperature = array[2];
/* 1432 */                   map.put("model", model);
/* 1433 */                   map.put("temp", temp);
/* 1434 */                   map.put("fan", fan);
/* 1435 */                   map.put("et", environment_temperature);
/* 1436 */                   this.requestJson.setData(map);
/* 1437 */                   this.requestJson.setSuccess(true);
/*      */                 }
/*      */               }
/*      */               finally {
/* 1441 */                 StaticUtil.AIR_Params.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.deviceAddress);
/*      */               }
/*      */ 
/*      */             }
/*      */ 
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/* 1450 */             logger.info("error_" + e.getMessage());
/* 1451 */             this.requestJson.setData(map);
/* 1452 */             this.requestJson.setMessage("服务器发生异常");
/* 1453 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1456 */           this.requestJson.setData(map);
/* 1457 */           this.requestJson.setMessage("超时了");
/* 1458 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1463 */         System.err.println("验证不通过");
/*      */ 
/* 1465 */         this.requestJson.setData(map);
/* 1466 */         this.requestJson.setMessage("验证不通过");
/* 1467 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1470 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="control", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String control()
/*      */   {
/* 1479 */     this.requestJson = new RequestJson();
/* 1480 */     Map map = new HashMap();
/* 1481 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 1482 */     String ip = request.getRemoteAddr();
/* 1483 */     String timestamp = request.getHeader("timestamp");
/* 1484 */     String nonce = request.getHeader("nonce");
/* 1485 */     String sign = request.getHeader("sign");
/* 1486 */     String access_token = request.getHeader("access_token");
/* 1487 */     String userCode = request.getHeader("userCode");
/* 1488 */     if (userCode.contains(",")) {
/* 1489 */       String[] userCode2 = userCode.split(",");
/* 1490 */       BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode2[0].trim().toString());
/* 1491 */       BoUsers phone = this.boUserServicess.findByUserPhone(userCode2[1].trim().toString());
/* 1492 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "发送控制AIR");
/* 1493 */       if (ral.booleanValue()) {
/* 1494 */         System.err.println("验证通过");
/* 1495 */         Long accessToken = Long.valueOf(timestamp);
/*      */ 
/* 1497 */         if ((phone == null) || (boUsers == null)) {
/* 1498 */           this.requestJson.setData(map);
/* 1499 */           this.requestJson.setMessage("没有找到该编号");
/* 1500 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1502 */         else if (access_token.equals(phone.getAccessToken())) {
/* 1503 */           if (accessToken.longValue() < Long.valueOf(phone.getAccessTokenTime()).longValue()) {
/*      */             try {
/* 1505 */               packNum(userCode2[0].trim().toString());
/* 1506 */               BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/* 1507 */               if (boHostDevice == null) {
/* 1508 */                 this.requestJson.setData(map);
/* 1509 */                 this.requestJson.setMessage("没有找到");
/* 1510 */                 this.requestJson.setSuccess(true);
/*      */               }
/*      */               else
/*      */               {
/* 1514 */                 BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode2[0].trim().toString(), this.deviceAddress);
/*      */                 String networkModes;
/*      */                 String panelAddress;
///*      */                 String networkModes;
/* 1515 */                 if (bindingPanel == null) {
///* 1516 */                   String panelAddress = "0000";
							 panelAddress = "0000";
/* 1517 */                   networkModes = this.networkMode;
/*      */                 } else {
/* 1519 */                   panelAddress = bindingPanel.getPanelAddress();
/* 1520 */                   networkModes = bindingPanel.getPatternType();
/*      */                 }
/*      */ 
/* 1523 */                 String[] controlParams = this.controlParam.split(",");
/* 1524 */                 System.err.println(networkModes);
/* 1525 */                 if (Integer.valueOf(controlParams[0]).intValue() > 99)
/*      */                   try {
/* 1527 */                     String strss = "AIR-POWER_OFF-" + user_num.get(userCode2[0].trim().toString()) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes;
/* 1528 */                     byte[] bss = strss.getBytes();
/* 1529 */                     System.err.println(new String(bss));
/* 1530 */                     this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/* 1531 */                     Thread.sleep(1000L);
/* 1532 */                     Object airOff = StaticUtil.AIROFF.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1533 */                     if (airOff != null) {
/* 1534 */                       if (airOff.equals("OK")) {
/* 1535 */                         this.requestJson.setData(map);
/* 1536 */                         this.requestJson.setMessage("控制成功");
/* 1537 */                         this.requestJson.setSuccess(true);
/*      */                       } else {
/* 1539 */                         this.requestJson.setData(map);
/* 1540 */                         this.requestJson.setMessage("控制失败");
/* 1541 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     }
/*      */                     else {
/* 1545 */                       this.requestJson.setData(map);
/* 1546 */                       this.requestJson.setMessage("控制失败");
/* 1547 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } finally {
/* 1550 */                     StaticUtil.AIROFF.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1551 */                     System.err.println("清楚关机AIROFF");
/*      */                   }
/*      */                 else {
/*      */                   try {
/* 1555 */                     String strss = "AIR-SEND-" + user_num.get(userCode2[0].trim().toString()) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + controlParams[0] + "," + controlParams[1] + "," + controlParams[2];
/* 1556 */                     byte[] bss = strss.getBytes();
/* 1557 */                     System.err.println(new String(bss));
/* 1558 */                     this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/* 1559 */                     Thread.sleep(1000L);
/* 1560 */                     Object airSend = StaticUtil.AIRSEND.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1561 */                     if (airSend != null) {
/* 1562 */                       if (airSend.equals("OK")) {
/* 1563 */                         System.err.println("控制成功");
/* 1564 */                         this.requestJson.setData(map);
/* 1565 */                         this.requestJson.setMessage("控制成功");
/* 1566 */                         this.requestJson.setSuccess(true);
/*      */                       } else {
/* 1568 */                         System.err.println("控制失败");
/* 1569 */                         this.requestJson.setData(map);
/* 1570 */                         this.requestJson.setMessage("控制失败");
/* 1571 */                         this.requestJson.setSuccess(false);
/*      */                       }
/*      */                     }
/*      */                     else {
/* 1575 */                       System.err.println("控制失败");
/* 1576 */                       this.requestJson.setData(map);
/* 1577 */                       this.requestJson.setMessage("控制失败");
/* 1578 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   } finally {
/* 1581 */                     StaticUtil.AIRSEND.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1582 */                     System.err.println("清楚控制AIRSEND");
/*      */                   }
/*      */                 }
/*      */               }
/*      */ 
/*      */             }
/*      */             catch (Exception e)
/*      */             {
/* 1590 */               logger.info("error_" + e.getMessage());
/* 1591 */               this.requestJson.setData(map);
/* 1592 */               this.requestJson.setMessage("服务器发生异常");
/* 1593 */               this.requestJson.setSuccess(false);
/*      */             }
/*      */           } else {
/* 1596 */             System.err.println("AToken时间戳超时了");
/* 1597 */             this.requestJson.setData(map);
/* 1598 */             this.requestJson.setMessage("超时了");
/* 1599 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1602 */           System.err.println("AToken超时了");
/* 1603 */           this.requestJson.setData(map);
/* 1604 */           this.requestJson.setMessage("超时了");
/* 1605 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else {
/* 1609 */         System.err.println("验证不通过");
/*      */ 
/* 1611 */         this.requestJson.setData(map);
/* 1612 */         this.requestJson.setMessage("验证不通过");
/* 1613 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     } else {
/* 1616 */       Boolean ral = isRal(timestamp, nonce, sign, access_token, userCode, "发送控制AIR");
/* 1617 */       if (ral.booleanValue()) {
/* 1618 */         System.err.println("验证通过");
/* 1619 */         Long accessToken = Long.valueOf(timestamp);
/* 1620 */         BoUsers boUsers = this.boUserServicess.findByUserUserCode(userCode);
/* 1621 */         if (boUsers == null) {
/* 1622 */           this.requestJson.setData(map);
/* 1623 */           this.requestJson.setMessage("没有找到该编号");
/* 1624 */           this.requestJson.setSuccess(true);
/*      */         }
/* 1626 */         else if (accessToken.longValue() < Long.valueOf(boUsers.getAccessTokenTime()).longValue()) {
/*      */           try {
/* 1628 */             packNum(userCode);
/* 1629 */             BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(userCode, this.deviceAddress);
/* 1630 */             if (boHostDevice == null) {
/* 1631 */               this.requestJson.setData(map);
/* 1632 */               this.requestJson.setMessage("没有找到");
/* 1633 */               this.requestJson.setSuccess(true);
/*      */             }
/*      */             else
/*      */             {
/* 1637 */               BoAirBindingPanel bindingPanel = this.boAirBindingPanelService.findPanelBindingDeviceAddress(userCode, this.deviceAddress);
/*      */               String networkModes;
/*      */               String panelAddress;
///*      */               String networkModes;
/* 1638 */               if (bindingPanel == null) {
///* 1639 */                 String panelAddress = "0000";
						   panelAddress = "0000";
/* 1640 */                 networkModes = this.networkMode;
/*      */               } else {
/* 1642 */                 panelAddress = bindingPanel.getPanelAddress();
/* 1643 */                 networkModes = bindingPanel.getPatternType();
/*      */               }
/*      */ 
/* 1646 */               String[] controlParams = this.controlParam.split(",");
/* 1647 */               System.err.println(networkModes);
/* 1648 */               if (Integer.valueOf(controlParams[0]).intValue() > 99)
/*      */                 try {
/* 1650 */                   String strss = "AIR-POWER_OFF-" + user_num.get(userCode) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes;
/* 1651 */                   byte[] bss = strss.getBytes();
/* 1652 */                   System.err.println(new String(bss));
/* 1653 */                   this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/* 1654 */                   Thread.sleep(1000L);
/* 1655 */                   Object airOff = StaticUtil.AIROFF.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1656 */                   if (airOff != null) {
/* 1657 */                     if (airOff.equals("OK")) {
/* 1658 */                       this.requestJson.setData(map);
/* 1659 */                       this.requestJson.setMessage("控制成功");
/* 1660 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1662 */                       this.requestJson.setData(map);
/* 1663 */                       this.requestJson.setMessage("控制失败");
/* 1664 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   }
/*      */                   else {
/* 1668 */                     this.requestJson.setData(map);
/* 1669 */                     this.requestJson.setMessage("控制失败");
/* 1670 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/* 1673 */                   StaticUtil.AIROFF.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1674 */                   System.err.println("清楚关机AIROFF");
/*      */                 }
/*      */               else {
/*      */                 try {
/* 1678 */                   String strss = "AIR-SEND-" + user_num.get(userCode) + "," + panelAddress + "," + this.deviceAddress + "," + networkModes + "," + controlParams[0] + "," + controlParams[1] + "," + controlParams[2];
/* 1679 */                   byte[] bss = strss.getBytes();
/* 1680 */                   System.err.println(new String(bss));
/* 1681 */                   this.packetProcessHelper.processSendDDatas(boHostDevice.getBoDevice().getDeviceCode(), bss);
/* 1682 */                   Thread.sleep(1000L);
/* 1683 */                   Object airSend = StaticUtil.AIRSEND.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1684 */                   if (airSend != null) {
/* 1685 */                     if (airSend.equals("OK")) {
/* 1686 */                       System.err.println("控制成功");
/* 1687 */                       this.requestJson.setData(map);
/* 1688 */                       this.requestJson.setMessage("控制成功");
/* 1689 */                       this.requestJson.setSuccess(true);
/*      */                     } else {
/* 1691 */                       System.err.println("控制失败");
/* 1692 */                       this.requestJson.setData(map);
/* 1693 */                       this.requestJson.setMessage("控制失败");
/* 1694 */                       this.requestJson.setSuccess(false);
/*      */                     }
/*      */                   }
/*      */                   else {
/* 1698 */                     System.err.println("控制失败");
/* 1699 */                     this.requestJson.setData(map);
/* 1700 */                     this.requestJson.setMessage("控制失败");
/* 1701 */                     this.requestJson.setSuccess(false);
/*      */                   }
/*      */                 } finally {
/* 1704 */                   StaticUtil.AIRSEND.remove(boHostDevice.getBoDevice().getDeviceCode() + "_" + panelAddress + "," + this.deviceAddress);
/* 1705 */                   System.err.println("清楚控制AIRSEND");
/*      */                 }
/*      */               }
/*      */             }
/*      */ 
/*      */           }
/*      */           catch (Exception e)
/*      */           {
/* 1713 */             logger.info("error_" + e.getMessage());
/* 1714 */             this.requestJson.setData(map);
/* 1715 */             this.requestJson.setMessage("服务器发生异常");
/* 1716 */             this.requestJson.setSuccess(false);
/*      */           }
/*      */         } else {
/* 1719 */           this.requestJson.setData(map);
/* 1720 */           this.requestJson.setMessage("超时了");
/* 1721 */           this.requestJson.setSuccess(false);
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1726 */         System.err.println("验证不通过");
/*      */ 
/* 1728 */         this.requestJson.setData(map);
/* 1729 */         this.requestJson.setMessage("验证不通过");
/* 1730 */         this.requestJson.setSuccess(false);
/*      */       }
/*      */     }
/* 1733 */     return "success";
/*      */   }
/*      */ 
/*      */   @Action(value="test", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*      */   public String Test() {
/* 1738 */     this.requestJson = new RequestJson();
/* 1739 */     Map map = new HashMap();
/* 1740 */     map.put("jsdhjsa", "1");
/* 1741 */     this.requestJson.setData(map);
/* 1742 */     this.requestJson.setMessage("测试");
/* 1743 */     this.requestJson.setSuccess(true);
/* 1744 */     return "success";
/*      */   }
/*      */ 
/*      */   public String getUserCode()
/*      */   {
/* 1759 */     return this.userCode;
/*      */   }
/*      */ 
/*      */   public void setUserCode(String userCode) {
/* 1763 */     this.userCode = userCode;
/*      */   }
/*      */ 
/*      */   public String getTimingInfo() {
/* 1767 */     return this.timingInfo;
/*      */   }
/*      */ 
/*      */   public void setTimingInfo(String timingInfo) {
/* 1771 */     this.timingInfo = timingInfo;
/*      */   }
/*      */ 
/*      */   public String getDeviceAddress() {
/* 1775 */     return this.deviceAddress;
/*      */   }
/*      */ 
/*      */   public void setDeviceAddress(String deviceAddress) {
/* 1779 */     this.deviceAddress = deviceAddress;
/*      */   }
/*      */ 
/*      */   public String getNetworkMode() {
/* 1783 */     return this.networkMode;
/*      */   }
/*      */ 
/*      */   public void setNetworkMode(String networkMode) {
/* 1787 */     this.networkMode = networkMode;
/*      */   }
/*      */ 
/*      */   public String getPanelAddress() {
/* 1791 */     return this.panelAddress;
/*      */   }
/*      */ 
/*      */   public void setPanelAddress(String panelAddress) {
/* 1795 */     this.panelAddress = panelAddress;
/*      */   }
/*      */ 
/*      */   public String getControlParam() {
/* 1799 */     return this.controlParam;
/*      */   }
/*      */ 
/*      */   public void setControlParam(String controlParam) {
/* 1803 */     this.controlParam = controlParam;
/*      */   }
/*      */ 
/*      */   public String getCommandType() {
/* 1807 */     return this.commandType;
/*      */   }
/*      */ 
/*      */   public void setCommandType(String commandType) {
/* 1811 */     this.commandType = commandType;
/*      */   }
/*      */ 
/*      */   public RequestJson getRequestJson()
/*      */   {
/* 1838 */     return this.requestJson;
/*      */   }
/*      */ 
/*      */   public void setRequestJson(RequestJson requestJson)
/*      */   {
/* 1843 */     this.requestJson = requestJson;
/*      */   }
/*      */ 
/*      */   public PacketProcessHelper getPacketProcessHelper()
/*      */   {
/* 1849 */     return this.packetProcessHelper;
/*      */   }
/*      */ 
/*      */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper)
/*      */   {
/* 1855 */     this.packetProcessHelper = packetProcessHelper;
/*      */   }
/*      */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.Customization_Action
 * JD-Core Version:    0.6.2
 */