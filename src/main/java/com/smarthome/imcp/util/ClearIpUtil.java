/*   */ package com.smarthome.imcp.util;
/*   */ 
/*   */ import com.smarthome.dock.server.util.StaticUtil;
/*   */ import java.util.Map;
/*   */ 
/*   */ public class ClearIpUtil
/*   */ {
/*   */   public static String ip;
/*   */ 
/*   */   public static void clearMap()
/*   */   {
/* 8 */     StaticUtil.IP.remove(ip);
/*   */   }
/*   */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.ClearIpUtil
 * JD-Core Version:    0.6.2
 */