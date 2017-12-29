/*    */ package com.smarthome.dock.server.packets.in;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class QueryPacket extends InPacket
/*    */ {
/* 12 */   private static Logger logger = LoggerFactory.getLogger(QueryPacket.class);
/*    */ 
/*    */   public QueryPacket(ChannelBuffer buf) throws PacketParseException {
/* 15 */     super(buf);
/*    */   }
/*    */ 
/*    */   protected void parseBody(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.QueryPacket
 * JD-Core Version:    0.6.2
 */