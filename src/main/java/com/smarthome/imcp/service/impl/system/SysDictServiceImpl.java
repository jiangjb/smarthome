/*     */ package com.smarthome.imcp.service.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.cache.SysDictCacheManager;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaDict;
/*     */ import com.smarthome.imcp.dao.model.system.SysDict;
/*     */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*     */ import com.smarthome.imcp.dao.system.SysDictDaoIface;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.BasicServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("sysDictService")
/*     */ public class SysDictServiceImpl extends AbstractBasicService<SysDict, Serializable>
/*     */   implements BasicServiceIface<SysDict, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysDictDaoIface<SysDict, Serializable> sysDictDao;
/*     */ 
/*     */   public boolean chkCriteriaValid(SearchCriteria searchCriteria)
/*     */   {
/*  31 */     SearchCriteriaDict searchCriteriaDict = (SearchCriteriaDict)searchCriteria;
/*  32 */     if (StringUtils.isEmpty(searchCriteriaDict.getDictCode())) {
/*  33 */       throw new BusinessException("请选择数据字典项代码！");
/*     */     }
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */   public List<SysDict> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  40 */     SearchCriteriaDict searchCriteriaDict = (SearchCriteriaDict)searchCriteria;
/*  41 */     if (chkCriteriaValid(searchCriteriaDict)) {
/*  42 */       return this.sysDictDao.getList(searchCriteriaDict, page);
/*     */     }
/*  44 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(SysDict sysDict)
/*     */   {
/*  50 */     if (this.sysDictDao.isExistsByDictCodeName(sysDict.getSysDictCode()
/*  50 */       .getDictCode(), sysDict.getDictName())) {
/*  51 */       throw new BusinessException("此数据字典名称已存在，不能重复添加！");
/*     */     }
/*  53 */     return true;
/*     */   }
/*     */ 
/*     */   public SysDict save(SysDict sysDict)
/*     */   {
/*  58 */     if (chkSaveValid(sysDict)) {
/*  59 */       this.sysDictDao.save(sysDict);
/*  60 */       SysDictCacheManager.getInstance().add(sysDict);
/*     */     }
/*  62 */     return sysDict;
/*     */   }
/*     */ 
/*     */   public SysDict findByKey(Serializable id)
/*     */   {
/*  67 */     return (SysDict)this.sysDictDao.findById(id);
/*     */   }
/*     */ 
/*     */   public SysDict update(SysDict sysDict)
/*     */   {
/*  72 */     if (chkUpdateValid(sysDict)) {
/*  73 */       this.sysDictDao.update(sysDict);
/*  74 */       SysDictCacheManager.getInstance().update(sysDict);
/*     */     }
/*  76 */     return sysDict;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String dictId)
/*     */   {
/*  81 */     SysDict sysDict = findByKey(Integer.valueOf(Integer.parseInt(dictId)));
/*     */ 
/*  83 */     if (this.sysDictDao.isLastOneByDictCode(sysDict.getSysDictCode()
/*  83 */       .getDictCode())) {
/*  84 */       throw new BusinessException("数据字典项：" + 
/*  85 */         sysDict.getSysDictCode().getDictCodeName() + "不允许全部删除！");
/*     */     }
/*     */ 
/*  88 */     SysDictCacheManager.getInstance().remove(sysDict);
/*  89 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String dictId)
/*     */   {
/*  94 */     if (chkDeleteValid(dictId))
/*     */     {
/*  96 */       this.sysDictDao.deleteLogicByKey(Integer.valueOf(Integer.parseInt(dictId)));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String dictIds)
/*     */   {
/* 102 */     StringTokenizer st = new StringTokenizer(dictIds, ",");
/* 103 */     while (st.hasMoreElements()) {
/* 104 */       String dictId = st.nextToken();
/* 105 */       deleteByKey(dictId);
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysDictServiceImpl
 * JD-Core Version:    0.6.2
 */