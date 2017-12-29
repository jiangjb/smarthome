/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class BoSimplify
/*     */   implements Serializable
/*     */ {
/*     */   private Integer userDeviceId;
/*     */   private BoUsers boUsers;
/*     */   private BoHost boHost;
/*     */   private BoIconLibrary boIconLibrary;
/*     */   private BoDevice boDevice;
/*     */   private BoRoom boRoom;
/*     */   private String nickName;
/*     */   private Boolean deviceClassify;
/*     */ 
/*     */   public BoSimplify()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoSimplify(BoUsers boUsers, BoHost boHost, BoIconLibrary boIconLibrary, BoDevice boDevice, BoRoom boRoom, String nickName, Boolean deviceClassify)
/*     */   {
/*  31 */     this.boUsers = boUsers;
/*  32 */     this.boHost = boHost;
/*  33 */     this.boIconLibrary = boIconLibrary;
/*  34 */     this.boDevice = boDevice;
/*  35 */     this.boRoom = boRoom;
/*  36 */     this.nickName = nickName;
/*  37 */     this.deviceClassify = deviceClassify;
/*     */   }
/*     */ 
/*     */   public Integer getUserDeviceId()
/*     */   {
/*  43 */     return this.userDeviceId;
/*     */   }
/*     */ 
/*     */   public void setUserDeviceId(Integer userDeviceId) {
/*  47 */     this.userDeviceId = userDeviceId;
/*     */   }
/*     */ 
/*     */   public BoUsers getBoUsers() {
/*  51 */     return this.boUsers;
/*     */   }
/*     */ 
/*     */   public void setBoUsers(BoUsers boUsers) {
/*  55 */     this.boUsers = boUsers;
/*     */   }
/*     */ 
/*     */   public BoHost getBoHost() {
/*  59 */     return this.boHost;
/*     */   }
/*     */ 
/*     */   public void setBoHost(BoHost boHost) {
/*  63 */     this.boHost = boHost;
/*     */   }
/*     */ 
/*     */   public BoIconLibrary getBoIconLibrary() {
/*  67 */     return this.boIconLibrary;
/*     */   }
/*     */ 
/*     */   public void setBoIconLibrary(BoIconLibrary boIconLibrary) {
/*  71 */     this.boIconLibrary = boIconLibrary;
/*     */   }
/*     */ 
/*     */   public BoDevice getBoDevice()
/*     */   {
/*  83 */     return this.boDevice;
/*     */   }
/*     */ 
/*     */   public void setBoDevice(BoDevice boDevice) {
/*  87 */     this.boDevice = boDevice;
/*     */   }
/*     */ 
/*     */   public BoRoom getBoRoom() {
/*  91 */     return this.boRoom;
/*     */   }
/*     */ 
/*     */   public void setBoRoom(BoRoom boRoom) {
/*  95 */     this.boRoom = boRoom;
/*     */   }
/*     */ 
/*     */   public String getNickName() {
/*  99 */     return this.nickName;
/*     */   }
/*     */ 
/*     */   public void setNickName(String nickName) {
/* 103 */     this.nickName = nickName;
/*     */   }
/*     */ 
/*     */   public Boolean getDeviceClassify() {
/* 107 */     return this.deviceClassify;
/*     */   }
/*     */ 
/*     */   public void setDeviceClassify(Boolean deviceClassify) {
/* 111 */     this.deviceClassify = deviceClassify;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoSimplify
 * JD-Core Version:    0.6.2
 */