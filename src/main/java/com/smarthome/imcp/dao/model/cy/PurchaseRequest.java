/*     */ package com.smarthome.imcp.dao.model.cy;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class PurchaseRequest
/*     */ {
/*     */   private int id;
/*     */   private BoUser boUser;
/*     */   private String deviceCode;
/*     */   private Plan plan;
/*     */   private String contactName;
/*     */   private String contactPhone;
/*     */   private String installAddress;
/*     */   private boolean completed;
/*     */   private Date requestDate;
/*     */   private Date completionDate;
/*     */   private String operator;
/*     */   private String remark;
/*     */ 
/*     */   public int getId()
/*     */   {
/*  25 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/*  31 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUser getBoUser()
/*     */   {
/*  37 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser)
/*     */   {
/*  43 */     this.boUser = boUser;
/*     */   }
/*     */ 
/*     */   public Plan getPlan()
/*     */   {
/*  49 */     return this.plan;
/*     */   }
/*     */ 
/*     */   public void setPlan(Plan plan)
/*     */   {
/*  55 */     this.plan = plan;
/*     */   }
/*     */   public String getContactPhone() {
/*  58 */     return this.contactPhone;
/*     */   }
/*     */   public void setContactPhone(String contactPhone) {
/*  61 */     this.contactPhone = contactPhone;
/*     */   }
/*     */   public String getInstallAddress() {
/*  64 */     return this.installAddress;
/*     */   }
/*     */   public void setInstallAddress(String installAddress) {
/*  67 */     this.installAddress = installAddress;
/*     */   }
/*     */   public boolean isCompleted() {
/*  70 */     return this.completed;
/*     */   }
/*     */   public void setCompleted(boolean completed) {
/*  73 */     this.completed = completed;
/*     */   }
/*     */   public Date getRequestDate() {
/*  76 */     return this.requestDate;
/*     */   }
/*     */   public void setRequestDate(Date requestDate) {
/*  79 */     this.requestDate = requestDate;
/*     */   }
/*     */   public Date getCompletionDate() {
/*  82 */     return this.completionDate;
/*     */   }
/*     */   public void setCompletionDate(Date completionDate) {
/*  85 */     this.completionDate = completionDate;
/*     */   }
/*     */   public String getContactName() {
/*  88 */     return this.contactName;
/*     */   }
/*     */   public void setContactName(String contactName) {
/*  91 */     this.contactName = contactName;
/*     */   }
/*     */   public String getDeviceCode() {
/*  94 */     return this.deviceCode;
/*     */   }
/*     */   public void setDeviceCode(String deviceCode) {
/*  97 */     this.deviceCode = deviceCode;
/*     */   }
/*     */   public String getOperator() {
/* 100 */     return this.operator;
/*     */   }
/*     */   public void setOperator(String operator) {
/* 103 */     this.operator = operator;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 109 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 115 */     this.remark = remark;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.PurchaseRequest
 * JD-Core Version:    0.6.2
 */