/*    */ package com.smarthome.dock.server.packets.out;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.OutPacket;
/*    */ import java.io.PrintStream;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class LoginReplyPacket extends OutPacket
/*    */ {
/*    */   public LoginReplyPacket(String devId, int ret)
/*    */   {
/* 11 */     super((char) 40961, devId);
/* 12 */     System.err.println("服务器回复设备注册序列号 " + devId);
/* 13 */     this.ext[0] = ((byte)ret);
/*    */   }
/*    */ 
/*    */   protected void putBody(ChannelBuffer buf)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.out.LoginReplyPacket
 * JD-Core Version:    0.6.2
 */