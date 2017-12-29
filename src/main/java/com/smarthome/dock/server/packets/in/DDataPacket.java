/*    */ package com.smarthome.dock.server.packets.in;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import com.smarthome.dock.server.util.Util;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class DDataPacket extends InPacket
/*    */ {
/* 12 */   private static Logger logger = LoggerFactory.getLogger(DDataPacket.class);
/*    */   private byte[] devData;
/*    */   private String clientId;
/*    */ 
/*    */   public DDataPacket(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 18 */     super(buf);
/*    */ 
/* 20 */     this.clientId = Util.getString2(this.ext);
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf) throws PacketParseException
/*    */   {
/* 25 */     this.devData = new byte[buf.readableBytes()];
/* 26 */     buf.readBytes(this.devData);
/*    */   }
/*    */ 
/*    */   public byte[] getDevData() {
/* 30 */     return this.devData;
/*    */   }
/*    */ 
/*    */   public String getClientId() {
/* 34 */     return this.clientId;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.DDataPacket
 * JD-Core Version:    0.6.2
 */