/*     */ package com.smarthome.dock.server.packets.in.report;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.PacketParseException;
/*     */ import com.smarthome.dock.server.packets.in.ReportPacket;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public class Report0140Packet extends ReportPacket
/*     */ {
/*     */   private int frameWork;
/*     */   private int frameStatus;
/*     */   private int TDS;
/*     */   private int life1;
/*     */   private int life2;
/*     */   private int life3;
/*     */   private int life4;
/*     */   private int life5;
/*     */ 
/*     */   public Report0140Packet(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  34 */     super(buf);
/*     */   }
/*     */ 
/*     */   protected void parseFrame(ChannelBuffer buf, int frameLen) throws PacketParseException
/*     */   {
/*  39 */     switch (this.frameType) {
/*     */     case 8:
/*  41 */       this.frameWork = buf.readByte();
/*  42 */       this.frameStatus = buf.readByte();
/*  43 */       break;
/*     */     case 3:
/*  45 */       this.TDS = buf.readChar();
/*  46 */       this.life1 = buf.readByte();
/*  47 */       this.life2 = buf.readByte();
/*  48 */       this.life3 = buf.readByte();
/*  49 */       this.life4 = buf.readByte();
/*  50 */       this.life5 = buf.readByte();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getFrameWork()
/*     */   {
/*  56 */     return this.frameWork;
/*     */   }
/*     */ 
/*     */   public void setFrameWork(int frameWork) {
/*  60 */     this.frameWork = frameWork;
/*     */   }
/*     */ 
/*     */   public int getFrameStatus() {
/*  64 */     return this.frameStatus;
/*     */   }
/*     */ 
/*     */   public void setFrameStatus(int frameStatus) {
/*  68 */     this.frameStatus = frameStatus;
/*     */   }
/*     */ 
/*     */   public int getTDS() {
/*  72 */     return this.TDS;
/*     */   }
/*     */ 
/*     */   public void setTDS(int tDS) {
/*  76 */     this.TDS = tDS;
/*     */   }
/*     */ 
/*     */   public int getLife1() {
/*  80 */     return this.life1;
/*     */   }
/*     */ 
/*     */   public void setLife1(int life1) {
/*  84 */     this.life1 = life1;
/*     */   }
/*     */ 
/*     */   public int getLife2() {
/*  88 */     return this.life2;
/*     */   }
/*     */ 
/*     */   public void setLife2(int life2) {
/*  92 */     this.life2 = life2;
/*     */   }
/*     */ 
/*     */   public int getLife3() {
/*  96 */     return this.life3;
/*     */   }
/*     */ 
/*     */   public void setLife3(int life3) {
/* 100 */     this.life3 = life3;
/*     */   }
/*     */ 
/*     */   public int getLife4() {
/* 104 */     return this.life4;
/*     */   }
/*     */ 
/*     */   public void setLife4(int life4) {
/* 108 */     this.life4 = life4;
/*     */   }
/*     */ 
/*     */   public int getLife5() {
/* 112 */     return this.life5;
/*     */   }
/*     */ 
/*     */   public void setLife5(int life5) {
/* 116 */     this.life5 = life5;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.report.Report0140Packet
 * JD-Core Version:    0.6.2
 */