/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import org.apache.tools.zip.ZipEntry;
/*     */ import org.apache.tools.zip.ZipFile;
/*     */ import org.apache.tools.zip.ZipOutputStream;
/*     */ 
/*     */ public class ZipUtil
/*     */ {
/*     */   public static boolean doZip(String filesDirPath, String zipFilePath)
/*     */   {
/*  20 */     return doZip(new File(filesDirPath), zipFilePath);
/*     */   }
/*     */ 
/*     */   private static boolean doZip(File inputFile, String zipFileName) {
/*  24 */     ZipOutputStream out = null;
/*     */     try {
/*  26 */       out = new ZipOutputStream(new FileOutputStream(zipFileName));
/*  27 */       boolean result = doZip(out, inputFile, "");
/*     */ 
/*  29 */       return result;
/*     */     }
/*     */     catch (FileNotFoundException ex) {
/*  32 */       return false;
/*     */     }
/*     */     catch (IOException ex) {
/*  35 */       return false;
/*     */     } finally {
/*     */       try {
/*  38 */         if (out != null)
/*  39 */           out.close();
/*     */       }
/*     */       catch (IOException ex) {
/*  42 */         return false;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean doZip(ZipOutputStream out, File f, String base) {
/*     */     try {
/*  49 */       if (f.isDirectory()) {
/*  50 */         File[] fl = f.listFiles();
/*  51 */         out.putNextEntry(new ZipEntry(base + "/"));
/*  52 */         base = base + "/";
/*  53 */         for (int i = 0; i < fl.length; i++)
/*  54 */           doZip(out, fl[i], base + fl[i].getName());
/*     */       }
/*     */       else {
/*  57 */         out.putNextEntry(new ZipEntry(base));
/*  58 */         FileInputStream in = new FileInputStream(f);
/*     */         int b;
/*  60 */         while ((b = in.read()) != -1)
/*     */         {
///*     */           int b;
/*  61 */           out.write(b);
/*     */         }
/*  63 */         in.close();
/*     */       }
/*  65 */       return true;
/*     */     } catch (IOException ex) {
/*     */     }
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean unZip(String srcFile, String dest, boolean deleteFile)
/*     */   {
/*     */     try {
/*  74 */       File file = new File(srcFile);
/*  75 */       if (!file.exists())
/*     */       {
/*  77 */         return false;
/*     */       }
/*  79 */       ZipFile zipFile = new ZipFile(file);
/*  80 */       Enumeration e = zipFile.getEntries();
/*  81 */       while (e.hasMoreElements()) {
/*  82 */         ZipEntry zipEntry = (ZipEntry)e.nextElement();
/*  83 */         if (zipEntry.isDirectory()) {
/*  84 */           String name = zipEntry.getName();
/*  85 */           name = name.substring(0, name.length() - 1);
/*  86 */           File f = new File(dest + name);
/*  87 */           f.mkdirs();
/*     */         } else {
/*  89 */           File f = new File(dest + zipEntry.getName());
/*  90 */           f.getParentFile().mkdirs();
/*  91 */           f.createNewFile();
/*  92 */           InputStream is = zipFile.getInputStream(zipEntry);
/*  93 */           FileOutputStream fos = new FileOutputStream(f);
/*  94 */           int length = 0;
/*  95 */           byte[] b = new byte[1024];
/*  96 */           while ((length = is.read(b, 0, 1024)) != -1) {
/*  97 */             fos.write(b, 0, length);
/*     */           }
/*  99 */           is.close();
/* 100 */           fos.close();
/*     */         }
/*     */       }
/*     */ 
/* 104 */       if (zipFile != null) {
/* 105 */         zipFile.close();
/*     */       }
/*     */ 
/* 108 */       if (deleteFile)
/*     */       {
/* 110 */         file.delete();
/*     */       }
/*     */ 
/* 113 */       return true; } catch (IOException ex) {
/*     */     }
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   public static void removeZip(String filesDirPath)
/*     */   {
/* 121 */     Calendar cdweek = Calendar.getInstance();
/* 122 */     cdweek.add(5, -7);
/* 123 */     Date d = cdweek.getTime();
/*     */ 
/* 125 */     File fileBag = new File(filesDirPath);
/* 126 */     String[] filesName = fileBag.list();
/* 127 */     for (int i = 0; i < filesName.length; i++) {
/* 128 */       String fileName = filesName[i];
/* 129 */       if (fileName.indexOf(".") != -1) {
/* 130 */         String fileType = fileName.substring(fileName.indexOf(".") + 1, 
/* 131 */           fileName.length());
/* 132 */         if ("zip".equals(fileType))
/*     */         {
/* 134 */           File file = new File(filesDirPath + "/" + 
/* 135 */             filesName[i]);
/* 136 */           Long time = Long.valueOf(file.lastModified());
/* 137 */           Calendar cd = Calendar.getInstance();
/* 138 */           cd.setTimeInMillis(time.longValue());
/*     */ 
/* 140 */           Date fileDate = cd.getTime();
/*     */ 
/* 142 */           boolean flag = fileDate.before(d);
/* 143 */           if ((flag) && 
/* 144 */             (file.exists()) && (file.isFile()))
/* 145 */             file.delete();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 155 */     boolean resultOfZip = doZip("E:/ZipTest/MyFiles", 
/* 156 */       "E:/ZipTest/test.youarestupid");
/*     */ 
/* 159 */     boolean resultOfUnZip = unZip("E:/ZipTest/test.youarestupid", 
/* 160 */       "E:/ZipTest/youarestupid/", false);
/*     */ 
/* 162 */     System.out.println("压缩结果：" + resultOfZip + "\n解压缩结果：" + resultOfUnZip);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.ZipUtil
 * JD-Core Version:    0.6.2
 */