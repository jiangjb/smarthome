/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class SysParamCode
/*    */   implements Serializable
/*    */ {
/*    */   private String paramCode;
/*    */   private String paramCodeName;
/*    */   private String paramCodeRmk;
/*    */ 
/*    */   public SysParamCode()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SysParamCode(String paramCode, String paramCodeName)
/*    */   {
/* 26 */     this.paramCode = paramCode;
/* 27 */     this.paramCodeName = paramCodeName;
/*    */   }
/*    */ 
/*    */   public String getParamCode()
/*    */   {
/* 33 */     return this.paramCode;
/*    */   }
/*    */ 
/*    */   public void setParamCode(String paramCode) {
/* 37 */     this.paramCode = paramCode;
/*    */   }
/*    */ 
/*    */   public String getParamCodeName() {
/* 41 */     return this.paramCodeName;
/*    */   }
/*    */ 
/*    */   public void setParamCodeName(String paramCodeName) {
/* 45 */     this.paramCodeName = paramCodeName;
/*    */   }
/*    */ 
/*    */   public String getParamCodeRmk() {
/* 49 */     return this.paramCodeRmk;
/*    */   }
/*    */ 
/*    */   public void setParamCodeRmk(String paramCodeRmk) {
/* 53 */     this.paramCodeRmk = paramCodeRmk;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysParamCode
 * JD-Core Version:    0.6.2
 */