/*    */ package com.smarthome.imcp.dao.model.bo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class BoFloor
/*    */   implements Serializable
/*    */ {
/*    */   private Integer floorId;
/*    */   private String userCode;
/*    */   private String houseCode;
/*    */   private String floorCode;
/*    */   private String floorName;
/*    */ 
/*    */   public BoFloor()
/*    */   {
/*    */   }
/*    */ 
/*    */   public BoFloor(String houseCode, String floorCode)
/*    */   {
/* 25 */     this.houseCode = houseCode;
/* 26 */     this.floorCode = floorCode;
/*    */   }
/*    */ 
/*    */   public BoFloor(String userCode, String houseCode, String floorCode, String floorName)
/*    */   {
/* 32 */     this.userCode = userCode;
/* 33 */     this.houseCode = houseCode;
/* 34 */     this.floorCode = floorCode;
/* 35 */     this.floorName = floorName;
/*    */   }
/*    */ 
/*    */   public Integer getFloorId()
/*    */   {
/* 41 */     return this.floorId;
/*    */   }
/*    */ 
/*    */   public void setFloorId(Integer floorId) {
/* 45 */     this.floorId = floorId;
/*    */   }
/*    */ 
/*    */   public String getUserCode() {
/* 49 */     return this.userCode;
/*    */   }
/*    */ 
/*    */   public void setUserCode(String userCode) {
/* 53 */     this.userCode = userCode;
/*    */   }
/*    */ 
/*    */   public String getHouseCode() {
/* 57 */     return this.houseCode;
/*    */   }
/*    */ 
/*    */   public void setHouseCode(String houseCode) {
/* 61 */     this.houseCode = houseCode;
/*    */   }
/*    */ 
/*    */   public String getFloorCode() {
/* 65 */     return this.floorCode;
/*    */   }
/*    */ 
/*    */   public void setFloorCode(String floorCode) {
/* 69 */     this.floorCode = floorCode;
/*    */   }
/*    */ 
/*    */   public String getFloorName() {
/* 73 */     return this.floorName;
/*    */   }
/*    */ 
/*    */   public void setFloorName(String floorName) {
/* 77 */     this.floorName = floorName;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoFloor
 * JD-Core Version:    0.6.2
 */