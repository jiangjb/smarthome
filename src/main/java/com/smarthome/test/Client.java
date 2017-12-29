/*    */ package com.smarthome.test;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.OutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.Socket;
/*    */ 
/*    */ public class Client
/*    */ {
/*    */   public static void main(String[] args)
/*    */     throws IOException
/*    */   {
/* 15 */     getTest();
/*    */   }
/*    */ 
/*    */   public static void getTest() throws IOException {
/* 19 */     Socket so = new Socket("127.0.0.1", 8888);
/* 20 */     OutputStream os = so.getOutputStream();
/* 21 */     PrintStream ps = new PrintStream(os);
/*    */ 
/* 24 */     InputStream is = so.getInputStream();
/* 25 */     BufferedReader br = new BufferedReader(new InputStreamReader(is));
/* 26 */     ps.println("测试发送数据");
/* 27 */     System.out.println(br.readLine());
/*    */ 
/* 29 */     ps.close();
/* 30 */     so.close();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.test.Client
 * JD-Core Version:    0.6.2
 */