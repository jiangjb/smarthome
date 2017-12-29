/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class BoModel
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private String modelId;
/*     */   private String name;
/*     */   private String ico;
/*     */   private String week;
/*     */   private String time;
/*     */   private Boolean flag;
/*  22 */   private Set boModelInfos = new HashSet(0);
/*  23 */   private Set boSensors = new HashSet(0);
/*     */ 
/*     */   public BoModel()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoModel(BoUsers boUsers, String modelId, String name, String ico, String week, String time, Boolean flag, Set boModelInfos, Set boSensors)
/*     */   {
/*  35 */     this.boUsers = boUsers;
/*  36 */     this.modelId = modelId;
/*  37 */     this.name = name;
/*  38 */     this.ico = ico;
/*  39 */     this.week = week;
/*  40 */     this.time = time;
/*  41 */     this.flag = flag;
/*  42 */     this.boModelInfos = boModelInfos;
/*  43 */     this.boSensors = boSensors;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  49 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  53 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  57 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  61 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public String getModelId() {
/*  65 */     return this.modelId;
/*     */   }
/*     */ 
/*     */   public void setModelId(String modelId) {
/*  69 */     this.modelId = modelId;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  73 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  77 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getIco() {
/*  81 */     return this.ico;
/*     */   }
/*     */ 
/*     */   public void setIco(String ico) {
/*  85 */     this.ico = ico;
/*     */   }
/*     */ 
/*     */   public String getWeek() {
/*  89 */     return this.week;
/*     */   }
/*     */ 
/*     */   public void setWeek(String week) {
/*  93 */     this.week = week;
/*     */   }
/*     */ 
/*     */   public String getTime() {
/*  97 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(String time) {
/* 101 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public Boolean getFlag() {
/* 105 */     return this.flag;
/*     */   }
/*     */ 
/*     */   public void setFlag(Boolean flag) {
/* 109 */     this.flag = flag;
/*     */   }
/*     */ 
/*     */   public Set getBoModelInfos() {
/* 113 */     return this.boModelInfos;
/*     */   }
/*     */ 
/*     */   public void setBoModelInfos(Set boModelInfos) {
/* 117 */     this.boModelInfos = boModelInfos;
/*     */   }
/*     */ 
/*     */   public Set getBoSensors() {
/* 121 */     return this.boSensors;
/*     */   }
/*     */ 
/*     */   public void setBoSensors(Set boSensors) {
/* 125 */     this.boSensors = boSensors;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoModel
 * JD-Core Version:    0.6.2
 */