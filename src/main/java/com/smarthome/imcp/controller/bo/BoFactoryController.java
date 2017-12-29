/*    */ package com.smarthome.imcp.controller.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*    */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*    */ import com.smarthome.imcp.controller.ResultJson;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFactory;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFactory;
/*    */ import com.smarthome.imcp.service.bo.BoFactoryServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.bind.annotation.RequestParam;
/*    */ import org.springframework.web.bind.annotation.ResponseBody;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ 
/*    */ @Controller
/*    */ public class BoFactoryController
/*    */ {
/* 26 */   private final String MENU_CODE = "BO0005";
/*    */ 
/*    */   @Autowired
/*    */   private BoFactoryServiceIface<BoFactory, Serializable> boFactoryService;
/*    */ 
/* 32 */   @RequestMapping({"bo/dispatchBoFactoryLookup.do"})
/*    */   public ModelAndView dispatchBoFactoryLookup() { List list = this.boFactoryService.getAllList();
/* 33 */     return new ModelAndView("bo/factory/factory_lookup", "boFactoryList", list); }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoFactoryListPage.do"})
/*    */   public String dispatchBoFactoryListPage()
/*    */   {
/* 38 */     return "bo/factory/factory_list";
/*    */   }
/*    */   @RequestMapping({"bo/searchBoFactoryListAjax.do"})
/*    */   @ResponseBody
/*    */   public Object searchBoFactoryListAjax(Page page, SearchCriteriaFactory searchCriteria) {
/* 44 */     List<BoFactory> boFactoryList = this.boFactoryService.getList(searchCriteria, page);
/* 45 */     FlexigridJson flexJson = new FlexigridJson();
/* 46 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/* 47 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/* 48 */     List rows = new ArrayList();
/* 49 */     for (BoFactory model : boFactoryList) {
/* 50 */       FlexigridJsonRow row = new FlexigridJsonRow();
/* 51 */       row.setId(model.getFactoryId());
/* 52 */       row.setCell(model);
/* 53 */       rows.add(row);
/*    */     }
/* 55 */     flexJson.setRows(rows);
/* 56 */     return flexJson;
/*    */   }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoFactoryAddPage.do"})
/*    */   public String dispatchBoFactoryAddPage() {
/* 61 */     return "bo/factory/factory_add";
/*    */   }
/*    */   @RequestMapping({"bo/saveBoFactory.do"})
/*    */   @ResponseBody
/*    */   public Object saveBoFactory(BoFactory boFactory) {
/* 67 */     this.boFactoryService.save(boFactory);
/* 68 */     return new ResultJson("保存成功", "200", "BO0005");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoFactoryEditPage.do"})
/*    */   public ModelAndView dispatchBoFactoryEditPage(@RequestParam("uid") String uid) {
/* 73 */     Integer id = Integer.valueOf(uid);
/* 74 */     BoFactory boFactory = (BoFactory)this.boFactoryService.findByKey(id);
/*    */ 
/* 76 */     return new ModelAndView("bo/factory/factory_edit", "boFactory", boFactory);
/*    */   }
/*    */   @RequestMapping({"bo/editBoFactory.do"})
/*    */   @ResponseBody
/*    */   public Object editBoFactory(BoFactory boFactory) {
/* 82 */     this.boFactoryService.update(boFactory);
/* 83 */     return new ResultJson("修改成功", "200", "BO0005");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoFactoryViewPage.do"})
/*    */   public ModelAndView dispatchBoFactoryViewPage(@RequestParam("uid") String uid) {
/* 88 */     Integer id = Integer.valueOf(uid);
/* 89 */     BoFactory boFactory = (BoFactory)this.boFactoryService.findByKey(id);
/* 90 */     return new ModelAndView("bo/factory/factory_view", "boFactory", boFactory);
/*    */   }
/*    */   @RequestMapping({"bo/deleteBoFactory.do"})
/*    */   @ResponseBody
/*    */   public Object deleteBoFactory(@RequestParam("uid") String uid) {
/* 96 */     String ids = uid;
/* 97 */     this.boFactoryService.deleteByKeys(ids);
/* 98 */     return new ResultJson("删除成功", "200", "BO0005");
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoFactoryController
 * JD-Core Version:    0.6.2
 */