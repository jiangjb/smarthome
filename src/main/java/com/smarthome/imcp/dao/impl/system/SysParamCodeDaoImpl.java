/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaParamCode;
/*    */ import com.smarthome.imcp.dao.model.system.SysParamCode;
/*    */ import com.smarthome.imcp.dao.system.SysParamCodeDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysParamCodeDao")
/*    */ public class SysParamCodeDaoImpl extends CommonsDaoImpl<SysParamCode, Serializable>
/*    */   implements SysParamCodeDaoIface<SysParamCode, Serializable>
/*    */ {
/*    */   public SysParamCodeDaoImpl()
/*    */   {
/* 26 */     super(SysParamCode.class);
/*    */   }
/*    */ 
/*    */   public List<SysParamCode> getList(SearchCriteriaParamCode searchCriteriaParamCode, Page page)
/*    */   {
/* 32 */     DetachedCriteria criteria = 
/* 33 */       DetachedCriteria.forClass(SysParamCode.class);
/* 34 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaParamCode))
/*    */     {
/* 36 */       if (StringUtils.isNotEmpty(searchCriteriaParamCode
/* 36 */         .getParamCodeName())) {
/* 37 */         criteria.add(Restrictions.like("paramCodeName", 
/* 38 */           searchCriteriaParamCode.getParamCodeName(), 
/* 39 */           MatchMode.ANYWHERE));
/*    */       }
/* 41 */       if (StringUtils.isNotEmpty(searchCriteriaParamCode.getParamCode())) {
/* 42 */         criteria.add(Restrictions.like("paramCode", 
/* 43 */           searchCriteriaParamCode.getParamCode(), 
/* 44 */           MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 47 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 48 */       if (page.isAsc())
/* 49 */         criteria.addOrder(Order.asc("paramCode"));
/*    */       else {
/* 51 */         criteria.addOrder(Order.desc("paramCode"));
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
/*    */   public boolean isExistsByParamCode(String paramCode)
/*    */   {
/* 65 */     StringBuffer hql = new StringBuffer();
/* 66 */     hql.append("select 1 from SysParamCode sysParamCode");
/* 67 */     hql.append(" where sysParamCode.paramCode = ?");
/* 68 */     List list = findByHQL(hql.toString(), paramCode);
/* 69 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 70 */       return false;
/*    */     }
/* 72 */     return true;
/*    */   }
/*    */ 
/*    */   public boolean isExistsByParamCodeName(String paramCodeName)
/*    */   {
/* 77 */     StringBuffer hql = new StringBuffer();
/* 78 */     hql.append("select 1 from SysParamCode sysParamCode");
/* 79 */     hql.append(" where sysParamCode.paramCodeName = ?");
/* 80 */     List list = findByHQL(hql.toString(), paramCodeName);
/* 81 */     if (GlobalMethod.isNullorEmpty(list)) {
/* 82 */       return false;
/*    */     }
/* 84 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysParamCodeDaoImpl
 * JD-Core Version:    0.6.2
 */