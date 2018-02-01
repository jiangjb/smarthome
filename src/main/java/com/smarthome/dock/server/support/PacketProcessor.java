/*     */ package com.smarthome.dock.server.support;
/*     */ 
/*     */ import com.smarthome.dock.server.event.IPacketListener;
/*     */ import com.smarthome.dock.server.event.PacketEvent;
/*     */ import com.smarthome.dock.server.helper.MessageHelper;
/*     */ import com.smarthome.dock.server.helper.PacketProcessHelper;
/*     */ import com.smarthome.dock.server.helper.UserManager;
/*     */ import com.smarthome.dock.server.packets.InPacket;
/*     */ import com.smarthome.dock.server.packets.OutPacket;
/*     */ import com.smarthome.dock.server.packets.PacketHelper;
/*     */ import com.smarthome.dock.server.packets.PacketHistory;
		  import com.smarthome.dock.server.packets.in.KeepAlivePacket;//离线  的类
/*     */ import com.smarthome.dock.server.util.Util;
/*     */ import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.LinkedList;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.ScheduledExecutorService;
/*     */ import org.jboss.netty.channel.Channel;
/*     */ import org.jboss.netty.channel.group.ChannelGroup;
/*     */ import org.jboss.netty.channel.group.DefaultChannelGroup;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class PacketProcessor
/*     */   implements IPacketListener
/*     */ {
/*  41 */   private static Logger logger = LoggerFactory.getLogger(PacketProcessor.class);
/*     */   private PacketHelper packetHelper;
/*     */   private Queue<InPacket> receiveQueue;
/*  46 */   public Executor executor = null;
/*     */ 
/*  48 */   public static final ScheduledExecutorService executorScheduled = Executors.newSingleThreadScheduledExecutor();
/*     */   protected Callable<Object> packetEventTrigger;
/*     */   private ProcessorRouter router;
/*     */   protected static final int PROCESSOR_COUNT = 3;
/*     */   protected ResendTrigger<Object> resendTrigger;
/*     */   protected KeepAliveTrigger<Object> keepAliveTrigger;
/*     */   private PacketProcessHelper packetProcessHelper;
/*     */   private MessageHelper messageHelper;
/*     */   private UserManager userManager;
/*     */   private boolean connctioned;
/*     */   private Channel channel;
/*  72 */   private final ChannelGroup allChannels = new DefaultChannelGroup("vlghs-server");
/*     */ 
/*  74 */   private final ConcurrentHashMap<String, String[]> allKeysChannelId = new ConcurrentHashMap();
/*     */ 
/*     */   public PacketProcessor() {
/*  77 */     this.executor = Executors.newCachedThreadPool();
/*     */ 
/*  79 */     this.router = new ProcessorRouter(3);//??应该很重要
/*     */ 
/*  81 */     this.packetEventTrigger = new PacketEventTrigger(this);
/*     */ 
/*  83 */     this.packetHelper = new PacketHelper();
/*  84 */     this.receiveQueue = new LinkedList();
/*  85 */     this.resendTrigger = new ResendTrigger(this);
/*     */ 
/*  87 */     this.userManager = new UserManager();
/*     */ 
/*  89 */     this.keepAliveTrigger = new KeepAliveTrigger(this);
/*     */ 
/*  91 */     if (this.packetProcessHelper == null) {
/*  92 */       this.packetProcessHelper = new PacketProcessHelper(this);
/*     */     }
/*  94 */     this.messageHelper = new MessageHelper();
/*     */ 
/*  96 */     this.router.installProcessor(this);
/*     */   }
/*     */ 
/*     */   public void release() {
/* 100 */     this.resendTrigger.clear();
/*     */ 
/* 102 */     executorScheduled.shutdownNow();
/* 103 */     this.packetHelper.getHistory().clear();
/*     */   }
/*     */ 
/*     */   public void processPacket(InPacket packet)
/*     */   {
	          logger.info("进入PacketProcessor类的 processPacket方法，这里面的packetArrived方法在下面 -判断command");
/* 115 */     PacketEvent e = new PacketEvent(packet);
/* 116 */     e.type = packet.getCommand();
/* 117 */     packetArrived(e);
/*     */   }
         
            //this.channel.write是 发送数据包到服务器吗？如果是的话    参照知识点--使用SocketChannel的NIO客户机服务器通信
/*     */   public void send(OutPacket packet)
/*     */   {
/* 131 */     if (this.channel != null)
/*     */     {
				System.err.println("进了send方法");
/* 133 */       this.channel.write(packet, new InetSocketAddress(packet.getHostName(), packet.getPort()));
/* 134 */       logger.debug("发送包" + Util.getCommandString(packet.getCommand()) + "　devId：" + packet.getDevId() + " ip:" + packet.getHostName());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendStrategy(OutPacket packet)
/*     */   {
/* 154 */     if (this.channel != null) {
/* 155 */       System.err.println("进了sendStrategy方法");
/* 156 */       this.channel.write(packet, new InetSocketAddress(packet.getHostName(), packet.getPort()));
/* 157 */       logger.info("发送包" + Util.getCommandString(packet.getCommand()) + "　devId：" + packet.getDevId() + " ip:" + packet.getHostName() + " port:" + packet.getPort());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sends(OutPacket packet)
/*     */   {
/* 172 */     if (this.channel != null) {
				System.err.println("进了sends方法");
/* 173 */       this.channel.write(packet, new InetSocketAddress(packet.getHostName(), packet.getPort()));
/* 174 */       logger.info("发送包" + Util.getCommandString(packet.getCommand()) + "　devId：" + packet.getDevId() + " ip:" + packet.getHostName() + " port:" + packet.getPort());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removeResendPacket(OutPacket packet)
/*     */   {
/* 186 */     this.resendTrigger.remove(packet);
/*     */   }
/*     */ 
/*     */   public void addResendPacket(OutPacket packet)
/*     */   {
/* 196 */     this.resendTrigger.add(packet);
/*     */   }
/*     */ 
/*     */   public synchronized void addIncomingPacket(InPacket packet)
/*     */   {
/* 205 */     if (packet == null)
/* 206 */       return;
/* 207 */     this.receiveQueue.offer(packet);
/* 208 */     ((ExecutorService)this.executor).submit(this.packetEventTrigger);
/*     */   }
/*     */ 
/*     */   public synchronized InPacket removeIncomingPacket()
/*     */   {
/* 217 */     return (InPacket)this.receiveQueue.poll();
/*     */   }
/*     */ 
/*     */   public synchronized boolean isEmpty()
/*     */   {
/* 224 */     return this.receiveQueue.isEmpty();
/*     */   }
/*     */ 
/*     */   public void firePacketArrivedEvent(PacketEvent e)
/*     */   {
			  InPacket in = (InPacket)e.getSource();//1-31test
			  logger.info("firePacketArrivedEvent method(异常路径)  command="+Util.getCommandString(in.getCommand()));//1-31test  这句和上句不能合成Util.getCommandString((InPacket)e.getSource().getCommand())  原因不明？？？
/* 233 */     this.router.packetArrived(e);//调用ProcessRouter的packetArrived方法
/*     */   }
/*     */   ////正确的流程：解析包-》 packetArrived -》 procesKeepAliveSuccess
/*     */   public void packetArrived(PacketEvent e)
/*     */   {//与Util类结合使用  Util.getCommandString()
/* 238 */     InPacket in = (InPacket)e.getSource();
/* 254 */     logger.info("开始处理" + in.toString() + "　devId:" + in.getDevId() + " commond：" + Util.getCommandString(in.getCommand()));
/* 259 */     switch (in.getCommand()) {
	/*     */     case 'ꀀ':
	/* 261 */       this.packetProcessHelper.processLoginSuccess(in);//command=MSG_DEV_REG
	/* 262 */       break;
	/*     */     case 'ꀂ':
	/* 264 */       this.packetProcessHelper.procesKeepAliveSuccess(in);//commad=MSG_DEV_HEART_BEAT
	/* 265 */       break;
	/*     */     case '뀀':
	/* 267 */       this.packetProcessHelper.processQuerySuccess(in);//command=MSG_CLI_QUERY
	/* 268 */       break;
	/*     */     case '\000':
	/* 270 */       this.packetProcessHelper.processUnknown(in);//command=Unknown
					break;
				  default:
					System.out.println("switch语句 default输出");
					break;
/*     */     }
/*     */ 
/* 274 */     if ((in.getCommand() >= 49152) && (in.getCommand() <= 53247)) {//command=MSG_C2D_DATA
				System.out.println("Command between 49152 and 53247");
/* 275 */       this.packetProcessHelper.processCDataSuccess(in);
/*     */     }
/* 277 */     if ((in.getCommand() >= 53248) && (in.getCommand() <= 57343)) {//command=MSG_D2C_DATA
//	            System.out.println("Command between 53248 and 57343");
/* 278 */       this.packetProcessHelper.processDDataSuccess(in);
/*     */     }
/* 280 */     if ((in.getCommand() >= 57344) && (in.getCommand() <= 61439)) {//command=MSG_D2S_DATA
//				System.out.println("Command between 57344 and 61439");
/* 281 */       this.packetProcessHelper.processReportSuccess(in);
/*     */     }
/* 283 */     if ((in.getCommand() >= 61440) && (in.getCommand() <= 65535)) {//command=MSG_D2S_ALARM
				System.out.println("Command between 61440 and 65535");
				this.packetProcessHelper.processAlarmSuccess(in);
			  }
/*     */   }
/*     */ 
/*     */   public PacketHistory getHistory()
/*     */   {
/* 292 */     return this.packetHelper.getHistory();
/*     */   }
/*     */ 
/*     */   public PacketHelper getPacketHelper() {
/* 296 */     return this.packetHelper;
/*     */   }
/*     */ 
/*     */   public MessageHelper getMessageHelper() {
/* 300 */     return this.messageHelper;
/*     */   }
/*     */ 
/*     */   public PacketProcessHelper getPacketProcessHelper() {
/* 304 */     return this.packetProcessHelper;
/*     */   }
/*     */ 
/*     */   public ProcessorRouter getRouter() {
/* 308 */     return this.router;
/*     */   }
/*     */ 
/*     */   public ResendTrigger<Object> getResendTrigger() {
/* 312 */     return this.resendTrigger;
/*     */   }
/*     */ 
/*     */   public KeepAliveTrigger<Object> getKeepAliveTrigger() {
/* 316 */     return this.keepAliveTrigger;
/*     */   }
/*     */ 
/*     */   public boolean isConnctioned() {
/* 320 */     return this.connctioned;
/*     */   }
/*     */ 
/*     */   public void setConnctioned() {
/* 324 */     this.connctioned = true;
/*     */   }
/*     */ 
/*     */   public void setDisConnctioned()
/*     */   {
/* 337 */     this.resendTrigger.clear();
/* 338 */     this.connctioned = false;
/*     */   }
/*     */ 
/*     */   public ChannelGroup getAllChannels() {
/* 342 */     return this.allChannels;
/*     */   }
/*     */ 
/*     */   public void addSocketAddress(String dockUser, String[] ipPort) {
/* 346 */     if (this.channel == null) return;
/*     */ 
/* 348 */     this.allKeysChannelId.put(dockUser, ipPort);
/*     */   }
/*     */ 
/*     */   public String[] getSocketAddress(String dockUser) {
/* 352 */     return (String[])this.allKeysChannelId.get(dockUser);
/*     */   }
/*     */ 
/*     */   public void removeSocketAddress(String dockUser) {
/* 356 */     this.allKeysChannelId.remove(dockUser);
/*     */   }
/*     */ 
/*     */   public UserManager getUserManager() {
/* 360 */     return this.userManager;
/*     */   }
/*     */ 
/*     */   public void setPacketProcessHelper(PacketProcessHelper packetProcessHelper) {
/* 364 */     this.packetProcessHelper = packetProcessHelper;
/*     */   }
/*     */ 
/*     */   public Channel getChannel() {
/* 368 */     return this.channel;
/*     */   }
/*     */ 
/*     */   public void setChannel(Channel channel) {
/* 372 */     this.channel = channel;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.support.PacketProcessor
 * JD-Core Version:    0.6.2
 */