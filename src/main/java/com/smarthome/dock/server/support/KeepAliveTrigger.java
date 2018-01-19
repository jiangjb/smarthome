/*     */ package com.smarthome.dock.server.support;
/*     */ 
/*     */ import com.smarthome.dock.server.helper.PacketProcessHelper;
/*     */ import com.smarthome.dock.server.packets.in.KeepAlivePacket;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ public class KeepAliveTrigger<T>
/*     */   implements Callable<T>
/*     */ {
/*     */   private PacketProcessor packetProcessor;
/*     */   private Queue<KeepAlivePacket> aliveQueue;
/*     */ 
/*     */   public KeepAliveTrigger(PacketProcessor packetProcessor)
/*     */   {
	          //System.out.println("KeepAliveTrigger 构造器");//有经过
/*  19 */     this.packetProcessor = packetProcessor;
/*  20 */     this.aliveQueue = new LinkedList();
/*  21 */     PacketProcessor.executorScheduled.schedule(this, 60000L, TimeUnit.MILLISECONDS);
/*     */   }
/*     */ 
/*     */   public synchronized void add(KeepAlivePacket packet)
/*     */   {
/*  33 */     packet.setTimeout(System.currentTimeMillis() + 60000L);
/*  34 */     remove(packet);
/*     */ 
/*  36 */     this.aliveQueue.offer(packet);
/*     */   }
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/*  43 */     this.aliveQueue.clear();
/*     */   }
/*     */ 
/*     */   public synchronized KeepAlivePacket get()
/*     */   {
/*  53 */     return (KeepAlivePacket)this.aliveQueue.peek();
/*     */   }
/*     */ 
/*     */   public synchronized KeepAlivePacket remove()
/*     */   {
/*  63 */     return (KeepAlivePacket)this.aliveQueue.poll();
/*     */   }
/*     */ 
/*     */   public synchronized void remove(KeepAlivePacket ack)
/*     */   {
/*  72 */     for (Iterator i = this.aliveQueue.iterator(); i.hasNext(); ) {
/*  73 */       KeepAlivePacket packet = (KeepAlivePacket)i.next();
/*  74 */       if (packet.equals(ack)) {
/*  75 */         i.remove();
/*  76 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private long getTimeoutLeft()
/*     */   {
/*  88 */     KeepAlivePacket packet = get();
/*  89 */     if (packet == null) {
/*  90 */       return 60000L;
/*     */     }
/*  92 */     return packet.getTimeout() - System.currentTimeMillis();
/*     */   }
/*     */ 
/*     */   public T call()
/*     */     throws Exception
/*     */   {
/*  99 */     long t = getTimeoutLeft();
//			  System.out.println("t:"+t);//60000
/* 100 */     while (t <= 0L) {
				System.out.println("t <= 0L");
/* 101 */       KeepAlivePacket packet = remove();
				System.out.println("packet:"+packet);//null
/*     */ 
/* 103 */       if (packet != null) {
	              System.out.println("packet != null");
/* 104 */         this.packetProcessor.getPacketProcessHelper().procesKeepAliveLost(packet);
/*     */       }
/*     */ 
/* 107 */       t = getTimeoutLeft();
/*     */     }
/* 109 */     PacketProcessor.executorScheduled.schedule(this, 10000L, TimeUnit.MILLISECONDS);
/* 110 */     return null;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.support.KeepAliveTrigger
 * JD-Core Version:    0.6.2
 */