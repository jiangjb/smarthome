/*     */ package com.smarthome.dock.server.packets;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.in.CDataPacket;
/*     */ import com.smarthome.dock.server.packets.in.DDataPacket;
/*     */ import com.smarthome.dock.server.packets.in.KeepAlivePacket;
/*     */ import com.smarthome.dock.server.packets.in.LoginPacket;
/*     */ import com.smarthome.dock.server.packets.in.QueryPacket;
/*     */ import com.smarthome.dock.server.packets.in.SendDDataReplyPacket;
/*     */ import com.smarthome.dock.server.packets.in.UnknownInPacket;
/*     */ import com.smarthome.dock.server.packets.in.alarm.Alarm0140Packet;
/*     */ import com.smarthome.dock.server.packets.in.alarm.Alarm0150Packet;
/*     */ import com.smarthome.dock.server.packets.in.alarm.Alarm0240Packet;
/*     */ import com.smarthome.dock.server.packets.in.report.Report0140Packet;
/*     */ import com.smarthome.dock.server.packets.in.report.Report0150Packet;
/*     */ import com.smarthome.dock.server.packets.in.report.Report0240Packet;
/*     */ import org.jboss.netty.buffer.ChannelBuffer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PacketParser
/*     */   implements IParser
/*     */ {
/*  27 */   private static Logger logger = LoggerFactory.getLogger(PacketParser.class);
/*     */   private PacketHistory history;
/*     */ 
/*     */   public PacketParser(PacketHistory history)
/*     */   {
/*  32 */     this.history = history;
/*     */   }
/*     */ 
/*     */   public boolean accept(ChannelBuffer buf)
/*     */   {
/*  37 */     int bufferLength = buf.readableBytes();
/*  38 */     if (bufferLength <= 0) {
/*  39 */       return false;
/*     */     }
/*     */ 
/*  42 */     byte mark = buf.getByte(0);
/*  43 */     byte version = buf.getByte(1);
/*  44 */     if (mark != 94) {
/*  45 */       return false;
/*     */     }
/*     */ 
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean isDuplicated(ChannelBuffer buf)
/*     */   {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isDuplicatedNeedReply()
/*     */   {
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isIncoming(ChannelBuffer buf)
/*     */   {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */   public InPacket parseIncoming(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */   public OutPacket parseOutcoming(ChannelBuffer buf)
/*     */     throws PacketParseException
/*     */   {
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   public InPacket parseIncoming(ChannelBuffer buf, int offset, int length)
/*     */     throws PacketParseException
/*     */   {
/*  96 */     char command = buf.getChar(2);
/*     */     try {
/*  98 */       switch (command) {
/*     */       case 'ꀀ':
/* 100 */         return new LoginPacket(buf);
/*     */       case 'ꀂ':
/* 102 */         return new KeepAlivePacket(buf);
/*     */       case '뀀':
/* 104 */         return new QueryPacket(buf);
/*     */       }
/* 106 */       byte frameType = buf.getByte(45);
/* 107 */       char devType = buf.getChar(46);
/*     */ 
/* 109 */       if ((command >= 49152) && (command <= 53247)) {
/* 110 */         switch (frameType) {
/*     */         case 2:
/*     */         case 16:
/* 113 */           return new SendDDataReplyPacket(buf);
/*     */         }
/* 115 */         return new CDataPacket(buf);
/*     */       }
/* 117 */       if ((command >= 53248) && (command <= 57343)) {
/* 118 */         switch (frameType) {
/*     */         case 2:
/*     */         case 16:
/* 121 */           return new SendDDataReplyPacket(buf);
/*     */         }
/* 123 */         return new DDataPacket(buf);
/*     */       }
/* 125 */       if ((command >= 57344) && (command <= 61439)) {
/* 126 */         switch (devType)
/*     */         {
/*     */         case 'ɀ':
/* 129 */           return new Report0240Packet(buf);
/*     */         case 'Ő':
/* 131 */           return new Report0150Packet(buf);
/*     */         case 'ŀ':
/* 133 */           return new Report0140Packet(buf);
/*     */         }
/*     */       }
/* 136 */       if ((command >= 61440) && (command <= 65535)) {
/* 137 */         switch (devType) {
/*     */         case 'ɀ':
/* 139 */           return new Alarm0240Packet(buf);
/*     */         case 'Ő':
/* 141 */           return new Alarm0150Packet(buf);
/*     */         case 'ŀ':
/* 143 */           return new Alarm0140Packet(buf);
/*     */         }
/*     */       }
/* 146 */       return new UnknownInPacket(buf);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 151 */       logger.error(e.getMessage(), e);
/* 152 */     }return new UnknownInPacket(buf);
/*     */   }
/*     */ 
/*     */   public OutPacket parseOutcoming(ChannelBuffer buf, int offset, int length)
/*     */     throws PacketParseException
/*     */   {
/* 159 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.PacketParser
 * JD-Core Version:    0.6.2
 */