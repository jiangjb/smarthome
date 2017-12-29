/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public class Base64Encoder extends FilterOutputStream
/*     */ {
/*   7 */   private static final char[] chars = { 
/*   8 */     'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
/*   9 */     'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
/*  10 */     'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
/*  11 */     'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
/*  12 */     'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
/*  13 */     'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
/*  14 */     '8', '9', '+', '/' };
/*     */   private int charCount;
/*     */   private int carryOver;
/*     */ 
/*     */   public Base64Encoder(OutputStream out)
/*     */   {
/*  27 */     super(out);
/*     */   }
/*     */ 
/*     */   public void write(int b)
/*     */     throws IOException
/*     */   {
/*  43 */     if (b < 0) {
/*  44 */       b += 256;
/*     */     }
/*     */ 
/*  48 */     if (this.charCount % 3 == 0) {
/*  49 */       int lookup = b >> 2;
/*  50 */       this.carryOver = (b & 0x3);
/*  51 */       this.out.write(chars[lookup]);
/*     */     }
/*  55 */     else if (this.charCount % 3 == 1) {
/*  56 */       int lookup = (this.carryOver << 4) + (b >> 4) & 0x3F;
/*  57 */       this.carryOver = (b & 0xF);
/*  58 */       this.out.write(chars[lookup]);
/*     */     }
/*  62 */     else if (this.charCount % 3 == 2) {
/*  63 */       int lookup = (this.carryOver << 2) + (b >> 6) & 0x3F;
/*  64 */       this.out.write(chars[lookup]);
/*  65 */       lookup = b & 0x3F;
/*  66 */       this.out.write(chars[lookup]);
/*  67 */       this.carryOver = 0;
/*     */     }
/*  69 */     this.charCount += 1;
/*     */ 
/*  72 */     if (this.charCount % 57 == 0)
/*  73 */       this.out.write(10);
/*     */   }
/*     */ 
/*     */   public void write(byte[] buf, int off, int len)
/*     */     throws IOException
/*     */   {
/*  88 */     for (int i = 0; i < len; i++)
/*  89 */       write(buf[(off + i)]);
/*     */   }
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 101 */     if (this.charCount % 3 == 1) {
/* 102 */       int lookup = this.carryOver << 4 & 0x3F;
/* 103 */       this.out.write(chars[lookup]);
/* 104 */       this.out.write(61);
/* 105 */       this.out.write(61);
/*     */     }
/* 107 */     else if (this.charCount % 3 == 2) {
/* 108 */       int lookup = this.carryOver << 2 & 0x3F;
/* 109 */       this.out.write(chars[lookup]);
/* 110 */       this.out.write(61);
/*     */     }
/* 112 */     super.close();
/*     */   }
/*     */ 
/*     */   public static String encode(String unencoded)
/*     */   {
/* 125 */     byte[] bytes = null;
/*     */     try {
/* 127 */       bytes = unencoded.getBytes("UTF-8");
/*     */     } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     }
/* 130 */     return encode(bytes);
/*     */   }
/*     */ 
/*     */   public static String encode(byte[] bytes)
/*     */   {
/* 140 */     ByteArrayOutputStream out = 
/* 141 */       new ByteArrayOutputStream((int)(bytes.length * 1.37D));
/* 142 */     Base64Encoder encodedOut = new Base64Encoder(out);
/*     */     try
/*     */     {
/* 145 */       encodedOut.write(bytes);
/* 146 */       encodedOut.close();
/*     */ 
/* 148 */       return out.toString("UTF-8"); } catch (IOException ignored) {
/*     */     }
/* 150 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 154 */     if (args.length != 1) {
/* 155 */       System.err.println(
/* 156 */         "Usage: java com.oreilly.servlet.Base64Encoder fileToEncode");
/* 157 */       return;
/*     */     }
/*     */ 
/* 160 */     Base64Encoder encoder = null;
/* 161 */     BufferedInputStream in = null;
/*     */     try {
/* 163 */       encoder = new Base64Encoder(System.out);
/* 164 */       in = new BufferedInputStream(new FileInputStream(args[0]));
/*     */ 
/* 166 */       byte[] buf = new byte[4096];
/*     */       int bytesRead;
/* 168 */       while ((bytesRead = in.read(buf)) != -1)
/*     */       {
///*     */         int bytesRead;
/* 169 */         encoder.write(buf, 0, bytesRead);
/*     */       }
/*     */     }
/*     */     finally {
/* 173 */       if (in != null) in.close();
/* 174 */       if (encoder != null) encoder.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.Base64Encoder
 * JD-Core Version:    0.6.2
 */