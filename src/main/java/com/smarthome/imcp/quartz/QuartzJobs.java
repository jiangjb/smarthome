/*     */ package com.smarthome.imcp.quartz;
/*     */ 
/*     */ import com.smarthome.dock.server.helper.PacketProcessHelper;
		  import com.smarthome.dock.server.packets.in.KeepAlivePacket;
		  import com.smarthome.dock.server.support.PacketProcessor;
/*     */ import com.smarthome.dock.server.util.SensorUtil;
/*     */ import com.smarthome.dock.server.util.StaticUtil;
		  import com.smarthome.imcp.action.xing.XingUserAction;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
/*     */ import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
/*     */ import com.smarthome.imcp.dao.model.bo.BoModel;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoHostDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoLockPasswordManageServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoLockVerdictServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoModelServiceIface;
/*     */ import com.smarthome.imcp.util.AES;
/*     */ import com.smarthome.imcp.util.SimulateHTTPRequestUtil;
/*     */ import com.smarthome.imcp.util.StaticUtils;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
		  import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.commons.lang3.StringUtils;
		  import org.apache.struts2.ServletActionContext;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.annotation.Lazy;
/*     */ import org.springframework.scheduling.annotation.Scheduled;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ @Lazy(false)
/*     */ public class QuartzJobs
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoHostDeviceServiceIface<BoHostDevice, Serializable> boHostDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoModelServiceIface<BoModel, Serializable> boModelService;
/*     */ 
/*     */   @Autowired
/*     */   private BoLockPasswordManageServiceIface<BoLockPasswordManage, Serializable> boLockPasswordManageService;
/*     */ 
/*     */   @Autowired
/*     */   private BoLockVerdictServiceIface<BoLockVerdict, Serializable> boLockVerdictService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private PacketProcessHelper packetProcessHelper;

			@Autowired
			private PacketProcessor packetProcessor;//new


/*  84 */   private static Map<String, Integer> user_num = new HashMap();
/*     */ 
/*     */   public PacketProcessHelper getPacketProcessHelper()
/*     */   {
/*  77 */     return this.packetProcessHelper;
/*     */   }
/*     */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper) {
/*  80 */     this.packetProcessHelper = packetProcessHelper;
/*     */   }

/*     */   public void packNum(String userCode)
/*     */   {//important
/*  90 */     if (user_num.get(userCode) == null)
/*  91 */       user_num.put(userCode, Integer.valueOf(0));
/*     */     else
/*  93 */       user_num.put(userCode, Integer.valueOf(((Integer)user_num.get(userCode)).intValue() == 255 ? 0 : ((Integer)user_num.get(userCode)).intValue() + 1));
/*     */   }

/*     */   @Scheduled(cron="0 0/1 * * * ?")
/*     */   public void setUserMode()
/*     */   {
/* 101 */     List by = this.boModelService.getBy();
			  System.out.println("QuartJobs   setUserMode by:"+by);
/* 102 */     for (int i = 0; i < by.size(); i++) {
/* 103 */       BoModel boModel = (BoModel)by.get(i);
/*     */ 
/* 105 */       String weekOfDate = SensorUtil.getWeekOfDate(new Date());
/*     */       String week;

/* 107 */       if ((boModel.getWeek() == null) || (boModel.getWeek().equals("")))
/* 108 */         week = "星期日,";
/*     */       else {
/* 110 */         week = boModel.getWeek();
/*     */       }
/* 112 */       boolean indexFromArr = SensorUtil.getIndexFromArr(weekOfDate, week);
/* 113 */       if (indexFromArr) {
/* 114 */         SimpleDateFormat dateFormater = new SimpleDateFormat("HH:mm");
/* 115 */         String format = dateFormater.format(new Date());
/* 116 */         System.err.println(format);
/* 117 */         List bys = this.boModelService.getBys(boModel.getWeek(), format);
//				  System.out.println("QuartzJobs bys===="+bys);//[]
/* 118 */         if (bys.size() <= 0)
/* 119 */           System.err.println("没有");
/*     */         else
/* 121 */           for (int j = 0; j < bys.size(); j++) {
/* 122 */             BoModel boModel2 = (BoModel)bys.get(j);
					  System.out.println("QuartzJobs boModel2.getModelId()==="+boModel2.getModelId());
/* 123 */             System.err.println(boModel2.getModelId());
/* 124 */             System.err.println(boModel2.getBoUsers().getUserCode());
/* 125 */             SimulateHTTPRequestUtil s = new SimulateHTTPRequestUtil();
///* 126 */             s.sendGet("http://127.0.0.1:8080/smarthome.IMCPlatform/xingUser/commandmodel.action?modelId=" + boModel2.getModelId(), boModel2.getBoUsers().getUserCode());
					  s.sendGet("http://120.77.250.17/smarthomeMavenWebProject/xingUser/commandmodel.action?modelId=" + boModel2.getModelId(), boModel2.getBoUsers().getUserCode());
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0 0/1 * * * ?")
/*     */   public void LockPassword()
/*     */     throws ParseException
/*     */   {
/* 140 */     List lock = this.boLockPasswordManageService.getLock(Integer.valueOf(65535));
			  System.out.println("lock.size() :"+lock.size()+"如果不是0则后面会有相应的操作");
/* 141 */     Date currentTime = new Date();
/* 142 */     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
/* 143 */     String dateString = formatter.format(currentTime);
/* 144 */     System.err.println(dateString);
/* 145 */     for (int i = 0; i < lock.size(); i++) {
/* 146 */       BoLockPasswordManage lockPassManage = (BoLockPasswordManage)lock.get(i);
/* 147 */       if (dateString.equals(lockPassManage.getStartTime())) {
/* 148 */         packNum(lockPassManage.getBoUsers().getUserCode());
/* 149 */         Date datesEndTime = formatter.parse(lockPassManage.getStartTime());
/* 150 */         long appEndTime = datesEndTime.getTime();
/* 151 */         long interval = (appEndTime - currentTime.getTime()) / 1000L;
/* 152 */         String SetString = "ZIGBEE_LOCK-SET-" + user_num.get(lockPassManage.getBoUsers().getUserCode()) + "," + lockPassManage.getLockAddress() + "," + lockPassManage.getLockType() + "," + lockPassManage.getLockOfTimes() + "," + AES.decrypt(lockPassManage.getAdminPwd()) + "," + AES.decrypt(lockPassManage.getLockPwd()) + "," + interval;
/* 153 */         byte[] Set = SetString.getBytes();
/* 154 */         System.err.println("Set :"+new String(Set));
/* 155 */         this.packetProcessHelper.processSendDData(lockPassManage.getBoDevice().getDeviceCode(), Set);
					
/*     */       } else {
/* 157 */         System.err.println("设置不是当前");
/*     */       }
/*     */ 
/* 160 */       if (dateString.equals(lockPassManage.getEndTime())) {
/* 161 */         packNum(lockPassManage.getBoUsers().getUserCode());
/* 162 */         String DeleteString = "ZIGBEE_LOCK-DELETE-" + user_num.get(lockPassManage.getBoUsers().getUserCode()) + "," + lockPassManage.getLockAddress() + "," + lockPassManage.getLockType() + "," + AES.decrypt(lockPassManage.getAdminPwd());
/* 163 */         byte[] Delete = DeleteString.getBytes();
/* 164 */         System.err.println(new String(Delete));
/* 165 */         this.packetProcessHelper.processSendDData(lockPassManage.getBoDevice().getDeviceCode(), Delete);
/* 166 */         StaticUtil.PWDDELETE.put(lockPassManage.getBoDevice().getDeviceCode() + "_" + lockPassManage.getLockAddress(), lockPassManage.getBoUsers().getUserCode());
/* 167 */         BoLockPasswordManage passwordManage = this.boLockPasswordManageService.findBylockAddress(lockPassManage.getBoUsers().getUserCode(), lockPassManage.getLockAddress(), Integer.valueOf(65535));
/* 168 */         if (passwordManage != null)
/* 169 */           this.boLockPasswordManageService.delete(passwordManage);
/*     */       }
/*     */       else {
/* 172 */         System.err.println("删除不是当前");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void lock() {
/* 179 */     List<BoLockVerdict> all = this.boLockVerdictService.getAll();
/* 180 */     Long lockTime = Long.valueOf((int)(System.currentTimeMillis() / 1000L));
/* 181 */     for (BoLockVerdict boLockVerdict : all)
/* 182 */       if (lockTime.longValue() > Long.valueOf(boLockVerdict.getUnlockTimes()).longValue()) {
/* 183 */         StaticUtils.locks.put(boLockVerdict.getLockAddress(), Integer.valueOf(0));
/* 184 */         boLockVerdict.setStatus("0");
/* 185 */         boLockVerdict.setLockTimes("");
/* 186 */         boLockVerdict.setUnlockTimes("");
/* 187 */         this.boLockVerdictService.update(boLockVerdict);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void mobileCode()
/*     */   {
/* 200 */     Iterator iterator = StaticUtil.msg_code.keySet().iterator();
/* 201 */     long timeout = 60000L;
/* 202 */     while (iterator.hasNext()) {
/* 203 */       String key = (String)iterator.next();
/* 204 */       String val = StaticUtil.msg_code.get(key).toString();
/* 205 */       if (!StringUtils.isEmpty(val))
/*     */       {
/* 207 */         String[] value = val.split(",");
/* 208 */         if (new Date().getTime() - Long.parseLong(value[1]) > timeout)
/* 209 */           iterator.remove();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void emailCode()
/*     */   {
/* 220 */     Iterator iterator = StaticUtil.msg_email_code.keySet().iterator();
/* 221 */     long timeout = 600000L;
/* 222 */     while (iterator.hasNext()) {
/* 223 */       String key = (String)iterator.next();
/* 224 */       String val = StaticUtil.msg_email_code.get(key).toString();
/* 225 */       if (!StringUtils.isEmpty(val))
/*     */       {
/* 227 */         String[] value = val.split(",");
/* 228 */         if (new Date().getTime() - Long.parseLong(value[1]) > timeout)
/* 229 */           iterator.remove();
/*     */       }
/*     */     }
/*     */   }

/*     */   @Scheduled(cron="0/4 * * * * ?")    //每隔4秒钟触发
/*     */   public void deviceS()
/*     */     throws ParseException
/*     */   {
//				System.out.println("tomcat加载完后，每隔4秒钟触发更新设备hostStatus状态");
				//new add 更新设备的hostStatus状态
				List<BoDevice> devicesList = this.boDeviceService.getAllHostDevices();
//				this.boDeviceService.updateStatus(0);
				for(BoDevice boDevice:devicesList) {
					if(boDevice.getStatus() == 1) {
						String deviceCode=boDevice.getDeviceCode();
						this.boDeviceService.updateStatus(deviceCode,1);
					}
				}
/*     */   }


			@Scheduled(cron="0/1 * * * * ?")    //每隔1秒钟触发
/*     */   public void deviceStatus() throws ParseException
/*     */   {
//				System.out.println("tomcat加载完后，每隔1秒钟触发更新主机设备的在线离线状态");
//				XingUserAction xA=new XingUserAction();
//				try {	
//					HttpServletRequest request = ServletActionContext.getRequest();
//					if(request != null) {
//						xA.getAllHost();	
//					}
//				} catch (Exception e) {
//					
//				}	
/*     */   }
/*     */ 
/*     */   @Scheduled(cron="0/1 * * * * ?")
/*     */   public void sds()
/*     */   {
/* 248 */     List<BoHostDevice> allList = this.boHostDeviceService.getAllList();
/* 249 */     for (BoHostDevice boHostDevice : allList)
/* 250 */       if (boHostDevice.getBoDevice() == null)
/* 251 */         this.boHostDeviceService.delete(boHostDevice);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.quartz.QuartzJobs
 * JD-Core Version:    0.6.2
 * new smarthomeMavenWebProject
 */