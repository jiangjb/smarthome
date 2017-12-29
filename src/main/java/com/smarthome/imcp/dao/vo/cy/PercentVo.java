/*    */ package com.smarthome.imcp.dao.vo.cy;
/*    */ 
/*    */ public class PercentVo
/*    */ {
/*    */   private int id;
/*    */   private double refillAmount;
/*    */   private int crystalQuantity;
/*    */ 
/*    */   public PercentVo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public PercentVo(int id, int refillAmount, int crystalQuantity)
/*    */   {
/* 13 */     setId(id);
/* 14 */     setRefillAmount(refillAmount);
/* 15 */     setCrystalQuantity(crystalQuantity);
/*    */   }
/*    */ 
/*    */   public int getId() {
/* 19 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id) {
/* 23 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public double getRefillAmount() {
/* 27 */     return this.refillAmount;
/*    */   }
/*    */ 
/*    */   public void setRefillAmount(double refillAmount) {
/* 31 */     this.refillAmount = refillAmount;
/*    */   }
/*    */ 
/*    */   public int getCrystalQuantity() {
/* 35 */     return this.crystalQuantity;
/*    */   }
/*    */ 
/*    */   public void setCrystalQuantity(int crystalQuantity) {
/* 39 */     this.crystalQuantity = crystalQuantity;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.cy.PercentVo
 * JD-Core Version:    0.6.2
 */