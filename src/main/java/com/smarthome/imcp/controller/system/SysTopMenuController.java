/*     */ package com.smarthome.imcp.controller.system;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysMenuRoleCacheManager;
/*     */ import com.smarthome.imcp.common.MenuConst;
/*     */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*     */ import com.smarthome.imcp.dao.vo.system.MenuPageVo;
/*     */ import com.smarthome.imcp.helper.result.DocumentZtreePage;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import com.smarthome.imcp.service.BasicServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ public class SysTopMenuController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BasicServiceIface<SysMenu, Serializable> sysMenuService;
/*     */ 
/*     */   @RequestMapping({"system/dispatchTopMenuPage.do"})
/*     */   public ModelAndView dispatchTopMenuPage(HttpServletRequest request)
/*     */   {
/*  39 */     CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("USER_INFO");
/*  40 */     String menuRole = currentUser.getMenuRole();
/*  41 */     StringBuffer sb = new StringBuffer();
/*  42 */     boolean isSelect = false;
/*  43 */     int navMenuCount = 0;
/*     */ 
/*  45 */     boolean hasEdit = true;
/*  46 */     boolean hasApprove = true;
/*     */ 
/*  48 */     for (SysMenu sysMenu : MenuConst.TOP_MENU)
/*     */     {
/*  50 */       if ((StringUtils.isNotEmpty(SysMenuRoleCacheManager.getInstance()
/*  50 */         .getRoleMenu(menuRole, sysMenu.getMenuCode()))) && (
/*  51 */         (!"EDIT".equals(sysMenu.getMenuCode())) || (hasEdit)))
/*     */       {
/*  54 */         if ((!"APPROVE".equals(sysMenu.getMenuCode())) || (hasApprove))
/*     */         {
/*  69 */           sb.append("<li");
/*  70 */           if (!isSelect) {
/*  71 */             sb.append(" class=\"selected\"");
/*  72 */             isSelect = true;
/*     */           }
/*  74 */           sb.append("><a href=\"system/dispatchMenuPage.do?menuCode=");
/*  75 */           sb.append(sysMenu.getMenuCode());
/*  76 */           sb.append("\"><span>").append(sysMenu.getMenuName()).append("</span></a></li>");
/*     */ 
/*  78 */           navMenuCount++;
/*     */         }
/*     */       }
/*     */     }
/*  82 */     currentUser.setNavMenuCount(navMenuCount);
/*  83 */     String topMenu = sb.toString();
/*  84 */     return new ModelAndView("navMenu", "topMenu", topMenu);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"system/dispatchMenuFirstPage.do"})
/*     */   public ModelAndView dispatchMenuFirstPage(String menuCode, HttpServletRequest request)
/*     */   {
/*  94 */     CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("USER_INFO");
/*  95 */     int navMenuCount = currentUser.getNavMenuCount();
/*  96 */     ModelAndView modelAndView = dispatchMenuPage(menuCode, request);
/*     */ 
/*  98 */     String menuTree = (String)modelAndView.getModel().get("menuTree");
/*  99 */     StringBuffer sb = new StringBuffer();
/* 100 */     for (int i = 0; i < navMenuCount; i++) {
/* 101 */       if (i == 0) {
/* 102 */         sb.append("<div class=\"accordion\" fillSpace=\"sidebar\">");
/* 103 */         sb.append(menuTree);
/*     */       } else {
/* 105 */         sb.append("<div class=\"accordion dwz-accordion\" fillSpace=\"sidebar\" style=\"display: none;\">");
/*     */       }
/* 107 */       sb.append("</div>");
/*     */     }
/* 109 */     menuTree = sb.toString();
/* 110 */     return new ModelAndView("sidebarMenu", "menuTree", menuTree);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"system/dispatchMenuPage.do"})
/*     */   public ModelAndView dispatchMenuPage(String menuCode, HttpServletRequest request)
/*     */   {
/* 120 */     MenuPageVo menuPageVo = new MenuPageVo("success", "");
/*     */ 
/* 122 */     CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("USER_INFO");
/* 123 */     String userType = currentUser.getUserType();
/* 124 */     String menuRole = currentUser.getMenuRole();
/* 125 */     String menuTree = "";
/*     */ 
/* 127 */     if (StringUtils.isEmpty(menuCode)) {
/* 128 */       for (SysMenu sysMenu : MenuConst.TOP_MENU)
/* 129 */         if (StringUtils.isNotEmpty(SysMenuRoleCacheManager.getInstance().getRoleMenu(menuRole, sysMenu.getMenuCode()))) {
/* 130 */           menuPageVo = getMenuTreeByMenuCode(currentUser, sysMenu.getMenuCode());
/* 131 */           menuTree = menuPageVo.getMenuTree();
/* 132 */           if (StringUtils.isNotEmpty(menuTree))
/*     */             break;
/*     */         }
/*     */     }
/*     */     else
/*     */     {
/* 138 */       menuPageVo = getMenuTreeByMenuCode(currentUser, menuCode);
/* 139 */       menuTree = menuPageVo.getMenuTree();
/* 140 */       if ("PORTAL".equals(menuCode)) {
/* 141 */         menuPageVo.setResultCode("templetPage");
/*     */       }
/*     */     }
/* 144 */     return new ModelAndView("sidebarMenu", "menuTree", menuTree);
/*     */   }
/*     */ 
/*     */   private DocumentZtreePage getDocumentZtreePage(String menuCode) {
/* 148 */     DocumentZtreePage documentZtreePage = null;
/*     */ 
/* 150 */     if ("DOCUMENT".equals(menuCode))
/* 151 */       documentZtreePage = new DocumentZtreePage("./cms/dispatchSiteColumnZTreeMenu.action", "cms/dispatchDocumentListPage.action", "CMS0003");
/* 152 */     else if ("DRAFT".equals(menuCode))
/* 153 */       documentZtreePage = new DocumentZtreePage("./cms/dispatchSiteColumnZTreeMenu.action", "cms/dispatchDocumentDraftListPage.action", "DRA0001");
/* 154 */     else if ("EDIT".equals(menuCode))
/* 155 */       documentZtreePage = new DocumentZtreePage("./cms/dispatchSiteColumnZTreeMenu.action", "cms/dispatchDocumentEditListPage.action", "EDI0001");
/* 156 */     else if ("APPROVE".equals(menuCode))
/* 157 */       documentZtreePage = new DocumentZtreePage("./cms/dispatchSiteColumnZTreeMenu.action", "cms/dispatchDocumentApproveListPage.action", "APP0001");
/* 158 */     else if ("DEPLOY".equals(menuCode)) {
/* 159 */       documentZtreePage = new DocumentZtreePage("./cms/dispatchSiteColumnZTreeMenu.action", "cms/dispatchDocumentDeployListPage.action", "DEP0001");
/*     */     }
/* 161 */     return documentZtreePage;
/*     */   }
/*     */ 
/*     */   private MenuPageVo getMenuTreeByMenuCode(CurrentUser currentUser, String menuCode)
/*     */   {
/* 166 */     MenuPageVo menuPageVo = new MenuPageVo("success", "");
/* 167 */     String menuTree = "";
/*     */ 
/* 207 */     menuTree = SysMenuRoleCacheManager.getInstance().getRoleMenu(currentUser.getMenuRole(), menuCode);
/*     */ 
/* 209 */     menuPageVo.setMenuTree(menuTree);
/* 210 */     return menuPageVo;
/*     */   }
/*     */ 
/*     */   public String dispatchMenuPageByType(HttpServletRequest request)
/*     */   {
/* 219 */     CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("USER_INFO");
/*     */ 
/* 230 */     return "success";
/*     */   }
/*     */ 
/*     */   public void dispatchColumnZTreeMenu(HttpServletRequest request)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void dispatchZTreeMenu(HttpServletRequest request)
/*     */   {
/* 248 */     CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("USER_INFO");
/*     */   }
/*     */ 
/*     */   public void dispatchTempletMenu(HttpServletRequest request)
/*     */   {
/* 259 */     CurrentUser currentUser = (CurrentUser)request.getSession().getAttribute("USER_INFO");
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.system.SysTopMenuController
 * JD-Core Version:    0.6.2
 */