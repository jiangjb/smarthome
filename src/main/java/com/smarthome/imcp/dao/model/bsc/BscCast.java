/*    */ package com.smarthome.imcp.dao.model.bsc;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class BscCast
/*    */   implements Serializable
/*    */ {
/*    */   private Integer castId;
/*    */   private String castName;
/*    */   private Integer totalAmount;
/*    */   private Integer castAmount;
/* 18 */   private Set bscCastRecords = new HashSet(0);
/*    */ 
/*    */   public BscCast()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BscCast(Integer castId)
/*    */   {
/* 28 */     this.castId = castId;
/*    */   }
/*    */ 
/*    */   public BscCast(Integer castId, String castName, Integer totalAmount, Integer castAmount, Set bscCastRecords)
/*    */   {
/* 34 */     this.castId = castId;
/* 35 */     this.castName = castName;
/* 36 */     this.totalAmount = totalAmount;
/* 37 */     this.castAmount = castAmount;
/* 38 */     this.bscCastRecords = bscCastRecords;
/*    */   }
/*    */ 
/*    */   public Integer getCastId()
/*    */   {
/* 44 */     return this.castId;
/*    */   }
/*    */ 
/*    */   public void setCastId(Integer castId) {
/* 48 */     this.castId = castId;
/*    */   }
/*    */ 
/*    */   public String getCastName() {
/* 52 */     return this.castName;
/*    */   }
/*    */ 
/*    */   public void setCastName(String castName) {
/* 56 */     this.castName = castName;
/*    */   }
/*    */ 
/*    */   public Integer getTotalAmount() {
/* 60 */     return this.totalAmount;
/*    */   }
/*    */ 
/*    */   public void setTotalAmount(Integer totalAmount) {
/* 64 */     this.totalAmount = totalAmount;
/*    */   }
/*    */ 
/*    */   public Integer getCastAmount() {
/* 68 */     return this.castAmount;
/*    */   }
/*    */ 
/*    */   public void setCastAmount(Integer castAmount) {
/* 72 */     this.castAmount = castAmount;
/*    */   }
/*    */ 
/*    */   public Set getBscCastRecords() {
/* 76 */     return this.bscCastRecords;
/*    */   }
/*    */ 
/*    */   public void setBscCastRecords(Set bscCastRecords) {
/* 80 */     this.bscCastRecords = bscCastRecords;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bsc.BscCast
 * JD-Core Version:    0.6.2
 */