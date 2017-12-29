/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class ValidatorUtil
/*     */ {
/*     */   public static boolean IsMobile(String str)
/*     */   {
/*  20 */     Pattern p = 
/*  21 */       Pattern.compile("^0{0,1}(13[0-9]|15[0-9]|151|153|156|18[1-9]|17[0-9])[0-9]{8}$");
/*  22 */     if (str.length() == 11) {
/*  23 */       return p.matcher(str).matches();
/*     */     }
/*  25 */     return false;
/*     */   }
/*     */ 
/*     */   public static boolean isEmail(String str)
/*     */   {
/*  35 */     String regex = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
/*  36 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean isIP(String str)
/*     */   {
/*  46 */     String num = "(25[0-5]|2[0-4]//d|[0-1]//d{2}|[1-9]?//d)";
/*  47 */     String regex = "^" + num + "//." + num + "//." + num + "//." + num + 
/*  48 */       "$";
/*  49 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsUrl(String str)
/*     */   {
/*  59 */     String regex = "http(s)?://([//w-]+//.)+[//w-]+(/[//w- ./?%&=]*)?";
/*  60 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsTelephone(String str)
/*     */   {
/*  70 */     Pattern p1 = null; Pattern p2 = null;
/*  71 */     Matcher m = null;
/*  72 */     boolean b = false;
/*  73 */     p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");
/*  74 */     p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");
/*  75 */     if (str.length() > 9) {
/*  76 */       m = p1.matcher(str);
/*  77 */       b = m.matches();
/*     */     } else {
/*  79 */       m = p2.matcher(str);
/*  80 */       b = m.matches();
/*     */     }
/*  82 */     return b;
/*     */   }
/*     */ 
/*     */   public static boolean IsPassword(String str)
/*     */   {
/*  92 */     String regex = "[A-Za-z]+[0-9]";
/*  93 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsPasswLength(String str)
/*     */   {
/* 103 */     String regex = "^//d{6,18}$";
/* 104 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsPostalcode(String str)
/*     */   {
/* 114 */     String regex = "^//d{6}$";
/* 115 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsHandset(String str)
/*     */   {
/* 125 */     String regex = "^[1]+[3,4,5,7,8]+//d{9}$";
/* 126 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsIDcard(String str)
/*     */   {
/* 136 */     String regex = "(^//d{18}$)|(^//d{15}$)";
/* 137 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsDecimal(String str)
/*     */   {
/* 147 */     String regex = "^[0-9]+(.[0-9]{2})?$";
/* 148 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsMonth(String str)
/*     */   {
/* 158 */     String regex = "^(0?[[1-9]|1[0-2])$";
/* 159 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsDay(String str)
/*     */   {
/* 169 */     String regex = "^((0?[1-9])|((1|2)[0-9])|30|31)$";
/* 170 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean isDate(String str)
/*     */   {
/* 188 */     String regex = "^((((1[6-9]|[2-9]//d)//d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]//d|3[01]))|(((1[6-9]|[2-9]//d)//d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]//d|30))|(((1[6-9]|[2-9]//d)//d{2})-0?2-(0?[1-9]|1//d|2[0-8]))|(((1[6-9]|[2-9]//d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?//d):[0-5]?//d:[0-5]?//d$";
/* 189 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsNumber(String str)
/*     */   {
/* 199 */     String regex = "^[0-9]*$";
/* 200 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsIntNumber(String str)
/*     */   {
/* 210 */     String regex = "[0-9]*";
/* 211 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsUpChar(String str)
/*     */   {
/* 221 */     String regex = "^[A-Z]+$";
/* 222 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsLowChar(String str)
/*     */   {
/* 232 */     String regex = "^[a-z]+$";
/* 233 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsLetter(String str)
/*     */   {
/* 243 */     String regex = "^[A-Za-z]+$";
/* 244 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsChinese(String str)
/*     */   {
/* 254 */     String regex = "^[/u4e00-/u9fa5],{0,}$";
/* 255 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean IsLength(String str)
/*     */   {
/* 265 */     String regex = "^.{8,}$";
/* 266 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   public static boolean isNumeric(String str)
/*     */   {
/* 276 */     String regex = "[0-9]*";
/* 277 */     return match(regex, str);
/*     */   }
/*     */ 
/*     */   private static boolean match(String regex, String str)
/*     */   {
/* 290 */     Pattern pattern = Pattern.compile(regex);
/* 291 */     Matcher matcher = pattern.matcher(str);
/* 292 */     return matcher.matches();
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/* 296 */     System.err.println(isNumeric("21"));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.ValidatorUtil
 * JD-Core Version:    0.6.2
 */