/*    */ package com.smarthome.imcp.helper.system;
/*    */ 
/*    */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*    */ import java.util.List;
/*    */ 
/*    */ public class SysDictCodeHelper
/*    */ {
/*    */   public static String genJqueryTree(List<SysDictCode> sysDictCodeList)
/*    */   {
/* 10 */     StringBuffer sb = new StringBuffer("");
/* 11 */     sb.append("<ul class=\"tree treeFolder expandFirst\">");
/* 12 */     genTreeNode(sb, sysDictCodeList);
/* 13 */     sb.append("</ul>");
/* 14 */     return sb.toString();
/*    */   }
/*    */ 
/*    */   public static void genTreeNode(StringBuffer sb, List<SysDictCode> sysDictCodeList)
/*    */   {
/* 19 */     for (SysDictCode sysDictCode : sysDictCodeList) {
/* 20 */       sb.append("<li id=\"li");
/* 21 */       sb.append(sysDictCode.getDictCode());
/* 22 */       sb.append("\">");
/* 23 */       sb.append("<a href=\"#\"");
/* 24 */       sb.append(" tname=\"");
/* 25 */       sb.append(sysDictCode.getDictCodeName());
/* 26 */       sb.append("\"");
/* 27 */       sb.append(" tvalue=\"");
/* 28 */       sb.append(sysDictCode.getDictCode());
/* 29 */       sb.append("\"");
/* 30 */       sb.append(">");
/* 31 */       sb.append(sysDictCode.getDictCodeName());
/* 32 */       sb.append("</a>");
/* 33 */       sb.append("</li>");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.helper.system.SysDictCodeHelper
 * JD-Core Version:    0.6.2
 */