/*    */ package com.smarthome.imcp.listener;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.net.URL;
/*    */ import java.util.Properties;
/*    */ import javax.servlet.ServletContext;
/*    */ import javax.servlet.ServletContextEvent;
/*    */ import javax.servlet.ServletContextListener;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.logicalcobwebs.proxool.ProxoolException;
/*    */ import org.logicalcobwebs.proxool.ProxoolFacade;
/*    */ import org.logicalcobwebs.proxool.configuration.JAXPConfigurator;
/*    */ import org.logicalcobwebs.proxool.configuration.PropertyConfigurator;
/*    */ 
/*    */ public class ProxoolConfiguratorListener
/*    */   implements ServletContextListener
/*    */ {
/* 20 */   private static final Log LOG = LogFactory.getLog(ProxoolConfiguratorListener.class);
/*    */   private static final String XML_FILE_PROPERTY = "proxoolConfigLocation";
/* 24 */   private boolean autoShutdown = true;
/*    */ 
/*    */   public void contextInitialized(ServletContextEvent servletConfig)
/*    */   {
/* 29 */     URL xmlpath = getClass().getClassLoader().getResource("");
/* 30 */     String appDir = xmlpath.getPath();
/* 31 */     Properties properties = new Properties();
/* 32 */     String value = servletConfig.getServletContext().getInitParameter(
/* 33 */       "proxoolConfigLocation");
/* 34 */     LOG.debug("proxoolConfigLocation:" + value);
/*    */     try
/*    */     {
/* 37 */       File file = new File(value);
/* 38 */       if (file.isAbsolute()) {
/* 39 */         JAXPConfigurator.configure(value, false);
/*    */       } else {
/* 41 */         LOG.debug(appDir + File.separator + value);
/* 42 */         JAXPConfigurator.configure(appDir + File.separator + value, 
/* 43 */           false);
/*    */       }
/*    */     } catch (ProxoolException e) {
/* 46 */       LOG.error("Problem configuring " + value, e);
/*    */     }
/* 48 */     if (properties.size() > 0)
/*    */       try {
/* 50 */         PropertyConfigurator.configure(properties);
/*    */       } catch (ProxoolException e) {
/* 52 */         LOG.error("Problem configuring using init properties", e);
/*    */       }
/*    */   }
/*    */ 
/*    */   public void contextDestroyed(ServletContextEvent s)
/*    */   {
/* 58 */     if (this.autoShutdown)
/* 59 */       ProxoolFacade.shutdown(0);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.listener.ProxoolConfiguratorListener
 * JD-Core Version:    0.6.2
 */