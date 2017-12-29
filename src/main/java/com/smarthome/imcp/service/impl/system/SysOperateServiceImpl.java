/*    */ package com.smarthome.imcp.service.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaOperate;
/*    */ import com.smarthome.imcp.dao.model.system.SysOperate;
/*    */ import com.smarthome.imcp.dao.system.SysOperateDaoIface;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.BasicServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("sysOperateService")
/*    */ public class SysOperateServiceImpl extends AbstractBasicService<SysOperate, Serializable>
/*    */   implements BasicServiceIface<SysOperate, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysOperateDaoIface<SysOperate, Serializable> sysOperateDao;
/*    */ 
/*    */   public boolean chkCriteriaValid(SearchCriteria searchCriteria)
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */ 
/*    */   public List<SysOperate> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 35 */     SearchCriteriaOperate searchCriteriaOperate = (SearchCriteriaOperate)searchCriteria;
/* 36 */     if (chkCriteriaValid(searchCriteriaOperate)) {
/* 37 */       return this.sysOperateDao.getList(searchCriteriaOperate, page);
/*    */     }
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(SysOperate sysParam)
/*    */   {
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */   public SysOperate save(SysOperate sysParam)
/*    */   {
/* 49 */     if (!chkSaveValid(sysParam)) {
/* 50 */       return sysParam;
/*    */     }
/* 52 */     this.sysOperateDao.save(sysParam);
/* 53 */     return sysParam;
/*    */   }
/*    */ 
/*    */   public SysOperate findByKey(Serializable id)
/*    */   {
/* 58 */     return (SysOperate)this.sysOperateDao.findById(id);
/*    */   }
/*    */ 
/*    */   public SysOperate update(SysOperate sysParam)
/*    */   {
/* 63 */     if (chkUpdateValid(sysParam)) {
/* 64 */       this.sysOperateDao.update(sysParam);
/*    */     }
/* 66 */     return sysParam;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String paramId)
/*    */   {
/* 71 */     if (chkDeleteValid(paramId))
/* 72 */       this.sysOperateDao.deleteByKey(Integer.valueOf(Integer.parseInt(paramId)));
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String paramIds)
/*    */   {
/* 78 */     StringTokenizer st = new StringTokenizer(paramIds, ",");
/* 79 */     while (st.hasMoreElements()) {
/* 80 */       String paramId = st.nextToken();
/* 81 */       deleteByKey(paramId);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysOperateServiceImpl
 * JD-Core Version:    0.6.2
 */