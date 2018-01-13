/*     */ package com.smarthome.dock.server;
/*     */ 
/*     */ import com.smarthome.dock.server.support.PacketProcessor;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.concurrent.Executors;
/*     */ import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
/*     */ import org.jboss.netty.channel.ChannelFactory;
/*     */ import org.jboss.netty.channel.group.ChannelGroup;
/*     */ import org.jboss.netty.channel.group.ChannelGroupFuture;
/*     */ import org.jboss.netty.channel.socket.DatagramChannel;
/*     */ import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
/*     */ import org.jboss.netty.util.HashedWheelTimer;
/*     */ import org.jboss.netty.util.Timer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
		  import com.smarthome.dock.server.packets.in.KeepAlivePacket;
		  import com.smarthome.dock.server.support.KeepAliveTrigger;
/*     */ 
/*     */ public class DockServer
/*     */ {
/*  25 */   private static Logger logger = LoggerFactory.getLogger(DockServer.class);

			@Autowired
			private BoDeviceServiceIface <BoDevice, Serializable> boDeviceService;

/*     */   private ChannelFactory factory;
/*     */   private boolean isStarted;
/*     */   private PacketProcessor packetProcessor;
/*     */ 
/*  35 */   public DockServer() { System.out.println(""); }
/*     */ 
/*     */ 
/*     */   public void startServer()
/*     */   {
/*  45 */     logger.debug("服务器启动中...");
/*     */ 
/*  47 */     if (this.packetProcessor == null) {
/*  48 */       this.packetProcessor = new PacketProcessor();
/*     */     }
/*  50 */     Timer timer = new HashedWheelTimer();
/*     */ 
/*  52 */     this.factory = new NioDatagramChannelFactory(Executors.newCachedThreadPool());
/*     */ 
/*  54 */     ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(this.factory);
/*     */ 
/*  57 */     bootstrap.setPipelineFactory(new DockServerPipelineFactory(
/*  58 */       this.packetProcessor));
/*     */ 
/*  62 */     bootstrap.setOption("tcpNoDelay", Boolean.valueOf(true));
/*  63 */     bootstrap.setOption("keepAlive", Boolean.valueOf(true));
/*     */ 
/*  66 */     DatagramChannel channel = (DatagramChannel)bootstrap
/*  67 */       .bind(new InetSocketAddress(5555));
/*  68 */     this.packetProcessor.setChannel(channel);
/*  69 */     this.packetProcessor.getAllChannels().add(channel);
/*  70 */     this.isStarted = true;
/*     */ 
/*  72 */     logger.debug("服务器启动成功！");
/*     */   }
/*     */ 
/*     */   public void start() {
/*     */     try {
/*  77 */       Thread t = new Thread(new Runnable()
/*     */       {
/*     */         public void run() {
/*     */           try {
/*  81 */             System.out.println("服务15秒后启动");

					  //判断离线|在线是否在线
//					  com.smarthome.dock.server.packets.in.KeepAlivePacket packet = (com.smarthome.dock.server.packets.in.KeepAlivePacket)in;
//					  String deviceCode = packet.getDevId();
//					  String hostName = packet.getHostName();
//					  boolean isUpdate = false;
//					  BoDevice device = this.boDeviceService.findByCode(deviceCode);
//					  if ((device.getStatus() == null) || (device.getStatus().intValue() <= 0) || (!ip.equals(device.getDevIp()))) {
//						  String[] ret = { "0", "0", "", "", "" };
//						  this.boDeviceService.updateIP(device.getDeviceId(), 1, ip, ret);
//						  isUpdate = true;
//					  }else {
//						  this.boDeviceService.updateStatus(deviceCode, 0);
//					  }
//					  if (!isUpdate)
//						  this.boDeviceService.updateStatus(deviceCode, 1);

					  System.out.println("tomcat服务启动前执行 ");
//		              DockServer.this.boDeviceService.updateStatus(0);//默认设为0
/*  83 */             Thread.sleep(15000L);
/*     */           } catch (InterruptedException e) {
/*  85 */             e.printStackTrace();
/*     */           }
/*  87 */           DockServer.this.startServer();
/*     */         }
/*     */       });
/*  90 */       t.start();
/*     */     } catch (Exception e) {
/*  92 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void stop() {
/*  97 */     if (this.isStarted) {
/*  98 */       if (this.packetProcessor.getAllChannels() != null) {
/*  99 */         ChannelGroupFuture future = this.packetProcessor.getAllChannels()
/* 100 */           .close();
/* 101 */         future.awaitUninterruptibly();
/*     */       }
/* 103 */       if (this.factory != null) {
/* 104 */         this.factory.releaseExternalResources();
/* 105 */         this.factory = null;
/*     */       }
/* 107 */       if (this.packetProcessor != null) {
/* 108 */         this.packetProcessor.release();
/*     */       }
/*     */ 
/* 111 */       this.isStarted = false;
/* 112 */       logger.debug("服务器停止!");
/*     */ 
/* 114 */       if (this.factory != null) {
/* 115 */         this.factory.releaseExternalResources();
/* 116 */         this.factory = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean isStarted() {
/* 122 */     return this.isStarted;
/*     */   }
/*     */ 
/*     */   public void setPacketProcessor(PacketProcessor packetProcessor) {
/* 126 */     this.packetProcessor = packetProcessor;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.DockServer
 * JD-Core Version:    0.6.2
 */