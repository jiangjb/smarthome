/*     */ package com.smarthome.dock.server.packets.out;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.OutPacket;
/*     */ import com.smarthome.dock.server.util.Util;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public class SendDDataPacket extends OutPacket
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
/*     */   public SendDDataPacket(String devId)
/*     */   {
/*  29 */     super((char) 49152, devId);//初始化
/*     */   }
/*     */ 
/*     */   protected void putBody(ChannelBuffer buf)
/*     */   {
/*  40 */     buf.writeBytes(this.frameBody);
/*     */   }
/*     */ 
/*     */   public byte getFrameHead()
/*     */   {
/*  60 */     return this.frameHead;
/*     */   }
/*     */ 
/*     */   public void setFrameHead(byte frameHead) {
/*  64 */     this.frameHead = frameHead;
/*     */   }
/*     */ 
/*     */   public byte getFrameType() {
/*  68 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(byte frameType) {
/*  72 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public char getDevType() {
/*  76 */     return this.devType;
/*     */   }
/*     */ 
/*     */   public void setDevType(char devType) {
/*  80 */     this.devType = devType;
/*     */   }
/*     */ 
/*     */   public char getDevAddr() {
/*  84 */     return this.devAddr;
/*     */   }
/*     */ 
/*     */   public void setDevAddr(char devAddr) {
/*  88 */     this.devAddr = devAddr;
/*     */   }
/*     */ 
/*     */   public void setFrameLen(int frameLen) {
/*  92 */     this.frameLen = Util.byte2ToInt(Util.toLH(frameLen));
/*     */   }
/*     */ 
/*     */   public byte getCheck() {
/*  96 */     return this.check;
/*     */   }
/*     */ 
/*     */   public void setCheck(byte check) {
/* 100 */     this.check = check;
/*     */   }
/*     */ 
/*     */   public byte getFrameTail() {
/* 104 */     return this.frameTail;
/*     */   }
/*     */ 
/*     */   public void setFrameTail(byte frameTail) {
/* 108 */     this.frameTail = frameTail;
/*     */   }
/*     */ 
/*     */   public byte[] getFrameBody() {
/* 112 */     return this.frameBody;
/*     */   }
/*     */ 
/*     */   public void setFrameBody(byte[] frameBody) {
/* 116 */     this.frameBody = frameBody;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.out.SendDDataPacket
 * JD-Core Version:    0.6.2
 */