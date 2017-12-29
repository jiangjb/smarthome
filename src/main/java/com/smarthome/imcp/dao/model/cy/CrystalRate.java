/*    */ package com.smarthome.imcp.dao.model.cy;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class CrystalRate
/*    */   implements Serializable
/*    */ {
/*    */   private int id;
/*    */   private int refillAmount;
/*    */   private int crystalQuantity;
/*    */ 
/*    */   public CrystalRate()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CrystalRate(int id, int refillAmount, int crystalQuantity)
/*    */   {
/* 14 */     setId(id);
/* 15 */     setRefillAmount(refillAmount);
/* 16 */     setCrystalQuantity(crystalQuantity);
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 20 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 24 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public int getRefillAmount() {
/* 28 */     return this.refillAmount;
/*    */   }
/*    */ 
/*    */   public void setRefillAmount(int refillAmount) {
/* 32 */     this.refillAmount = refillAmount;
/*    */   }
/*    */ 
/*    */   public int getCrystalQuantity() {
/* 36 */     return this.crystalQuantity;
/*    */   }
/*    */ 
/*    */   public void setCrystalQuantity(int crystalQuantity) {
/* 40 */     this.crystalQuantity = crystalQuantity;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.CrystalRate
 * JD-Core Version:    0.6.2
 */