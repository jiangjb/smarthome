/*     */ package com.smarthome.imcp.secur;
/*     */ 
/*     */ import com.smarthome.imcp.dao.vo.system.OperateVo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public final class SecurCommon
/*     */ {
/*     */   public static final int OPER_LIST_ADD = 0;
/*     */   public static final int OPER_LIST_EDIT = 1;
/*     */   public static final int OPER_LIST_DELETE = 2;
/*     */   public static final int OPER_LIST_VIEW = 3;
/*     */   public static final int OPER_LIST_SEARCH = 4;
/*     */   public static final int OPER_LIST_ADVSEARCH = 5;
/*     */   public static final int OPER_LIST_APPROVE = 6;
/*     */   public static final String OPER_LIST_ADD_NAME = "添加";
/*     */   public static final String OPER_LIST_EDIT_NAME = "修改";
/*     */   public static final String OPER_LIST_DELETE_NAME = "删除";
/*     */   public static final String OPER_LIST_VIEW_NAME = "查看";
/*     */   public static final String OPER_LIST_SEARCH_NAME = "查询";
/*     */   public static final String OPER_LIST_ADVSEARCH_NAME = "高级查询";
/*     */   public static final String OPER_LIST_APPROVE_NAME = "审批";
/*     */   public static final int OPER_LIST_COUNT = 7;
/*     */   public static final String ROLE_SUPER_ADMIN = "SUP";
/*     */   public static final String ROLE_WASU_ADMIN = "WAS";
/*     */   public static final String ROLE_NORMAL_ADMIN = "NOR";
/*     */   public static final String SECUR_ERROR_CODE_NO_POLIT = "NO_POLIT";
/*     */ 
/*     */   public static boolean isOperationAccessible(String roleType, int intaOperIdx)
/*     */   {
/*  43 */     return true;
/*     */   }
/*     */ 
/*     */   public static String getOperateList(String button) {
/*  47 */     String operList = "";
/*  48 */     for (int i = 0; i < 7; i++) {
/*  49 */       operList = operList + (button.indexOf(i) >= 0 ? 'Y' : 'N');
/*     */     }
/*  51 */     return operList;
/*     */   }
/*     */ 
/*     */   public static String getOperateIndex(String operateList) {
/*  55 */     String operIndex = "";
/*  56 */     for (int i = 0; i < operateList.length(); i++) {
/*  57 */       if (operateList.charAt(i) == 'Y') {
/*  58 */         operIndex = operIndex + i + ",";
/*     */       }
/*     */     }
/*  61 */     return operIndex;
/*     */   }
/*     */ 
/*     */   public static String mergeOpreate(String operate1, String operate2) {
/*  65 */     String opreate = "";
/*  66 */     int opreate1Size = operate1.length();
/*  67 */     int opreate2Size = operate2.length();
/*  68 */     int size = opreate1Size >= opreate2Size ? opreate1Size : opreate2Size;
/*  69 */     for (int i = 0; i < size; i++) {
/*  70 */       if ((opreate1Size > i ? operate1.charAt(i) : 32) != 89);
/*  70 */       opreate = opreate + ((opreate2Size > i ? operate2
/*  71 */         .charAt(i) : 32) == 89 ? 'Y' : 'N');
/*     */     }
/*     */ 
/*  74 */     return opreate;
/*     */   }
/*     */ 
/*     */   public static List<OperateVo> getOpreateVoList(String operateList) {
/*  78 */     List operateVoList = new ArrayList();
/*  79 */     for (int i = 0; i < 7; i++) {
/*  80 */       char value = operateList.length() > i ? operateList.charAt(i) : 'N';
/*  81 */       switch (i) {
/*     */       case 0:
/*  83 */         operateVoList.add(new OperateVo("添加", 
/*  84 */           0, value));
/*  85 */         break;
/*     */       case 1:
/*  87 */         operateVoList.add(new OperateVo("修改", 
/*  88 */           1, value));
/*  89 */         break;
/*     */       case 2:
/*  91 */         operateVoList.add(new OperateVo("删除", 
/*  92 */           2, value));
/*  93 */         break;
/*     */       case 3:
/*  95 */         operateVoList.add(new OperateVo("查看", 
/*  96 */           3, value));
/*  97 */         break;
/*     */       case 4:
/*  99 */         operateVoList.add(new OperateVo("查询", 
/* 100 */           4, value));
/* 101 */         break;
/*     */       case 5:
/* 103 */         operateVoList.add(new OperateVo("高级查询", 
/* 104 */           5, value));
/* 105 */         break;
/*     */       case 6:
/* 107 */         operateVoList.add(new OperateVo("审批", 
/* 108 */           6, value));
/*     */       }
/*     */     }
/*     */ 
/* 112 */     return operateVoList;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.secur.SecurCommon
 * JD-Core Version:    0.6.2
 */