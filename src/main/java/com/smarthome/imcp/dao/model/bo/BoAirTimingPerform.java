/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoAirTimingPerform
/*     */   implements Serializable
/*     */ {
/*     */   private Integer id;
/*     */   private BoUsers boUsers;
/*     */   private String start;
/*     */   private String time;
/*     */   private String days;
/*     */   private String contrl;
/*     */   private String model;
/*     */   private String temp;
/*     */   private String fan;
/*     */   private String deviceaddress;
/*     */ 
/*     */   public BoAirTimingPerform()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoAirTimingPerform(BoUsers boUsers, String start, String time, String days, String contrl, String model, String temp, String fan, String deviceaddress)
/*     */   {
/*  32 */     this.boUsers = boUsers;
/*  33 */     this.start = start;
/*  34 */     this.time = time;
/*  35 */     this.days = days;
/*  36 */     this.contrl = contrl;
/*  37 */     this.model = model;
/*  38 */     this.temp = temp;
/*  39 */     this.fan = fan;
/*  40 */     this.deviceaddress = deviceaddress;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  46 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  50 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  54 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  58 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public String getStart() {
/*  62 */     return this.start;
/*     */   }
/*     */ 
/*     */   public void setStart(String start) {
/*  66 */     this.start = start;
/*     */   }
/*     */ 
/*     */   public String getTime() {
/*  70 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(String time) {
/*  74 */     this.time = time;
/*     */   }
/*     */ 
/*     */   public String getDays() {
/*  78 */     return this.days;
/*     */   }
/*     */ 
/*     */   public void setDays(String days) {
/*  82 */     this.days = days;
/*     */   }
/*     */ 
/*     */   public String getContrl() {
/*  86 */     return this.contrl;
/*     */   }
/*     */ 
/*     */   public void setContrl(String contrl) {
/*  90 */     this.contrl = contrl;
/*     */   }
/*     */ 
/*     */   public String getModel() {
/*  94 */     return this.model;
/*     */   }
/*     */ 
/*     */   public void setModel(String model) {
/*  98 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public String getTemp() {
/* 102 */     return this.temp;
/*     */   }
/*     */ 
/*     */   public void setTemp(String temp) {
/* 106 */     this.temp = temp;
/*     */   }
/*     */ 
/*     */   public String getFan() {
/* 110 */     return this.fan;
/*     */   }
/*     */ 
/*     */   public void setFan(String fan) {
/* 114 */     this.fan = fan;
/*     */   }
/*     */ 
/*     */   public String getDeviceaddress() {
/* 118 */     return this.deviceaddress;
/*     */   }
/*     */ 
/*     */   public void setDeviceaddress(String deviceaddress) {
/* 122 */     this.deviceaddress = deviceaddress;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoAirTimingPerform
 * JD-Core Version:    0.6.2
 */