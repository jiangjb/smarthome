/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaDictCode;
/*    */ import com.smarthome.imcp.dao.model.system.SysDictCode;
/*    */ import com.smarthome.imcp.dao.system.SysDictCodeDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysDictCodeDao")
/*    */ public class SysDictCodeDaoImpl extends CommonsDaoImpl<SysDictCode, Serializable>
/*    */   implements SysDictCodeDaoIface<SysDictCode, Serializable>
/*    */ {
/*    */   public SysDictCodeDaoImpl()
/*    */   {
/* 26 */     super(SysDictCode.class);
/*    */   }
/*    */ 
/*    */   public List<SysDictCode> getList(SearchCriteriaDictCode searchCriteriaDictCode, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = 
/* 33 */       DetachedCriteria.forClass(SysDictCode.class);
/* 34 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaDictCode))
/*    */     {
/* 36 */       if (StringUtils.isNotEmpty(searchCriteriaDictCode.getDictCodeName())) {
/* 37 */         criteria.add(Restrictions.like("dictCodeName", 
/* 38 */           searchCriteriaDictCode.getDictCodeName(), 
/* 39 */           MatchMode.ANYWHERE));
/*    */       }
/* 41 */       if (StringUtils.isNotEmpty(searchCriteriaDictCode.getDictCode())) {
/* 42 */         criteria.add(Restrictions.like("dictCode", 
/* 43 */           searchCriteriaDictCode.getDictCode(), 
/* 44 */           MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 47 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 48 */       if (page.isAsc())
/* 49 */         criteria.addOrder(Order.asc("dictCode"));
/*    */       else {
/* 51 */         criteria.addOrder(Order.desc("dictCode"));
/*    */       }
/*    */     }
/* 54 */     else if (page.isAsc())
/* 55 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 57 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 60 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public boolean isExistsByDictCode(String dictCode)
/*    */   {
/* 65 */     StringBuffer hql = new StringBuffer();
/* 66 */     hql.append("select 1 from SysDictCode sysDictCode");
/* 67 */     hql.append(" where sysDictCode.dictCode = ?");
/* 68 */     List list = findByHQL(hql.toString(), dictCode);
/* 69 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean isExistsByDictCodeName(String dictCodeName)
/*    */   {
/* 77 */     StringBuffer hql = new StringBuffer();
/* 78 */     hql.append("select 1 from SysDictCode sysDictCode");
/* 79 */     hql.append(" where sysDictCode.dictCodeName = ?");
/* 80 */     List list = findByHQL(hql.toString(), dictCodeName);
/* 81 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 82 */       return false;
/*    */     }
/* 84 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysDictCodeDaoImpl
 * JD-Core Version:    0.6.2
 */