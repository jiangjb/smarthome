/*     */ package com.smarthome.imcp.action.xing;
/*     */ 
/*     */ import com.smarthome.imcp.action.AbstractAction;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.controller.RequestJson;
/*     */ import com.smarthome.imcp.dao.model.bo.BoErrorreport;
/*     */ import com.smarthome.imcp.service.bo.BoErrorreportServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang3.StringUtils;
/*     */ import org.apache.struts2.convention.annotation.Action;
/*     */ import org.apache.struts2.convention.annotation.Namespace;
/*     */ import org.apache.struts2.convention.annotation.ParentPackage;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ 
/*     */ @ParentPackage("auth-check-package")
/*     */ @Namespace("/xingErrorr")
/*     */ public class ErrorreportAction extends AbstractAction
/*     */ {
/*     */   private int id;
/* 120 */   private String appName = "";
/* 121 */   private String content = "";
/*     */ 
/* 123 */   private Integer pageNum = Integer.valueOf(1); private Integer pageSize = Integer.valueOf(5);
/*     */   private String orderField;
/*     */   private String orderDirection;
/*     */   public List<BoErrorreport> allList;
/*     */ 
/*     */   @Autowired
/*     */   private BoErrorreportServiceIface<BoErrorreport, Serializable> bes;
/* 205 */   private RequestJson requestJson = new RequestJson();
/*     */ 
/*     */   @Action(value="delete", results={@org.apache.struts2.convention.annotation.Result(name="success", location="/xingErrorr/getList.action", type="redirect")})
/*     */   public String delete()
/*     */   {
/*  33 */     BoErrorreport boErrorreport = new BoErrorreport();
/*  34 */     boErrorreport.setId(Integer.valueOf(this.id));
/*  35 */     this.bes.delete(boErrorreport);
/*  36 */     Page page = new Page();
/*  37 */     page.setPageSize(this.pageSize.intValue());
/*  38 */     page.setPageNum(this.pageNum.intValue());
/*     */ 
/*  40 */     if (StringUtils.isNotEmpty(this.orderField)) {
/*  41 */       page.setOrderField(this.orderField);
/*     */     }
/*     */ 
/*  44 */     if (StringUtils.isNotEmpty(this.orderDirection)) {
/*  45 */       page.setOrderDirection(this.orderDirection);
/*     */     }
/*  47 */     this.allList = this.bes.getAllList(page);
/*  48 */     return "success";
/*     */   }
/*     */ 
/*     */   @Action(value="getList", results={@org.apache.struts2.convention.annotation.Result(name="success", location="/jsp/bo/user/error.jsp")})
/*     */   public String getList()
/*     */   {
/*  57 */     Page page = new Page();
/*  58 */     page.setPageSize(this.pageSize.intValue());
/*  59 */     page.setPageNum(this.pageNum.intValue());
/*     */ 
/*  61 */     if (StringUtils.isNotEmpty(this.orderField)) {
/*  62 */       page.setOrderField(this.orderField);
/*     */     }
/*     */ 
/*  65 */     if (StringUtils.isNotEmpty(this.orderDirection)) {
/*  66 */       page.setOrderDirection(this.orderDirection);
/*     */     }
/*  68 */     this.allList = this.bes.getAllList(page);
/*  69 */     return "success";
/*     */   }
/*     */ 
/*     */   @Action(value="report", results={@org.apache.struts2.convention.annotation.Result(type="json", params={"root", "requestJson"})})
/*     */   public String errorReport()
/*     */   {
/*  84 */     this.requestJson = new RequestJson();
/*     */ 
/*  86 */     if (StringUtils.isEmpty(this.appName)) {
/*  87 */       this.requestJson.setSuccess(false);
/*  88 */       this.requestJson.setMessage("appName 参数不能为空!");
/*  89 */       return "success";
/*  90 */     }if (StringUtils.isEmpty(this.content)) {
/*  91 */       this.requestJson.setSuccess(false);
/*  92 */       this.requestJson.setMessage("content 参数不能为空!");
/*  93 */       return "success";
/*     */     }
/*  95 */     Map map = new HashMap();
/*  96 */     BoErrorreport boErrorreport = new BoErrorreport();
/*  97 */     boErrorreport.setAppName(this.appName);
/*  98 */     boErrorreport.setContent(this.content);
/*  99 */     boErrorreport.setReportdate(new Date());
/* 100 */     map.put("appName", boErrorreport.getAppName());
/* 101 */     map.put("content", boErrorreport.getContent());
/*     */ 
/* 103 */     this.requestJson.setData(map);
/* 104 */     this.bes.save(boErrorreport);
/*     */ 
/* 108 */     return "success";
/*     */   }
/*     */ 
/*     */   public int getId()
/*     */   {
/* 113 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(int id)
/*     */   {
/* 118 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public String getOrderField()
/*     */   {
/* 128 */     return this.orderField;
/*     */   }
/*     */ 
/*     */   public void setOrderField(String orderField)
/*     */   {
/* 133 */     this.orderField = orderField;
/*     */   }
/*     */ 
/*     */   public String getOrderDirection()
/*     */   {
/* 138 */     return this.orderDirection;
/*     */   }
/*     */ 
/*     */   public void setOrderDirection(String orderDirection)
/*     */   {
/* 143 */     this.orderDirection = orderDirection;
/*     */   }
/*     */ 
/*     */   public void setPageNum(Integer pageNum)
/*     */   {
/* 148 */     this.pageNum = pageNum;
/*     */   }
/*     */ 
/*     */   public void setPageSize(Integer pageSize)
/*     */   {
/* 153 */     this.pageSize = pageSize;
/*     */   }
/*     */ 
/*     */   public int getPageNum()
/*     */   {
/* 158 */     return this.pageNum.intValue();
/*     */   }
/*     */ 
/*     */   public void setPageNum(int pageNum) {
/* 162 */     this.pageNum = Integer.valueOf(pageNum);
/*     */   }
/*     */ 
/*     */   public int getPageSize() {
/* 166 */     return this.pageSize.intValue();
/*     */   }
/*     */ 
/*     */   public void setPageSize(int pageSize) {
/* 170 */     this.pageSize = Integer.valueOf(pageSize);
/*     */   }
/*     */ 
/*     */   public String getAppName() {
/* 174 */     return this.appName;
/*     */   }
/*     */ 
/*     */   public void setAppName(String appName) {
/* 178 */     this.appName = appName;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 183 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content) {
/* 187 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public List<BoErrorreport> getAllList()
/*     */   {
/* 193 */     return this.allList;
/*     */   }
/*     */ 
/*     */   public void setAllList(List<BoErrorreport> allList) {
/* 197 */     this.allList = allList;
/*     */   }
/*     */ 
/*     */   public RequestJson getRequestJson()
/*     */   {
/* 208 */     return this.requestJson;
/*     */   }
/*     */ 
/*     */   public void setRequestJson(RequestJson requestJson) {
/* 212 */     this.requestJson = requestJson;
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.action.xing.ErrorreportAction
 * JD-Core Version:    0.6.2
 */