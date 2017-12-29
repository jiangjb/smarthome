/*     */ package com.smarthome.imcp.helper.dispatch;
/*     */ 
/*     */ public class DispatchPageUtil
/*     */ {
/*     */   public static RedirectAction getDispatchListPage(String dispathDocumentPage)
/*     */   {
/*  12 */     RedirectAction redirectAction = new RedirectAction();
/*  13 */     String namespace = "";
/*  14 */     int namespacePos = dispathDocumentPage.lastIndexOf("/");
/*  15 */     if (namespacePos > -1) {
/*  16 */       namespace = dispathDocumentPage.substring(0, namespacePos);
/*  17 */       if (namespace.charAt(0) != '/') {
/*  18 */         namespace = "/" + namespace;
/*     */       }
/*  20 */       namespacePos++;
/*     */     } else {
/*  22 */       namespacePos = 0;
/*     */     }
/*  24 */     redirectAction.setNamespace(namespace);
/*     */ 
/*  26 */     redirectAction.setRedirectAction(dispathDocumentPage);
/*     */ 
/*  28 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchAddPage(String dispatchListPage)
/*     */   {
/*  37 */     RedirectAction redirectAction = new RedirectAction();
/*  38 */     String namespace = "";
/*  39 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/*  40 */     if (namespacePos > -1) {
/*  41 */       namespace = dispatchListPage.substring(0, namespacePos);
/*  42 */       if (namespace.charAt(0) != '/') {
/*  43 */         namespace = "/" + namespace;
/*     */       }
/*  45 */       namespacePos++;
/*     */     } else {
/*  47 */       namespacePos = 0;
/*     */     }
/*  49 */     redirectAction.setNamespace(namespace);
/*     */ 
/*  51 */     redirectAction.setRedirectAction(dispatchListPage.substring(
/*  52 */       namespacePos, dispatchListPage.length()).replaceAll(
/*  53 */       "ListPage.action", "AddPage"));
/*     */ 
/*  55 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchDraftEditPage(String dispatchListPage)
/*     */   {
/*  66 */     RedirectAction redirectAction = new RedirectAction();
/*  67 */     String namespace = "";
/*  68 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/*  69 */     if (namespacePos > -1) {
/*  70 */       namespace = dispatchListPage.substring(0, namespacePos);
/*  71 */       if (namespace.charAt(0) != '/') {
/*  72 */         namespace = "/" + namespace;
/*     */       }
/*  74 */       namespacePos++;
/*     */     } else {
/*  76 */       namespacePos = 0;
/*     */     }
/*  78 */     redirectAction.setNamespace(namespace);
/*     */ 
/*  80 */     redirectAction.setRedirectAction(dispatchListPage.substring(
/*  81 */       namespacePos, dispatchListPage.length()).replaceAll(
/*  82 */       "ListPage.action", "DraftEditPage"));
/*     */ 
/*  84 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchEditPage(String dispatchListPage)
/*     */   {
/*  94 */     RedirectAction redirectAction = new RedirectAction();
/*  95 */     String namespace = "";
/*  96 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/*  97 */     if (namespacePos > -1) {
/*  98 */       namespace = dispatchListPage.substring(0, namespacePos);
/*  99 */       if (namespace.charAt(0) != '/') {
/* 100 */         namespace = "/" + namespace;
/*     */       }
/* 102 */       namespacePos++;
/*     */     } else {
/* 104 */       namespacePos = 0;
/*     */     }
/* 106 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 108 */     redirectAction.setRedirectAction(dispatchListPage.substring(
/* 109 */       namespacePos, dispatchListPage.length()).replaceAll(
/* 110 */       "ListPage.action", "EditPage"));
/*     */ 
/* 112 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchViewPage(String dispatchListPage)
/*     */   {
/* 122 */     RedirectAction redirectAction = new RedirectAction();
/* 123 */     String namespace = "";
/* 124 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/* 125 */     if (namespacePos > -1) {
/* 126 */       namespace = dispatchListPage.substring(0, namespacePos);
/* 127 */       if (namespace.charAt(0) != '/') {
/* 128 */         namespace = "/" + namespace;
/*     */       }
/* 130 */       namespacePos++;
/*     */     } else {
/* 132 */       namespacePos = 0;
/*     */     }
/* 134 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 136 */     redirectAction.setRedirectAction(dispatchListPage.substring(
/* 137 */       namespacePos, dispatchListPage.length()).replaceAll(
/* 138 */       "ListPage.action", "ViewPage"));
/*     */ 
/* 140 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchApprovePage(String dispatchListPage)
/*     */   {
/* 150 */     RedirectAction redirectAction = new RedirectAction();
/* 151 */     String namespace = "";
/* 152 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/* 153 */     if (namespacePos > -1) {
/* 154 */       namespace = dispatchListPage.substring(0, namespacePos);
/* 155 */       if (namespace.charAt(0) != '/') {
/* 156 */         namespace = "/" + namespace;
/*     */       }
/* 158 */       namespacePos++;
/*     */     } else {
/* 160 */       namespacePos = 0;
/*     */     }
/* 162 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 164 */     redirectAction.setRedirectAction(dispatchListPage.substring(
/* 165 */       namespacePos, dispatchListPage.length()).replaceAll(
/* 166 */       "ListPage.action", "ApprovePage"));
/*     */ 
/* 168 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchDeployPage(String dispatchListPage)
/*     */   {
/* 178 */     RedirectAction redirectAction = new RedirectAction();
/* 179 */     String namespace = "";
/* 180 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/* 181 */     if (namespacePos > -1) {
/* 182 */       namespace = dispatchListPage.substring(0, namespacePos);
/* 183 */       if (namespace.charAt(0) != '/') {
/* 184 */         namespace = "/" + namespace;
/*     */       }
/* 186 */       namespacePos++;
/*     */     } else {
/* 188 */       namespacePos = 0;
/*     */     }
/* 190 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 192 */     redirectAction.setRedirectAction(dispatchListPage.substring(
/* 193 */       namespacePos, dispatchListPage.length()).replaceAll(
/* 194 */       "ListPage.action", "DeployPage"));
/*     */ 
/* 196 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchDraftDeletePage(String dispatchListPage)
/*     */   {
/* 207 */     RedirectAction redirectAction = new RedirectAction();
/* 208 */     String namespace = "";
/* 209 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/* 210 */     if (namespacePos > -1) {
/* 211 */       namespace = dispatchListPage.substring(0, namespacePos);
/* 212 */       if (namespace.charAt(0) != '/') {
/* 213 */         namespace = "/" + namespace;
/*     */       }
/* 215 */       namespacePos++;
/*     */     } else {
/* 217 */       namespacePos = 0;
/*     */     }
/* 219 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 221 */     redirectAction.setRedirectAction("deleteDraft" + 
/* 222 */       dispatchListPage.substring(namespace.length() + 
/* 223 */       "dispatch".length(), dispatchListPage
/* 224 */       .indexOf("ListPage")));
/* 225 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchEditDeletePage(String dispatchListPage)
/*     */   {
/* 236 */     RedirectAction redirectAction = new RedirectAction();
/* 237 */     String namespace = "";
/* 238 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/* 239 */     if (namespacePos > -1) {
/* 240 */       namespace = dispatchListPage.substring(0, namespacePos);
/* 241 */       if (namespace.charAt(0) != '/') {
/* 242 */         namespace = "/" + namespace;
/*     */       }
/* 244 */       namespacePos++;
/*     */     } else {
/* 246 */       namespacePos = 0;
/*     */     }
/* 248 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 250 */     redirectAction.setRedirectAction("deleteEdit" + 
/* 251 */       dispatchListPage.substring(namespace.length() + 
/* 252 */       "dispatch".length(), dispatchListPage
/* 253 */       .indexOf("ListPage")));
/* 254 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchDeployDeletePage(String dispatchListPage)
/*     */   {
/* 265 */     RedirectAction redirectAction = new RedirectAction();
/* 266 */     String namespace = "";
/* 267 */     int namespacePos = dispatchListPage.lastIndexOf("/");
/* 268 */     if (namespacePos > -1) {
/* 269 */       namespace = dispatchListPage.substring(0, namespacePos);
/* 270 */       if (namespace.charAt(0) != '/') {
/* 271 */         namespace = "/" + namespace;
/*     */       }
/* 273 */       namespacePos++;
/*     */     } else {
/* 275 */       namespacePos = 0;
/*     */     }
/* 277 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 279 */     redirectAction.setRedirectAction("deleteDeploy" + 
/* 280 */       dispatchListPage.substring(namespace.length() + 
/* 281 */       "dispatch".length(), dispatchListPage
/* 282 */       .indexOf("ListPage")));
/* 283 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchVideoPage(String dispathDocumentPage)
/*     */   {
/* 293 */     RedirectAction redirectAction = new RedirectAction();
/* 294 */     String namespace = "";
/* 295 */     int namespacePos = dispathDocumentPage.lastIndexOf("/");
/* 296 */     if (namespacePos > -1) {
/* 297 */       namespace = dispathDocumentPage.substring(0, namespacePos);
/* 298 */       if (namespace.charAt(0) != '/') {
/* 299 */         namespace = "/" + namespace;
/*     */       }
/* 301 */       namespacePos++;
/*     */     } else {
/* 303 */       namespacePos = 0;
/*     */     }
/* 305 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 307 */     redirectAction.setRedirectAction(dispathDocumentPage.substring(
/* 308 */       namespacePos, dispathDocumentPage.length()).replaceAll(
/* 309 */       "Document", "Video"));
/*     */ 
/* 311 */     return redirectAction;
/*     */   }
/*     */ 
/*     */   public static RedirectAction getDispatchBallotPage(String dispathDocumentPage)
/*     */   {
/* 322 */     RedirectAction redirectAction = new RedirectAction();
/* 323 */     String namespace = "";
/* 324 */     int namespacePos = dispathDocumentPage.lastIndexOf("/");
/* 325 */     if (namespacePos > -1) {
/* 326 */       namespace = dispathDocumentPage.substring(0, namespacePos);
/* 327 */       if (namespace.charAt(0) != '/') {
/* 328 */         namespace = "/" + namespace;
/*     */       }
/* 330 */       namespacePos++;
/*     */     } else {
/* 332 */       namespacePos = 0;
/*     */     }
/* 334 */     redirectAction.setNamespace(namespace);
/*     */ 
/* 336 */     redirectAction.setRedirectAction(dispathDocumentPage.substring(
/* 337 */       namespacePos, dispathDocumentPage.length()).replaceAll(
/* 338 */       "Document", "Ballot"));
/*     */ 
/* 340 */     return redirectAction;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.helper.dispatch.DispatchPageUtil
 * JD-Core Version:    0.6.2
 */