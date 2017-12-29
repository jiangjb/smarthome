/*     */ package com.smarthome.dock.server.support;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.ErrorPacket;
/*     */ import com.smarthome.dock.server.packets.OutPacket;
/*     */ import com.smarthome.dock.server.packets.PacketHistory;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class ResendTrigger<T>
/*     */   implements Callable<T>
/*     */ {
/*     */   private PacketProcessor packetProcessor;
/*     */   private PacketHistory history;
/*     */   private Queue<OutPacket> timeoutQueue;
/*     */   private String portName;
/*     */ 
/*     */   public ResendTrigger(PacketProcessor packetProcessor)
/*     */   {
/*  27 */     this.packetProcessor = packetProcessor;
/*  28 */     this.history = packetProcessor.getHistory();
/*  29 */     this.timeoutQueue = new LinkedList();
/*  30 */     PacketProcessor.executorScheduled.schedule(this, 350L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */   public synchronized void add(OutPacket packet)
/*     */   {
/*  42 */     this.timeoutQueue.offer(packet);
/*     */   }
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/*  49 */     this.timeoutQueue.clear();
/*     */   }
/*     */ 
/*     */   public synchronized OutPacket get()
/*     */   {
/*  59 */     return (OutPacket)this.timeoutQueue.peek();
/*     */   }
/*     */ 
/*     */   public synchronized OutPacket remove()
/*     */   {
/*  69 */     return (OutPacket)this.timeoutQueue.poll();
/*     */   }
/*     */ 
/*     */   public synchronized void remove(OutPacket ack)
/*     */   {
/*  78 */     int hash = ack.hashCode();
/*  79 */     for (Iterator i = this.timeoutQueue.iterator(); i.hasNext(); ) {
/*  80 */       OutPacket packet = (OutPacket)i.next();
/*  81 */       if (packet.hashCode() == hash) {
/*  82 */         i.remove();
/*  83 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private long getTimeoutLeft()
/*     */   {
/*  95 */     OutPacket packet = get();
/*  96 */     if (packet == null) {
/*  97 */       return 350L;
/*     */     }
/*  99 */     return packet.getTimeout() - System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   private void fireOperationTimeOutEvent(OutPacket packet)
/*     */   {
/* 107 */     ErrorPacket error = new ErrorPacket(1, packet.getDevId());
/* 108 */     error.timeoutPacket = packet;
/* 109 */     this.packetProcessor.addIncomingPacket(error);
/*     */   }
/*     */ 
/*     */   public T call()
/*     */     throws Exception
/*     */   {
/* 116 */     long t = getTimeoutLeft();
/* 117 */     while (t <= 0L) {
/* 118 */       OutPacket packet = remove();
/*     */ 
/* 121 */       if ((packet != null) && (!this.history.check(packet, false))) {
/* 122 */         if (packet.needResend())
/*     */         {
/* 124 */           this.packetProcessor.send(packet);
/*     */         }
/*     */         else {
/* 127 */           fireOperationTimeOutEvent(packet);
/*     */         }
/*     */       }
/*     */ 
/* 131 */       t = getTimeoutLeft();
/*     */     }
/* 133 */     PacketProcessor.executorScheduled.schedule(this, t, TimeUnit.MILLISECONDS);
/*     */ 
/* 135 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.support.ResendTrigger
 * JD-Core Version:    0.6.2
 */