/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public class UpNumber
/*     */ {
/*  11 */   private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍", 
/*  12 */     "陆", "柒", "捌", "玖" };
/*     */ 
/*  14 */   private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾", "佰", 
/*  15 */     "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
/*     */ 
/*  17 */   private static final String[] DUNIT = { "角", "分" };
/*     */ 
/*     */   public static String toChinese(String str)
/*     */   {
/*  21 */     str = str.replaceAll(",", "");
/*     */     String decimalStr;
/*     */     String integerStr;
///*     */     String decimalStr;
/*  25 */     if (str.indexOf(".") > 0) {
///*  26 */       String integerStr = str.substring(0, str.indexOf("."));
				integerStr = str.substring(0, str.indexOf("."));
/*  27 */       decimalStr = str.substring(str.indexOf(".") + 1);
/*     */     }
/*     */     else
/*     */     {
///*     */       String decimalStr;
/*  28 */       if (str.indexOf(".") == 0) {
///*  29 */         String integerStr = "";
				  integerStr = "";
/*  30 */         decimalStr = str.substring(1);
/*     */       } else {
/*  32 */         integerStr = str;
/*  33 */         decimalStr = "";
/*     */       }
/*     */     }
/*  35 */     if (!integerStr.equals("")) {
/*  36 */       integerStr = Long.toString(Long.parseLong(integerStr));
/*  37 */       if (integerStr.equals("0")) {
/*  38 */         integerStr = "";
/*     */       }
/*     */     }
/*  41 */     if (integerStr.length() > IUNIT.length) {
/*  42 */       System.out.println(str + ":超出处理能力");
/*  43 */       return str;
/*     */     }
/*  45 */     int[] integers = toArray(integerStr);
/*  46 */     boolean isMust5 = isMust5(integerStr);
/*  47 */     int[] decimals = toArray(decimalStr);
/*  48 */     return getChineseInteger(integers, isMust5) + 
/*  49 */       getChineseDecimal(decimals);
/*     */   }
/*     */ 
/*     */   private static int[] toArray(String number)
/*     */   {
/*  54 */     int[] array = new int[number.length()];
/*  55 */     for (int i = 0; i < number.length(); i++) {
/*  56 */       array[i] = Integer.parseInt(number.substring(i, i + 1));
/*     */     }
/*  58 */     return array;
/*     */   }
/*     */ 
/*     */   private static String getChineseInteger(int[] integers, boolean isMust5)
/*     */   {
/*  63 */     StringBuffer chineseInteger = new StringBuffer("");
/*  64 */     int length = integers.length;
/*  65 */     for (int i = 0; i < length; i++)
/*     */     {
/*  68 */       String key = "";
/*  69 */       if (integers[i] == 0) {
/*  70 */         if (length - i == 13)
/*     */         {
/*  72 */           key = IUNIT[4];
/*  73 */         } else if (length - i == 9)
/*     */         {
/*  75 */           key = IUNIT[8];
/*  76 */         } else if ((length - i == 5) && (isMust5))
/*     */         {
/*  78 */           key = IUNIT[4];
/*  79 */         } else if (length - i == 1)
/*     */         {
/*  81 */           key = IUNIT[0];
/*     */         }
/*  83 */         if ((length - i > 1) && (integers[(i + 1)] != 0))
/*  84 */           key = key + NUMBERS[0];
/*     */       }
/*  86 */       chineseInteger.append(
/*  87 */         NUMBERS[integers[i]] + IUNIT[(length - i - 1)]);
/*     */     }
/*  89 */     return chineseInteger.toString();
/*     */   }
/*     */ 
/*     */   private static String getChineseDecimal(int[] decimals)
/*     */   {
/*  94 */     StringBuffer chineseDecimal = new StringBuffer("");
/*  95 */     for (int i = 0; i < decimals.length; i++)
/*     */     {
/*  97 */       if (i == 2)
/*     */         break;
/*  99 */       chineseDecimal.append(
/* 100 */         NUMBERS[decimals[i]] + DUNIT[i]);
/*     */     }
/* 102 */     return chineseDecimal.toString();
/*     */   }
/*     */ 
/*     */   private static boolean isMust5(String integerStr)
/*     */   {
/* 107 */     int length = integerStr.length();
/* 108 */     if (length > 4) {
/* 109 */       String subInteger = "";
/* 110 */       if (length > 8)
/*     */       {
/* 112 */         subInteger = integerStr.substring(length - 8, length - 4);
/*     */       }
/* 114 */       else subInteger = integerStr.substring(0, length - 4);
/*     */ 
/* 116 */       return Integer.parseInt(subInteger) > 0;
/*     */     }
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */   {
/* 123 */     System.out.println(toChinese("1000.23"));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.UpNumber
 * JD-Core Version:    0.6.2
 */