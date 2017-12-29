/*    */ package com.smarthome.imcp.cache;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysDict;
/*    */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class SysDictComparator
/*    */   implements Comparator<SysDict>
/*    */ {
/*    */   public int compare(SysDict o1, SysDict o2)
/*    */   {
/* 10 */     if (o1.equals(o2)) {
/* 11 */       return 0;
/*    */     }
/* 13 */     if (o1.getDictId().equals(o2.getDictId())) {
/* 14 */       return 0;
/*    */     }
/*    */ 
/* 17 */     if (o1.getSysDictCode().getDictCode()
/* 17 */       .equals(o2.getSysDictCode().getDictCode())) {
/* 18 */       if ((o1.getMntPosition() == null) && (o2.getMntPosition() == null)) {
/* 19 */         return o1.getDictValue().compareTo(o2.getDictValue());
/*    */       }
/* 21 */       if (o1.getMntPosition() == null) {
/* 22 */         return -1;
/*    */       }
/* 24 */       if (o2.getMntPosition() == null) {
/* 25 */         return 1;
/*    */       }
/* 27 */       if (o1.getMntPosition().equals(o2.getMntPosition())) {
/* 28 */         return o1.getDictValue().compareTo(o2.getDictValue());
/*    */       }
/* 30 */       return o1.getMntPosition().compareTo(o2.getMntPosition());
/*    */     }
/*    */ 
/* 33 */     return o1.getSysDictCode().getDictCode()
/* 34 */       .compareTo(o2.getSysDictCode().getDictCode());
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysDictComparator
 * JD-Core Version:    0.6.2
 */