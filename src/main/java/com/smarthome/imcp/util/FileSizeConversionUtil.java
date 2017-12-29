/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.PrintStream;
/*    */ import java.text.DecimalFormat;
/*    */ 
/*    */ public class FileSizeConversionUtil
/*    */ {
/*    */   public static long getFileSize(String filename)
/*    */   {
/*  8 */     File file = new File(filename);
/*  9 */     if ((!file.exists()) || (!file.isFile())) {
/* 10 */       System.out.println("文件不存在");
/* 11 */       return -1L;
/*    */     }
/* 13 */     return file.length();
/*    */   }
/*    */ 
/*    */   public static String sormetFileSize(long fileS)
/*    */   {
/* 18 */     DecimalFormat df = new DecimalFormat("#.00");
/* 19 */     String fileSizeString = "";
/* 20 */     if (fileS < 1024L)
/* 21 */       fileSizeString = df.format(fileS) + "B";
/* 22 */     else if (fileS < 1048576L)
/* 23 */       fileSizeString = df.format(fileS / 1024.0D) + "K";
/* 24 */     else if (fileS < 1073741824L)
/* 25 */       fileSizeString = df.format(fileS / 1048576.0D) + "M";
/*    */     else {
/* 27 */       fileSizeString = df.format(fileS / 1073741824.0D) + "G";
/*    */     }
/* 29 */     return fileSizeString;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.FileSizeConversionUtil
 * JD-Core Version:    0.6.2
 */