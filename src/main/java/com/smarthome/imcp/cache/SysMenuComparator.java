/*    */ package com.smarthome.imcp.cache;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysMenu;
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class SysMenuComparator
/*    */   implements Comparator<SysMenu>
/*    */ {
/*    */   public int compare(SysMenu o1, SysMenu o2)
/*    */   {
/* 11 */     if (o1.equals(o2)) {
/* 12 */       return 0;
/*    */     }
/* 14 */     if (o1.getMenuCode().equals(o2.getMenuCode())) {
/* 15 */       return 0;
/*    */     }
/* 17 */     if ((o1.getMntPosition() == null) && (o2.getMntPosition() == null)) {
/* 18 */       return o1.getMenuCode().compareTo(o2.getMenuCode());
/*    */     }
/* 20 */     if (o1.getMntPosition() == null) {
/* 21 */       return -1;
/*    */     }
/* 23 */     if (o2.getMntPosition() == null) {
/* 24 */       return 1;
/*    */     }
/* 26 */     if (o1.getMntPosition().equals(o2.getMntPosition())) {
/* 27 */       return o1.getMenuCode().compareTo(o2.getMenuCode());
/*    */     }
/* 29 */     return o1.getMntPosition().compareTo(o2.getMntPosition());
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.cache.SysMenuComparator
 * JD-Core Version:    0.6.2
 */