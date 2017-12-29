/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class BoFriend
/*    */   implements Serializable
/*    */ {
/*    */   private Integer id;
/*    */   private BoUser user;
/*    */   private BoUser friend;
/*    */   private Date addTime;
/*    */   private String userName;
/*    */   private String friendName;
/*    */ 
/*    */   public Integer getId()
/*    */   {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Integer id) {
/* 34 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public BoUser getUser() {
/* 38 */     return this.user;
/*    */   }
/*    */ 
/*    */   public void setUser(BoUser user) {
/* 42 */     this.user = user;
/*    */   }
/*    */ 
/*    */   public BoUser getFriend() {
/* 46 */     return this.friend;
/*    */   }
/*    */ 
/*    */   public void setFriend(BoUser friend) {
/* 50 */     this.friend = friend;
/*    */   }
/*    */ 
/*    */   public Date getAddTime() {
/* 54 */     return this.addTime;
/*    */   }
/*    */ 
/*    */   public void setAddTime(Date addTime) {
/* 58 */     this.addTime = addTime;
/*    */   }
/*    */ 
/*    */   public String getUserName() {
/* 62 */     this.userName = this.user.getUserName();
/* 63 */     return this.userName;
/*    */   }
/*    */ 
/*    */   public void setUserName(String userName) {
/* 67 */     this.userName = userName;
/*    */   }
/*    */ 
/*    */   public String getFriendName() {
/* 71 */     this.friendName = this.friend.getUserName();
/* 72 */     return this.friendName;
/*    */   }
/*    */ 
/*    */   public void setFriendName(String friendName) {
/* 76 */     this.friendName = friendName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoFriend
 * JD-Core Version:    0.6.2
 */