/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoUserDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUser;
import com.smarthome.imcp.dao.model.bo.BoHostDevice;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsers;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;

/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.MatchMode;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boUserDao")
/*    */ public class BoUserDaoImpl extends CommonsDaoImpl<BoUser, Serializable>
/*    */   implements BoUserDaoIface<BoUser, Serializable>
/*    */ {
/*    */   public BoUserDaoImpl()
/*    */   {
/* 26 */     super(BoUser.class);
/*    */   }
/*    */ 
/*    */   public List<BoUser> getAllList()
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/* 32 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoUser> getList(SearchCriteriaUser searchCriteriaUser)
/*    */   {
/* 37 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/* 38 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaUser)) {
/* 39 */       if (searchCriteriaUser.getGroupId() != null)
/* 40 */         criteria.add(Restrictions.eq("boUserGroup.groupId", searchCriteriaUser.getGroupId()));
/*    */       else {
/* 42 */         criteria.add(Restrictions.isNull("boUserGroup.groupId"));
/*    */       }
/*    */ 
/* 45 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserName())) {
/* 46 */         criteria.add(Restrictions.like("userName", searchCriteriaUser.getUserName(), MatchMode.ANYWHERE));
/*    */       }
/* 48 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserPhone())) {
/* 49 */         criteria.add(Restrictions.like("userPhone", searchCriteriaUser.getUserPhone(), MatchMode.END));
/*    */       }
/* 51 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserEmail())) {
/* 52 */         criteria.add(Restrictions.like("userEmail", searchCriteriaUser.getUserEmail(), MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 55 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 56 */     criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("userId"));
/* 57 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoUser> getList(SearchCriteriaUser searchCriteriaUser, Page page)
/*    */   {
/* 63 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/* 64 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaUser)) {
/* 65 */       if ((searchCriteriaUser.getType() != null) && (searchCriteriaUser.getType().intValue() > 0)) {
/* 66 */         criteria.add(Restrictions.eq("source", searchCriteriaUser.getType()));
/*    */       }
/*    */ 
/* 69 */       if (searchCriteriaUser.getGroupId() != null)
/* 70 */         criteria.add(Restrictions.eq("boUserGroup.groupId", searchCriteriaUser.getGroupId()));
/*    */       else {
/* 72 */         criteria.add(Restrictions.isNull("boUserGroup.groupId"));
/*    */       }
/*    */ 
/* 75 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserName())) {
/* 76 */         criteria.add(Restrictions.like("userName", searchCriteriaUser.getUserName(), MatchMode.ANYWHERE));
/*    */       }
/* 78 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserPhone())) {
/* 79 */         criteria.add(Restrictions.like("userPhone", searchCriteriaUser.getUserPhone(), MatchMode.END));
/*    */       }
/* 81 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserEmail())) {
/* 82 */         criteria.add(Restrictions.like("userEmail", searchCriteriaUser.getUserEmail(), MatchMode.ANYWHERE));
/*    */       }
/*    */     }
/* 85 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/* 86 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 87 */       if (page.isAsc())
/* 88 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("userId"));
/*    */       else {
/* 90 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("userId"));
/*    */       }
/*    */     }
/* 93 */     else if (page.isAsc())
/* 94 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 96 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 99 */     return findByCriteria(criteria, page);
/*    */   }
/*    */
			
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUserDaoImpl
 * JD-Core Version:    0.6.2
 */