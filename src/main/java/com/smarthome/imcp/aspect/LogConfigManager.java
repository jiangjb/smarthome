/*    */ package com.smarthome.imcp.aspect;
/*    */ 
/*    */ import com.smarthome.imcp.common.XmlDom4j;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.dom4j.Element;
/*    */ 
/*    */ public class LogConfigManager
/*    */ {
/*    */   public static final String strcLogConfigFile = "logConfig.xml";
/* 18 */   private static Map<String, LogConfig> mpLogConfig = new HashMap();
/*    */ 
/*    */   public static void init() {
/* 21 */     String strLogConfigFile = LogConfigManager.class.getClassLoader()
/* 22 */       .getResource("logConfig.xml").getFile();
/* 23 */     parseLogConfig(strLogConfigFile);
/*    */   }
/*    */ 
/*    */   private static void parseLogConfig(String straLogConfigFile)
/*    */   {
/* 28 */     XmlDom4j dom = new XmlDom4j();
/* 29 */     dom.readXML(straLogConfigFile);
/* 30 */     Element rootNode = dom.getRootNode();
/* 31 */     for (Iterator iter = rootNode.elementIterator(); iter.hasNext(); ) {
/* 32 */       Element logNode = (Element)iter.next();
/* 33 */       LogConfig logConfig = new LogConfig();
/* 34 */       String strClass = logNode.attributeValue("class");
/* 35 */       String strContent = logNode.attributeValue("content");
/* 36 */       String visiable = logNode.attributeValue("visiable");
/* 37 */       String model = logNode.attributeValue("model");
/* 38 */       if ("Y".equals(visiable)) {
/* 39 */         List listKey = new ArrayList();
/* 40 */         for (Iterator key = logNode.elementIterator(); key.hasNext(); ) {
/* 41 */           Element keyNode = (Element)key.next();
/* 42 */           listKey.add(keyNode.getText().trim());
/*    */         }
/* 44 */         logConfig.setClassName(strClass);
/* 45 */         logConfig.setContent(strContent);
/* 46 */         logConfig.setVisiable(visiable);
/* 47 */         logConfig.setModel(model);
/* 48 */         logConfig.setListKey(listKey);
/* 49 */         mpLogConfig.put(strClass, logConfig);
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public static LogConfig getLogConfig(String straClass) {
/* 55 */     return (LogConfig)mpLogConfig.get(straClass);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.aspect.LogConfigManager
 * JD-Core Version:    0.6.2
 */