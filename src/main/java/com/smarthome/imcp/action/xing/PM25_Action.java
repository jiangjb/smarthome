/*     */ package com.smarthome.imcp.action.xing;
/*     */ 
/*     */ import com.smarthome.dock.server.helper.PacketProcessHelper;
/*     */ import com.smarthome.dock.server.util.StaticUtil;
/*     */ import com.smarthome.imcp.action.AbstractAction;
/*     */ import com.smarthome.imcp.controller.RequestJson;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*     */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*     */ import com.smarthome.imcp.util.SignUtils;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.struts2.ServletActionContext;
/*     */ import org.apache.struts2.convention.annotation.Action;
/*     */ import org.apache.struts2.convention.annotation.Namespace;
/*     */ import org.apache.struts2.convention.annotation.ParentPackage;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ @ParentPackage("auth-check-package")
/*     */ @Namespace("/pm25")
/*     */ public class PM25_Action extends AbstractAction
/*     */ {
/*  39 */   private static Logger logger = LoggerFactory.getLogger(PM25_Action.class);
/*  40 */   private static Map<String, Integer> user_num = new HashMap();
/*  41 */   private List<String> al = new ArrayList();
/*     */   private String deviceCode;
/*     */   private String deviceAddress;
/*     */ 
/*     */   @Autowired
/*     */   private PacketProcessHelper packetProcessHelper;
/*     */ 
/*     */   @Autowired
/*     */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/* 126 */   private RequestJson requestJson = new RequestJson();
/*     */ 
/*     */   public void packNum(String userCode)
/*     */   {
/*  48 */     if (user_num.get(userCode) == null)
/*  49 */       user_num.put(userCode, Integer.valueOf(0));
/*     */     else
/*  51 */       user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*     */   }
/*     */ 
/*     */   @Action(value="gainPM25", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String gainPM25()
/*     */     throws Exception
/*     */   {
/*  58 */     this.requestJson = new RequestJson();
/*     */     try {
/*  60 */       HttpServletRequest request = ServletActionContext.getRequest();
/*  61 */       String userCode = request.getHeader("userCode");
/*  62 */       Boolean ral = SignUtils.isRal(request);
/*  63 */       if (ral.booleanValue()) {
/*  64 */         String[] user_Code = userCode.split(",");
/*  65 */         if (userCode.contains(",")) {
/*  66 */           List pm25List = new ArrayList();
/*  67 */           Map pm25_Map = new HashMap();
/*  68 */           packNum(user_Code[0].trim().toString());
/*  69 */           BoHostDevice boHostDevice = this.boHostDeviceService.findBydeviceAddress(user_Code[0].trim().toString(), this.deviceAddress);
/*  70 */           if (boHostDevice != null) {
/*  71 */             String pm2_5 = "ZIGBEE_PM25-READ-" + user_num.get(user_Code[0].trim().toString()) + "," + this.deviceAddress;
/*  72 */             byte[] pm25 = pm2_5.getBytes();
/*  73 */             System.err.println(new String(pm25));
/*  74 */             this.packetProcessHelper.processSendDData(boHostDevice.getBoDevice().getDeviceCode(), pm25);
/*  75 */             Thread.sleep(2000L);
/*  76 */             String pm25Map = (String)StaticUtil.PM25.get(boHostDevice.getBoDevice().getDeviceCode() + "_" + this.deviceAddress);
/*  77 */             if (pm25Map != null) {
/*  78 */               pm25_Map.put("pm25", pm25Map);
/*  79 */               pm25List.add(pm25_Map);
/*  80 */               this.requestJson = new RequestJson(true, "", pm25List);
/*     */             } else {
/*  82 */               this.requestJson = new RequestJson(false, "网络超时,通讯失败", this.al);
/*     */             }
/*     */           } else {
/*  85 */             this.requestJson = new RequestJson(false, "该用户没有该设备", this.al);
/*     */           }
/*     */         } else {
/*  88 */           this.requestJson = new RequestJson(false, "当前版本低级", this.al);
/*     */         }
/*     */       } else {
/*  91 */         this.requestJson = new RequestJson(false, "<gainPM25接口验证不通过>", this.al);
/*  92 */         System.err.println("<gainPM25接口验证不通过>");
/*     */       }
/*     */     }
/*     */     catch (Exception e) {
/*  96 */       logger.info("error" + e.getMessage());
/*  97 */       e.printStackTrace();
/*  98 */       this.requestJson.setData(this.al);
/*  99 */       this.requestJson.setMessage("服务器发生异常");
/* 100 */       this.requestJson.setSuccess(false);
/*     */     }
/* 102 */     return "success";
/*     */   }
/*     */ 
/*     */   public String getDeviceCode()
/*     */   {
/* 109 */     return this.deviceCode;
/*     */   }
/*     */   public void setDeviceCode(String deviceCode) {
/* 112 */     this.deviceCode = deviceCode;
/*     */   }
/*     */ 
/*     */   public String getDeviceAddress() {
/* 116 */     return this.deviceAddress;
/*     */   }
/*     */   public void setDeviceAddress(String deviceAddress) {
/* 119 */     this.deviceAddress = deviceAddress;
/*     */   }
/*     */ 
/*     */   public RequestJson getRequestJson()
/*     */   {
/* 128 */     return this.requestJson;
/*     */   }
/*     */   public void setRequestJson(RequestJson requestJson) {
/* 131 */     this.requestJson = requestJson;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.PM25_Action
 * JD-Core Version:    0.6.2
 */