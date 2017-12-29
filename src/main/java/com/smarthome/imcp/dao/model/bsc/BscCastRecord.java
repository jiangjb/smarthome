/*    */ package com.smarthome.imcp.dao.model.bsc;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysUser;
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BscCastRecord
/*    */   implements Serializable
/*    */ {
/*    */   private Integer recordId;
/*    */   private BscCast bscCast;
/*    */   private SysUser sysUser;
/*    */   private Integer amount;
/*    */   private Date castDate;
/*    */ 
/*    */   public BscCastRecord()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BscCastRecord(Integer recordId)
/*    */   {
/* 29 */     this.recordId = recordId;
/*    */   }
/*    */ 
/*    */   public BscCastRecord(Integer recordId, BscCast bscCast, SysUser sysUser, Integer amount, Date castDate)
/*    */   {
/* 35 */     this.recordId = recordId;
/* 36 */     this.bscCast = bscCast;
/* 37 */     this.sysUser = sysUser;
/* 38 */     this.amount = amount;
/* 39 */     this.castDate = castDate;
/*    */   }
/*    */ 
/*    */   public Integer getRecordId()
/*    */   {
/* 45 */     return this.recordId;
/*    */   }
/*    */ 
/*    */   public void setRecordId(Integer recordId) {
/* 49 */     this.recordId = recordId;
/*    */   }
/*    */ 
/*    */   public BscCast getBscCast() {
/* 53 */     return this.bscCast;
/*    */   }
/*    */ 
/*    */   public void setBscCast(BscCast bscCast) {
/* 57 */     this.bscCast = bscCast;
/*    */   }
/*    */ 
/*    */   public SysUser getSysUser() {
/* 61 */     return this.sysUser;
/*    */   }
/*    */ 
/*    */   public void setSysUser(SysUser sysUser) {
/* 65 */     this.sysUser = sysUser;
/*    */   }
/*    */ 
/*    */   public Integer getAmount() {
/* 69 */     return this.amount;
/*    */   }
/*    */ 
/*    */   public void setAmount(Integer amount) {
/* 73 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */   public Date getCastDate() {
/* 77 */     return this.castDate;
/*    */   }
/*    */ 
/*    */   public void setCastDate(Date castDate) {
/* 81 */     this.castDate = castDate;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bsc.BscCastRecord
 * JD-Core Version:    0.6.2
 */