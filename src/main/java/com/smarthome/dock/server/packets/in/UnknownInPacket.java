/*    */ package com.smarthome.dock.server.packets.in;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class UnknownInPacket extends InPacket
/*    */ {
/*    */   public UnknownInPacket(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 11 */     super(buf);
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.UnknownInPacket
 * JD-Core Version:    0.6.2
 */