/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoFeedBack
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUsers boUsers;
/*    */   private String userPhone;
/*    */   private String content;
/*    */   private Date time;
/*    */ 
/*    */   public BoFeedBack()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoFeedBack(BoUsers boUsers, String userPhone, String content, Date time)
/*    */   {
/* 29 */     this.boUsers = boUsers;
/* 30 */     this.userPhone = userPhone;
/* 31 */     this.content = content;
/* 32 */     this.time = time;
/*    */   }
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 38 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 42 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoUsers getBoUsers() {
/* 46 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(BoUsers boUsers) {
/* 50 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public String getUserPhone() {
/* 54 */     return this.userPhone;
/*    */   }
/*    */ 
/*    */   public void setUserPhone(String userPhone) {
/* 58 */     this.userPhone = userPhone;
/*    */   }
/*    */ 
/*    */   public String getContent() {
/* 62 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(String content) {
/* 66 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public Date getTime() {
/* 70 */     return this.time;
/*    */   }
/*    */ 
/*    */   public void setTime(Date time) {
/* 74 */     this.time = time;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoFeedBack
 * JD-Core Version:    0.6.2
 */