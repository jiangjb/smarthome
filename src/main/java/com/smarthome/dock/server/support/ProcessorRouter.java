/*    */ package com.smarthome.dock.server.support;
/*    */ 
/*    */ import com.smarthome.dock.server.event.IPacketListener;
/*    */ import com.smarthome.dock.server.event.PacketEvent;
/*    */ import java.util.List;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class ProcessorRouter
/*    */ {
/*    */   private List<IPacketListener> listeners;
/*    */   private List<IPacketListener> listenersBackup;
/*    */   private boolean listenerChanged;
/*    */ 
/*    */   public ProcessorRouter(int capacity)
/*    */   {
/* 14 */     this.listeners = new Vector();
/* 15 */     this.listenersBackup = new Vector();
/*    */   }
/*    */ 
/*    */   public synchronized void setListenerChanged(boolean b)
/*    */   {
/* 22 */     this.listenerChanged = b;
/*    */   }
/*    */ 
/*    */   public void installProcessor(IPacketListener listener)
/*    */   {
/* 31 */     if (!this.listeners.contains(listener)) {
/* 32 */       this.listeners.add(listener);
/* 33 */       setListenerChanged(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void removeProcessor(IPacketListener listener)
/*    */   {
/* 42 */     if (this.listeners.contains(listener)) {
/* 43 */       this.listeners.remove(listener);
/* 44 */       setListenerChanged(true);
/*    */     }
/*    */   }
/*    */ 
/*    */   private synchronized boolean isListenerChanged()
/*    */   {
/* 55 */     return this.listenerChanged;
/*    */   }
/*    */ 
/*    */   private synchronized void checkListenerChange()
/*    */   {
/* 62 */     if (isListenerChanged()) {
/* 63 */       this.listenersBackup.clear();
/* 64 */       this.listenersBackup.addAll(this.listeners);
/* 65 */       setListenerChanged(false);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void packetArrived(PacketEvent e)
/*    */   {
/* 75 */     checkListenerChange();
/* 76 */     int size = this.listenersBackup.size();
/* 77 */     for (int i = 0; i < size; i++)
/* 78 */       ((IPacketListener)this.listenersBackup.get(i)).packetArrived(e);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.support.ProcessorRouter
 * JD-Core Version:    0.6.2
 */