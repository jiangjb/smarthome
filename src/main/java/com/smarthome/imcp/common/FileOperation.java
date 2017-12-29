/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ 
/*     */ public class FileOperation
/*     */ {
/*     */   public static boolean createFile(File fileName)
/*     */     throws Exception
/*     */   {
/*  24 */     boolean flag = false;
/*     */     try {
/*  26 */       if (!fileName.exists()) {
/*  27 */         fileName.createNewFile();
/*  28 */         flag = true;
/*     */       }
/*     */     } catch (Exception e) {
/*  31 */       e.printStackTrace();
/*     */     }
/*  33 */     return flag;
/*     */   }
/*     */ 
/*     */   public static boolean deleteFile(String sPath)
/*     */   {
/*  44 */     boolean flag = false;
/*  45 */     File file = new File(sPath);
/*     */ 
/*  47 */     if ((file.isFile()) && (file.exists())) {
/*  48 */       file.delete();
/*  49 */       flag = true;
/*     */     }
/*  51 */     return flag;
/*     */   }
/*     */ 
/*     */   public static String readHtml(String filePath)
/*     */   {
/*  60 */     BufferedReader br = null;
/*  61 */     StringBuffer sb = new StringBuffer();
/*     */     try {
/*  63 */       br = new BufferedReader(new InputStreamReader(new FileInputStream(
/*  64 */         filePath), "UTF-8"));
/*  65 */       String temp = null;
/*  66 */       while ((temp = br.readLine()) != null) {
/*  67 */         sb.append(temp);
/*  68 */         sb.append("\n");
/*     */       }
/*     */     } catch (FileNotFoundException e) {
/*  71 */       e.printStackTrace();
/*     */     } catch (IOException e) {
/*  73 */       e.printStackTrace();
/*     */     }
/*  75 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String readTxtFile(File fileName)
/*     */     throws Exception
/*     */   {
/*  85 */     String result = null;
/*  86 */     FileReader fileReader = null;
/*  87 */     BufferedReader bufferedReader = null;
/*     */     try {
/*  89 */       fileReader = new FileReader(fileName);
/*  90 */       bufferedReader = new BufferedReader(fileReader);
/*     */       try {
/*  92 */         String read = null;
/*  93 */         while ((read = bufferedReader.readLine()) != null)
/*  94 */           result = result + read + "\r\n";
/*     */       }
/*     */       catch (Exception e) {
/*  97 */         e.printStackTrace();
/*     */       }
/*     */     } catch (Exception e) {
/* 100 */       e.printStackTrace();
/*     */     } finally {
/* 102 */       if (bufferedReader != null) {
/* 103 */         bufferedReader.close();
/*     */       }
/* 105 */       if (fileReader != null) {
/* 106 */         fileReader.close();
/*     */       }
/*     */     }
/* 109 */     System.out.println("读取出来的文件内容是：\r\n" + result);
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean writeTxtFile(String content, File fileName) throws Exception
/*     */   {
/* 115 */     RandomAccessFile mm = null;
/* 116 */     boolean flag = false;
/* 117 */     FileOutputStream o = null;
/*     */     try {
/* 119 */       o = new FileOutputStream(fileName);
/* 120 */       o.write(content.getBytes("UTF-8"));
/* 121 */       o.close();
/*     */ 
/* 124 */       flag = true;
/*     */     }
/*     */     catch (Exception e) {
/* 127 */       e.printStackTrace();
/*     */     } finally {
/* 129 */       if (mm != null) {
/* 130 */         mm.close();
/*     */       }
/*     */     }
/* 133 */     return flag;
/*     */   }
/*     */ 
/*     */   public static void contentToTxt(String filePath, String content) {
/* 137 */     String str = new String();
/* 138 */     String s1 = new String();
/*     */     try {
/* 140 */       File f = new File(filePath);
/* 141 */       if (f.exists()) {
/* 142 */         System.out.print("文件存在");
/*     */       } else {
/* 144 */         System.out.print("文件不存在");
/* 145 */         f.createNewFile();
/*     */       }
/* 147 */       BufferedReader input = new BufferedReader(new FileReader(f));
/*     */ 
/* 149 */       while ((str = input.readLine()) != null) {
/* 150 */         s1 = s1 + str + "\n";
/*     */       }
/* 152 */       System.out.println(s1);
/* 153 */       input.close();
/* 154 */       s1 = s1 + content;
/*     */ 
/* 156 */       BufferedWriter output = new BufferedWriter(new FileWriter(f));
/* 157 */       output.write(s1);
/* 158 */       output.close();
/*     */     } catch (Exception e) {
/* 160 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean delAllFile(String path)
/*     */   {
/* 168 */     boolean flag = false;
/* 169 */     File file = new File(path);
/* 170 */     if (!file.exists()) {
/* 171 */       return flag;
/*     */     }
/* 173 */     if (!file.isDirectory()) {
/* 174 */       return flag;
/*     */     }
/* 176 */     String[] tempList = file.list();
/* 177 */     File temp = null;
/* 178 */     for (int i = 0; i < tempList.length; i++) {
/* 179 */       if (path.endsWith("/"))
/* 180 */         temp = new File(path + tempList[i]);
/*     */       else {
/* 182 */         temp = new File(path + "/" + tempList[i]);
/*     */       }
/* 184 */       if (temp.isFile()) {
/* 185 */         temp.delete();
/*     */       }
/* 187 */       if (temp.isDirectory()) {
/* 188 */         delAllFile(path + "/" + tempList[i]);
/* 189 */         delFolder(path + "/" + tempList[i]);
/* 190 */         flag = true;
/*     */       }
/*     */     }
/* 193 */     return flag;
/*     */   }
/*     */ 
/*     */   public static void delFolder(String folderPath) {
/*     */     try {
/* 198 */       delAllFile(folderPath);
/* 199 */       String filePath = folderPath;
/* 200 */       filePath = filePath.toString();
/* 201 */       File myFilePath = new File(filePath);
/* 202 */       myFilePath.delete();
/*     */     } catch (Exception e) {
/* 204 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.FileOperation
 * JD-Core Version:    0.6.2
 */