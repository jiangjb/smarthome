/*    */ package com.smarthome.dock.server.packets;
/*    */ 
/*    */ import com.smarthome.dock.server.util.Util;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public abstract class InPacket extends Packet
/*    */ {
/*    */   public InPacket(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 12 */     super(buf);
/*    */   }
/*    */ 
/*    */   public InPacket(char command, String devId) {
/* 16 */     super(command, 0, devId);
/*    */   }
/*    */ 
/*    */   protected void parseHeader(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 22 */     this.mark = buf.readByte();
/* 23 */     this.version = buf.readByte();
/* 24 */     this.command = buf.readChar();
/*    */ 
/* 26 */     this.bodyLength = buf.readChar();
/*    */ 
/* 28 */     byte[] devIdByte = new byte[28];
/* 29 */     buf.readBytes(devIdByte);
/* 30 */     this.devId = Util.getString2(devIdByte);
/* 31 */     buf.readBytes(this.ext);
/*    */   }
/*    */ 
/*    */   protected void parseTail(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/*    */   }
/*    */ 
/*    */   protected byte[] encryptBody(byte[] body, int offset, int len)
/*    */   {
/* 41 */     return body;
/*    */   }
/*    */ 
/*    */   protected byte[] decryptBody(byte[] body, int offset, int len)
/*    */   {
/* 46 */     return body;
/*    */   }
/*    */ 
/*    */   protected void putHead(ChannelBuffer buf)
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void putBody(ChannelBuffer buf)
/*    */   {
/*    */   }
/*    */ 
/*    */   protected void putTail(ChannelBuffer buf)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.InPacket
 * JD-Core Version:    0.6.2
 */