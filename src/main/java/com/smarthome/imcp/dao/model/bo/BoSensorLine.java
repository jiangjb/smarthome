/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoSensorLine
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private BoDevice boDevice;
/*    */   private Integer infraredLine;
/*    */ 
/*    */   public BoSensorLine()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoSensorLine(BoUsers boUsers, BoDevice boDevice, Integer infraredLine)
/*    */   {
/* 24 */     this.boUsers = boUsers;
/* 25 */     this.boDevice = boDevice;
/* 26 */     this.infraredLine = infraredLine;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 32 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 36 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 40 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 44 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice() {
/* 48 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 52 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public Integer getInfraredLine() {
/* 56 */     return this.infraredLine;
/*    */   }
/*    */ 
/*    */   public void setInfraredLine(Integer infraredLine) {
/* 60 */     this.infraredLine = infraredLine;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoSensorLine
 * JD-Core Version:    0.6.2
 */