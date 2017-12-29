/*   */ package com.smarthome.dock.server.helper;
/*   */ 
/*   */ public class MessageIDGenerator
/*   */ {
/* 4 */   private static int messageId = 0;
/*   */ 
/*   */   public static int getNext() {
/* 7 */     return messageId++;
/*   */   }
/*   */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.MessageIDGenerator
 * JD-Core Version:    0.6.2
 */