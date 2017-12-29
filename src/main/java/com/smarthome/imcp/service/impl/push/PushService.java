/*     */ package com.smarthome.imcp.service.impl.push;
/*     */ 
/*     */ import com.gexin.rp.sdk.base.IPushResult;
/*     */ import com.gexin.rp.sdk.base.impl.ListMessage;
/*     */ import com.gexin.rp.sdk.base.impl.SingleMessage;
/*     */ import com.gexin.rp.sdk.base.impl.Target;
/*     */ import com.gexin.rp.sdk.base.payload.APNPayload;
/*     */ import com.gexin.rp.sdk.base.payload.APNPayload.DictionaryAlertMsg;
/*     */ import com.gexin.rp.sdk.exceptions.RequestException;
/*     */ import com.gexin.rp.sdk.http.IGtPush;
/*     */ import com.gexin.rp.sdk.template.APNTemplate;
/*     */ import com.gexin.rp.sdk.template.LinkTemplate;
/*     */ import com.gexin.rp.sdk.template.NotificationTemplate;
/*     */ import com.gexin.rp.sdk.template.TransmissionTemplate;
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ public class PushService
/*     */ {
/*  24 */   String appId = "";
/*     */ 
/*  26 */   String appkey = "";
/*     */ 
/*  30 */   String master = "";
/*     */ 
/*  32 */   String CID = "722d4d309ae460a9ee18cd523997a7ac";
/*     */ 
/*  35 */   String host = "http://sdk.open.api.igexin.com/apiex.htm";
/*     */ 
/*  37 */   String url = "http://sdk.open.api.igexin.com/serviceex";
/*     */ 
/*     */   public static void main(String[] args) {
/*  40 */     PushService pushService = new PushService();
/*     */ 
/*  42 */     String cid = "722d4d309ae460a9ee18cd523997a7ac";
/*     */ 
/*  44 */     pushService.pushToSingle(cid, "警报", GlobalMethod.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "\n\r  尊敬的客户，你的（设备的xxxxx）  出现漏水故障", "2015-07-03 10:59:30 \n\r  尊敬的客户，你的（设备的xxxxx）  出现漏水故障");
/*     */   }
/*     */ 
/*     */   public void pushToList(List<String> list, String title, String text, String content)
/*     */   {
/*  49 */     IGtPush push = new IGtPush(this.host, this.appkey, this.master);
/*  50 */     if ((list == null) || (list.isEmpty())) {
/*  51 */       return;
/*     */     }
/*     */ 
/*  54 */     NotificationTemplate template = notificationTemplateDemo(title, text, content);
/*     */ 
/*  56 */     ListMessage message = new ListMessage();
/*  57 */     message.setOffline(true);
/*     */ 
/*  59 */     message.setOfflineExpireTime(86400000L);
/*  60 */     message.setData(template);
/*  61 */     message.setPushNetWorkType(0);
/*     */ 
/*  63 */     List targets = new ArrayList(list.size());
/*     */ 
/*  65 */     for (String cid : list) {
/*  66 */       Target target = new Target();
/*  67 */       target.setAppId(this.appId);
/*  68 */       target.setClientId(cid);
/*  69 */       targets.add(target);
/*     */     }
/*     */ 
/*  73 */     String taskId = push.getContentId(message);
/*     */ 
/*  75 */     IPushResult ret = push.pushMessageToList(taskId, targets);
/*     */ 
/*  77 */     if (ret != null)
/*  78 */       System.out.println(ret.getResponse().toString());
/*     */     else
/*  80 */       System.out.println("服务器响应异常");
/*     */   }
/*     */ 
/*     */   public void pushToSingle(String cid, String title, String text, String content)
/*     */   {
/*  85 */     IGtPush push = new IGtPush(this.host, this.appkey, this.master);
/*  86 */     if (StringUtils.isEmpty(cid)) {
/*  87 */       return;
/*     */     }
/*     */ 
/*  90 */     NotificationTemplate template = notificationTemplateDemo(title, text, content);
/*  91 */     template.setLogo("push.png");
/*     */ 
/*  93 */     SingleMessage message = new SingleMessage();
/*  94 */     message.setOffline(true);
/*     */ 
/*  96 */     message.setOfflineExpireTime(86400000L);
/*  97 */     message.setData(template);
/*  98 */     message.setPushNetWorkType(0);
/*  99 */     Target target = new Target();
/*     */ 
/* 101 */     target.setAppId(this.appId);
/* 102 */     target.setClientId(cid);
/*     */ 
/* 106 */     IPushResult ret = null;
/*     */     try {
/* 108 */       ret = push.pushMessageToSingle(message, target);
/*     */     } catch (RequestException e) {
/* 110 */       e.printStackTrace();
/* 111 */       ret = push.pushMessageToSingle(message, target, e.getRequestId());
/*     */     }
/* 113 */     if (ret != null)
/* 114 */       System.out.println(ret.getResponse().toString());
/*     */     else
/* 116 */       System.out.println("服务器响应异常");
/*     */   }
/*     */ 
/*     */   public void apnPush(String cid, String title, String text, String content)
/*     */   {
/*     */     try {
/* 122 */       System.err.println(cid);
/* 123 */       System.err.println(title);
/* 124 */       System.err.println(text);
/* 125 */       System.err.println(content);
/* 126 */       System.err.println(this.url);
/* 127 */       System.err.println(this.appkey);
/* 128 */       System.err.println(this.master);
/* 129 */       IGtPush push = new IGtPush(this.url, this.appkey, this.master);
/* 130 */       APNTemplate t = new APNTemplate();
/* 131 */       APNPayload apnpayload = new APNPayload();
/* 132 */       apnpayload.setSound("");
/* 133 */       APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
/* 134 */       alertMsg.setTitle(title);
/* 135 */       alertMsg.setBody(text);
/* 136 */       alertMsg.setTitleLocKey(title);
/* 137 */       alertMsg.setActionLocKey(title);
/* 138 */       apnpayload.setAlertMsg(alertMsg);
/*     */ 
/* 140 */       t.setAPNInfo(apnpayload);
/* 141 */       SingleMessage sm = new SingleMessage();
/* 142 */       sm.setData(t);
/* 143 */       IPushResult ret0 = push.pushAPNMessageToSingle(this.appId, cid, sm);
/* 144 */       System.out.println("<> " + ret0.getResponse());
/*     */ 
/* 146 */       pushToSingle(cid, title, text, content);
/*     */     } catch (Exception e) {
/* 148 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void apnPush(List<String> list, String title, String text, String content) {
/*     */     try {
/* 154 */       IGtPush push = new IGtPush(this.url, this.appkey, this.master);
/* 155 */       APNTemplate t = new APNTemplate();
/* 156 */       APNPayload apnpayload = new APNPayload();
/* 157 */       apnpayload.setSound("");
/* 158 */       APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
/* 159 */       alertMsg.setTitle(title);
/* 160 */       alertMsg.setBody(text);
/* 161 */       alertMsg.setTitleLocKey(title);
/* 162 */       alertMsg.setActionLocKey(title);
/* 163 */       apnpayload.setAlertMsg(alertMsg);
/*     */ 
/* 165 */       t.setAPNInfo(apnpayload);
/* 166 */       ListMessage sm = new ListMessage();
/* 167 */       sm.setData(t);
/*     */ 
/* 170 */       String taskId = push.getContentId(sm);
/*     */ 
/* 172 */       IPushResult ret0 = push.pushAPNMessageToList(text, taskId, list);
/* 173 */       System.out.println(ret0.getResponse());
/*     */ 
/* 175 */       pushToList(list, title, text, content);
/*     */     } catch (Exception e) {
/* 177 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public LinkTemplate linkTemplateDemo(String tile, String text, String url) {
/* 182 */     LinkTemplate template = new LinkTemplate();
/*     */ 
/* 184 */     template.setAppId(this.appId);
/* 185 */     template.setAppkey(this.appkey);
/*     */ 
/* 188 */     template.setTitle(tile);
/* 189 */     template.setText(text);
/*     */ 
/* 191 */     template.setLogo("push.png");
/*     */ 
/* 193 */     template.setLogoUrl("");
/*     */ 
/* 195 */     template.setIsRing(true);
/* 196 */     template.setIsVibrate(true);
/* 197 */     template.setIsClearable(true);
/*     */ 
/* 199 */     template.setUrl(url);
/* 200 */     return template;
/*     */   }
/*     */ 
/*     */   public NotificationTemplate notificationTemplateDemo(String tile, String text, String content) {
/* 204 */     NotificationTemplate template = new NotificationTemplate();
/*     */ 
/* 206 */     template.setAppId(this.appId);
/* 207 */     template.setAppkey(this.appkey);
/*     */ 
/* 209 */     template.setTitle(tile);
/* 210 */     template.setText(text);
/*     */ 
/* 212 */     template.setLogo("push.png");
/*     */ 
/* 214 */     template.setLogoUrl("");
/*     */ 
/* 216 */     template.setIsRing(true);
/* 217 */     template.setIsVibrate(true);
/* 218 */     template.setIsClearable(true);
/*     */ 
/* 220 */     template.setTransmissionType(2);
/* 221 */     template.setTransmissionContent(content);
/* 222 */     return template;
/*     */   }
/*     */ 
/*     */   public TransmissionTemplate transmissionTemplateDemo(String content) {
/* 226 */     TransmissionTemplate template = new TransmissionTemplate();
/* 227 */     template.setAppId(this.appId);
/* 228 */     template.setAppkey(this.appkey);
/*     */ 
/* 230 */     template.setTransmissionType(2);
/* 231 */     template.setTransmissionContent(content);
/* 232 */     return template;
/*     */   }
/*     */ 
/*     */   public String getAppId() {
/* 236 */     return this.appId;
/*     */   }
/*     */ 
/*     */   public void setAppId(String appId) {
/* 240 */     this.appId = appId;
/*     */   }
/*     */ 
/*     */   public String getAppkey() {
/* 244 */     return this.appkey;
/*     */   }
/*     */ 
/*     */   public void setAppkey(String appkey) {
/* 248 */     this.appkey = appkey;
/*     */   }
/*     */ 
/*     */   public String getMaster() {
/* 252 */     return this.master;
/*     */   }
/*     */ 
/*     */   public void setMaster(String master) {
/* 256 */     this.master = master;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.push.PushService
 * JD-Core Version:    0.6.2
 */