/*    */ package com.smarthome.imcp.dao.model.system;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class SysMessage
/*    */   implements Serializable
/*    */ {
/*    */   private Integer messageId;
/*    */   private String messageTitle;
/*    */   private String messageContent;
/*    */   private Date messageDate;
/*    */ 
/*    */   public Integer getMessageId()
/*    */   {
/* 17 */     return this.messageId;
/*    */   }
/*    */ 
/*    */   public void setMessageId(Integer messageId) {
/* 21 */     this.messageId = messageId;
/*    */   }
/*    */ 
/*    */   public String getMessageTitle() {
/* 25 */     return this.messageTitle;
/*    */   }
/*    */ 
/*    */   public void setMessageTitle(String messageTitle) {
/* 29 */     this.messageTitle = messageTitle;
/*    */   }
/*    */ 
/*    */   public String getMessageContent() {
/* 33 */     return this.messageContent;
/*    */   }
/*    */ 
/*    */   public void setMessageContent(String messageContent) {
/* 37 */     this.messageContent = messageContent;
/*    */   }
/*    */ 
/*    */   public Date getMessageDate() {
/* 41 */     return this.messageDate;
/*    */   }
/*    */ 
/*    */   public void setMessageDate(Date messageDate) {
/* 45 */     this.messageDate = messageDate;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysMessage
 * JD-Core Version:    0.6.2
 */