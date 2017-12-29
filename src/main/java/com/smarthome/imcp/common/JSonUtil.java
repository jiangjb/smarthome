/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.util.List;
/*     */ import net.sf.json.JSONArray;
/*     */ import net.sf.json.JSONException;
/*     */ import net.sf.json.JSONObject;
/*     */ import net.sf.json.JsonConfig;
/*     */ import net.sf.json.util.CycleDetectionStrategy;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ 
/*     */ public abstract class JSonUtil
/*     */ {
/*  15 */   private static JsonConfig jsonConfig = null;
/*     */ 
/*     */   public static synchronized JsonConfig getJsonConfig() {
/*  18 */     if (jsonConfig == null) {
/*  19 */       jsonConfig = new JsonConfig();
/*     */     }
/*     */ 
/*  36 */     return jsonConfig;
/*     */   }
/*     */ 
/*     */   public static Object getObject(JSONObject object, String key) {
/*  40 */     return getObject(object, key, "");
/*     */   }
/*     */ 
/*     */   public static Object getObject(JSONObject object, String key, Object defaultValue)
/*     */   {
/*  45 */     if ((object == null) || (!object.has(key))) {
/*  46 */       return defaultValue;
/*     */     }
/*  48 */     return object.get(key);
/*     */   }
/*     */ 
/*     */   public static String getString(JSONObject object, String key) {
/*  52 */     return getString(object, key, "");
/*     */   }
/*     */ 
/*     */   public static String getString(JSONObject object, String key, String defaultValue)
/*     */   {
/*  57 */     if ((object == null) || (!object.has(key))) {
/*  58 */       return defaultValue;
/*     */     }
/*  60 */     return object.getString(key);
/*     */   }
/*     */ 
/*     */   public static int getInt(JSONObject object, String key) {
/*  64 */     return getInt(object, key, 0);
/*     */   }
/*     */ 
/*     */   public static int getInt(JSONObject object, String key, int defaultValue) {
/*  68 */     if ((object == null) || (!object.has(key)))
/*  69 */       return defaultValue;
/*     */     try
/*     */     {
/*  72 */       return object.getInt(key); } catch (Exception e) {
/*     */     }
/*  74 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   public static Long getLong(JSONObject object, String key)
/*     */   {
/*  79 */     return getLong(object, key, new Long(0L));
/*     */   }
/*     */ 
/*     */   public static Long getLong(JSONObject object, String key, Long defaultValue) {
/*  83 */     if ((object == null) || (!object.has(key))) {
/*  84 */       if (defaultValue == null) {
/*  85 */         return null;
/*     */       }
/*  87 */       return new Long(defaultValue.longValue());
/*     */     }
/*     */     try {
/*  90 */       return Long.valueOf(object.getLong(key)); } catch (Exception e) {
/*     */     }
/*  92 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   public static Double getDouble(JSONObject object, String key)
/*     */   {
/*  97 */     return getDouble(object, key, new Double(0.0D));
/*     */   }
/*     */ 
/*     */   public static Double getDouble(JSONObject object, String key, Double defaultValue)
/*     */   {
/* 102 */     if ((object == null) || (!object.has(key))) {
/* 103 */       if (defaultValue == null) {
/* 104 */         return null;
/*     */       }
/* 106 */       return new Double(defaultValue.doubleValue());
/*     */     }
/*     */     try {
/* 109 */       return Double.valueOf(object.getDouble(key)); } catch (Exception e) {
/*     */     }
/* 111 */     return defaultValue;
/*     */   }
/*     */ 
/*     */   public static boolean getBoolean(JSONObject object, String key)
/*     */   {
/* 116 */     return getBoolean(object, key, false);
/*     */   }
/*     */ 
/*     */   public static boolean getBoolean(JSONObject object, String key, boolean defaultValue)
/*     */   {
/* 121 */     if ((object == null) || (!object.has(key))) {
/* 122 */       return defaultValue;
/*     */     }
/* 124 */     return object.getBoolean(key);
/*     */   }
/*     */ 
/*     */   public static JSONObject getJSONObject(JSONObject object, String key) {
/* 128 */     if ((object == null) || (!object.has(key))) {
/* 129 */       return new JSONObject();
/*     */     }
/* 131 */     return object.getJSONObject(key);
/*     */   }
/*     */ 
/*     */   public static JSONArray getJSONArray(JSONObject object, String key) {
/* 135 */     if ((object == null) || (!object.has(key))) {
/* 136 */       return new JSONArray();
/*     */     }
/* 138 */     return object.getJSONArray(key);
/*     */   }
/*     */ 
/*     */   public static String genJson(List list)
/*     */   {
/* 150 */     return genJson(list, null, true);
/*     */   }
/*     */ 
/*     */   public static String genJson(List list, String[] excludes)
/*     */   {
/* 155 */     return genJson(list, excludes, true);
/*     */   }
/*     */ 
/*     */   public static String genJson(List list, String[] excludes, boolean nullValue)
/*     */   {
/* 167 */     String json = "";
/* 168 */     if ((list == null) || (list.size() < 1)) {
/* 169 */       json = "{}";
/* 170 */       return json;
/*     */     }
/* 172 */     JSONArray jsonList = null;
/*     */     try {
/* 174 */       if (excludes == null) {
/* 175 */         jsonList = JSONArray.fromObject(list, getJsonConfig());
/*     */       } else {
/* 177 */         JsonConfig cfg = getJsonConfig();
/* 178 */         cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
/* 179 */         cfg.setExcludes(excludes);
/*     */ 
/* 181 */         jsonList = JSONArray.fromObject(list, cfg);
/*     */       }
/*     */     } catch (JSONException e) {
/* 184 */       e.printStackTrace();
/* 185 */       return "{}";
/*     */     }
/*     */ 
/* 188 */     json = jsonList.toString();
/* 189 */     return json;
/*     */   }
/*     */ 
/*     */   public static String genJson(List list, long count)
/*     */   {
/* 201 */     return genJson(list, count, null, true);
/*     */   }
/*     */ 
/*     */   public static String genJson(List list, long count, boolean nullValue)
/*     */   {
/* 213 */     return genJson(list, count, null, nullValue);
/*     */   }
/*     */ 
/*     */   public static String genJson(Object obj)
/*     */   {
/* 224 */     return genJson(obj, null, true);
/*     */   }
/*     */ 
/*     */   public static String genJson(Object obj, boolean nullValue)
/*     */   {
/* 236 */     return genJson(obj, null, nullValue);
/*     */   }
/*     */ 
/*     */   public static String genJson(List list, long count, String[] excludes, boolean nullValue)
/*     */   {
/* 254 */     String json = "";
/* 255 */     if ((list == null) || (list.size() < 1)) {
/* 256 */       json = "{'success':true,'results':'0','nodes':[]}";
/* 257 */       return json;
/*     */     }
/* 259 */     JSONArray jsonList = null;
/*     */     try {
/* 261 */       if (excludes == null) {
/* 262 */         jsonList = JSONArray.fromObject(list, getJsonConfig());
/*     */       } else {
/* 264 */         JsonConfig cfg = getJsonConfig();
/* 265 */         cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
/* 266 */         cfg.setExcludes(excludes);
/* 267 */         jsonList = JSONArray.fromObject(list, cfg);
/*     */       }
/*     */     } catch (JSONException e) {
/* 270 */       return "{'success':false,'results':'0','nodes':[]}";
/*     */     }
/*     */ 
/* 273 */     if (count <= 0L)
/* 274 */       json = "{'success':true,'results':'" + list.size() + "','nodes':" + 
/* 275 */         jsonList.toString() + "}";
/*     */     else {
/* 277 */       json = "{'success':true,'results':'" + count + "','nodes':" + 
/* 278 */         jsonList.toString() + "}";
/*     */     }
/* 280 */     return json;
/*     */   }
/*     */ 
/*     */   public static String genJson(Object obj, String[] excludes, boolean nullValue)
/*     */   {
/* 295 */     String json = "";
/* 296 */     if (obj == null) {
/* 297 */       json = "{'success':true,'results':'0','nodes':[]}";
/* 298 */       return json;
/*     */     }
/* 300 */     JSONObject jsonObject = null;
/*     */     try {
/* 302 */       if (excludes == null) {
/* 303 */         jsonObject = JSONObject.fromObject(obj, getJsonConfig());
/*     */       } else {
/* 305 */         JsonConfig cfg = getJsonConfig();
/* 306 */         cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
/* 307 */         cfg.setExcludes(excludes);
/* 308 */         jsonObject = JSONObject.fromObject(obj, cfg);
/*     */       }
/*     */     } catch (JSONException e) {
/* 311 */       return "{'success':false,'results':'0','nodes':[]}";
/*     */     }
/*     */ 
/* 314 */     json = "{'success':true,'results':'1','nodes':" + jsonObject.toString() + 
/* 315 */       "}";
/* 316 */     return json;
/*     */   }
/*     */ 
/*     */   public static String getErrorMessage(String message) {
/* 320 */     return "{success:false,results:0,msg:'" + message + "',nodes:[]}";
/*     */   }
/*     */ 
/*     */   public static String getSuccessMessage(String message) {
/* 324 */     return "{success:true,results:0,msg:'" + message + "',nodes:[]}";
/*     */   }
/*     */ 
/*     */   public static String getDWZMessage(String type, String message, String nvaTabId, String callbackType, String forwardUrl, String flexId)
/*     */   {
/* 330 */     if (callbackType == null) {
/* 331 */       callbackType = "";
/* 332 */       if ("success".equals(type)) {
/* 333 */         callbackType = "closeCurrent";
/*     */       }
/*     */     }
/* 336 */     if (StringUtils.isEmpty(forwardUrl))
/* 337 */       forwardUrl = "";
/*     */     else {
/* 339 */       callbackType = "forward";
/*     */     }
/* 341 */     String statusCode = "200";
/* 342 */     if ("error".equals(type))
/* 343 */       statusCode = "300";
/* 344 */     else if ("timeout".equals(type))
/* 345 */       statusCode = "301";
/*     */     else {
/* 347 */       statusCode = "200";
/*     */     }
/* 349 */     StringBuffer sb = new StringBuffer();
/* 350 */     sb.append("{");
/* 351 */     sb.append("\"statusCode\":");
/* 352 */     sb.append("\"");
/* 353 */     sb.append(statusCode);
/* 354 */     sb.append("\"");
/* 355 */     sb.append(",\"message\":");
/* 356 */     sb.append("\"");
/* 357 */     sb.append(message);
/* 358 */     sb.append("\"");
/* 359 */     if (StringUtils.isNotEmpty(nvaTabId)) {
/* 360 */       sb.append(",\"navTabId\":");
/* 361 */       sb.append("\"");
/* 362 */       sb.append(nvaTabId);
/* 363 */       sb.append("\"");
/*     */     }
/* 365 */     sb.append(", \"forwardUrl\":");
/* 366 */     sb.append("\"");
/* 367 */     sb.append(forwardUrl);
/* 368 */     sb.append("\"");
/* 369 */     sb.append(", \"callbackType\":");
/* 370 */     sb.append("\"");
/* 371 */     sb.append(callbackType);
/* 372 */     sb.append("\"");
/* 373 */     if (StringUtils.isNotEmpty(flexId)) {
/* 374 */       sb.append(", \"flexId\":");
/* 375 */       sb.append("\"");
/* 376 */       sb.append(flexId);
/* 377 */       sb.append("\"");
/*     */     }
/* 379 */     sb.append("}");
/* 380 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   public static String getDWZMessage(String type, String message, String params)
/*     */   {
/* 385 */     String nvaTabId = "";
/* 386 */     if (StringUtils.isEmpty(nvaTabId)) {
/* 387 */       nvaTabId = "";
/*     */     }
/* 389 */     String callbackType = "";
/* 390 */     if (StringUtils.isEmpty(callbackType)) {
/* 391 */       callbackType = "";
/* 392 */       if ("success".equals(type)) {
/* 393 */         callbackType = "closeCurrent";
/*     */       }
/*     */     }
/* 396 */     String forwardUrl = "";
/* 397 */     if (StringUtils.isEmpty(forwardUrl))
/* 398 */       forwardUrl = "";
/*     */     else {
/* 400 */       callbackType = "forward";
/*     */     }
/* 402 */     String statusCode = "200";
/* 403 */     if ("error".equals(type))
/* 404 */       statusCode = "300";
/* 405 */     else if ("timeout".equals(type))
/* 406 */       statusCode = "301";
/*     */     else {
/* 408 */       statusCode = "200";
/*     */     }
/* 410 */     if (StringUtils.isNotEmpty(params)) {
/* 411 */       params = "," + params;
/*     */     }
/* 413 */     return "{\"statusCode\":\"" + statusCode + "\",\"message\":\"" + 
/* 414 */       message + "\",\"navTabId\":\"" + nvaTabId + 
/* 415 */       "\", \"forwardUrl\":\"" + forwardUrl + 
/* 416 */       "\", \"callbackType\":\"" + callbackType + "\"" + params + 
/* 417 */       "}";
/*     */   }
/*     */ 
/*     */   public static String genJsonDWZ(List list, long count)
/*     */   {
/* 422 */     String json = "";
/* 423 */     if ((list == null) || (list.size() < 1)) {
/* 424 */       json = "{'statusCode':200,message:'','results':0,'nodes':[],navTabId:'',forwardUrl:'',callbackType:''}";
/* 425 */       return json;
/*     */     }
/* 427 */     JSONArray jsonList = null;
/*     */     try {
/* 429 */       jsonList = JSONArray.fromObject(list, getJsonConfig());
/*     */     } catch (JSONException e) {
/* 431 */       return "{'statusCode':200,message:'','results':0,'nodes':[],navTabId:'',forwardUrl:'',callbackType:''}";
/*     */     }
/*     */ 
/* 434 */     if (count <= 0L)
/* 435 */       json = "{'statusCode':200,message:'','results':" + list.size() + 
/* 436 */         ",'nodes':" + jsonList.toString() + 
/* 437 */         ",navTabId:'',forwardUrl:'',callbackType:''}";
/*     */     else {
/* 439 */       json = "{'statusCode':200,message:'','results':" + count + 
/* 440 */         ",'nodes':" + jsonList.toString() + 
/* 441 */         ",navTabId:'',forwardUrl:'',callbackType:''}";
/*     */     }
/* 443 */     return json;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.JSonUtil
 * JD-Core Version:    0.6.2
 */