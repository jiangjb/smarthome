/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAlarmRecord;
/*    */ import com.smarthome.imcp.dao.model.bo.BoSensor;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*    */ import com.smarthome.imcp.service.bo.BoAlarmRecordServiceIface;
/*    */ import com.smarthome.imcp.service.bo.BoSensorServiceIface;
/*    */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*    */ import com.smarthome.imcp.service.impl.push.PushService;
/*    */ import java.io.PrintStream;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.annotation.Lazy;
/*    */ 
/*    */ @Lazy(false)
/*    */ public class SensorPushUtil
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoAlarmRecordServiceIface<BoAlarmRecord, Serializable> boAlarmRecordService;
/*    */ 
/*    */   @Autowired
/*    */   private BoSensorServiceIface<BoSensor, Serializable> boSensorService;
/*    */ 
/*    */   @Autowired
/*    */   private BoUserssServiceIface<BoUsers, Serializable> boUserServicess;
/*    */ 
/*    */   public void push(String deviceCode, String deviceAddress)
/*    */     throws InterruptedException
/*    */   {
/* 24 */     String formatDate = GlobalMethod.formatDate(new Date(), "yy/MM/dd HH:mm:ss");
/* 25 */     BoSensor ssss = this.boSensorService.find(deviceCode, deviceAddress);
/* 26 */     BoUsers boUsers = this.boUserServicess.findByUserUserCode(ssss.getBoUsers().getUserCode());
/* 27 */     if (boUsers != null) {
/* 28 */       List<BoUsers> list = this.boUserServicess.getByAuthorizeUserCode(ssss.getBoUsers().getUserCode());
/* 29 */       PushService pushService = new PushService();
/* 30 */       if (boUsers.getVersionType().equals("2")) {
/* 31 */         System.err.println("爱博瑞KEY");
/* 32 */         pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/* 33 */         pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/* 34 */         pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*    */       } else {
/* 36 */         System.err.println("易联智家KEY");
/* 37 */         pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/* 38 */         pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/* 39 */         pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*    */       }
/* 41 */       for (BoUsers boUsers2 : list) {
/* 42 */         Thread.sleep(500L);
/* 43 */         System.err.println(boUsers2.getUserPhone());
/* 44 */         String title = "";
/* 45 */         String CID = boUsers2.getCid();
/* 46 */         System.err.println("() " + CID + " ()");
/* 47 */         if ((CID == null) || (CID.equals(""))) {
/* 48 */           System.err.println("CID为空推送不到信息");
/*    */         } else {
/* 50 */           StringBuffer text = new StringBuffer();
/* 51 */           System.err.println(ssss.getType());
/* 52 */           Integer valueOf = Integer.valueOf(ssss.getType());
/* 53 */           switch (valueOf.intValue()) {
/*    */           case 1:
/* 55 */             System.err.println("*****>> " + ssss.getBoUsers().getUserCode());
/* 56 */             System.err.println("*****<< " + boUsers.getVersionType());
/*    */ 
/* 58 */             if (boUsers2.getVersionType().equals("2")) {
/* 59 */               System.err.println("爱博瑞推送内容");
/* 60 */               title = "爱波瑞科技";
/* 61 */               if (!ssss.getPushContent().equals("")) {
/* 62 */                 text.append(ssss.getPushContent());
/*    */               } else {
/* 64 */                 text.append("尊敬的客户");
/* 65 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/* 66 */                 text.append(formatDate);
/* 67 */                 text.append(" 请务必留意。");
/*    */               }
/*    */             }
/*    */             else {
/* 71 */               System.err.println("易联智家推送内容");
/* 72 */               title = "掌上智家";
/* 73 */               if (!ssss.getPushContent().equals("")) {
/* 74 */                 text.append(ssss.getPushContent());
/*    */               } else {
/* 76 */                 text.append("尊敬的客户");
/* 77 */                 text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/* 78 */                 text.append(formatDate);
/* 79 */                 text.append(" 请务必留意。");
/*    */               }
/*    */             }
/*    */ 
/* 83 */             Integer type = boUsers2.getPhoneType();
/*    */ 
/* 85 */             if ((type == null) || (type.intValue() == 0)) {
/* 86 */               pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*    */ 
/* 88 */               System.err.println(text.toString());
/*    */             } else {
/* 90 */               pushService.apnPush(CID, title, text.toString(), text.toString());
/*    */ 
/* 92 */               System.err.println(text.toString());
/*    */             }
/*    */ 
/*    */             break;
/*    */           }
/*    */ 
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 103 */       String title = "";
/* 104 */       String CID = boUsers.getCid();
/* 105 */       System.err.println("() " + CID + " ()");
/* 106 */       if ((CID == null) || (CID.equals(""))) {
/* 107 */         System.err.println("CID为空推送不到信息");
/*    */       } else {
/* 109 */         StringBuffer text = new StringBuffer();
/* 110 */         System.err.println(ssss.getType());
/* 111 */         Integer valueOf = Integer.valueOf(ssss.getType());
/* 112 */         switch (valueOf.intValue()) {
/*    */         case 1:
/* 114 */           if (boUsers.getVersionType().equals("2")) {
/* 115 */             System.err.println("爱博瑞推送内容");
/* 116 */             title = "爱波瑞科技";
/* 117 */             if (!ssss.getPushContent().equals("")) {
/* 118 */               text.append(ssss.getPushContent());
/*    */             } else {
/* 120 */               text.append("尊敬的客户");
/* 121 */               text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/* 122 */               text.append(formatDate);
/* 123 */               text.append(" 请务必留意。");
/*    */             }
/*    */           } else {
/* 126 */             System.err.println("易联智家推送内容");
/* 127 */             title = "掌上智家";
/* 128 */             if (!ssss.getPushContent().equals("")) {
/* 129 */               text.append(ssss.getPushContent());
/*    */             } else {
/* 131 */               text.append("尊敬的客户");
/* 132 */               text.append("您的 < " + ssss.getNickName() + " > " + " 传感器正在被触发 ");
/* 133 */               text.append(formatDate);
/* 134 */               text.append(" 请务必留意。");
/*    */             }
/*    */           }
/*    */ 
/* 138 */           Integer type = boUsers.getPhoneType();
/*    */ 
/* 140 */           if ((type == null) || (type.intValue() == 0)) {
/* 141 */             pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*    */ 
/* 143 */             System.err.println(text.toString());
/*    */           } else {
/* 145 */             pushService.apnPush(CID, title, text.toString(), text.toString());
/*    */ 
/* 147 */             System.err.println(text.toString());
/*    */           }
/* 149 */           BoAlarmRecord boAlarmRecord = new BoAlarmRecord();
/* 150 */           boAlarmRecord.setDeviceCode(deviceCode);
/* 151 */           boAlarmRecord.setUserCode(ssss.getBoUsers().getUserCode());
/* 152 */           boAlarmRecord.setSebsorName(ssss.getNickName());
/* 153 */           Integer phoneType = ssss.getBoUsers().getPhoneType();
/*    */           String alarmPhoneType;
///*    */           String alarmPhoneType;
/* 155 */           if (phoneType.intValue() == 0)
/* 156 */             alarmPhoneType = "安卓";
/*    */           else {
/* 158 */             alarmPhoneType = "苹果";
/*    */           }
/* 160 */           boAlarmRecord.setAlarmPhoneType(alarmPhoneType);
/*    */ 
/* 162 */           boAlarmRecord.setReportTime(formatDate);
/* 163 */           Long timestamp = Long.valueOf(172800L);
/* 164 */           Long timestamps = Long.valueOf((int)(System.currentTimeMillis() / 1000L) + timestamp.longValue());
/* 165 */           boAlarmRecord.setReportTimestamp(timestamps+"");
/* 166 */           boAlarmRecord.setReportDate(new Date());
/* 167 */           this.boAlarmRecordService.save(boAlarmRecord);
/* 168 */           break;
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.SensorPushUtil
 * JD-Core Version:    0.6.2
 */