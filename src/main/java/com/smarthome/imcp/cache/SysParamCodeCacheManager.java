/*     */ package com.smarthome.imcp.cache;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.system.SysParamCode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ import net.sf.ehcache.Statistics;
/*     */ 
/*     */ public final class SysParamCodeCacheManager
/*     */ {
/*  15 */   public static SysParamCodeCacheManager instance = new SysParamCodeCacheManager();
/*     */ 
/*  17 */   private String CACHE_NAME = "sysParamCodeCache";
/*     */ 
/*     */   public static SysParamCodeCacheManager getInstance()
/*     */   {
/*  23 */     return instance;
/*     */   }
/*     */ 
/*     */   public CacheInfo getCacheInfo()
/*     */   {
/*  32 */     CacheManager manager = CacheManager.getInstance();
/*  33 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  34 */     CacheInfo cacheInfo = new CacheInfo();
/*  35 */     cacheInfo.setCacheName(this.CACHE_NAME);
/*  36 */     cacheInfo.setCacheHits(Long.valueOf(cache.getStatistics().getCacheHits()));
/*  37 */     cacheInfo.setCacheMisses(Long.valueOf(cache.getStatistics().getCacheMisses()));
/*  38 */     cacheInfo.setMemoryStoreSize(Long.valueOf(cache.calculateInMemorySize()));
/*  39 */     cacheInfo.setSize(Integer.valueOf(cache.getSize()));
/*  40 */     return cacheInfo;
/*     */   }
/*     */ 
/*     */   public synchronized void init(List<SysParamCode> list)
/*     */   {
/*  45 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/*  46 */       SysParamCode sysParamCode = (SysParamCode)iter.next();
/*  47 */       add(sysParamCode);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void destroy() {
/*  52 */     CacheManager manager = CacheManager.getInstance();
/*  53 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  54 */     cache.removeAll();
/*     */   }
/*     */ 
/*     */   public SysParamCode getSysParamCode(String paramCode) {
/*  58 */     CacheManager manager = CacheManager.getInstance();
/*  59 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  60 */     Element element = cache.get(paramCode);
/*  61 */     if (element == null) {
/*  62 */       return new SysParamCode(paramCode, paramCode);
/*     */     }
/*  64 */     return (SysParamCode)element.getValue();
/*     */   }
/*     */ 
/*     */   public List<SysParamCode> getList()
/*     */   {
/*  69 */     CacheManager manager = CacheManager.getInstance();
/*  70 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  71 */     List result = new ArrayList();
/*  72 */     List list = cache.getKeys();
/*  73 */     int i = 0; for (int size = list.size(); i < size; i++) {
/*  74 */       result.add((SysParamCode)cache.get(list.get(i)).getValue());
/*     */     }
/*  76 */     return result;
/*     */   }
/*     */ 
/*     */   public synchronized void add(SysParamCode sysParamCode) {
/*  80 */     CacheManager manager = CacheManager.getInstance();
/*  81 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  82 */     Element element = cache.get(sysParamCode.getParamCode());
/*  83 */     if (element == null) {
/*  84 */       element = new Element(sysParamCode.getParamCode(), sysParamCode);
/*     */     }
/*  86 */     cache.put(element);
/*     */   }
/*     */ 
/*     */   public synchronized void update(SysParamCode sysParamCode) {
/*  90 */     CacheManager manager = CacheManager.getInstance();
/*  91 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  92 */     Element element = new Element(sysParamCode.getParamCode(), sysParamCode);
/*  93 */     cache.put(element);
/*     */   }
/*     */ 
/*     */   public synchronized void remove(String paramCode) {
/*  97 */     CacheManager manager = CacheManager.getInstance();
/*  98 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  99 */     cache.remove(paramCode);
/*     */   }
/*     */ 
/*     */   public synchronized void remove(SysParamCode sysParamCode) {
/* 103 */     CacheManager manager = CacheManager.getInstance();
/* 104 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 105 */     cache.remove(sysParamCode.getParamCode());
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysParamCodeCacheManager
 * JD-Core Version:    0.6.2
 */