/*    */ package com.smarthome.dock.server.handler;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.OutPacket;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.jboss.netty.buffer.ChannelBuffers;
/*    */ import org.jboss.netty.channel.Channel;
/*    */ import org.jboss.netty.channel.ChannelHandlerContext;
/*    */ import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
/*    */ 
/*    */ public class PacketEncoder extends OneToOneEncoder
/*    */ {
/*    */   protected Object encode(ChannelHandlerContext ctx, Channel channel, Object obj)
/*    */     throws Exception
/*    */   {
/* 17 */     ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
/*    */ 
/* 19 */     OutPacket packet = (OutPacket)obj;
/* 20 */     packet.fill(buffer);
/*    */ 
/* 22 */     return buffer;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.handler.PacketEncoder
 * JD-Core Version:    0.6.2
 */