/*     */ package com.smarthome.imcp.cache;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.system.SysDict;
/*     */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ import net.sf.ehcache.Statistics;
/*     */ 
/*     */ public final class SysDictCacheManager
/*     */ {
/*  18 */   public static SysDictCacheManager instance = new SysDictCacheManager();
/*     */ 
/*  20 */   private String CACHE_NAME = "sysDictCache";
/*     */ 
/*     */   public static SysDictCacheManager getInstance()
/*     */   {
/*  26 */     return instance;
/*     */   }
/*     */ 
/*     */   public synchronized void init(List list)
/*     */   {
/*  31 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/*  32 */       SysDict sysDict = (SysDict)iter.next();
/*  33 */       add(sysDict);
/*     */     }
/*     */   }
/*     */ 
/*     */   public CacheInfo getCacheInfo()
/*     */   {
/*  43 */     CacheManager manager = CacheManager.getInstance();
/*  44 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  45 */     CacheInfo cacheInfo = new CacheInfo();
/*  46 */     cacheInfo.setCacheName(this.CACHE_NAME);
/*  47 */     cacheInfo.setCacheHits(Long.valueOf(cache.getStatistics().getCacheHits()));
/*  48 */     cacheInfo.setCacheMisses(Long.valueOf(cache.getStatistics().getCacheMisses()));
/*  49 */     cacheInfo.setMemoryStoreSize(Long.valueOf(cache.calculateInMemorySize()));
/*  50 */     cacheInfo.setSize(Integer.valueOf(cache.getSize()));
/*  51 */     return cacheInfo;
/*     */   }
/*     */ 
/*     */   public synchronized void destroy() {
/*  55 */     CacheManager manager = CacheManager.getInstance();
/*  56 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  57 */     cache.removeAll();
/*     */   }
/*     */ 
/*     */   public SysDict getSysDict(String dictCode, String dictValue)
/*     */   {
/*  62 */     CacheManager manager = CacheManager.getInstance();
/*  63 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  64 */     Element element = cache.get(dictCode);
/*  65 */     if (element == null) {
/*  66 */       return new SysDict(dictCode, dictValue, dictValue);
/*     */     }
/*  68 */     Map mpDict = (Map)element.getValue();
/*  69 */     if (mpDict == null) {
/*  70 */       return new SysDict(dictCode, dictValue, dictValue);
/*     */     }
/*  72 */     if (mpDict.get(dictValue) == null) {
/*  73 */       return new SysDict(dictCode, dictValue, dictValue);
/*     */     }
/*  75 */     return (SysDict)mpDict.get(dictValue);
/*     */   }
/*     */ 
/*     */   public List<SysDict> getList(String dictCode)
/*     */   {
/*  80 */     CacheManager manager = CacheManager.getInstance();
/*  81 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  82 */     Element element = cache.get(dictCode);
/*  83 */     if (element == null) {
/*  84 */       return new ArrayList();
/*     */     }
/*  86 */     Map mpDict = (Map)element.getValue();
/*  87 */     if (mpDict == null) {
/*  88 */       return new ArrayList();
/*     */     }
/*  90 */     List result = new ArrayList(mpDict.values());
/*  91 */     Collections.sort(result, new SysDictComparator());
/*  92 */     return result;
/*     */   }
/*     */ 
/*     */   public synchronized void add(SysDict sysDict)
/*     */   {
/*  97 */     CacheManager manager = CacheManager.getInstance();
/*  98 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  99 */     Element element = cache.get(sysDict.getSysDictCode().getDictCode());
/* 100 */     Map mpDict = null;
/* 101 */     if (element == null) {
/* 102 */       mpDict = new HashMap();
/* 103 */       element = new Element(sysDict.getSysDictCode().getDictCode(), 
/* 104 */         mpDict);
/* 105 */       cache.put(element);
/*     */     } else {
/* 107 */       mpDict = (Map)element.getValue();
/*     */     }
/* 109 */     mpDict.put(sysDict.getDictValue(), sysDict);
/*     */   }
/*     */ 
/*     */   public synchronized void update(SysDict sysDict)
/*     */   {
/* 114 */     CacheManager manager = CacheManager.getInstance();
/* 115 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 116 */     Element element = cache.get(sysDict.getSysDictCode().getDictCode());
/* 117 */     if (element != null) {
/* 118 */       Map mpDict = (Map)element.getValue();
/* 119 */       mpDict.put(sysDict.getDictValue(), sysDict);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void remove(String dictCode, String dictValue)
/*     */   {
/* 125 */     CacheManager manager = CacheManager.getInstance();
/* 126 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 127 */     Element element = cache.get(dictCode);
/* 128 */     if (element != null) {
/* 129 */       Map mpDict = (Map)element.getValue();
/* 130 */       mpDict.remove(dictValue);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void remove(SysDict sysDict)
/*     */   {
/* 136 */     CacheManager manager = CacheManager.getInstance();
/* 137 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 138 */     Element element = cache.get(sysDict.getSysDictCode().getDictCode());
/* 139 */     if (element != null) {
/* 140 */       Map mpDict = (Map)element.getValue();
/* 141 */       mpDict.remove(sysDict.getDictValue());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysDictCacheManager
 * JD-Core Version:    0.6.2
 */