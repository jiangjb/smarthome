/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class BoRoom
/*     */   implements Serializable
/*     */ {
/*     */   private Integer roomId;
/*     */   private String userCode;
/*     */   private String floorName;
/*     */   private String floorCode;
/*     */   private String roomCode;
/*     */   private String roomName;
/*  20 */   private Set boSimplifies = new HashSet(0);
/*  21 */   private Set boHostDevices = new HashSet(0);
/*  22 */   private Set boSensors = new HashSet(0);
/*     */ 
/*     */   public BoRoom()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoRoom(String userCode, String floorCode, String roomCode, String floorName, String roomName, Set boSimplifies, Set boHostDevices, Set boSensors)
/*     */   {
/*  32 */     this.userCode = userCode;
/*  33 */     this.floorCode = floorCode;
/*  34 */     this.roomCode = roomCode;
/*  35 */     this.roomName = roomName;
/*  36 */     this.boSimplifies = boSimplifies;
/*  37 */     this.floorName = floorName;
/*  38 */     this.boHostDevices = boHostDevices;
/*  39 */     this.boSensors = boSensors;
/*     */   }
/*     */ 
/*     */   public Integer getRoomId()
/*     */   {
/*  45 */     return this.roomId;
/*     */   }
/*     */ 
/*     */   public void setRoomId(Integer roomId) {
/*  49 */     this.roomId = roomId;
/*     */   }
/*     */ 
/*     */   public String getUserCode() {
/*  53 */     return this.userCode;
/*     */   }
/*     */ 
/*     */   public void setUserCode(String userCode) {
/*  57 */     this.userCode = userCode;
/*     */   }
/*     */ 
/*     */   public String getFloorCode() {
/*  61 */     return this.floorCode;
/*     */   }
/*     */ 
/*     */   public void setFloorCode(String floorCode) {
/*  65 */     this.floorCode = floorCode;
/*     */   }
/*     */ 
/*     */   public Set getBoSensors() {
/*  69 */     return this.boSensors;
/*     */   }
/*     */ 
/*     */   public void setBoSensors(Set boSensors) {
/*  73 */     this.boSensors = boSensors;
/*     */   }
/*     */ 
/*     */   public String getRoomCode() {
/*  77 */     return this.roomCode;
/*     */   }
/*     */ 
/*     */   public void setRoomCode(String roomCode) {
/*  81 */     this.roomCode = roomCode;
/*     */   }
/*     */ 
/*     */   public String getRoomName() {
/*  85 */     return this.roomName;
/*     */   }
/*     */ 
/*     */   public void setRoomName(String roomName) {
/*  89 */     this.roomName = roomName;
/*     */   }
/*     */ 
/*     */   public String getFloorName()
/*     */   {
/*  94 */     return this.floorName;
/*     */   }
/*     */ 
/*     */   public void setFloorName(String floorName) {
/*  98 */     this.floorName = floorName;
/*     */   }
/*     */ 
/*     */   public Set getBoSimplifies() {
/* 102 */     return this.boSimplifies;
/*     */   }
/*     */ 
/*     */   public void setBoSimplifies(Set boSimplifies) {
/* 106 */     this.boSimplifies = boSimplifies;
/*     */   }
/*     */   public Set getBoHostDevices() {
/* 109 */     return this.boHostDevices;
/*     */   }
/*     */ 
/*     */   public void setBoHostDevices(Set boHostDevices) {
/* 113 */     this.boHostDevices = boHostDevices;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoRoom
 * JD-Core Version:    0.6.2
 */