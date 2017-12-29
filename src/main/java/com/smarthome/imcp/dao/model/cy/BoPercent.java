/*    */ package com.smarthome.imcp.dao.model.cy;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoPercent
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private Double money;
/*    */   private Integer crystals;
/*    */   private Integer dayCrystal;
/*    */ 
/*    */   public BoPercent()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoPercent(Double money, Integer crystals, Integer dayCrystal)
/*    */   {
/* 24 */     this.money = money;
/* 25 */     this.crystals = crystals;
/* 26 */     this.dayCrystal = dayCrystal;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 32 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 36 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Double getMoney() {
/* 40 */     return this.money;
/*    */   }
/*    */ 
/*    */   public void setMoney(Double money) {
/* 44 */     this.money = money;
/*    */   }
/*    */ 
/*    */   public Integer getCrystals() {
/* 48 */     return this.crystals;
/*    */   }
/*    */ 
/*    */   public void setCrystals(Integer crystals) {
/* 52 */     this.crystals = crystals;
/*    */   }
/*    */ 
/*    */   public Integer getDayCrystal() {
/* 56 */     return this.dayCrystal;
/*    */   }
/*    */ 
/*    */   public void setDayCrystal(Integer dayCrystal) {
/* 60 */     this.dayCrystal = dayCrystal;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.BoPercent
 * JD-Core Version:    0.6.2
 */