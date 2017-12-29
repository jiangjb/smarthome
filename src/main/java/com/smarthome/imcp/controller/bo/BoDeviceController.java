/*     */ package com.smarthome.imcp.controller.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*     */ import com.smarthome.imcp.controller.ResultJson;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.propertyeditors.CustomDateEditor;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.ServletRequestDataBinder;
/*     */ import org.springframework.web.bind.annotation.InitBinder;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ 
/*     */ @Controller
/*     */ public class BoDeviceController
/*     */ {
/*  37 */   private final String MENU_CODE = "BO0006";
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*  43 */   @RequestMapping({"bo/dispatchBoDeviceMapPage.do"})
/*     */   public ModelAndView dispatchBoDeviceMapPage() { List list = this.boDeviceService.getAllList();
/*  44 */     return new ModelAndView("bo/device/device_map", "boDeviceList", list); }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceLookup.do"})
/*     */   public ModelAndView dispatchBoDeviceLookup()
/*     */   {
/*  49 */     List list = this.boDeviceService.getAllList();
/*  50 */     return new ModelAndView("bo/device/device_lookup", "boDeviceList", list);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceOnlineListPage.do"})
/*     */   public String dispatchBoDeviceOnlineListPage()
/*     */   {
/*  59 */     return "bo/device/deviceOnline_list";
/*     */   }
/*     */   @RequestMapping({"bo/searchBoDeviceOnlineListAjax.do"})
/*     */   @ResponseBody
/*     */   public Object searchBoDeviceOnlineListAjax(Page page, SearchCriteriaDevice searchCriteria) {
///*  65 */     List boDeviceList = this.boDeviceService.getList(searchCriteria, page);
			  List<BoDevice> boDeviceList = this.boDeviceService.getList(searchCriteria, page);
/*  66 */     FlexigridJson flexJson = new FlexigridJson();
/*  67 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/*  68 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/*  69 */     List rows = new ArrayList();
/*  70 */     for (BoDevice model : boDeviceList) {
/*  71 */       FlexigridJsonRow row = new FlexigridJsonRow();
/*  72 */       row.setId(model.getDeviceId());
/*  73 */       row.setCell(model);
/*  74 */       rows.add(row);
/*     */     }
/*  76 */     flexJson.setRows(rows);
/*  77 */     return flexJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceListPage.do"})
/*     */   public String dispatchBoDeviceListPage() {
/*  82 */     return "bo/device/device_list";
/*     */   }
/*     */   @RequestMapping({"bo/searchBoDeviceListAjax.do"})
/*     */   @ResponseBody
/*     */   public Object searchBoDeviceListAjax(Page page, SearchCriteriaDevice searchCriteria) {
///*  88 */     List boDeviceList = this.boDeviceService.getList(searchCriteria, page);
			  List<BoDevice> boDeviceList = this.boDeviceService.getList(searchCriteria, page);
/*  89 */     FlexigridJson flexJson = new FlexigridJson();
/*  90 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/*  91 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/*  92 */     List rows = new ArrayList();
/*  93 */     for (BoDevice model : boDeviceList) {
/*  94 */       FlexigridJsonRow row = new FlexigridJsonRow();
/*  95 */       row.setId(model.getDeviceId());
/*  96 */       row.setCell(model);
/*  97 */       rows.add(row);
/*     */     }
/*  99 */     flexJson.setRows(rows);
/* 100 */     return flexJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceAddPage.do"})
/*     */   public String dispatchBoDeviceAddPage()
/*     */   {
/* 107 */     return "bo/device/device_add";
/*     */   }
/*     */   @RequestMapping({"bo/saveBoDevice.do"})
/*     */   @ResponseBody
/*     */   public Object saveBoDevice(BoDevice boDevice) {
/* 113 */     this.boDeviceService.save(boDevice);
/* 114 */     return new ResultJson("保存成功！", "200", "BO0006");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceEditPage.do"})
/*     */   public ModelAndView dispatchBoDeviceEditPage(@RequestParam("uid") String uid) {
/* 119 */     Integer id = Integer.valueOf(uid);
/* 120 */     BoDevice boDevice = (BoDevice)this.boDeviceService.findByKey(id);
/*     */ 
/* 122 */     return new ModelAndView("bo/device/device_edit", "boDevice", boDevice);
/*     */   }
/*     */   @RequestMapping({"bo/editBoDevice.do"})
/*     */   @ResponseBody
/*     */   public Object editBoDevice(BoDevice boDevice) {
/* 128 */     this.boDeviceService.update(boDevice);
/* 129 */     return new ResultJson("修改成功！", "200", "BO0006");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoDeviceViewPage.do"})
/*     */   public ModelAndView dispatchBoDeviceViewPage(@RequestParam("uid") String uid) {
/* 134 */     Integer id = Integer.valueOf(uid);
/* 135 */     BoDevice boDevice = (BoDevice)this.boDeviceService.findByKey(id);
/* 136 */     return new ModelAndView("bo/device/device_view", "boDevice", boDevice);
/*     */   }
/*     */   @RequestMapping({"bo/deleteBoDevice.do"})
/*     */   @ResponseBody
/*     */   public Object deleteBoDevice(@RequestParam("uid") String uid) {
/* 142 */     String ids = uid;
/* 143 */     this.boDeviceService.deleteByKeys(ids);
/* 144 */     return new ResultJson("删除成功！", "200", "BO0006");
/*     */   }
/*     */ 
/*     */   @InitBinder
/*     */   protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
/*     */   {
/* 150 */     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 151 */     CustomDateEditor editor = new CustomDateEditor(df, true);
/* 152 */     binder.registerCustomEditor(Date.class, editor);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoDeviceController
 * JD-Core Version:    0.6.2
 */