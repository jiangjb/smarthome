/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysDictCacheManager;
/*     */ import com.smarthome.imcp.dao.model.system.SysDict;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoReport extends ReportModel
/*     */   implements Serializable
/*     */ {
/*  16 */   private Integer work = Integer.valueOf(0);
/*  17 */   private Integer status = Integer.valueOf(0);
/*     */ 
/*  19 */   private Integer tds = Integer.valueOf(0);
/*  20 */   private Integer life1 = Integer.valueOf(0);
/*  21 */   private Integer life2 = Integer.valueOf(0);
/*  22 */   private Integer life3 = Integer.valueOf(0);
/*  23 */   private Integer life4 = Integer.valueOf(0);
/*  24 */   private Integer life5 = Integer.valueOf(0);
/*     */ 
/*  29 */   private Integer inTds = Integer.valueOf(0);
/*  30 */   private Integer outTds = Integer.valueOf(0);
/*  31 */   private Integer totalWater = Integer.valueOf(0);
/*     */ 
/*  36 */   private Integer temperature = Integer.valueOf(0);
/*  37 */   private Integer humidity = Integer.valueOf(0);
/*  38 */   private Integer electric = Integer.valueOf(0);
/*  39 */   private String imagePath = "";
/*     */ 
/*  41 */   private String workName = "";
/*  42 */   private String statusName = "";
/*     */ 
/*     */   public Integer getWork()
/*     */   {
/*  52 */     return this.work;
/*     */   }
/*     */ 
/*     */   public void setWork(Integer work) {
/*  56 */     this.work = work;
/*     */   }
/*     */ 
/*     */   public Integer getStatus() {
/*  60 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(Integer status) {
/*  64 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public Integer getTds() {
/*  68 */     return this.tds;
/*     */   }
/*     */ 
/*     */   public void setTds(Integer tds) {
/*  72 */     this.tds = tds;
/*     */   }
/*     */ 
/*     */   public Integer getLife1() {
/*  76 */     return this.life1;
/*     */   }
/*     */ 
/*     */   public void setLife1(Integer life1) {
/*  80 */     this.life1 = life1;
/*     */   }
/*     */ 
/*     */   public Integer getLife2() {
/*  84 */     return this.life2;
/*     */   }
/*     */ 
/*     */   public void setLife2(Integer life2) {
/*  88 */     this.life2 = life2;
/*     */   }
/*     */ 
/*     */   public Integer getLife3() {
/*  92 */     return this.life3;
/*     */   }
/*     */ 
/*     */   public void setLife3(Integer life3) {
/*  96 */     this.life3 = life3;
/*     */   }
/*     */ 
/*     */   public Integer getLife4() {
/* 100 */     return this.life4;
/*     */   }
/*     */ 
/*     */   public void setLife4(Integer life4) {
/* 104 */     this.life4 = life4;
/*     */   }
/*     */ 
/*     */   public Integer getLife5() {
/* 108 */     return this.life5;
/*     */   }
/*     */ 
/*     */   public void setLife5(Integer life5) {
/* 112 */     this.life5 = life5;
/*     */   }
/*     */ 
/*     */   public Integer getInTds() {
/* 116 */     return this.inTds;
/*     */   }
/*     */ 
/*     */   public void setInTds(Integer inTds) {
/* 120 */     this.inTds = inTds;
/*     */   }
/*     */ 
/*     */   public Integer getOutTds() {
/* 124 */     return this.outTds;
/*     */   }
/*     */ 
/*     */   public void setOutTds(Integer outTds) {
/* 128 */     this.outTds = outTds;
/*     */   }
/*     */ 
/*     */   public Integer getTotalWater() {
/* 132 */     return this.totalWater;
/*     */   }
/*     */ 
/*     */   public void setTotalWater(Integer totalWater) {
/* 136 */     this.totalWater = totalWater;
/*     */   }
/*     */ 
/*     */   public Integer getTemperature() {
/* 140 */     return this.temperature;
/*     */   }
/*     */ 
/*     */   public void setTemperature(Integer temperature) {
/* 144 */     this.temperature = temperature;
/*     */   }
/*     */ 
/*     */   public Integer getHumidity() {
/* 148 */     return this.humidity;
/*     */   }
/*     */ 
/*     */   public void setHumidity(Integer humidity) {
/* 152 */     this.humidity = humidity;
/*     */   }
/*     */ 
/*     */   public String getImagePath() {
/* 156 */     return this.imagePath;
/*     */   }
/*     */ 
/*     */   public void setImagePath(String imagePath) {
/* 160 */     this.imagePath = imagePath;
/*     */   }
/*     */ 
/*     */   public Integer getElectric() {
/* 164 */     return this.electric;
/*     */   }
/*     */ 
/*     */   public void setElectric(Integer electric) {
/* 168 */     this.electric = electric;
/*     */   }
/*     */ 
/*     */   public String getWorkName() {
/* 172 */     SysDict dict = SysDictCacheManager.getInstance().getSysDict("w" + this.frameType + "_" + this.deviceType, this.work+"");
/* 173 */     if (dict == null)
/* 174 */       this.workName = "";
/*     */     else {
/* 176 */       this.workName = dict.getDictName();
/*     */     }
/* 178 */     return this.workName;
/*     */   }
/*     */ 
/*     */   public void setWorkName(String workName) {
/* 182 */     this.workName = workName;
/*     */   }
/*     */ 
/*     */   public String getStatusName() {
/* 186 */     SysDict dict = SysDictCacheManager.getInstance().getSysDict("s" + this.frameType + "_" + this.deviceType, this.status+"");
/* 187 */     if (dict == null)
/* 188 */       this.statusName = "";
/*     */     else {
/* 190 */       this.statusName = dict.getDictName();
/*     */     }
/* 192 */     return this.statusName;
/*     */   }
/*     */ 
/*     */   public void setStatusName(String statusName) {
/* 196 */     this.statusName = statusName;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoReport
 * JD-Core Version:    0.6.2
 */