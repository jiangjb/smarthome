/*     */ package com.smarthome.imcp.dao.model.bo;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.struts2.json.annotations.JSON;
/*     */ 
/*     */ public class BoLaunch
/*     */   implements Serializable
/*     */ {
/*     */   private Integer imageId;
/*     */   private BoUserGroup boUserGroup;
/*     */   private Integer type;
/*     */   private String imageName;
/*     */   private String imagePath;
/*     */   private String imageFilename;
/*     */   private Integer imageHeight;
/*     */   private Integer imageWidth;
/*     */   private Integer imageSize;
/*     */   private String fileSizeName;
/*     */   private String fileNames;
/*     */ 
/*     */   public BoLaunch()
/*     */   {
/*     */   }
/*     */ 
/*     */   public BoLaunch(BoUserGroup boUserGroup, String imageName, String imagePath, String imageFilename, Integer imageHeight, Integer imageWidth, Integer imageSize)
/*     */   {
/*  37 */     this.boUserGroup = boUserGroup;
/*  38 */     this.imageName = imageName;
/*  39 */     this.imagePath = imagePath;
/*  40 */     this.imageFilename = imageFilename;
/*  41 */     this.imageHeight = imageHeight;
/*  42 */     this.imageWidth = imageWidth;
/*  43 */     this.imageSize = imageSize;
/*     */   }
/*     */ 
/*     */   public Integer getImageId()
/*     */   {
/*  49 */     return this.imageId;
/*     */   }
/*     */ 
/*     */   public void setImageId(Integer imageId) {
/*  53 */     this.imageId = imageId;
/*     */   }
/*     */   @JSON(serialize=false)
/*     */   public BoUserGroup getBoUserGroup() {
/*  57 */     return this.boUserGroup;
/*     */   }
/*     */ 
/*     */   public void setBoUserGroup(BoUserGroup boUserGroup) {
/*  61 */     this.boUserGroup = boUserGroup;
/*     */   }
/*     */ 
/*     */   public String getImageName() {
/*  65 */     return this.imageName;
/*     */   }
/*     */ 
/*     */   public void setImageName(String imageName) {
/*  69 */     this.imageName = imageName;
/*     */   }
/*     */ 
/*     */   public String getImagePath() {
/*  73 */     return this.imagePath;
/*     */   }
/*     */ 
/*     */   public void setImagePath(String imagePath) {
/*  77 */     this.imagePath = imagePath;
/*     */   }
/*     */ 
/*     */   public String getImageFilename() {
/*  81 */     return this.imageFilename;
/*     */   }
/*     */ 
/*     */   public void setImageFilename(String imageFilename) {
/*  85 */     this.imageFilename = imageFilename;
/*     */   }
/*     */ 
/*     */   public Integer getImageHeight() {
/*  89 */     return this.imageHeight;
/*     */   }
/*     */ 
/*     */   public void setImageHeight(Integer imageHeight) {
/*  93 */     this.imageHeight = imageHeight;
/*     */   }
/*     */ 
/*     */   public Integer getImageWidth() {
/*  97 */     return this.imageWidth;
/*     */   }
/*     */ 
/*     */   public void setImageWidth(Integer imageWidth) {
/* 101 */     this.imageWidth = imageWidth;
/*     */   }
/*     */ 
/*     */   public Integer getImageSize() {
/* 105 */     return this.imageSize;
/*     */   }
/*     */ 
/*     */   public void setImageSize(Integer imageSize) {
/* 109 */     this.imageSize = imageSize;
/*     */   }
/*     */ 
/*     */   public String getFileNames() {
/* 113 */     return this.fileNames;
/*     */   }
/*     */ 
/*     */   public void setFileNames(String fileNames) {
/* 117 */     this.fileNames = fileNames;
/*     */   }
/*     */ 
/*     */   public String getFileSizeName() {
/* 121 */     return this.fileSizeName;
/*     */   }
/*     */ 
/*     */   public void setFileSizeName(String fileSizeName) {
/* 125 */     this.fileSizeName = fileSizeName;
/*     */   }
/*     */ 
/*     */   public Integer getType() {
/* 129 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(Integer type) {
/* 133 */     this.type = type;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.bo.BoLaunch
 * JD-Core Version:    0.6.2
 */