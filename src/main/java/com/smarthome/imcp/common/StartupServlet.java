/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import com.smarthome.imcp.cache.SysDictCacheManager;
/*    */ import com.smarthome.imcp.cache.SysDictCodeCacheManager;
/*    */ import com.smarthome.imcp.cache.SysMenuRoleCacheManager;
/*    */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*    */ import com.smarthome.imcp.cache.SysParamCodeCacheManager;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServlet;
/*    */ import net.sf.ehcache.CacheManager;
/*    */ 
/*    */ public class StartupServlet extends HttpServlet
/*    */ {
/*    */   public void init()
/*    */     throws ServletException
/*    */   {
/* 47 */     super.init();
/* 48 */     restart();
/*    */   }
/*    */ 
/*    */   public void destroy() {
/* 52 */     SysParamCodeCacheManager.getInstance().destroy();
/* 53 */     SysParamCacheManager.getInstance().destroy();
/* 54 */     SysDictCodeCacheManager.getInstance().destroy();
/* 55 */     SysDictCacheManager.getInstance().destroy();
/* 56 */     SysMenuRoleCacheManager.getInstance().destroy();
/* 57 */     CacheManager manager = CacheManager.getInstance();
/* 58 */     manager.shutdown();
/*    */   }
/*    */ 
/*    */   public void restart()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.StartupServlet
 * JD-Core Version:    0.6.2
 */