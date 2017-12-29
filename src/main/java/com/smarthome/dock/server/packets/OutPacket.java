/*     */ package com.smarthome.dock.server.packets;
/*     */ 
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public abstract class OutPacket extends Packet
/*     */ {
/*  11 */   protected static int seq = 1000;
/*     */   private long timeout;
/*     */   private int resendCountDown;
/*     */ 
/*     */   public OutPacket(char command, String devId)
/*     */   {
/*  24 */     super(command, getNextSeq(), devId);
/*  25 */     this.resendCountDown = 2;
/*     */   }
/*     */ 
/*     */   public OutPacket(char command, int sequence, String devId) {
/*  29 */     super(command, sequence, devId);
/*     */   }
/*     */ 
/*     */   public void fill(ChannelBuffer buffer)
/*     */   {
/*  37 */     putHead(buffer);
/*  38 */     putBody(buffer);
/*  39 */     putTail(buffer);
/*     */ 
/*  41 */     int bodyLength = buffer.readableBytes() - 44;
/*     */ 
/*  43 */     buffer.setChar(4, bodyLength);
/*     */   }
/*     */ 
/*     */   protected void putHead(ChannelBuffer buf)
/*     */   {
/*  53 */     buf.writeByte(this.mark);
/*  54 */     buf.writeByte(this.version);
/*  55 */     buf.writeChar(this.command);
/*  56 */     buf.writeChar(this.bodyLength);
/*  57 */     buf.writeBytes(getDevIdBytes());
/*  58 */     buf.writeBytes(this.ext);
/*     */   }
/*     */ 
/*     */   protected void putTail(ChannelBuffer buf)
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void parseHeader(ChannelBuffer buffer)
/*     */     throws PacketParseException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void parseBody(ChannelBuffer buffer)
/*     */     throws PacketParseException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected void parseTail(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*     */   }
/*     */ 
/*     */   protected byte[] decryptBody(byte[] body, int offset, int length)
/*     */   {
/*  84 */     return body;
/*     */   }
/*     */ 
/*     */   protected byte[] encryptBody(byte[] body, int offset, int length)
/*     */   {
/*  92 */     return body;
/*     */   }
/*     */ 
/*     */   public static int getNextSeq()
/*     */   {
/* 100 */     if (seq >= 2147483647) {
/* 101 */       seq = 0;
/*     */     }
/* 103 */     seq += 1;
/* 104 */     return seq;
/*     */   }
/*     */ 
/*     */   public final boolean needResend()
/*     */   {
/* 113 */     return this.resendCountDown-- > 0;
/*     */   }
/*     */ 
/*     */   public long getTimeout() {
/* 117 */     return this.timeout;
/*     */   }
/*     */ 
/*     */   public void setTimeout(long timeout) {
/* 121 */     this.timeout = timeout;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.OutPacket
 * JD-Core Version:    0.6.2
 */