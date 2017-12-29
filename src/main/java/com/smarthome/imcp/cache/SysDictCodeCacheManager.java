/*     */ package com.smarthome.imcp.cache;
/*     */ 
/*     */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.sf.ehcache.Cache;
/*     */ import net.sf.ehcache.CacheManager;
/*     */ import net.sf.ehcache.Element;
/*     */ import net.sf.ehcache.Statistics;
/*     */ 
/*     */ public final class SysDictCodeCacheManager
/*     */ {
/*  15 */   public static SysDictCodeCacheManager instance = new SysDictCodeCacheManager();
/*     */ 
/*  17 */   private String CACHE_NAME = "sysDictCodeCache";
/*     */ 
/*     */   public static SysDictCodeCacheManager getInstance()
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
/*     */   public synchronized void init(List list)
/*     */   {
/*  45 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) {
/*  46 */       SysDictCode sysDictCode = (SysDictCode)iter.next();
/*  47 */       add(sysDictCode);
/*     */     }
/*     */   }
/*     */ 
/*     */   public synchronized void destroy() {
/*  52 */     CacheManager manager = CacheManager.getInstance();
/*  53 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  54 */     cache.removeAll();
/*     */   }
/*     */ 
/*     */   public SysDictCode getSysDictCode(String dictCode) {
/*  58 */     CacheManager manager = CacheManager.getInstance();
/*  59 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  60 */     Element element = cache.get(dictCode);
/*  61 */     if (element == null) {
/*  62 */       return new SysDictCode(dictCode, dictCode);
/*     */     }
/*  64 */     return (SysDictCode)element.getValue();
/*     */   }
/*     */ 
/*     */   public List<SysDictCode> getList()
/*     */   {
/*  69 */     CacheManager manager = CacheManager.getInstance();
/*  70 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  71 */     List result = new ArrayList();
/*  72 */     List list = cache.getKeys();
/*  73 */     int i = 0; for (int size = list.size(); i < size; i++) {
/*  74 */       result.add((SysDictCode)cache.get(list.get(i)).getValue());
/*     */     }
/*  76 */     return result;
/*     */   }
/*     */ 
/*     */   public synchronized void add(SysDictCode sysDictCode) {
/*  80 */     CacheManager manager = CacheManager.getInstance();
/*  81 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  82 */     Element element = cache.get(sysDictCode.getDictCode());
/*  83 */     if (element == null) {
/*  84 */       element = new Element(sysDictCode.getDictCode(), sysDictCode);
/*     */     }
/*  86 */     cache.put(element);
/*     */   }
/*     */ 
/*     */   public synchronized void update(SysDictCode sysDictCode) {
/*  90 */     add(sysDictCode);
/*     */   }
/*     */ 
/*     */   public synchronized void remove(String dictCode) {
/*  94 */     CacheManager manager = CacheManager.getInstance();
/*  95 */     Cache cache = manager.getCache(this.CACHE_NAME);
/*  96 */     cache.remove(dictCode);
/*     */   }
/*     */ 
/*     */   public synchronized void remove(SysDictCode sysDictCode) {
/* 100 */     CacheManager manager = CacheManager.getInstance();
/* 101 */     Cache cache = manager.getCache(this.CACHE_NAME);
/* 102 */     cache.remove(sysDictCode.getDictCode());
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysDictCodeCacheManager
 * JD-Core Version:    0.6.2
 */