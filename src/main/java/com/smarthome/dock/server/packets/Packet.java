/*     */ package com.smarthome.dock.server.packets;
/*     */ 
/*     */ import com.smarthome.dock.server.util.Util;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ 
/*     */ public abstract class Packet
/*     */ {
/*  17 */   protected byte mark = 94;
/*     */ 
/*  22 */   protected byte version = 1;
/*     */   protected char command;
/*     */   protected int sequence;
/*     */   protected int bodyLength;
/*     */   protected String devId;
/*  40 */   protected byte[] ext = new byte[10];
/*     */   protected boolean duplicated;
/*     */   protected String hostName;
/*     */   protected int port;

/*     */   protected Packet()
/*     */   {
/*     */   }
/*     */ 
/*     */   public Packet(char command, int sequence, String devId)
/*     */   {
/*  52 */     this.command = command;
/*  53 */     this.sequence = sequence;
/*  54 */     this.devId = devId;
/*     */   }
/*     */ 
/*     */   protected Packet(ChannelBuffer buf) throws PacketParseException
/*     */   {
/*  59 */     parseHeader(buf);
/*     */ 
/*  62 */     parseBody(buf);
/*     */ 
/*  65 */     parseTail(buf);
/*     */   }
/*     */ 
/*     */   protected abstract void putHead(ChannelBuffer paramChannelBuffer);
/*     */ 
/*     */   protected abstract void putBody(ChannelBuffer paramChannelBuffer);
/*     */ 
/*     */   protected abstract void putTail(ChannelBuffer paramChannelBuffer);
/*     */ 
/*     */   protected abstract void parseHeader(ChannelBuffer paramChannelBuffer)
/*     */     throws PacketParseException;
/*     */ 
/*     */   protected abstract void parseBody(ChannelBuffer paramChannelBuffer)
/*     */     throws PacketParseException;
/*     */ 
/*     */   protected abstract void parseTail(ChannelBuffer paramChannelBuffer)
/*     */     throws PacketParseException;
/*     */ 
/*     */   protected abstract byte[] encryptBody(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
/*     */ 
/*     */   protected abstract byte[] decryptBody(byte[] paramArrayOfByte, int paramInt1, int paramInt2);
/*     */ 
/*     */   public final boolean equals(Object obj)
/*     */   {
/* 148 */     if ((obj instanceof Packet)) {
/* 149 */       return equals((Packet)obj);
/*     */     }
/* 151 */     return super.equals(obj);
/*     */   }
/*     */ 
/*     */   public final boolean equals(Packet packet)
/*     */   {
/* 162 */     return 
/* 163 */       (this.command == packet.command) && (this.devId.equals(packet.devId));
/*     */   }
/*     */ 
/*     */   public final int hashCode()
/*     */   {
/* 170 */     return this.sequence << 16 | this.command;
/*     */   }
/*     */ 
/*     */   public char getCommand() {
/* 174 */     return this.command;
/*     */   }
/*     */ 
/*     */   public int getSequence() {
/* 178 */     return this.sequence;
/*     */   }
/*     */ 
/*     */   public int getBodyLength() {
/* 182 */     return this.bodyLength;
/*     */   }
/*     */ 
/*     */   public void setBodyLength(char bodyLength) {
/* 186 */     this.bodyLength = bodyLength;
/*     */   }
/*     */ 
/*     */   public byte[] getExt() {
/* 190 */     return this.ext;
/*     */   }
/*     */ 
/*     */   public void setExt(byte[] ext) {
/* 194 */     this.ext = ext;
/*     */   }
/*     */ 
/*     */   public byte getMark() {
/* 198 */     return this.mark;
/*     */   }
/*     */ 
/*     */   public byte getVersion() {
/* 202 */     return this.version;
/*     */   }
/*     */ 
/*     */   public String getDevId() {
/* 206 */     return this.devId;
/*     */   }
/*     */ 
/*     */   public byte[] getDevIdBytes() {
/* 210 */     return Util.getBytes(this.devId, 28);
/*     */   }
/*     */ 
/*     */   public boolean isDuplicated() {
/* 214 */     return this.duplicated;
/*     */   }
/*     */ 
/*     */   public void setDuplicated(boolean duplicated) {
/* 218 */     this.duplicated = duplicated;
/*     */   }
/*     */ 
/*     */   public String getHostName() {
/* 222 */     return this.hostName;
/*     */   }
/*     */ 
/*     */   public void setHostName(String hostName) {
/* 226 */     this.hostName = hostName;
/*     */   }
/*     */ 
/*     */   public int getPort() {
/* 230 */     return this.port;
/*     */   }
/*     */ 
/*     */   public void setPort(int port) {
/* 234 */     this.port = port;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.Packet
 * JD-Core Version:    0.6.2
 */