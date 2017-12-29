/*    */ package com.smarthome.dock.server.packets.in;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import com.smarthome.dock.server.util.Util;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class CDataPacket extends InPacket
/*    */ {
/* 12 */   private static Logger logger = LoggerFactory.getLogger(CDataPacket.class);
/*    */   private byte[] devData;
/*    */   private String clientId;
/*    */ 
/*    */   public CDataPacket(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 18 */     super(buf);
/*    */ 
/* 20 */     this.clientId = Util.getString2(this.ext);
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 26 */     this.devData = new byte[buf.readableBytes()];
/* 27 */     buf.readBytes(this.devData);
/*    */   }
/*    */ 
/*    */   public byte[] getDevData()
/*    */   {
/* 32 */     return this.devData;
/*    */   }
/*    */ 
/*    */   public String getClientId() {
/* 36 */     return this.clientId;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.CDataPacket
 * JD-Core Version:    0.6.2
 */