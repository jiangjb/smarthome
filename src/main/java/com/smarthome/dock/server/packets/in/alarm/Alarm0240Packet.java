/*    */ package com.smarthome.dock.server.packets.in.alarm;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import com.smarthome.dock.server.packets.in.AlarmPacket;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class Alarm0240Packet extends AlarmPacket
/*    */ {
/* 28 */   private static Logger logger = LoggerFactory.getLogger(Alarm0240Packet.class);
/*    */   private int frameStatus;
/*    */ 
/*    */   public Alarm0240Packet(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 32 */     super(buf);
/*    */   }
/*    */ 
/*    */   protected void parseFrame(ChannelBuffer buf, int frameLen)
/*    */     throws PacketParseException
/*    */   {
/* 38 */     this.frameStatus = buf.readByte();
/*    */   }
/*    */ 
/*    */   public int getFrameStatus() {
/* 42 */     return this.frameStatus;
/*    */   }
/*    */ 
/*    */   public void setFrameStatus(int frameStatus) {
/* 46 */     this.frameStatus = frameStatus;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.alarm.Alarm0240Packet
 * JD-Core Version:    0.6.2
 */