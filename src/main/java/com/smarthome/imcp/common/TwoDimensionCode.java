/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import com.google.zxing.BarcodeFormat;
/*    */ import com.google.zxing.BinaryBitmap;
/*    */ import com.google.zxing.DecodeHintType;
/*    */ import com.google.zxing.EncodeHintType;
/*    */ import com.google.zxing.LuminanceSource;
/*    */ import com.google.zxing.MultiFormatReader;
/*    */ import com.google.zxing.MultiFormatWriter;
/*    */ import com.google.zxing.Reader;
/*    */ import com.google.zxing.ReaderException;
/*    */ import com.google.zxing.Result;
/*    */ import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
/*    */ import com.google.zxing.client.j2se.MatrixToImageWriter;
/*    */ import com.google.zxing.common.BitMatrix;
/*    */ import com.google.zxing.common.HybridBinarizer;
/*    */ import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Hashtable;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ public class TwoDimensionCode
/*    */ {
/*    */   private static Result result;
/*    */ 
/*    */   public static void encode(String contents, String path)
/*    */   {
/* 32 */     File file = new File(path);
/*    */     try
/*    */     {
/* 36 */       Hashtable hints = new Hashtable();
/* 37 */       hints.put(EncodeHintType.CHARACTER_SET, "GBK");
/* 38 */       hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
/*    */ 
/* 40 */       BitMatrix byteMatrix = new MultiFormatWriter().encode(contents, 
/* 41 */         BarcodeFormat.QR_CODE, 400, 400, hints);
/* 42 */       MatrixToImageWriter.writeToFile(byteMatrix, 
/* 43 */         path.substring(path.lastIndexOf(".") + 1), file);
/*    */     } catch (Exception e) {
/* 45 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ 
/*    */   public static String decode(String path)
/*    */   {
/* 51 */     File file = new File(path);
/* 52 */     if (file.exists()) {
/*    */       try {
/* 54 */         Reader reader = new MultiFormatReader();
/*    */         try
/*    */         {
/* 57 */           BufferedImage image = ImageIO.read(file);
/* 58 */           if (image == null) {
/* 59 */             System.out.println("Could not decode image");
/*    */           }
/* 61 */           LuminanceSource source = new BufferedImageLuminanceSource(
/* 62 */             image);
/* 63 */           BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
/* 64 */             source));
/* 65 */           Hashtable hints = new Hashtable();
/* 66 */           hints.put(DecodeHintType.CHARACTER_SET, "GBK");
/* 67 */           result = new MultiFormatReader().decode(bitmap, hints);
/*    */         } catch (IOException ioe) {
/* 69 */           System.out.println(ioe.toString());
/*    */         } catch (ReaderException re) {
/* 71 */           System.out.println(re.toString());
/*    */         }
/*    */       } catch (Exception ex) {
/* 74 */         System.out.println(ex.toString());
/*    */       }
/* 76 */       return result.getText();
/*    */     }
/* 78 */     return "\"" + path + "\" 该图片不存在";
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 84 */     encode("城云科技(杭州)有限公司 信息服务事业部", "f:\\1.jpg");
/* 85 */     System.out.println(decode("f:\\1.jpg"));
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.TwoDimensionCode
 * JD-Core Version:    0.6.2
 */