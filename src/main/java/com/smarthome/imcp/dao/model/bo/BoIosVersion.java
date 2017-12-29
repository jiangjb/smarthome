/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoIosVersion
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private String version;
/*    */   private String provider;
/*    */   private String apkUrl;
/*    */ 
/*    */   public BoIosVersion()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoIosVersion(String version)
/*    */   {
/* 24 */     this.version = version;
/*    */   }
/*    */ 
/*    */   public BoIosVersion(String version, String provider, String apkUrl)
/*    */   {
/* 29 */     this.version = version;
/* 30 */     this.provider = provider;
/* 31 */     this.apkUrl = apkUrl;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 37 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 41 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getVersion() {
/* 45 */     return this.version;
/*    */   }
/*    */ 
/*    */   public void setVersion(String version) {
/* 49 */     this.version = version;
/*    */   }
/*    */ 
/*    */   public String getProvider() {
/* 53 */     return this.provider;
/*    */   }
/*    */ 
/*    */   public void setProvider(String provider) {
/* 57 */     this.provider = provider;
/*    */   }
/*    */ 
/*    */   public String getApkUrl() {
/* 61 */     return this.apkUrl;
/*    */   }
/*    */ 
/*    */   public void setApkUrl(String apkUrl) {
/* 65 */     this.apkUrl = apkUrl;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoIosVersion
 * JD-Core Version:    0.6.2
 */