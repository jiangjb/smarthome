/*     */ package com.smarthome.dock.server.common;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import org.apache.tools.zip.ZipEntry;
/*     */ import org.apache.tools.zip.ZipFile;
/*     */ import org.apache.tools.zip.ZipOutputStream;
/*     */ 
/*     */ public class ZipUtil
/*     */ {
/*     */   public static boolean doZip(String filesDirPath, String zipFilePath)
/*     */   {
/*  18 */     return doZip(new File(filesDirPath), zipFilePath);
/*     */   }
/*     */ 
/*     */   private static boolean doZip(File inputFile, String zipFileName) {
/*  22 */     ZipOutputStream out = null;
/*     */     try {
/*  24 */       out = new ZipOutputStream(new FileOutputStream(zipFileName));
/*  25 */       boolean result = doZip(out, inputFile, "");
/*     */ 
/*  27 */       return result;
/*     */     }
/*     */     catch (FileNotFoundException ex) {
/*  30 */       return false;
/*     */     }
/*     */     catch (IOException ex) {
/*  33 */       return false;
/*     */     } finally {
/*     */       try {
/*  36 */         out.close();
/*     */       }
/*     */       catch (IOException ex) {
/*  39 */         return false;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean doZip(ZipOutputStream out, File f, String base) {
/*     */     try {
/*  46 */       if (f.isDirectory()) {
/*  47 */         File[] fl = f.listFiles();
/*  48 */         out.putNextEntry(new ZipEntry(base + "/"));
/*  49 */         base = base + "/";
/*  50 */         for (int i = 0; i < fl.length; i++)
/*  51 */           doZip(out, fl[i], base + fl[i].getName());
/*     */       }
/*     */       else {
/*  54 */         out.putNextEntry(new ZipEntry(base));
/*  55 */         FileInputStream in = new FileInputStream(f);
/*     */         int b;
/*  57 */         while ((b = in.read()) != -1)
/*     */         {
///*     */           int b;
/*  58 */           out.write(b);
/*     */         }
/*  60 */         in.close();
/*     */       }
/*  62 */       return true;
/*     */     } catch (IOException ex) {
/*     */     }
/*  65 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean unZip(String srcFile, String dest, boolean deleteFile)
/*     */   {
/*     */     try {
/*  71 */       File file = new File(srcFile);
/*  72 */       if (!file.exists())
/*     */       {
/*  74 */         return false;
/*     */       }
/*  76 */       ZipFile zipFile = new ZipFile(file);
/*  77 */       Enumeration e = zipFile.getEntries();
/*  78 */       while (e.hasMoreElements()) {
/*  79 */         ZipEntry zipEntry = (ZipEntry)e.nextElement();
/*  80 */         if (zipEntry.isDirectory()) {
/*  81 */           String name = zipEntry.getName();
/*  82 */           name = name.substring(0, name.length() - 1);
/*  83 */           File f = new File(dest + name);
/*  84 */           f.mkdirs();
/*     */         } else {
/*  86 */           File f = new File(dest + zipEntry.getName());
/*  87 */           f.getParentFile().mkdirs();
/*  88 */           f.createNewFile();
/*  89 */           InputStream is = zipFile.getInputStream(zipEntry);
/*  90 */           FileOutputStream fos = new FileOutputStream(f);
/*  91 */           int length = 0;
/*  92 */           byte[] b = new byte[1024];
/*  93 */           while ((length = is.read(b, 0, 1024)) != -1) {
/*  94 */             fos.write(b, 0, length);
/*     */           }
/*  96 */           is.close();
/*  97 */           fos.close();
/*     */         }
/*     */       }
/*     */ 
/* 101 */       if (zipFile != null) {
/* 102 */         zipFile.close();
/*     */       }
/*     */ 
/* 105 */       if (deleteFile)
/*     */       {
/* 107 */         file.delete();
/*     */       }
/*     */ 
/* 110 */       return true; } catch (IOException ex) {
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws Exception
/*     */   {
/* 118 */     boolean resultOfZip = doZip("E:/ZipTest/MyFiles", 
/* 119 */       "E:/ZipTest/test.youarestupid");
/*     */ 
/* 122 */     boolean resultOfUnZip = unZip("E:/ZipTest/test.youarestupid", 
/* 123 */       "E:/ZipTest/youarestupid/", false);
/*     */ 
/* 125 */     System.out.println("压缩结果：" + resultOfZip + "\n解压缩结果：" + resultOfUnZip);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.ZipUtil
 * JD-Core Version:    0.6.2
 */