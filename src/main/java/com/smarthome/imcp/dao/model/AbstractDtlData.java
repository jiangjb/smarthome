/*    */ package com.smarthome.imcp.dao.model;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public abstract class AbstractDtlData
/*    */   implements Serializable
/*    */ {
/*  8 */   protected String mntSttsF = "L";
/*    */ 
/* 10 */   protected Integer mntVersionNo = Integer.valueOf(0);
/*    */ 
/* 12 */   protected String mntDelete = "N";
/*    */ 
/*    */   public String getMntSttsF() {
/* 15 */     return this.mntSttsF;
/*    */   }
/*    */ 
/*    */   public void setMntSttsF(String mntSttsF) {
/* 19 */     this.mntSttsF = mntSttsF;
/*    */   }
/*    */ 
/*    */   public Integer getMntVersionNo() {
/* 23 */     return this.mntVersionNo;
/*    */   }
/*    */ 
/*    */   public void setMntVersionNo(Integer mntVersionNo) {
/* 27 */     this.mntVersionNo = mntVersionNo;
/*    */   }
/*    */ 
/*    */   public String getMntDelete() {
/* 31 */     return this.mntDelete;
/*    */   }
/*    */ 
/*    */   public void setMntDelete(String mntDelete) {
/* 35 */     this.mntDelete = mntDelete;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.AbstractDtlData
 * JD-Core Version:    0.6.2
 */