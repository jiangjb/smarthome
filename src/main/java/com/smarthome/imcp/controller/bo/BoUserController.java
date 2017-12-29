/*     */ package com.smarthome.imcp.controller.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Md5;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*     */ import com.smarthome.imcp.controller.RequestJson;
/*     */ import com.smarthome.imcp.controller.ResultJson;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.service.bo.BoUserServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ public class BoUserController
/*     */ {
/*  28 */   private final String MENU_CODE = "BO0002";
/*     */ 
/*     */   @Autowired
/*     */   private BoUserServiceIface<BoUser, Serializable> boUserService;
/*     */ 
/*     */   @RequestMapping({"public/registerUser.do"})
/*     */   @ResponseBody
/*     */   public Object registerUser(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd, @RequestParam("userPhone") String userPhone)
/*     */   {
/*  45 */     Md5 md5 = new Md5();
/*     */ 
/*  47 */     BoUser boUser = new BoUser();
/*  48 */     boUser.setUserName(userName);
/*  49 */     boUser.setUserPwd(md5.getMD5ofStr(userPwd));
/*  50 */     boUser.setUserPhone(userPhone);
/*  51 */     boUser = (BoUser)this.boUserService.save(boUser);
/*  52 */     RequestJson requestJson = new RequestJson();
/*  53 */     Map map = new HashMap();
/*     */ 
/*  55 */     map.put("userCode", boUser.getUserCode());
/*     */ 
/*  57 */     requestJson.setData(map);
/*  58 */     return requestJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserMapPage.do"})
/*     */   public ModelAndView dispatchBoUserMapPage() {
/*  63 */     List list = this.boUserService.getAllList();
/*  64 */     return new ModelAndView("bo/user/user_map", "boUserList", list);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserLookup.do"})
/*     */   public ModelAndView dispatchBoUserLookup() {
/*  69 */     List list = this.boUserService.getAllList();
/*  70 */     return new ModelAndView("bo/user/user_lookup", "boUserList", list);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserListPage.do"})
/*     */   public String dispatchBoUserListPage() {
/*  75 */     return "bo/user/user_list";
/*     */   }
/*     */   @RequestMapping({"bo/searchBoUserListAjax.do"})
/*     */   @ResponseBody
/*     */   public Object searchBoUserListAjax(Page page, SearchCriteriaUser searchCriteria) {
/*  81 */     List<BoUser> boUserList = this.boUserService.getList(searchCriteria, page);
/*  82 */     FlexigridJson flexJson = new FlexigridJson();
/*  83 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/*  84 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/*  85 */     List rows = new ArrayList();
/*  86 */     for (BoUser model : boUserList) {
/*  87 */       FlexigridJsonRow row = new FlexigridJsonRow();
/*  88 */       row.setId(model.getUserId());
/*  89 */       row.setCell(model);
/*  90 */       rows.add(row);
/*     */     }
/*  92 */     flexJson.setRows(rows);
/*  93 */     return flexJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserAddPage.do"})
/*     */   public String dispatchBoUserAddPage() {
/*  98 */     return "bo/user/user_add";
/*     */   }
/*     */   @RequestMapping({"bo/saveBoUser.do"})
/*     */   @ResponseBody
/*     */   public Object saveBoUser(BoUser boUser) {
/* 104 */     this.boUserService.save(boUser);
/* 105 */     return new ResultJson("保存成功！", "200", "BO0002");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserEditPage.do"})
/*     */   public ModelAndView dispatchBoUserEditPage(@RequestParam("uid") String uid) {
/* 110 */     Integer id = Integer.valueOf(uid);
/* 111 */     BoUser boUser = (BoUser)this.boUserService.findByKey(id);
/*     */ 
/* 113 */     return new ModelAndView("bo/user/user_edit", "boUser", boUser);
/*     */   }
/*     */   @RequestMapping({"bo/editBoUser.do"})
/*     */   @ResponseBody
/*     */   public Object editBoUser(BoUser boUser) {
/* 119 */     this.boUserService.update(boUser);
/* 120 */     return new ResultJson("修改成功！", "200", "BO0002");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserViewPage.do"})
/*     */   public ModelAndView dispatchBoUserViewPage(@RequestParam("uid") String uid) {
/* 125 */     Integer id = Integer.valueOf(uid);
/* 126 */     BoUser boUser = (BoUser)this.boUserService.findByKey(id);
/* 127 */     return new ModelAndView("bo/user/user_view", "boUser", boUser);
/*     */   }
/*     */   @RequestMapping({"bo/deleteBoUser.do"})
/*     */   @ResponseBody
/*     */   public Object deleteBoUser(@RequestParam("uid") String uid) {
/* 133 */     String ids = uid;
/* 134 */     this.boUserService.deleteByKeys(ids);
/* 135 */     return new ResultJson("删除成功！", "200", "BO0002");
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoUserController
 * JD-Core Version:    0.6.2
 */