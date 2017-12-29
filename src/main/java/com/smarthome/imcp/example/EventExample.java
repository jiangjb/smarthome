/*     */ package com.smarthome.imcp.example;
/*     */ 
/*     */ import com.pingplusplus.exception.APIConnectionException;
/*     */ import com.pingplusplus.exception.APIException;
/*     */ import com.pingplusplus.exception.AuthenticationException;
/*     */ import com.pingplusplus.exception.ChannelException;
/*     */ import com.pingplusplus.exception.InvalidRequestException;
/*     */ import com.pingplusplus.model.Charge;
/*     */ import com.pingplusplus.model.Event;
/*     */ import com.pingplusplus.model.EventCollection;
/*     */ import com.pingplusplus.model.Refund;
/*     */ import com.pingplusplus.model.Summary;
/*     */ import com.pingplusplus.model.Webhooks;
/*     */ import java.io.PrintStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class EventExample
/*     */ {
/*  38 */   public static String apiKey = "sk_live_0CG88GOWHKG4TqTifDC0ivLK";
/*     */ 
/*  42 */   public static String appId = "app_8aDS0OXXLqbDyLCS";
/*     */ 
/*  47 */   public static String eventid = "YOUR-EVNETID";
/*     */ 
/*     */   public static void main(String[] args) {
/*  50 */     com.pingplusplus.Pingpp.apiKey = apiKey;
/*  51 */     EventExample eventExample = new EventExample();
/*     */ 
/*  54 */     System.out.println("---------查询event列表");
/*  55 */     eventExample.all();
/*     */   }
/*     */ 
/*     */   public void retrieve(String id)
/*     */   {
/*     */     try
/*     */     {
/*  68 */       Event event = Event.retrieve(id);
/*  69 */       System.out.println(event);
/*     */ 
/*  71 */       Object obj = Webhooks.parseEvnet(event.toString());
/*  72 */       if ((obj instanceof Charge))
/*  73 */         System.out.println("webhooks 发送了 Charge");
/*  74 */       else if ((obj instanceof Refund))
/*  75 */         System.out.println("webhooks 发送了 Refund");
/*  76 */       else if ((obj instanceof Summary))
/*  77 */         System.out.println("webhooks 发送了 Summary");
/*     */     }
/*     */     catch (AuthenticationException e) {
/*  80 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/*  82 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/*  84 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/*  86 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/*  89 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void all()
/*     */   {
/* 102 */     Map params = new HashMap();
/*     */     try
/*     */     {
/* 105 */       EventCollection eventCollection = Event.all(params);
/* 106 */       System.out.println(eventCollection);
/*     */     } catch (AuthenticationException e) {
/* 108 */       e.printStackTrace();
/*     */     } catch (InvalidRequestException e) {
/* 110 */       e.printStackTrace();
/*     */     } catch (APIConnectionException e) {
/* 112 */       e.printStackTrace();
/*     */     } catch (APIException e) {
/* 114 */       e.printStackTrace();
/*     */     }
/*     */     catch (ChannelException e) {
/* 117 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.example.EventExample
 * JD-Core Version:    0.6.2
 */