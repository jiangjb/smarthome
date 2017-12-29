/*     */ package com.smarthome.dock.server.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.StringTokenizer;
/*     */ 
/*     */ public class Util
/*     */ {
/*  16 */   private static StringBuilder sb = new StringBuilder();
/*     */ 
/*  19 */   private static char[] hex = { 
/*  20 */     '0', '1', '2', '3', '4', '5', '6', '7', 
/*  21 */     '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */   public static boolean isByteArrayEqual(byte[] b1, byte[] b2)
/*     */   {
/*  35 */     if (b1.length != b2.length) {
/*  36 */       return false;
/*     */     }
/*  38 */     for (int i = 0; i < b1.length; i++) {
/*  39 */       if (b1[i] != b2[i])
/*  40 */         return false;
/*     */     }
/*  42 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean isIpZero(byte[] ip)
/*     */   {
/*  51 */     for (int i = 0; i < ip.length; i++) {
/*  52 */       if (ip[i] != 0)
/*  53 */         return false;
/*     */     }
/*  55 */     return true;
/*     */   }
/*     */ 
/*     */   public static byte[] int2byte(int res) {
/*  59 */     byte[] targets = new byte[4];
/*     */ 
/*  61 */     targets[0] = ((byte)(res & 0xFF));
/*  62 */     targets[1] = ((byte)(res >> 8 & 0xFF));
/*  63 */     targets[2] = ((byte)(res >> 16 & 0xFF));
/*  64 */     targets[3] = ((byte)(res >>> 24));
/*  65 */     return targets;
/*     */   }
/*     */ 
/*     */   public static byte[] getBytes(String s, String encoding)
/*     */   {
/*     */     try
/*     */     {
/*  76 */       return s.getBytes(encoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/*  78 */     return s.getBytes();
/*     */   }
/*     */ 
/*     */   public static byte[] getBytes(String s)
/*     */   {
/*  89 */     return getBytes(s, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static byte[] getBytes(String s, int maxLength) {
/*  93 */     byte[] tp = new byte[maxLength];
/*     */ 
/*  95 */     byte[] b = getBytes(s);
/*  96 */     int len = b.length > maxLength ? maxLength : b.length;
/*  97 */     System.arraycopy(b, 0, tp, 0, len);
/*     */ 
/* 104 */     return tp;
/*     */   }
/*     */ 
/*     */   public static String getString(String s, String srcEncoding, String destEncoding)
/*     */   {
/*     */     try
/*     */     {
/* 116 */       return new String(s.getBytes(srcEncoding), destEncoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/* 118 */     return s;
/*     */   }
/*     */ 
/*     */   public static String getString2(byte[] b, String encoding)
/*     */   {
/*     */     try
/*     */     {
/* 130 */       int count = 0;
/* 131 */       for (int i = 0; i < b.length; i++) {
/* 132 */         if (b[i] == 0) {
/*     */           break;
/*     */         }
/* 135 */         count++;
/*     */       }
/* 137 */       byte[] b2 = new byte[count];
/* 138 */       System.arraycopy(b, 0, b2, 0, count);
/*     */ 
/* 140 */       return new String(b2, encoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/* 142 */     return new String(b);
/*     */   }
/*     */ 
/*     */   public static String getString(byte[] b, String encoding)
/*     */   {
/*     */     try
/*     */     {
/* 154 */       return new String(b, encoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/* 156 */     return new String(b);
/*     */   }
/*     */ 
/*     */   public static String getString2(byte[] b)
/*     */   {
/* 169 */     return getString2(b, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String getString(byte[] b)
/*     */   {
/* 181 */     return getString(b, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static String getString(byte[] b, int offset, int len, String encoding)
/*     */   {
/*     */     try
/*     */     {
/* 194 */       return new String(b, offset, len, encoding); } catch (UnsupportedEncodingException e) {
/*     */     }
/* 196 */     return new String(b, offset, len);
/*     */   }
/*     */ 
/*     */   public static String getString(byte[] b, int offset, int len)
/*     */   {
/* 208 */     return getString(b, offset, len, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static int getInt(String s, int faultValue)
/*     */   {
/*     */     try
/*     */     {
/* 219 */       return Integer.parseInt(s); } catch (NumberFormatException e) {
/*     */     }
/* 221 */     return faultValue;
/*     */   }
/*     */ 
/*     */   public static long getLong(String s, int radix, long faultValue)
/*     */   {
/*     */     try
/*     */     {
/* 233 */       return Long.parseLong(s, radix); } catch (NumberFormatException e) {
/*     */     }
/* 235 */     return faultValue;
/*     */   }
/*     */ 
/*     */   public static int getInt(String s, int radix, int faultValue)
/*     */   {
/*     */     try
/*     */     {
/* 249 */       return Integer.parseInt(s, radix); } catch (NumberFormatException e) {
/*     */     }
/* 251 */     return faultValue;
/*     */   }
/*     */ 
/*     */   public static int byteArrayToInt(byte[] b, int offset)
/*     */   {
/* 256 */     int value = 0;
/* 257 */     for (int i = 0; i < 4; i++) {
/* 258 */       int shift = (3 - i) * 8;
/* 259 */       value += ((b[(i + offset)] & 0xFF) << shift);
/*     */     }
/* 261 */     return value;
/*     */   }
/*     */ 
/*     */   public static int byte2ToInt(byte[] b) {
/* 265 */     int value = 0;
/* 266 */     for (int i = 0; i < 2; i++) {
/* 267 */       int shift = (1 - i) * 8;
/* 268 */       value += ((b[i] & 0xFF) << shift);
/*     */     }
/* 270 */     return value;
/*     */   }
/*     */ 
/*     */   public static boolean isInt(String s)
/*     */   {
/*     */     try
/*     */     {
/* 283 */       Integer.parseInt(s);
/* 284 */       return true; } catch (NumberFormatException e) {
/*     */     }
/* 286 */     return false;
/*     */   }
/*     */ 
/*     */   public static char getChar(String s, int faultValue)
/*     */   {
/* 297 */     return (char)(getInt(s, faultValue) & 0xFFFF);
/*     */   }
/*     */ 
/*     */   public static byte getByte(String s, int faultValue)
/*     */   {
/* 307 */     return (byte)(getInt(s, faultValue) & 0xFF);
/*     */   }
/*     */ 
/*     */   public static String getIpStringFromBytes(byte[] ip)
/*     */   {
/* 315 */     sb.delete(0, sb.length());
/* 316 */     sb.append(ip[0] & 0xFF);
/* 317 */     sb.append('.');
/* 318 */     sb.append(ip[1] & 0xFF);
/* 319 */     sb.append('.');
/* 320 */     sb.append(ip[2] & 0xFF);
/* 321 */     sb.append('.');
/* 322 */     sb.append(ip[3] & 0xFF);
/* 323 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static byte[] getIpByteArrayFromString(String ip)
/*     */   {
/* 332 */     byte[] ret = new byte[4];
/* 333 */     StringTokenizer st = new StringTokenizer(ip, ".");
/*     */     try {
/* 335 */       ret[0] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/* 336 */       ret[1] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/* 337 */       ret[2] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/* 338 */       ret[3] = ((byte)(Integer.parseInt(st.nextToken()) & 0xFF));
/*     */     }
/*     */     catch (Exception localException) {
/*     */     }
/* 342 */     return ret;
/*     */   }
/*     */ 
/*     */   public static boolean isIpEquals(byte[] ip1, byte[] ip2)
/*     */   {
/* 352 */     return (ip1[0] == ip2[0]) && (ip1[1] == ip2[1]) && (ip1[2] == ip2[2]) && (ip1[3] == ip2[3]);
/*     */   }
/*     */ 
/*     */   public static byte[] toLH(char n)
/*     */   {
/* 359 */     byte[] b = new byte[2];
/* 360 */     b[0] = ((byte)(n & 0xFF));
/* 361 */     b[1] = ((byte)(n >> '\b' & 0xFF));
/* 362 */     return b;
/*     */   }
/*     */ 
/*     */   public static byte[] toLH(int n)
/*     */   {
/* 369 */     byte[] b = new byte[4];
/* 370 */     b[0] = ((byte)(n & 0xFF));
/* 371 */     b[1] = ((byte)(n >> 8 & 0xFF));
/* 372 */     b[2] = ((byte)(n >> 16 & 0xFF));
/* 373 */     b[3] = ((byte)(n >> 24 & 0xFF));
/* 374 */     return b;
/*     */   }
/*     */ 
/*     */   public static byte[] toLH(float f)
/*     */   {
/* 380 */     return toLH(Float.floatToRawIntBits(f));
/*     */   }
/*     */ 
/*     */   public static String getCommandString(char command)
/*     */   {
/* 389 */     switch (command) {
/*     */     case 'ꀀ':
/* 391 */       return "MSG_DEV_REG";
/*     */     case 'ꀁ':
/* 393 */       System.err.println("发了");
/* 394 */       return "MSG_DEV_REG_RE";
/*     */     case 'ꀂ':
/* 396 */       return "MSG_DEV_HEART_BEAT";
/*     */     case 'ꀃ':
/* 398 */       return "MSG_DEV_HEART_BEAT_RE";
/*     */     case '뀀':
/* 400 */       return "MSG_CLI_QUERY";
/*     */     case '뀁':
/* 402 */       return "MSG_CLI_QUERY_RE";
/*     */     }
/* 404 */     if ((command >= 49152) && (command <= 53247)) {
/* 405 */       return "MSG_C2D_DATA";
/*     */     }
/* 407 */     if ((command >= 53248) && (command <= 57343)) {
/* 408 */       return "MSG_D2C_DATA";
/*     */     }
/* 410 */     if ((command >= 57344) && (command <= 61439)) {
/* 411 */       return "MSG_D2S_DATA";
/*     */     }
/* 413 */     if ((command >= 61440) && (command <= 65535)) {
/* 414 */       return "MSG_D2S_ALARM";
/*     */     }
/* 416 */     return "Unknown";
/*     */   }
/*     */ 
/*     */   public static byte getValidateByte(byte[] sendData)
/*     */   {
/* 421 */     int sum = 0;
/* 422 */     for (int i = 0; i < sendData.length; i++) {
/* 423 */       sum += getInt(sendData[i]);
/*     */     }
/* 425 */     return (byte)sum;
/*     */   }
/*     */ 
/*     */   public static int getInt(byte b) {
/* 429 */     return b & 0xFF;
/*     */   }
/*     */ 
/*     */   public String getDevTypeName(char devType) {
/* 433 */     switch (devType) {
/*     */     case 'Đ':
/* 435 */       return "PM2.5传感器";
/*     */     case 'Ȑ':
/* 437 */       return "人体红外传感器";
/*     */     case '̐':
/* 439 */       return "温湿度传感器";
/*     */     case 'Ġ':
/* 441 */       return "U-air(wifi)传感器";
/*     */     case 'İ':
/* 443 */       return "水阀(wifi)";
/*     */     case 'ŀ':
/* 445 */       return "东磁净水器(wifi)";
/*     */     }
/* 447 */     return "未知设备";
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.util.Util
 * JD-Core Version:    0.6.2
 */