/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.math.BigDecimal;
/*     */ import java.sql.Timestamp;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public final class GlobalMethod
/*     */ {
/*     */   private static final String strcE = "E";
/*     */ 
/*     */   public static String removeLeadingChar(String strSource, char leadChar)
/*     */   {
/*  22 */     if ((strSource != null) && 
/*  23 */       (strSource.startsWith(String.valueOf(leadChar)))) {
/*  24 */       int length = strSource.length();
/*  25 */       for (int i = 0; i < length; i++) {
/*  26 */         if (strSource.charAt(i) != leadChar) {
/*  27 */           return strSource.substring(i, length);
/*     */         }
/*     */       }
/*  30 */       return "";
/*     */     }
/*     */ 
/*  33 */     return strSource;
/*     */   }
/*     */ 
/*     */   public static String removeLeadingZero(String strSource) {
/*  37 */     return removeLeadingChar(strSource, '0');
/*     */   }
/*     */ 
/*     */   public static String addLeadingZero(int source, int intTotalCount) {
/*  41 */     String strSource = String.valueOf(source);
/*  42 */     return addLeadingZero(strSource, intTotalCount);
/*     */   }
/*     */ 
/*     */   public static String addLeadingZero(String strSource, int intTotalCount) {
/*  46 */     int intCount = intTotalCount - strSource.length();
/*  47 */     String strFormat = strSource;
/*  48 */     for (int i = 0; i < intCount; i++) {
/*  49 */       strFormat = "0" + strFormat;
/*     */     }
/*  51 */     return strFormat;
/*     */   }
/*     */ 
/*     */   public static boolean isNull(Object objValue)
/*     */   {
/*  62 */     return objValue == null;
/*     */   }
/*     */ 
/*     */   public static boolean isNullorEmpty(Object objValue)
/*     */   {
/*  74 */     if (objValue == null)
/*  75 */       return true;
/*  76 */     if ((objValue instanceof String))
/*  77 */       return ((String)objValue).length() == 0;
/*  78 */     if ((objValue instanceof List))
/*  79 */       return ((List)objValue).isEmpty();
/*  80 */     if ((objValue instanceof Map))
/*  81 */       return ((Map)objValue).isEmpty();
/*  82 */     if ((objValue instanceof Set)) {
/*  83 */       return ((Set)objValue).isEmpty();
/*     */     }
/*  85 */     return objValue.toString().length() == 0;
/*     */   }
/*     */ 
/*     */   public static boolean isNumericType(Object value)
/*     */   {
/*  96 */     boolean result = false;
/*  97 */     if ((value instanceof Integer))
/*  98 */       result = true;
/*  99 */     else if ((value instanceof Long))
/* 100 */       result = true;
/* 101 */     else if ((value instanceof Double))
/* 102 */       result = true;
/* 103 */     else if ((value instanceof Float))
/* 104 */       result = true;
/* 105 */     else if ((value instanceof BigDecimal)) {
/* 106 */       result = true;
/*     */     }
/* 108 */     return result;
/*     */   }
/*     */ 
/*     */   public static boolean isNumericString(Object obj, boolean baReturnValueIfNull)
/*     */   {
/* 113 */     if (isNullorEmpty(obj)) {
/* 114 */       return baReturnValueIfNull;
/*     */     }
/* 116 */     boolean bResult = true;
/* 117 */     String str = (obj instanceof String) ? (String)obj : obj.toString();
/* 118 */     int intLength = str.length();
/* 119 */     for (int i = 0; (bResult) && (i < intLength); i++) {
/* 120 */       char c = str.charAt(i);
/* 121 */       bResult = Character.isDigit(c);
/*     */     }
/* 123 */     return bResult;
/*     */   }
/*     */ 
/*     */   public static String getCurrentDateTime(String strFormat)
/*     */   {
/* 133 */     Timestamp ts = new Timestamp(System.currentTimeMillis());
/* 134 */     return SyncSimpleDateFormat.format(strFormat, ts);
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date, String format, Locale locale) {
/* 138 */     if (date == null) {
/* 139 */       return "";
/*     */     }
/* 141 */     if (format == null) {
/* 142 */       format = "yyyy-MM-dd";
/*     */     }
/*     */ 
/* 145 */     SimpleDateFormat df = new SimpleDateFormat(format, locale);
/* 146 */     return df.format(date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date format(java.util.Date date) {
/* 150 */     String defaultFormatString = "yyyy-MM-dd";
/* 151 */     return format(date, defaultFormatString);
/*     */   }
/*     */ 
/*     */   public static java.util.Date format(java.util.Date date, String format) {
/* 155 */     return formatDate(formatDate(date, format));
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date) {
/* 159 */     String defaultFormatString = "yyyy-MM-dd";
/* 160 */     return formatDate(date, defaultFormatString);
/*     */   }
/*     */ 
/*     */   public static java.util.Date formatDate(String data) {
/* 164 */     String defaultFormatString = "yyyy-MM-dd";
/* 165 */     return formatDate(data, defaultFormatString);
/*     */   }
/*     */ 
/*     */   public static String formatDate(java.util.Date date, String format) {
/* 169 */     if (date == null) {
/* 170 */       return "";
/*     */     }
/* 172 */     if (format == null) {
/* 173 */       format = "yyyy-MM-dd";
/*     */     }
/* 175 */     return SyncSimpleDateFormat.format(format, date);
/*     */   }
/*     */ 
/*     */   public static java.util.Date formatDate(String data, String format)
/*     */   {
/* 180 */     if (data == null) {
/* 181 */       return null;
/*     */     }
/* 183 */     if (format == null) {
/* 184 */       format = "yyyy-MM-dd";
/*     */     }
/* 186 */     java.util.Date dt = null;
/*     */     try {
/* 188 */       dt = SyncSimpleDateFormat.parse(format, data);
/*     */     } catch (Exception e) {
/* 190 */       e.printStackTrace();
/*     */     }
/* 192 */     return dt;
/*     */   }
/*     */ 
/*     */   public static String formatNumber(int intNumber, String format) {
/* 196 */     Number number = new Integer(intNumber);
/* 197 */     return formatNumber(number, format);
/*     */   }
/*     */ 
/*     */   public static String formatNumber(Number number, String format) {
/* 201 */     if (number == null)
/* 202 */       return "";
/* 203 */     if (format == null)
/* 204 */       format = "#";
/* 205 */     String result = SyncDecimalFormat.format(format, number);
/*     */     try {
/* 207 */       if ((result.startsWith("-")) && 
/* 208 */         (SyncDecimalFormat.parse(format, result).doubleValue() == 0.0D))
/* 209 */         result = result.substring(1);
/*     */     }
/*     */     catch (Exception e) {
/* 212 */       e.printStackTrace();
/*     */     }
/*     */ 
/* 215 */     return result;
/*     */   }
/*     */ 
/*     */   public static String convdoubleToString(double dblValue)
/*     */   {
/* 229 */     String strTemp = String.valueOf(dblValue);
/*     */ 
/* 231 */     if (strTemp.indexOf("E") == -1) {
/* 232 */       return strTemp;
/*     */     }
/* 234 */     return new BigDecimal(dblValue).toString();
/*     */   }
/*     */ 
/*     */   public static String convBigDecToString(BigDecimal bdaValue)
/*     */   {
/* 247 */     return round(bdaValue, 10).toPlainString();
/*     */   }
/*     */ 
/*     */   public static double ceil(double dblNum, int intDecPlace)
/*     */   {
/* 260 */     BigDecimal bdTemp1 = new BigDecimal(convdoubleToString(dblNum));
/*     */ 
/* 262 */     BigDecimal bdTemp = bdTemp1.multiply(new BigDecimal(Math.pow(10.0D, 
/* 263 */       intDecPlace)));
/* 264 */     bdTemp = bdTemp.setScale(0, 2);
/* 265 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, intDecPlace)), 
/* 266 */       intDecPlace, 4);
/* 267 */     double dblTemp = bdTemp.doubleValue();
/* 268 */     return dblTemp;
/*     */   }
/*     */ 
/*     */   public static double abs(double dblNum)
/*     */   {
/* 281 */     BigDecimal bdTemp = new BigDecimal(dblNum * Math.pow(10.0D, 8.0D));
/* 282 */     bdTemp = bdTemp.setScale(0, 4);
/* 283 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, 8.0D)), 8, 
/* 284 */       4);
/* 285 */     double dblTemp = bdTemp.doubleValue();
/* 286 */     return dblTemp;
/*     */   }
/*     */ 
/*     */   public static double floor(double dblNum, int intDecPlace)
/*     */   {
/* 299 */     BigDecimal bdTemp1 = new BigDecimal(convdoubleToString(dblNum));
/*     */ 
/* 301 */     BigDecimal bdTemp = bdTemp1.multiply(new BigDecimal(Math.pow(10.0D, 
/* 302 */       intDecPlace)));
/*     */ 
/* 310 */     bdTemp = bdTemp.setScale(0, 3);
/*     */ 
/* 312 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, intDecPlace)), 
/* 313 */       intDecPlace, 4);
/*     */ 
/* 315 */     double dblTemp = bdTemp.doubleValue();
/* 316 */     return dblTemp;
/*     */   }
/*     */ 
/*     */   public static double round(double dblNum, int intDecPlace)
/*     */   {
/* 328 */     BigDecimal bdTemp1 = new BigDecimal(convdoubleToString(dblNum));
/*     */ 
/* 330 */     BigDecimal bdTemp = bdTemp1.multiply(new BigDecimal(Math.pow(10.0D, 
/* 331 */       intDecPlace)));
/* 332 */     bdTemp = bdTemp.setScale(0, 4);
/* 333 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, intDecPlace)), 8, 
/* 334 */       4);
/* 335 */     double dblTemp = bdTemp.doubleValue();
/* 336 */     return dblTemp;
/*     */   }
/*     */ 
/*     */   public static BigDecimal ceil(BigDecimal bdaNum, int intDecPlace)
/*     */   {
/* 352 */     BigDecimal bdTemp = bdaNum.multiply(new BigDecimal(Math.pow(10.0D, 
/* 353 */       intDecPlace)));
/* 354 */     bdTemp = bdTemp.setScale(0, 2);
/* 355 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, intDecPlace)), 
/* 356 */       intDecPlace, 4);
/* 357 */     return bdTemp;
/*     */   }
/*     */ 
/*     */   public static BigDecimal floor(BigDecimal bdaNum, int intDecPlace)
/*     */   {
/* 372 */     BigDecimal bdTemp = bdaNum.multiply(new BigDecimal(Math.pow(10.0D, 
/* 373 */       intDecPlace)));
/*     */ 
/* 375 */     bdTemp = bdTemp.setScale(0, 3);
/* 376 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, intDecPlace)), 
/* 377 */       intDecPlace, 4);
/*     */ 
/* 379 */     return bdTemp;
/*     */   }
/*     */ 
/*     */   public static BigDecimal round(BigDecimal bdaNum, int intDecPlace)
/*     */   {
/* 393 */     BigDecimal bdTemp = bdaNum.multiply(new BigDecimal(Math.pow(10.0D, 
/* 394 */       intDecPlace)));
/* 395 */     bdTemp = bdTemp.setScale(0, 4);
/* 396 */     bdTemp = bdTemp.divide(new BigDecimal(Math.pow(10.0D, intDecPlace)), 8, 
/* 397 */       4);
/*     */ 
/* 399 */     return bdTemp;
/*     */   }
/*     */ 
/*     */   public static java.util.Date getMaxDate(java.util.Date dt1, java.util.Date dt2)
/*     */   {
/* 412 */     if (dt1 == null)
/* 413 */       return dt2;
/* 414 */     if (dt2 != null) {
/* 415 */       return getDateDiff(dt1, dt2) > 0 ? dt2 : dt1;
/*     */     }
/* 417 */     return null;
/*     */   }
/*     */ 
/*     */   public static java.util.Date getMinDate(java.util.Date dt1, java.util.Date dt2)
/*     */   {
/* 430 */     if (dt1 == null)
/* 431 */       return dt2;
/* 432 */     if (dt2 != null) {
/* 433 */       return getDateDiff(dt1, dt2) < 0 ? dt2 : dt1;
/*     */     }
/* 435 */     return null;
/*     */   }
/*     */ 
/*     */   public static int getDateDiff(java.util.Date dtStartDate, java.util.Date dtEndDate)
/*     */   {
/* 447 */     long StartTime = dtStartDate.getTime();
/*     */ 
/* 449 */     long EndTime = dtEndDate.getTime();
/*     */ 
/* 454 */     int StartDay = Integer.parseInt(
/* 455 */       String.valueOf((StartTime - StartTime % 86400000L) / 86400000L));
/*     */ 
/* 461 */     int EndDay = Integer.parseInt(
/* 462 */       String.valueOf((EndTime - EndTime % 86400000L) / 86400000L));
/*     */ 
/* 468 */     int difference = EndDay - StartDay;
/*     */ 
/* 471 */     return difference;
/*     */   }
/*     */ 
/*     */   public static int getDayDiff(java.util.Date dtStartDate, java.util.Date dtEndDate) {
/* 475 */     java.util.Date dStartDate = format(dtStartDate);
/* 476 */     java.util.Date dEndDate = format(dtEndDate);
/* 477 */     return Long.valueOf((dEndDate.getTime() - dStartDate.getTime()) / 86400000L)
/* 478 */       .intValue();
/*     */   }
/*     */ 
/*     */   public static boolean isValidDate(String straDate, String straFormat)
/*     */     throws Exception
/*     */   {
/* 484 */     java.util.Date dtValidDate = null;
/*     */     try {
/* 486 */       dtValidDate = SyncSimpleDateFormat.parse(straFormat, straDate, 
/* 487 */         false, 0);
/* 488 */       if (dtValidDate == null) {
/* 489 */         return false;
/*     */       }
/* 491 */       return true; } catch (Exception ex) {
/*     */     }
/* 493 */     return false;
/*     */   }
/*     */ 
/*     */   public static java.util.Date addDate(java.util.Date dtStartDate, int intField, int intNum)
/*     */   {
/* 498 */     Calendar cal = Calendar.getInstance();
/* 499 */     cal.setTime(dtStartDate);
/* 500 */     cal.add(intField, intNum);
/* 501 */     java.util.Date dt = new java.util.Date(cal.getTime().getTime());
/* 502 */     return dt;
/*     */   }
/*     */ 
/*     */   public static int returnHour(Timestamp straDate) {
/* 506 */     Calendar cal = Calendar.getInstance();
/* 507 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 508 */     return cal.get(10);
/*     */   }
/*     */ 
/*     */   public static int returnMin(Timestamp straDate) {
/* 512 */     Calendar cal = Calendar.getInstance();
/* 513 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 514 */     return cal.get(12);
/*     */   }
/*     */ 
/*     */   public static int returnHourOfDay(Timestamp straDate) {
/* 518 */     Calendar cal = Calendar.getInstance();
/* 519 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 520 */     return cal.get(11);
/*     */   }
/*     */ 
/*     */   public static int returnAmPm(Timestamp straDate) {
/* 524 */     Calendar cal = Calendar.getInstance();
/* 525 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 526 */     return cal.get(9);
/*     */   }
/*     */ 
/*     */   public static int returnYear(java.util.Date straDate) {
/* 530 */     Calendar cal = Calendar.getInstance();
/* 531 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 532 */     return cal.get(1);
/*     */   }
/*     */ 
/*     */   public static int returnMonth(java.util.Date straDate) {
/* 536 */     Calendar cal = Calendar.getInstance();
/* 537 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 538 */     return cal.get(2) + 1;
/*     */   }
/*     */ 
/*     */   public static int returnDate(java.util.Date straDate) {
/* 542 */     Calendar cal = Calendar.getInstance();
/* 543 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 544 */     return cal.get(5);
/*     */   }
/*     */ 
/*     */   public static int returnDayOfMonth(java.util.Date straDate) {
/* 548 */     Calendar cal = Calendar.getInstance();
/* 549 */     cal.setTime(new java.sql.Date(straDate.getTime()));
/* 550 */     return cal.get(5);
/*     */   }
/*     */ 
/*     */   public static String returnString(Object obj) {
/* 554 */     if (obj == null) {
/* 555 */       return "";
/*     */     }
/* 557 */     return obj.toString();
/*     */   }
/*     */ 
/*     */   public static Object returnObject(Object obj)
/*     */   {
/* 563 */     if ((obj instanceof Double)) {
/* 564 */       Double d = (Double)obj;
/* 565 */       DecimalFormat df = new DecimalFormat("###0.00#");
/* 566 */       return df.format(d);
/*     */     }
/* 568 */     return obj == null ? "" : obj;
/*     */   }
/*     */ 
/*     */   public static String trim(String str) {
/* 572 */     if (str == null) {
/* 573 */       return null;
/*     */     }
/* 575 */     return str.trim();
/*     */   }
/*     */ 
/*     */   public static Integer currentYear()
/*     */   {
/* 585 */     Integer currentYear = Integer.valueOf(Calendar.getInstance().get(1));
/* 586 */     return currentYear;
/*     */   }
/*     */ 
/*     */   public static List<Integer> getThisAndNextYearList()
/*     */   {
/* 596 */     Integer currentYear = currentYear();
/* 597 */     List yearList = new ArrayList();
/* 598 */     yearList.add(currentYear);
/* 599 */     yearList.add(Integer.valueOf(currentYear.intValue() + 1));
/* 600 */     return yearList;
/*     */   }
/*     */ 
/*     */   public static String getFiledAttribute(String filedName)
/*     */   {
/* 610 */     StringTokenizer st = new StringTokenizer(filedName, 
/* 611 */       "_");
/* 612 */     boolean isFirst = true;
/* 613 */     String filedAttr = "";
/* 614 */     while (st.hasMoreElements()) {
/* 615 */       String filedStr = st.nextToken().toLowerCase();
/* 616 */       if (isFirst) {
/* 617 */         filedAttr = filedAttr + (filedStr.length() == 1 ? filedStr.toUpperCase() : 
/* 618 */           filedStr);
/* 619 */         isFirst = false;
/*     */       } else {
/* 621 */         filedAttr = filedAttr + StringUtils.capitalize(filedStr);
/*     */       }
/*     */     }
/* 624 */     return filedAttr;
/*     */   }
/*     */ 
/*     */   public static String getReqXMLValue(String xml, String key)
/*     */   {
/* 634 */     String bg = "<" + key + ">";
/* 635 */     String ed = "</" + key + ">";
/* 636 */     if (xml.indexOf(bg) > -1) {
/* 637 */       return xml
/* 638 */         .substring(xml.indexOf(bg) + bg.length(), xml.indexOf(ed))
/* 639 */         .trim();
/*     */     }
/* 641 */     return "";
/*     */   }
/*     */ 
/*     */   public static String getFiledMethod(String columnName) {
/* 645 */     StringTokenizer st = new StringTokenizer(columnName, 
/* 646 */       "_");
/* 647 */     String methodName = "get";
/* 648 */     while (st.hasMoreElements()) {
/* 649 */       String filedStr = st.nextToken();
/* 650 */       methodName = methodName + StringUtils.capitalize(filedStr.toLowerCase());
/*     */     }
/* 652 */     return methodName;
/*     */   }
/*     */ 
/*     */   public static String getFiled(Object obj, String columnName, String fieldType)
/*     */   {
/* 657 */     if (obj == null) {
/* 658 */       return "";
/*     */     }
/* 660 */     if ("ITEM_SEX".equals(columnName)) {
/* 661 */       columnName = "ITEM_SEX_NAME";
/*     */     }
/* 663 */     String methodName = getFiledMethod(columnName);
/* 664 */     Class clazz = obj.getClass();
/*     */     try {
/* 666 */       Method m = clazz.getDeclaredMethod(methodName, new Class[0]);
/* 667 */       Object returnValue = m.invoke(obj, new Object[0]);
/*     */ 
/* 669 */       if (returnValue == null) {
/* 670 */         return "";
/*     */       }
/*     */ 
/* 673 */       if (("DATE".equals(fieldType)) && 
/* 674 */         ((returnValue instanceof java.util.Date))) {
/* 675 */         return formatDate((java.util.Date)returnValue, 
/* 676 */           "yyyy-MM-dd");
/*     */       }
/*     */ 
/* 679 */       if (("DATETIME".equals(fieldType)) && 
/* 680 */         ((returnValue instanceof java.util.Date))) {
/* 681 */         return formatDate((java.util.Date)returnValue, 
/* 682 */           "yyyy-MM-dd HH:mm:ss");
/*     */       }
/*     */ 
/* 685 */       if ((("NUM".equals(fieldType)) || 
/* 686 */         ("FLOAT"
/* 686 */         .equals(fieldType))) && ((returnValue instanceof Number))) {
/* 687 */         return SyncDecimalFormat.format("#0.##", 
/* 688 */           returnValue);
/*     */       }
/* 690 */       return returnValue.toString();
/*     */     } catch (Exception e) {
/* 692 */       e.printStackTrace();
/* 693 */     }return "";
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.GlobalMethod
 * JD-Core Version:    0.6.2
 */