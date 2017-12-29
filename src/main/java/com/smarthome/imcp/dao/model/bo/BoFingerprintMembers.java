/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoFingerprintMembers
/*    */   implements Serializable
/*    */ {
/*    */   private String fingerprintMembersId;
/*    */   private String lockAddress;
/*    */   private String fingerprintSubscript;
/*    */   private String membersName;
/*    */   private String membersHeadpic;
/*    */ 
/*    */   public BoFingerprintMembers()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoFingerprintMembers(String fingerprintMembersId)
/*    */   {
/* 25 */     this.fingerprintMembersId = fingerprintMembersId;
/*    */   }
/*    */ 
/*    */   public BoFingerprintMembers(String fingerprintMembersId, String lockAddress, String fingerprintSubscript, String membersName, String membersHeadpic)
/*    */   {
/* 32 */     this.fingerprintMembersId = fingerprintMembersId;
/*    */ 
/* 34 */     this.lockAddress = lockAddress;
/* 35 */     this.fingerprintSubscript = fingerprintSubscript;
/* 36 */     this.membersName = membersName;
/* 37 */     this.membersHeadpic = membersHeadpic;
/*    */   }
/*    */ 
/*    */   public String getFingerprintMembersId()
/*    */   {
/* 43 */     return this.fingerprintMembersId;
/*    */   }
/*    */ 
/*    */   public void setFingerprintMembersId(String fingerprintMembersId) {
/* 47 */     this.fingerprintMembersId = fingerprintMembersId;
/*    */   }
/*    */ 
/*    */   public String getLockAddress()
/*    */   {
/* 53 */     return this.lockAddress;
/*    */   }
/*    */ 
/*    */   public void setLockAddress(String lockAddress) {
/* 57 */     this.lockAddress = lockAddress;
/*    */   }
/*    */ 
/*    */   public String getFingerprintSubscript() {
/* 61 */     return this.fingerprintSubscript;
/*    */   }
/*    */ 
/*    */   public void setFingerprintSubscript(String fingerprintSubscript) {
/* 65 */     this.fingerprintSubscript = fingerprintSubscript;
/*    */   }
/*    */ 
/*    */   public String getMembersName() {
/* 69 */     return this.membersName;
/*    */   }
/*    */ 
/*    */   public void setMembersName(String membersName) {
/* 73 */     this.membersName = membersName;
/*    */   }
/*    */ 
/*    */   public String getMembersHeadpic() {
/* 77 */     return this.membersHeadpic;
/*    */   }
/*    */ 
/*    */   public void setMembersHeadpic(String membersHeadpic) {
/* 81 */     this.membersHeadpic = membersHeadpic;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoFingerprintMembers
 * JD-Core Version:    0.6.2
 */