/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class StringUtils extends org.apache.commons.lang3.StringUtils
/*     */ {
/*     */   public static String isNull(String str)
/*     */   {
/*  21 */     if (str == null) {
/*  22 */       return "";
/*     */     }
/*  24 */     return str;
/*     */   }
/*     */ 
/*     */   public static String isStringNull(String str)
/*     */   {
/*  34 */     if ((str == null) || ("null".equalsIgnoreCase(str))) {
/*  35 */       return "";
/*     */     }
/*  37 */     return str;
/*     */   }
/*     */ 
/*     */   public static String isNull(Object o) {
/*  41 */     if (o == null) {
/*  42 */       return "";
/*     */     }
/*  44 */     String str = "";
/*  45 */     if ((o instanceof String))
/*  46 */       str = (String)o;
/*     */     else {
/*  48 */       str = o.toString();
/*     */     }
/*  50 */     return str;
/*     */   }
/*     */ 
/*     */   public static boolean isEmail(String email)
/*     */   {
/*  60 */     email = isNull(email);
/*  61 */     Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
/*  62 */     Matcher matcher = regex.matcher(email);
/*  63 */     boolean isMatched = matcher.matches();
/*  64 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isMobile(String mobiles)
/*     */   {
/*  72 */     Pattern p = Pattern.compile("^(13[0-9]{9})|(17(0|[6-8])[0-9]{8})|(18[0-9]{9})|(15[0-35-9][0-9]{8})$");
/*  73 */     Matcher m = p.matcher(mobiles);
/*  74 */     return m.matches();
/*     */   }
/*     */ 
/*     */   public static boolean isCard(String cardId)
/*     */   {
/*  84 */     cardId = isNull(cardId);
/*     */ 
/*  86 */     Pattern isIDCard1 = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
/*     */ 
/*  88 */     Pattern isIDCard2 = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
/*  89 */     Matcher matcher1 = isIDCard1.matcher(cardId);
/*  90 */     Matcher matcher2 = isIDCard2.matcher(cardId);
/*  91 */     boolean isMatched = (matcher1.matches()) || (matcher2.matches());
/*  92 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isRealName(String realName)
/*     */   {
/* 101 */     realName = isNull(realName);
/* 102 */     Pattern regex = Pattern.compile("^[一-龥]{2,5}$");
/* 103 */     Matcher matcher = regex.matcher(realName);
/* 104 */     boolean isMatched = matcher.matches();
/* 105 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isAdminPassword(String password)
/*     */   {
/* 127 */     password = isNull(password);
/* 128 */     Pattern pattern = Pattern.compile("^(?=.*\\d.*)(?=.*[a-zA-Z].*)(?=.*[-`~!@#$%^&*()_+\\|\\\\=,./?><\\{\\}\\[\\]].*).*$");
/* 129 */     Matcher match = pattern.matcher(password);
/* 130 */     boolean isMatched = match.matches();
/* 131 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isUrl(String url)
/*     */   {
/* 140 */     url = isNull(url);
/* 141 */     Pattern pattern = Pattern.compile("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?");
/* 142 */     Matcher match = pattern.matcher(url);
/* 143 */     boolean isMatched = match.matches();
/* 144 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isInteger(String str)
/*     */   {
/* 154 */     if (isEmpty(str)) {
/* 155 */       return false;
/*     */     }
/* 157 */     Pattern regex = Pattern.compile("\\d*");
/* 158 */     Matcher matcher = regex.matcher(str);
/* 159 */     boolean isMatched = matcher.matches();
/* 160 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isNumber(String str)
/*     */   {
/* 170 */     if (isEmpty(str)) {
/* 171 */       return false;
/*     */     }
/*     */ 
/* 174 */     Pattern regex = Pattern.compile("\\d*(.\\d*)?");
/* 175 */     Matcher matcher = regex.matcher(str);
/* 176 */     boolean isMatched = matcher.matches();
/* 177 */     return isMatched;
/*     */   }
/*     */ 
/*     */   public static boolean isEmpty(String str)
/*     */   {
/* 187 */     if ((str == null) || ("".equals(str))) {
/* 188 */       return true;
/*     */     }
/* 190 */     return false;
/*     */   }
/*     */ 
/*     */   public static String firstCharUpperCase(String s)
/*     */   {
/* 200 */     StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
/* 201 */     sb.append(s.substring(1, s.length()));
/* 202 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String hideChar(String str, int len) {
/* 206 */     if (str == null) return null;
/* 207 */     char[] chars = str.toCharArray();
/* 208 */     for (int i = 1; i > chars.length - 1; i++) {
/* 209 */       if (i < len) {
/* 210 */         chars[i] = '*';
/*     */       }
/*     */     }
/* 213 */     str = new String(chars);
/* 214 */     return str;
/*     */   }
/*     */ 
/*     */   public static String hideLastChar(String str, int len) {
/* 218 */     if (str == null) return null;
/* 219 */     char[] chars = str.toCharArray();
/* 220 */     if (str.length() <= len) {
/* 221 */       for (int i = 0; i < chars.length; i++)
/* 222 */         chars[i] = '*';
/*     */     }
/*     */     else {
/* 225 */       for (int i = chars.length - 1; i > chars.length - len - 1; i--) {
/* 226 */         chars[i] = '*';
/*     */       }
/*     */     }
/* 229 */     str = new String(chars);
/* 230 */     return str;
/*     */   }
/*     */ 
/*     */   public static String subLastLengthStr(String str, int len) {
/* 234 */     if (str == null) {
/* 235 */       return null;
/*     */     }
/* 237 */     if (str.length() <= len) {
/* 238 */       return str;
/*     */     }
/*     */ 
/* 242 */     return str.substring(str.length() - len, str.length());
/*     */   }
/*     */ 
/*     */   public static String format(String str, int len)
/*     */   {
/* 249 */     if (str == null) return "-";
/* 250 */     if (str.length() <= len) {
/* 251 */       int pushlen = len - str.length();
/* 252 */       StringBuffer sb = new StringBuffer();
/* 253 */       for (int i = 0; i < pushlen; i++) {
/* 254 */         sb.append("0");
/*     */       }
/* 256 */       sb.append(str);
/* 257 */       str = sb.toString();
/*     */     } else {
/* 259 */       String newStr = str.substring(0, len);
/* 260 */       str = newStr;
/*     */     }
/* 262 */     return str;
/*     */   }
/*     */ 
/*     */   public static String contact(Object[] args) {
/* 266 */     StringBuffer sb = new StringBuffer();
/* 267 */     for (int i = 0; i < args.length; i++) {
/* 268 */       sb.append(args[i]);
/* 269 */       if (i < args.length - 1) {
/* 270 */         sb.append(",");
/*     */       }
/*     */     }
/* 273 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static boolean isInSplit(String s, String type)
/*     */   {
/* 283 */     if (isNull(s).equals("")) {
/* 284 */       return false;
/*     */     }
/* 286 */     List list = Arrays.asList(s.split(","));
/* 287 */     if (list.contains(type)) {
/* 288 */       return true;
/*     */     }
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isBlank(String str) {
/* 294 */     return isNull(str).equals("");
/*     */   }
/*     */ 
/*     */   public static String generateTradeNO(long userid, String type)
/*     */   {
/* 299 */     String s = type + userid + getFullTimeStr();
/* 300 */     return s;
/*     */   }
/*     */ 
/*     */   public static String generatePnrTradeNO(long userid, String type)
/*     */   {
/* 305 */     SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
/* 306 */     String str = format.format(Calendar.getInstance().getTime());
///* 307 */     long r = NumberUtils.getRandom(4);
			  String r = NumberUtils.getRandom(4)+"";
/* 308 */     format(r, 4);
/* 309 */     String s = type + userid + str + format(new StringBuilder(String.valueOf(r)).toString(), 4);
/* 310 */     return s;
/*     */   }
/*     */ 
/*     */   public static synchronized String getYJFTradeNO()
/*     */   {
/* 315 */     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
/* 316 */     String str = format.format(Calendar.getInstance().getTime());
///* 317 */     long r = NumberUtils.getRandom(8);
			  String r = NumberUtils.getRandom(8)+"";
/* 318 */     format(r, 8);
/* 319 */     String s = str + format(new StringBuilder(String.valueOf(r)).toString(), 8);
/* 320 */     return s;
/*     */   }
/*     */ 
/*     */   public static String getFullTimeStr() {
/* 324 */     String s = DateUtils.dateStr3(Calendar.getInstance().getTime());
/* 325 */     return s;
/*     */   }
/*     */ 
/*     */   public static String array2Str(Object[] arr) {
/* 329 */     StringBuffer s = new StringBuffer();
/* 330 */     for (int i = 0; i < arr.length; i++) {
/* 331 */       s.append(arr[i]);
/* 332 */       if (i < arr.length - 1) {
/* 333 */         s.append(",");
/*     */       }
/*     */     }
/* 336 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static String array2Str(int[] arr) {
/* 340 */     StringBuffer s = new StringBuffer();
/* 341 */     for (int i = 0; i < arr.length; i++) {
/* 342 */       s.append(arr[i]);
/* 343 */       if (i < arr.length - 1) {
/* 344 */         s.append(",");
/*     */       }
/*     */     }
/* 347 */     return s.toString();
/*     */   }
/*     */ 
/*     */   public static String gbk2Utf(String gbk) throws UnsupportedEncodingException {
/* 351 */     char[] c = gbk.toCharArray();
/* 352 */     byte[] fullByte = new byte[3 * c.length];
/* 353 */     for (int i = 0; i < c.length; i++) {
/* 354 */       String binary = Integer.toBinaryString(c[i]);
/* 355 */       StringBuffer sb = new StringBuffer();
/* 356 */       int len = 16 - binary.length();
/*     */ 
/* 358 */       for (int j = 0; j < len; j++) {
/* 359 */         sb.append("0");
/*     */       }
/* 361 */       sb.append(binary);
/*     */ 
/* 363 */       sb.insert(0, "1110");
/* 364 */       sb.insert(8, "10");
/* 365 */       sb.insert(16, "10");
/* 366 */       fullByte[(i * 3)] = Integer.valueOf(sb.substring(0, 8), 2).byteValue();
/* 367 */       fullByte[(i * 3 + 1)] = Integer.valueOf(sb.substring(8, 16), 2).byteValue();
/* 368 */       fullByte[(i * 3 + 2)] = Integer.valueOf(sb.substring(16, 24), 2).byteValue();
/*     */     }
/*     */ 
/* 371 */     return new String(fullByte, "UTF-8");
/*     */   }
/*     */ 
/*     */   public static boolean checkDateString(String dateStr) {
/* 375 */     String eL = "[1-9]{1}[0-9]{3}-[0-9]{2}-[0-9]{2}";
/* 376 */     Pattern p = Pattern.compile(eL);
/* 377 */     Matcher m = p.matcher(dateStr);
/* 378 */     return m.matches();
/*     */   }
/*     */ 
/*     */   public static String getGbk(String str) throws UnsupportedEncodingException {
/* 382 */     return new String(str.getBytes("UTF-8"), "GB2312");
/*     */   }
/*     */ 
/*     */   public static String[] StringSort(String[] str) {
/* 386 */     MyString[] mySs = new MyString[str.length];
/* 387 */     for (int i = 0; i < str.length; i++) {
/* 388 */       mySs[i] = new MyString(str[i]);
/*     */     }
/* 390 */     Arrays.sort(mySs);
/* 391 */     String[] str2 = new String[mySs.length];
/* 392 */     for (int i = 0; i < mySs.length; i++) {
/* 393 */       str2[i] = mySs[i].s;
/*     */     }
/* 395 */     return str2;
/*     */   }
/*     */ 
/*     */   public static String[] str2Array(String str, String reg) {
/* 399 */     String[] backArr = null;
/* 400 */     if (isNotBlank(str)) {
/* 401 */       backArr = str.split(reg);
/*     */     }
/* 403 */     return backArr;
/*     */   }
/*     */ 
/*     */   public static String truncatUrl(String old, String truncat) {
/* 407 */     String url = "";
/* 408 */     url = old.replace(truncat, "");
/* 409 */     url = url.replace(File.separator, "/");
/* 410 */     return url;
/*     */   }
/*     */ 
/*     */   public static String randNumCode(int len)
/*     */   {
/* 415 */     if (len <= 0) {
/* 416 */       return "";
/*     */     }
/* 418 */     StringBuffer sb = new StringBuffer();
/* 419 */     for (int i = 0; i < len; i++) {
/* 420 */       sb.append(Math.round(Math.random() * 8.0D + 1.0D));
/*     */     }
/* 422 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String upNumb(double numb)
/*     */   {
/* 431 */     return UpNumber.toChinese(numb+"");
/*     */   }
/*     */ 
///*     */   public static String leftZeroFill(int num, int len)
			public static String leftZeroFill(int num, int len)
/*     */   {
///* 440 */     if (num.length() > len) {
			  if (num > len) {
/* 441 */       return num+"";
/*     */     }
/*     */ 
/* 444 */     NumberFormat nf = NumberFormat.getInstance();
/*     */ 
/* 446 */     nf.setGroupingUsed(false);
/*     */ 
/* 448 */     nf.setMaximumIntegerDigits(len);
/*     */ 
/* 450 */     nf.setMinimumIntegerDigits(len);
/*     */ 
/* 452 */     return nf.format(num);
/*     */   }
/*     */ 
/*     */   public static String hidePhone(String phone)
/*     */   {
/* 457 */     String finalPhone = "";
/* 458 */     if (!isBlank(phone)) {
/* 459 */       if (phone.length() < 7)
/* 460 */         finalPhone = phone;
/*     */       else {
/* 462 */         finalPhone = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
/*     */       }
/*     */     }
/*     */ 
/* 466 */     return finalPhone;
/*     */   }
/*     */ 
/*     */   public static boolean isHave(String[] strings, String string)
/*     */   {
/* 478 */     for (int i = 0; i < strings.length; i++) {
/* 479 */       if (strings[i].indexOf(string) != -1) {
/* 480 */         return true;
/*     */       }
/*     */     }
/* 483 */     return false;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.StringUtils
 * JD-Core Version:    0.6.2
 */