/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ 
/*     */ public class Base64Decoder extends FilterInputStream
/*     */ {
/*   7 */   private static final char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 
/*   8 */     'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
/*   9 */     'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 
/*  10 */     'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
/*  11 */     'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', 
/*  12 */     '7', '8', '9', '+', '/' };
/*     */ 
/*  15 */   private static final int[] ints = new int[''];
/*     */   private int charCount;
/*     */   private int carryOver;
/*     */ 
/*     */   static
/*     */   {
/*  17 */     for (int i = 0; i < 64; i++)
/*  18 */       ints[chars[i]] = i;
/*     */   }
/*     */ 
/*     */   public Base64Decoder(InputStream in)
/*     */   {
/*  33 */     super(in);
/*     */   }
/*     */ 
/*     */   public int read()
/*     */     throws IOException
/*     */   {int x;
/*     */     do
/*     */     {
/*  49 */       x = this.in.read();
/*  50 */       if (x == -1)
/*  51 */         return -1;
/*     */     }
/*  53 */     while (Character.isWhitespace((char)x));
/*  54 */     this.charCount += 1;
/*     */ 
/*  57 */     if (x == 61) {
/*  58 */       return -1;
/*     */     }
/*     */ 
/*  62 */     x = ints[x];
/*     */ 
/*  65 */     int mode = (this.charCount - 1) % 4;
/*     */ 
/*  68 */     if (mode == 0) {
/*  69 */       this.carryOver = (x & 0x3F);
/*  70 */       return read();
/*     */     }
/*     */ 
/*  74 */     if (mode == 1) {
/*  75 */       int decoded = (this.carryOver << 2) + (x >> 4) & 0xFF;
/*  76 */       this.carryOver = (x & 0xF);
/*  77 */       return decoded;
/*     */     }
/*     */ 
/*  81 */     if (mode == 2) {
/*  82 */       int decoded = (this.carryOver << 4) + (x >> 2) & 0xFF;
/*  83 */       this.carryOver = (x & 0x3);
/*  84 */       return decoded;
/*     */     }
/*     */ 
/*  87 */     if (mode == 3) {
/*  88 */       int decoded = (this.carryOver << 6) + x & 0xFF;
/*  89 */       return decoded;
/*     */     }
/*  91 */     return -1;
/*     */   }
/*     */ 
/*     */   public int read(byte[] buf, int off, int len)
/*     */     throws IOException
/*     */   {
/* 110 */     if (buf.length < len + off - 1) {
/* 111 */       throw new IOException("The input buffer is too small: " + len + 
/* 112 */         " bytes requested starting at offset " + off + 
/* 113 */         " while the buffer " + " is only " + buf.length + 
/* 114 */         " bytes long.");
/*     */     }
/*     */ 	  int x = read();
/* 119 */     for (int i = 0; i < len; i++) {
/* 120 */       
/* 121 */       if ((x == -1) && (i == 0))
/* 122 */         return -1;
/* 123 */       if (x == -1) {
/*     */         break;
/*     */       }
/* 126 */       buf[(off + i)] = ((byte)x);
/*     */     }
/* 128 */     return x;
/*     */   }
/*     */ 
/*     */   public static String decode(String encoded)
/*     */   {
/* 142 */     return new String(decodeToBytes(encoded));
/*     */   }
/*     */ 
/*     */   public static byte[] decodeToBytes(String encoded)
/*     */   {
/* 153 */     byte[] bytes = null;
/*     */     try {
/* 155 */       bytes = encoded.getBytes("UTF-8");
/*     */     }
/*     */     catch (UnsupportedEncodingException localUnsupportedEncodingException) {
/*     */     }
/* 159 */     Base64Decoder in = new Base64Decoder(new ByteArrayInputStream(bytes));
/*     */ 
/* 161 */     ByteArrayOutputStream out = new ByteArrayOutputStream(
/* 162 */       (int)(bytes.length * 0.67D));
/*     */     try
/*     */     {
/* 165 */       byte[] buf = new byte[4096];
/*     */       int bytesRead;
/* 167 */       while ((bytesRead = in.read(buf)) != -1)
/*     */       {
///*     */         int bytesRead;
/* 168 */         out.write(buf, 0, bytesRead);
/*     */       }
/* 170 */       out.close();
/*     */ 
/* 172 */       return out.toByteArray(); } catch (IOException ignored) {
/*     */     }
/* 174 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception
/*     */   {
/* 179 */     if (args.length != 1) {
/* 180 */       System.err.println("Usage: java Base64Decoder fileToDecode");
/* 181 */       return;
/*     */     }
/*     */ 
/* 184 */     Base64Decoder decoder = null;
/*     */     try {
/* 186 */       decoder = new Base64Decoder(new BufferedInputStream(
/* 187 */         new FileInputStream(args[0])));
/* 188 */       byte[] buf = new byte[4096];
/*     */       int bytesRead;
/* 190 */       while ((bytesRead = decoder.read(buf)) != -1)
/*     */       {
///*     */         int bytesRead;
/* 191 */         System.out.write(buf, 0, bytesRead);
/*     */       }
/*     */     } finally {
/* 194 */       if (decoder != null)
/* 195 */         decoder.close();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.Base64Decoder
 * JD-Core Version:    0.6.2
 */