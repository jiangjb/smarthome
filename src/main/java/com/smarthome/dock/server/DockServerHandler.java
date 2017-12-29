/*     */ package com.smarthome.dock.server;
/*     */ 
/*     */ import com.smarthome.dock.server.packets.InPacket;
/*     */ import com.smarthome.dock.server.support.PacketProcessor;
/*     */ import java.io.IOException;
/*     */ import java.net.InetAddress;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.List;
/*     */ import org.jboss.netty.channel.Channel;
/*     */ import org.jboss.netty.channel.ChannelEvent;
/*     */ import org.jboss.netty.channel.ChannelHandlerContext;
/*     */ import org.jboss.netty.channel.ChannelStateEvent;
/*     */ import org.jboss.netty.channel.ChildChannelStateEvent;
/*     */ import org.jboss.netty.channel.ExceptionEvent;
/*     */ import org.jboss.netty.channel.MessageEvent;
/*     */ import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ public class DockServerHandler extends SimpleChannelUpstreamHandler
/*     */ {
/*  22 */   private static Logger logger = LoggerFactory.getLogger(DockServerHandler.class);
/*     */   private PacketProcessor packetProcessor;
/*     */ 
/*     */   public DockServerHandler(PacketProcessor packetProcessor)
/*     */   {
/*  27 */     this.packetProcessor = packetProcessor;
/*     */   }
/*     */ 
/*     */   public void handleUpstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception
/*     */   {
/*  32 */     if ((e instanceof ChannelStateEvent)) {
/*  33 */       logger.debug(e.toString());
/*     */     }
/*  35 */     super.handleUpstream(ctx, e);
/*     */   }
/*     */ 
/*     */   public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
/*     */   {
/*  40 */     ctx.sendUpstream(e);
/*     */   }
/*     */ 
/*     */   public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
/*     */   {
/*  45 */     ctx.sendUpstream(e);
/*     */   }
/*     */ 
/*     */   public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception
/*     */   {
/*  50 */     Channel channel = e.getChannel();
/*     */ 
/*  54 */     super.channelConnected(ctx, e);
/*     */   }
/*     */ 
/*     */   public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
/*     */     throws Exception
/*     */   {
/*  67 */     super.channelClosed(ctx, e);
/*     */   }
/*     */ 
/*     */   public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e)
/*     */     throws Exception
/*     */   {
/*  75 */     super.channelDisconnected(ctx, e);
/*     */   }
/*     */ 
/*     */   public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception
/*     */   {
/*  80 */     List<InPacket> list = (List)e.getMessage();
/*  81 */     if (list != null)
/*  82 */       for (InPacket packet :list)
/*  83 */         if (packet != null)
/*     */         {
/*  87 */           InetSocketAddress isa = (InetSocketAddress)e.getRemoteAddress();
/*     */ 
/*  90 */           packet.setHostName(isa.getAddress().getHostAddress());
/*  91 */           packet.setPort(isa.getPort());
/*     */ 
/*  95 */           this.packetProcessor.addIncomingPacket(packet);
/*     */         }
/*     */   }
/*     */ 
/*     */   public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
/*     */   {
/* 127 */     if ((e.getCause() != null) && ((e.getCause() instanceof IOException)))
/* 128 */       e.getChannel().close();
/*     */   }
/*     */ 
/*     */   public void childChannelOpen(ChannelHandlerContext ctx, ChildChannelStateEvent e)
/*     */     throws Exception
/*     */   {
/* 134 */     ctx.sendUpstream(e);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.DockServerHandler
 * JD-Core Version:    0.6.2
 */