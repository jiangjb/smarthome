/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.HttpClientUtils;
/*     */ import com.smarthome.imcp.dao.model.bo.BoAlarm;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoReport;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.service.bo.BoAlarmServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoProcessServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoReportServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserServiceIface;
/*     */ import com.smarthome.imcp.service.impl.push.PushService;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import net.sf.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boProcessService")
/*     */ public class BoProcessServiceImpl
/*     */   implements BoProcessServiceIface
/*     */ {
/*  34 */   private static Logger logger = LoggerFactory.getLogger(BoProcessServiceImpl.class);
/*     */ 
/*     */   @Autowired
/*     */   private BoUserServiceIface<BoUser, Serializable> boUserService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceServiceIface<BoUserDevice, Serializable> boUserDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoReportServiceIface<BoReport, Serializable> boReportService;
/*     */ 
/*     */   @Autowired
/*     */   private BoAlarmServiceIface<BoAlarm, Serializable> boAlarmService;
/*     */ 
/*  48 */   public String[] getTude(String ip) { String[] ret = { "", "", "", "", "" };
/*  49 */     String res = "";
/*  50 */     String latitude = "";
/*  51 */     String longitude = "";
/*     */     try
/*     */     {
/*  54 */       res = HttpClientUtils.getDoGetURL("http://api.map.baidu.com/location/ip?ak=2ajUY32HGnGdLWv0Q1sT9pHI&ip=" + ip + "&coor=bd09ll", null);
/*     */ 
/*  57 */       JSONObject json = JSONObject.fromObject(res);
/*  58 */       JSONObject content = json.getJSONObject("content");
/*  59 */       if (content != null) {
/*  60 */         String address = content.getString("address");
/*  61 */         JSONObject point = content.getJSONObject("point");
/*     */ 
/*  63 */         latitude = point.getString("x");
/*  64 */         longitude = point.getString("y");
/*     */ 
/*  66 */         ret[0] = latitude;
/*  67 */         ret[1] = longitude;
/*     */ 
/*  69 */         JSONObject detail = content.getJSONObject("address_detail");
/*     */ 
/*  71 */         String province = detail.getString("province");
/*  72 */         String city = detail.getString("city");
/*  73 */         ret[2] = province;
/*  74 */         ret[3] = city;
/*  75 */         ret[4] = address;
/*     */       }
/*     */ 
/*  78 */       logger.debug("latitude:" + latitude + " longitude:" + longitude);
/*     */     } catch (Exception e) {
/*  80 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  83 */     return ret; }
/*     */ 
/*     */   public void doInitDevice()
/*     */   {
/*  87 */     this.boDeviceService.updateAllStatus(0);
/*     */   }
/*     */ 
/*     */   public BoDevice findDevice(String deviceCode) {
/*  91 */     return this.boDeviceService.findByCode(deviceCode);
/*     */   }
/*     */ 
/*     */   public boolean doLogin(String deviceCode, String ip) {
/*  95 */     BoDevice device = this.boDeviceService.findByCode(deviceCode);
/*  96 */     if (device == null) {
/*  97 */       return false;
/*     */     }
/*  99 */     String[] ret = { "0", "0", "", "", "" };
/* 100 */     String latitude = device.getLatitude();
/* 101 */     String longitude = device.getLongitude();
/* 102 */     if ((latitude == null) || (longitude == null)) {
/* 103 */       logger.info("getTude......................");
/*     */ 
/* 105 */       latitude = ret[0];
/* 106 */       longitude = ret[1];
/* 107 */       logger.info(ret[0] + ret[1] + ret[2] + ret[3] + ret[4]);
/* 108 */       logger.info(ip);
/*     */ 
/* 110 */       device.setStatus(Integer.valueOf(1));
/* 111 */       device.setDevIp(ip);
/* 112 */       device.setLatitude(latitude);
/* 113 */       device.setLongitude(longitude);
/* 114 */       device.setProvince(ret[2]);
/* 115 */       device.setCity(ret[3]);
/* 116 */       device.setRegion(ret[4]);
/*     */     }
/* 118 */     device.setStatus(Integer.valueOf(1));
/* 119 */     device.setHostStatus("在线");
/* 120 */     this.boDeviceService.update(device);
/*     */ 
/* 122 */     List<BoUserDevice> list = this.boUserDeviceService.getListByDeviceCode(deviceCode);
/* 123 */     if (list != null) {
/* 124 */       for (BoUserDevice b : list) {
/* 125 */         this.boUserService.updateUserIP(b.getBoUser().getUserId(), ip, latitude, longitude);
/*     */       }
/*     */     }
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   public void doKeepAliveLost(String deviceCode, String ip) {
/* 132 */     this.boDeviceService.updateStatus(deviceCode, 0);
/*     */   }
/*     */ 
/*     */   public void doKeepAlive(String deviceCode, String ip) {
/* 136 */     boolean isUpdate = false;
/* 137 */     BoDevice device = this.boDeviceService.findByCode(deviceCode);
/* 138 */     if ((device.getStatus() == null) || (device.getStatus().intValue() <= 0) || (!ip.equals(device.getDevIp()))) {
/* 139 */       String[] ret = { "0", "0", "", "", "" };
/*     */ 
/* 141 */       this.boDeviceService.updateIP(device.getDeviceId(), 1, ip, ret);
/* 142 */       isUpdate = true;
/*     */     }
/* 144 */     if (!isUpdate)
/* 145 */       this.boDeviceService.updateStatus(deviceCode, 1);
/*     */   }
/*     */ 
/*     */   public void doSaveReport(BoReport report)
/*     */   {
/* 150 */     this.boReportService.save(report);
/*     */   }
/*     */ 
/*     */   public void doSaveAlarm(BoAlarm alarm) {
/* 154 */     PushService pushService = new PushService();
/* 155 */     if ((alarm.getDeviceType() != null) && (alarm.getDeviceType().intValue() == 336)) {
/* 156 */       pushService.setAppId("egB7MW2o0L9It60nlxt098");
/* 157 */       pushService.setAppkey("iIgM1MTmnY8F1YDF8f9x38");
/* 158 */       pushService.setMaster("KhY2GOaBPq6Xupb7FFzBM4");
/*     */     }
/*     */ 
/* 161 */     this.boAlarmService.save(alarm);
/*     */ 
/* 163 */     List<BoUserDevice> list = this.boUserDeviceService.getListByDeviceCode(alarm.getDeviceCode());
/*     */ 
/* 165 */     for (BoUserDevice ud : list) {
/* 166 */       String CID = ud.getBoUser().getCID();
/*     */ 
/* 168 */       String nickName = ud.getNickName();
/* 169 */       System.out.println(CID + "\n" + nickName + "\n" + ud.getBoUser().getUserPhone());
/* 170 */       String title = "";
/*     */ 
/* 172 */       StringBuffer text = new StringBuffer();
/* 173 */       if ((alarm.getDeviceType() != null) && (alarm.getDeviceType().intValue() == 336)) {
/* 174 */         title = "友情提示";
/* 175 */         text.append("尊敬的客户您好");
/* 176 */         text.append("你的").append(nickName).append("设备在");
/* 177 */         text.append(GlobalMethod.formatDate(alarm.getTime(), "yy/MM/dd HH:mm:ss"));
/* 178 */         text.append(alarm.getStatusName()).append(",");
/* 179 */         text.append("请务必留意。");
/*     */       } else {
/* 181 */         title = "警报";
/* 182 */         text.append(GlobalMethod.formatDate(alarm.getTime(), "yyyy-MM-dd HH:mm:ss"));
/* 183 */         text.append("\n\r");
/* 184 */         text.append("尊敬的客户，你的").append(nickName);
/* 185 */         text.append(alarm.getStatusName());
/* 186 */         text.append(",");
/* 187 */         text.append("请及时查修");
/*     */       }
/*     */ 
/* 190 */       Integer type = ud.getBoUser().getPhoneType();
/*     */ 
/* 192 */       if ((type == null) || (type.intValue() == 0))
/* 193 */         pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*     */       else
/* 195 */         pushService.apnPush(CID, title, text.toString(), text.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void doUpdateDeviceWater(String deviceCode, int water)
/*     */   {
/* 201 */     this.boDeviceService.updateStatus(deviceCode, 1);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoProcessServiceImpl
 * JD-Core Version:    0.6.2
 */