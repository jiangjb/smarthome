/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ import java.util.Date;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoAdvice extends AbstractData
/*     */ {
/*     */   private Integer adviceId;
/*     */   private BoUser boUser;
/*     */   private String adviceTxt;
/*     */   private Date adviceDate;
/*     */   private String solveName;
/*     */   private String solveTxt;
/*     */   private Date solveDate;
/*     */   private Integer adviceMark;
/*     */   private String userName;
/*     */ 
/*     */   public BoAdvice()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoAdvice(BoUser boUser, String adviceTxt, Date adviceDate, String solveName, String solveTxt, Date solveDate, Integer adviceMark)
/*     */   {
/*  38 */     this.boUser = boUser;
/*  39 */     this.adviceTxt = adviceTxt;
/*  40 */     this.adviceDate = adviceDate;
/*  41 */     this.solveName = solveName;
/*  42 */     this.solveTxt = solveTxt;
/*  43 */     this.solveDate = solveDate;
/*  44 */     this.adviceMark = adviceMark;
/*     */   }
/*     */ 
/*     */   public Integer getAdviceId()
/*     */   {
/*  50 */     return this.adviceId;
/*     */   }
/*     */ 
/*     */   public void setAdviceId(Integer adviceId) {
/*  54 */     this.adviceId = adviceId;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoUser getBoUser() {
/*  58 */     return this.boUser;
/*     */   }
/*     */ 
/*     */   public void setBoUser(BoUser boUser) {
/*  62 */     this.boUser = boUser;
/*     */   }
/*     */ 
/*     */   public String getAdviceTxt() {
/*  66 */     return this.adviceTxt;
/*     */   }
/*     */ 
/*     */   public void setAdviceTxt(String adviceTxt) {
/*  70 */     this.adviceTxt = adviceTxt;
/*     */   }
/*     */ 
/*     */   public Date getAdviceDate() {
/*  74 */     return this.adviceDate;
/*     */   }
/*     */ 
/*     */   public void setAdviceDate(Date adviceDate) {
/*  78 */     this.adviceDate = adviceDate;
/*     */   }
/*     */ 
/*     */   public String getSolveName() {
/*  82 */     return this.solveName;
/*     */   }
/*     */ 
/*     */   public void setSolveName(String solveName) {
/*  86 */     this.solveName = solveName;
/*     */   }
/*     */ 
/*     */   public String getSolveTxt() {
/*  90 */     return this.solveTxt;
/*     */   }
/*     */ 
/*     */   public void setSolveTxt(String solveTxt) {
/*  94 */     this.solveTxt = solveTxt;
/*     */   }
/*     */ 
/*     */   public Date getSolveDate() {
/*  98 */     return this.solveDate;
/*     */   }
/*     */ 
/*     */   public void setSolveDate(Date solveDate) {
/* 102 */     this.solveDate = solveDate;
/*     */   }
/*     */ 
/*     */   public Integer getAdviceMark() {
/* 106 */     return this.adviceMark;
/*     */   }
/*     */ 
/*     */   public void setAdviceMark(Integer adviceMark) {
/* 110 */     this.adviceMark = adviceMark;
/*     */   }
/*     */ 
/*     */   public String getUserName() {
/* 114 */     return this.boUser.getUserName();
/*     */   }
/*     */ 
/*     */   public void setUserName(String userName) {
/* 118 */     this.userName = userName;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoAdvice
 * JD-Core Version:    0.6.2
 */