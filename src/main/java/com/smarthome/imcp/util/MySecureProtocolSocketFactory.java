/*    */ package com.smarthome.imcp.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.net.InetAddress;
/*    */ import java.net.Socket;
/*    */ import java.net.UnknownHostException;
/*    */ import javax.net.ssl.SSLContext;
/*    */ import javax.net.ssl.SSLSocketFactory;
/*    */ import javax.net.ssl.TrustManager;
/*    */ import org.apache.commons.httpclient.ConnectTimeoutException;
/*    */ import org.apache.commons.httpclient.HttpClientError;
/*    */ import org.apache.commons.httpclient.params.HttpConnectionParams;
/*    */ import org.apache.commons.httpclient.protocol.ControllerThreadSocketFactory;
/*    */ import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
/*    */ 
/*    */ public class MySecureProtocolSocketFactory
/*    */   implements SecureProtocolSocketFactory
/*    */ {
/* 20 */   private SSLContext sslContext = null;
/*    */ 
/*    */   private static SSLContext createEasySSLContext()
/*    */   {
/*    */     try
/*    */     {
/* 33 */       SSLContext context = SSLContext.getInstance("SSL");
/* 34 */       context.init(null, new TrustManager[] { new MyX509TrustManager() }, null);
/* 35 */       return context;
/*    */     } catch (Exception e) {
/* 37 */       throw new HttpClientError(e.toString());
/*    */     }
/*    */   }
/*    */ 
/*    */   private SSLContext getSSLContext()
/*    */   {
/* 45 */     if (this.sslContext == null) {
/* 46 */       this.sslContext = createEasySSLContext();
/*    */     }
/* 48 */     return this.sslContext;
/*    */   }
/*    */ 
/*    */   public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
/*    */     throws IOException, UnknownHostException
/*    */   {
/* 60 */     return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
/*    */   }
/*    */ 
/*    */   public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params)
/*    */     throws IOException, UnknownHostException, ConnectTimeoutException
/*    */   {
/* 71 */     if (params == null) {
/* 72 */       throw new IllegalArgumentException("Parameters may not be null");
/*    */     }
/* 74 */     int timeout = params.getConnectionTimeout();
/* 75 */     if (timeout == 0) {
/* 76 */       return createSocket(host, port, localAddress, localPort);
/*    */     }
/* 78 */     return ControllerThreadSocketFactory.createSocket(this, host, port, localAddress, localPort, timeout);
/*    */   }
/*    */ 
/*    */   public Socket createSocket(String host, int port)
/*    */     throws IOException, UnknownHostException
/*    */   {
/* 87 */     return getSSLContext().getSocketFactory().createSocket(host, port);
/*    */   }
/*    */ 
/*    */   public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
/*    */     throws IOException, UnknownHostException
/*    */   {
/* 96 */     return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.util.MySecureProtocolSocketFactory
 * JD-Core Version:    0.6.2
 */