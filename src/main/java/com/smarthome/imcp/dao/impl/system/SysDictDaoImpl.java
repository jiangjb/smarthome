/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaDict;
/*    */ import com.smarthome.imcp.dao.model.system.SysDict;
/*    */ import com.smarthome.imcp.dao.system.SysDictDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysDictDao")
/*    */ public class SysDictDaoImpl extends CommonsDaoImpl<SysDict, Serializable>
/*    */   implements SysDictDaoIface<SysDict, Serializable>
/*    */ {
/*    */   public SysDictDaoImpl()
/*    */   {
/* 26 */     super(SysDict.class);
/*    */   }
/*    */ 
/*    */   public List<SysDict> getList(SearchCriteriaDict searchCriteriaDict, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysDict.class);
/* 33 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaDict)) {
/* 34 */       criteria.add(Restrictions.eq("sysDictCode.dictCode", 
/* 35 */         searchCriteriaDict.getDictCode()));
/* 36 */       if (StringUtils.isNotEmpty(searchCriteriaDict.getDictName())) {
/* 37 */         criteria.add(Restrictions.like("dictName", 
/* 38 */           searchCriteriaDict.getDictName(), MatchMode.ANYWHERE));
/*    */       }
/* 40 */       if (StringUtils.isNotEmpty(searchCriteriaDict.getDictValue())) {
/* 41 */         criteria.add(Restrictions.eq("dictValue", 
/* 42 */           searchCriteriaDict.getDictValue()));
/*    */       }
/*    */     }
/* 45 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 46 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 47 */       if (page.isAsc())
/* 48 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(
/* 49 */           Order.asc("dictId"));
/*    */       else {
/* 51 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(
/* 52 */           Order.asc("dictId"));
/*    */       }
/*    */     }
/* 55 */     else if (page.isAsc())
/* 56 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 58 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 61 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public boolean isExistsByDictCodeName(String dictCode, String dictName)
/*    */   {
/* 66 */     Object[] values = { dictCode, dictName };
/* 67 */     StringBuffer hql = new StringBuffer();
/* 68 */     hql.append("select 1 from SysDict sysDict");
/* 69 */     hql.append(" where sysDict.mntDelete = ");
/* 70 */     hql.append("'");
/* 71 */     hql.append("N");
/* 72 */     hql.append("'");
/* 73 */     hql.append(" and sysDict.sysDictCode.dictCode = ?");
/* 74 */     hql.append(" and sysDict.dictName = ?");
/* 75 */     List list = findByHQL(hql.toString(), values);
/* 76 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 77 */       return false;
/*    */     }
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean isLastOneByDictCode(String dictCode)
/*    */   {
/* 84 */     StringBuffer hql = new StringBuffer();
/* 85 */     hql.append("select 1 from SysDict sysDict");
/* 86 */     hql.append(" where sysDict.mntDelete = ");
/* 87 */     hql.append("'");
/* 88 */     hql.append("N");
/* 89 */     hql.append("'");
/* 90 */     hql.append(" and sysDict.sysDictCode.dictCode = ?");
/* 91 */     List list = findByHQL(hql.toString(), dictCode);
/* 92 */     if ((GlobalMethod.isNullorEmpty(list)) || (list.size() == 1)) {
/* 93 */       return true;
/*    */     }
/* 95 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysDictDaoImpl
 * JD-Core Version:    0.6.2
 */