/*    */ package com.smarthome.imcp.service.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.cache.SysDictCodeCacheManager;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaDictCode;
/*    */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*    */ import com.smarthome.imcp.dao.system.SysDictCodeDaoIface;
/*    */ import com.smarthome.imcp.exception.BusinessException;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.BasicServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("sysDictCodeService")
/*    */ public class SysDictCodeServiceImpl extends AbstractBasicService<SysDictCode, Serializable>
/*    */   implements BasicServiceIface<SysDictCode, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysDictCodeDaoIface<SysDictCode, Serializable> sysDictCodeDao;
/*    */ 
/*    */   public List<SysDictCode> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 30 */     SearchCriteriaDictCode searchCriteriaDictCode = (SearchCriteriaDictCode)searchCriteria;
/* 31 */     if (chkCriteriaValid(searchCriteriaDictCode)) {
/* 32 */       return this.sysDictCodeDao.getList(searchCriteriaDictCode, page);
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(SysDictCode sysDictCode)
/*    */   {
/* 39 */     if (this.sysDictCodeDao.isExistsByDictCode(sysDictCode.getDictCode())) {
/* 40 */       throw new BusinessException("此数据字典项代码已存在，不能重复添加！");
/*    */     }
/*    */ 
/* 43 */     if (this.sysDictCodeDao
/* 43 */       .isExistsByDictCodeName(sysDictCode.getDictCodeName())) {
/* 44 */       throw new BusinessException("此数据字典项名称已存在，不能重复添加！");
/*    */     }
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   public SysDictCode save(SysDictCode sysDictCode)
/*    */   {
/* 51 */     if (chkSaveValid(sysDictCode)) {
/* 52 */       this.sysDictCodeDao.save(sysDictCode);
/* 53 */       SysDictCodeCacheManager.getInstance().add(sysDictCode);
/*    */     }
/* 55 */     return sysDictCode;
/*    */   }
/*    */ 
/*    */   public SysDictCode findByKey(Serializable id)
/*    */   {
/* 60 */     return (SysDictCode)this.sysDictCodeDao.findById(id);
/*    */   }
/*    */ 
/*    */   public SysDictCode update(SysDictCode sysDictCode)
/*    */   {
/* 65 */     if (chkUpdateValid(sysDictCode)) {
/* 66 */       this.sysDictCodeDao.update(sysDictCode);
/* 67 */       SysDictCodeCacheManager.getInstance().update(sysDictCode);
/*    */     }
/* 69 */     return sysDictCode;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String dictCode)
/*    */   {
/* 74 */     if (chkDeleteValid(dictCode)) {
/* 75 */       SysDictCodeCacheManager.getInstance().remove(dictCode);
/* 76 */       this.sysDictCodeDao.deleteByKey(dictCode);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String dictCodes)
/*    */   {
/* 82 */     StringTokenizer st = new StringTokenizer(dictCodes, ",");
/* 83 */     while (st.hasMoreElements()) {
/* 84 */       String dictCode = st.nextToken();
/* 85 */       deleteByKey(dictCode);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysDictCodeServiceImpl
 * JD-Core Version:    0.6.2
 */