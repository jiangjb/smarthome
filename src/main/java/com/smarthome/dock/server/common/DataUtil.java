/*     */ package com.smarthome.dock.server.common;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.ParseException;
/*     */ 
/*     */ public class DataUtil
/*     */ {
/*     */   public static Object convertValue(Class aClass, Object aValue)
/*     */     throws Exception
/*     */   {
/*  14 */     if ((aValue == null) || (aClass == null)) {
/*  15 */       return null;
/*     */     }
/*  17 */     if ((aValue.getClass().equals(aClass)) && 
/*  18 */       (aClass != Timestamp.class) && 
/*  19 */       (aClass != java.sql.Date.class) && 
/*  20 */       (aClass != java.util.Date.class)) {
/*  21 */       return aValue;
/*     */     }
/*     */ 
/*  24 */     String strValue = aValue.toString();
/*  25 */     int iValueLen = strValue.length();
/*  26 */     if (iValueLen == 0) {
/*  27 */       return null;
/*     */     }
/*     */ 
/*     */     try
/*     */     {
/*  33 */       if (aClass == String.class) {
/*  34 */         if ((aValue instanceof byte[]))
/*  35 */           return new String((byte[])aValue);
/*  36 */         if ((aValue instanceof char[])) {
/*  37 */           return new String((char[])aValue);
/*     */         }
/*  39 */         return formatToString(aValue);
/*     */       }
/*     */ 
/*  43 */       if (aClass == Boolean.class) {
/*  44 */         strValue = removeNumStrComma(strValue);
/*  45 */         if (strValue.lastIndexOf(".") != -1) {
/*  46 */           strValue = strValue.substring(0, strValue.lastIndexOf("."));
/*     */         }
/*  48 */         if ((strValue.equals("true")) || (strValue.equals("false")))
/*  49 */           return Boolean.valueOf(strValue);
/*  50 */         if (Long.valueOf(strValue).longValue() == 0L) {
/*  51 */           return Boolean.valueOf("false");
/*     */         }
/*  53 */         return Boolean.valueOf("true");
/*     */       }
/*     */ 
/*  56 */       if (aClass == Double.class) {
/*  57 */         strValue = removeNumStrComma(strValue);
/*  58 */         return Double.valueOf(SyncDecimalFormat.format("#.########", Double.valueOf(strValue)));
/*     */       }
/*     */ 
/*  61 */       if (aClass == Float.class) {
/*  62 */         strValue = removeNumStrComma(strValue);
/*  63 */         return Float.valueOf(strValue);
/*     */       }
/*     */ 
/*  66 */       if (aClass == Byte.class) {
/*  67 */         strValue = removeNumStrComma(strValue);
/*  68 */         int iValueLastIndex = strValue.lastIndexOf(".");
/*  69 */         if (iValueLastIndex != -1) {
/*  70 */           return Byte.valueOf(strValue.substring(0, iValueLastIndex));
/*     */         }
/*  72 */         return Byte.valueOf(strValue);
/*     */       }
/*     */ 
/*  76 */       if (aClass == Short.class) {
/*  77 */         strValue = removeNumStrComma(strValue);
/*  78 */         if (strValue.lastIndexOf(".") != -1) {
/*  79 */           return Short.valueOf(strValue.substring(0, strValue.lastIndexOf(".")));
/*     */         }
/*  81 */         return Short.valueOf(strValue);
/*     */       }
/*     */ 
/*  85 */       if (aClass == Integer.class) {
/*  86 */         strValue = removeNumStrComma(strValue);
/*  87 */         if (strValue.lastIndexOf(".") != -1) {
/*  88 */           return Integer.valueOf(strValue.substring(0, strValue.lastIndexOf(".")));
/*     */         }
/*  90 */         return Integer.valueOf(strValue);
/*     */       }
/*     */ 
/*  94 */       if (aClass == Long.class) {
/*  95 */         strValue = removeNumStrComma(strValue);
/*  96 */         if (strValue.lastIndexOf(".") != -1) {
/*  97 */           return Long.valueOf(
/*  98 */             strValue.substring(0, strValue.lastIndexOf(".")));
/*     */         }
/* 100 */         return Long.valueOf(strValue);
/*     */       }
/*     */ 
/* 104 */       if (aClass == BigDecimal.class) {
/* 105 */         strValue = removeNumStrComma(strValue);
/* 106 */         return new BigDecimal(strValue);
/*     */       }
/*     */ 
/* 109 */       if (aClass == BigInteger.class) {
/* 110 */         strValue = removeNumStrComma(strValue);
/* 111 */         if (strValue.lastIndexOf(".") != -1) {
/* 112 */           return new BigInteger(strValue.substring(0, strValue.lastIndexOf(".")));
/*     */         }
/* 114 */         return new BigInteger(strValue);
/*     */       }
/*     */ 
/* 117 */       if (aClass == java.util.Date.class) {
/* 118 */         if ((aValue instanceof java.util.Date)) {
/* 119 */           return new java.sql.Date(((java.util.Date)aValue).getTime());
/*     */         }
/* 121 */         if (aValue.getClass() == String.class) {
/* 122 */           return new java.sql.Date(SyncSimpleDateFormat.parse("yyyy-MM-dd", strValue).getTime());
/*     */         }
/*     */ 
/*     */       }
/* 126 */       else if (aClass == java.sql.Date.class) {
/* 127 */         if ((aValue instanceof java.util.Date)) {
/* 128 */           return new java.sql.Date(((java.util.Date)aValue).getTime());
/*     */         }
/* 130 */         if (aValue.getClass() == String.class) {
/* 131 */           return new java.sql.Date(SyncSimpleDateFormat.parse("yyyy-MM-dd", strValue).getTime());
/*     */         }
/*     */ 
/*     */       }
/* 135 */       else if (aClass == Timestamp.class) {
/* 136 */         if ((aValue instanceof java.util.Date))
/* 137 */           return new Timestamp(((java.util.Date)aValue).getTime());
/* 138 */         if (aValue.getClass() == String.class)
/*     */         {
/* 140 */           if (aValue.toString().length() >= "yyyy-MM-dd HH:mm:ss".length()) {
/* 141 */             return new Timestamp(SyncSimpleDateFormat.parse("yyyy-MM-dd HH:mm:ss", strValue).getTime());
/*     */           }
/*     */ 
/* 144 */           if (aValue.toString().length() >= "yyyy-MM-dd HH:mm:ss".length())
/*     */           {
/* 146 */             return new Timestamp(SyncSimpleDateFormat.parse("yyyy-MM-dd HH:mm:ss", strValue).getTime());
/*     */           }
/*     */           try
/*     */           {
/* 150 */             return new Timestamp(SyncSimpleDateFormat.parse("yyyy-MM-dd", strValue).getTime());
/*     */           }
/*     */           catch (Exception localException)
/*     */           {
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */     catch (ParseException e)
/*     */     {
/* 161 */       String strMsg = "";
/* 162 */       if ((aClass == java.util.Date.class) || (aClass == java.sql.Date.class)) {
/* 163 */         strMsg = "Incorrect data format. Date value required.";
/*     */       }
/* 165 */       else if (aClass == Timestamp.class)
/* 166 */         strMsg = "Incorrect data format. Datetime value required.";
/*     */     }
/*     */     catch (NumberFormatException e) {
/* 169 */       String strMsg = "";
/* 170 */       if (aClass == Boolean.class)
/* 171 */         strMsg = "Incorrect data format. 0 or 1 required.";
/*     */       else {
/* 173 */         strMsg = "Incorrect data format. Numeric value required.";
/*     */       }
/*     */     }
/* 176 */     return aValue;
/*     */   }
/*     */ 
/*     */   public static String formatToString(Object Value)
/*     */   {
/* 185 */     if (Value == null) {
/* 186 */       return "";
/*     */     }
/*     */ 
/* 192 */     if (Value.getClass() == String.class) {
/* 193 */       return (String)Value;
/*     */     }
/* 195 */     if ((Value instanceof Number)) {
/* 196 */       return Value.toString();
/*     */     }
/* 198 */     if ((Value.getClass() == java.util.Date.class) || (Value.getClass() == java.sql.Date.class)) {
/* 199 */       return SyncSimpleDateFormat.format("yyyy-MM-dd", (java.util.Date)Value);
/*     */     }
/* 201 */     if (Value.getClass() == Timestamp.class) {
/* 202 */       return SyncSimpleDateFormat.format("yyyy-MM-dd HH:mm:ss", (java.util.Date)Value);
/*     */     }
/* 204 */     return Value.toString().trim();
/*     */   }
/*     */ 
/*     */   public static String formatToNullOrString(Object Value)
/*     */   {
/* 213 */     if (Value == null) {
/* 214 */       return null;
/*     */     }
/*     */ 
/* 217 */     if (Value.getClass() == String.class)
/*     */     {
/* 219 */       if (((String)Value).length() == 0) {
/* 220 */         return null;
/*     */       }
/*     */ 
/* 223 */       return ((String)Value).replace('\000', ' ').trim();
/*     */     }
/*     */ 
/* 226 */     if ((Value instanceof Number)) {
/* 227 */       return Value.toString();
/*     */     }
/* 229 */     if ((Value.getClass() == java.util.Date.class) || (Value.getClass() == java.sql.Date.class)) {
/* 230 */       return SyncSimpleDateFormat.format("yyyy-MM-dd", (java.util.Date)Value);
/*     */     }
/* 232 */     if (Value.getClass() == Timestamp.class) {
/* 233 */       return SyncSimpleDateFormat.format("yyyy-MM-dd HH:mm:ss", (java.util.Date)Value);
/*     */     }
/* 235 */     return Value.toString().trim();
/*     */   }
/*     */ 
/*     */   public static String removeNumStrComma(String straValue)
/*     */   {
/* 244 */     return straValue.replaceAll(",", "");
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.DataUtil
 * JD-Core Version:    0.6.2
 */