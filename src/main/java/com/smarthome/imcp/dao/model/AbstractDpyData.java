/*    */ package com.smarthome.imcp.dao.model;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public abstract class AbstractDpyData extends AbstractData
/*    */ {
/*    */   private Integer mntDeployId;
/*    */   private String mntDeployName;
/*    */   private Date mntDeployDate;
/*    */ 
/*    */   public Integer getMntDeployId()
/*    */   {
/* 14 */     return this.mntDeployId;
/*    */   }
/*    */ 
/*    */   public void setMntDeployId(Integer mntDeployId) {
/* 18 */     this.mntDeployId = mntDeployId;
/*    */   }
/*    */ 
/*    */   public String getMntDeployName() {
/* 22 */     return this.mntDeployName;
/*    */   }
/*    */ 
/*    */   public void setMntDeployName(String mntDeployName) {
/* 26 */     this.mntDeployName = mntDeployName;
/*    */   }
/*    */ 
/*    */   public Date getMntDeployDate() {
/* 30 */     return this.mntDeployDate;
/*    */   }
/*    */ 
/*    */   public void setMntDeployDate(Date mntDeployDate) {
/* 34 */     this.mntDeployDate = mntDeployDate;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.AbstractDpyData
 * JD-Core Version:    0.6.2
 */