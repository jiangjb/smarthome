/*     */ package com.smarthome.dock.server.packets.in;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.InPacket;
/*     */ import com.smarthome.dock.server.packets.PacketParseException;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public abstract class AlarmPacket extends InPacket
/*     */ {
/*  27 */   protected static Logger logger = LoggerFactory.getLogger(AlarmPacket.class);
/*     */   protected byte frameHead;
/*     */   protected byte frameType;
/*     */   protected char devType;
/*     */   protected char devAddr;
/*     */   protected int frameLen;
/*     */   protected int frameStatus;
/*     */   protected byte check;
/*     */   protected byte frameTail;
/*     */   protected byte[] devData;
/*     */ 
/*     */   public AlarmPacket(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  40 */     super(buf);
/*     */   }
/*     */ 
/*     */   protected void parseBody(ChannelBuffer buf) throws PacketParseException
/*     */   {
/*  45 */     int length = buf.readableBytes();
/*     */ 
/*  47 */     this.devData = new byte[length];
/*  48 */     buf.getBytes(buf.readerIndex(), this.devData);
/*     */ 
/*  54 */     this.frameHead = buf.readByte();
/*  55 */     this.frameType = buf.readByte();
/*  56 */     this.devType = buf.readChar();
/*  57 */     this.devAddr = buf.readChar();
/*  58 */     this.frameLen = buf.readChar();
/*  59 */     parseFrame(buf, this.frameLen);
/*     */ 
/*  61 */     this.check = buf.readByte();
/*  62 */     this.frameTail = buf.readByte();
/*     */   }
/*     */ 
/*     */   protected abstract void parseFrame(ChannelBuffer paramChannelBuffer, int paramInt) throws PacketParseException;
/*     */ 
/*     */   public byte getFrameHead() {
/*  68 */     return this.frameHead;
/*     */   }
/*     */ 
/*     */   public void setFrameHead(byte frameHead) {
/*  72 */     this.frameHead = frameHead;
/*     */   }
/*     */ 
/*     */   public byte getFrameType() {
/*  76 */     return this.frameType;
/*     */   }
/*     */ 
/*     */   public void setFrameType(byte frameType) {
/*  80 */     this.frameType = frameType;
/*     */   }
/*     */ 
/*     */   public char getDevType() {
/*  84 */     return this.devType;
/*     */   }
/*     */ 
/*     */   public void setDevType(char devType) {
/*  88 */     this.devType = devType;
/*     */   }
/*     */ 
/*     */   public char getDevAddr() {
/*  92 */     return this.devAddr;
/*     */   }
/*     */ 
/*     */   public void setDevAddr(char devAddr) {
/*  96 */     this.devAddr = devAddr;
/*     */   }
/*     */ 
/*     */   public int getFrameLen() {
/* 100 */     return this.frameLen;
/*     */   }
/*     */ 
/*     */   public void setFrameLen(int frameLen) {
/* 104 */     this.frameLen = frameLen;
/*     */   }
/*     */ 
/*     */   public int getFrameStatus() {
/* 108 */     return this.frameStatus;
/*     */   }
/*     */ 
/*     */   public void setFrameStatus(int frameStatus) {
/* 112 */     this.frameStatus = frameStatus;
/*     */   }
/*     */ 
/*     */   public byte getCheck() {
/* 116 */     return this.check;
/*     */   }
/*     */ 
/*     */   public void setCheck(byte check) {
/* 120 */     this.check = check;
/*     */   }
/*     */ 
/*     */   public byte getFrameTail() {
/* 124 */     return this.frameTail;
/*     */   }
/*     */ 
/*     */   public void setFrameTail(byte frameTail) {
/* 128 */     this.frameTail = frameTail;
/*     */   }
/*     */ 
/*     */   public byte[] getDevData() {
/* 132 */     return this.devData;
/*     */   }
/*     */ 
/*     */   public void setDevData(byte[] devData) {
/* 136 */     this.devData = devData;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.AlarmPacket
 * JD-Core Version:    0.6.2
 */