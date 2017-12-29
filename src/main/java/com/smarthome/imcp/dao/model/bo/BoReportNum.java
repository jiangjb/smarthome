/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoReportNum
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String deviceCode;
/*    */   private Integer frameType;
/*    */   private Integer deviceType;
/*    */   private Integer reportNum;
/*    */   private Date updateTime;
/*    */ 
/*    */   public BoReportNum()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoReportNum(String deviceCode, Integer frameType, Integer deviceType, Integer reportNum, Date updateTime)
/*    */   {
/* 29 */     this.deviceCode = deviceCode;
/* 30 */     this.frameType = frameType;
/* 31 */     this.deviceType = deviceType;
/* 32 */     this.reportNum = reportNum;
/* 33 */     this.updateTime = updateTime;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 39 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 43 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getDeviceCode() {
/* 47 */     return this.deviceCode;
/*    */   }
/*    */ 
/*    */   public void setDeviceCode(String deviceCode) {
/* 51 */     this.deviceCode = deviceCode;
/*    */   }
/*    */ 
/*    */   public Integer getFrameType() {
/* 55 */     return this.frameType;
/*    */   }
/*    */ 
/*    */   public void setFrameType(Integer frameType) {
/* 59 */     this.frameType = frameType;
/*    */   }
/*    */ 
/*    */   public Integer getDeviceType() {
/* 63 */     return this.deviceType;
/*    */   }
/*    */ 
/*    */   public void setDeviceType(Integer deviceType) {
/* 67 */     this.deviceType = deviceType;
/*    */   }
/*    */ 
/*    */   public Integer getReportNum() {
/* 71 */     return this.reportNum;
/*    */   }
/*    */ 
/*    */   public void setReportNum(Integer reportNum) {
/* 75 */     this.reportNum = reportNum;
/*    */   }
/*    */ 
/*    */   public Date getUpdateTime() {
/* 79 */     return this.updateTime;
/*    */   }
/*    */ 
/*    */   public void setUpdateTime(Date updateTime) {
/* 83 */     this.updateTime = updateTime;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoReportNum
 * JD-Core Version:    0.6.2
 */