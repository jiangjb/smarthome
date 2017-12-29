/*    */ package com.smarthome.dock.server;
/*    */ 
/*    */ import com.smarthome.dock.server.handler.PacketDecoder;
/*    */ import com.smarthome.dock.server.handler.PacketEncoder;
/*    */ import com.smarthome.dock.server.support.PacketProcessor;
/*    */ import org.jboss.netty.channel.ChannelPipeline;
/*    */ import org.jboss.netty.channel.ChannelPipelineFactory;
/*    */ import org.jboss.netty.channel.Channels;
/*    */ 
/*    */ public class DockServerPipelineFactory
/*    */   implements ChannelPipelineFactory
/*    */ {
/*    */   private PacketProcessor packetProcessor;
/*    */ 
/*    */   public DockServerPipelineFactory(PacketProcessor packetProcessor)
/*    */   {
/* 18 */     this.packetProcessor = packetProcessor;
/*    */   }
/*    */ 
/*    */   public ChannelPipeline getPipeline()
/*    */     throws Exception
/*    */   {
/* 24 */     ChannelPipeline pipeline = Channels.pipeline();
/*    */ 
/* 37 */     pipeline.addLast("decoder", new PacketDecoder(this.packetProcessor));
/* 38 */     pipeline.addLast("encoder", new PacketEncoder());
/*    */ 
/* 40 */     pipeline.addLast("handler", new DockServerHandler(this.packetProcessor));
/*    */ 
/* 42 */     return pipeline;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.DockServerPipelineFactory
 * JD-Core Version:    0.6.2
 */