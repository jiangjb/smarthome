/*    */ package com.smarthome.imcp.controller.system;
/*    */ 
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class SysMessageController
/*    */ {
/*    */   @RequestMapping({"dispatchIndexMessagePage.do"})
/*    */   public String dispatchIndexMessagePage()
/*    */   {
/* 12 */     return "message";
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.system.SysMessageController
 * JD-Core Version:    0.6.2
 */