/*     */ package com.smarthome.imcp.controller.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*     */ import com.smarthome.imcp.controller.RequestJson;
/*     */ import com.smarthome.imcp.controller.ResultJson;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUserDevice;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoUserServiceIface;
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
/*     */ public class BoUserDeviceController
/*     */ {
/*  38 */   private final String MENU_CODE = "BO0002";
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDeviceServiceIface<BoUserDevice, Serializable> boUserDeviceService;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserServiceIface<BoUser, Serializable> boUserService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @RequestMapping({"public/registerDevice.do"})
/*     */   @ResponseBody
/*     */   public Object registerDevice(@RequestParam("userCode") String userCode, @RequestParam("deviceCode") String deviceCode)
/*     */   {
/*  57 */     RequestJson requestJson = new RequestJson();
/*     */ 
/*  59 */     BoUser user = this.boUserService.findByUserCode(userCode);
/*  60 */     if (user == null) {
/*  61 */       requestJson.setMessage("用户  " + userCode + " 未注册！");
/*  62 */       requestJson.setSuccess(false);
/*  63 */       return requestJson;
/*     */     }
/*     */ 
/*  66 */     BoDevice device = this.boDeviceService.findByCode(deviceCode);
/*     */ 
/*  68 */     if (device == null) {
/*  69 */       requestJson.setMessage("设备" + deviceCode + " 不成在！");
/*  70 */       requestJson.setSuccess(false);
/*  71 */       return requestJson;
/*     */     }
/*     */ 
/*  74 */     BoUserDevice boUserDevice = new BoUserDevice();
/*  75 */     boUserDevice.setBoDevice(device);
/*  76 */     boUserDevice.setBoUser(user);
/*     */ 
/*  78 */     this.boUserDeviceService.save(boUserDevice);
/*  79 */     return requestJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserDeviceListPage.do"})
/*     */   public String dispatchBoUserDeviceListPage() {
/*  84 */     return "bo/userDevice/userDevice_list";
/*     */   }
/*     */   @RequestMapping({"bo/searchBoUserDeviceListAjax.do"})
/*     */   @ResponseBody
/*     */   public Object searchBoUserDeviceListAjax(Page page, SearchCriteriaUserDevice searchCriteria) {
/*  90 */     List<BoUserDevice> boUserDeviceList = this.boUserDeviceService.getList(searchCriteria, page);
/*  91 */     FlexigridJson flexJson = new FlexigridJson();
/*  92 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/*  93 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/*  94 */     List rows = new ArrayList();
/*  95 */     for (BoUserDevice model : boUserDeviceList) {
/*  96 */       FlexigridJsonRow row = new FlexigridJsonRow();
/*  97 */       row.setId(model.getUserDeviceId());
/*  98 */       row.setCell(model);
/*  99 */       rows.add(row);
/*     */     }
/* 101 */     flexJson.setRows(rows);
/* 102 */     return flexJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserDeviceAddPage.do"})
/*     */   public String dispatchBoUserDeviceAddPage() {
/* 107 */     return "bo/userDevice/userDevice_add";
/*     */   }
/*     */   @RequestMapping({"bo/saveBoUserDevice.do"})
/*     */   @ResponseBody
/*     */   public Object saveBoUserDevice(BoUserDevice boUserDevice) {
/* 113 */     this.boUserDeviceService.save(boUserDevice);
/* 114 */     return new ResultJson("保存成功！", "200", "BO0002");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserDeviceEditPage.do"})
/*     */   public ModelAndView dispatchBoUserDeviceEditPage(@RequestParam("uid") String uid) {
/* 119 */     Integer id = Integer.valueOf(uid);
/* 120 */     BoUserDevice boUserDevice = (BoUserDevice)this.boUserDeviceService.findByKey(id);
/*     */ 
/* 122 */     return new ModelAndView("bo/userDevice/userDevice_edit", "boUserDevice", boUserDevice);
/*     */   }
/*     */   @RequestMapping({"bo/editBoUserDevice.do"})
/*     */   @ResponseBody
/*     */   public Object editBoUserDevice(BoUserDevice boUserDevice) {
/* 128 */     this.boUserDeviceService.update(boUserDevice);
/* 129 */     return new ResultJson("修改成功！", "200", "BO0002");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoUserDeviceViewPage.do"})
/*     */   public ModelAndView dispatchBoUserDeviceViewPage(@RequestParam("uid") String uid) {
/* 134 */     Integer id = Integer.valueOf(uid);
/* 135 */     BoUserDevice boUserDevice = (BoUserDevice)this.boUserDeviceService.findByKey(id);
/* 136 */     return new ModelAndView("bo/userDevice/userDevice_view", "boUserDevice", boUserDevice);
/*     */   }
/*     */   @RequestMapping({"bo/deleteBoUserDevice.do"})
/*     */   @ResponseBody
/*     */   public Object deleteBoUserDevice(@RequestParam("uid") String uid) {
/* 142 */     String ids = uid;
/* 143 */     this.boUserDeviceService.deleteByKeys(ids);
/* 144 */     return new ResultJson("删除成功！", "200", "BO0002");
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
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoUserDeviceController
 * JD-Core Version:    0.6.2
 */