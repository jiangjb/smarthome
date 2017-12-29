/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoShoppingCart
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoGoods boGoods;
/*    */   private BoUsers boUsers;
/*    */   private Integer purchaseQuantity;
/*    */   private Date createTime;
/*    */ 
/*    */   public BoShoppingCart()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoShoppingCart(BoGoods boGoods, BoUsers boUsers, Integer purchaseQuantity, Date createTime)
/*    */   {
/* 27 */     this.boGoods = boGoods;
/* 28 */     this.boUsers = boUsers;
/* 29 */     this.purchaseQuantity = purchaseQuantity;
/* 30 */     this.createTime = createTime;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 36 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 40 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoGoods getBoGoods() {
/* 44 */     return this.boGoods;
/*    */   }
/*    */ 
/*    */   public void setBoGoods(BoGoods boGoods) {
/* 48 */     this.boGoods = boGoods;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 52 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 56 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public Integer getPurchaseQuantity() {
/* 60 */     return this.purchaseQuantity;
/*    */   }
/*    */ 
/*    */   public void setPurchaseQuantity(Integer purchaseQuantity) {
/* 64 */     this.purchaseQuantity = purchaseQuantity;
/*    */   }
/*    */ 
/*    */   public Date getCreateTime() {
/* 68 */     return this.createTime;
/*    */   }
/*    */ 
/*    */   public void setCreateTime(Date createTime) {
/* 72 */     this.createTime = createTime;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoShoppingCart
 * JD-Core Version:    0.6.2
 */