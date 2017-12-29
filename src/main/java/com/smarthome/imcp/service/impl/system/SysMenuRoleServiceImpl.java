/*     */ package com.smarthome.imcp.service.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysMenuRoleCacheManager;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenuRole;
/*     */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*     */ import com.smarthome.imcp.dao.model.system.SysMenuRole;
/*     */ import com.smarthome.imcp.dao.model.system.SysRoleMenus;
/*     */ import com.smarthome.imcp.dao.system.SysMenuRoleDaoIface;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.system.SysMenuRoleServiceIface;
/*     */ import com.smarthome.imcp.service.system.SysRoleMenusServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("sysMenuRoleService")
/*     */ public class SysMenuRoleServiceImpl extends AbstractBasicService<SysMenuRole, Serializable>
/*     */   implements SysMenuRoleServiceIface<SysMenuRole, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysMenuRoleDaoIface<SysMenuRole, Serializable> sysMenuRoleDao;
/*     */ 
/*     */   @Autowired
/*     */   private SysRoleMenusServiceIface<SysRoleMenus, Serializable> sysRoleMenusService;
/*     */ 
/*     */   public List<SysMenuRole> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  40 */     SearchCriteriaMenuRole searchCriteriaMenuRole = (SearchCriteriaMenuRole)searchCriteria;
/*  41 */     if (chkCriteriaValid(searchCriteriaMenuRole)) {
/*  42 */       return this.sysMenuRoleDao.getList(searchCriteriaMenuRole, page);
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(SysMenuRole sysMenuRole)
/*     */   {
/*  50 */     if (this.sysMenuRoleDao
/*  50 */       .isExistsByMenuRoleName(sysMenuRole.getMenuRoleName())) {
/*  51 */       throw new BusinessException("此菜单角色名称已存在，不能重复添加！");
/*     */     }
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */   public SysMenuRole save(SysMenuRole sysMenuRole)
/*     */   {
/*  58 */     if (chkSaveValid(sysMenuRole)) {
/*  59 */       this.sysMenuRoleDao.save(sysMenuRole);
/*     */     }
/*  61 */     return sysMenuRole;
/*     */   }
/*     */ 
/*     */   public void save(SysMenuRole sysMenuRole, String menuCodes)
/*     */   {
/*  66 */     save(sysMenuRole);
/*     */ 
/*  68 */     Set sysRoleMenuses = new HashSet();
/*     */ 
/*  70 */     StringTokenizer st = new StringTokenizer(menuCodes, ",");
/*  71 */     while (st.hasMoreElements()) {
/*  72 */       String menuCode = st.nextToken();
/*  73 */       if (!StringUtils.isEmpty(menuCode))
/*     */       {
/*  76 */         SysRoleMenus sysRoleMenus = new SysRoleMenus();
/*     */ 
/*  78 */         SysMenu sysMenu = new SysMenu();
/*  79 */         sysMenu.setMenuCode(menuCode);
/*     */ 
/*  81 */         sysRoleMenus.setSysMenu(sysMenu);
/*  82 */         sysRoleMenus.setSysMenuRole(sysMenuRole);
/*     */ 
/*  84 */         this.sysRoleMenusService.save(sysRoleMenus);
/*  85 */         sysRoleMenuses.add(sysRoleMenus);
/*     */       }
/*     */     }
/*  87 */     sysMenuRole.setSysRoleMenuses(sysRoleMenuses);
/*     */ 
/*  89 */     SysMenuRoleCacheManager.getInstance().add(sysMenuRole);
/*     */   }
/*     */ 
/*     */   public SysMenuRole findByKey(Serializable id)
/*     */   {
/*  94 */     return (SysMenuRole)this.sysMenuRoleDao.findById(id);
/*     */   }
/*     */ 
/*     */   public SysMenuRole update(SysMenuRole sysMenuRole)
/*     */   {
/*  99 */     if (chkUpdateValid(sysMenuRole)) {
/* 100 */       this.sysMenuRoleDao.update(sysMenuRole);
/*     */     }
/* 102 */     return sysMenuRole;
/*     */   }
/*     */ 
/*     */   public void update(SysMenuRole sysMenuRole, String menuCodes)
/*     */   {
/* 107 */     this.sysRoleMenusService.deleteAllByMenuRoleCode(sysMenuRole
/* 108 */       .getMenuRoleCode());
/*     */ 
/* 110 */     update(sysMenuRole);
/*     */ 
/* 112 */     Set sysRoleMenuses = new HashSet();
/*     */ 
/* 114 */     StringTokenizer st = new StringTokenizer(menuCodes, ",");
/* 115 */     while (st.hasMoreElements()) {
/* 116 */       String menuCode = st.nextToken();
/* 117 */       if (!StringUtils.isEmpty(menuCode))
/*     */       {
/* 120 */         SysRoleMenus sysRoleMenus = new SysRoleMenus();
/*     */ 
/* 122 */         SysMenu sysMenu = new SysMenu();
/* 123 */         sysMenu.setMenuCode(menuCode);
/*     */ 
/* 125 */         sysRoleMenus.setSysMenu(sysMenu);
/* 126 */         sysRoleMenus.setSysMenuRole(sysMenuRole);
/*     */ 
/* 128 */         this.sysRoleMenusService.save(sysRoleMenus);
/* 129 */         sysRoleMenuses.add(sysRoleMenus);
/*     */       }
/*     */     }
/* 131 */     sysMenuRole.setSysRoleMenuses(sysRoleMenuses);
/*     */ 
/* 133 */     SysMenuRoleCacheManager.getInstance().add(sysMenuRole);
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String menuRoleCode)
/*     */   {
/* 138 */     if ("SUP".equals(menuRoleCode)) {
/* 139 */       throw new BusinessException("默认角色无法删除！");
/*     */     }
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String menuRoleCode)
/*     */   {
/* 146 */     if (chkDeleteValid(menuRoleCode))
/* 147 */       this.sysMenuRoleDao.deleteByKey(menuRoleCode);
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String menuRoleCodes)
/*     */   {
/* 153 */     StringTokenizer st = new StringTokenizer(menuRoleCodes, ",");
/* 154 */     while (st.hasMoreElements()) {
/* 155 */       String menuRoleCode = st.nextToken();
/* 156 */       deleteByKey(menuRoleCode);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysMenuRoleServiceImpl
 * JD-Core Version:    0.6.2
 */