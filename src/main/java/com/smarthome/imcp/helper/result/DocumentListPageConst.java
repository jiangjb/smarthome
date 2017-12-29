/*    */ package com.smarthome.imcp.helper.result;
/*    */ 
/*    */ public class DocumentListPageConst
/*    */ {
/*    */   public static final String DOCUMENT_LIST_PAGE = "cms/dispatchDocumentListPage";
/*    */   public static final String DOCUMENT_LIST_PAGE_DRAFT = "cms/dispatchDocumentDraftListPage";
/*    */   public static final String DOCUMENT_LIST_PAGE_EDIT = "cms/dispatchDocumentEditListPage";
/*    */   public static final String DOCUMENT_LIST_PAGE_APPROVE = "cms/dispatchDocumentApproveListPage";
/*    */   public static final String DOCUMENT_LIST_PAGE_DEPLOY = "cms/dispatchDocumentDeployListPage";
/*    */   public static final String DOCUMENT_LIST_PAGE_TEMPLATE = "cms/dispatchCmsColumnTempletListPage";
/*    */ 
/*    */   public static DocumentPage getPageUrlByMenuCode(String menuCode)
/*    */   {
/* 28 */     DocumentPage documentPage = new DocumentPage();
/* 29 */     if ("DRAFT".equals(menuCode)) {
/* 30 */       documentPage.setUrl("cms/dispatchDocumentDraftListPage.action");
/* 31 */       documentPage.setRel("DRA0001");
/* 32 */       documentPage.setSieadType("I");
/* 33 */     } else if ("EDIT".equals(menuCode)) {
/* 34 */       documentPage.setUrl("cms/dispatchDocumentEditListPage.action");
/* 35 */       documentPage.setRel("EDI0001");
/* 36 */       documentPage.setSieadType("E");
/* 37 */     } else if ("APPROVE".equals(menuCode)) {
/* 38 */       documentPage.setUrl("cms/dispatchDocumentApproveListPage.action");
/* 39 */       documentPage.setRel("APP0001");
/* 40 */       documentPage.setSieadType("A");
/* 41 */     } else if ("DEPLOY".equals(menuCode)) {
/* 42 */       documentPage.setUrl("cms/dispatchDocumentDeployListPage.action");
/* 43 */       documentPage.setRel("DEP0001");
/* 44 */       documentPage.setSieadType("D");
/* 45 */     } else if ("DOCUMENT".equals(menuCode)) {
/* 46 */       documentPage.setUrl("cms/dispatchDocumentListPage.action");
/* 47 */       documentPage.setRel("CMS0003");
/* 48 */       documentPage.setSieadType("S");
/* 49 */     } else if ("PORTAL".equals(menuCode)) {
/* 50 */       documentPage.setUrl("");
/* 51 */       documentPage.setRel("CMS0011");
/* 52 */       documentPage.setToParent(true);
/* 53 */     } else if ("".equals(menuCode)) {
/* 54 */       documentPage.setUrl("");
/* 55 */       documentPage.setRel("");
/* 56 */       documentPage.setSieadType("");
/*    */     }
/* 58 */     return documentPage;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.helper.result.DocumentListPageConst
 * JD-Core Version:    0.6.2
 */