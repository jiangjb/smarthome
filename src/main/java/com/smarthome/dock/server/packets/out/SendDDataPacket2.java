/*     */ package com.smarthome.dock.server.packets.out;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.OutPacket;
/*     */ import com.smarthome.dock.server.util.Util;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public class SendDDataPacket2 extends OutPacket
/*     */ {
/*  18 */   protected byte frameHead = 2;
/*  19 */   protected byte frameType = 2;
/*  20 */   protected char devType = 'ɀ';
/*  21 */   protected char devAddr = '\000';
/*  22 */   protected int frameLen = 256;
/*  23 */   protected byte check = 0;
/*  24 */   protected byte frameTail = 3;
/*     */   private byte[] frameBody;
/*     */ 
/*     */   public SendDDataPacket2(String devId)
/*     */   {
/*  29 */     super((char) 49152, devId);
/*     */   }
/*     */ 
/*     */   protected void putBody(ChannelBuffer buf)
/*     */   {
/*  34 */     buf.writeByte(this.frameHead);
/*  35 */     buf.writeByte(this.frameType);
/*  36 */     buf.writeChar(this.devType);
/*  37 */     buf.writeChar(this.devAddr);
/*  38 */     buf.writeChar(this.frameLen);
/*     */ 
/*  40 */     buf.writeBytes(this.frameBody);
/*  41 */     byte[] cb = new byte[this.frameBody.length + 7];
/*     */ 
/*  43 */     cb[0] = this.frameType;
/*  44 */     cb[1] = 2;
/*  45 */     cb[2] = 4;
/*  46 */     cb[3] = 0;
/*  47 */     cb[4] = 0;
/*  48 */     cb[5] = 1;
/*  49 */     cb[6] = 0;
/*     */ 
/*  51 */     System.arraycopy(this.frameBody, 0, cb, 7, this.frameBody.length);
/*  52 */     this.check = Util.getValidateByte(cb);
/*     */ 
/*  54 */     buf.writeByte(this.check);
/*  55 */     buf.writeByte(this.frameTail);
/*     */   }
/*     */ 
/*     */   public byte getFrameHead() {
/*  59 */     return this.frameHead;
/*     */   }
/*     */ 
/*     */   public void setFrameHead(byte frameHead) {
/*  63 */     this.frameHead = frameHead;
/*     */   }
/*     */ 
/*     */   public byte getFrameType() {
/*  67 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(byte frameType) {
/*  71 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public char getDevType() {
/*  75 */     return this.devType;
/*     */   }
/*     */ 
/*     */   public void setDevType(char devType) {
/*  79 */     this.devType = devType;
/*     */   }
/*     */ 
/*     */   public char getDevAddr() {
/*  83 */     return this.devAddr;
/*     */   }
/*     */ 
/*     */   public void setDevAddr(char devAddr) {
/*  87 */     this.devAddr = devAddr;
/*     */   }
/*     */ 
/*     */   public void setFrameLen(int frameLen) {
/*  91 */     this.frameLen = Util.byte2ToInt(Util.toLH(frameLen));
/*     */   }
/*     */ 
/*     */   public byte getCheck() {
/*  95 */     return this.check;
/*     */   }
/*     */ 
/*     */   public void setCheck(byte check) {
/*  99 */     this.check = check;
/*     */   }
/*     */ 
/*     */   public byte getFrameTail() {
/* 103 */     return this.frameTail;
/*     */   }
/*     */ 
/*     */   public void setFrameTail(byte frameTail) {
/* 107 */     this.frameTail = frameTail;
/*     */   }
/*     */ 
/*     */   public byte[] getFrameBody() {
/* 111 */     return this.frameBody;
/*     */   }
/*     */ 
/*     */   public void setFrameBody(byte[] frameBody) {
/* 115 */     this.frameBody = frameBody;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.out.SendDDataPacket2
 * JD-Core Version:    0.6.2
 */