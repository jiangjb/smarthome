/*     */ package com.smarthome.dock.server.helper;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.OutPacket;
/*     */ import com.smarthome.dock.server.packets.out.SendDDataPacket;
/*     */ import com.smarthome.dock.server.support.PacketProcessor;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class BoProcessHelper
/*     */ {
/*  46 */   private static Logger logger = LoggerFactory.getLogger(BoProcessHelper.class);
/*     */   private PacketProcessor packetProcessor;
/*     */   private UserManager userManager;
/*     */ 
/*     */   public BoProcessHelper(PacketProcessor packetProcessor)
/*     */   {
/*  53 */     this.packetProcessor = packetProcessor;
/*  54 */     this.userManager = packetProcessor.getUserManager();
/*     */   }
/*     */ 
/*     */   public void processSendDData(String devId, char devType, byte frameType, byte[] frameBody)
/*     */   {
/*  65 */     logger.info("发送 " + devId + " processSendDData");
/*  66 */     SendDDataPacket packet = new SendDDataPacket(devId);
/*  67 */     packet.setFrameType(frameType);
/*  68 */     packet.setDevType(devType);
/*  69 */     packet.setFrameBody(frameBody);
/*     */ 
/*  72 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*     */ 
/*  74 */     if (address == null) {
/*  75 */       logger.error(devId + " 设备IP 不存在");
/*  76 */       return;
/*     */     }
/*     */ 
/*  79 */     packet.setHostName(address[0]);
/*  80 */     packet.setPort(Integer.valueOf(address[1]).intValue());
/*     */ 
/*  82 */     this.packetProcessor.sendStrategy(packet);
/*     */   }
/*     */ 
/*     */   public void processSend0Packet(String devId, char devType, byte frameType)
/*     */   {
/*  93 */     SendDDataPacket packet = new SendDDataPacket(devId);
/*  94 */     int req = OutPacket.getNextSeq();
/*  95 */     logger.info("发送清0命令 " + devId + " frameType = " + frameType + " rand=" + req);
/*     */ 
/*  97 */     packet.setFrameType(frameType);
/*  98 */     packet.setDevType(devType);
/*  99 */     byte[] b = new byte[1];
/* 100 */     b[0] = ((byte)req);
/*     */ 
/* 102 */     packet.setFrameBody(b);
/*     */ 
/* 104 */     String[] address = this.packetProcessor.getSocketAddress(devId);
/*     */ 
/* 106 */     if (address == null) {
/* 107 */       logger.error(devId + " 设备IP 不存在");
/* 108 */       return;
/*     */     }
/*     */ 
/* 111 */     packet.setHostName(address[0]);
/* 112 */     packet.setPort(Integer.valueOf(address[1]).intValue());
/*     */ 
/* 114 */     this.packetProcessor.send(packet);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.helper.BoProcessHelper
 * JD-Core Version:    0.6.2
 */