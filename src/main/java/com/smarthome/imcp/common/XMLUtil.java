/*    */ package com.smarthome.imcp.common;
/*    */ 
/*    */ import java.io.StringReader;
/*    */ import java.io.StringWriter;
/*    */ import javax.xml.parsers.DocumentBuilder;
/*    */ import javax.xml.parsers.DocumentBuilderFactory;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerFactory;
/*    */ import javax.xml.transform.dom.DOMSource;
/*    */ import javax.xml.transform.stream.StreamResult;
/*    */ import org.dom4j.DocumentException;
/*    */ import org.dom4j.DocumentHelper;
/*    */ import org.w3c.dom.Document;
/*    */ import org.w3c.dom.Element;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
/*    */ import org.xml.sax.InputSource;
/*    */ 
/*    */ public class XMLUtil
/*    */ {
/*    */   public static boolean isXML(String value)
/*    */   {
/*    */     try
/*    */     {
/* 25 */       DocumentHelper.parseText(value);
/*    */     } catch (DocumentException e) {
/* 27 */       return false;
/*    */     }
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   public static String removeEmpty(String xml) {
/*    */     try {
/* 34 */       StringReader reader = new StringReader(xml);
/* 35 */       DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
/* 36 */       DocumentBuilder dombuilder = domfac.newDocumentBuilder();
/* 37 */       Document doc = dombuilder.parse(new InputSource(reader));
/* 38 */       Element root = doc.getDocumentElement();
/* 39 */       removeEmprty(root);
/* 40 */       DOMSource xmlSource = new DOMSource(doc);
/* 41 */       StringWriter writer = new StringWriter();
/* 42 */       StreamResult result = new StreamResult(writer);
/* 43 */       TransformerFactory tfactory = TransformerFactory.newInstance();
/* 44 */       Transformer transformer = tfactory.newTransformer();
/*    */ 
/* 47 */       transformer.transform(xmlSource, result);
/* 48 */       return writer.toString();
/*    */     } catch (Exception e) {
/* 50 */       e.printStackTrace();
/*    */     }
/* 52 */     return xml;
/*    */   }
/*    */ 
/*    */   private static void removeEmprty(Node parent) {
/* 56 */     NodeList nodes = parent.getChildNodes();
/* 57 */     for (int i = 0; i < nodes.getLength(); ) {
/* 58 */       Node node = nodes.item(i);
/* 59 */       if (node.getNodeType() == 1) {
/* 60 */         if ((!node.hasAttributes()) && (!node.hasChildNodes())) {
/* 61 */           parent.removeChild(node);
/* 62 */           continue;
/*    */         }
/* 64 */         removeEmprty(node);
/*    */       }
/* 66 */       else if ((node.getNodeType() == 3) || 
/* 67 */         (node.getNodeType() == 4)) {
/* 68 */         String value = node.getNodeValue();
/*    */ 
/* 70 */         if (value.length() == 0) {
/* 71 */           parent.removeChild(node);
/* 72 */           continue;
/*    */         }
/*    */       }
/* 75 */       i++;
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.XMLUtil
 * JD-Core Version:    0.6.2
 */