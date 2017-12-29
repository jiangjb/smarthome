/*    */ package com.smarthome.dock.server.bean;
/*    */ 
/*    */ import java.net.SocketAddress;
/*    */ import org.jboss.netty.channel.Channel;
/*    */ 
/*    */ public class SocketChannel
/*    */ {
/*    */   private Channel channel;
/*    */   private SocketAddress socketAddress;
/*    */ 
/*    */   public SocketChannel(Channel channel, SocketAddress socketAddress)
/*    */   {
/* 12 */     this.channel = channel;
/* 13 */     this.socketAddress = socketAddress;
/*    */   }
/*    */ 
/*    */   public Channel getChannel()
/*    */   {
/* 18 */     return this.channel;
/*    */   }
/*    */ 
/*    */   public void setChannel(Channel channel) {
/* 22 */     this.channel = channel;
/*    */   }
/*    */ 
/*    */   public SocketAddress getSocketAddress() {
/* 26 */     return this.socketAddress;
/*    */   }
/*    */ 
/*    */   public void setSocketAddress(SocketAddress socketAddress) {
/* 30 */     this.socketAddress = socketAddress;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.bean.SocketChannel
 * JD-Core Version:    0.6.2
 */