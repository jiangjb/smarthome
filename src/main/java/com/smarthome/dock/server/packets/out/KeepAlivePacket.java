/*    */ package com.smarthome.dock.server.packets.out;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.OutPacket;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class KeepAlivePacket extends OutPacket
/*    */ {
/*    */   public KeepAlivePacket(String devId, int sequence, int ret)
/*    */   {
/* 10 */     super((char) 40963, sequence, devId);
/* 11 */     this.ext[0] = ((byte)ret);
/*    */   }
/*    */ 
/*    */   protected void putBody(ChannelBuffer buf)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.out.KeepAlivePacket
 * JD-Core Version:    0.6.2
 */