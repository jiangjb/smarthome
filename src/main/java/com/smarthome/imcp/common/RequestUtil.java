/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class RequestUtil
/*     */ {
/*     */   private static final String CS_EMPTY = "";
/*     */ 
/*     */   public static String getStringParameter(HttpServletRequest request, String strFieldName)
/*     */   {
/*  20 */     return getStringParameter(request, strFieldName, "");
/*     */   }
/*     */ 
/*     */   public static String getStringParameter(HttpServletRequest request, String strFieldName, String strDefaultValue)
/*     */   {
/*  34 */     String strValue = request.getParameter(strFieldName);
/*  35 */     if ((strValue == null) || (strValue.length() == 0)) {
/*  36 */       strValue = (String)request.getAttribute(strFieldName);
/*  37 */       if ((strValue == null) || (strValue.length() == 0)) {
/*  38 */         return strDefaultValue;
/*     */       }
/*  40 */       return strValue;
/*     */     }
/*     */ 
/*  43 */     return strValue;
/*     */   }
/*     */ 
/*     */   public static int getIntParameter(HttpServletRequest request, String strFieldName)
/*     */   {
/*  56 */     return getIntParameter(request, strFieldName, 0);
/*     */   }
/*     */ 
/*     */   public static int getIntParameter(HttpServletRequest request, String strFieldName, int inDefaultValue)
/*     */   {
/*  70 */     int intValue = inDefaultValue;
/*  71 */     String strValue = request.getParameter(strFieldName);
/*  72 */     if ((strValue != null) && (strValue.length() != 0))
/*     */       try {
/*  74 */         intValue = Integer.parseInt(strValue);
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/*  78 */     return intValue;
/*     */   }
/*     */ 
/*     */   public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String strFieldName)
/*     */   {
/*  90 */     return getBigDecimalParameter(request, strFieldName, new BigDecimal(0));
/*     */   }
/*     */ 
/*     */   public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String strFieldName, BigDecimal decDefaultValue)
/*     */   {
/* 104 */     BigDecimal decValue = decDefaultValue;
/* 105 */     String strValue = request.getParameter(strFieldName);
/* 106 */     if ((strValue != null) && (strValue.length() != 0))
/*     */       try {
/* 108 */         decValue = new BigDecimal(strValue);
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/* 112 */     return decValue;
/*     */   }
/*     */ 
/*     */   public static Long getLongParameter(HttpServletRequest request, String strFieldName)
/*     */   {
/* 124 */     return getLongParameter(request, strFieldName, new Long(0L));
/*     */   }
/*     */ 
/*     */   public static Long getLongParameter(HttpServletRequest request, String strFieldName, Long decDefaultValue)
/*     */   {
/* 138 */     Long decValue = decDefaultValue;
/* 139 */     String strValue = request.getParameter(strFieldName);
/* 140 */     if ((strValue != null) && (strValue.length() != 0))
/*     */       try {
/* 142 */         decValue = new Long(strValue);
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/* 146 */     return decValue;
/*     */   }
/*     */ 
/*     */   public static Double getDoubleParameter(HttpServletRequest request, String strFieldName, Double decDefaultValue)
/*     */   {
/* 160 */     Double decValue = decDefaultValue;
/* 161 */     String strValue = request.getParameter(strFieldName);
/* 162 */     if ((strValue != null) && (strValue.length() != 0))
/*     */       try {
/* 164 */         decValue = new Double(strValue);
/*     */       }
/*     */       catch (Exception localException) {
/*     */       }
/* 168 */     return decValue;
/*     */   }
/*     */ 
/*     */   public static boolean getBoolParameter(HttpServletRequest request, String strFieldName, String strTrueValue)
/*     */   {
/* 181 */     boolean boolRtn = false;
/* 182 */     String strValue = request.getParameter(strFieldName);
/* 183 */     if (strTrueValue.equals(strValue)) {
/* 184 */       boolRtn = true;
/*     */     }
/* 186 */     return boolRtn;
/*     */   }
/*     */ 
/*     */   public static String getHostContextURL(HttpServletRequest request)
/*     */   {
/* 198 */     StringBuffer sbURL = new StringBuffer();
/*     */ 
/* 200 */     sbURL.append("http://").append(request.getHeader("Host"));
/*     */ 
/* 202 */     sbURL.append(request.getContextPath()).append("/");
/* 203 */     return sbURL.toString();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.RequestUtil
 * JD-Core Version:    0.6.2
 */