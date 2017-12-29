/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.velocity.Template;
/*     */ import org.apache.velocity.VelocityContext;
/*     */ import org.apache.velocity.app.VelocityEngine;
/*     */ import org.apache.velocity.runtime.RuntimeServices;
/*     */ import org.apache.velocity.runtime.log.LogSystem;
/*     */ 
/*     */ public class VelocityRunner
/*     */   implements LogSystem
/*     */ {
/*     */   public static final String TEMPLATE_DIRECTORY = "templates/";
/*     */   public static final String TEMPLATE_EXTENSION = ".vm";
/*     */   private final VelocityEngine _engine;
/*     */   private final VelocityContext _ctx;
/*     */   private final boolean _overrideExisting;
/*     */   private int _loggingLevel;
/*     */   private String _templateName;
/*     */   private String _result;
/*     */   private File _resultFile;
/*     */ 
/*     */   public VelocityRunner()
/*     */     throws Exception
/*     */   {
/*  32 */     this(null, null);
/*     */   }
/*     */ 
/*     */   public VelocityRunner(String tmplName)
/*     */     throws Exception
/*     */   {
/*  38 */     this(tmplName, null);
/*     */   }
/*     */ 
/*     */   public VelocityRunner(String tmplName, File file)
/*     */     throws Exception
/*     */   {
/*  44 */     this(tmplName, file, false);
/*     */   }
/*     */ 
/*     */   public VelocityRunner(String tmplName, File file, boolean overrideExisting)
/*     */     throws Exception
/*     */   {
/*  50 */     this(tmplName, file, overrideExisting, 3);
/*     */   }
/*     */ 
/*     */   public VelocityRunner(String tmplName, File file, boolean overrideExisting, int loggingLevel)
/*     */     throws Exception
/*     */   {
/*  56 */     this._engine = new VelocityEngine();
/*  57 */     this._ctx = new VelocityContext();
/*  58 */     this._loggingLevel = 2;
/*  59 */     this._loggingLevel = loggingLevel;
/*  60 */     configureEngineBeforeInit();
/*  61 */     this._engine.init();
/*  62 */     this._templateName = tmplName;
/*  63 */     this._resultFile = file;
/*  64 */     this._overrideExisting = overrideExisting;
/*     */   }
/*     */ 
/*     */   private void configureEngineBeforeInit()
/*     */   {
/*  69 */     this._engine.setProperty("runtime.log.logsystem", this);
/*  70 */     this._engine.setProperty("runtime.log.error.stacktrace", Boolean.TRUE);
/*     */ 
/*  72 */     this._engine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
/*  73 */     this._engine.setProperty("velocimacro.library", "templates/_doc_content.vm");
/*  74 */     this._engine.setProperty("input.encoding", "UTF-8");
/*  75 */     this._engine.setProperty("output.encoding", "UTF-8");
/*     */   }
/*     */ 
/*     */   public void add(String key, Object object)
/*     */   {
/*  80 */     this._ctx.put(key, object);
/*     */   }
/*     */ 
/*     */   public Object remove(String key)
/*     */   {
/*  85 */     return this._ctx.remove(key);
/*     */   }
/*     */ 
/*     */   public void reset()
/*     */   {
/*  90 */     Object[] keys = this._ctx.getKeys();
/*  91 */     for (int i = 0; i < keys.length; i++)
/*  92 */       remove((String)keys[i]);
/*     */   }
/*     */ 
/*     */   public void run()
/*     */     throws InvocationTargetException, InterruptedException
/*     */   {
/*  99 */     if (this._templateName == null)
/* 100 */       throw new IllegalArgumentException("Template name not specified");
/* 101 */     String tmpl = "templates/" + this._templateName + ".vm";
/* 102 */     StringWriter writer = new StringWriter();
/*     */     try
/*     */     {
/* 105 */       Template template = this._engine.getTemplate(tmpl);
/* 106 */       template.merge(this._ctx, writer);
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 110 */       throw new InvocationTargetException(e);
/*     */     }
/* 112 */     this._result = writer.getBuffer().toString();
/* 113 */     if (this._resultFile != null)
/*     */       try
/*     */       {
/* 116 */         if ((this._resultFile.exists()) && (this._overrideExisting))
/* 117 */           this._resultFile.delete();
/* 118 */         FileOutputStream fstream = new FileOutputStream(this._resultFile);
/* 119 */         fstream.write(this._result.getBytes("utf-8"));
/*     */       } catch (Exception e) {
/* 121 */         e.printStackTrace();
/*     */       }
/*     */   }
/*     */ 
/*     */   public String getTemplateName()
/*     */   {
/* 128 */     return this._templateName;
/*     */   }
/*     */ 
/*     */   public void setTemplateName(String templateName)
/*     */   {
/* 133 */     this._templateName = templateName;
/*     */   }
/*     */ 
/*     */   public File getResultFile()
/*     */   {
/* 138 */     return this._resultFile;
/*     */   }
/*     */ 
/*     */   public void setResultFile(File file)
/*     */   {
/* 143 */     this._resultFile = file;
/*     */   }
/*     */ 
/*     */   public String getResult()
/*     */   {
/* 148 */     return this._result;
/*     */   }
/*     */ 
/*     */   public void init(RuntimeServices runtimeservices)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   public void logVelocityMessage(int loggingLevel, String message)
/*     */   {
/* 158 */     if (loggingLevel >= this._loggingLevel)
/*     */     {
/* 160 */       String fullMsg = message;
/* 161 */       if (loggingLevel == 0) {
/* 162 */         fullMsg = "[DEBUG] " + message;
/*     */       }
/* 164 */       else if (loggingLevel == 1) {
/* 165 */         fullMsg = "[INFO]  " + message;
/*     */       }
/* 167 */       else if (loggingLevel == 2) {
/* 168 */         fullMsg = "[WARN]  " + message;
/*     */       }
/* 170 */       else if (loggingLevel == 3)
/* 171 */         fullMsg = "[ERROR] " + message;
/*     */       else
/* 173 */         fullMsg = "[OTHER] " + message;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static final void main(String[] args)
/*     */   {
/* 179 */     File file = new File("d:\\f1.html");
/*     */     try {
/* 181 */       VelocityRunner vr = new VelocityRunner("_doc_content", file, true);
/* 182 */       vr.add("documentContent", "<div>aaaaaaaaaaaaaaaaaaaaaaaaa</div>");
/* 183 */       vr.run();
/*     */     } catch (Exception e) {
/* 185 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.VelocityRunner
 * JD-Core Version:    0.6.2
 */