/*     */ package com.smarthome.imcp.common;
/*     */ 
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileLock;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.dom4j.Document;
/*     */ import org.dom4j.DocumentHelper;
/*     */ import org.dom4j.Element;
/*     */ import org.dom4j.Node;
/*     */ import org.dom4j.io.DocumentSource;
/*     */ import org.dom4j.io.SAXReader;
/*     */ 
/*     */ public class XmlDom4j
/*     */ {
/*  31 */   Document document = DocumentHelper.createDocument();
/*     */   String filename;
/*     */   private RandomAccessFile raf;
/*     */   private FileChannel fc;
/*     */   private FileLock fl;
/*  42 */   private boolean isLocked = false;
/*     */ 
/*     */   public void setXMLEncoding(String encode) {
/*  45 */     this.document.setXMLEncoding(encode);
/*     */   }
/*     */ 
/*     */   public Element getRootNode() {
/*  49 */     return this.document.getRootElement();
/*     */   }
/*     */ 
/*     */   public Node getNodeByXpath(Node parentNode, String xpath) {
/*  53 */     return parentNode.selectSingleNode(xpath);
/*     */   }
/*     */ 
/*     */   public Element getNode(Element parentNode, String key) {
/*  57 */     return parentNode.element(key);
/*     */   }
/*     */ 
/*     */   public List<Element> getNodes(Element parentNode, String key)
/*     */   {
/*  62 */     return parentNode.elements(key);
/*     */   }
/*     */ 
/*     */   public List<Node> getNodesByXpath(Node parentNode, String xpath)
/*     */   {
/*  67 */     return parentNode.selectNodes(xpath);
/*     */   }
/*     */ 
/*     */   public Element createRootNode(String rootValue)
/*     */   {
/*  77 */     Element root = this.document.addElement(rootValue);
/*  78 */     return root;
/*     */   }
/*     */ 
/*     */   public Element createNode(Element parentNode, String key)
/*     */   {
/*  89 */     Element childNode = parentNode.addElement(key);
/*  90 */     return childNode;
/*     */   }
/*     */ 
/*     */   public Element createChildElement(Element parentNode, String key, String value)
/*     */   {
/* 102 */     String tmp = value;
/* 103 */     if ((value == null) || (value.equalsIgnoreCase("null")) || (value.equals("")))
/* 104 */       tmp = " ";
/* 105 */     Element childNode = parentNode.addElement(key);
/* 106 */     childNode.addText(tmp);
/* 107 */     return childNode;
/*     */   }
/*     */ 
/*     */   public Element addAttribute(Element node, String key, String value)
/*     */   {
/* 119 */     return node.addAttribute(key, value);
/*     */   }
/*     */ 
/*     */   public Element addComment(Element node, String comment) {
/* 123 */     return node.addComment(comment);
/*     */   }
/*     */ 
/*     */   public String getXML()
/*     */   {
/* 132 */     return this.document.asXML();
/*     */   }
/*     */ 
/*     */   public String saveXML(String fileName, String encoding)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 143 */       if ((this.filename == null) || (this.filename.equals("")))
/* 144 */         this.filename = fileName;
/* 145 */       TransformerFactory tf = TransformerFactory.newInstance();
/* 146 */       Transformer transformer = tf.newTransformer();
/* 147 */       DocumentSource source = new DocumentSource(this.document);
/* 148 */       transformer.setOutputProperty("encoding", encoding);
/* 149 */       transformer.setOutputProperty("media-type", "text/html");
/* 150 */       transformer.setOutputProperty("indent", "yes");
/* 151 */       transformer.setOutputProperty(
/* 152 */         "{http://xml.apache.org/xslt}indent-amount", "4");
/* 153 */       OutputStreamWriter osw = new OutputStreamWriter(
/* 154 */         new FileOutputStream(this.filename), encoding);
/* 155 */       BufferedWriter bw = new BufferedWriter(osw);
/* 156 */       StreamResult result = new StreamResult(bw);
/*     */ 
/* 158 */       if (this.isLocked) {
/* 159 */         unLockFile();
/*     */       }
/* 161 */       transformer.transform(source, result);
/*     */     } catch (TransformerException mye) {
/* 163 */       mye.printStackTrace();
/*     */     } catch (IOException exp) {
/* 165 */       exp.printStackTrace();
/*     */     } catch (Exception ex) {
/* 167 */       ex.printStackTrace();
/*     */     }
/* 169 */     String retStr = this.document.asXML();
/* 170 */     return retStr;
/*     */   }
/*     */ 
/*     */   public String outPutXML(String encoding)
/*     */   {
/* 179 */     String retXML = "";
/*     */     try {
/* 181 */       TransformerFactory tf = TransformerFactory.newInstance();
/* 182 */       Transformer transformer = tf.newTransformer();
/* 183 */       DocumentSource source = new DocumentSource(this.document);
/* 184 */       transformer.setOutputProperty("encoding", encoding);
/* 185 */       transformer.setOutputProperty("media-type", "text/html");
/* 186 */       transformer.setOutputProperty("indent", "yes");
/* 187 */       transformer.setOutputProperty(
/* 188 */         "{http://xml.apache.org/xslt}indent-amount", "4");
/* 189 */       StringWriter sw = new StringWriter();
/* 190 */       StreamResult result = new StreamResult(sw);
/* 191 */       transformer.transform(source, result);
/* 192 */       retXML = sw.toString();
/*     */     } catch (TransformerException mye) {
/* 194 */       mye.printStackTrace();
/*     */     }
/*     */ 
/* 197 */     return retXML;
/*     */   }
/*     */ 
/*     */   public Document readXML(String fileName)
/*     */   {
/*     */     try
/*     */     {
/* 208 */       if ((this.filename == null) || (this.filename.equals("")))
/* 209 */         this.filename = fileName;
/* 210 */       SAXReader reader = new SAXReader();
/* 211 */       this.document = reader.read(this.filename);
/*     */     } catch (Exception ex) {
/* 213 */       ex.printStackTrace();
/*     */     }
/* 215 */     return this.document;
/*     */   }
/*     */ 
/*     */   public Document parseXML(String xml)
/*     */   {
/*     */     try
/*     */     {
/* 227 */       SAXReader reader = new SAXReader();
/* 228 */       this.document = reader.read(new StringReader(xml));
/*     */     } catch (Exception e) {
/* 230 */       e.printStackTrace();
/*     */     }
/* 232 */     return this.document;
/*     */   }
/*     */ 
/*     */   public boolean lockFile(String fileName)
/*     */   {
/*     */     try
/*     */     {
/* 244 */       if ((this.filename == null) || (this.filename.equals("")))
/* 245 */         this.filename = fileName;
/* 246 */       this.raf = new RandomAccessFile(new File(this.filename), "rw");
/* 247 */       this.fc = this.raf.getChannel();
/* 248 */       this.fl = this.fc.tryLock();
/* 249 */       if (this.fl.isValid()) {
/* 250 */         return true;
/*     */       }
/* 252 */       return false;
/*     */     } catch (Exception ex) {
/*     */     }
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   public void unLockFile()
/*     */   {
/*     */     try
/*     */     {
/* 266 */       this.fl.release();
/* 267 */       this.fc.close();
/* 268 */       this.raf.close();
/*     */     } catch (Exception ex) {
/* 270 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void main(String[] args)
/*     */     throws IOException
/*     */   {
/* 283 */     XmlDom4j xmlDoc = new XmlDom4j();
/*     */ 
/* 285 */     String xml = "<REQUEST><HEAD><JYLSH>123</JYLSH><JYLX>JC_RZYHXXCX</JYLX><JYFQSJ>YYYYMMDDHHMMSS</JYFQSJ><JYSH>TL0001</JYSH><JYRZM>ZKD</JYRZM></HEAD><BODY><SSSHI>这里有中文</SSSHI><SSSHI1>这里有中文1</SSSHI1><SSSHI2>这里有中文2</SSSHI2></BODY></REQUEST>";
/*     */ 
/* 287 */     xmlDoc.parseXML(xml);
/*     */ 
/* 289 */     Element root = xmlDoc.getRootNode();
/* 290 */     System.out.println(root.getName());
/*     */ 
/* 292 */     Element ehead = root.element("HEAD");
/* 293 */     Element ebody = root.element("BODY");
/* 294 */     for (Iterator localIterator = ehead.elements().iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 295 */       System.out.println(((Element)o).getName() + ":" + ((Element)o).getTextTrim());
/*     */     }
/*     */ 
///* 298 */     for (localIterator = ebody.elements().iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
			  for (Iterator localIterator = ebody.elements().iterator(); localIterator.hasNext(); ) { Object o = localIterator.next();
/* 299 */       System.out.println(((Element)o).getName() + ":" + ((Element)o).getTextTrim());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void test_lock()
/*     */     throws IOException
/*     */   {
/* 325 */     String version = 20071206L+"";//long 转 String
/*     */ 
/* 327 */     lockFile("d:/visiontv/test.xml");
/*     */ 
/* 329 */     Element root = createRootNode("VOD_LIST");
/* 330 */     root.addAttribute("vod_count", "3");
/* 331 */     root.addAttribute("version", version);
/* 332 */     for (int i = 0; i < 3; i++) {
/* 333 */       Element iNode = createNode(root, "VOD");
/* 334 */       iNode.addAttribute("Name", "陈容棄1¤7" + i);
/* 335 */       iNode.addAttribute("Ages", "20" + i);
/* 336 */       iNode.addAttribute("Telephone", "13622223517" + i);
/*     */     }
/* 338 */     unLockFile();
/* 339 */     String str = saveXML("d:/visiontv/test.xml", "GB2312");
/* 340 */     System.out.println(str);
/*     */   }
/*     */ 
/*     */   public String test_write()
/*     */   {
/*     */     try {
/* 346 */       Element root = createRootNode("VOD_LIST");
/* 347 */       root.addAttribute("vod_count", "3");
/* 348 */       root.addAttribute("version", "20071214");
/* 349 */       for (int i = 0; i < 3; i++) {
/* 350 */         Element iNode = createNode(root, "VOD");
/* 351 */         iNode.addAttribute("Name", "陈容棄1¤7" + i);
/* 352 */         iNode.addAttribute("Ages", "20" + i);
/* 353 */         iNode.addAttribute("Telephone", "13622223517" + i);
/*     */       }
/* 355 */       return saveXML("d:/visiontv/test.xml", "UTF-8");
/*     */     }
/*     */     catch (Exception e) {
/* 358 */       e.printStackTrace();
/*     */     }
/* 360 */     return null;
/*     */   }
/*     */ 
/*     */   public Document test_0() {
/* 364 */     Element root = this.document.addElement("root");
/*     */ 
/* 366 */     Element author1 = root.addElement("Lynch");
/* 367 */     author1.addAttribute("Country", "25");
/* 368 */     author1.addAttribute("Age", "China");
/* 369 */     author1.addText("I am great!");
/*     */ 
/* 371 */     Element author2 = root.addElement("Legend");
/* 372 */     author2.addAttribute("Age", "25");
/* 373 */     author2.addAttribute("Country", "China");
/* 374 */     author2.addText("I am great!too!");
/*     */ 
/* 376 */     return this.document;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.common.XmlDom4j
 * JD-Core Version:    0.6.2
 */