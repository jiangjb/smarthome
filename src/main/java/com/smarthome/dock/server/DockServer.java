 package com.smarthome.dock.server;
 
 import com.smarthome.dock.server.support.PacketProcessor;
 import com.smarthome.imcp.dao.model.bo.BoDevice;
 import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.net.InetSocketAddress;
 import java.util.concurrent.Executors;
 import org.jboss.netty.bootstrap.ConnectionlessBootstrap;
 import org.jboss.netty.channel.ChannelFactory;
 import org.jboss.netty.channel.group.ChannelGroup;
 import org.jboss.netty.channel.group.ChannelGroupFuture;
 import org.jboss.netty.channel.socket.DatagramChannel;
 import org.jboss.netty.channel.socket.nio.NioDatagramChannelFactory;
 import org.jboss.netty.util.HashedWheelTimer;
 import org.jboss.netty.util.Timer;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import com.smarthome.dock.server.packets.in.KeepAlivePacket;
 import com.smarthome.dock.server.support.KeepAliveTrigger;
 
 public class DockServer
 {
/*  25 */   private static Logger logger = LoggerFactory.getLogger(DockServer.class);

			@Autowired
			private BoDeviceServiceIface <BoDevice, Serializable> boDeviceService;
		    private ChannelFactory factory;
		    private boolean isStarted;
		    private PacketProcessor packetProcessor;
 
/*  35 */   public DockServer() { System.out.println("DockServer"); }
 
 
   public void startServer()
   {
/*  45 */     logger.debug("服务器启动中...");
 
/*  47 */     if (this.packetProcessor == null) {
/*  48 */       this.packetProcessor = new PacketProcessor();
                 System.out.println("null this.packetProcessor ="+this.packetProcessor);
     			}
//			  System.out.println("this.packetProcessor ="+this.packetProcessor);//com.smarthome.dock.server.support.PacketProcessor@5e9a1d8d
/*  50 */     Timer timer = new HashedWheelTimer();
 
/*  52 */     this.factory = new NioDatagramChannelFactory(Executors.newCachedThreadPool());
 
/*  54 */     ConnectionlessBootstrap bootstrap = new ConnectionlessBootstrap(this.factory);
 
/*  57 */     bootstrap.setPipelineFactory(new DockServerPipelineFactory(
/*  58 */       this.packetProcessor));
 
/*  62 */     bootstrap.setOption("tcpNoDelay", Boolean.valueOf(true));
/*  63 */     bootstrap.setOption("keepAlive", Boolean.valueOf(true));
 
/*  66 */     DatagramChannel channel = (DatagramChannel)bootstrap
/*  67 */       .bind(new InetSocketAddress(5555));
//              System.out.println("channel :"+channel);//[id: 0xd3583bb4]
/*  68 */     this.packetProcessor.setChannel(channel);
/*  69 */     this.packetProcessor.getAllChannels().add(channel);

/*  70 */     this.isStarted = true;
 
/*  72 */     logger.debug("服务器启动成功！");
   }
 
   public void start() {
     try {
       Thread t = new Thread(new Runnable()
       {
         public void run() {
           try {
        	   System.out.println("服务15秒后启动");      

//		              DockServer.this.boDeviceService.updateStatus(0);//默认设为0
			  Thread.sleep(15000L);
           } catch (InterruptedException e) {
             e.printStackTrace();
           }
           DockServer.this.startServer();
         }
       });
       t.start();
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public void stop() {
     if (this.isStarted) {
       if (this.packetProcessor.getAllChannels() != null) {
         ChannelGroupFuture future = this.packetProcessor.getAllChannels()
           .close();
         future.awaitUninterruptibly();
       }
       if (this.factory != null) {
         this.factory.releaseExternalResources();
         this.factory = null;
       }
       if (this.packetProcessor != null) {
        this.packetProcessor.release();
       }
 
       this.isStarted = false;
       logger.debug("服务器停止!");
 
       if (this.factory != null) {
         this.factory.releaseExternalResources();
         this.factory = null;
       }
     }
   }
 
   public boolean isStarted() {
     return this.isStarted;
   }
 
   public void setPacketProcessor(PacketProcessor packetProcessor) {
     this.packetProcessor = packetProcessor;
   }
 
   public static void main(String[] args)
   {
   }
 }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.DockServer
 * JD-Core Version:    0.6.2
 */