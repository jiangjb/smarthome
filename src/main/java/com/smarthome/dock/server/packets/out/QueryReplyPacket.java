/*    */ package com.smarthome.dock.server.packets.out;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.OutPacket;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class QueryReplyPacket extends OutPacket
/*    */ {
/*    */   public QueryReplyPacket(String devId, int ret)
/*    */   {
/* 11 */     super((char) 45057, devId);
/* 12 */     this.ext[0] = ((byte)ret);
/*    */   }
/*    */ 
/*    */   protected void putBody(ChannelBuffer buf)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.out.QueryReplyPacket
 * JD-Core Version:    0.6.2
 */