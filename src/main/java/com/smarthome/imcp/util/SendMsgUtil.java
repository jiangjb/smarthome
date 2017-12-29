/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
          import java.util.List;
/*     */ import java.util.Map;
          import com.smarthome.imcp.common.Map2List;
/*     */ 
/*     */ @SuppressWarnings("unused")
		  public class SendMsgUtil
/*     */ {
/*     */   @SuppressWarnings({ "static-access", "rawtypes", "unchecked", "deprecation" })
          public static String sendMsg(String mobile, String content)
/*     */     throws UnsupportedEncodingException
/*     */   { 
	          Map2List map2List=new Map2List();
/*  31 */     Map params = new HashMap();
//			  List<String> keys=(List<String>) params.keySet();
			  List<String> keys=map2List.mapTransitionList(params);
			  System.out.println("maptolist.......");
/*     */ 
/*  33 */     params.put("mobile", mobile);
/*     */ 
/*  35 */     params.put("nr", URLEncoder.encode(content, "UTF-8"));
/*  36 */     String string = null;
/*  37 */     String contents = null;
/*     */ 
/*  39 */     String encode = URLEncoder.encode(content);
/*  40 */     for (String key : keys) {
/*  41 */       if ("mobile".equals(key)) {
/*  42 */         string = (String)params.get(key);
/*     */       }
/*     */ 
/*  45 */       if ("nr".equals(key)) {
/*  46 */         contents = (String)params.get(key);
/*     */       }
/*     */     }
/*     */ 
/*  50 */     String url = "http://222.73.117.158/msg/HttpBatchSendSM?account=Jkdz888&mobile=" + mobile + "&pswd=Hificat882&needstatus=1&msg=" + contents;
/*  51 */     return HttpRequestUtil.getRequest(url, mobile);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings({ "unchecked", "static-access", "deprecation", "rawtypes" })
			public static String aiBoRuisendMsg(String mobile, String content)
/*     */     throws UnsupportedEncodingException
/*     */   {
			  Map2List map2List=new Map2List();
/*  65 */     Map params = new HashMap();
///*     */ 	  List<String> keys=(List<String>) params.keySet();
			  List<String> keys=map2List.mapTransitionList(params);
			  System.out.println("siChuang.......");
/*  67 */     params.put("mobile", mobile);
/*     */ 
/*  69 */     params.put("nr", URLEncoder.encode(content, "UTF-8"));
		  	  String string = null;
/*  71 */     String contents = null;
/*     */ 
			  String encode = URLEncoder.encode(content);
/*  74 */     for (String key : keys) {
/*  75 */       if ("mobile".equals(key)) {
/*  76 */         string = (String)params.get(key);
/*     */       }
/*     */ 
/*  79 */       if ("nr".equals(key)) {
/*  80 */         contents = (String)params.get(key);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  85 */     String url = "http://222.73.117.169/msg/HttpBatchSendSM?account=N1168435&pswd=Ps0459e5&mobile=" + mobile + "&needstatus=true&msg=" + contents;
/*  86 */     return HttpRequestUtil.getRequest(url, mobile);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings({ "unchecked", "static-access", "deprecation", "rawtypes" })
			public static String siChuangSendMsg(String mobile, String content)
/*     */     throws UnsupportedEncodingException
/*     */   {
	          Map2List map2List=new Map2List();
/*  99 */     Map params = new HashMap();
///*     */     List<String> keys=(List<String>) params.keySet();
			  List<String> keys=map2List.mapTransitionList(params);
			  System.out.println("siChuang.......");
/* 101 */     params.put("mobile", mobile);
/*     */ 
/* 103 */     params.put("nr", URLEncoder.encode(content, "UTF-8"));
/* 104 */     String string = null;
/* 105 */     String contents = null;
/*     */ 
/* 107 */     String encode = URLEncoder.encode(content);
/* 108 */     for (String key : keys) {
/* 109 */       if ("mobile".equals(key)) {
/* 110 */         string = (String)params.get(key);
/*     */       }
/*     */ 
/* 113 */       if ("nr".equals(key)) {
/* 114 */         contents = (String)params.get(key);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 119 */     String url = "http://sms.253.com/msg/send?un=N2720392&pw=Ps0459e5&phone=" + mobile + "&msg=" + contents + "&rd=1";
/* 120 */     return HttpRequestUtil.getRequest(url, mobile);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings({ "rawtypes", "unchecked", "static-access", "deprecation" })
            public static String fengTingSendMsg(String mobile, String content)
/*     */     throws UnsupportedEncodingException
/*     */   {
	          Map2List map2List=new Map2List();
/* 133 */     Map params = new HashMap();
///*     */     List<String> keys=(List<String>) params.keySet();
			  List<String> keys=map2List.mapTransitionList(params);
			  System.out.println("fengting.......");
/* 135 */     params.put("mobile", mobile);
/*     */ 
/* 137 */     params.put("nr", URLEncoder.encode(content, "UTF-8"));
/* 138 */     String string = null;
/* 139 */     String contents = null;
/*     */ 
/* 141 */     String encode = URLEncoder.encode(content);
/* 142 */     for (String key : keys) {
/* 143 */       if ("mobile".equals(key)) {
/* 144 */         string = (String)params.get(key);
/*     */       }
/*     */ 
/* 147 */       if ("nr".equals(key)) {
/* 148 */         contents = (String)params.get(key);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 153 */     String url = "http://sms.253.com/msg/send?un=N4558391&pw=uTrj4NlkS&phone=" + mobile + "&msg=" + contents + "&rd=1";
/* 154 */     return HttpRequestUtil.getRequest(url, mobile);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings({ "rawtypes", "unchecked", "static-access", "deprecation" })
            public static String maiBaoSendMsg(String mobile, String content)
/*     */     throws UnsupportedEncodingException
/*     */   {
	          Map2List map2List=new Map2List();
/* 167 */     Map params = new HashMap();
///*     */     List<String> keys=(List<String>) params.keySet();
			  List<String> keys=map2List.mapTransitionList(params);
			  System.out.println("maptolist.......");
/* 169 */     params.put("mobile", mobile);
/*     */ 
/* 171 */     params.put("nr", URLEncoder.encode(content, "UTF-8"));
/* 172 */     String string = null;
/* 173 */     String contents = null;
/*     */ 
/* 175 */     String encode = URLEncoder.encode(content);
/* 176 */     for (String key : keys) {
/* 177 */       if ("mobile".equals(key)) {
/* 178 */         string = (String)params.get(key);
/*     */       }
/*     */ 
/* 181 */       if ("nr".equals(key)) {
/* 182 */         contents = (String)params.get(key);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 187 */     String url = "http://sms.253.com/msg/send?un=N9159092&pw=e432PMNLmJ94cf&phone=" + mobile + "&msg=" + contents + "&rd=1";
/* 188 */     return HttpRequestUtil.getRequest(url, mobile);
/*     */   }
/*     */ 
/*     */   @SuppressWarnings({ "rawtypes", "unchecked", "static-access", "deprecation" })
			public static String lavo(String mobile, String content)
/*     */     throws UnsupportedEncodingException
/*     */   {
			  Map2List map2List=new Map2List();
/* 202 */     Map params = new HashMap();
///*     */     List<String> keys=(List<String>) params.keySet();
			  List<String> keys=map2List.mapTransitionList(params);
			  System.out.println("lavo.......");
/* 204 */     params.put("mobile", mobile);
/*     */ 
/* 206 */     params.put("nr", URLEncoder.encode(content, "UTF-8"));
			  String string = null;
/* 208 */     String contents = null;
/*     */ 
			  String encode = URLEncoder.encode(content);
/* 211 */     for (String key : keys) {
/* 212 */       if ("mobile".equals(key)) {
/* 213 */         string = (String)params.get(key);
/*     */       }
/*     */ 
/* 216 */       if ("nr".equals(key)) {
/* 217 */         contents = (String)params.get(key);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 222 */     String url = "http://sms.253.com/msg/send?un=N5271492&pw=ePNXqiwdYz5264&phone=" + mobile + "&msg=" + contents + "&rd=1";
/* 223 */     return HttpRequestUtil.getRequest(url, mobile);
/*     */   }
/*     */ 
/*     */   public String createRandomVcode()
/*     */   {
/* 235 */     String vcode = "";
/* 236 */     for (int i = 0; i < 6; i++) {
/* 237 */       vcode = vcode + (int)(Math.random() * 9.0D);
/*     */     }
/* 239 */     return vcode;
/*     */   }
/*     */ 
/*     */   public String createRandomVcodes()
/*     */   {
/* 251 */     String vcode = "";
/* 252 */     for (int i = 0; i < 3; i++) {
/* 253 */       vcode = vcode + (int)(Math.random() * 9.0D);
/*     */     }
/* 255 */     return vcode;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws UnsupportedEncodingException
/*     */   {
/* 267 */     SendMsgUtil s = new SendMsgUtil();
/* 268 */     s.createRandomVcode();
/* 269 */     System.out.println(maiBaoSendMsg("15105873889", "【智能屋】尊敬的用户，您的验证码为 :522852"));
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.SendMsgUtil
 * JD-Core Version:    0.6.2
 */