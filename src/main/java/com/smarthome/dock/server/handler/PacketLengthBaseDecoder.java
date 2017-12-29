/*    */ package com.smarthome.dock.server.handler;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketHelper;
/*    */ import com.smarthome.dock.server.support.PacketProcessor;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.jboss.netty.channel.Channel;
/*    */ import org.jboss.netty.channel.ChannelHandlerContext;
/*    */ import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
/*    */ 
/*    */ public class PacketLengthBaseDecoder extends LengthFieldBasedFrameDecoder
/*    */ {
/*    */   private PacketProcessor packetProcessor;
/*    */ 
/*    */   public PacketLengthBaseDecoder(PacketProcessor packetProcessor, int maxFrameLength, int lengthFieldOffset, int lengthFieldLength)
/*    */   {
/* 17 */     super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
/* 18 */     this.packetProcessor = packetProcessor;
/*    */   }
/*    */ 
/*    */   protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buf)
/*    */     throws Exception
/*    */   {
/* 24 */     Object buffer = super.decode(ctx, channel, buf);
/* 25 */     if (buffer != null) {
/* 26 */       InPacket packet = this.packetProcessor.getPacketHelper().processIn((ChannelBuffer)buffer);
/*    */ 
/* 30 */       return packet;
/*    */     }
/* 32 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.handler.PacketLengthBaseDecoder
 * JD-Core Version:    0.6.2
 */