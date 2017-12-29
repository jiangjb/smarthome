/*    */ package com.smarthome.imcp.dao.impl.system;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaOperate;
/*    */ import com.smarthome.imcp.dao.model.system.SysOperate;
/*    */ import com.smarthome.imcp.dao.system.SysOperateDaoIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository("sysOperateDao")
/*    */ public class SysOperateDaoImpl extends CommonsDaoImpl<SysOperate, Serializable>
/*    */   implements SysOperateDaoIface<SysOperate, Serializable>
/*    */ {
/*    */   public SysOperateDaoImpl()
/*    */   {
/* 26 */     super(SysOperate.class);
/*    */   }
/*    */ 
/*    */   public List<SysOperate> getList(SearchCriteriaOperate searchCriteriaOperate, Page page)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysOperate.class);
/* 32 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaOperate)) {
/* 33 */       if (StringUtils.isNotEmpty(searchCriteriaOperate.getOperateUserName())) {
/* 34 */         criteria.add(Restrictions.like("operateUserName", searchCriteriaOperate.getOperateUserName(), MatchMode.ANYWHERE));
/*    */       }
/* 36 */       if (StringUtils.isNotEmpty(searchCriteriaOperate.getOperateType())) {
/* 37 */         criteria.add(Restrictions.eq("operateType", searchCriteriaOperate.getOperateType()));
/*    */       }
/*    */     }
/* 40 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 41 */       if (page.isAsc())
/* 42 */         criteria.addOrder(Order.asc("operateId"));
/*    */       else {
/* 44 */         criteria.addOrder(Order.asc("operateId"));
/*    */       }
/*    */     }
/* 47 */     else if (page.isAsc())
/* 48 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 50 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 53 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysOperateDaoImpl
 * JD-Core Version:    0.6.2
 */