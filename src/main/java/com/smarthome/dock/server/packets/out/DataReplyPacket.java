/*    */ package com.smarthome.dock.server.packets.out;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.OutPacket;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class DataReplyPacket extends OutPacket
/*    */ {
/*    */   private byte[] devData;
/*    */ 
/*    */   public DataReplyPacket(char command, String devId)
/*    */   {
/* 13 */     super(command, devId);
/*    */   }
/*    */ 
/*    */   protected void putBody(ChannelBuffer buf)
/*    */   {
/* 19 */     buf.writeBytes(this.devData);
/*    */   }
/*    */ 
/*    */   public void setDevData(byte[] devData) {
/* 23 */     this.devData = devData;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.out.DataReplyPacket
 * JD-Core Version:    0.6.2
 */