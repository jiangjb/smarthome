/*    */ package com.smarthome.imcp.dao.model.cy;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*    */ import java.io.Serializable;
/*    */ import org.apache.struts2.json.annotations.JSON;
/*    */ 
/*    */ public class BoCrystal
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUser boUser;
/*    */   private Integer total;
/*    */   private Integer balance;
/*    */   private Integer freeze;
/*    */   private String userName;
/*    */   private String userPhone;
/*    */ 
/*    */   public BoCrystal()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoCrystal(BoUser boUser, Integer total, Integer balance, Integer freeze)
/*    */   {
/* 33 */     this.boUser = boUser;
/* 34 */     this.total = total;
/* 35 */     this.balance = balance;
/* 36 */     this.freeze = freeze;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 42 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */   @JSON(serialize=false)
/*    */   public BoUser getBoUser() {
/* 51 */     return this.boUser;
/*    */   }
/*    */ 
/*    */   public void setBoUser(BoUser boUser) {
/* 55 */     this.boUser = boUser;
/*    */   }
/*    */ 
/*    */   public Integer getTotal() {
/* 59 */     return this.total;
/*    */   }
/*    */ 
/*    */   public void setTotal(Integer total) {
/* 63 */     this.total = total;
/*    */   }
/*    */ 
/*    */   public Integer getBalance() {
/* 67 */     return this.balance;
/*    */   }
/*    */ 
/*    */   public void setBalance(Integer balance) {
/* 71 */     this.balance = balance;
/*    */   }
/*    */ 
/*    */   public Integer getFreeze() {
/* 75 */     return this.freeze;
/*    */   }
/*    */ 
/*    */   public void setFreeze(Integer freeze) {
/* 79 */     this.freeze = freeze;
/*    */   }
/*    */ 
/*    */   public String getUserName() {
/* 83 */     return this.boUser.getUserName();
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 87 */     this.userName = userName;
/*    */   }
/*    */ 
/*    */   public String getUserPhone()
/*    */   {
/* 94 */     return getBoUser().getUserPhone();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.BoCrystal
 * JD-Core Version:    0.6.2
 */