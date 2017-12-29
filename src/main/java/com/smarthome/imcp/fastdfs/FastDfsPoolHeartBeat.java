/*    */ package com.smarthome.imcp.fastdfs;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Timer;
/*    */ import java.util.TimerTask;
/*    */ import java.util.concurrent.ArrayBlockingQueue;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import org.csource.fastdfs.ProtoCommon;
/*    */ import org.csource.fastdfs.TrackerServer;
/*    */ 
/*    */ public class FastDfsPoolHeartBeat
/*    */ {
/* 13 */   public static int ahour = 600000;
/* 14 */   public static int waitTimes = 600000;
/*    */ 
/* 16 */   private FastDfsConnectionPool pool = null;
/*    */ 
/*    */   public FastDfsPoolHeartBeat(FastDfsConnectionPool pool) {
/* 19 */     this.pool = pool;
/*    */   }
/*    */ 
/*    */   public void beat() {
/* 23 */     TimerTask task = new TimerTask()
/*    */     {
/*    */       public void run() {
/* 26 */         ArrayBlockingQueue idleConnectionPool = FastDfsPoolHeartBeat.this.pool
/* 27 */           .getIdleConnectionPool();
/* 28 */         TrackerServer ts = null;
/*    */ 
/* 30 */         FastDfsPoolSysout.info("ConnectionPool execution HeartBeat to fdfs server");
/* 31 */         for (int i = 0; i < idleConnectionPool.size(); i++)
/*    */           try {
/* 33 */             ts = (TrackerServer)idleConnectionPool.poll(FastDfsPoolHeartBeat.waitTimes, 
/* 34 */               TimeUnit.SECONDS);
/* 35 */             if (ts == null) break;
/* 36 */             ProtoCommon.activeTest(ts
/* 37 */               .getSocket());
/* 38 */             idleConnectionPool.add(ts);
/*    */           }
/*    */           catch (InterruptedException e)
/*    */           {
/* 44 */             e.printStackTrace();
/*    */           }
/*    */           catch (IOException e) {
/* 47 */             FastDfsPoolHeartBeat.this.pool.drop(ts);
/* 48 */             e.printStackTrace();
/*    */           } catch (Exception e) {
/* 50 */             e.printStackTrace();
/*    */           }
/*    */       }
/*    */     };
/* 56 */     Timer timer = new Timer();
/* 57 */     timer.schedule(task, ahour, waitTimes);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.fastdfs.FastDfsPoolHeartBeat
 * JD-Core Version:    0.6.2
 */