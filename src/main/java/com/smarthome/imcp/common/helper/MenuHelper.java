/*     */ package com.smarthome.imcp.common.helper;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*     */ import com.smarthome.imcp.dao.model.system.SysRoleMenus;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ public final class MenuHelper
/*     */ {
/*     */   public static String genJqueryTree(Map<SysMenu, List<SysMenu>> menuMap)
/*     */   {
/*  15 */     StringBuffer sb = new StringBuffer("");
/*  16 */     Iterator iter = menuMap.keySet().iterator();
/*  17 */     while (iter
/*  17 */       .hasNext()) {
/*  18 */       SysMenu menu1 = (SysMenu)iter.next();
/*  19 */       List<SysMenu> sysMenuList = (List)menuMap.get(menu1);
/*  20 */       if ((sysMenuList != null) && (sysMenuList.size() > 0)) {
/*  21 */         sb.append("<div class=\"accordionHeader\">");
/*  22 */         sb.append("<h2><span>Folder</span>");
/*  23 */         sb.append(menu1.getMenuName());
/*  24 */         sb.append("</h2>");
/*  25 */         sb.append("</div>");
/*  26 */         sb.append("<div class=\"accordionContent\">");
/*  27 */         sb.append("<ul class=\"tree treeFolder\">");
/*  28 */         for (SysMenu menu2 : sysMenuList) {
/*  29 */           sb.append("<li><a href=\"");
/*  30 */           sb.append(menu2.getMenuUrl());
/*  31 */           sb.append("\"");
/*  32 */           sb.append(" target=\"navTab\"");
/*  33 */           sb.append(" rel=\"");
/*  34 */           sb.append(menu2.getMenuCode());
/*  35 */           sb.append("\"");
/*  36 */           sb.append(" tname=\"");
/*  37 */           sb.append(menu2.getMenuCode());
/*  38 */           sb.append("\"");
/*  39 */           sb.append(" tvalue=\"");
/*  40 */           sb.append(menu2.getMenuCode());
/*  41 */           sb.append("\"");
/*  42 */           sb.append(">");
/*  43 */           sb.append(menu2.getMenuName());
/*  44 */           sb.append("</a></li>");
/*     */         }
/*  46 */         sb.append("</ul>");
/*  47 */         sb.append("</div>");
/*     */       }
/*     */     }
/*  50 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String genJqueryTreeForRole(Map<SysMenu, List<SysMenu>> menuMap, Set<SysRoleMenus> sysRoleMenusSet)
/*     */   {
/*  56 */     StringBuffer sb = new StringBuffer("");
/*  57 */     Set sysMenuCodeSet = getRoleMenus(sysRoleMenusSet);
/*  58 */     Iterator iter = menuMap.keySet().iterator();
/*  59 */     while (iter
/*  59 */       .hasNext()) {
/*  60 */       SysMenu menu1 = (SysMenu)iter.next();
/*  61 */       List<SysMenu> sysMenuList = (List)menuMap.get(menu1);
/*  62 */       if ((sysMenuList != null) && (sysMenuList.size() > 0)) {
/*  63 */         boolean hasChild = false;
/*  64 */         StringBuffer sbLeaf = new StringBuffer();
/*  65 */         for (SysMenu menu2 : sysMenuList)
/*     */         {
/*  67 */           if (sysMenuCodeSet.contains(menu2.getMenuCode())) {
/*  68 */             hasChild = true;
/*  69 */             sbLeaf.append("<li><a href=\"");
/*  70 */             sbLeaf.append(menu2.getMenuUrl());
/*  71 */             sbLeaf.append("\"");
/*  72 */             sbLeaf.append(" target=\"navTab\"");
/*  73 */             sbLeaf.append(" rel=\"");
/*  74 */             sbLeaf.append(menu2.getMenuCode());
/*  75 */             sbLeaf.append("\"");
/*  76 */             sbLeaf.append(" tname=\"");
/*  77 */             sbLeaf.append(menu2.getMenuCode());
/*  78 */             sbLeaf.append("\"");
/*  79 */             sbLeaf.append(" tvalue=\"");
/*  80 */             sbLeaf.append(menu2.getMenuCode());
/*  81 */             sbLeaf.append("\"");
/*  82 */             sbLeaf.append(">");
/*  83 */             sbLeaf.append(menu2.getMenuName());
/*  84 */             sbLeaf.append("</a></li>");
/*     */           }
/*     */         }
/*  87 */         if (hasChild) {
/*  88 */           sb.append("<div class=\"accordionHeader\">");
/*  89 */           sb.append("<h2><span>Folder</span>");
/*  90 */           sb.append(menu1.getMenuName());
/*  91 */           sb.append("</h2>");
/*  92 */           sb.append("</div>");
/*  93 */           sb.append("<div class=\"accordionContent\">");
/*  94 */           sb.append("<ul class=\"tree treeFolder\">");
/*  95 */           sb.append(sbLeaf);
/*  96 */           sb.append("</ul>");
/*  97 */           sb.append("</div>");
/*     */         }
/*     */       }
/*     */     }
/* 101 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String genJqueryCheckTree(String jqueryTree, List<SysRoleMenus> sysRoleMenusList)
/*     */   {
/* 106 */     String jqueryCheckTree = jqueryTree;
/* 107 */     for (SysRoleMenus sysRoleMenus : sysRoleMenusList) {
/* 108 */       String subString = "tname=\"" + 
/* 109 */         sysRoleMenus.getSysMenu().getMenuCode() + "\"";
/* 110 */       int pos = jqueryCheckTree.indexOf(subString);
/* 111 */       if (pos >= 0) {
/* 112 */         jqueryCheckTree = jqueryCheckTree.substring(0, pos) + 
/* 113 */           "checked=\"true\" " + jqueryCheckTree.substring(pos);
/*     */       }
/*     */     }
/* 116 */     return jqueryCheckTree;
/*     */   }
/*     */ 
/*     */   public static Set<String> getRoleMenus(Set<SysRoleMenus> sysRoleMenusSet) {
/* 120 */     Set sysMenuCodeSet = new HashSet();
/* 121 */     for (SysRoleMenus sysRoleMenus : sysRoleMenusSet) {
/* 122 */       sysMenuCodeSet.add(sysRoleMenus.getSysMenu().getMenuCode()
/* 123 */         .toString());
/*     */     }
/* 125 */     return sysMenuCodeSet;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.helper.MenuHelper
 * JD-Core Version:    0.6.2
 */