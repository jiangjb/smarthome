/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.cy.Plan;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoDevicePlan
/*    */ {
/*    */   private int id;
/*    */   private Date startDate;
/*    */   private Date endDate;
/*    */   private Plan plan;
/*    */   private boolean initPlan;
/*    */   private BoDevice device;
/*    */   private int refillBalance;
/*    */ 
/*    */   public Date getStartDate()
/*    */   {
/* 20 */     return this.startDate;
/*    */   }
/*    */ 
/*    */   public void setStartDate(Date startDate)
/*    */   {
/* 26 */     this.startDate = startDate;
/*    */   }
/*    */ 
/*    */   public Date getEndDate()
/*    */   {
/* 32 */     return this.endDate;
/*    */   }
/*    */ 
/*    */   public void setEndDate(Date endDate)
/*    */   {
/* 38 */     this.endDate = endDate;
/*    */   }
/*    */ 
/*    */   public int getId()
/*    */   {
/* 44 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 50 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Plan getPlan()
/*    */   {
/* 56 */     return this.plan;
/*    */   }
/*    */ 
/*    */   public void setPlan(Plan plan)
/*    */   {
/* 62 */     this.plan = plan;
/*    */   }
/*    */ 
/*    */   public BoDevice getDevice()
/*    */   {
/* 68 */     return this.device;
/*    */   }
/*    */ 
/*    */   public void setDevice(BoDevice device)
/*    */   {
/* 74 */     this.device = device;
/*    */   }
/*    */ 
/*    */   public boolean isInitPlan()
/*    */   {
/* 80 */     return this.initPlan;
/*    */   }
/*    */ 
/*    */   public void setInitPlan(boolean initPlan)
/*    */   {
/* 86 */     this.initPlan = initPlan;
/*    */   }
/*    */ 
/*    */   public int getRefillBalance()
/*    */   {
/* 92 */     return this.refillBalance;
/*    */   }
/*    */ 
/*    */   public void setRefillBalance(int refillBalance)
/*    */   {
/* 98 */     this.refillBalance = refillBalance;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoDevicePlan
 * JD-Core Version:    0.6.2
 */