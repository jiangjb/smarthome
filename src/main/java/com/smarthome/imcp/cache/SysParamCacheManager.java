/*     */ package com.smarthome.imcp.cache;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.system.SysParam;
/*     */ import com.smarthome.imcp.dao.model.system.SysParamCode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ import net.sf.ehcache.Statistics;
/*     */ 
/*     */ public final class SysParamCacheManager
/*     */ {
/*  19 */   public static SysParamCacheManager instance = new SysParamCacheManager();
/*     */ 
/*  21 */   private String CACHE_NAME = "sysParamCache";
/*     */ 
/*     */   public static SysParamCacheManager getInstance()
/*     */   {
/*  27 */     return instance;
/*     */   }
/*     */ 
/*     */   public synchronized void init(List list)
/*     */   {
/*  32 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/*  33 */       SysParam sysParam = (SysParam)iter.next();
/*  34 */       add(sysParam);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void destroy() {
/*  39 */     CacheManager manager = CacheManager.getInstance();
/*  40 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  41 */     cache.removeAll();
/*     */   }
/*     */ 
/*     */   public CacheInfo getCacheInfo()
/*     */   {
/*  50 */     CacheManager manager = CacheManager.getInstance();
/*  51 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  52 */     CacheInfo cacheInfo = new CacheInfo();
/*  53 */     cacheInfo.setCacheName(this.CACHE_NAME);
/*  54 */     cacheInfo.setCacheHits(Long.valueOf(cache.getStatistics().getCacheHits()));
/*  55 */     cacheInfo.setCacheMisses(Long.valueOf(cache.getStatistics().getCacheMisses()));
/*  56 */     cacheInfo.setMemoryStoreSize(Long.valueOf(cache.calculateInMemorySize()));
/*  57 */     cacheInfo.setSize(Integer.valueOf(cache.getSize()));
/*  58 */     return cacheInfo;
/*     */   }
/*     */ 
/*     */   public SysParam getSysParam(String paramCode, String paramValue)
/*     */   {
/*  63 */     CacheManager manager = CacheManager.getInstance();
/*  64 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*     */ 
/*  66 */     Element element = cache.get(paramCode);
/*  67 */     if (element == null) {
/*  68 */       SysParamCode sysParamCode = new SysParamCode();
/*  69 */       return new SysParam(sysParamCode, paramValue, paramValue);
/*     */     }
/*  71 */     Map mpParam = (Map)element.getValue();
/*  72 */     if (mpParam == null) {
/*  73 */       SysParamCode sysParamCode = new SysParamCode();
/*  74 */       return new SysParam(sysParamCode, paramValue, paramValue);
/*     */     }
/*  76 */     if (mpParam.get(paramValue) == null) {
/*  77 */       SysParamCode sysParamCode = new SysParamCode();
/*  78 */       return new SysParam(sysParamCode, paramValue, paramValue);
/*     */     }
/*  80 */     return (SysParam)mpParam.get(paramValue);
/*     */   }
/*     */ 
/*     */   public List<SysParam> getList(String paramCode)
/*     */   {
/*  85 */     CacheManager manager = CacheManager.getInstance();
/*  86 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  87 */     Element element = cache.get(paramCode);
/*  88 */     if (element == null) {
/*  89 */       return new ArrayList();
/*     */     }
/*  91 */     Map mpParam = (Map)element.getValue();
/*  92 */     if (mpParam == null) {
/*  93 */       return new ArrayList();
/*     */     }
/*  95 */     List result = new ArrayList(mpParam.values());
/*     */ 
/*  97 */     return result;
/*     */   }
/*     */ 
/*     */   public List<SysParam> getList(String paramCode, String[] excludes)
/*     */   {
/* 102 */     CacheManager manager = CacheManager.getInstance();
/* 103 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 104 */     Element element = cache.get(paramCode);
/* 105 */     if (element == null) {
/* 106 */       return new ArrayList();
/*     */     }
/* 108 */     Map mpParam = (Map)element.getValue();
/* 109 */     if (mpParam == null) {
/* 110 */       return new ArrayList();
/*     */     }
/*     */ 
/* 113 */     Map mpParamClone = new HashMap(
/* 114 */       mpParam.size());
/* 115 */     mpParamClone.putAll(mpParam);
/*     */ 
/* 117 */     for (String exclude : excludes) {
/* 118 */       mpParamClone.remove(exclude);
/*     */     }
/*     */ 
/* 121 */     List result = new ArrayList(mpParamClone.values());
/*     */ 
/* 123 */     return result;
/*     */   }
/*     */ 
/*     */   public synchronized void add(SysParam sysParam)
/*     */   {
/* 128 */     CacheManager manager = CacheManager.getInstance();
/* 129 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 130 */     Element element = cache.get(sysParam.getSysParamCode().getParamCode());
/* 131 */     Map mpParam = null;
/* 132 */     if (element == null) {
/* 133 */       mpParam = new HashMap();
/* 134 */       element = new Element(sysParam.getSysParamCode().getParamCode(), 
/* 135 */         mpParam);
/*     */     } else {
/* 137 */       mpParam = (Map)element.getValue();
/*     */     }
/* 139 */     mpParam.put(sysParam.getParamValue(), sysParam);
/* 140 */     cache.put(element);
/*     */   }
/*     */ 
/*     */   public synchronized void update(SysParam sysParam)
/*     */   {
/* 145 */     CacheManager manager = CacheManager.getInstance();
/* 146 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 147 */     Element element = cache.get(sysParam.getSysParamCode().getParamCode());
/* 148 */     if (element != null) {
/* 149 */       Map mpParam = (Map)element.getValue();
/* 150 */       mpParam.put(sysParam.getParamValue(), sysParam);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void remove(SysParam sysParam)
/*     */   {
/* 156 */     CacheManager manager = CacheManager.getInstance();
/* 157 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 158 */     Element element = cache.get(sysParam.getSysParamCode().getParamCode());
/* 159 */     if (element != null) {
/* 160 */       Map mpParam = (Map)element.getValue();
/* 161 */       mpParam.remove(sysParam.getParamValue());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysParamCacheManager
 * JD-Core Version:    0.6.2
 */