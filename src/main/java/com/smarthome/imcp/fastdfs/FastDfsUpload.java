/*    */ package com.smarthome.imcp.fastdfs;
/*    */ 
/*    */ import com.smarthome.imcp.exception.BusinessException;
/*    */ import org.csource.fastdfs.StorageClient1;
/*    */ import org.csource.fastdfs.StorageServer;
/*    */ import org.csource.fastdfs.TrackerServer;
/*    */ 
/*    */ public class FastDfsUpload
/*    */ {
/* 11 */   private static int waitTimes = 30;
/* 12 */   private static TrackerServer trackerServer = null;
/*    */ 
/*    */   private static StorageClient1 getStorageClient1() {
/* 15 */     StorageClient1 storageClient1 = null;
/*    */     try {
/* 17 */       trackerServer = FastDfsConnectionPool.checkout(waitTimes);
/* 18 */       if (trackerServer != null) {
/* 19 */         StorageServer storageServer = null;
/* 20 */         return new StorageClient1(trackerServer, 
/* 21 */           storageServer);
/*    */       }
/*    */ 
/* 24 */       throw new BusinessException("无法找到有效的文件服务器连接!");
/*    */     } catch (InterruptedException e) {
/*    */     }
/* 27 */     throw new BusinessException("文件服务器异常!");
/*    */   }
/*    */ 
/*    */   private static void idleTrackerServer()
/*    */   {
/* 32 */     FastDfsConnectionPool.checkin(trackerServer);
/*    */   }
/*    */ 
/*    */   public static String upload(String filePath) throws Exception {
/* 36 */     StorageClient1 storageClient1 = getStorageClient1();
/*    */ 
/* 39 */     String fileType = filePath.substring(filePath.lastIndexOf(".") + 1, 
/* 40 */       filePath.length());
/* 41 */     String file_path = storageClient1
/* 42 */       .upload_file1(filePath, fileType, null);
/*    */ 
/* 47 */     idleTrackerServer();
/* 48 */     return "/" + file_path;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.fastdfs.FastDfsUpload
 * JD-Core Version:    0.6.2
 */