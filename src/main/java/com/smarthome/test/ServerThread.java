/*    */ package com.smarthome.test;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.io.PrintWriter;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class ServerThread extends Thread
/*    */ {
/*    */   private Socket socket;
/*    */ 
/*    */   public ServerThread(Socket socket)
/*    */   {
/* 14 */     this.socket = socket;
/*    */   }
/*    */   public void run() {
/*    */     try {
/* 18 */       InputStream is = this.socket.getInputStream();
/* 19 */       OutputStream os = this.socket.getOutputStream();
/* 20 */       BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 21 */       PrintWriter pw = new PrintWriter(os);
/* 22 */       String temp = br.readLine();
/* 23 */       System.out.println("服务器端消息:" + temp);
/* 24 */       sleep(3000L);
/* 25 */       pw.println("接收到了");
/* 26 */       pw.close();
/* 27 */       br.close();
/* 28 */       this.socket.close();
/*    */     } catch (Exception rr) {
/* 30 */       rr.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 35 */     new Server().start();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.test.ServerThread
 * JD-Core Version:    0.6.2
 */