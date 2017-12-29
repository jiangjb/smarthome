/*     */ package com.smarthome.imcp.controller.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*     */ import com.smarthome.imcp.controller.ResultJson;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDeviceType;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDeviceType;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceTypeServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ public class BoDeviceTypeController
/*     */ {
/*  25 */   private final String MENU_CODE = "BO0007";
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceTypeServiceIface<BoDeviceType, Serializable> boDeviceTypeService;
/*     */ 
/*  31 */   @RequestMapping({"bo/dispatchBoDeviceTypeLookup.do"})
/*     */   public ModelAndView dispatchBoDeviceTypeLookup(@RequestParam("factoryId") Integer factoryId, String typeName) { SearchCriteriaDeviceType searchCriteriaDeviceType = new SearchCriteriaDeviceType();
/*  32 */     searchCriteriaDeviceType.setFactoryId(factoryId);
/*  33 */     searchCriteriaDeviceType.setTypeName(typeName);
/*  34 */     List list = this.boDeviceTypeService.getList(searchCriteriaDeviceType);
/*     */ 
/*  36 */     ModelAndView model = new ModelAndView("bo/deviceType/deviceType_lookup", "boDeviceTypeList", list);
/*  37 */     model.addObject("factoryId", factoryId);
/*     */ 
/*  39 */     return model; }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceTypeListPage.do"})
/*     */   public String dispatchBoDeviceTypeListPage()
/*     */   {
/*  44 */     return "bo/deviceType/deviceType_list";
/*     */   }
/*     */   @RequestMapping({"bo/searchBoDeviceTypeListAjax.do"})
/*     */   @ResponseBody
/*     */   public Object searchBoDeviceTypeListAjax(Page page, SearchCriteriaDeviceType searchCriteria) {
/*  50 */     List<BoDeviceType> boDeviceTypeList = this.boDeviceTypeService.getList(searchCriteria, page);
/*  51 */     FlexigridJson flexJson = new FlexigridJson();
/*  52 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/*  53 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/*  54 */     List rows = new ArrayList();
/*  55 */     for (BoDeviceType model : boDeviceTypeList) {
/*  56 */       FlexigridJsonRow row = new FlexigridJsonRow();
/*  57 */       row.setId(model.getTypeId());
/*  58 */       row.setCell(model);
/*  59 */       rows.add(row);
/*     */     }
/*  61 */     flexJson.setRows(rows);
/*  62 */     return flexJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceTypeAddPage.do"})
/*     */   public String dispatchBoDeviceTypeAddPage() {
/*  67 */     return "bo/deviceType/deviceType_add";
/*     */   }
/*     */   @RequestMapping({"bo/saveBoDeviceType.do"})
/*     */   @ResponseBody
/*     */   public Object saveBoDeviceType(BoDeviceType boDeviceType) {
/*  73 */     this.boDeviceTypeService.save(boDeviceType);
/*  74 */     return new ResultJson("保存成功！", "200", "BO0007");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceTypeEditPage.do"})
/*     */   public ModelAndView dispatchBoDeviceTypeEditPage(@RequestParam("uid") String uid) {
/*  79 */     Integer id = Integer.valueOf(uid);
/*  80 */     BoDeviceType boDeviceType = (BoDeviceType)this.boDeviceTypeService.findByKey(id);
/*     */ 
/*  82 */     return new ModelAndView("bo/deviceType/deviceType_edit", "boDeviceType", boDeviceType);
/*     */   }
/*     */   @RequestMapping({"bo/editBoDeviceType.do"})
/*     */   @ResponseBody
/*     */   public Object editBoDeviceType(BoDeviceType boDeviceType) {
/*  88 */     this.boDeviceTypeService.update(boDeviceType);
/*  89 */     return new ResultJson("修改成功！", "200", "BO0007");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceTypeViewPage.do"})
/*     */   public ModelAndView dispatchBoDeviceTypeViewPage(@RequestParam("uid") String uid) {
/*  94 */     Integer id = Integer.valueOf(uid);
/*  95 */     BoDeviceType boDeviceType = (BoDeviceType)this.boDeviceTypeService.findByKey(id);
/*  96 */     return new ModelAndView("bo/deviceType/deviceType_view", "boDeviceType", boDeviceType);
/*     */   }
/*     */   @RequestMapping({"bo/deleteBoDeviceType.do"})
/*     */   @ResponseBody
/*     */   public Object deleteBoDeviceType(@RequestParam("uid") String uid) {
/* 102 */     String ids = uid;
/* 103 */     this.boDeviceTypeService.deleteByKeys(ids);
/* 104 */     return new ResultJson("删除成功！", "200", "BO0007", "");
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoDeviceTypeController
 * JD-Core Version:    0.6.2
 */