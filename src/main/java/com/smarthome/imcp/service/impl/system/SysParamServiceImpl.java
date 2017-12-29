/*    */ package com.smarthome.imcp.service.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.cache.SysParamCacheManager;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaParam;
/*    */ import com.smarthome.imcp.dao.model.system.SysParam;
/*    */ import com.smarthome.imcp.dao.model.system.SysParamCode;
/*    */ import com.smarthome.imcp.dao.system.SysParamDaoIface;
/*    */ import com.smarthome.imcp.exception.BusinessException;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.BasicServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("sysParamService")
/*    */ public class SysParamServiceImpl extends AbstractBasicService<SysParam, Serializable>
/*    */   implements BasicServiceIface<SysParam, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysParamDaoIface<SysParam, Serializable> sysParamDao;
/*    */ 
/*    */   public boolean chkCriteriaValid(SearchCriteria searchCriteria)
/*    */   {
/* 31 */     SearchCriteriaParam searchCriteriaParam = (SearchCriteriaParam)searchCriteria;
/* 32 */     if (StringUtils.isEmpty(searchCriteriaParam.getParamCode())) {
/* 33 */       throw new BusinessException("请选择系统参数代码！");
/*    */     }
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   public List<SysParam> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 40 */     SearchCriteriaParam searchCriteriaParam = (SearchCriteriaParam)searchCriteria;
/* 41 */     if (chkCriteriaValid(searchCriteriaParam)) {
/* 42 */       return this.sysParamDao.getList(searchCriteriaParam, page);
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(SysParam sysParam)
/*    */   {
/* 50 */     if (this.sysParamDao.isExistsByParamCodeName(sysParam.getSysParamCode()
/* 50 */       .getParamCode(), sysParam.getParamName())) {
/* 51 */       throw new BusinessException("此系统参数名称已存在，不能重复添加！");
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */ 
/*    */   public SysParam save(SysParam sysParam)
/*    */   {
/* 58 */     if (chkSaveValid(sysParam)) {
/* 59 */       this.sysParamDao.save(sysParam);
/* 60 */       SysParamCacheManager.getInstance().add(sysParam);
/*    */     }
/* 62 */     return sysParam;
/*    */   }
/*    */ 
/*    */   public SysParam findByKey(Serializable id)
/*    */   {
/* 67 */     return (SysParam)this.sysParamDao.findById(id);
/*    */   }
/*    */ 
/*    */   public SysParam update(SysParam sysParam)
/*    */   {
/* 72 */     if (chkUpdateValid(sysParam)) {
/* 73 */       this.sysParamDao.update(sysParam);
/* 74 */       SysParamCacheManager.getInstance().update(sysParam);
/*    */     }
/* 76 */     return sysParam;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String paramId)
/*    */   {
/* 81 */     if (chkDeleteValid(paramId)) {
/* 82 */       SysParam sysParam = findByKey(Integer.valueOf(Integer.parseInt(paramId)));
/* 83 */       SysParamCacheManager.getInstance().remove(sysParam);
/* 84 */       this.sysParamDao.deleteByKey(Integer.valueOf(Integer.parseInt(paramId)));
/*    */     }
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String paramIds)
/*    */   {
/* 90 */     StringTokenizer st = new StringTokenizer(paramIds, ",");
/* 91 */     while (st.hasMoreElements()) {
/* 92 */       String paramId = st.nextToken();
/* 93 */       deleteByKey(paramId);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysParamServiceImpl
 * JD-Core Version:    0.6.2
 */