/*     */ package com.smarthome.imcp.action;
/*     */ 
/*     */ import com.opensymphony.xwork2.ActionContext;
/*     */ import com.opensymphony.xwork2.ActionSupport;
/*     */ import com.opensymphony.xwork2.util.ValueStack;
/*     */ import com.smarthome.imcp.common.JSonUtil;
/*     */ import com.smarthome.imcp.common.NumberUtils;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.common.ResponseUtil;
/*     */ import com.smarthome.imcp.common.StringUtils;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintWriter;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.struts2.ServletActionContext;
/*     */ 
/*     */ public abstract class AbstractAction extends ActionSupport
/*     */ {
/*  29 */   private static final Log logger = LogFactory.getLog(AbstractAction.class);
/*     */   public static final String NORMAL = "normal";
/*     */   public static final String SYSTEM = "system";
/*  34 */   private int pageNum = 1;
/*     */ 
/*  36 */   private int pageSize = 10;
/*     */   private String orderField;
/*     */   private String orderDirection;
/*  42 */   protected Page page = new Page();
/*     */ 
/*     */   protected CurrentUser getCurrentUser() {
/*  45 */     return (CurrentUser)ActionContext.getContext().getSession().get("USER_INFO");
/*     */   }
/*     */ 
/*     */   public void writer(String message) {
/*  49 */     if (logger.isDebugEnabled()) {
/*  50 */       logger.debug(message);
/*     */     }
/*  52 */     ResponseUtil.writer(getResponse(), message);
/*     */   }
/*     */ 
/*     */   public void writerJson(List list)
/*     */   {
/*  57 */     ResponseUtil.writer(getResponse(), JSonUtil.genJson(list));
/*     */   }
/*     */ 
/*     */   public void writerSuccess(String message) {
/*  61 */     ResponseUtil.writerSuccess(getResponse(), message);
/*     */   }
/*     */ 
/*     */   public void writerError(String message) {
/*  65 */     ResponseUtil.writerError(getResponse(), message);
/*     */   }
/*     */ 
/*     */   public void writerErrorClose(String message) {
/*  69 */     ResponseUtil.writerErrorClose(getResponse(), message);
/*     */   }
/*     */ 
/*     */   public void writerTimeout(String message) {
/*  73 */     ResponseUtil.writerTimeout(getResponse(), message);
/*     */   }
/*     */ 
/*     */   public void writerUploadSuccess(String message, String params) {
/*  77 */     writer(JSonUtil.getDWZMessage("success", message, params));
/*     */   }
/*     */ 
/*     */   public void writerSuccess(String message, String nvaTabId) {
/*  81 */     ResponseUtil.writerSuccess(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerSuccessNotClose(String message, String nvaTabId) {
/*  85 */     ResponseUtil.writerSuccessNotClose(getResponse(), message, 
/*  86 */       nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerCopy(String message, String nvaTabId) {
/*  90 */     ResponseUtil.writerCopy(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerDeploySuccess(String message, String nvaTabId) {
/*  94 */     ResponseUtil.writerDeploySuccess(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerApproveSuccess(String message, String nvaTabId)
/*     */   {
/*  99 */     ResponseUtil.writerApproveSuccess(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerDeleteSuccess(String message, String nvaTabId) {
/* 103 */     ResponseUtil.writerDeleteSuccess(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerDeleteSuccess(String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 108 */     ResponseUtil.writerDeleteSuccess(getResponse(), message, nvaTabId, 
/* 109 */       callbackType, forwardUrl);
/*     */   }
/*     */ 
/*     */   public void writerDeleteSuccessByFlexId(String message, String nvaTabId, String flexId)
/*     */   {
/* 114 */     ResponseUtil.writerDeleteSuccessByFlexId(getResponse(), message, 
/* 115 */       nvaTabId, flexId);
/*     */   }
/*     */ 
/*     */   public void writerError(String message, String nvaTabId) {
/* 119 */     ResponseUtil.writerError(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerTimeout(String message, String nvaTabId) {
/* 123 */     ResponseUtil.writerTimeout(getResponse(), message, nvaTabId);
/*     */   }
/*     */ 
/*     */   public void writerSuccess(String message, String nvaTabId, String callbackType)
/*     */   {
/* 128 */     ResponseUtil.writerSuccess(getResponse(), message, nvaTabId, 
/* 129 */       callbackType);
/*     */   }
/*     */ 
/*     */   public void writerSuccessByFlexId(String message, String nvaTabId, String flexId)
/*     */   {
/* 134 */     ResponseUtil.writerSuccessByFlexId(getResponse(), message, 
/* 135 */       nvaTabId, flexId);
/*     */   }
/*     */ 
/*     */   public void writerError(String message, String nvaTabId, String callbackType) {
/* 139 */     ResponseUtil.writerError(getResponse(), message, nvaTabId, 
/* 140 */       callbackType);
/*     */   }
/*     */ 
/*     */   public void writerTimeout(String message, String nvaTabId, String callbackType)
/*     */   {
/* 145 */     ResponseUtil.writerTimeout(getResponse(), message, nvaTabId, 
/* 146 */       callbackType);
/*     */   }
/*     */ 
/*     */   public void writerSuccess(String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 151 */     ResponseUtil.writerSuccess(getResponse(), message, nvaTabId, 
/* 152 */       callbackType, forwardUrl);
/*     */   }
/*     */ 
/*     */   public void writerError(String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 157 */     ResponseUtil.writerError(getResponse(), message, nvaTabId, 
/* 158 */       callbackType, forwardUrl);
/*     */   }
/*     */ 
/*     */   public void writerTimeout(String message, String nvaTabId, String callbackType, String forwardUrl)
/*     */   {
/* 163 */     ResponseUtil.writerTimeout(getResponse(), message, nvaTabId, 
/* 164 */       callbackType, forwardUrl);
/*     */   }
/*     */ 
/*     */   public String convertChineseFileName(String value) {
/* 168 */     String convetValue = "";
/*     */     try {
/* 170 */       String agent = getRequest().getHeader("USER-AGENT");
/* 171 */       if ((agent != null) && (-1 != agent.indexOf("MSIE")))
/* 172 */         convetValue = URLEncoder.encode(value, "UTF-8");
/* 173 */       else if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
/* 174 */         convetValue = new String(value.getBytes("UTF-8"), "ISO8859-1");
/*     */     }
/*     */     catch (IOException e) {
/* 177 */       e.printStackTrace();
/*     */     }
/* 179 */     return convetValue;
/*     */   }
/*     */ 
/*     */   public void writerFile(String fileName, String fileContent) {
/* 183 */     HttpServletResponse response = getResponse();
/* 184 */     String convetFilename = fileName;
/* 185 */     PrintWriter w = null;
/*     */     try {
/* 187 */       String agent = getRequest().getHeader("USER-AGENT");
/* 188 */       if ((agent != null) && (-1 != agent.indexOf("MSIE")))
/* 189 */         convetFilename = URLEncoder.encode(fileName, "UTF-8");
/* 190 */       else if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
/* 191 */         convetFilename = new String(fileName.getBytes("UTF-8"), 
/* 192 */           "ISO8859-1");
/*     */       }
/* 194 */       response.setContentType("text/plain");
/* 195 */       response.setHeader("Content-Disposition", "attachment; filename=" + 
/* 196 */         convetFilename);
/* 197 */       response.setHeader("Expires", "-1");
/* 198 */       response.setHeader("Pragma", "no-cache");
/* 199 */       response.setHeader("Cache-control", "no-cache");
/* 200 */       response.setHeader("Content-Type", "text/html; charset=UTF-8");
/* 201 */       w = response.getWriter();
/* 202 */       w.write(fileContent);
/* 203 */       w.flush();
/*     */     } catch (IOException e) {
/* 205 */       e.printStackTrace();
/*     */     } finally {
/* 207 */       if (w != null)
/* 208 */         w.close();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void putValueStack(String key, Object value)
/*     */   {
/* 219 */     ActionContext.getContext().getValueStack().set(key, value);
/*     */   }
/*     */ 
/*     */   protected HttpServletRequest getRequest()
/*     */   {
/* 227 */     return (HttpServletRequest)ActionContext.getContext().get(
/* 228 */       "com.opensymphony.xwork2.dispatcher.HttpServletRequest");
/*     */   }
/*     */ 
/*     */   protected HttpServletResponse getResponse()
/*     */   {
/* 236 */     return (HttpServletResponse)ActionContext.getContext().get(
/* 237 */       "com.opensymphony.xwork2.dispatcher.HttpServletResponse");
/*     */   }
/*     */ 
/*     */   protected Object putSession(String key, Object value)
/*     */   {
/* 248 */     return ActionContext.getContext().getSession().put(key, value);
/*     */   }
/*     */ 
/*     */   protected Object removeSession(String key)
/*     */   {
/* 258 */     return ActionContext.getContext().getSession().remove(key);
/*     */   }
/*     */ 
/*     */   protected String getIP() {
/* 262 */     HttpServletRequest request = ServletActionContext.getRequest();
/* 263 */     String ip = request.getHeader("x-forwarded-for");
/* 264 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 265 */       ip = request.getHeader("Proxy-Client-IP");
/*     */     }
/* 267 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 268 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*     */     }
/* 270 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 271 */       ip = request.getRemoteAddr();
/*     */     }
/* 273 */     return ip;
/*     */   }
/*     */ 
/*     */   protected int paramInt(String str) {
/* 277 */     return NumberUtils.getInt(getRequest().getParameter(str));
/*     */   }
/*     */ 
/*     */   protected int paramIntRange(String str, int min, int max) {
/* 281 */     int result = NumberUtils.getInt(getRequest().getParameter(str));
/* 282 */     if (result > max)
/* 283 */       return max;
/* 284 */     if (result < min)
/* 285 */       return min;
/* 286 */     return result;
/*     */   }
/*     */ 
/*     */   protected long paramLong(String str) {
/* 290 */     return NumberUtils.getLong(getRequest().getParameter(str));
/*     */   }
/*     */ 
/*     */   protected double paramDouble(String str) {
/* 294 */     return NumberUtils.getDouble(getRequest().getParameter(str));
/*     */   }
/*     */ 
/*     */   protected String paramString(String str) {
/* 298 */     String val = StringUtils.isNull(getRequest().getParameter(str));
/* 299 */     return val;
/*     */   }
/*     */ 
/*     */   public int getPageNum() {
/* 303 */     return this.pageNum;
/*     */   }
/*     */ 
/*     */   public void setPageNum(int pageNum) {
/* 307 */     this.pageNum = pageNum;
/* 308 */     this.page.setPageNum(pageNum);
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 312 */     return this.pageSize;
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 316 */     this.pageSize = pageSize;
/* 317 */     this.page.setPageSize(pageSize);
/*     */   }
/*     */ 
/*     */   public Page getPage() {
/* 321 */     return this.page;
/*     */   }
/*     */ 
/*     */   public void setPage(Page page) {
/* 325 */     this.page = page;
/*     */   }
/*     */ 
/*     */   public String getOrderField() {
/* 329 */     return this.orderField;
/*     */   }
/*     */ 
/*     */   public void setOrderField(String orderField) {
/* 333 */     this.orderField = orderField;
/* 334 */     this.page.setOrderField(orderField);
/*     */   }
/*     */ 
/*     */   public String getOrderDirection() {
/* 338 */     return this.orderDirection;
/*     */   }
/*     */ 
/*     */   public void setOrderDirection(String orderDirection) {
/* 342 */     this.orderDirection = orderDirection;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.AbstractAction
 * JD-Core Version:    0.6.2
 */