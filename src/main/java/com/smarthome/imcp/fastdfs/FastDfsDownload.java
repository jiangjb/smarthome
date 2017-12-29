/*    */ package com.smarthome.imcp.fastdfs;
/*    */ 
/*    */ import com.smarthome.imcp.exception.BusinessException;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import org.csource.fastdfs.StorageClient;
/*    */ import org.csource.fastdfs.StorageServer;
/*    */ import org.csource.fastdfs.TrackerServer;
/*    */ 
/*    */ public class FastDfsDownload
/*    */ {
/* 14 */   private static int waitTimes = 180;
/* 15 */   private static TrackerServer trackerServer = null;
/*    */ 
/*    */   private static StorageClient getStorageClient() {
/* 18 */     StorageClient storageClient = null;
/*    */     try {
/* 20 */       trackerServer = FastDfsConnectionPool.checkout(waitTimes);
/* 21 */       if (trackerServer != null) {
/* 22 */         StorageServer storageServer = null;
/* 23 */         return new StorageClient(trackerServer, 
/* 24 */           storageServer);
/*    */       }
/*    */ 
/* 27 */       throw new BusinessException("无法找到有效的文件服务器连接!");
/*    */     } catch (InterruptedException e) {
/*    */     }
/* 30 */     throw new BusinessException("文件服务器异常!");
/*    */   }
/*    */ 
/*    */   private static void createFile(String path, byte[] content)
/*    */     throws IOException
/*    */   {
/* 37 */     FileOutputStream fos = new FileOutputStream(path);
/* 38 */     fos.write(content);
/* 39 */     fos.close();
/*    */   }
/*    */ 
/*    */   private static void idleTrackerServer() {
/* 43 */     FastDfsConnectionPool.checkin(trackerServer);
/*    */   }
/*    */ 
/*    */   public static void download(String groupName, String remoteFilename, String descFile) throws Exception
/*    */   {
/* 48 */     StorageClient storageClient = getStorageClient();
/*    */ 
/* 51 */     byte[] content = storageClient
/* 52 */       .download_file(groupName, remoteFilename);
/*    */ 
/* 54 */     createFile(descFile, content);
/*    */ 
/* 58 */     idleTrackerServer();
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.fastdfs.FastDfsDownload
 * JD-Core Version:    0.6.2
 */