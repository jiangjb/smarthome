/*     */ package com.smarthome.imcp.controller.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJson;
/*     */ import com.smarthome.imcp.common.flexigrid.FlexigridJsonRow;
/*     */ import com.smarthome.imcp.controller.RequestJson;
/*     */ import com.smarthome.imcp.controller.ResultJson;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaRepair;
/*     */ import com.smarthome.imcp.dao.model.bo.BoDevice;
/*     */ import com.smarthome.imcp.dao.model.bo.BoRepair;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
/*     */ import com.smarthome.imcp.service.bo.BoDeviceServiceIface;
/*     */ import com.smarthome.imcp.service.bo.BoRepairServiceIface;
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
/*     */ public class BoRepairController
/*     */ {
/*  39 */   private final String MENU_CODE = "BO0013";
/*     */ 
/*     */   @Autowired
/*     */   private BoRepairServiceIface<BoRepair, Serializable> boRepairService;
/*     */ 
/*     */   @Autowired
/*     */   private BoUserServiceIface<BoUser, Serializable> boUserService;
/*     */ 
/*     */   @Autowired
/*     */   private BoDeviceServiceIface<BoDevice, Serializable> boDeviceService;
/*     */ 
/*     */   @RequestMapping({"public/addRepair.do"})
/*     */   @ResponseBody
/*     */   public Object registerDevice(@RequestParam("userCode") String userCode, @RequestParam("deviceCode") String deviceCode)
/*     */   {
/*  58 */     RequestJson requestJson = new RequestJson();
/*     */ 
/*  60 */     BoUser user = this.boUserService.findByUserCode(userCode);
/*  61 */     if (user == null) {
/*  62 */       requestJson.setMessage("用户  " + userCode + " 未注册！");
/*  63 */       requestJson.setSuccess(false);
/*  64 */       return requestJson;
/*     */     }
/*  66 */     BoDevice device = this.boDeviceService.findByCode(deviceCode);
/*  67 */     if (device == null) {
/*  68 */       requestJson.setMessage("设备  " + deviceCode + " 不成在！");
/*  69 */       requestJson.setSuccess(false);
/*  70 */       return requestJson;
/*     */     }
/*     */ 
/*  73 */     BoRepair repair = new BoRepair();
/*  74 */     repair.setBoDevice(device);
/*  75 */     repair.setBoFactory(device.getBoFactory());
/*  76 */     repair.setBoUser(user);
/*  77 */     repair.setRepairDate(new Date());
/*     */ 
/*  79 */     this.boRepairService.save(repair);
/*     */ 
/*  81 */     return requestJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoRepairListPage.do"})
/*     */   public String dispatchBoRepairListPage() {
/*  86 */     return "bo/repair/repair_list";
/*     */   }
/*     */   @RequestMapping({"bo/searchBoRepairListAjax.do"})
/*     */   @ResponseBody
/*     */   public Object searchBoRepairListAjax(Page page, SearchCriteriaRepair searchCriteria) {
/*  92 */     List<BoRepair> boRepairList = this.boRepairService.getList(searchCriteria, page);
/*  93 */     FlexigridJson flexJson = new FlexigridJson();
/*  94 */     flexJson.setPage(Integer.valueOf(page.getPageNum()));
/*  95 */     flexJson.setTotal(Integer.valueOf(page.getTotalCount()));
/*  96 */     List rows = new ArrayList();
/*  97 */     for (BoRepair model : boRepairList) {
/*  98 */       FlexigridJsonRow row = new FlexigridJsonRow();
/*  99 */       row.setId(model.getRepairId());
/* 100 */       row.setCell(model);
/* 101 */       rows.add(row);
/*     */     }
/* 103 */     flexJson.setRows(rows);
/* 104 */     return flexJson;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoRepairAddPage.do"})
/*     */   public String dispatchBoRepairAddPage() {
/* 109 */     return "bo/repair/repair_add";
/*     */   }
/*     */   @RequestMapping({"bo/saveBoRepair.do"})
/*     */   @ResponseBody
/*     */   public Object saveBoRepair(BoRepair boRepair) {
/* 115 */     this.boRepairService.save(boRepair);
/* 116 */     return new ResultJson("保存成功！", "200", "BO0013");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoRepairEditPage.do"})
/*     */   public ModelAndView dispatchBoRepairEditPage(@RequestParam("uid") String uid) {
/* 121 */     Integer id = Integer.valueOf(uid);
/* 122 */     BoRepair boRepair = (BoRepair)this.boRepairService.findByKey(id);
/*     */ 
/* 124 */     return new ModelAndView("bo/repair/repair_edit", "boRepair", boRepair);
/*     */   }
/*     */   @RequestMapping({"bo/editBoRepair.do"})
/*     */   @ResponseBody
/*     */   public Object editBoRepair(BoRepair boRepair) {
/* 130 */     this.boRepairService.update(boRepair);
/* 131 */     return new ResultJson("修改成功！", "200", "BO0013");
/*     */   }
/*     */ 
/*     */   @RequestMapping({"bo/dispatchBoRepairViewPage.do"})
/*     */   public ModelAndView dispatchBoRepairViewPage(@RequestParam("uid") String uid) {
/* 136 */     Integer id = Integer.valueOf(uid);
/* 137 */     BoRepair boRepair = (BoRepair)this.boRepairService.findByKey(id);
/* 138 */     return new ModelAndView("bo/repair/repair_view", "boRepair", boRepair);
/*     */   }
/*     */   @RequestMapping({"bo/deleteBoRepair.do"})
/*     */   @ResponseBody
/*     */   public Object deleteBoRepair(@RequestParam("uid") String uid) {
/* 144 */     String ids = uid;
/* 145 */     this.boRepairService.deleteByKeys(ids);
/* 146 */     return new ResultJson("删除成功！", "200", "BO0013");
/*     */   }
/*     */ 
/*     */   @InitBinder
/*     */   protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception
/*     */   {
/* 152 */     DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
/* 153 */     CustomDateEditor editor = new CustomDateEditor(df, true);
/* 154 */     binder.registerCustomEditor(Date.class, editor);
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.controller.bo.BoRepairController
 * JD-Core Version:    0.6.2
 */