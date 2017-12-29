/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoErrorreport
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String appName;
/*    */   private String content;
/*    */   private Date reportdate;
/*    */ 
/*    */   public BoErrorreport()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoErrorreport(String appName, String content, Date reportdate)
/*    */   {
/* 26 */     this.appName = appName;
/* 27 */     this.content = content;
/* 28 */     this.reportdate = reportdate;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 34 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 38 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getAppName() {
/* 42 */     return this.appName;
/*    */   }
/*    */ 
/*    */   public void setAppName(String appName) {
/* 46 */     this.appName = appName;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 50 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 54 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public Date getReportdate() {
/* 58 */     return this.reportdate;
/*    */   }
/*    */ 
/*    */   public void setReportdate(Date reportdate) {
/* 62 */     this.reportdate = reportdate;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoErrorreport
 * JD-Core Version:    0.6.2
 */