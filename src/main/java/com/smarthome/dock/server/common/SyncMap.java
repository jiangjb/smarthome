/*    */ package com.smarthome.dock.server.common;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class SyncMap extends HashMap
/*    */ {
/*    */   public SyncMap()
/*    */   {
/*    */   }
/*    */ 
/*    */   public SyncMap(Map map)
/*    */   {
/* 45 */     super(map);
/*    */   }
/*    */ 
/*    */   public synchronized Object put(Object key, Object value) {
/* 49 */     return super.put(key, value);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.SyncMap
 * JD-Core Version:    0.6.2
 */