/*    */ package com.smarthome.dock.server.packets.in;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class KeepAlivePacket extends InPacket
/*    */ {
/*    */   private long timeout;
/*    */ 
/*    */   public KeepAlivePacket(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 16 */     super(buf);
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf) throws PacketParseException
/*    */   {
/*    */   }
/*    */ 
/*    */   public long getTimeout()
/*    */   {
/* 25 */     return this.timeout;
/*    */   }
/*    */ 
/*    */   public void setTimeout(long timeout) {
/* 29 */     this.timeout = timeout;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.KeepAlivePacket
 * JD-Core Version:    0.6.2
 */