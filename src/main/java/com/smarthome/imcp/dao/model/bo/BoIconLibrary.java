/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class BoIconLibrary
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String url;
/*    */   private String type;
/*    */   private Integer classify;
/* 18 */   private Set boSimplifies = new HashSet(0);
/* 19 */   private Set boHostDevices = new HashSet(0);
/*    */ 
/*    */   public BoIconLibrary()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoIconLibrary(String url, String type, Integer classify, Set boSimplifies, Set boHostDevices)
/*    */   {
/* 29 */     this.url = url;
/* 30 */     this.type = type;
/* 31 */     this.classify = classify;
/* 32 */     this.boSimplifies = boSimplifies;
/* 33 */     this.boHostDevices = boHostDevices;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 39 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 43 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getUrl() {
/* 47 */     return this.url;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 51 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public String getType() {
/* 55 */     return this.type;
/*    */   }
/*    */ 
/*    */   public void setType(String type) {
/* 59 */     this.type = type;
/*    */   }
/*    */ 
/*    */   public Integer getClassify() {
/* 63 */     return this.classify;
/*    */   }
/*    */ 
/*    */   public void setClassify(Integer classify) {
/* 67 */     this.classify = classify;
/*    */   }
/*    */ 
/*    */   public Set getBoSimplifies() {
/* 71 */     return this.boSimplifies;
/*    */   }
/*    */ 
/*    */   public void setBoSimplifies(Set boSimplifies) {
/* 75 */     this.boSimplifies = boSimplifies;
/*    */   }
/*    */   public Set getBoHostDevices() {
/* 78 */     return this.boHostDevices;
/*    */   }
/*    */ 
/*    */   public void setBoHostDevices(Set boHostDevices) {
/* 82 */     this.boHostDevices = boHostDevices;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoIconLibrary
 * JD-Core Version:    0.6.2
 */