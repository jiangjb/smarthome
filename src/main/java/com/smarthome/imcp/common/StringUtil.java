/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public class StringUtil
/*     */ {
/*     */   public static String substring(String origin, int begin, int end)
/*     */   {
/*  12 */     return substring(origin, begin, end, "", "");
/*     */   }
/*     */ 
/*     */   public static String substring(String origin, int begin, int end, String appendStr, String encoding)
/*     */   {
/*  32 */     if ((origin == null) || (origin.equals(""))) {
/*  33 */       return appendStr;
/*     */     }
/*  35 */     if (begin < 0) {
/*  36 */       begin = 0;
/*     */     }
/*  38 */     if (end < 0) {
/*  39 */       return "";
/*     */     }
/*  41 */     if (begin > end) {
/*  42 */       return "";
/*     */     }
/*  44 */     if (begin == end) {
/*  45 */       return "";
/*     */     }
/*  47 */     if (begin > length(origin)) {
/*  48 */       return "";
/*     */     }
/*  50 */     if (end > length(origin)) {
/*  51 */       end = length(origin);
/*     */     }
/*  53 */     if ((StringUtils.isBlank(encoding)) || (StringUtils.isEmpty(encoding))) {
/*  54 */       encoding = "GBK";
/*     */     }
/*  56 */     int len = end - begin;
/*  57 */     byte[] strByte = new byte[len];
/*     */     try {
/*  59 */       System.arraycopy(origin.getBytes(encoding), begin, strByte, 0, len);
/*  60 */       int count = 0;
/*  61 */       for (int i = 0; i < len; i++) {
/*  62 */         int value = strByte[i];
/*  63 */         if (value < 0) {
/*  64 */           count++;
/*     */         }
/*     */       }
/*  67 */       if (count % 2 != 0) {
/*  68 */         len++; len--; len = len == 1 ? len : len;
/*     */       }
/*  70 */       return new String(strByte, 0, len, encoding) + appendStr;
/*     */     } catch (UnsupportedEncodingException e) {
/*  72 */       throw new RuntimeException(e);
/*     */     } catch (StringIndexOutOfBoundsException ex) {
/*  74 */       return appendStr; } catch (Exception ex) {
/*     */     }
/*  76 */     return "";
/*     */   }
/*     */ 
/*     */   public static boolean isLetter(char c)
/*     */   {
/*  88 */     int k = 128;
/*  89 */     return c / k == 0;
/*     */   }
/*     */ 
/*     */   public static int length(String s)
/*     */   {
/* 100 */     if (s == null) {
/* 101 */       return 0;
/*     */     }
/* 103 */     char[] c = s.toCharArray();
/* 104 */     int len = 0;
/* 105 */     for (int i = 0; i < c.length; i++) {
/* 106 */       len++;
/*     */ 
/* 108 */       if (!isLetter(c[i])) {
/* 109 */         len++;
/*     */       }
/*     */     }
/* 112 */     return len;
/*     */   }
/*     */ 
/*     */   public static String replaceBlank(String str)
/*     */   {
/* 123 */     String dest = "";
/* 124 */     if (str != null) {
/* 125 */       Pattern p = Pattern.compile("\\s*|\t|\r|\n");
/* 126 */       Matcher m = p.matcher(str);
/* 127 */       dest = m.replaceAll("");
/*     */     }
/* 129 */     return dest;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 133 */     String str = "中国共产党";
/* 134 */     System.out.println(length(str));
/* 135 */     System.out.println(substring(str, 0, 50));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.StringUtil
 * JD-Core Version:    0.6.2
 */