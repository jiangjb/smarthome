/*    */ package com.smarthome.imcp.controller.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*    */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*    */ import com.smarthome.imcp.controller.ResultJson;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAdvice;
/*    */ import com.smarthome.imcp.dao.model.bo.BoAdvice;
/*    */ import com.smarthome.imcp.service.bo.BoAdviceServiceIface;
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
/*    */ public class BoAdviceController
/*    */ {
/* 26 */   private final String MENU_CODE = "BO0009";
/*    */ 
/*    */   @Autowired
/*    */   private BoAdviceServiceIface<BoAdvice, Serializable> boAdviceService;
/*    */ 
/* 32 */   @RequestMapping({"bo/dispatchBoAdviceListPage.do"})
/*    */   public String dispatchBoAdviceListPage() { System.out.println("sssssssssssssssssss");return "bo/advice/advice_list"; }
/*    */ 
/*    */   @RequestMapping({"bo/searchBoAdviceListAjax.do"})
/*    */   @ResponseBody
/*    */   public Object searchBoAdviceListAjax(Page page, SearchCriteriaAdvice searchCriteria) {
/* 38 */     List<BoAdvice> boAdviceList = this.boAdviceService.getList(searchCriteria, page);
/* 39 */     FlexigridJson flexJson = new FlexigridJson();
/* 40 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/* 41 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/* 42 */     List rows = new ArrayList();
/* 43 */     for (BoAdvice model : boAdviceList) {
/* 44 */       FlexigridJsonRow row = new FlexigridJsonRow();
/* 45 */       row.setId(model.getAdviceId());
/* 46 */       row.setCell(model);
/* 47 */       rows.add(row);
/*    */     }
/* 49 */     flexJson.setRows(rows);
/* 50 */     return flexJson;
/*    */   }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoAdviceAddPage.do"})
/*    */   public String dispatchBoAdviceAddPage() {
/* 55 */     return "bo/advice/advice_add";
/*    */   }
/*    */   @RequestMapping({"bo/saveBoAdvice.do"})
/*    */   @ResponseBody
/*    */   public Object saveBoAdvice(BoAdvice boAdvice) {
/* 61 */     this.boAdviceService.save(boAdvice);
/* 62 */     return new ResultJson("保存成功！", "200", "BO0009");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoAdviceEditPage.do"})
/*    */   public ModelAndView dispatchBoAdviceEditPage(@RequestParam("uid") String uid) {
/* 67 */     Integer id = Integer.valueOf(uid);
/* 68 */     BoAdvice boAdvice = (BoAdvice)this.boAdviceService.findByKey(id);
/*    */ 
/* 70 */     return new ModelAndView("bo/advice/advice_edit", "boAdvice", boAdvice);
/*    */   }
/*    */   @RequestMapping({"bo/editBoAdvice.do"})
/*    */   @ResponseBody
/*    */   public Object editBoAdvice(BoAdvice boAdvice) {
/* 76 */     this.boAdviceService.update(boAdvice);
/* 77 */     return new ResultJson("修改成功！", "200", "BO0009");
/*    */   }
/*    */ 
/*    */   @RequestMapping({"bo/dispatchBoAdviceViewPage.do"})
/*    */   public ModelAndView dispatchBoAdviceViewPage(@RequestParam("uid") String uid) {
/* 82 */     Integer id = Integer.valueOf(uid);
/* 83 */     BoAdvice boAdvice = (BoAdvice)this.boAdviceService.findByKey(id);
/* 84 */     return new ModelAndView("bo/advice/advice_view", "boAdvice", boAdvice);
/*    */   }
/*    */   @RequestMapping({"bo/deleteBoAdvice.do"})
/*    */   @ResponseBody
/*    */   public Object deleteBoAdvice(@RequestParam("uid") String uid) {
/* 90 */     String ids = uid;
/* 91 */     this.boAdviceService.deleteByKeys(ids);
/* 92 */     return new ResultJson("删除成功！", "200", "BO0009");
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoAdviceController
 * JD-Core Version:    0.6.2
 */