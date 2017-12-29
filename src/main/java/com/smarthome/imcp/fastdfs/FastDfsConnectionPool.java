/*     */ package com.smarthome.imcp.fastdfs;
/*     */ 
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.URL;
/*     */ import java.util.concurrent.ArrayBlockingQueue;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import org.csource.fastdfs.ClientGlobal;
/*     */ import org.csource.fastdfs.ProtoCommon;
/*     */ import org.csource.fastdfs.TrackerClient;
/*     */ import org.csource.fastdfs.TrackerServer;
/*     */ 
/*     */ public class FastDfsConnectionPool
/*     */ {
/*     */   private static FastDfsConnectionPool _instance;
/*  21 */   public static int size = 20;
/*     */ 
/*  23 */   private static ConcurrentHashMap<TrackerServer, Object> busyConnectionPool = null;
/*     */ 
/*  25 */   private static ArrayBlockingQueue<TrackerServer> idleConnectionPool = null;
/*     */ 
/*  27 */   private static Object obj = new Object();
/*     */ 
/*  29 */   boolean hasConnectionException = false;
/*     */ 
/*  31 */   FastDfsPoolHeartBeat beat = null;
/*     */ 
/*     */   public static FastDfsConnectionPool getInstance()
/*     */   {
/*  37 */     if (_instance == null) {
/*  38 */       _instance = new FastDfsConnectionPool();
/*     */     }
/*  40 */     return _instance;
/*     */   }
/*     */ 
/*     */   public void initialize() {
/*  44 */     init();
/*     */ 
/*  46 */     this.beat = new FastDfsPoolHeartBeat(this);
/*  47 */     this.beat.beat();
/*     */   }
/*     */ 
/*     */   private void init()
/*     */   {
/*  56 */     busyConnectionPool = new ConcurrentHashMap();
/*  57 */     idleConnectionPool = new ArrayBlockingQueue(size);
/*     */     try {
/*  59 */       String classPath = new File(FastDfsUpload.class.getResource("/")
/*  60 */         .getFile()).getCanonicalPath();
/*  61 */       String configFilePath = classPath + "/" + 
/*  62 */         "fastdfs_client.conf";
/*     */ 
/*  65 */       ClientGlobal.init(configFilePath);
/*     */ 
/*  67 */       TrackerServer trackerServer = null;
/*  68 */       TrackerClient trackerClient = new TrackerClient();
/*  69 */       for (int i = 0; i < size; i++) {
/*  70 */         trackerServer = trackerClient.getConnection();
/*  71 */         InetSocketAddress isa = trackerServer.getInetSocketAddress();
/*  72 */         ProtoCommon.activeTest(trackerServer.getSocket());
/*  73 */         idleConnectionPool.add(trackerServer);
/*     */       }
/*     */     } catch (IOException e) {
/*  76 */       e.printStackTrace();
/*     */     } catch (Exception e) {
/*  78 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static TrackerServer checkout(int waitTimes)
/*     */     throws InterruptedException
/*     */   {
/*  93 */     TrackerServer client1 = (TrackerServer)idleConnectionPool.poll(waitTimes, 
/*  94 */       TimeUnit.SECONDS);
/*  95 */     if (client1 == null)
/*     */     {
/*  97 */       FastDfsPoolSysout.warn("FastDfsConnectionPool wait time out ,return null");
/*  98 */       throw new BusinessException(
/*  99 */         "FastDfsConnectionPool wait time out ,return null");
/*     */     }
/* 101 */     busyConnectionPool.put(client1, obj);
/* 102 */     return client1;
/*     */   }
/*     */ 
/*     */   public static void checkin(TrackerServer client1)
/*     */   {
/* 109 */     if (busyConnectionPool.remove(client1) != null)
/* 110 */       idleConnectionPool.add(client1);
/*     */   }
/*     */ 
/*     */   public synchronized void drop(TrackerServer trackerServer)
/*     */   {
/*     */     try
/*     */     {
/* 122 */       trackerServer.close();
/*     */     } catch (IOException localIOException1) {
/*     */     }
/* 125 */     if (busyConnectionPool.remove(trackerServer) != null)
/*     */       try
/*     */       {
/* 128 */         FastDfsPoolSysout.warn("FastDfsConnectionPool drop a connnection");
/* 129 */         FastDfsPoolSysout.warn("FastDfsConnectionPool size:" + (
/* 130 */           busyConnectionPool.size() + idleConnectionPool
/* 131 */           .size()));
/* 132 */         TrackerClient trackerClient = new TrackerClient();
/* 133 */         trackerServer = trackerClient.getConnection();
/*     */       }
/*     */       catch (IOException ie)
/*     */       {
			      if (!isContinued(trackerServer)) {
/* 140 */         return;}
/*     */         try
/*     */         {
/* 144 */           ProtoCommon.activeTest(trackerServer
/* 145 */             .getSocket());
/* 146 */           idleConnectionPool.add(trackerServer);
/*     */ 
/* 148 */           FastDfsPoolSysout.info("FastDfsConnectionPool add a connnection");
/* 149 */           FastDfsPoolSysout.info("FastDfsConnectionPool size:" + (
/* 150 */             busyConnectionPool.size() + idleConnectionPool
/* 151 */             .size()));
/*     */         } catch (IOException ie1) {
/* 153 */           trackerServer = null;
/*     */ 
/* 155 */           FastDfsPoolSysout.warn("FastDfsConnectionPool getConnection generate exception");
/*     */         }
/*     */       }
/*     */       finally
/*     */       {
/* 139 */         if (!isContinued(trackerServer)) {
/* 140 */           return;
/*     */         }
/*     */         try
/*     */         {
/* 144 */           ProtoCommon.activeTest(trackerServer
/* 145 */             .getSocket());
/* 146 */           idleConnectionPool.add(trackerServer);
/*     */ 
/* 148 */           FastDfsPoolSysout.info("FastDfsConnectionPool add a connnection");
/* 149 */           FastDfsPoolSysout.info("FastDfsConnectionPool size:" + (
/* 150 */             busyConnectionPool.size() + idleConnectionPool
/* 151 */             .size()));
/*     */         } catch (IOException ie) {
/* 153 */           trackerServer = null;
/*     */ 
/* 155 */           FastDfsPoolSysout.warn("FastDfsConnectionPool getConnection generate exception");
/*     */         }
/*     */       }
/*     */   }
/*     */ 
/*     */   public boolean isContinued(TrackerServer trackerServer)
/*     */   {
/* 162 */     if ((trackerServer == null) && (this.hasConnectionException)) {
/* 163 */       return false;
/*     */     }
/* 165 */     if (trackerServer == null) {
/* 166 */       this.hasConnectionException = true;
/*     */ 
/* 168 */       detector();
/*     */     }
/* 170 */     if (this.hasConnectionException)
/*     */     {
/* 172 */       return false;
/*     */     }
/* 174 */     return true;
/*     */   }
/*     */ 
/*     */   private void detector()
/*     */   {
/* 179 */     new Thread()
/*     */     {
/*     */       public void run() {
/* 182 */         String msg = "detector connection fail";
/*     */         while (true) {
/* 184 */           TrackerServer trackerServer = null;
/* 185 */           TrackerClient trackerClient = new TrackerClient();
/*     */           try {
/* 187 */             trackerServer = trackerClient.getConnection();
/* 188 */             Thread.sleep(5000L);
/*     */           } catch (IOException e) {
/* 190 */             e.printStackTrace();
/*     */ 
/* 194 */             if (trackerServer != null) {
/* 195 */               msg = "detector connection success";
/* 196 */               break;
/*     */             }
/*     */ 
/* 199 */             FastDfsPoolSysout.warn("current FastDfsConnectionPool has size:" + (
/* 200 */               FastDfsConnectionPool.busyConnectionPool.size() + FastDfsConnectionPool.idleConnectionPool
/* 201 */               .size()));
/* 202 */             FastDfsPoolSysout.warn(msg); continue;
/*     */           }
/*     */           catch (InterruptedException e)
/*     */           {
/* 192 */             e.printStackTrace();
/*     */ 
/* 194 */             if (trackerServer != null) {
/* 195 */               msg = "detector connection success";
/* 196 */               break;
/*     */             }
/*     */ 
/* 199 */             FastDfsPoolSysout.warn("current FastDfsConnectionPool has size:" + (
/* 200 */               FastDfsConnectionPool.busyConnectionPool.size() + FastDfsConnectionPool.idleConnectionPool
/* 201 */               .size()));
/* 202 */             FastDfsPoolSysout.warn(msg); continue;
/*     */           }
/*     */           finally
/*     */           {
/* 194 */             if (trackerServer != null) {
/* 195 */               msg = "detector connection success";
/* 196 */               break;
/*     */             }
/*     */ 
/* 199 */             FastDfsPoolSysout.warn("current FastDfsConnectionPool has size:" + (
/* 200 */               FastDfsConnectionPool.busyConnectionPool.size() + FastDfsConnectionPool.idleConnectionPool
/* 201 */               .size()));
/* 202 */             FastDfsPoolSysout.warn(msg);
/*     */           }
/* 194 */           if (trackerServer != null) {
/* 195 */             msg = "detector connection success";
/* 196 */             break;
/*     */           }
/*     */ 
/* 199 */           FastDfsPoolSysout.warn("current FastDfsConnectionPool has size:" + (
/* 200 */             FastDfsConnectionPool.busyConnectionPool.size() + FastDfsConnectionPool.idleConnectionPool
/* 201 */             .size()));
/* 202 */           FastDfsPoolSysout.warn(msg);
/*     */         }
/*     */ 
/* 219 */         if (FastDfsConnectionPool.idleConnectionPool.size() != 0)
/*     */         {
/* 221 */           FastDfsPoolSysout.warn("idleConnectionPool start close trackerserver");
/*     */ 
/* 223 */           for (int i = 0; i < FastDfsConnectionPool.size; i++) {
/* 224 */             TrackerServer ts = (TrackerServer)FastDfsConnectionPool.idleConnectionPool.poll();
/* 225 */             if (ts != null) {
/*     */               try {
/* 227 */                 ts.close();
/*     */               }
/*     */               catch (Exception e) {
/* 230 */                 FastDfsPoolSysout.warn("idleConnectionPool start close trackerserver");
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 236 */         FastDfsConnectionPool.this.hasConnectionException = false;
/* 237 */         FastDfsConnectionPool.this.init();
/*     */       }
/*     */     }
/* 239 */     .start();
/*     */   }
/*     */ 
/*     */   public ArrayBlockingQueue<TrackerServer> getIdleConnectionPool() {
/* 243 */     return idleConnectionPool;
/*     */   }
/*     */ 
/*     */   public ConcurrentHashMap<TrackerServer, Object> getBusyConnectionPool() {
/* 247 */     return busyConnectionPool;
/*     */   }
/*     */ 
/*     */   public void setBusyConnectionPool(ConcurrentHashMap<TrackerServer, Object> busyConnectionPool)
/*     */   {
/* 252 */     busyConnectionPool = busyConnectionPool;
/*     */   }
/*     */ 
/*     */   public boolean isHasConnectionException() {
/* 256 */     return this.hasConnectionException;
/*     */   }
/*     */ 
/*     */   public void setHasConnectionException(boolean hasConnectionException) {
/* 260 */     this.hasConnectionException = hasConnectionException;
/*     */   }
/*     */ 
/*     */   public void setIdleConnectionPool(ArrayBlockingQueue<TrackerServer> idleConnectionPool)
/*     */   {
/* 265 */     idleConnectionPool = idleConnectionPool;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.fastdfs.FastDfsConnectionPool
 * JD-Core Version:    0.6.2
 */