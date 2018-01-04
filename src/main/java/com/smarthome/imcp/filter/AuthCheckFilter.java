/*    */ package com.smarthome.imcp.filter;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ 
/*    */ public class AuthCheckFilter
/*    */   implements Filter
/*    */ {
/*    */   private static final String LOGIN_FILE = "login.jsp";
/*    */   private String strcLogin;
/*    */   private String strcEncoding;
/*    */ 
/*    */   public void init(FilterConfig filterconfig)
/*    */     throws ServletException
/*    */   {
/* 29 */     this.strcLogin = filterconfig.getInitParameter("redirectURL");
/* 30 */     if (this.strcLogin == null) {
/* 31 */       this.strcLogin = "login.jsp";
/*    */     }
/* 33 */     this.strcEncoding = filterconfig.getInitParameter("encoding");
/* 34 */     if (this.strcEncoding == null)
/* 35 */       this.strcEncoding = "UTF-8";
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 41 */     HttpServletRequest req = (HttpServletRequest)request;
/* 42 */     HttpServletResponse res = (HttpServletResponse)response;
/* 43 */     HttpSession session = req.getSession(true);
/*    */ 
/* 45 */     req.setCharacterEncoding(this.strcEncoding);
/* 46 */     res.setCharacterEncoding(this.strcEncoding);
/*    */ 
/* 48 */     res.setHeader("Pragma", "No-cache");
/* 49 */     res.setHeader("Cache-Control", "no-cache");
/* 50 */     res.setDateHeader("Expires", 0L);
/*    */ 
/* 52 */     String uri = req.getRequestURI().substring(
/* 53 */       req.getContextPath().length() + 1);
/*    */ 
/* 55 */     Object account = session.getAttribute("USER_INFO");
//			 System.out.println("uri:"+uri);
			//这个if判断用于 让这四个页面免于过滤
/*    */ 	if((uri.equals("register.jsp")) || (uri.equals("TelOrEmail.jsp")) || (uri.equals("findPwdByEmail.jsp")) || (uri.equals("findPwdByTel.jsp"))) {
				System.out.println(uri+" pass!");
			}else if ((!uri.startsWith("public/")) && (!uri.equals("login.action")) && (!this.strcLogin.equals(uri)) && 
/* 58 */       (account == null)) {
/* 59 */       res.sendRedirect(req.getContextPath() + 
/* 60 */         "/" + this.strcLogin);
/* 61 */       return;
/*    */     }
/*    */ 
/* 65 */     chain.doFilter(req, res);
/*    */   }
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.filter.AuthCheckFilter
 * JD-Core Version:    0.6.2
 */