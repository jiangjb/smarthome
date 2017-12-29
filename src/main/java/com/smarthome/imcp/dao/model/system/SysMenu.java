/*     */ package com.smarthome.imcp.dao.model.system;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class SysMenu
/*     */   implements Serializable
/*     */ {
	        private int menuId;//new add
/*     */   private String menuCode;
/*     */   private String menuPcode;
/*     */   private String menuName;
/*     */   private Integer menuLevel;
/*     */   private String menuUrl;
/*     */   private String menuIcon;
/*     */   private String menuIconopen;
/*     */   private String menuOpened;
/*     */   private String menuIconcls;
/*     */   private String menuVisiable;
/*     */   private Integer mntPosition;
/*     */ 
/*     */   public SysMenu()
/*     */   {
/*     */   }
/*     */ 
/*     */   public SysMenu(String menuCode)
/*     */   {
/*  34 */     this.menuCode = menuCode;
/*     */   }
/*     */ 
/*     */   public SysMenu(String menuCode, String menuPcode, String menuName, String menuUrl, String menuIcon, String menuIconopen, String menuOpened, String menuIconcls, String menuVisiable, Integer mntPosition)
/*     */   {
/*  42 */     this.menuCode = menuCode;
/*  43 */     this.menuPcode = menuPcode;
/*  44 */     this.menuName = menuName;
/*  45 */     this.menuUrl = menuUrl;
/*  46 */     this.menuIcon = menuIcon;
/*  47 */     this.menuIconopen = menuIconopen;
/*  48 */     this.menuOpened = menuOpened;
/*  49 */     this.menuIconcls = menuIconcls;
/*  50 */     this.menuVisiable = menuVisiable;
/*  51 */     this.mntPosition = mntPosition;
/*     */   }
/*     */   public int getMenuId()
/*     */   {
/*  57 */     return this.menuId;
/*     */   }
/*     */ 
/*     */   public void setMenuId(int menuId) {
/*  61 */     this.menuId = menuId;
/*     */   }

/*     */   public String getMenuCode()
/*     */   {
/*  57 */     return this.menuCode;
/*     */   }
/*     */ 
/*     */   public void setMenuCode(String menuCode) {
/*  61 */     this.menuCode = menuCode;
/*     */   }
/*     */ 
/*     */   public String getMenuPcode() {
/*  65 */     return this.menuPcode;
/*     */   }
/*     */ 
/*     */   public void setMenuPcode(String menuPcode) {
/*  69 */     this.menuPcode = menuPcode;
/*     */   }
/*     */ 
/*     */   public String getMenuName() {
/*  73 */     return this.menuName;
/*     */   }
/*     */ 
/*     */   public void setMenuName(String menuName) {
/*  77 */     this.menuName = menuName;
/*     */   }
/*     */ 
/*     */   public String getMenuUrl() {
/*  81 */     return this.menuUrl;
/*     */   }
/*     */ 
/*     */   public void setMenuUrl(String menuUrl) {
/*  85 */     this.menuUrl = menuUrl;
/*     */   }
/*     */ 
/*     */   public String getMenuIcon() {
/*  89 */     return this.menuIcon;
/*     */   }
/*     */ 
/*     */   public void setMenuIcon(String menuIcon) {
/*  93 */     this.menuIcon = menuIcon;
/*     */   }
/*     */ 
/*     */   public String getMenuIconopen() {
/*  97 */     return this.menuIconopen;
/*     */   }
/*     */ 
/*     */   public void setMenuIconopen(String menuIconopen) {
/* 101 */     this.menuIconopen = menuIconopen;
/*     */   }
/*     */ 
/*     */   public String getMenuOpened() {
/* 105 */     return this.menuOpened;
/*     */   }
/*     */ 
/*     */   public void setMenuOpened(String menuOpened) {
/* 109 */     this.menuOpened = menuOpened;
/*     */   }
/*     */ 
/*     */   public String getMenuIconcls() {
/* 113 */     return this.menuIconcls;
/*     */   }
/*     */ 
/*     */   public void setMenuIconcls(String menuIconcls) {
/* 117 */     this.menuIconcls = menuIconcls;
/*     */   }
/*     */ 
/*     */   public String getMenuVisiable() {
/* 121 */     return this.menuVisiable;
/*     */   }
/*     */ 
/*     */   public void setMenuVisiable(String menuVisiable) {
/* 125 */     this.menuVisiable = menuVisiable;
/*     */   }
/*     */ 
/*     */   public Integer getMntPosition() {
/* 129 */     return this.mntPosition;
/*     */   }
/*     */ 
/*     */   public void setMntPosition(Integer mntPosition) {
/* 133 */     this.mntPosition = mntPosition;
/*     */   }
/*     */ 
/*     */   public Integer getMenuLevel() {
/* 137 */     return this.menuLevel;
/*     */   }
/*     */ 
/*     */   public void setMenuLevel(Integer menuLevel) {
/* 141 */     this.menuLevel = menuLevel;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.model.system.SysMenu
 * JD-Core Version:    0.6.2
 */