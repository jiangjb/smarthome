/*   */ package com.smarthome.imcp.util;
/*   */ 
/*   */ import java.util.Comparator;
/*   */ 
/*   */ public class NumComparator
/*   */   implements Comparator<Integer>
/*   */ {
/*   */   public int compare(Integer o1, Integer o2)
/*   */   {
/* 9 */     return o1.intValue() - o2.intValue();
/*   */   }
/*   */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.NumComparator
 * JD-Core Version:    0.6.2
 */