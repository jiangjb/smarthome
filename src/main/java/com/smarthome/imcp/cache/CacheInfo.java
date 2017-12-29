/*    */ package com.smarthome.imcp.cache;
/*    */ 
/*    */ public class CacheInfo
/*    */ {
/*    */   private String cacheName;
/*    */   private Integer size;
/*    */   private Long memoryStoreSize;
/*    */   private Long cacheHits;
/*    */   private Long cacheMisses;
/*    */ 
/*    */   public String getCacheName()
/*    */   {
/* 12 */     return this.cacheName;
/*    */   }
/*    */ 
/*    */   public void setCacheName(String cacheName) {
/* 16 */     this.cacheName = cacheName;
/*    */   }
/*    */ 
/*    */   public Integer getSize() {
/* 20 */     return this.size;
/*    */   }
/*    */ 
/*    */   public void setSize(Integer size) {
/* 24 */     this.size = size;
/*    */   }
/*    */ 
/*    */   public Long getMemoryStoreSize() {
/* 28 */     return this.memoryStoreSize;
/*    */   }
/*    */ 
/*    */   public void setMemoryStoreSize(Long memoryStoreSize) {
/* 32 */     this.memoryStoreSize = memoryStoreSize;
/*    */   }
/*    */ 
/*    */   public Long getCacheHits() {
/* 36 */     return this.cacheHits;
/*    */   }
/*    */ 
/*    */   public void setCacheHits(Long cacheHits) {
/* 40 */     this.cacheHits = cacheHits;
/*    */   }
/*    */ 
/*    */   public Long getCacheMisses() {
/* 44 */     return this.cacheMisses;
/*    */   }
/*    */ 
/*    */   public void setCacheMisses(Long cacheMisses) {
/* 48 */     this.cacheMisses = cacheMisses;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.CacheInfo
 * JD-Core Version:    0.6.2
 */