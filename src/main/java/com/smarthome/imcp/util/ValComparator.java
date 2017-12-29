/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class ValComparator
/*    */   implements Comparator<String>
/*    */ {
/*    */   public int compare(String o1, String o2)
/*    */   {
/*  9 */     int seq1 = 0;
/* 10 */     int seq2 = 0;
/*    */     try {
/* 12 */       seq1 = Integer.parseInt(o1);
/* 13 */       seq2 = Integer.parseInt(o2);
/*    */     }
/*    */     catch (Exception localException) {
/*    */     }
/* 17 */     return seq1 - seq2;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.ValComparator
 * JD-Core Version:    0.6.2
 */