/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ 
/*     */ public class ResponseUtil
/*     */ {
/*     */   public static void writer(HttpServletResponse response, String json)
/*     */   {
/*  18 */     response.setContentType("json");
/*  19 */     response.setHeader("Expires", "-1");
/*  20 */     response.setHeader("Pragma", "no-cache");
/*  21 */     response.setHeader("Cache-control", "no-cache");
/*  22 */     response.setHeader("Content-Type", "json; charset=UTF-8");
/*  23 */     PrintWriter w = null;
/*     */     try {
/*  25 */       w = getWriter(response);
/*  26 */       w.write(json);
/*  27 */       w.flush();
/*  28 */       w.close();
/*     */     } catch (IOException e) {
/*  30 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected static PrintWriter getWriter(HttpServletResponse response)
/*     */     throws IOException
/*     */   {
/*  42 */     response.setContentType("text/html");
/*  43 */     response.setHeader("Expires", "-1");
/*  44 */     response.setHeader("Pragma", "no-cache");
/*  45 */     response.setHeader("Cache-control", "no-cache");
/*  46 */     response.setHeader("Content-Type", "text/html; charset=UTF-8");
/*  47 */     return response.getWriter();
/*     */   }
/*     */ 
/*     */   public static void writerSuccess(HttpServletResponse response, String message)
/*     */   {
/*  52 */     writer(response, JSonUtil.getDWZMessage("success", message, null, null, 
/*  53 */       null, null));
/*     */   }
/*     */ 
/*     */   public static void writerJson(HttpServletResponse response, List list, long total)
/*     */   {
/*  59 */     writer(response, JSonUtil.genJsonDWZ(list, total));
/*     */   }
/*     */ 
/*     */   public void writerDeleteSuccess(HttpServletResponse response, String message) {
/*  63 */     writer(response, JSonUtil.getDWZMessage("delete", message, null, null, 
/*  64 */       null, null));
/*     */   }
/*     */ 
/*     */   public static void writerError(HttpServletResponse response, String message) {
/*  68 */     writer(response, JSonUtil.getDWZMessage("error", message, null, null, 
/*  69 */       null, null));
/*     */   }
/*     */ 
/*     */   public static void writerErrorClose(HttpServletResponse response, String message)
/*     */   {
/*  74 */     writer(response, JSonUtil.getDWZMessage("error", message, null, 
/*  75 */       "closeCurrent", null, null));
/*     */   }
/*     */ 
/*     */   public static void writerTimeout(HttpServletResponse response, String message)
/*     */   {
/*  80 */     writer(response, JSonUtil.getDWZMessage("timeout", message, null, null, 
/*  81 */       null, null));
/*     */   }
/*     */ 
/*     */   public static void writerSuccess(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/*  86 */     writer(response, JSonUtil.getDWZMessage("success", message, nvaTabId, 
/*  87 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerSuccessNotClose(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/*  92 */     writer(response, JSonUtil.getDWZMessage("successNotClose", message, nvaTabId, 
/*  93 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerCopy(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/*  98 */     writer(response, JSonUtil.getDWZMessage("copy", message, nvaTabId, 
/*  99 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerDeploySuccess(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/* 104 */     writer(response, JSonUtil.getDWZMessage("deploy", message, nvaTabId, 
/* 105 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerApproveSuccess(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/* 110 */     writer(response, JSonUtil.getDWZMessage("approve", message, nvaTabId, 
/* 111 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerDeleteSuccess(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/* 116 */     writer(response, JSonUtil.getDWZMessage("delete", message, nvaTabId, 
/* 117 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerDeleteSuccess(HttpServletResponse response, String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 123 */     writer(response, JSonUtil.getDWZMessage("delete", message, nvaTabId, 
/* 124 */       callbackType, forwardUrl, null));
/*     */   }
/*     */ 
/*     */   public static void writerError(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/* 129 */     writer(response, JSonUtil.getDWZMessage("error", message, nvaTabId, 
/* 130 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerTimeout(HttpServletResponse response, String message, String nvaTabId)
/*     */   {
/* 135 */     writer(response, JSonUtil.getDWZMessage("timeout", message, nvaTabId, 
/* 136 */       null, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerDeleteSuccessByFlexId(HttpServletResponse response, String message, String nvaTabId, String flexId)
/*     */   {
/* 142 */     writer(response, JSonUtil.getDWZMessage("delete", message, nvaTabId, 
/* 143 */       null, null, flexId));
/*     */   }
/*     */ 
/*     */   public static void writerSuccessByFlexId(HttpServletResponse response, String message, String nvaTabId, String flexId)
/*     */   {
/* 148 */     writer(response, JSonUtil.getDWZMessage("success", message, nvaTabId, 
/* 149 */       null, null, flexId));
/*     */   }
/*     */ 
/*     */   public static void writerSuccess(HttpServletResponse response, String message, String nvaTabId, String callbackType)
/*     */   {
/* 154 */     writer(response, JSonUtil.getDWZMessage("success", message, nvaTabId, 
/* 155 */       callbackType, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerError(HttpServletResponse response, String message, String nvaTabId, String callbackType)
/*     */   {
/* 160 */     writer(response, JSonUtil.getDWZMessage("error", message, nvaTabId, 
/* 161 */       callbackType, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerTimeout(HttpServletResponse response, String message, String nvaTabId, String callbackType)
/*     */   {
/* 166 */     writer(response, JSonUtil.getDWZMessage("timeout", message, nvaTabId, 
/* 167 */       callbackType, null, null));
/*     */   }
/*     */ 
/*     */   public static void writerSuccess(HttpServletResponse response, String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 173 */     writer(response, JSonUtil.getDWZMessage("success", message, nvaTabId, 
/* 174 */       callbackType, forwardUrl, null));
/*     */   }
/*     */ 
/*     */   public static void writerError(HttpServletResponse response, String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 180 */     writer(response, JSonUtil.getDWZMessage("error", message, nvaTabId, 
/* 181 */       callbackType, forwardUrl, null));
/*     */   }
/*     */ 
/*     */   public static void writerTimeout(HttpServletResponse response, String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 187 */     writer(response, JSonUtil.getDWZMessage("timeout", message, nvaTabId, 
/* 188 */       callbackType, forwardUrl, null));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.ResponseUtil
 * JD-Core Version:    0.6.2
 */