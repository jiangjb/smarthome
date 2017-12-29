/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoDevicehostinfo
/*    */   implements Serializable
/*    */ {
/*    */   private Integer deviceId;
/*    */   private String deviceCode;
/*    */   private String deviceName;
/*    */   private String deviceInfo;
/*    */   private Date addDate;
/*    */ 
/*    */   public BoDevicehostinfo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoDevicehostinfo(String deviceCode, String deviceName, String deviceInfo, Date addDate)
/*    */   {
/* 28 */     this.deviceCode = deviceCode;
/* 29 */     this.deviceName = deviceName;
/* 30 */     this.deviceInfo = deviceInfo;
/* 31 */     this.addDate = addDate;
/*    */   }
/*    */ 
/*    */   public Integer getDeviceId()
/*    */   {
/* 37 */     return this.deviceId;
/*    */   }
/*    */ 
/*    */   public void setDeviceId(Integer deviceId) {
/* 41 */     this.deviceId = deviceId;
/*    */   }
/*    */ 
/*    */   public String getDeviceCode() {
/* 45 */     return this.deviceCode;
/*    */   }
/*    */ 
/*    */   public void setDeviceCode(String deviceCode) {
/* 49 */     this.deviceCode = deviceCode;
/*    */   }
/*    */ 
/*    */   public String getDeviceName() {
/* 53 */     return this.deviceName;
/*    */   }
/*    */ 
/*    */   public void setDeviceName(String deviceName) {
/* 57 */     this.deviceName = deviceName;
/*    */   }
/*    */ 
/*    */   public String getDeviceInfo() {
/* 61 */     return this.deviceInfo;
/*    */   }
/*    */ 
/*    */   public void setDeviceInfo(String deviceInfo) {
/* 65 */     this.deviceInfo = deviceInfo;
/*    */   }
/*    */ 
/*    */   public Date getAddDate() {
/* 69 */     return this.addDate;
/*    */   }
/*    */ 
/*    */   public void setAddDate(Date addDate) {
/* 73 */     this.addDate = addDate;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoDevicehostinfo
 * JD-Core Version:    0.6.2
 */