/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ 
/*     */ public class NumberUtils
/*     */ {
/*     */   public static double format(double d, String format)
/*     */   {
/*   9 */     DecimalFormat df = new DecimalFormat(format);
/*  10 */     String ds = df.format(d);
/*  11 */     return Double.parseDouble(ds);
/*     */   }
/*     */ 
/*     */   public static double format2(double d) {
/*  15 */     DecimalFormat df = new DecimalFormat("0.00");
/*  16 */     String ds = df.format(d);
/*  17 */     return Double.parseDouble(ds);
/*     */   }
/*     */ 
/*     */   public static String format(double d) {
/*  21 */     DecimalFormat df = new DecimalFormat("0");
/*  22 */     String ds = df.format(d);
/*  23 */     return ds;
/*     */   }
/*     */ 
/*     */   public static String format2Str(double d)
/*     */   {
/*  28 */     DecimalFormat df = new DecimalFormat("0.00");
/*  29 */     String ds = df.format(d);
/*  30 */     return ds;
/*     */   }
/*     */ 
/*     */   public static String format2Str2(double d) {
/*  34 */     DecimalFormat df = new DecimalFormat("00");
/*  35 */     String ds = df.format(d);
/*  36 */     return ds;
/*     */   }
/*     */ 
/*     */   public static double format4(double d)
/*     */   {
/*  41 */     DecimalFormat df = new DecimalFormat("0.0000");
/*  42 */     String ds = df.format(d);
/*  43 */     return Double.parseDouble(ds);
/*     */   }
/*     */ 
/*     */   public static double format6(double d) {
/*  47 */     DecimalFormat df = new DecimalFormat("0.000000");
/*  48 */     String ds = df.format(d);
/*  49 */     return Double.parseDouble(ds);
/*     */   }
/*     */ 
/*     */   public static double format6(String d)
/*     */   {
/*  54 */     DecimalFormat df = new DecimalFormat("#.000000");
/*  55 */     String ds = df.format(d);
/*  56 */     return Double.parseDouble(ds);
/*     */   }
/*     */ 
/*     */   public static double getDouble(String str)
/*     */   {
/*  62 */     if ((str == null) || (str.equals("")))
/*  63 */       return 0.0D;
/*  64 */     double ret = 0.0D;
/*     */     try {
/*  66 */       ret = Double.parseDouble(str);
/*     */     } catch (NumberFormatException e) {
/*  68 */       ret = 0.0D;
/*     */     }
/*  70 */     return format6(ret);
/*     */   }
/*     */ 
/*     */   public static BigDecimal getBigDecimal(String str)
/*     */   {
/*  75 */     if ((str == null) || (str.equals(""))) {
/*  76 */       return new BigDecimal(0);
/*     */     }
/*  78 */     BigDecimal original = null;
/*     */     try {
/*  80 */       original = new BigDecimal(str);
/*     */     } catch (NumberFormatException e) {
/*  82 */       original = new BigDecimal(0);
/*     */     }
/*  84 */     return original;
/*     */   }
/*     */ 
/*     */   public static long getLong(String str)
/*     */   {
/*  90 */     if ((str == null) || (str.equals("")))
/*  91 */       return 0L;
/*  92 */     long ret = 0L;
/*     */     try {
/*  94 */       ret = Long.parseLong(str);
/*     */     } catch (NumberFormatException e) {
/*  96 */       ret = 0L;
/*     */     }
/*  98 */     return ret;
/*     */   }
/*     */ 
/*     */   public static Long[] getLongs(String[] str)
/*     */   {
/* 103 */     if ((str == null) || (str.length < 1))
/* 104 */       return new Long[] { Long.valueOf(0L) };
/* 105 */     Long[] ret = new Long[str.length];
/* 106 */     for (int i = 0; i < str.length; i++) {
/* 107 */       ret[i] = Long.valueOf(getLong(str[i]));
/*     */     }
/* 109 */     return ret;
/*     */   }
/*     */ 
/*     */   public static int[] getInts(String[] str)
/*     */   {
/* 114 */     if ((str == null) || (str.length < 1))
/* 115 */       return new int[1];
/* 116 */     int[] ret = new int[str.length];
/* 117 */     for (int i = 0; i < str.length; i++) {
/* 118 */       ret[i] = getInt(str[i]);
/*     */     }
/* 120 */     return ret;
/*     */   }
/*     */ 
/*     */   public static int getInt(String str) {
/* 124 */     if ((str == null) || (str.equals("")))
/* 125 */       return 0;
/* 126 */     int ret = 0;
/*     */     try {
/* 128 */       ret = Integer.parseInt(str);
/*     */     } catch (NumberFormatException e) {
/* 130 */       ret = 0;
/*     */     }
/* 132 */     return ret;
/*     */   }
/*     */ 
/*     */   public static int compare(double x, double y) {
/* 136 */     BigDecimal val1 = new BigDecimal(x);
/* 137 */     BigDecimal val2 = new BigDecimal(y);
/* 138 */     return val1.compareTo(val2);
/*     */   }
/*     */ 
/*     */   public static double ceil(double d, int len)
/*     */   {
/* 147 */     String str = Double.toString(d);
/* 148 */     int a = str.indexOf(".");
/* 149 */     if (a + 3 > str.length())
/* 150 */       a = str.length();
/*     */     else {
/* 152 */       a += 3;
/*     */     }
/* 154 */     str = str.substring(0, a);
/* 155 */     return Double.parseDouble(str);
/*     */   }
/*     */ 
/*     */   public static double ceil(double d) {
/* 159 */     return ceil(d, 2);
/*     */   }
/*     */ 
/*     */   public static long getRandom(int len) {
/* 163 */     double r = Math.random();
/* 164 */     for (int i = 0; i < len; i++) {
/* 165 */       r *= 10.0D;
/*     */     }
/* 167 */     long ret = (long)r;
/* 168 */     return ret;
/*     */   }
/*     */ 
/*     */   public static BigDecimal round(double d)
/*     */   {
/* 178 */     return new BigDecimal(d).setScale(0, 4);
/*     */   }
/*     */ 
/*     */   public static BigDecimal round(double d, int scale)
/*     */   {
/* 188 */     return new BigDecimal(d).setScale(scale, 4);
/*     */   }
/*     */ 
/*     */   public static double toFixed(double d, int len)
/*     */   {
/* 198 */     len = len <= 1 ? 1 : len;
/* 199 */     NumberFormat nbf = NumberFormat.getInstance();
/* 200 */     nbf.setMinimumFractionDigits(len);
/* 201 */     nbf.setMaximumFractionDigits(len);
/* 202 */     nbf.setGroupingUsed(false);
/* 203 */     return new Double(nbf.format(d)).doubleValue();
/*     */   }
/*     */ 
/*     */   public static boolean isInRange(int num, int min, int max)
/*     */   {
/* 214 */     if ((num >= min) && (num <= max)) {
/* 215 */       return true;
/*     */     }
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 222 */     double dd = 152.41999999999999D;
/* 223 */     System.out.println(format(0.0D));
/* 224 */     System.out.println(getBigDecimal("15667641.70"));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.NumberUtils
 * JD-Core Version:    0.6.2
 */