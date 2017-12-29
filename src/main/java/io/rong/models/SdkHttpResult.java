/*    */ package io.rong.models;
/*    */ 
/*    */ public class SdkHttpResult
/*    */ {
/*    */   private int code;
/*    */   private String result;
/*    */ 
/*    */   public SdkHttpResult(int code, String result)
/*    */   {
/* 10 */     this.code = code;
/* 11 */     this.result = result;
/*    */   }
/*    */ 
/*    */   public int getHttpCode() {
/* 15 */     return this.code;
/*    */   }
/*    */ 
/*    */   public String getResult() {
/* 19 */     return this.result;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 24 */     return String.format("{\"code\":\"%s\",\"result\":%s}", new Object[] { Integer.valueOf(this.code), 
/* 25 */       this.result });
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.models.SdkHttpResult
 * JD-Core Version:    0.6.2
 */