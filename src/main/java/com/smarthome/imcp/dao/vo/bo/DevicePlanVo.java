/*    */ package com.smarthome.imcp.dao.vo.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.vo.cy.PlanVo;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class DevicePlanVo
/*    */ {
/*    */   private int id;
/*    */   private Date startDate;
/*    */   private Date endDate;
/*    */   private String stage;
/*    */   private PlanVo plan;
/*    */ 
/*    */   public Date getStartDate()
/*    */   {
/* 16 */     return this.startDate;
/*    */   }
/*    */ 
/*    */   public void setStartDate(Date startDate)
/*    */   {
/* 22 */     this.startDate = startDate;
/*    */   }
/*    */ 
/*    */   public Date getEndDate()
/*    */   {
/* 28 */     return this.endDate;
/*    */   }
/*    */ 
/*    */   public void setEndDate(Date endDate)
/*    */   {
/* 34 */     this.endDate = endDate;
/*    */   }
/*    */ 
/*    */   public int getId()
/*    */   {
/* 40 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(int id)
/*    */   {
/* 46 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public PlanVo getPlan()
/*    */   {
/* 52 */     return this.plan;
/*    */   }
/*    */ 
/*    */   public void setPlan(PlanVo plan)
/*    */   {
/* 58 */     this.plan = plan;
/*    */   }
/*    */ 
/*    */   public String getStage()
/*    */   {
/* 64 */     return this.stage;
/*    */   }
/*    */ 
/*    */   public void setStage(String stage)
/*    */   {
/* 70 */     this.stage = stage;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.vo.bo.DevicePlanVo
 * JD-Core Version:    0.6.2
 */