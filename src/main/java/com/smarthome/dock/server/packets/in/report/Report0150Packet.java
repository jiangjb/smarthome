/*    */ package com.smarthome.dock.server.packets.in.report;
/*    */ 
/*    */ import com.smarthome.dock.server.packets.PacketParseException;
/*    */ import com.smarthome.dock.server.packets.in.ReportPacket;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import org.apache.commons.io.FileUtils;
/*    */ import org.jboss.netty.buffer.ChannelBuffer;
/*    */ 
/*    */ public class Report0150Packet extends ReportPacket
/*    */ {
/*    */   private int frameStatus;
/*    */   private int temperature;
/*    */   private int humidity;
/*    */   private int electric;
/*    */   private String imgPath;
/*    */ 
/*    */   public Report0150Packet(ChannelBuffer buf)
/*    */     throws PacketParseException
/*    */   {
/* 29 */     super(buf);
/*    */   }
/*    */ 
/*    */   protected void parseFrame(ChannelBuffer buf, int frameLen) throws PacketParseException
/*    */   {
/* 34 */     switch (this.frameType) {
/*    */     case 8:
/* 36 */       this.frameStatus = buf.readByte();
/* 37 */       this.temperature = buf.readByte();
/* 38 */       this.humidity = buf.readByte();
/* 39 */       this.electric = buf.readByte();
/* 40 */       break;
/*    */     case 3:
/* 42 */       byte[] imgByte = new byte[frameLen];
/* 43 */       buf.readBytes(imgByte);
/*    */ 
/* 45 */       String root = System.getProperty("webapp.root");
/*    */ 
/* 47 */       this.imgPath = ("\\pic\\" + System.currentTimeMillis() + ".jpg");
/*    */ 
/* 49 */       File imgFile = new File(root + this.imgPath);
/*    */       try
/*    */       {
/* 52 */         FileUtils.writeByteArrayToFile(imgFile, imgByte);
/*    */       } catch (IOException e) {
/* 54 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getFrameStatus()
/*    */   {
/* 61 */     return this.frameStatus;
/*    */   }
/*    */ 
/*    */   public void setFrameStatus(int frameStatus) {
/* 65 */     this.frameStatus = frameStatus;
/*    */   }
/*    */ 
/*    */   public int getTemperature() {
/* 69 */     return this.temperature;
/*    */   }
/*    */ 
/*    */   public void setTemperature(int temperature) {
/* 73 */     this.temperature = temperature;
/*    */   }
/*    */ 
/*    */   public int getHumidity() {
/* 77 */     return this.humidity;
/*    */   }
/*    */ 
/*    */   public void setHumidity(int humidity) {
/* 81 */     this.humidity = humidity;
/*    */   }
/*    */ 
/*    */   public String getImgPath() {
/* 85 */     return this.imgPath;
/*    */   }
/*    */ 
/*    */   public void setImgPath(String imgPath) {
/* 89 */     this.imgPath = imgPath;
/*    */   }
/*    */ 
/*    */   public int getElectric() {
/* 93 */     return this.electric;
/*    */   }
/*    */ 
/*    */   public void setElectric(int electric) {
/* 97 */     this.electric = electric;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.dock.server.packets.in.report.Report0150Packet
 * JD-Core Version:    0.6.2
 */