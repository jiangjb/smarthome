/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import com.smarthome.imcp.exception.BusinessException;
/*    */ import freemarker.template.Configuration;
/*    */ import freemarker.template.DefaultObjectWrapper;
/*    */ import freemarker.template.Template;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStreamWriter;
/*    */ import java.io.Writer;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class FreemarkerUtil
/*    */ {
/* 19 */   private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
/*    */   public static Configuration config;
/*    */ 
/*    */   static
/*    */   {
/*    */     try
/*    */     {
/* 25 */       config = new Configuration();
/* 26 */       File file = new File(System.getProperty("webapp.root"));
/*    */ 
/* 28 */       config.setDirectoryForTemplateLoading(file);
/* 29 */       config.setObjectWrapper(new DefaultObjectWrapper());
/*    */     } catch (IOException e) {
/* 31 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static void write(Object rootMap, String templateName, String file) {
/*    */     try {
/* 37 */       Writer out = null;
/*    */ 
/* 39 */       Template template = config.getTemplate(templateName, "GBK");
/*    */ 
/* 41 */       File f = new File(file);
/* 42 */       File p = f.getParentFile();
/* 43 */       if (!p.exists()) {
/* 44 */         p.mkdirs();
/*    */       }
/*    */ 
/* 47 */       out = new OutputStreamWriter(new FileOutputStream(file), "GBK");
/* 48 */       template.process(rootMap, out);
/*    */ 
/* 50 */       if (out != null) {
/* 51 */         out.flush();
/* 52 */         out.close();
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 56 */       logger.error(file + templateName + " 模板生成出错！");
/* 57 */       throw new BusinessException(templateName + " 模板生成出错！", e);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.FreemarkerUtil
 * JD-Core Version:    0.6.2
 */