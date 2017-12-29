/*     */ package com.smarthome.dock.server.packets.in;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.InPacket;
/*     */ import com.smarthome.dock.server.packets.PacketParseException;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public class SendDDataReplyPacket extends InPacket
/*     */ {
/*     */   protected byte frameHead;
/*     */   protected byte frameType;
/*     */   protected char devType;
/*     */   protected char devAddr;
/*     */   protected int frameLen;
/*     */   protected int frameStatus;
/*     */   protected byte check;
/*     */   protected byte frameTail;
/*     */   protected byte[] devData;
/*     */   protected byte[] frameBody;
/*     */ 
/*     */   public SendDDataReplyPacket(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  33 */     super(buf);
/*     */   }
/*     */ 
/*     */   protected void parseBody(ChannelBuffer buf) throws PacketParseException
/*     */   {
/*  38 */     int length = buf.readableBytes();
/*     */ 
/*  40 */     this.devData = new byte[length];
/*  41 */     buf.getBytes(buf.readerIndex(), this.devData);
/*     */ 
/*  47 */     this.frameHead = buf.readByte();
/*  48 */     this.frameType = buf.readByte();
/*  49 */     this.devType = buf.readChar();
/*  50 */     this.devAddr = buf.readChar();
/*  51 */     this.frameLen = buf.readChar();
/*     */ 
/*  53 */     length = buf.readableBytes();
/*     */ 
/*  55 */     this.frameBody = new byte[length - 2];
/*     */ 
/*  57 */     buf.readBytes(this.frameBody);
/*     */ 
/*  59 */     this.check = buf.readByte();
/*  60 */     this.frameTail = buf.readByte();
/*     */   }
/*     */ 
/*     */   public byte getFrameHead() {
/*  64 */     return this.frameHead;
/*     */   }
/*     */ 
/*     */   public void setFrameHead(byte frameHead) {
/*  68 */     this.frameHead = frameHead;
/*     */   }
/*     */ 
/*     */   public byte getFrameType() {
/*  72 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(byte frameType) {
/*  76 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public char getDevType() {
/*  80 */     return this.devType;
/*     */   }
/*     */ 
/*     */   public void setDevType(char devType) {
/*  84 */     this.devType = devType;
/*     */   }
/*     */ 
/*     */   public char getDevAddr() {
/*  88 */     return this.devAddr;
/*     */   }
/*     */ 
/*     */   public void setDevAddr(char devAddr) {
/*  92 */     this.devAddr = devAddr;
/*     */   }
/*     */ 
/*     */   public int getFrameLen() {
/*  96 */     return this.frameLen;
/*     */   }
/*     */ 
/*     */   public void setFrameLen(int frameLen) {
/* 100 */     this.frameLen = frameLen;
/*     */   }
/*     */ 
/*     */   public int getFrameStatus() {
/* 104 */     return this.frameStatus;
/*     */   }
/*     */ 
/*     */   public void setFrameStatus(int frameStatus) {
/* 108 */     this.frameStatus = frameStatus;
/*     */   }
/*     */ 
/*     */   public byte getCheck() {
/* 112 */     return this.check;
/*     */   }
/*     */ 
/*     */   public void setCheck(byte check) {
/* 116 */     this.check = check;
/*     */   }
/*     */ 
/*     */   public byte getFrameTail() {
/* 120 */     return this.frameTail;
/*     */   }
/*     */ 
/*     */   public void setFrameTail(byte frameTail) {
/* 124 */     this.frameTail = frameTail;
/*     */   }
/*     */ 
/*     */   public byte[] getDevData() {
/* 128 */     return this.devData;
/*     */   }
/*     */ 
/*     */   public void setDevData(byte[] devData) {
/* 132 */     this.devData = devData;
/*     */   }
/*     */ 
/*     */   public byte[] getFrameBody() {
/* 136 */     return this.frameBody;
/*     */   }
/*     */ 
/*     */   public void setFrameBody(byte[] frameBody) {
/* 140 */     this.frameBody = frameBody;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.SendDDataReplyPacket
 * JD-Core Version:    0.6.2
 */