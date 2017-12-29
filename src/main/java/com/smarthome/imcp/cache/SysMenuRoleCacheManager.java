/*     */ package com.smarthome.imcp.cache;
/*     */ 
/*     */ import com.smarthome.imcp.common.helper.MenuHelper;
/*     */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*     */ import com.smarthome.imcp.dao.model.system.SysMenuRole;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ import net.sf.ehcache.Statistics;
/*     */ 
/*     */ public final class SysMenuRoleCacheManager
/*     */ {
/*     */   public static Map<SysMenu, Map<SysMenu, List<SysMenu>>> SYS_MENU_MAP;
/*  22 */   public static SysMenuRoleCacheManager instance = new SysMenuRoleCacheManager();
/*     */ 
/*  24 */   private String CACHE_NAME = "sysMenuRoleCache";
/*     */ 
/*     */   public static SysMenuRoleCacheManager getInstance()
/*     */   {
/*  30 */     return instance;
/*     */   }
/*     */ 
/*     */   public CacheInfo getCacheInfo()
/*     */   {
/*  39 */     CacheManager manager = CacheManager.getInstance();
/*  40 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  41 */     CacheInfo cacheInfo = new CacheInfo();
/*  42 */     cacheInfo.setCacheName(this.CACHE_NAME);
/*  43 */     cacheInfo.setCacheHits(Long.valueOf(cache.getStatistics().getCacheHits()));
/*  44 */     cacheInfo.setCacheMisses(Long.valueOf(cache.getStatistics().getCacheMisses()));
/*  45 */     cacheInfo.setMemoryStoreSize(Long.valueOf(cache.calculateInMemorySize()));
/*  46 */     cacheInfo.setSize(Integer.valueOf(cache.getSize()));
/*  47 */     return cacheInfo;
/*     */   }
/*     */ 
/*     */   public synchronized void init(List<SysMenuRole> menuRoleList, Map<SysMenu, Map<SysMenu, List<SysMenu>>> sysMenuMap)
/*     */   {
/*  60 */     SYS_MENU_MAP = sysMenuMap;
/*     */ 
/*  63 */     Iterator iter = sysMenuMap.keySet().iterator();
/*     */     SysMenu menu1;
/*  64 */     while (iter
/*  64 */       .hasNext()) {
/*  65 */       menu1 = (SysMenu)iter.next();
/*  66 */       Map menuMap = (Map)sysMenuMap.get(menu1);
/*  67 */       String jqueryTree = MenuHelper.genJqueryTree(menuMap);
/*     */ 
/*  69 */       add("SUP", menu1.getMenuCode(), jqueryTree);
/*     */     }
/*     */ 
/*  72 */     for (SysMenuRole sysMenuRole : menuRoleList)
/*  73 */       if (!"SUP".equals(sysMenuRole.getMenuRoleCode()))
/*     */       {
/*  76 */         add(sysMenuRole);
/*     */       }
/*     */   }
/*     */ 
/*     */   public synchronized void add(SysMenuRole sysMenuRole)
/*     */   {
/*  87 */     Iterator iter = SYS_MENU_MAP.keySet().iterator();
/*  88 */     while (iter
/*  88 */       .hasNext()) {
/*  89 */       SysMenu menu1 = (SysMenu)iter.next();
/*  90 */       Map menuMap = (Map)SYS_MENU_MAP.get(menu1);
/*     */ 
/*  92 */       String jqueryTree = MenuHelper.genJqueryTreeForRole(menuMap, 
/*  93 */         sysMenuRole.getSysRoleMenuses());
/*  94 */       add(sysMenuRole.getMenuRoleCode(), menu1.getMenuCode(), 
/*  95 */         jqueryTree);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void add(String menuRoleCode, String menuCode, String jqueryTree)
/*     */   {
/* 109 */     CacheManager manager = CacheManager.getInstance();
/* 110 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 111 */     Element element = cache.get(menuRoleCode);
/* 112 */     Map mpMenu = null;
/* 113 */     if (element == null) {
/* 114 */       mpMenu = new HashMap();
/* 115 */       element = new Element(menuRoleCode, mpMenu);
/* 116 */       cache.put(element);
/*     */     } else {
/* 118 */       mpMenu = (Map)element.getValue();
/*     */     }
/* 120 */     mpMenu.put(menuCode.toString(), jqueryTree);
/*     */   }
/*     */ 
/*     */   public String getRoleMenu(String menuRoleCode, String menuCode)
/*     */   {
/* 125 */     CacheManager manager = CacheManager.getInstance();
/* 126 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 127 */     Element element = cache.get(menuRoleCode);
/* 128 */     Map mpMenu = null;
/* 129 */     if (element != null) {
/* 130 */       mpMenu = (Map)element.getValue();
/*     */     }
/* 132 */     if ((mpMenu != null) && (mpMenu.containsKey(menuCode))) {
/* 133 */       return (String)mpMenu.get(menuCode);
/*     */     }
/* 135 */     return "";
/*     */   }
/*     */ 
/*     */   public synchronized void destroy() {
/* 139 */     SYS_MENU_MAP.clear();
/* 140 */     SYS_MENU_MAP = null;
/* 141 */     CacheManager manager = CacheManager.getInstance();
/* 142 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 143 */     cache.removeAll();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysMenuRoleCacheManager
 * JD-Core Version:    0.6.2
 */