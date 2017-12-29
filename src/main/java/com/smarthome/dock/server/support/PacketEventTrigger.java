/*    */ package com.smarthome.dock.server.support;
/*    */ 
/*    */ import com.smarthome.dock.server.event.PacketEvent;
/*    */ import com.smarthome.dock.server.packets.InPacket;
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ public class PacketEventTrigger<T>
/*    */   implements Callable<T>
/*    */ {
/*    */   private PacketProcessor packetProcessor;
/*    */ 
/*    */   public PacketEventTrigger(PacketProcessor packetProcessor)
/*    */   {
/* 19 */     this.packetProcessor = packetProcessor;
/*    */   }
/*    */ 
/*    */   public T call() throws Exception
/*    */   {
/* 24 */     InPacket packet = this.packetProcessor.removeIncomingPacket();
/* 25 */     while (packet != null)
/*    */     {
/* 27 */       PacketEvent e = new PacketEvent(packet);
/* 28 */       e.type = packet.getCommand();
/* 29 */       this.packetProcessor.firePacketArrivedEvent(e);
/*    */ 
/* 31 */       packet = this.packetProcessor.removeIncomingPacket();
/*    */     }
/* 33 */     return null;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.support.PacketEventTrigger
 * JD-Core Version:    0.6.2
 */