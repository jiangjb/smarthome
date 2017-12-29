/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.AbstractData;
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class BoUserGroup extends AbstractData
/*    */ {
/*    */   private Integer groupId;
/*    */   private String groupName;
/*    */   private String groupRmk;
/* 22 */   private Set boUsers = new HashSet(0);
/*    */ 
/*    */   public BoUserGroup()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoUserGroup(String groupName, String groupRmk, Set boUsers)
/*    */   {
/* 33 */     this.groupName = groupName;
/* 34 */     this.groupRmk = groupRmk;
/* 35 */     this.boUsers = boUsers;
/*    */   }
/*    */ 
/*    */   public Integer getGroupId()
/*    */   {
/* 41 */     return this.groupId;
/*    */   }
/*    */ 
/*    */   public void setGroupId(Integer groupId) {
/* 45 */     this.groupId = groupId;
/*    */   }
/*    */ 
/*    */   public String getGroupName() {
/* 49 */     return this.groupName;
/*    */   }
/*    */ 
/*    */   public void setGroupName(String groupName) {
/* 53 */     this.groupName = groupName;
/*    */   }
/*    */ 
/*    */   public String getGroupRmk() {
/* 57 */     return this.groupRmk;
/*    */   }
/*    */ 
/*    */   public void setGroupRmk(String groupRmk) {
/* 61 */     this.groupRmk = groupRmk;
/*    */   }
/*    */ 
/*    */   public Set getBoUsers() {
/* 65 */     return this.boUsers;
/*    */   }
/*    */ 
/*    */   public void setBoUsers(Set boUsers) {
/* 69 */     this.boUsers = boUsers;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoUserGroup
 * JD-Core Version:    0.6.2
 */