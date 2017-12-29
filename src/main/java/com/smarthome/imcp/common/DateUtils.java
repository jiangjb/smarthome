/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ public class DateUtils
/*     */ {
/*  13 */   private static Logger logger = Logger.getLogger(DateUtils.class);
/*     */ 
/*  15 */   public static String dateStr(Date date) { SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm");
/*  16 */     String str = format.format(date);
/*  17 */     return str; }
/*     */ 
/*     */   public static String dateStr(Date date, String f)
/*     */   {
/*  21 */     if (date == null) {
/*  22 */       return "";
/*     */     }
/*  24 */     SimpleDateFormat format = new SimpleDateFormat(f);
/*  25 */     String str = format.format(date);
/*  26 */     return str;
/*     */   }
/*     */ 
/*     */   public static String dateStr2(Date date) {
/*  30 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
/*  31 */     String str = format.format(date);
/*  32 */     return str;
/*     */   }
/*     */ 
/*     */   public static String dateStr3(Date date) {
/*  36 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
/*  37 */     String str = format.format(date);
/*  38 */     return str;
/*     */   }
/*     */ 
/*     */   public static String dateStr5(Date date) {
/*  42 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/*  43 */     String str = format.format(date);
/*  44 */     return str;
/*     */   }
/*     */ 
/*     */   public static String dateStr6(Date date) {
/*  48 */     SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
/*  49 */     String str = format.format(date);
/*  50 */     return str;
/*     */   }
/*     */ 
/*     */   public static String dateStr4(Date date) {
/*  54 */     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*  55 */     String str = format.format(date);
/*  56 */     return str;
/*     */   }
/*     */ 
/*     */   public static String dateStr7(Date date)
/*     */   {
/*  61 */     SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
/*  62 */     String str = format.format(date);
/*  63 */     return str;
/*     */   }
/*     */ 
/*     */   public static Date getDate(String time, String formatStr)
/*     */   {
/*  68 */     SimpleDateFormat format = new SimpleDateFormat(formatStr);
/*     */     try {
/*  70 */       return format.parse(time);
/*     */     } catch (ParseException e) {
/*  72 */       logger.error(e);
/*     */     }
/*  74 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date getDate(Date date, String formatStr)
/*     */   {
/*  79 */     SimpleDateFormat format = new SimpleDateFormat(formatStr);
/*  80 */     String dateStr = format.format(date);
/*     */     try {
/*  82 */       return format.parse(dateStr);
/*     */     } catch (ParseException e) {
/*  84 */       logger.error(e);
/*     */     }
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date getDate(String times)
/*     */   {
/*  95 */     long time = Long.parseLong(times);
/*  96 */     return new Date(time * 1000L);
/*     */   }
/*     */ 
/*     */   public static String dateStr(String times) {
/* 100 */     return dateStr(getDate(times));
/*     */   }
/*     */   public static String dateStr2(String times) {
/* 103 */     return dateStr2(getDate(times));
/*     */   }
/*     */   public static String dateStr3(String times) {
/* 106 */     return dateStr3(getDate(times));
/*     */   }
/*     */   public static String dateStr4(String times) {
/* 109 */     return dateStr4(getDate(times));
/*     */   }
/*     */   public static long getTime(Date date) {
/* 112 */     return date.getTime() / 1000L;
/*     */   }
/*     */ 
/*     */   public static int getDay(Date d) {
/* 116 */     Calendar cal = Calendar.getInstance();
/* 117 */     cal.setTime(d);
/* 118 */     return cal.get(5);
/*     */   }
/*     */ 
/*     */   public static Date valueOf(String s)
/*     */   {
/* 127 */     int YEAR_LENGTH = 4;
/* 128 */     int MONTH_LENGTH = 2;
/* 129 */     int DAY_LENGTH = 2;
/* 130 */     int MAX_MONTH = 12;
/* 131 */     int MAX_DAY = 31;
/*     */ 
/* 134 */     Date d = null;
/*     */ 
/* 136 */     if (s == null) {
/* 137 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/* 140 */     int firstDash = s.indexOf('-');
/* 141 */     int secondDash = s.indexOf('-', firstDash + 1);
/* 142 */     if ((firstDash > 0) && (secondDash > 0) && (secondDash < s.length() - 1)) {
/* 143 */       String yyyy = s.substring(0, firstDash);
/* 144 */       String mm = s.substring(firstDash + 1, secondDash);
/* 145 */       String dd = s.substring(secondDash + 1);
/* 146 */       if ((yyyy.length() == 4) && (mm.length() == 2) && 
/* 147 */         (dd.length() == 2)) {
/* 148 */         int year = Integer.parseInt(yyyy);
/* 149 */         int month = Integer.parseInt(mm);
/* 150 */         int day = Integer.parseInt(dd);
/* 151 */         if ((month >= 1) && (month <= 12)) {
/* 152 */           int maxDays = 31;
/* 153 */           switch (month)
/*     */           {
/*     */           case 2:
/* 156 */             if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
/* 157 */               maxDays = 29;
/*     */             else {
/* 159 */               maxDays = 28;
/*     */             }
/* 161 */             break;
/*     */           case 4:
/*     */           case 6:
/*     */           case 9:
/*     */           case 11:
/* 167 */             maxDays = 30;
/*     */           case 3:
/*     */           case 5:
/*     */           case 7:
/*     */           case 8:
/* 170 */           case 10: } if ((day >= 1) && (day <= maxDays)) {
/* 171 */             Calendar cal = Calendar.getInstance();
/* 172 */             cal.set(year, month - 1, day, 0, 0, 0);
/* 173 */             cal.set(14, 0);
/* 174 */             d = cal.getTime();
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 179 */     if (d == null) {
/* 180 */       throw new IllegalArgumentException();
/*     */     }
/* 182 */     return d;
/*     */   }
/*     */ 
/*     */   public static Map getApartTime(String Begin, String end)
/*     */   {
/* 198 */     String[] temp = Begin.split("-");
/* 199 */     String[] temp2 = end.split("-");
/* 200 */     if ((temp.length > 1) && (temp2.length > 1)) {
/* 201 */       Calendar ends = Calendar.getInstance();
/* 202 */       Calendar begin = Calendar.getInstance();
/*     */ 
/* 204 */       begin.set(NumberUtils.getInt(temp[0]), 
/* 205 */         NumberUtils.getInt(temp[1]), NumberUtils.getInt(temp[2]));
/* 206 */       ends.set(NumberUtils.getInt(temp2[0]), 
/* 207 */         NumberUtils.getInt(temp2[1]), NumberUtils.getInt(temp2[2]));
/* 208 */       if (begin.compareTo(ends) < 0) {
/* 209 */         Map map = new HashMap();
/* 210 */         ends.add(1, -NumberUtils.getInt(temp[0]));
/* 211 */         ends.add(2, -NumberUtils.getInt(temp[1]));
/* 212 */         ends.add(5, -NumberUtils.getInt(temp[2]));
/* 213 */         map.put("YEAR", Integer.valueOf(ends.get(1)));
/* 214 */         map.put("MONTH", Integer.valueOf(ends.get(2) + 1));
/* 215 */         map.put("DAY", Integer.valueOf(ends.get(5)));
/* 216 */         return map;
/*     */       }
/*     */     }
/* 219 */     return null;
/*     */   }
/*     */ 
/*     */   public static Date rollDay(Date d, int day) {
/* 223 */     Calendar cal = Calendar.getInstance();
/* 224 */     cal.setTime(d);
/* 225 */     cal.add(5, day);
/* 226 */     return cal.getTime();
/*     */   }
/*     */   public static Date rollMon(Date d, int mon) {
/* 229 */     Calendar cal = Calendar.getInstance();
/* 230 */     cal.setTime(d);
/* 231 */     cal.add(2, mon);
/* 232 */     return cal.getTime();
/*     */   }
/*     */   public static Date rollYear(Date d, int year) {
/* 235 */     Calendar cal = Calendar.getInstance();
/* 236 */     cal.setTime(d);
/* 237 */     cal.add(1, year);
/* 238 */     return cal.getTime();
/*     */   }
/*     */   public static Date rollDate(Date d, int year, int mon, int day) {
/* 241 */     Calendar cal = Calendar.getInstance();
/* 242 */     cal.setTime(d);
/* 243 */     cal.add(1, year);
/* 244 */     cal.add(2, mon);
/* 245 */     cal.add(5, day);
/* 246 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static int getYear(Date date)
/*     */   {
/* 255 */     Calendar cal = Calendar.getInstance();
/* 256 */     cal.setTime(date);
/* 257 */     return cal.get(1);
/*     */   }
/*     */ 
/*     */   public static int getMonth(Date date)
/*     */   {
/* 266 */     Calendar cal = Calendar.getInstance();
/* 267 */     cal.setTime(date);
/* 268 */     return cal.get(2) + 1;
/*     */   }
/*     */ 
/*     */   public static String getNowTimeStr()
/*     */   {
/* 276 */     String str = Long.toString(System.currentTimeMillis() / 1000L);
/* 277 */     return str;
/*     */   }
/*     */   public static String getTimeStr(Date time) {
/* 280 */     long date = time.getTime();
/* 281 */     String str = Long.toString(date / 1000L);
/* 282 */     return str;
/*     */   }
///*     */   public static String rollMonth(String addtime, String time_limit) {
			public static long rollMonth(String addtime, String time_limit) {
/* 285 */     Date t = rollDate(getDate(addtime), 0, NumberUtils.getInt(time_limit), 0);
/* 286 */     return t.getTime() / 1000L;
/*     */   }
/*     */ 
///*     */   public static String rollDay(String addtime, String time_limit_day) {
			public static long rollDay(String addtime, String time_limit_day) {
/* 290 */     Date t = rollDate(getDate(addtime), 0, 0, NumberUtils.getInt(time_limit_day));
/* 291 */     return t.getTime() / 1000L;
/*     */   }
/*     */ 
/*     */   public static Date getIntegralTime() {
/* 295 */     Calendar cal = Calendar.getInstance();
/* 296 */     cal.set(11, 0);
/* 297 */     cal.set(13, 0);
/* 298 */     cal.set(12, 0);
/* 299 */     cal.set(14, 0);
/* 300 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getIntegralTime(Date d) {
/* 304 */     Calendar cal = Calendar.getInstance();
/* 305 */     cal.setTimeInMillis(d.getTime());
/* 306 */     cal.set(11, 0);
/* 307 */     cal.set(13, 0);
/* 308 */     cal.set(12, 0);
/* 309 */     cal.set(14, 0);
/* 310 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getDirectedTime(int hour, int minute, int second) {
/* 314 */     Calendar cal = Calendar.getInstance();
/* 315 */     cal.set(11, hour);
/* 316 */     cal.set(12, minute);
/* 317 */     cal.set(13, second);
/* 318 */     cal.set(14, 0);
/* 319 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getLastIntegralTime() {
/* 323 */     Calendar cal = Calendar.getInstance();
/* 324 */     cal.set(11, 23);
/* 325 */     cal.set(13, 59);
/* 326 */     cal.set(12, 59);
/* 327 */     cal.set(14, 0);
/* 328 */     return cal.getTime();
/*     */   }
/*     */   public static Date getLastSecIntegralTime(Date d) {
/* 331 */     Calendar cal = Calendar.getInstance();
/* 332 */     cal.setTimeInMillis(d.getTime());
/* 333 */     cal.set(11, 23);
/* 334 */     cal.set(13, 59);
/* 335 */     cal.set(12, 59);
/* 336 */     cal.set(14, 0);
/* 337 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getSixtenClockTime(Date d)
/*     */   {
/* 345 */     Calendar cal = Calendar.getInstance();
/* 346 */     cal.setTimeInMillis(d.getTime());
/* 347 */     cal.set(11, 16);
/* 348 */     cal.set(13, 0);
/* 349 */     cal.set(12, 0);
/* 350 */     cal.set(14, 0);
/* 351 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static long getTime(String format) {
/* 355 */     long t = 0L;
/* 356 */     if (StringUtils.isBlank(format)) return t;
/* 357 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*     */     try
/*     */     {
/* 360 */       Date date = sdf.parse(format);
/* 361 */       t = date.getTime() / 1000L;
/*     */     } catch (ParseException e) {
/* 363 */       logger.error(e);
/*     */     }
/* 365 */     return t;
/*     */   }
/*     */ 
/*     */   public static int getOffsetHours(Date d1, Date d2)
/*     */   {
/* 375 */     long hourMillSecs = 3600000L;
/* 376 */     return (int)((d2.getTime() - d1.getTime()) / hourMillSecs);
/*     */   }
/*     */ 
/*     */   public static int getOffsetDays(Date d1, Date d2)
/*     */   {
/* 386 */     return getOffsetHours(d1, d2) / 24;
/*     */   }
/*     */ 
/*     */   public static int getDayOfMonth(Date d)
/*     */   {
/* 396 */     Calendar cal = Calendar.getInstance();
/* 397 */     cal.setTimeInMillis(d.getTime());
/* 398 */     int dayOfMonth = cal.getActualMaximum(5);
/* 399 */     return dayOfMonth;
/*     */   }
/*     */ 
/*     */   public static boolean isTwoDateEqual(Date d1, Date d2, String pattern)
/*     */   {
/* 410 */     SimpleDateFormat format = new SimpleDateFormat(pattern);
/* 411 */     String fDate1 = format.format(d1);
/* 412 */     String fDate2 = format.format(d2);
/* 413 */     return fDate1.equals(fDate2);
/*     */   }
/*     */ 
/*     */   public static Date lastDayOfMonth(Date date)
/*     */   {
/* 423 */     Calendar cal = Calendar.getInstance();
/* 424 */     cal.setTime(date);
/* 425 */     int value = cal.getActualMaximum(5);
/* 426 */     cal.set(5, value);
/* 427 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date firstDayOfMonth(Date date)
/*     */   {
/* 436 */     Calendar cal = Calendar.getInstance();
/* 437 */     cal.setTime(date);
/* 438 */     int value = cal.getActualMinimum(5);
/* 439 */     cal.set(5, value);
/* 440 */     return cal.getTime();
/*     */   }
/*     */ 
/*     */   public static Date getCurrentDate()
/*     */   {
/* 448 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 449 */     Date date = null;
/*     */     try {
/* 451 */       date = sdf.parse(sdf.format(new Date()));
/*     */     } catch (ParseException e) {
/* 453 */       e.printStackTrace();
/*     */     }
/* 455 */     return date;
/*     */   }
/*     */ 
/*     */   public static Date getDate(int year, int month, int day)
/*     */   {
/* 466 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/* 467 */     Calendar cal = Calendar.getInstance();
/* 468 */     cal.set(year, month - 1, day);
/* 469 */     Date date = cal.getTime();
/* 470 */     Date finalDate = null;
/*     */     try {
/* 472 */       finalDate = sdf.parse(sdf.format(date));
/*     */     } catch (ParseException e) {
/* 474 */       e.printStackTrace();
/*     */     }
/* 476 */     return finalDate;
/*     */   }
/*     */ 
/*     */   public static int compareDate(String date1, String date2, String formatStr)
/*     */   {
/* 488 */     int result = 0;
/* 489 */     SimpleDateFormat df = new SimpleDateFormat(formatStr);
/*     */     try {
/* 491 */       Date dt1 = df.parse(date1);
/* 492 */       Date dt2 = df.parse(date2);
/* 493 */       if (dt1.getTime() > dt2.getTime())
/* 494 */         result = 1;
/* 495 */       else if (dt1.getTime() < dt2.getTime())
/* 496 */         result = -1;
/*     */       else
/* 498 */         result = 0;
/*     */     }
/*     */     catch (ParseException e) {
/* 501 */       e.printStackTrace();
/*     */     }
/* 503 */     return result;
/*     */   }
/*     */ 
/*     */   public static int getMinute(Date date)
/*     */   {
/* 513 */     Calendar calendar = Calendar.getInstance();
/* 514 */     calendar.setTime(date);
/* 515 */     return calendar.get(12);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.DateUtils
 * JD-Core Version:    0.6.2
 */