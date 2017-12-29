/*    */ package com.smarthome.dock.server.packets;
/*    */ 
/*    */ public class PacketParseException extends Exception
/*    */ {
/*    */   private static final long serialVersionUID = 3257284738459775545L;
/*    */ 
/*    */   public PacketParseException()
/*    */   {
/*    */   }
/*    */ 
/*    */   public PacketParseException(String arg0)
/*    */   {
/* 35 */     super(arg0);
/*    */   }
/*    */ 
/*    */   public PacketParseException(Throwable arg0) {
/* 39 */     super(arg0);
/*    */   }
/*    */ 
/*    */   public PacketParseException(String arg0, Throwable arg1) {
/* 43 */     super(arg0, arg1);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.PacketParseException
 * JD-Core Version:    0.6.2
 */