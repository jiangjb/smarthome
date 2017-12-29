/*     */ package com.smarthome.dock.server.packets.in.report;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.PacketParseException;
/*     */ import com.smarthome.dock.server.packets.in.ReportPacket;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public class Report0240Packet extends ReportPacket
/*     */ {
/*     */   private int frameWork;
/*     */   private int frameStatus;
/*     */   private int inTDS;
/*     */   private int outTDS;
/*     */   private int life1;
/*     */   private int life2;
/*     */   private int life3;
/*     */   private int life4;
/*     */   private int life5;
/*     */   private int totalWater;
/*     */ 
/*     */   public Report0240Packet(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  37 */     super(buf);
/*     */   }
/*     */ 
/*     */   protected void parseFrame(ChannelBuffer buf, int frameLen) throws PacketParseException
/*     */   {
/*  42 */     switch (this.frameType) {
/*     */     case 8:
/*  44 */       this.frameWork = buf.readByte();
/*  45 */       this.frameStatus = buf.readByte();
/*  46 */       break;
/*     */     case 3:
/*  48 */       this.inTDS = buf.readChar();
/*  49 */       this.outTDS = buf.readChar();
/*  50 */       this.life1 = buf.readByte();
/*  51 */       this.life2 = buf.readByte();
/*  52 */       this.life3 = buf.readByte();
/*  53 */       this.life4 = buf.readByte();
/*  54 */       this.life5 = buf.readByte();
/*  55 */       this.totalWater = buf.readChar();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getFrameWork()
/*     */   {
/*  61 */     return this.frameWork;
/*     */   }
/*     */ 
/*     */   public void setFrameWork(int frameWork) {
/*  65 */     this.frameWork = frameWork;
/*     */   }
/*     */ 
/*     */   public int getFrameStatus() {
/*  69 */     return this.frameStatus;
/*     */   }
/*     */ 
/*     */   public void setFrameStatus(int frameStatus) {
/*  73 */     this.frameStatus = frameStatus;
/*     */   }
/*     */ 
/*     */   public int getInTDS() {
/*  77 */     return this.inTDS;
/*     */   }
/*     */ 
/*     */   public void setInTDS(int inTDS) {
/*  81 */     this.inTDS = inTDS;
/*     */   }
/*     */ 
/*     */   public int getOutTDS() {
/*  85 */     return this.outTDS;
/*     */   }
/*     */ 
/*     */   public void setOutTDS(int outTDS) {
/*  89 */     this.outTDS = outTDS;
/*     */   }
/*     */ 
/*     */   public int getTotalWater() {
/*  93 */     return this.totalWater;
/*     */   }
/*     */ 
/*     */   public void setTotalWater(int totalWater) {
/*  97 */     this.totalWater = totalWater;
/*     */   }
/*     */ 
/*     */   public int getLife1() {
/* 101 */     return this.life1;
/*     */   }
/*     */ 
/*     */   public void setLife1(int life1) {
/* 105 */     this.life1 = life1;
/*     */   }
/*     */ 
/*     */   public int getLife2() {
/* 109 */     return this.life2;
/*     */   }
/*     */ 
/*     */   public void setLife2(int life2) {
/* 113 */     this.life2 = life2;
/*     */   }
/*     */ 
/*     */   public int getLife3() {
/* 117 */     return this.life3;
/*     */   }
/*     */ 
/*     */   public void setLife3(int life3) {
/* 121 */     this.life3 = life3;
/*     */   }
/*     */ 
/*     */   public int getLife4() {
/* 125 */     return this.life4;
/*     */   }
/*     */ 
/*     */   public void setLife4(int life4) {
/* 129 */     this.life4 = life4;
/*     */   }
/*     */ 
/*     */   public int getLife5() {
/* 133 */     return this.life5;
/*     */   }
/*     */ 
/*     */   public void setLife5(int life5) {
/* 137 */     this.life5 = life5;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.report.Report0240Packet
 * JD-Core Version:    0.6.2
 */