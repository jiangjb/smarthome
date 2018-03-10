/*     */ package com.smarthome.imcp.service.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.common.CompressImage;
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.dao.vo.system.FileVo;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.system.FileServiceIface;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Random;
/*     */ import org.apache.commons.io.FileUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("fileService")
/*     */ public class FileServiceImpl extends AbstractBasicService<Object, Serializable>
/*     */   implements FileServiceIface<Object, Serializable>
/*     */ {
/*  29 */   public static Logger logger = Logger.getLogger(FileServiceImpl.class);
/*     */ 
/*     */   public ByteArrayInputStream getFile(String filePathName) throws IOException {
///*  32 */     String root = System.getProperty("webapp.root");
			  String root="E:/smarthome/tomcat8";
/*  33 */     File file = new File(root, filePathName);
/*  34 */     if ((file == null) || (!file.exists())) {
/*  35 */       throw new BusinessException("文件不存在或已删除！");
/*     */     }
/*  37 */     return new ByteArrayInputStream(FileUtils.readFileToByteArray(file));
/*     */   }
/*     */ 
/*     */   public String saveToTemp(File fileupload, String fileName, String sessionId)
/*     */   {
/*     */     try {
/*  43 */       String root = System.getProperty("webapp.root");
//				String root="E:/smarthome/tomcat8";
/*  44 */       String tempDir = root + "temp" + "/" + 
/*  45 */         sessionId;
				logger.info("saveToTemp tempDir>>"+tempDir);
/*  46 */       File targetDir = new File(tempDir);
/*  47 */       if (!targetDir.exists()) {
/*  48 */         targetDir.mkdirs();
/*     */       }
/*     */ 
/*  51 */       File destFile = new File(targetDir, fileName);
/*     */ 
/*  53 */       FileUtils.copyFile(fileupload, destFile);
/*  54 */       return destFile.getAbsolutePath();
/*     */     } catch (IOException e) {
/*  56 */       e.printStackTrace();
/*  57 */     }throw new BusinessException("保存文件出错！");
/*     */   }
/*     */ 
/*     */   public String saveToDir(File fileupload, String fileName, String dir)
/*     */   {
/*     */     try {
/*  63 */       String root = System.getProperty("webapp.root");//动态获项目的运行路径
//	            String root="E:/smarthome";//这里设置服务器tomcat下的一个绝对路径
/*  64 */       String tempDir = root + "/" + dir;
//				String tempDir = root + dir;//root=E:\smarthome\tomcat8\webapps\smarthome.IMCPlatform\
                logger.info("saveToDir tempDir>>"+tempDir);//E:\smarthome\tomcat8\webapps\smarthome.IMCPlatform\/uploads/headpic
/*  65 */       File targetDir = new File(tempDir);
/*  66 */       if (!targetDir.exists()) {
/*  67 */         targetDir.mkdirs();
/*     */       }
/*  70 */       File destFile = new File(targetDir, fileName);
/*  71 */       FileUtils.copyFile(fileupload, destFile);
/*  72 */       return dir + "/" + fileName;
/*     */     } catch (Exception e) {
/*  74 */       e.printStackTrace();
/*  75 */     }throw new BusinessException("保存文件出错！");
/*     */   }
/*     */ 
/*     */   public FileVo copyTempFileToDir(String fileName, String destStr, String sessionId)
/*     */   {
/*     */     try
/*     */     {
/*  83 */       FileVo fileVo = new FileVo();
/*  84 */       fileVo.setFileName(fileName);
/*  85 */       String root = System.getProperty("webapp.root");
//				String root="E:/smarthome/tomcat8";
/*  86 */       String tempStr = root + "temp" + "/" + 
/*  87 */         sessionId + "/" + fileName;
				logger.error("copyTempFileToDir tempStr = " + tempStr);
/*  88 */       File tempFile = new File(tempStr);
/*  89 */       if (!tempFile.exists()) {
/*  90 */         logger.error("sessionId = " + sessionId);
/*  91 */         logger.error("临时文件路径 ：" + tempStr);
/*  92 */         throw new BusinessException("文件不存在！");
/*     */       }
/*     */ 
/*  95 */       String[] fileSizes = getFileSizes(tempFile);
/*     */ 
/*  97 */       fileVo.setFileSize(Long.valueOf(fileSizes[0]));
/*  98 */       fileVo.setFileSizeName(fileSizes[1]);
/*     */ 
/* 100 */       Random dom = new Random();
/* 101 */       String fileFileNameAll = dom.nextDouble() + "_" + GlobalMethod.getCurrentDateTime("yyyyMMddHHmmss") + fileName.substring(fileName.lastIndexOf("."));
/* 102 */       String abFilePathName = destStr + "/" + fileFileNameAll;
/* 103 */       fileVo.setPathName(abFilePathName);
/* 104 */       File destDir = new File(root + "/" + destStr);
/* 105 */       if (!destDir.exists()) {
/* 106 */         destDir.mkdirs();
/*     */       }
/* 108 */       File destFile = new File(root + abFilePathName);
/* 109 */       FileUtils.copyFile(tempFile, destFile);
/*     */ 
/* 111 */       deleteFile(tempStr);
/*     */ 
/* 116 */       return fileVo;
/*     */     } catch (Exception e) {
/* 118 */       e.printStackTrace();
/* 119 */     }throw new BusinessException("保存文件出错！");
/*     */   }
/*     */ 
/*     */   private String[] getFileSizes(File file)
/*     */   {
/* 124 */     String[] fileSizes = { "0", "" };
/*     */     try
/*     */     {
/* 127 */       FileInputStream fis = new FileInputStream(file);
/*     */ 
/* 129 */       long fileSize = fis.available();
/*     */ 
/* 131 */       String suffix = "KB";
/* 132 */       double dFizeSize = GlobalMethod.round(fileSize / 1024L, 2);
/* 133 */       if (dFizeSize > 1000.0D) {
/* 134 */         suffix = "MB";
/* 135 */         dFizeSize = GlobalMethod.round(
/* 136 */           Double.valueOf(fileSize).doubleValue() / 1024.0D / 1000.0D, 2);
/*     */       }
///* 138 */       fileSizes[0] = fileSize;
				fileSizes[0] = fileSize+"";
/* 139 */       fileSizes[1] = (dFizeSize + suffix);
/* 140 */       fis.close();
/*     */     } catch (Exception e) {
/* 142 */       e.printStackTrace();
/*     */     }
/* 144 */     return fileSizes;
/*     */   }
/*     */ 
/*     */   public FileVo compressImage(FileVo fileVo, int outputWidth, int outputHeight) {
/* 148 */     String root = System.getProperty("webapp.root");
//			  String root="E:/smarthome/tomcat8";
/*     */ 
/* 150 */     FileVo compressFileVo = new FileVo();
/* 151 */     compressFileVo.setFileName(fileVo.getFileName());
/* 152 */     compressFileVo.setFileSize(fileVo.getFileSize());
/* 153 */     compressFileVo.setFileSizeName(fileVo.getFileSizeName());
/* 154 */     compressFileVo.setPathName(fileVo.getPathName());
/*     */ 
/* 156 */     String pathName = fileVo.getPathName();
/* 157 */     int extIndex = pathName.lastIndexOf(".");
/* 158 */     StringBuffer sbOutPathName = new StringBuffer(pathName.length() + 10);
/* 159 */     if (extIndex > 0) {
/* 160 */       sbOutPathName.append(pathName.substring(0, extIndex));
/* 161 */       sbOutPathName.append("_");
/* 162 */       sbOutPathName.append(outputWidth);
/* 163 */       sbOutPathName.append("x");
/* 164 */       sbOutPathName.append(outputHeight);
/* 165 */       sbOutPathName.append(pathName.substring(extIndex));
/*     */     } else {
/* 167 */       sbOutPathName.append(pathName);
/* 168 */       sbOutPathName.append("_");
/* 169 */       sbOutPathName.append(outputWidth);
/* 170 */       sbOutPathName.append("x");
/* 171 */       sbOutPathName.append(outputHeight);
/*     */     }
/* 173 */     String outPathName = sbOutPathName.toString();
/* 174 */     File outFile = new File(root + outPathName);
/*     */ 
/* 177 */     if (CompressImage.compressImage(new File(root + fileVo.getPathName()), 
/* 177 */       outFile, outputWidth, outputHeight, false)) {
/* 178 */       String[] fileSizes = getFileSizes(outFile);
/* 179 */       compressFileVo.setFileSize(Long.valueOf(fileSizes[0]));
/* 180 */       compressFileVo.setFileSizeName(fileSizes[1]);
/* 181 */       compressFileVo.setPathName(outPathName);
/*     */     }
/*     */ 
/* 208 */     return compressFileVo;
/*     */   }
/*     */ 
/*     */   public boolean deleteTempDirectory(String sessionId)
/*     */   {
/* 218 */     String root = System.getProperty("webapp.root");
//			  String root="E:/smarthome/tomcat8";
/* 219 */     String tempPath = root + "temp" + "/" + 
/* 220 */       sessionId;
/*     */ 
/* 222 */     deleteDirectory(tempPath);
/*     */ 
/* 224 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean deleteDirectory(String sPath)
/*     */   {
/* 236 */     if (!sPath.endsWith("/")) {
/* 237 */       sPath = sPath + "/";
/*     */     }
/* 239 */     File dirFile = new File(sPath);
/*     */ 
/* 241 */     if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
/* 242 */       return false;
/*     */     }
/* 244 */     boolean flag = true;
/*     */ 
/* 246 */     File[] files = dirFile.listFiles();
/* 247 */     for (int i = 0; i < files.length; i++)
/*     */     {
/* 249 */       if (files[i].isFile()) {
/* 250 */         flag = deleteFile(files[i].getAbsolutePath());
/* 251 */         if (!flag)
/* 252 */           break;
/*     */       }
/*     */       else {
/* 255 */         flag = deleteDirectory(files[i].getAbsolutePath());
/* 256 */         if (!flag)
/*     */           break;
/*     */       }
/*     */     }
/* 260 */     if (!flag) {
/* 261 */       return false;
/*     */     }
/* 263 */     if (dirFile.delete()) {
/* 264 */       return true;
/*     */     }
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean deleteFile(String sPath)
/*     */   {
/* 278 */     boolean flag = false;
/* 279 */     File file = new File(sPath);
/*     */ 
/* 281 */     if ((file.isFile()) && (file.exists())) {
/* 282 */       file.delete();
/* 283 */       flag = true;
/*     */     }
/* 285 */     return flag;
/*     */   }
/*     */ 
/*     */   public void copyToDir(File file, String fileName, String destStr)
/*     */   {
/*     */     try {
/* 291 */       String root = System.getProperty("webapp.root");
//			    String root="E:/smarthome/tomcat8";
/* 292 */       String destDir = root + (
/* 293 */         !root.endsWith("/") ? "/" : 
/* 294 */         "") + destStr;
/* 295 */       File targetDir = new File(destDir);
/* 296 */       if (!targetDir.exists()) {
/* 297 */         targetDir.mkdirs();
/*     */       }
/*     */ 
/* 300 */       File destFile = new File(targetDir, fileName);
/*     */ 
/* 302 */       FileUtils.copyFile(file, destFile);
/*     */     } catch (IOException e) {
/* 304 */       e.printStackTrace();
/* 305 */       throw new BusinessException("保存文件出错！");
/*     */     }
/*     */   }
/*     */ 
/*     */   public String readFile(File file, String encoding)
/*     */   {
/*     */     try {
/* 312 */       return FileUtils.readFileToString(file, encoding);
/*     */     } catch (FileNotFoundException e) {
/* 314 */       e.printStackTrace();
/* 315 */       throw new BusinessException(file + " 读取文件出错！");
/*     */     } catch (IOException e) {
/* 317 */       e.printStackTrace();
/* 318 */     }throw new BusinessException(file + " 读取文件出错！");
/*     */   }
/*     */ 
/*     */   public void writeFile(String content, String destStr, String encode)
/*     */   {
/* 324 */     if (StringUtils.isEmpty(encode))
/* 325 */       encode = "UTF-8";
/*     */     try
/*     */     {
/* 328 */       File file = new File(destStr);
/* 329 */       if (!file.exists()) {
/* 330 */         file.createNewFile();
/*     */       }
/*     */ 
/* 333 */       FileUtils.writeStringToFile(file, content, encode);
/*     */     } catch (Exception e) {
/* 335 */       System.out.println("写入文件出错！");
/* 336 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static Logger getLogger() {
/* 341 */     return logger;
/*     */   }
/*     */ 
/*     */   public static void setLogger(Logger logger) {
/* 345 */     logger = logger;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.FileServiceImpl
 * JD-Core Version:    0.6.2
 */