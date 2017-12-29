/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import net.sourceforge.pinyin4j.PinyinHelper;
/*    */ import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
/*    */ import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
/*    */ import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
/*    */ import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
/*    */ import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/*    */ 
/*    */ public class PinYinUtil
/*    */ {
/*    */   public static String getPinYin(String zhongwen)
/*    */   {
/* 20 */     String zhongWenPinYin = "";
/* 21 */     char[] chars = zhongwen.toCharArray();
/*    */     try {
/* 23 */       for (int i = 0; i < chars.length; i++) {
/* 24 */         String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());
/*    */ 
/* 26 */         if (pinYin != null)
/* 27 */           zhongWenPinYin = zhongWenPinYin + capitalize(pinYin[0]);
/*    */         else
/* 29 */           zhongWenPinYin = zhongWenPinYin + chars[i];
/*    */       }
/*    */     }
/*    */     catch (BadHanyuPinyinOutputFormatCombination e) {
/* 33 */       e.printStackTrace();
/*    */     }
/* 35 */     return zhongWenPinYin;
/*    */   }
/*    */ 
/*    */   public static String getInitialPinYin(String zhongwen)
/*    */   {
/* 44 */     String zhongWenPinYin = "";
/* 45 */     char[] chars = zhongwen.toCharArray();
/*    */     try {
/* 47 */       for (int i = 0; i < chars.length; i++) {
/* 48 */         String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(chars[i], getDefaultOutputFormat());
/*    */ 
/* 50 */         if (pinYin != null)
/* 51 */           zhongWenPinYin = zhongWenPinYin + pinYin[0].substring(0, 1).toUpperCase();
/*    */         else
/* 53 */           zhongWenPinYin = zhongWenPinYin + chars[i];
/*    */       }
/*    */     }
/*    */     catch (BadHanyuPinyinOutputFormatCombination e) {
/* 57 */       e.printStackTrace();
/*    */     }
/* 59 */     return zhongWenPinYin;
/*    */   }
/*    */ 
/*    */   public static HanyuPinyinOutputFormat getDefaultOutputFormat()
/*    */   {
/* 68 */     HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
/* 69 */     format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
/* 70 */     format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
/* 71 */     format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);
/*    */ 
/* 73 */     return format;
/*    */   }
/*    */ 
/*    */   public static String capitalize(String s)
/*    */   {
/* 84 */     char[] ch = s.toCharArray();
/* 85 */     if ((ch[0] >= 'a') && (ch[0] <= 'z')) {
/* 86 */       ch[0] = ((char)(ch[0] - ' '));
/*    */     }
/* 88 */     String newString = new String(ch);
/* 89 */     return newString;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.PinYinUtil
 * JD-Core Version:    0.6.2
 */