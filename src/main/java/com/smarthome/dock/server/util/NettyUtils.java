/*    */ package com.smarthome.dock.server.util;
/*    */ 
/*    */ import java.net.InetSocketAddress;
/*    */ import java.net.SocketAddress;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class NettyUtils
/*    */ {
/* 10 */   private static Logger logger = LoggerFactory.getLogger(NettyUtils.class);
/*    */ 
/*    */   public static String getIp(SocketAddress socketAddress) {
/* 13 */     String ip = "";
/*    */ 
/* 15 */     if (socketAddress != null) {
/* 16 */       InetSocketAddress isa = (InetSocketAddress)socketAddress;
/*    */ 
/* 18 */       logger.debug("isa.getHostName() => " + isa.getHostName());
/*    */ 
/* 20 */       ip = isa.getHostName();
/*    */     }
/*    */ 
/* 23 */     return ip;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.util.NettyUtils
 * JD-Core Version:    0.6.2
 */