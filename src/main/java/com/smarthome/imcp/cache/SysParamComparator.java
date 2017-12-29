/*    */ package com.smarthome.imcp.cache;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysParam;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class SysParamComparator
/*    */   implements Comparator<SysParam>
/*    */ {
/*    */   public int compare(SysParam o1, SysParam o2)
/*    */   {
/* 10 */     if (o1.equals(o2)) {
/* 11 */       return 0;
/*    */     }
/* 13 */     if (o1.getParamId().equals(o2.getParamId())) {
/* 14 */       return 0;
/*    */     }
/* 16 */     return o1.getParamValue().compareTo(o2.getParamValue());
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysParamComparator
 * JD-Core Version:    0.6.2
 */