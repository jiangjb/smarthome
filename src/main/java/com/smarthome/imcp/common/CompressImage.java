/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public final class CompressImage
/*     */ {
/*     */   public static boolean compressImage(File inFile, File outFile, int outputWidth, int outputHeight, boolean proportion)
/*     */   {
/*     */     try
/*     */     {
/*  23 */       if ((!inFile.exists()) || (!inFile.isFile())) {
/*  24 */         return false;
/*     */       }
/*  26 */       String fileName = inFile.getName();
/*  27 */       String fileType = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
/*     */ 
/*  29 */       if (StringUtils.isEmpty(fileType)) {
/*  30 */         return false;
/*     */       }
/*     */ 
/*  33 */       Image image = ImageIO.read(inFile);
/*     */ 
/*  35 */       if (outputHeight == -1) {
/*  36 */         outputHeight = image.getHeight(null);
/*     */       }
/*     */ 
/*  39 */       if (outputWidth > image.getWidth(null)) {
/*  40 */         outputWidth = image.getWidth(null);
/*     */       }
/*     */ 
/*  43 */       if (outputHeight > image.getHeight(null)) {
/*  44 */         outputHeight = image.getHeight(null);
/*     */       }
/*     */ 
/*  48 */       if (image.getWidth(null) == -1) {
/*  49 */         System.out.println(" can't read,retry!<BR>");
/*  50 */         return false;
/*     */       }
/*     */       int newHeight;
/*     */       int newWidth;
///*     */       int newHeight;
/*  54 */       if (proportion)
/*     */       {
/*  56 */         double rate1 = image.getWidth(null) / outputWidth + 0.1D;
/*  57 */         double rate2 = image.getHeight(null) / outputHeight + 0.1D;
/*     */ 
/*  59 */         double rate = rate1 > rate2 ? rate1 : rate2;
///*  60 */         int newWidth = (int)(image.getWidth(null) / rate);
				  newWidth = (int)(image.getWidth(null) / rate);
/*  61 */         newHeight = (int)(image.getHeight(null) / rate);
/*     */       } else {
/*  63 */         newWidth = outputWidth;
/*  64 */         newHeight = outputHeight;
/*     */       }
/*  66 */       BufferedImage tag = new BufferedImage(newWidth, newHeight, 1);
/*     */ 
/*  71 */       tag.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight, 4), 0, 0, null);
/*  72 */       ImageIO.write(tag, fileType, outFile);
/*     */     }
/*     */     catch (IOException ex) {
/*  75 */       ex.printStackTrace();
/*     */     }
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isImage(File file)
/*     */   {
/*  87 */     if ((!file.exists()) || (!file.isFile())) {
/*  88 */       return false;
/*     */     }
/*  90 */     String fileName = file.getName();
/*  91 */     String fileType = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
/*  92 */     fileType = fileType.toLowerCase();
/*     */ 
/*  94 */     if (StringUtils.isEmpty(fileType)) {
/*  95 */       return false;
/*     */     }
/*  97 */     if (("jpg".equals(fileType)) || ("bmp".equals(fileType)) || ("gif".equals(fileType)) || ("png".equals(fileType))) {
/*  98 */       return true;
/*     */     }
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.CompressImage
 * JD-Core Version:    0.6.2
 */