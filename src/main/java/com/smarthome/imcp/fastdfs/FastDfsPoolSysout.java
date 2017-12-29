/*    */ package com.smarthome.imcp.fastdfs;
/*    */ 
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ public class FastDfsPoolSysout
/*    */ {
/*  8 */   public static Log log = LogFactory.getLog(FastDfsPoolSysout.class);
/*    */ 
/*    */   public static void info(Object o) {
/* 11 */     log.info(o.toString());
/*    */   }
/*    */ 
/*    */   public static void warn(Object o) {
/* 15 */     log.warn(o.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.fastdfs.FastDfsPoolSysout
 * JD-Core Version:    0.6.2
 */