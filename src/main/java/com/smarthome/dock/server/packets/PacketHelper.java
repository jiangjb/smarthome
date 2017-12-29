/*    */ package com.smarthome.dock.server.packets;
/*    */ 
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public final class PacketHelper
/*    */ {
/*    */   private IParser parser;
/*    */   private PacketHistory history;
/*    */ 
/*    */   public PacketHelper()
/*    */   {
/* 13 */     this.history = new PacketHistory();
/* 14 */     this.parser = new PacketParser(this.history);
/*    */   }
/*    */ 
/*    */   public InPacket processIn(ChannelBuffer buf) throws PacketParseException
/*    */   {
/*    */     try {
/* 20 */       if (!this.parser.accept(buf))
/* 21 */         return null;
/* 22 */       boolean duplicated = this.parser.isDuplicated(buf);
/* 23 */       if (duplicated) {
/* 24 */         return null;
/*    */       }
/* 26 */       return this.parser.parseIncoming(buf, 0, 0);
/*    */     }
/*    */     catch (PacketParseException e) {
/* 29 */       throw e;
/*    */     }
/*    */   }
/*    */ 
/*    */   public IParser getParser()
/*    */   {
/* 35 */     return this.parser;
/*    */   }
/*    */ 
/*    */   public PacketHistory getHistory()
/*    */   {
/* 42 */     return this.history;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.PacketHelper
 * JD-Core Version:    0.6.2
 */