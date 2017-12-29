/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ class MyString
/*     */   implements Comparable<MyString>
/*     */ {
/*     */   public String s;
/*     */ 
/*     */   public MyString(String s)
/*     */   {
/* 491 */     this.s = s;
/*     */   }
/*     */ 
/*     */   public int compareTo(MyString o)
/*     */   {
/* 496 */     if ((o == null) || (o.s == null))
/* 497 */       return 1;
/* 498 */     return this.s.compareTo(o.s);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.MyString
 * JD-Core Version:    0.6.2
 */