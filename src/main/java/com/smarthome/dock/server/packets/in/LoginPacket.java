/*    */ package com.smarthome.dock.server.packets.in;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import com.smarthome.dock.server.util.Util;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class LoginPacket extends InPacket
/*    */ {
/* 12 */   private static Logger logger = LoggerFactory.getLogger(LoginPacket.class);
/*    */   private String devData;
/*    */ 
/*    */   public LoginPacket(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 16 */     super(buf);
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf) throws PacketParseException
/*    */   {
/* 21 */     int bufferLength = this.bodyLength;
/* 22 */     byte[] bytes = new byte[bufferLength];
/* 23 */     buf.readBytes(bytes);
/*    */ 
/* 25 */     this.devData = Util.getString(bytes);
/*    */   }
/*    */ 
/*    */   public String getDevData() {
/* 29 */     return this.devData;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.LoginPacket
 * JD-Core Version:    0.6.2
 */