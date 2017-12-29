/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaParam;
/*    */ import com.smarthome.imcp.dao.model.system.SysParam;
/*    */ import com.smarthome.imcp.dao.system.SysParamDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysParamDao")
/*    */ public class SysParamDaoImpl extends CommonsDaoImpl<SysParam, Serializable>
/*    */   implements SysParamDaoIface<SysParam, Serializable>
/*    */ {
/*    */   public SysParamDaoImpl()
/*    */   {
/* 25 */     super(SysParam.class);
/*    */   }
/*    */ 
/*    */   public List<SysParam> getList(SearchCriteriaParam searchCriteriaParam, Page page)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysParam.class);
/* 32 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaParam)) {
/* 33 */       criteria.add(Restrictions.eq("sysParamCode.paramCode", 
/* 34 */         searchCriteriaParam.getParamCode()));
/* 35 */       if (StringUtils.isNotEmpty(searchCriteriaParam.getParamName())) {
/* 36 */         criteria.add(Restrictions.like("paramName", 
/* 37 */           searchCriteriaParam.getParamName(), MatchMode.ANYWHERE));
/*    */       }
/* 39 */       if (StringUtils.isNotEmpty(searchCriteriaParam.getParamValue())) {
/* 40 */         criteria.add(
/* 41 */           Restrictions.like("paramValue", 
/* 42 */           searchCriteriaParam.getParamValue(), 
/* 43 */           MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 46 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 47 */       if (page.isAsc())
/* 48 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("paramId"));
/*    */       else {
/* 50 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("paramId"));
/*    */       }
/*    */     }
/* 53 */     else if (page.isAsc())
/* 54 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 56 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 59 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ 
/*    */   public boolean isExistsByParamCodeName(String paramCode, String paramName) {
/* 63 */     Object[] values = { paramCode, paramName };
/* 64 */     StringBuffer hql = new StringBuffer();
/* 65 */     hql.append("select 1 from SysParam sysParam");
/* 66 */     hql.append(" where sysParam.sysParamCode.paramCode = ?");
/* 67 */     hql.append(" and sysParam.paramName = ?");
/* 68 */     List list = findByHQL(hql.toString(), values);
/* 69 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysParamDaoImpl
 * JD-Core Version:    0.6.2
 */