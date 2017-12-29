/*    */ package com.smarthome.imcp.dao.model.cy;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CrystalRecharge
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private Integer tradId;
/*    */   private BoUser boUser;
/*    */   private String tradeNo;
/*    */   private Double tradeMoney;
/*    */   private Integer crystal;
/*    */   private Integer oldCrystal;
/*    */   private String userName;
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 31 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 35 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Integer getTradId() {
/* 39 */     return this.tradId;
/*    */   }
/*    */ 
/*    */   public void setTradId(Integer tradId) {
/* 43 */     this.tradId = tradId;
/*    */   }
/*    */ 
/*    */   public BoUser getBoUser() {
/* 47 */     return this.boUser;
/*    */   }
/*    */ 
/*    */   public void setBoUser(BoUser boUser) {
/* 51 */     this.boUser = boUser;
/*    */   }
/*    */ 
/*    */   public String getTradeNo() {
/* 55 */     return this.tradeNo;
/*    */   }
/*    */ 
/*    */   public void setTradeNo(String tradeNo) {
/* 59 */     this.tradeNo = tradeNo;
/*    */   }
/*    */ 
/*    */   public Double getTradeMoney() {
/* 63 */     return this.tradeMoney;
/*    */   }
/*    */ 
/*    */   public void setTradeMoney(Double tradeMoney) {
/* 67 */     this.tradeMoney = tradeMoney;
/*    */   }
/*    */ 
/*    */   public Integer getCrystal() {
/* 71 */     return this.crystal;
/*    */   }
/*    */ 
/*    */   public void setCrystal(Integer crystal) {
/* 75 */     this.crystal = crystal;
/*    */   }
/*    */ 
/*    */   public Integer getOldCrystal() {
/* 79 */     return this.oldCrystal;
/*    */   }
/*    */ 
/*    */   public void setOldCrystal(Integer oldCrystal) {
/* 83 */     this.oldCrystal = oldCrystal;
/*    */   }
/*    */ 
/*    */   public String getUserName() {
/* 87 */     this.userName = this.boUser.getUserName();
/* 88 */     return this.userName;
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 92 */     this.userName = userName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.CrystalRecharge
 * JD-Core Version:    0.6.2
 */