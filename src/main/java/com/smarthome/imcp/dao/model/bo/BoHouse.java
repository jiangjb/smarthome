/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoHouse
/*    */   implements Serializable
/*    */ {
/*    */   private Integer houseId;
/*    */   private String userCode;
/*    */   private String houseCode;
/*    */   private String houseName;
/*    */ 
/*    */   public BoHouse()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoHouse(String userCode, String houseCode)
/*    */   {
/* 28 */     this.userCode = userCode;
/* 29 */     this.houseCode = houseCode;
/*    */   }
/*    */ 
/*    */   public BoHouse(String userCode, String houseCode, String houseName)
/*    */   {
/* 34 */     this.userCode = userCode;
/* 35 */     this.houseCode = houseCode;
/* 36 */     this.houseName = houseName;
/*    */   }
/*    */ 
/*    */   public Integer getHouseId()
/*    */   {
/* 43 */     return this.houseId;
/*    */   }
/*    */ 
/*    */   public void setHouseId(Integer houseId) {
/* 47 */     this.houseId = houseId;
/*    */   }
/*    */ 
/*    */   public String getUserCode() {
/* 51 */     return this.userCode;
/*    */   }
/*    */ 
/*    */   public void setUserCode(String userCode) {
/* 55 */     this.userCode = userCode;
/*    */   }
/*    */ 
/*    */   public String getHouseCode() {
/* 59 */     return this.houseCode;
/*    */   }
/*    */ 
/*    */   public void setHouseCode(String houseCode) {
/* 63 */     this.houseCode = houseCode;
/*    */   }
/*    */ 
/*    */   public String getHouseName() {
/* 67 */     return this.houseName;
/*    */   }
/*    */ 
/*    */   public void setHouseName(String houseName) {
/* 71 */     this.houseName = houseName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoHouse
 * JD-Core Version:    0.6.2
 */