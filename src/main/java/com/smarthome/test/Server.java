/*    */ package com.smarthome.test;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.net.ServerSocket;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class Server extends Thread
/*    */ {
/*  8 */   private ServerSocket ss = null;
/*    */ 
/*    */   public Server() {
/*    */     try { this.ss = new ServerSocket(7777);
/*    */     } catch (IOException e) {
/* 13 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public void run() {
/*    */     while (true) try {
/* 19 */         System.out.println("-------------服务启动------------");
/* 20 */         Socket socket = this.ss.accept();
/* 21 */         ServerThread st = new ServerThread(socket);
/* 22 */         st.start();
/*    */       } catch (IOException e) {
/* 24 */         e.printStackTrace();
/*    */       } 
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) throws Exception {
/* 29 */     new Server().start();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.test.Server
 * JD-Core Version:    0.6.2
 */