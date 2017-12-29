/*     */ package com.smarthome.imcp.dao.model.cy;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.AbstractData;
/*     */ 
/*     */ public class Product extends AbstractData
/*     */ {
/*     */   private Integer id;
/*     */   private String code;
/*     */   private String name;
/*     */   private String img;
/*     */   private Double price;
/*     */   private Double oidPrice;
/*     */   private String rmk;
/*     */   private Integer crystal;
/*     */ 
/*     */   public Product()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Product(String code, String name, String img, Double price, Double oidPrice, String rmk)
/*     */   {
/*  34 */     this.code = code;
/*  35 */     this.name = name;
/*  36 */     this.img = img;
/*  37 */     this.price = price;
/*  38 */     this.oidPrice = oidPrice;
/*  39 */     this.rmk = rmk;
/*     */   }
/*     */ 
/*     */   public Integer getId()
/*     */   {
/*  45 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(Integer id) {
/*  49 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getCode() {
/*  53 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code) {
/*  57 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getName() {
/*  61 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  65 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getImg() {
/*  69 */     return this.img;
/*     */   }
/*     */ 
/*     */   public void setImg(String img) {
/*  73 */     this.img = img;
/*     */   }
/*     */ 
/*     */   public Double getPrice() {
/*  77 */     return this.price;
/*     */   }
/*     */ 
/*     */   public void setPrice(Double price) {
/*  81 */     this.price = price;
/*     */   }
/*     */ 
/*     */   public Double getOidPrice() {
/*  85 */     return this.oidPrice;
/*     */   }
/*     */ 
/*     */   public void setOidPrice(Double oidPrice) {
/*  89 */     this.oidPrice = oidPrice;
/*     */   }
/*     */ 
/*     */   public String getRmk() {
/*  93 */     return this.rmk;
/*     */   }
/*     */ 
/*     */   public void setRmk(String rmk) {
/*  97 */     this.rmk = rmk;
/*     */   }
/*     */ 
/*     */   public Integer getCrystal() {
/* 101 */     return this.crystal;
/*     */   }
/*     */ 
/*     */   public void setCrystal(Integer crystal) {
/* 105 */     this.crystal = crystal;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.cy.Product
 * JD-Core Version:    0.6.2
 */