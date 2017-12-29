/*     */ package com.smarthome.dock.server.packets.in;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.InPacket;
/*     */ import com.smarthome.dock.server.packets.PacketParseException;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public abstract class ReportPacket extends InPacket
/*     */ {
/*  30 */   private static Logger logger = LoggerFactory.getLogger(ReportPacket.class);
/*     */   protected byte frameHead;
/*     */   protected byte frameType;
/*     */   protected char devType;
/*     */   protected char devAddr;
/*     */   protected int frameLen;
/*     */   protected byte check;
/*     */   protected byte frameTail;
/*     */   protected byte[] devData;
/*     */ 
/*     */   public ReportPacket(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  44 */     super(buf);
/*     */   }
/*     */ 
/*     */   protected void parseBody(ChannelBuffer buf) throws PacketParseException
/*     */   {
/*  49 */     int length = buf.readableBytes();
/*     */ 
/*  51 */     this.devData = new byte[length];
/*  52 */     buf.getBytes(buf.readerIndex(), this.devData);
/*     */ 
/*  58 */     this.frameHead = buf.readByte();
/*  59 */     this.frameType = buf.readByte();
/*  60 */     this.devType = buf.readChar();
/*  61 */     this.devAddr = buf.readChar();
/*  62 */     this.frameLen = buf.readChar();
/*  63 */     parseFrame(buf, this.frameLen);
/*  64 */     this.check = buf.readByte();
/*  65 */     this.frameTail = buf.readByte();
/*     */   }
/*     */ 
/*     */   protected abstract void parseFrame(ChannelBuffer paramChannelBuffer, int paramInt) throws PacketParseException;
/*     */ 
/*     */   public byte getFrameHead() {
/*  71 */     return this.frameHead;
/*     */   }
/*     */ 
/*     */   public void setFrameHead(byte frameHead) {
/*  75 */     this.frameHead = frameHead;
/*     */   }
/*     */ 
/*     */   public byte getFrameType() {
/*  79 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(byte frameType) {
/*  83 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public char getDevType() {
/*  87 */     return this.devType;
/*     */   }
/*     */ 
/*     */   public void setDevType(char devType) {
/*  91 */     this.devType = devType;
/*     */   }
/*     */ 
/*     */   public char getDevAddr() {
/*  95 */     return this.devAddr;
/*     */   }
/*     */ 
/*     */   public void setDevAddr(char devAddr) {
/*  99 */     this.devAddr = devAddr;
/*     */   }
/*     */ 
/*     */   public int getFrameLen() {
/* 103 */     return this.frameLen;
/*     */   }
/*     */ 
/*     */   public void setFrameLen(int frameLen) {
/* 107 */     this.frameLen = frameLen;
/*     */   }
/*     */ 
/*     */   public byte getCheck() {
/* 111 */     return this.check;
/*     */   }
/*     */ 
/*     */   public void setCheck(byte check) {
/* 115 */     this.check = check;
/*     */   }
/*     */ 
/*     */   public byte getFrameTail() {
/* 119 */     return this.frameTail;
/*     */   }
/*     */ 
/*     */   public void setFrameTail(byte frameTail) {
/* 123 */     this.frameTail = frameTail;
/*     */   }
/*     */ 
/*     */   public byte[] getDevData() {
/* 127 */     return this.devData;
/*     */   }
/*     */ 
/*     */   public void setDevData(byte[] devData) {
/* 131 */     this.devData = devData;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.ReportPacket
 * JD-Core Version:    0.6.2
 */