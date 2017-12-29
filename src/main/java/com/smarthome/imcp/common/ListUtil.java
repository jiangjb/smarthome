/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashSet;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.apache.commons.beanutils.BeanUtils;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ 
/*    */ public class ListUtil
/*    */ {
/*    */   private static final char SEPARATOR = '|';
/*    */ 
/*    */   public static <T> List<T> removeDuplication(List<T> list, String[] keys)
/*    */   {
/* 36 */     if ((list == null) || (list.isEmpty()))
/*    */     {
/* 39 */       return list;
/*    */     }
/*    */ 
/* 42 */     if ((keys == null) || (keys.length < 1))
/*    */     {
/* 45 */       return list;
/*    */     }
/*    */ 
/* 48 */     for (String key : keys)
/*    */     {
/* 50 */       if (StringUtils.isBlank(key))
/*    */       {
/* 53 */         return list;
/*    */       }
/*    */     }
/*    */ 
/* 57 */     List newList = new ArrayList();
/* 58 */     Object keySet = new HashSet();
/*    */ 
/* 60 */     for (Iterator iter = list.iterator(); iter.hasNext(); ) { Object t = (Object)iter.next();
/*    */ 
/* 62 */       StringBuffer logicKey = new StringBuffer();
/* 63 */       for (String keyField : keys)
/*    */       {
/*    */         try
/*    */         {
/* 67 */           logicKey.append(BeanUtils.getProperty(t, keyField));
/* 68 */           logicKey.append('|');
/*    */         }
/*    */         catch (Exception e)
/*    */         {
/* 72 */           e.printStackTrace();
/* 73 */           return list;
/*    */         }
/*    */       }
/* 76 */       if (!((Set)keySet).contains(logicKey.toString()))
/*    */       {
/* 78 */         ((Set)keySet).add(logicKey.toString());
/* 79 */         newList.add(t);
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 87 */     return newList;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.ListUtil
 * JD-Core Version:    0.6.2
 */