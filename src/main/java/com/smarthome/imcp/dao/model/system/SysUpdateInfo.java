/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.AbstractData;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SysUpdateInfo extends AbstractData
/*    */ {
/*    */   private Integer sysUpdateInfoId;
/*    */   private String versionNo;
/*    */   private Date updateTime;
/*    */   private String updateContent;
/*    */ 
/*    */   public Integer getSysUpdateInfoId()
/*    */   {
/* 16 */     return this.sysUpdateInfoId;
/*    */   }
/*    */ 
/*    */   public void setSysUpdateInfoId(Integer sysUpdateInfoId) {
/* 20 */     this.sysUpdateInfoId = sysUpdateInfoId;
/*    */   }
/*    */ 
/*    */   public String getVersionNo() {
/* 24 */     return this.versionNo;
/*    */   }
/*    */ 
/*    */   public void setVersionNo(String versionNo) {
/* 28 */     this.versionNo = versionNo;
/*    */   }
/*    */ 
/*    */   public Date getUpdateTime() {
/* 32 */     return this.updateTime;
/*    */   }
/*    */ 
/*    */   public void setUpdateTime(Date updateTime) {
/* 36 */     this.updateTime = updateTime;
/*    */   }
/*    */ 
/*    */   public String getUpdateContent() {
/* 40 */     return this.updateContent;
/*    */   }
/*    */ 
/*    */   public void setUpdateContent(String updateContent) {
/* 44 */     this.updateContent = updateContent;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysUpdateInfo
 * JD-Core Version:    0.6.2
 */