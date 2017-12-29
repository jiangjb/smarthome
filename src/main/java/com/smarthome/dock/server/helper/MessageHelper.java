/*    */ package com.smarthome.dock.server.helper;
/*    */ 
/*    */ import com.smarthome.dock.server.util.FileSegmentor;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class MessageHelper
/*    */ {
/*    */   private Map<Integer, Object[]> fragmentCache;
/*    */ 
/*    */   public MessageHelper()
/*    */   {
/* 15 */     this.fragmentCache = new HashMap();
/*    */   }
/*    */ 
/*    */   public void initFragment(int messageId, FileSegmentor fileSegmentor)
/*    */   {
/* 25 */     int totalFragments = fileSegmentor.getTotalFragments();
/* 26 */     Object[] fragments = (Object[])this.fragmentCache.get(Integer.valueOf(messageId));
/* 27 */     if ((fragments == null) || (fragments.length != totalFragments)) {
/* 28 */       fragments = new Object[totalFragments];
/* 29 */       this.fragmentCache.put(Integer.valueOf(messageId), fragments);
/*    */     }
/*    */ 
/* 32 */     for (int index = 0; index < totalFragments; index++)
/* 33 */       fragments[index] = fileSegmentor.getFragment(index);
/*    */   }
/*    */ 
/*    */   public byte[] getFragment(int messageId, int index)
/*    */   {
/* 44 */     if (!this.fragmentCache.containsKey(Integer.valueOf(messageId))) {
/* 45 */       return null;
/*    */     }
/* 47 */     Object[] fragments = (Object[])this.fragmentCache.get(Integer.valueOf(messageId));
/* 48 */     if (fragments.length <= index) {
/* 49 */       return null;
/*    */     }
/*    */ 
/* 52 */     if (fragments.length == index - 1) {
/* 53 */       this.fragmentCache.remove(Integer.valueOf(messageId));
/*    */     }
/* 55 */     return (byte[])fragments[index];
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.MessageHelper
 * JD-Core Version:    0.6.2
 */