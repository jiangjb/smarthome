/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class BoHost
/*    */   implements Serializable
/*    */ {
/*    */   private Integer hostId;
/*    */   private BoUsers boUsers;
/*    */   private String hostCode;
/*    */   private Date addDate;
/* 19 */   private Set boSimplifies = new HashSet(0);
/*    */ 
/*    */   public BoHost()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoHost(BoUsers boUsers, String hostCode, Date addDate, Set boSimplifies)
/*    */   {
/* 30 */     this.boUsers = boUsers;
/* 31 */     this.hostCode = hostCode;
/* 32 */     this.addDate = addDate;
/* 33 */     this.boSimplifies = boSimplifies;
/*    */   }
/*    */ 
/*    */   public Integer getHostId()
/*    */   {
/* 39 */     return this.hostId;
/*    */   }
/*    */ 
/*    */   public void setHostId(Integer hostId) {
/* 43 */     this.hostId = hostId;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 47 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 51 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public String getHostCode() {
/* 55 */     return this.hostCode;
/*    */   }
/*    */ 
/*    */   public void setHostCode(String hostCode) {
/* 59 */     this.hostCode = hostCode;
/*    */   }
/*    */ 
/*    */   public Date getAddDate() {
/* 63 */     return this.addDate;
/*    */   }
/*    */ 
/*    */   public void setAddDate(Date addDate) {
/* 67 */     this.addDate = addDate;
/*    */   }
/*    */ 
/*    */   public Set getBoSimplifies() {
/* 71 */     return this.boSimplifies;
/*    */   }
/*    */ 
/*    */   public void setBoSimplifies(Set boSimplifies) {
/* 75 */     this.boSimplifies = boSimplifies;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoHost
 * JD-Core Version:    0.6.2
 */