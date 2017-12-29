/*     */ package com.smarthome.dock.server.handler;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.InPacket;
/*     */ import com.smarthome.dock.server.packets.PacketHelper;
/*     */ import com.smarthome.dock.server.packets.PacketParseException;
/*     */ import com.smarthome.dock.server.support.PacketProcessor;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ import org.jboss.netty.buffer.ChannelBufferFactory;
/*     */ import org.jboss.netty.channel.Channel;
/*     */ import org.jboss.netty.channel.ChannelHandlerContext;
/*     */ import org.jboss.netty.handler.codec.frame.FrameDecoder;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PacketDecoder extends FrameDecoder
/*     */ {
/*  22 */   private static Logger logger = LoggerFactory.getLogger(PacketDecoder.class);
/*     */   private PacketProcessor packetProcessor;
/*     */ 
/*     */   public PacketDecoder(PacketProcessor packetProcessor)
/*     */   {
/*  27 */     this.packetProcessor = packetProcessor;
/*     */   }
/*     */ 
/*     */   protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buf)
/*     */     throws Exception
/*     */   {
/*  33 */     int length = buf.readableBytes();
/*  34 */     int packetLength = ',' + buf.getChar(4);
/*  35 */     ChannelBuffer frame = buf.factory().getBuffer(packetLength);
/*  36 */     InPacket packets = this.packetProcessor.getPacketHelper().processIn(frame);
/*  37 */     if (length > 4) {
/*     */       try {
/*  39 */         List ret = new ArrayList();
/*  40 */         char command = buf.getChar(2);
/*     */ 
/*  42 */         while ((length > 4) && (accept(command))) {
/*  43 */           InPacket packet = decodePacket(buf);
/*  44 */           if (packet != null) {
/*  45 */             ret.add(packet);
/*     */           }
/*  47 */           length = buf.readableBytes();
/*  48 */           command = length > 4 ? buf.getChar(2) : 65535;
/*     */         }
/*  50 */         int alen = buf.readableBytes();
/*  51 */         if (alen > 0) {
/*  52 */           int readerIndex = buf.readerIndex();
/*  53 */           buf.skipBytes(alen);
/*  54 */           buf.readerIndex(alen + readerIndex);
/*  55 */           logger.error("异常数据 " + alen + "字节");
/*  56 */           if (packets != null) {
/*  57 */             String devId = packets.getDevId();
/*  58 */             System.err.println("devId " + devId);
/*     */           }
/*     */         }
/*  61 */         return ret;
/*     */       } catch (Exception e) {
/*  63 */         int alen = buf.readableBytes();
/*  64 */         if (alen > 0) {
/*  65 */           int readerIndex = buf.readerIndex();
/*  66 */           buf.skipBytes(alen);
/*  67 */           buf.readerIndex(alen + readerIndex);
/*     */ 
/*  69 */           logger.error("异常数据 " + alen + "字节");
/*  70 */           if (packets != null) {
/*  71 */             String devId = packets.getDevId();
/*  72 */             System.err.println("devId " + devId);
/*     */           }
/*     */         }
/*  75 */         e.printStackTrace();
/*  76 */         throw e;
/*     */       }
/*     */     }
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */   private InPacket decodePacket(ChannelBuffer buf) throws PacketParseException {
/*  83 */     int length = buf.readableBytes();
/*  84 */     int packetLength = ',' + buf.getChar(4);
/*     */ 
/*  86 */     if (length < packetLength) {
/*  87 */       buf.skipBytes(length);
/*  88 */       buf.readerIndex(length);
/*  89 */       logger.error("异常数据 " + length + "字节, 数据长度不对");
/*     */ 
/*  91 */       return null;
/*     */     }
/*     */ 
/*  94 */     int readerIndex = buf.readerIndex();
/*     */ 
/*  96 */     ChannelBuffer frame = buf.factory().getBuffer(packetLength);
/*  97 */     frame.writeBytes(buf, 0, packetLength);
/*  98 */     buf.skipBytes(packetLength);
/*  99 */     buf.readerIndex(readerIndex + packetLength);
/* 100 */     InPacket packet = this.packetProcessor.getPacketHelper().processIn(frame);
/*     */ 
/* 103 */     if (packet != null) {
/* 104 */       String devId = packet.getDevId();
/* 105 */       System.err.println("devId " + devId);
/* 106 */       return packet;
/*     */     }
/* 108 */     return null;
/*     */   }
/*     */ 
/*     */   private boolean accept(char command) {
/* 112 */     switch (command) {
/*     */     case 'ꀀ':
/* 114 */       return true;
/*     */     case 'ꀂ':
/* 116 */       return true;
/*     */     case '뀀':
/* 118 */       return true;
/*     */     }
/* 120 */     if ((command >= 49152) && (command <= 53247)) {
/* 121 */       return true;
/*     */     }
/* 123 */     if ((command >= 53248) && (command <= 57343)) {
/* 124 */       return true;
/*     */     }
/* 126 */     if ((command >= 57344) && (command <= 61439)) {
/* 127 */       return true;
/*     */     }
/* 129 */     if ((command >= 61440) && (command <= 65535)) {
/* 130 */       return true;
/*     */     }
/*     */ 
/* 134 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.handler.PacketDecoder
 * JD-Core Version:    0.6.2
 */