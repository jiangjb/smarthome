/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.apache.struts2.json.annotations.JSON;
/*    */ 
/*    */ public class SysParam
/*    */   implements Serializable
/*    */ {
/*    */   private SysParamCode sysParamCode;
/*    */   private Integer paramId;
/*    */   private String paramValue;
/*    */   private String paramName;
/*    */   private String paramRmk;
/*    */   private Integer mntPosition;
/*    */ 
/*    */   public SysParam()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysParam(SysParamCode sysParamCode, String paramValue, String paramName)
/*    */   {
/* 32 */     this.sysParamCode = sysParamCode;
/* 33 */     this.paramValue = paramValue;
/* 34 */     this.paramName = paramName;
/*    */   }
/*    */ 
/*    */   @JSON(serialize=false)
/*    */   public SysParamCode getSysParamCode()
/*    */   {
/* 41 */     return this.sysParamCode;
/*    */   }
/*    */ 
/*    */   public void setSysParamCode(SysParamCode sysParamCode) {
/* 45 */     this.sysParamCode = sysParamCode;
/*    */   }
/*    */ 
/*    */   public Integer getParamId() {
/* 49 */     return this.paramId;
/*    */   }
/*    */ 
/*    */   public void setParamId(Integer paramId) {
/* 53 */     this.paramId = paramId;
/*    */   }
/*    */ 
/*    */   public String getParamValue() {
/* 57 */     return this.paramValue;
/*    */   }
/*    */ 
/*    */   public void setParamValue(String paramValue) {
/* 61 */     this.paramValue = paramValue;
/*    */   }
/*    */ 
/*    */   public String getParamName() {
/* 65 */     return this.paramName;
/*    */   }
/*    */ 
/*    */   public void setParamName(String paramName) {
/* 69 */     this.paramName = paramName;
/*    */   }
/*    */ 
/*    */   public String getParamRmk() {
/* 73 */     return this.paramRmk;
/*    */   }
/*    */ 
/*    */   public void setParamRmk(String paramRmk) {
/* 77 */     this.paramRmk = paramRmk;
/*    */   }
/*    */ 
/*    */   public Integer getMntPosition() {
/* 81 */     return this.mntPosition;
/*    */   }
/*    */ 
/*    */   public void setMntPosition(Integer mntPosition) {
/* 85 */     this.mntPosition = mntPosition;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysParam
 * JD-Core Version:    0.6.2
 */