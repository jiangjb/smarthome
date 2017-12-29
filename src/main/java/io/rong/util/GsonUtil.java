/*    */ package io.rong.util;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ public class GsonUtil
/*    */ {
/*  9 */   private static Gson gson = new Gson();
/*    */ 
/*    */   public static String toJson(Object obj, Type type) {
/* 12 */     return gson.toJson(obj, type);
/*    */   }
/*    */ 
/*    */   public static Object fromJson(String str, Type type) {
/* 16 */     return gson.fromJson(str, type);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.util.GsonUtil
 * JD-Core Version:    0.6.2
 */