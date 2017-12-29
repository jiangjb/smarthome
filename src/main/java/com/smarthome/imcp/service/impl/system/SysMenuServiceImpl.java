/*     */ package com.smarthome.imcp.service.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenu;
/*     */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*     */ import com.smarthome.imcp.dao.system.SysMenuDaoIface;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.BasicServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("sysMenuService")
/*     */ public class SysMenuServiceImpl extends AbstractBasicService<SysMenu, Serializable>
/*     */   implements BasicServiceIface<SysMenu, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysMenuDaoIface<SysMenu, Serializable> sysMenuDao;
/*     */ 
/*     */   public List<SysMenu> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  30 */     SearchCriteriaMenu searchCriteriaMenu = (SearchCriteriaMenu)searchCriteria;
/*  31 */     if (chkCriteriaValid(searchCriteriaMenu)) {
/*  32 */       return this.sysMenuDao.getList(searchCriteriaMenu, page);
/*     */     }
/*  34 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(SysMenu sysMenu)
/*     */   {
/*  39 */     if (this.sysMenuDao.isExistsByMenuCode(sysMenu.getMenuCode())) {
/*  40 */       throw new BusinessException("此菜单代码已存在，不能重复添加！");
/*     */     }
/*     */ 
/*  43 */     if (this.sysMenuDao.isExistsByMenuPcodeName(sysMenu.getMenuPcode(), 
/*  43 */       sysMenu.getMenuName())) {
/*  44 */       throw new BusinessException("此菜单父代码下菜单名称已存在，不能重复添加！");
/*     */     }
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   public SysMenu save(SysMenu sysMenu)
/*     */   {
/*  51 */     if (GlobalMethod.isNullorEmpty(sysMenu.getMenuPcode())) {
/*  52 */       sysMenu.setMenuPcode("0");
/*     */     }
/*  54 */     if (chkSaveValid(sysMenu)) {
/*  55 */       this.sysMenuDao.save(sysMenu);
/*     */     }
/*  57 */     return sysMenu;
/*     */   }
/*     */ 
/*     */   public SysMenu findByKey(Serializable id)
/*     */   {
/*  62 */     SysMenu sysMenu = (SysMenu)this.sysMenuDao.findById(id);
/*  63 */     return sysMenu;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(SysMenu sysMenu)
/*     */   {
/*  69 */     if (this.sysMenuDao.isExistsByMenuCodePcodeName(sysMenu.getMenuCode(), 
/*  69 */       sysMenu.getMenuPcode(), sysMenu.getMenuName())) {
/*  70 */       throw new BusinessException("此菜单父代码下菜单名称已存在，不能进行修改操作！");
/*     */     }
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   public SysMenu update(SysMenu sysMenu)
/*     */   {
/*  77 */     if (chkUpdateValid(sysMenu)) {
/*  78 */       this.sysMenuDao.update(sysMenu);
/*     */     }
/*  80 */     return sysMenu;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String menuCode)
/*     */   {
/*  85 */     if (this.sysMenuDao.isExistsByMenuPcodeName(menuCode, "")) {
/*  86 */       throw new BusinessException("此菜单下存在子菜单，不能进行删除操作！");
/*     */     }
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String menuCode)
/*     */   {
/*  93 */     if (chkDeleteValid(menuCode))
/*  94 */       this.sysMenuDao.deleteByKey(menuCode);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String menuCodes)
/*     */   {
/* 100 */     StringTokenizer st = new StringTokenizer(menuCodes, ",");
/* 101 */     while (st.hasMoreElements()) {
/* 102 */       String menuCode = st.nextToken();
/* 103 */       deleteByKey(menuCode);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysMenuServiceImpl
 * JD-Core Version:    0.6.2
 */