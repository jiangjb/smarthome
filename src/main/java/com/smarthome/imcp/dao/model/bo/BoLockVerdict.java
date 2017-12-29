/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoLockVerdict
/*    */   implements Serializable
/*    */ {
/*    */   private String lockverdictId;
/*    */   private BoDevice boDevice;
/*    */   private String lockAddress;
/*    */   private String status;
/*    */   private String lockTimes;
/*    */   private String unlockTimes;
/*    */ 
/*    */   public BoLockVerdict()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoLockVerdict(String lockverdictId, BoDevice boDevice)
/*    */   {
/* 28 */     this.lockverdictId = lockverdictId;
/*    */ 
/* 30 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public BoLockVerdict(String lockverdictId, BoDevice boDevice, String lockAddress, String status, String lockTimes, String unlockTimes)
/*    */   {
/* 37 */     this.lockverdictId = lockverdictId;
/*    */ 
/* 39 */     this.boDevice = boDevice;
/* 40 */     this.lockAddress = lockAddress;
/* 41 */     this.status = status;
/* 42 */     this.lockTimes = lockTimes;
/* 43 */     this.unlockTimes = unlockTimes;
/*    */   }
/*    */ 
/*    */   public String getLockverdictId()
/*    */   {
/* 49 */     return this.lockverdictId;
/*    */   }
/*    */ 
/*    */   public void setLockverdictId(String lockverdictId) {
/* 53 */     this.lockverdictId = lockverdictId;
/*    */   }
/*    */ 
/*    */   public BoDevice getBoDevice()
/*    */   {
/* 59 */     return this.boDevice;
/*    */   }
/*    */ 
/*    */   public void setBoDevice(BoDevice boDevice) {
/* 63 */     this.boDevice = boDevice;
/*    */   }
/*    */ 
/*    */   public String getLockAddress() {
/* 67 */     return this.lockAddress;
/*    */   }
/*    */ 
/*    */   public void setLockAddress(String lockAddress) {
/* 71 */     this.lockAddress = lockAddress;
/*    */   }
/*    */ 
/*    */   public String getStatus() {
/* 75 */     return this.status;
/*    */   }
/*    */ 
/*    */   public void setStatus(String status) {
/* 79 */     this.status = status;
/*    */   }
/*    */ 
/*    */   public String getLockTimes() {
/* 83 */     return this.lockTimes;
/*    */   }
/*    */ 
/*    */   public void setLockTimes(String lockTimes) {
/* 87 */     this.lockTimes = lockTimes;
/*    */   }
/*    */ 
/*    */   public String getUnlockTimes() {
/* 91 */     return this.unlockTimes;
/*    */   }
/*    */ 
/*    */   public void setUnlockTimes(String unlockTimes) {
/* 95 */     this.unlockTimes = unlockTimes;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoLockVerdict
 * JD-Core Version:    0.6.2
 */