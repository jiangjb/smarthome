/*    */ package com.smarthome.imcp.interceptor;
/*    */ 
/*    */ import com.opensymphony.xwork2.ActionContext;
/*    */ import com.opensymphony.xwork2.ActionInvocation;
/*    */ import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Map.Entry;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class TrimInterceptor extends AbstractInterceptor
/*    */ {
/*    */   public String intercept(ActionInvocation invocation)
/*    */     throws Exception
/*    */   {
/* 15 */     Map parameters = invocation.getInvocationContext()
/* 16 */       .getParameters();
/* 17 */     Set entrySet = parameters.entrySet();
/* 18 */     String[] strings = null;
/* 19 */     String[] values = null;
/* 20 */     int strLength = 0;
/* 21 */     for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
/* 22 */       Map.Entry entry = (Map.Entry)it.next();
/* 23 */       Object key = entry.getKey();
/* 24 */       Object value = entry.getValue();
/*    */ 
/* 26 */       if ((value instanceof String[])) {
/* 27 */         values = (String[])value;
/* 28 */         strLength = values.length;
/* 29 */         strings = new String[strLength];
/* 30 */         for (int i = 0; i < strLength; i++) {
/* 31 */           strings[i] = values[i].trim();
/*    */         }
/* 33 */         parameters.put((String)key, strings);
/*    */       }
/*    */     }
/*    */ 
/* 37 */     invocation.getInvocationContext().setParameters(parameters);
/* 38 */     return invocation.invoke();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.interceptor.TrimInterceptor
 * JD-Core Version:    0.6.2
 */