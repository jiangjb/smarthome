/*     */ package io.rong.rong;
/*     */ 
/*     */ import io.rong.models.ChatroomInfo;
/*     */ import io.rong.models.FormatType;
/*     */ import io.rong.models.GroupInfo;
/*     */ import io.rong.models.Message;
/*     */ import io.rong.models.SdkHttpResult;
/*     */ import io.rong.util.HttpUtil;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ApiHttpClient
/*     */ {
/*     */   private static final String RONGCLOUDURI = "http://api.cn.ronghub.com";
/*     */   private static final String UTF8 = "UTF-8";
/*     */ 
/*     */   public static SdkHttpResult getToken(String appKey, String appSecret, String userId, String userName, String portraitUri, FormatType format)
/*     */     throws Exception
/*     */   {
/*  25 */     HttpURLConnection conn = 
/*  26 */       HttpUtil.CreatePostHttpConnection(appKey, appSecret, "http://api.cn.ronghub.com/user/getToken." + 
/*  27 */       format.toString());
/*     */ 
/*  29 */     StringBuilder sb = new StringBuilder();
/*  30 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/*  31 */     sb.append("&name=").append(URLEncoder.encode(userName == null ? "" : userName, "UTF-8"));
/*  32 */     sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri == null ? "" : portraitUri, "UTF-8"));
/*  33 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/*  35 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult checkOnline(String appKey, String appSecret, String userId, FormatType format)
/*     */     throws Exception
/*     */   {
/*  42 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/*  43 */       appSecret, 
/*  44 */       "http://api.cn.ronghub.com/user/checkOnline." + format.toString());
/*     */ 
/*  46 */     StringBuilder sb = new StringBuilder();
/*  47 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/*  48 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/*  50 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult refreshUser(String appKey, String appSecret, String userId, String userName, String portraitUri, FormatType format)
/*     */     throws Exception
/*     */   {
/*  58 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/*  59 */       appSecret, "http://api.cn.ronghub.com/user/refresh." + format.toString());
/*     */ 
/*  61 */     StringBuilder sb = new StringBuilder();
/*  62 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/*  63 */     sb.append("&name=").append(URLEncoder.encode(userName == null ? "" : userName, "UTF-8"));
/*  64 */     sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri == null ? "" : portraitUri, "UTF-8"));
/*     */ 
/*  66 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/*  68 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult blockUser(String appKey, String appSecret, String userId, int minute, FormatType format)
/*     */     throws Exception
/*     */   {
/*  75 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/*  76 */       appSecret, "http://api.cn.ronghub.com/user/block." + format.toString());
/*     */ 
/*  78 */     StringBuilder sb = new StringBuilder();
/*  79 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/*  80 */     sb.append("&minute=").append(
/*  81 */       URLEncoder.encode(String.valueOf(minute), "UTF-8"));
/*     */ 
/*  83 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/*  85 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult unblockUser(String appKey, String appSecret, String userId, FormatType format)
/*     */     throws Exception
/*     */   {
/*  92 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/*  93 */       appSecret, "http://api.cn.ronghub.com/user/unblock." + format.toString());
/*     */ 
/*  95 */     StringBuilder sb = new StringBuilder();
/*  96 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/*     */ 
/*  98 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 100 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult queryBlockUsers(String appKey, String appSecret, FormatType format)
/*     */     throws Exception
/*     */   {
/* 107 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 108 */       appSecret, 
/* 109 */       "http://api.cn.ronghub.com/user/block/query." + format.toString());
/*     */ 
/* 111 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult blackUser(String appKey, String appSecret, String userId, List<String> blackUserIds, FormatType format)
/*     */     throws Exception
/*     */   {
/* 119 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 120 */       appSecret, 
/* 121 */       "http://api.cn.ronghub.com/user/blacklist/add." + format.toString());
/*     */ 
/* 123 */     StringBuilder sb = new StringBuilder();
/* 124 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/* 125 */     if (blackUserIds != null) {
/* 126 */       for (String blackId : blackUserIds) {
/* 127 */         sb.append("&blackUserId=").append(
/* 128 */           URLEncoder.encode(blackId, "UTF-8"));
/*     */       }
/*     */     }
/*     */ 
/* 132 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 134 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult unblackUser(String appKey, String appSecret, String userId, List<String> blackUserIds, FormatType format)
/*     */     throws Exception
/*     */   {
/* 142 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 143 */       appSecret, 
/* 144 */       "http://api.cn.ronghub.com/user/blacklist/remove." + format.toString());
/*     */ 
/* 146 */     StringBuilder sb = new StringBuilder();
/* 147 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/* 148 */     if (blackUserIds != null) {
/* 149 */       for (String blackId : blackUserIds) {
/* 150 */         sb.append("&blackUserId=").append(
/* 151 */           URLEncoder.encode(blackId, "UTF-8"));
/*     */       }
/*     */     }
/*     */ 
/* 155 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 157 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult QueryblackUser(String appKey, String appSecret, String userId, FormatType format)
/*     */     throws Exception
/*     */   {
/* 164 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 165 */       appSecret, 
/* 166 */       "http://api.cn.ronghub.com/user/blacklist/query." + format.toString());
/*     */ 
/* 168 */     StringBuilder sb = new StringBuilder();
/* 169 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/*     */ 
/* 171 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 173 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult createGroup(String appKey, String appSecret, List<String> userIds, String groupId, String groupName, FormatType format)
/*     */     throws Exception
/*     */   {
/* 181 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 182 */       appSecret, "http://api.cn.ronghub.com/group/create." + format.toString());
/*     */ 
/* 184 */     StringBuilder sb = new StringBuilder();
/* 185 */     sb.append("groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 186 */     sb.append("&groupName=").append(URLEncoder.encode(groupName == null ? "" : groupName, "UTF-8"));
/* 187 */     if (userIds != null) {
/* 188 */       for (String id : userIds) {
/* 189 */         sb.append("&userId=").append(URLEncoder.encode(id, "UTF-8"));
/*     */       }
/*     */     }
/* 192 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 194 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult joinGroup(String appKey, String appSecret, String userId, String groupId, String groupName, FormatType format)
/*     */     throws Exception
/*     */   {
/* 202 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 203 */       appSecret, "http://api.cn.ronghub.com/group/join." + format.toString());
/*     */ 
/* 205 */     StringBuilder sb = new StringBuilder();
/* 206 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/* 207 */     sb.append("&groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 208 */     sb.append("&groupName=").append(URLEncoder.encode(groupName == null ? "" : groupName, "UTF-8"));
/* 209 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 211 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult joinGroupBatch(String appKey, String appSecret, List<String> userIds, String groupId, String groupName, FormatType format)
/*     */     throws Exception
/*     */   {
/* 219 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 220 */       appSecret, "http://api.cn.ronghub.com/group/join." + format.toString());
/*     */ 
/* 222 */     StringBuilder sb = new StringBuilder();
/* 223 */     sb.append("groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 224 */     sb.append("&groupName=").append(URLEncoder.encode(groupName == null ? "" : groupName, "UTF-8"));
/* 225 */     if (userIds != null) {
/* 226 */       for (String id : userIds) {
/* 227 */         sb.append("&userId=").append(URLEncoder.encode(id, "UTF-8"));
/*     */       }
/*     */     }
/* 230 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 232 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult quitGroup(String appKey, String appSecret, String userId, String groupId, FormatType format)
/*     */     throws Exception
/*     */   {
/* 239 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 240 */       appSecret, "http://api.cn.ronghub.com/group/quit." + format.toString());
/*     */ 
/* 242 */     StringBuilder sb = new StringBuilder();
/* 243 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/* 244 */     sb.append("&groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 245 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 247 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult quitGroupBatch(String appKey, String appSecret, List<String> userIds, String groupId, FormatType format)
/*     */     throws Exception
/*     */   {
/* 255 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 256 */       appSecret, "http://api.cn.ronghub.com/group/quit." + format.toString());
/*     */ 
/* 258 */     StringBuilder sb = new StringBuilder();
/* 259 */     sb.append("groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 260 */     if (userIds != null) {
/* 261 */       for (String id : userIds) {
/* 262 */         sb.append("&userId=").append(URLEncoder.encode(id, "UTF-8"));
/*     */       }
/*     */     }
/*     */ 
/* 266 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 268 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult dismissGroup(String appKey, String appSecret, String userId, String groupId, FormatType format)
/*     */     throws Exception
/*     */   {
/* 275 */     HttpURLConnection conn = 
/* 276 */       HttpUtil.CreatePostHttpConnection(appKey, appSecret, "http://api.cn.ronghub.com/group/dismiss." + 
/* 277 */       format.toString());
/*     */ 
/* 279 */     StringBuilder sb = new StringBuilder();
/* 280 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/* 281 */     sb.append("&groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 282 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 284 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult syncGroup(String appKey, String appSecret, String userId, List<GroupInfo> groups, FormatType format)
/*     */     throws Exception
/*     */   {
/* 292 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 293 */       appSecret, "http://api.cn.ronghub.com/group/sync." + format.toString());
/*     */ 
/* 295 */     StringBuilder sb = new StringBuilder();
/* 296 */     sb.append("userId=").append(URLEncoder.encode(userId, "UTF-8"));
/* 297 */     if (groups != null) {
/* 298 */       for (GroupInfo info : groups) {
/* 299 */         if (info != null) {
/* 300 */           sb.append(
/* 301 */             String.format("&group[%s]=", new Object[] { 
/* 302 */             URLEncoder.encode(info.getId(), "UTF-8") }))
/* 303 */             .append(URLEncoder.encode(info.getName(), "UTF-8"));
/*     */         }
/*     */       }
/*     */     }
/* 307 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 309 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult refreshGroupInfo(String appKey, String appSecret, String groupId, String groupName, FormatType format)
/*     */     throws Exception
/*     */   {
/* 317 */     HttpURLConnection conn = 
/* 318 */       HttpUtil.CreatePostHttpConnection(appKey, appSecret, "http://api.cn.ronghub.com/group/refresh." + 
/* 319 */       format.toString());
/*     */ 
/* 321 */     StringBuilder sb = new StringBuilder();
/* 322 */     sb.append("groupId=").append(URLEncoder.encode(groupId, "UTF-8"));
/* 323 */     sb.append("&groupName=").append(URLEncoder.encode(groupName == null ? "" : groupName, "UTF-8"));
/*     */ 
/* 325 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 327 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult refreshGroupInfo(String appKey, String appSecret, GroupInfo group, FormatType format)
/*     */     throws Exception
/*     */   {
/* 335 */     HttpURLConnection conn = 
/* 336 */       HttpUtil.CreatePostHttpConnection(appKey, appSecret, "http://api.cn.ronghub.com/group/refresh." + 
/* 337 */       format.toString());
/*     */ 
/* 339 */     StringBuilder sb = new StringBuilder();
/* 340 */     sb.append("groupId=").append(URLEncoder.encode(group.getId(), "UTF-8"));
/* 341 */     sb.append("&groupName=").append(
/* 342 */       URLEncoder.encode(group.getName(), "UTF-8"));
/*     */ 
/* 344 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 346 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult publishMessage(String appKey, String appSecret, String fromUserId, List<String> toUserIds, Message msg, FormatType format)
/*     */     throws Exception
/*     */   {
/* 354 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 355 */       appSecret, 
/* 356 */       "http://api.cn.ronghub.com/message/private/publish." + format.toString());
/*     */ 
/* 358 */     StringBuilder sb = new StringBuilder();
/* 359 */     sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, "UTF-8"));
/* 360 */     if (toUserIds != null) {
/* 361 */       for (int i = 0; i < toUserIds.size(); i++) {
/* 362 */         sb.append("&toUserId=").append(
/* 363 */           URLEncoder.encode((String)toUserIds.get(i), "UTF-8"));
/*     */       }
/*     */     }
/* 366 */     sb.append("&objectName=")
/* 367 */       .append(URLEncoder.encode(msg.getType(), "UTF-8"));
/* 368 */     sb.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
/*     */ 
/* 370 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 372 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult publishMessage(String appKey, String appSecret, String fromUserId, List<String> toUserIds, Message msg, String pushContent, String pushData, FormatType format)
/*     */     throws Exception
/*     */   {
/* 381 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 382 */       appSecret, 
/* 383 */       "http://api.cn.ronghub.com/message/publish." + format.toString());
/*     */ 
/* 385 */     StringBuilder sb = new StringBuilder();
/* 386 */     sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, "UTF-8"));
/* 387 */     if (toUserIds != null) {
/* 388 */       for (int i = 0; i < toUserIds.size(); i++) {
/* 389 */         sb.append("&toUserId=").append(
/* 390 */           URLEncoder.encode((String)toUserIds.get(i), "UTF-8"));
/*     */       }
/*     */     }
/* 393 */     sb.append("&objectName=")
/* 394 */       .append(URLEncoder.encode(msg.getType(), "UTF-8"));
/* 395 */     sb.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
/*     */ 
/* 397 */     if (pushContent != null) {
/* 398 */       sb.append("&pushContent=").append(
/* 399 */         URLEncoder.encode(pushContent == null ? "" : pushContent, "UTF-8"));
/*     */     }
/*     */ 
/* 402 */     if (pushData != null) {
/* 403 */       sb.append("&pushData=").append(URLEncoder.encode(pushData == null ? "" : pushData, "UTF-8"));
/*     */     }
/*     */ 
/* 406 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 408 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult publishSystemMessage(String appKey, String appSecret, String fromUserId, List<String> toUserIds, Message msg, String pushContent, String pushData, FormatType format)
/*     */     throws Exception
/*     */   {
/* 417 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 418 */       appSecret, 
/* 419 */       "http://api.cn.ronghub.com/message/system/publish." + format.toString());
/*     */ 
/* 421 */     StringBuilder sb = new StringBuilder();
/* 422 */     sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, "UTF-8"));
/* 423 */     if (toUserIds != null) {
/* 424 */       for (int i = 0; i < toUserIds.size(); i++) {
/* 425 */         sb.append("&toUserId=").append(
/* 426 */           URLEncoder.encode((String)toUserIds.get(i), "UTF-8"));
/*     */       }
/*     */     }
/* 429 */     sb.append("&objectName=")
/* 430 */       .append(URLEncoder.encode(msg.getType(), "UTF-8"));
/* 431 */     sb.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
/*     */ 
/* 433 */     if (pushContent != null) {
/* 434 */       sb.append("&pushContent=").append(
/* 435 */         URLEncoder.encode(pushContent == null ? "" : pushContent, "UTF-8"));
/*     */     }
/*     */ 
/* 438 */     if (pushData != null) {
/* 439 */       sb.append("&pushData=").append(URLEncoder.encode(pushData == null ? "" : pushData, "UTF-8"));
/*     */     }
/*     */ 
/* 442 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 444 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult publishGroupMessage(String appKey, String appSecret, String fromUserId, List<String> toGroupIds, Message msg, String pushContent, String pushData, FormatType format)
/*     */     throws Exception
/*     */   {
/* 453 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 454 */       appSecret, 
/* 455 */       "http://api.cn.ronghub.com/message/group/publish." + format.toString());
/*     */ 
/* 457 */     StringBuilder sb = new StringBuilder();
/* 458 */     sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, "UTF-8"));
/* 459 */     if (toGroupIds != null) {
/* 460 */       for (int i = 0; i < toGroupIds.size(); i++) {
/* 461 */         sb.append("&toGroupId=").append(
/* 462 */           URLEncoder.encode((String)toGroupIds.get(i), "UTF-8"));
/*     */       }
/*     */     }
/* 465 */     sb.append("&objectName=")
/* 466 */       .append(URLEncoder.encode(msg.getType(), "UTF-8"));
/* 467 */     sb.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
/*     */ 
/* 469 */     if (pushContent != null) {
/* 470 */       sb.append("&pushContent=").append(
/* 471 */         URLEncoder.encode(pushContent == null ? "" : pushContent, "UTF-8"));
/*     */     }
/*     */ 
/* 474 */     if (pushData != null) {
/* 475 */       sb.append("&pushData=").append(URLEncoder.encode(pushData == null ? "" : pushData, "UTF-8"));
/*     */     }
/*     */ 
/* 478 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 480 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult publishChatroomMessage(String appKey, String appSecret, String fromUserId, List<String> toChatroomIds, Message msg, FormatType format)
/*     */     throws Exception
/*     */   {
/* 488 */     HttpURLConnection conn = 
/* 489 */       HttpUtil.CreatePostHttpConnection(appKey, appSecret, "http://api.cn.ronghub.com/message/chatroom/publish." + 
/* 490 */       format.toString());
/*     */ 
/* 492 */     StringBuilder sb = new StringBuilder();
/* 493 */     sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, "UTF-8"));
/* 494 */     if (toChatroomIds != null) {
/* 495 */       for (int i = 0; i < toChatroomIds.size(); i++) {
/* 496 */         sb.append("&toChatroomId=").append(
/* 497 */           URLEncoder.encode((String)toChatroomIds.get(i), "UTF-8"));
/*     */       }
/*     */     }
/* 500 */     sb.append("&objectName=")
/* 501 */       .append(URLEncoder.encode(msg.getType(), "UTF-8"));
/* 502 */     sb.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
/*     */ 
/* 504 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 506 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult broadcastMessage(String appKey, String appSecret, String fromUserId, Message msg, String pushContent, String pushData, FormatType format)
/*     */     throws Exception
/*     */   {
/* 512 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 513 */       appSecret, 
/* 514 */       "http://api.cn.ronghub.com/message/broadcast." + format.toString());
/*     */ 
/* 516 */     StringBuilder sb = new StringBuilder();
/* 517 */     sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, "UTF-8"));
/* 518 */     sb.append("&objectName=")
/* 519 */       .append(URLEncoder.encode(msg.getType(), "UTF-8"));
/* 520 */     sb.append("&content=").append(URLEncoder.encode(msg.toString(), "UTF-8"));
/* 521 */     if (pushContent != null) {
/* 522 */       sb.append("&pushContent=").append(
/* 523 */         URLEncoder.encode(pushContent == null ? "" : pushContent, "UTF-8"));
/*     */     }
/*     */ 
/* 526 */     if (pushData != null) {
/* 527 */       sb.append("&pushData=").append(URLEncoder.encode(pushData == null ? "" : pushData, "UTF-8"));
/*     */     }
/*     */ 
/* 530 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 532 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult createChatroom(String appKey, String appSecret, List<ChatroomInfo> chatrooms, FormatType format)
/*     */     throws Exception
/*     */   {
/* 539 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 540 */       appSecret, 
/* 541 */       "http://api.cn.ronghub.com/chatroom/create." + format.toString());
/*     */ 
/* 543 */     StringBuilder sb = new StringBuilder();
/* 544 */     sb.append("1=1");
/* 545 */     if (chatrooms != null) {
/* 546 */       for (ChatroomInfo info : chatrooms) {
/* 547 */         if (info != null) {
/* 548 */           sb.append(
/* 549 */             String.format("&chatroom[%s]=", new Object[] { 
/* 550 */             URLEncoder.encode(info.getId(), "UTF-8") }))
/* 551 */             .append(URLEncoder.encode(info.getName(), "UTF-8"));
/*     */         }
/*     */       }
/*     */     }
/* 555 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 557 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult destroyChatroom(String appKey, String appSecret, List<String> chatroomIds, FormatType format)
/*     */     throws Exception
/*     */   {
/* 565 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 566 */       appSecret, 
/* 567 */       "http://api.cn.ronghub.com/chatroom/destroy." + format.toString());
/*     */ 
/* 569 */     StringBuilder sb = new StringBuilder();
/* 570 */     sb.append("1=1");
/* 571 */     if (chatroomIds != null) {
/* 572 */       for (String id : chatroomIds) {
/* 573 */         sb.append("&chatroomId=").append(URLEncoder.encode(id, "UTF-8"));
/*     */       }
/*     */     }
/*     */ 
/* 577 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 579 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult queryChatroom(String appKey, String appSecret, List<String> chatroomIds, FormatType format)
/*     */     throws Exception
/*     */   {
/* 586 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 587 */       appSecret, 
/* 588 */       "http://api.cn.ronghub.com/chatroom/query." + format.toString());
/*     */ 
/* 590 */     StringBuilder sb = new StringBuilder();
/* 591 */     sb.append("1=1");
/* 592 */     if (chatroomIds != null) {
/* 593 */       for (String id : chatroomIds) {
/* 594 */         sb.append("&chatroomId=").append(URLEncoder.encode(id, "UTF-8"));
/*     */       }
/*     */     }
/*     */ 
/* 598 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 600 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult getMessageHistoryUrl(String appKey, String appSecret, String date, FormatType format)
/*     */     throws Exception
/*     */   {
/* 607 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 608 */       appSecret, 
/* 609 */       "http://api.cn.ronghub.com/message/history." + format.toString());
/*     */ 
/* 611 */     StringBuilder sb = new StringBuilder();
/* 612 */     sb.append("date=").append(URLEncoder.encode(date, "UTF-8"));
/* 613 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 615 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ 
/*     */   public static SdkHttpResult deleteMessageHistory(String appKey, String appSecret, String date, FormatType format)
/*     */     throws Exception
/*     */   {
/* 622 */     HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(appKey, 
/* 623 */       appSecret, 
/* 624 */       "http://api.cn.ronghub.com/message/history/delete." + format.toString());
/*     */ 
/* 626 */     StringBuilder sb = new StringBuilder();
/* 627 */     sb.append("date=").append(URLEncoder.encode(date, "UTF-8"));
/* 628 */     HttpUtil.setBodyParameter(sb, conn);
/*     */ 
/* 630 */     return HttpUtil.returnResult(conn);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     io.rong.rong.ApiHttpClient
 * JD-Core Version:    0.6.2
 */