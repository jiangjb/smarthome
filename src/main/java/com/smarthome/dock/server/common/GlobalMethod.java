/*     */ package com.smarthome.dock.server.common;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ 
/*     */ public final class GlobalMethod
/*     */ {
/*     */   public static String removeLeadingChar(String strSource, char leadChar)
/*     */   {
/*  16 */     if ((strSource != null) && 
/*  17 */       (strSource.startsWith(String.valueOf(leadChar)))) {
/*  18 */       int length = strSource.length();
/*  19 */       for (int i = 0; i < length; i++) {
/*  20 */         if (strSource.charAt(i) != leadChar) {
/*  21 */           return strSource.substring(i, length);
/*     */         }
/*     */       }
/*  24 */       return "";
/*     */     }
/*     */ 
/*  27 */     return strSource;
/*     */   }
/*     */ 
/*     */   public static String removeLeadingZero(String strSource) {
/*  31 */     return removeLeadingChar(strSource, '0');
/*     */   }
/*     */ 
/*     */   public static String addLeadingZero(int source, int intTotalCount) {
/*  35 */     String strSource = String.valueOf(source);
/*  36 */     return addLeadingZero(strSource, intTotalCount);
/*     */   }
/*     */ 
/*     */   public static String addLeadingZero(String strSource, int intTotalCount) {
/*  40 */     int intCount = intTotalCount - strSource.length();
/*  41 */     String strFormat = strSource;
/*  42 */     for (int i = 0; i < intCount; i++) {
/*  43 */       strFormat = "0" + strFormat;
/*     */     }
/*  45 */     return strFormat;
/*     */   }
/*     */ 
/*     */   public static BigDecimal ifNullBigDec(Object objValue)
/*     */   {
/*  56 */     BigDecimal bdValue = new BigDecimal(0);
/*     */     try {
/*  58 */       if (!isNullorEmpty(objValue));
/*  59 */       return (BigDecimal)DataUtil.convertValue(
/*  60 */         BigDecimal.class, objValue);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/*  64 */       System.out.println(e);
/*  65 */     }return bdValue;
/*     */   }
/*     */ 
/*     */   public static boolean isNull(Object objValue)
/*     */   {
/*  77 */     return objValue == null;
/*     */   }
/*     */ 
/*     */   public static boolean isNullorEmpty(Object objValue)
/*     */   {
/*  88 */     if (objValue == null)
/*  89 */       return true;
/*  90 */     if ((objValue instanceof String))
/*  91 */       return ((String)objValue).length() == 0;
/*  92 */     if ((objValue instanceof List))
/*  93 */       return ((List)objValue).isEmpty();
/*  94 */     if ((objValue instanceof Map)) {
/*  95 */       return ((Map)objValue).isEmpty();
/*     */     }
/*  97 */     return objValue.toString().length() == 0;
/*     */   }
/*     */ 
/*     */   public static boolean isNumericType(Object value)
/*     */   {
/* 108 */     boolean result = false;
/* 109 */     if ((value instanceof Integer))
/* 110 */       result = true;
/* 111 */     else if ((value instanceof Long))
/* 112 */       result = true;
/* 113 */     else if ((value instanceof Double))
/* 114 */       result = true;
/* 115 */     else if ((value instanceof Float))
/* 116 */       result = true;
/* 117 */     else if ((value instanceof BigDecimal)) {
/* 118 */       result = true;
/*     */     }
/* 120 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean isNumericString(Object obj, boolean baReturnValueIfNull)
/*     */   {
/* 125 */     if (isNullorEmpty(obj)) {
/* 126 */       return baReturnValueIfNull;
/*     */     }
/* 128 */     boolean bResult = true;
/* 129 */     String str = (obj instanceof String) ? (String)obj : obj.toString();
/* 130 */     int intLength = str.length();
/* 131 */     for (int i = 0; (bResult) && (i < intLength); i++) {
/* 132 */       char c = str.charAt(i);
/* 133 */       bResult = Character.isDigit(c);
/*     */     }
/* 135 */     return bResult;
/*     */   }
/*     */ 
/*     */   public static String getCurrentDateTime(String strFormat)
/*     */   {
/* 145 */     Timestamp ts = new Timestamp(System.currentTimeMillis());
/* 146 */     return SyncSimpleDateFormat.format(strFormat, ts);
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date, String format, Locale locale) {
/* 150 */     if (date == null) {
/* 151 */       return "";
/*     */     }
/* 153 */     if (format == null) {
/* 154 */       format = "yyyy-MM-dd";
/*     */     }
/*     */ 
/* 157 */     SimpleDateFormat df = new SimpleDateFormat(format, locale);
/* 158 */     return df.format(date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date format(java.util.Date date) {
/* 162 */     String defaultFormatString = "yyyy-MM-dd";
/* 163 */     return format(date, defaultFormatString);
/*     */   }
/*     */ 
/*     */   public static java.util.Date format(java.util.Date date, String format) {
/* 167 */     return formatDate(formatDate(date, format));
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date) {
/* 171 */     String defaultFormatString = "yyyy-MM-dd";
/* 172 */     return formatDate(date, defaultFormatString);
/*     */   }
/*     */ 
/*     */   public static java.util.Date formatDate(String data) {
/* 176 */     String defaultFormatString = "yyyy-MM-dd";
/* 177 */     return formatDate(data, defaultFormatString);
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date, String format) {
/* 181 */     if (date == null) {
/* 182 */       return "";
/*     */     }
/* 184 */     if (format == null) {
/* 185 */       format = "yyyy-MM-dd";
/*     */     }
/* 187 */     return SyncSimpleDateFormat.format(format, date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date formatDate(String data, String format)
/*     */   {
/* 192 */     if (data == null) {
/* 193 */       return null;
/*     */     }
/* 195 */     if (format == null) {
/* 196 */       format = "yyyy-MM-dd";
/*     */     }
/* 198 */     java.util.Date dt = null;
/*     */     try {
/* 200 */       dt = SyncSimpleDateFormat.parse(format, data);
/*     */     } catch (Exception e) {
/* 202 */       e.printStackTrace();
/*     */     }
/* 204 */     return dt;
/*     */   }
/*     */ 
/*     */   public static java.util.Date getMaxDate(java.util.Date dt1, java.util.Date dt2)
/*     */   {
/* 217 */     if (dt1 == null)
/* 218 */       return dt2;
/* 219 */     if (dt2 != null) {
/* 220 */       return getDateDiff(dt1, dt2) > 0 ? dt2 : dt1;
/*     */     }
/* 222 */     return null;
/*     */   }
/*     */ 
/*     */   public static java.util.Date getMinDate(java.util.Date dt1, java.util.Date dt2)
/*     */   {
/* 235 */     if (dt1 == null)
/* 236 */       return dt2;
/* 237 */     if (dt2 != null) {
/* 238 */       return getDateDiff(dt1, dt2) < 0 ? dt2 : dt1;
/*     */     }
/* 240 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getDateDiff(java.util.Date dtStartDate, java.util.Date dtEndDate)
/*     */   {
/* 252 */     long StartTime = dtStartDate.getTime();
/*     */ 
/* 254 */     long EndTime = dtEndDate.getTime();
/*     */ 
/* 259 */     int StartDay = Integer.parseInt(
/* 260 */       String.valueOf((StartTime - StartTime % 86400000L) / 86400000L));
/*     */ 
/* 266 */     int EndDay = Integer.parseInt(
/* 267 */       String.valueOf((EndTime - EndTime % 86400000L) / 86400000L));
/*     */ 
/* 273 */     int difference = EndDay - StartDay;
/*     */ 
/* 276 */     return difference;
/*     */   }
/*     */ 
/*     */   public static int getDayDiff(java.util.Date dtStartDate, java.util.Date dtEndDate) throws Exception {
/* 280 */     java.util.Date dStartDate = format(dtStartDate);
/* 281 */     java.util.Date dEndDate = format(dtEndDate);
/* 282 */     return Long.valueOf((dEndDate.getTime() - dStartDate.getTime()) / 86400000L).intValue();
/*     */   }
/*     */ 
/*     */   public static boolean isValidDate(String straDate, String straFormat) throws Exception
/*     */   {
/* 287 */     java.util.Date dtValidDate = null;
/*     */     try {
/* 289 */       dtValidDate = SyncSimpleDateFormat.parse(straFormat, straDate, false, 0);
/* 290 */       if (dtValidDate == null) {
/* 291 */         return false;
/*     */       }
/* 293 */       return true; } catch (Exception ex) {
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */   public static java.util.Date addDate(java.util.Date dtStartDate, int intField, int intNum)
/*     */   {
/* 300 */     Calendar cal = Calendar.getInstance();
/* 301 */     cal.setTime(dtStartDate);
/* 302 */     cal.add(intField, intNum);
/* 303 */     java.util.Date dt = new java.util.Date(cal.getTime().getTime());
/* 304 */     return dt;
/*     */   }
/*     */ 
/*     */   public static int returnHour(Timestamp straDate) {
/* 308 */     Calendar cal = Calendar.getInstance();
/* 309 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 310 */     return cal.get(10);
/*     */   }
/*     */ 
/*     */   public static int returnMin(Timestamp straDate) {
/* 314 */     Calendar cal = Calendar.getInstance();
/* 315 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 316 */     return cal.get(12);
/*     */   }
/*     */ 
/*     */   public static int returnHourOfDay(Timestamp straDate) {
/* 320 */     Calendar cal = Calendar.getInstance();
/* 321 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 322 */     return cal.get(11);
/*     */   }
/*     */ 
/*     */   public static int returnAmPm(Timestamp straDate) {
/* 326 */     Calendar cal = Calendar.getInstance();
/* 327 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 328 */     return cal.get(9);
/*     */   }
/*     */ 
/*     */   public static int returnYear(java.util.Date straDate) {
/* 332 */     Calendar cal = Calendar.getInstance();
/* 333 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 334 */     return cal.get(1);
/*     */   }
/*     */ 
/*     */   public static int returnMonth(java.util.Date straDate) {
/* 338 */     Calendar cal = Calendar.getInstance();
/* 339 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 340 */     return cal.get(2) + 1;
/*     */   }
/*     */ 
/*     */   public static int returnDate(java.util.Date straDate) {
/* 344 */     Calendar cal = Calendar.getInstance();
/* 345 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 346 */     return cal.get(5);
/*     */   }
/*     */ 
/*     */   public static int returnDayOfMonth(java.util.Date straDate) {
/* 350 */     Calendar cal = Calendar.getInstance();
/* 351 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 352 */     return cal.get(5);
/*     */   }
/*     */ 
/*     */   public static String returnStirng(Object obj) {
/* 356 */     if (obj == null) {
/* 357 */       return "";
/*     */     }
/* 359 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public static String trim(String str)
/*     */   {
/* 364 */     if (str == null) {
/* 365 */       return null;
/*     */     }
/* 367 */     return str.trim();
/*     */   }
/*     */ 
/*     */   public static Integer currentYear()
/*     */   {
/* 377 */     Integer currentYear = Integer.valueOf(Calendar.getInstance().get(1));
/* 378 */     return currentYear;
/*     */   }
/*     */ 
/*     */   public static List<Integer> getThisAndNextYearList()
/*     */   {
/* 388 */     Integer currentYear = currentYear();
/* 389 */     List yearList = new ArrayList();
/* 390 */     yearList.add(currentYear);
/* 391 */     yearList.add(Integer.valueOf(currentYear.intValue() + 1));
/* 392 */     return yearList;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.common.GlobalMethod
 * JD-Core Version:    0.6.2
 */