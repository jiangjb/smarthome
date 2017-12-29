/*     */ package com.smarthome.imcp.util;
/*     */ 
/*     */ import com.smarthome.imcp.service.impl.push.PushService;
/*     */ import java.io.PrintStream;
/*     */ import org.springframework.context.annotation.Lazy;
/*     */ 
/*     */ @Lazy(false)
/*     */ public class LockPushUtil
/*     */ {
/*     */   public static void lockPush(String versionType, String Cid, String phoneType, String unlockMethods, String unlockStatus, String formatDate, String lockName, String membersName)
/*     */   {
/*  21 */     PushService pushService = new PushService();
/*  22 */     if (versionType.equals("1")) {
/*  23 */       System.err.println("易联智家KEY");
/*  24 */       pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/*  25 */       pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/*  26 */       pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/*  27 */     } else if (versionType.equals("2")) {
/*  28 */       System.err.println("爱博瑞KEY");
/*  29 */       pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/*  30 */       pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/*  31 */       pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/*  32 */     } else if (!versionType.equals("3"))
/*     */     {
/*  34 */       if (versionType.equals("4")) {
/*  35 */         System.err.println("思创智能KEY");
/*  36 */         pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/*  37 */         pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/*  38 */         pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/*  39 */       } else if (versionType.equals("5")) {
/*  40 */         System.err.println("峰庭智能KEY");
/*  41 */         pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/*  42 */         pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/*  43 */         pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/*  44 */       } else if (versionType.equals("6")) {
/*  45 */         System.err.println("麦宝KEY");
/*  46 */         pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/*  47 */         pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/*  48 */         pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/*  49 */       } else if (versionType.equals("7")) {
/*  50 */         System.err.println("乐沃KEY");
/*  51 */         pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/*  52 */         pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/*  53 */         pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*     */       }
/*     */     }
/*  55 */     String title = "";
/*  56 */     String CID = Cid;
/*     */ 
/*  58 */     if ((CID == null) || (CID.equals(""))) {
/*  59 */       System.err.println("CID为空推送不到信息");
/*     */     } else {
/*  61 */       StringBuffer text = new StringBuffer();
/*     */ 
/*  63 */       System.err.println("*****<< " + versionType);
/*     */ 
/*  65 */       if (versionType.equals("1")) {
/*  66 */         System.err.println("易联智家推送内容");
/*  67 */         title = "掌上智家";
/*  68 */         text.append(formatDate + "\n");
/*  69 */         text.append(lockName + "正在终端开锁" + "\n");
/*  70 */         if (unlockMethods.equals("指纹"))
/*  71 */           text.append("开锁方法:" + membersName + unlockMethods + "\n");
/*     */         else {
/*  73 */           text.append("开锁方法:" + unlockMethods + "\n");
/*     */         }
/*  75 */         text.append("开锁状态:" + unlockStatus + "\n");
/*  76 */         text.append("请务必留意。\n");
/*  77 */       } else if (versionType.equals("2")) {
/*  78 */         System.err.println("爱博瑞推送内容");
/*  79 */         title = "爱波瑞科技";
/*  80 */         text.append(formatDate + "\n");
/*  81 */         text.append(lockName + "正在终端开锁" + "\n");
/*  82 */         if (unlockMethods.equals("指纹"))
/*  83 */           text.append("开锁方法:" + membersName + unlockMethods + "\n");
/*     */         else {
/*  85 */           text.append("开锁方法:" + unlockMethods + "\n");
/*     */         }
/*  87 */         text.append("开锁状态:" + unlockStatus + "\n");
/*  88 */         text.append("请务必留意。\n");
/*  89 */       } else if (!versionType.equals("3"))
/*     */       {
/*  91 */         if (versionType.equals("4")) {
/*  92 */           System.err.println("思创智能推送内容");
/*  93 */           title = "思创智能";
/*  94 */           text.append(formatDate + "\n");
/*  95 */           text.append(lockName + "正在终端开锁" + "\n");
/*  96 */           if (unlockMethods.equals("指纹"))
/*  97 */             text.append("开锁方法:" + membersName + unlockMethods + "\n");
/*     */           else {
/*  99 */             text.append("开锁方法:" + unlockMethods + "\n");
/*     */           }
/* 101 */           text.append("开锁状态:" + unlockStatus + "\n");
/* 102 */           text.append("请务必留意。\n");
/* 103 */         } else if (versionType.equals("5")) {
/* 104 */           System.err.println("峰庭智能推送内容");
/* 105 */           title = "峰庭智能";
/* 106 */           text.append(formatDate + "\n");
/* 107 */           text.append(lockName + "正在终端开锁" + "\n");
/* 108 */           if (unlockMethods.equals("指纹"))
/* 109 */             text.append("开锁方法:" + membersName + unlockMethods + "\n");
/*     */           else {
/* 111 */             text.append("开锁方法:" + unlockMethods + "\n");
/*     */           }
/* 113 */           text.append("开锁状态:" + unlockStatus + "\n");
/* 114 */           text.append("请务必留意。\n");
/* 115 */         } else if (versionType.equals("6")) {
/* 116 */           System.err.println("麦宝推送内容");
/* 117 */           title = "麦宝";
/* 118 */           text.append(formatDate + "\n");
/* 119 */           text.append(lockName + "正在终端开锁" + "\n");
/* 120 */           if (unlockMethods.equals("指纹"))
/* 121 */             text.append("开锁方法:" + membersName + unlockMethods + "\n");
/*     */           else {
/* 123 */             text.append("开锁方法:" + unlockMethods + "\n");
/*     */           }
/* 125 */           text.append("开锁状态:" + unlockStatus + "\n");
/* 126 */           text.append("请务必留意。\n");
/* 127 */         } else if (versionType.equals("7")) {
/* 128 */           System.err.println("乐沃推送内容");
/* 129 */           title = "乐沃";
/* 130 */           text.append(formatDate + "\n");
/* 131 */           text.append(lockName + "正在终端开锁" + "\n");
/* 132 */           if (unlockMethods.equals("指纹"))
/* 133 */             text.append("开锁方法:" + membersName + unlockMethods + "\n");
/*     */           else {
/* 135 */             text.append("开锁方法:" + unlockMethods + "\n");
/*     */           }
/* 137 */           text.append("开锁状态:" + unlockStatus + "\n");
/* 138 */           text.append("请务必留意。\n");
/*     */         }
/*     */       }
/* 141 */       Integer type = Integer.valueOf(phoneType);
/*     */ 
/* 143 */       if ((type == null) || (type.intValue() == 0)) {
/* 144 */         pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*     */       }
/*     */       else
/* 147 */         pushService.apnPush(CID, title, text.toString(), text.toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void lockPowr(String versionType, String Cid, String phoneType, String Status, String formatDate)
/*     */   {
/* 157 */     PushService pushService = new PushService();
/*     */ 
/* 159 */     if (versionType.equals("1")) {
/* 160 */       System.err.println("易联智家KEY");
/* 161 */       pushService.setAppId("C2rT1RdCoB7BaZ83l2AJM7");
/* 162 */       pushService.setAppkey("ApgJ2YFdI573k57hLt9Mz9");
/* 163 */       pushService.setMaster("JBWO7E3WyW75zgd2Bdr4NA");
/* 164 */     } else if (versionType.equals("2")) {
/* 165 */       System.err.println("爱博瑞KEY");
/* 166 */       pushService.setAppId("qy0HMfNc8o6fiLdtUGRfo1");
/* 167 */       pushService.setAppkey("cu1LHpYRwnAwDtTjq8XaQ7");
/* 168 */       pushService.setMaster("UpAQnXf7S47Snh00l5P5E8");
/* 169 */     } else if (!versionType.equals("3"))
/*     */     {
/* 171 */       if (versionType.equals("4")) {
/* 172 */         System.err.println("思创智能KEY");
/* 173 */         pushService.setAppId("m5H4RPxliZAVXBlG1jka32");
/* 174 */         pushService.setAppkey("xizBIaTLNY5g7HxCi6kP05");
/* 175 */         pushService.setMaster("TRuul8ZWoO8rfFexEbvN09");
/* 176 */       } else if (versionType.equals("5")) {
/* 177 */         System.err.println("峰庭智能KEY");
/* 178 */         pushService.setAppId("8qv7s4OhGEAjDjEaC5bBw4");
/* 179 */         pushService.setAppkey("DH3NlHfEOG6YtySTspB4LA");
/* 180 */         pushService.setMaster("utgpb3GAGN9KsVKVun7W32");
/* 181 */       } else if (versionType.equals("6")) {
/* 182 */         System.err.println("麦宝KEY");
/* 183 */         pushService.setAppId("OAzON9h86e8Y2XREgjU0R9");
/* 184 */         pushService.setAppkey("SAE1hTMJcW8ZkRfZPRDja6");
/* 185 */         pushService.setMaster("y5zkumXwYPACBLJ59BZnr6");
/* 186 */       } else if (versionType.equals("7")) {
/* 187 */         System.err.println("乐沃KEY");
/* 188 */         pushService.setAppId("pHU6NuXh789r2ZXEYzj7z1");
/* 189 */         pushService.setAppkey("COGyB0sKyQ8nGHmnuNUK41");
/* 190 */         pushService.setMaster("qSTfJpPIQc7OkaswRI1YH7");
/*     */       }
/*     */     }
/* 192 */     String title = "";
/* 193 */     String CID = Cid;
/*     */ 
/* 195 */     if ((CID == null) || (CID.equals(""))) {
/* 196 */       System.err.println("CID为空推送不到信息");
/*     */     } else {
/* 198 */       StringBuffer text = new StringBuffer();
/*     */ 
/* 200 */       System.err.println("*****<< " + versionType);
/* 201 */       if (versionType.equals("1")) {
/* 202 */         System.err.println("易联智家推送内容");
/* 203 */         title = "掌上智家";
/* 204 */         text.append("尊敬的客户");
/* 205 */         text.append("您的锁电量");
/* 206 */         text.append(" 状态 " + Status);
/* 207 */         text.append(" 请务必留意。");
/* 208 */       } else if (versionType.equals("2")) {
/* 209 */         System.err.println("爱博瑞推送内容");
/* 210 */         title = "爱波瑞科技";
/* 211 */         text.append("尊敬的客户");
/* 212 */         text.append("您的锁电量");
/* 213 */         text.append(" 状态 " + Status);
/*     */ 
/* 215 */         text.append(" 请务必留意。");
/* 216 */       } else if (!versionType.equals("3"))
/*     */       {
/* 218 */         if (versionType.equals("4")) {
/* 219 */           System.err.println("思创智能推送内容");
/* 220 */           title = "思创智能";
/* 221 */           text.append("尊敬的客户");
/* 222 */           text.append("您的锁电量");
/* 223 */           text.append(" 状态 " + Status);
/*     */ 
/* 225 */           text.append(" 请务必留意。");
/* 226 */         } else if (versionType.equals("5")) {
/* 227 */           System.err.println("峰庭智能推送内容");
/* 228 */           title = "峰庭智能";
/* 229 */           text.append("尊敬的客户");
/* 230 */           text.append("您的锁电量");
/* 231 */           text.append(" 状态 " + Status);
/*     */ 
/* 233 */           text.append(" 请务必留意。");
/* 234 */         } else if (versionType.equals("6")) {
/* 235 */           System.err.println("麦宝推送内容");
/* 236 */           title = "麦宝";
/* 237 */           text.append("尊敬的客户");
/* 238 */           text.append("您的锁电量");
/* 239 */           text.append(" 状态 " + Status);
/*     */ 
/* 241 */           text.append(" 请务必留意。");
/* 242 */         } else if (versionType.equals("7")) {
/* 243 */           System.err.println("乐沃推送内容");
/* 244 */           title = "乐沃";
/* 245 */           text.append("尊敬的客户");
/* 246 */           text.append("您的锁电量");
/* 247 */           text.append(" 状态 " + Status);
/*     */ 
/* 249 */           text.append(" 请务必留意。");
/*     */         }
/*     */       }
/* 252 */       Integer type = Integer.valueOf(phoneType);
/*     */ 
/* 254 */       if ((type == null) || (type.intValue() == 0)) {
/* 255 */         pushService.pushToSingle(CID, title, text.toString(), text.toString());
/*     */       }
/*     */       else
/* 258 */         pushService.apnPush(CID, title, text.toString(), text.toString());
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.LockPushUtil
 * JD-Core Version:    0.6.2
 */