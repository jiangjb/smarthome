/*    */ package com.smarthome.dock.server.packets;
/*    */ 
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class ErrorPacket extends InPacket
/*    */ {
/*    */   public static final int ERROR_REMOTE_CLOSED = 0;
/*    */   public static final int ERROR_TIMEOUT = 1;
/*    */   public int errorCode;
/*    */   public String portName;
/*    */   public OutPacket timeoutPacket;
/*    */ 
/*    */   public ErrorPacket(int errorCode, String devId)
/*    */   {
/* 22 */     super('\000', devId);
/* 23 */     this.errorCode = errorCode;
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.ErrorPacket
 * JD-Core Version:    0.6.2
 */