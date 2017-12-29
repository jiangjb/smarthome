/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class ImageUtils
/*     */ {
/*  16 */   private static Font FONT = new Font("微软雅黑", 1, 24);
/*  17 */   private static final Color FONT_COLOR = new Color(0, 0, 0, 255);
/*     */ 
/*     */   public static boolean drawString(File inFile, File descFile, String str) {
/*  20 */     return drawString(inFile, descFile, str, null, null);
/*     */   }
/*     */ 
/*     */   public static boolean drawString(File inFile, File descFile, String str, Font font, Color color) {
/*     */     try {
/*  25 */       if ((str == null) || ("".equals(str))) {
/*  26 */         return false;
/*     */       }
/*     */ 
/*  29 */       if ((!inFile.exists()) || (!inFile.isFile())) {
/*  30 */         return false;
/*     */       }
/*  32 */       if (descFile.exists()) {
/*  33 */         return false;
/*     */       }
/*  35 */       String fileName = inFile.getName();
/*  36 */       String fileType = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
/*     */ 
/*  38 */       if (StringUtils.isEmpty(fileType)) {
/*  39 */         return false;
/*     */       }
/*     */ 
/*  42 */       if (font == null) font = FONT;
/*     */ 
/*  44 */       if (color == null) color = FONT_COLOR;
/*     */ 
/*  46 */       Image image = ImageIO.read(inFile);
/*     */ 
/*  48 */       int imgWidth = image.getWidth(null);
/*  49 */       int imgHeight = image.getHeight(null);
/*     */ 
/*  51 */       BufferedImage tag = new BufferedImage(imgWidth, imgHeight, 1);
/*  52 */       Graphics g = tag.getGraphics();
/*  53 */       g.drawImage(image, 0, 0, null);
/*  54 */       g.setFont(font);
/*  55 */       g.setColor(color);
/*     */ 
/*  57 */       int len = str.length();
/*     */ 
/*  59 */       int fontSize = FONT.getSize();
/*     */ 
/*  61 */       if (Float.valueOf(len).floatValue() * fontSize > Float.valueOf(imgWidth).floatValue()) {
/*  62 */         String str1 = str.substring(0, len / 2);
/*  63 */         String str2 = str.substring(len / 2);
/*     */ 
/*  65 */         Float x = Float.valueOf(Float.valueOf(imgWidth).floatValue() / 2.0F - Float.valueOf(str1.length()).floatValue() / 2.0F * fontSize);
/*  66 */         int y = imgHeight / 2 / 2 + fontSize / 2;
/*  67 */         g.drawString(str1, x.intValue(), y);
/*     */ 
/*  69 */         x = Float.valueOf(Float.valueOf(imgWidth).floatValue() / 2.0F - Float.valueOf(str2.length()).floatValue() / 2.0F * fontSize);
/*  70 */         int y2 = imgHeight / 2 + fontSize;
/*  71 */         g.drawString(str2, x.intValue(), y2);
/*     */       } else {
/*  73 */         Float x = Float.valueOf(Float.valueOf(imgWidth).floatValue() / 2.0F - Float.valueOf(len).floatValue() / 2.0F * fontSize);
/*  74 */         int y = imgHeight / 2 + 5;
/*     */ 
/*  76 */         g.drawString(str, x.intValue(), y);
/*     */       }
/*     */ 
/*  79 */       ImageIO.write(tag, fileType, descFile);
/*     */ 
/*  81 */       g.dispose();
/*     */     } catch (IOException ex) {
/*  83 */       ex.printStackTrace();
/*     */     }
/*  85 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean drawImage(File inFile, File sFile, int x, int y, String str, int fX, int fY)
/*     */   {
/*     */     try
/*     */     {
/* 101 */       if ((!inFile.exists()) || (!sFile.isFile())) {
/* 102 */         return false;
/*     */       }
/*     */ 
/* 105 */       String fileName = inFile.getName();
/* 106 */       String fileType = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
/*     */ 
/* 108 */       if (StringUtils.isEmpty(fileType)) {
/* 109 */         return false;
/*     */       }
/*     */ 
/* 112 */       Image image = ImageIO.read(inFile);
/*     */ 
/* 114 */       int imgWidth = image.getWidth(null);
/* 115 */       int imgHeight = image.getHeight(null);
/*     */ 
/* 117 */       BufferedImage tag = new BufferedImage(imgWidth, imgHeight, 1);
/* 118 */       Graphics g = tag.getGraphics();
/* 119 */       g.drawImage(image, 0, 0, null);
/*     */ 
/* 122 */       Image simage = ImageIO.read(sFile);
/*     */ 
/* 124 */       g.drawImage(simage, x, y, null);
/*     */ 
/* 126 */       Font font = new Font("微软雅黑", 1, 32);
/*     */ 
/* 128 */       Color color = FONT_COLOR;
/*     */ 
/* 130 */       g.setFont(font);
/* 131 */       g.setColor(color);
/*     */ 
/* 133 */       g.drawString(str, fX, fY);
/*     */ 
/* 135 */       ImageIO.write(tag, fileType, inFile);
/*     */ 
/* 137 */       g.dispose();
/*     */     } catch (IOException ex) {
/* 139 */       ex.printStackTrace();
/*     */     }
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isImage(File file)
/*     */   {
/* 151 */     if ((!file.exists()) || (!file.isFile())) {
/* 152 */       return false;
/*     */     }
/* 154 */     String fileName = file.getName();
/* 155 */     String fileType = fileName.lastIndexOf(".") > 0 ? fileName.substring(fileName.lastIndexOf(".") + 1) : "";
/* 156 */     fileType = fileType.toLowerCase();
/*     */ 
/* 158 */     if (StringUtils.isEmpty(fileType)) {
/* 159 */       return false;
/*     */     }
/* 161 */     if (("jpg".equals(fileType)) || ("bmp".equals(fileType)) || ("gif".equals(fileType)) || ("png".equals(fileType))) {
/* 162 */       return true;
/*     */     }
/* 164 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.ImageUtils
 * JD-Core Version:    0.6.2
 */