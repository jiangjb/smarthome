/*   */ package com.smarthome.dock.server;
/*   */ 
/*   */ import java.io.PrintStream;
/*   */ 
/*   */ public class Testysa
/*   */ {
/*   */   public static void main(String[] args)
/*   */   {
/* 5 */     String das = "ZIGBEE_SCAN-DEVEICE-4,32836,0;1,33021,2;2,56194,0;1,61978,3;1,61967,3;8192,65535,0";
/* 6 */     String[] split = das.split("-");
/* 7 */     for (int i = 0; i < split.length; i++)
/* 8 */       System.err.println("截取 " + split[i]);
/*   */   }
/*   */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.Testysa
 * JD-Core Version:    0.6.2
 */