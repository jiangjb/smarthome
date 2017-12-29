/*    */ package com.smarthome.dock.server.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.RandomAccessFile;
/*    */ 
/*    */ public class FileSegmentor
/*    */ {
/*    */   private RandomAccessFile file;
/*    */   private int totalFragments;
/*    */   private int fragmentSize;
/*    */   private long fileSize;
/*    */ 
/*    */   public FileSegmentor(String path, int fragmentSize)
/*    */   {
/*    */     try
/*    */     {
/* 28 */       this.fragmentSize = fragmentSize;
/* 29 */       this.file = new RandomAccessFile(path, "r");
/* 30 */       this.fileSize = this.file.length();
/* 31 */       this.totalFragments = ((int)(this.file.length() - 1L) / fragmentSize + 1);
/*    */     } catch (IOException e) {
/* 33 */       this.totalFragments = 0;
/*    */     }
/*    */   }
/*    */ 
/*    */   public boolean isLoadSuccess()
/*    */   {
/* 42 */     return this.totalFragments > 0;
/*    */   }
/*    */ 
/*    */   public byte[] getFragment(int index)
/*    */   {
/* 54 */     if (index >= this.totalFragments)
/* 55 */       return null;
/*    */     try
/*    */     {
/* 58 */       byte[] ret = null;
/* 59 */       if (index < this.totalFragments - 1)
/* 60 */         ret = new byte[this.fragmentSize];
/*    */       else {
/* 62 */         ret = new byte[(int)this.file.length() % this.fragmentSize];
/*    */       }
/* 64 */       this.file.seek(index * this.fragmentSize);
/* 65 */       this.file.readFully(ret);
/* 66 */       return ret; } catch (IOException e) {
/*    */     }
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */   public void close()
/*    */   {
/*    */     try
/*    */     {
/* 77 */       this.file.close();
/*    */     }
/*    */     catch (IOException localIOException)
/*    */     {
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getTotalFragments()
/*    */   {
/* 86 */     return this.totalFragments;
/*    */   }
/*    */ 
/*    */   public long getFileSize() {
/* 90 */     return this.fileSize;
/*    */   }
/*    */ 
/*    */   public int getFragmentSize() {
/* 94 */     return this.fragmentSize;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.util.FileSegmentor
 * JD-Core Version:    0.6.2
 */