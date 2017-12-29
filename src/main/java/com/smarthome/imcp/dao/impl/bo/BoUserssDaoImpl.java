/*     */ package com.smarthome.imcp.dao.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*     */ import com.smarthome.imcp.dao.bo.BoUserssDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaUsers;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boUserssDao")
/*     */ public class BoUserssDaoImpl extends CommonsDaoImpl<BoUsers, Serializable>
/*     */   implements BoUserssDaoIface<BoUsers, Serializable>
/*     */ {
/*     */   public BoUserssDaoImpl()
/*     */   {
/*  29 */     super(BoUsers.class);
/*     */   }
/*     */ 
/*     */   public List<BoUsers> getAllList()
/*     */   {
/*  34 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/*  35 */     return findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoUsers> getList(SearchCriteriaUsers searchCriteriaUser)
/*     */   {
/*  41 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/*  42 */     GlobalMethod.isNullorEmpty(searchCriteriaUser);
/*     */ 
/*  61 */     return findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public List<BoUsers> getList(SearchCriteriaUsers searchCriteriaUser, Page page)
/*     */   {
/*  67 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/*  68 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaUser))
/*     */     {
/*  88 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserPhone())) {
/*  89 */         criteria.add(Restrictions.like("userPhone", searchCriteriaUser.getUserPhone(), MatchMode.END));
/*     */       }
/*     */     }
/*     */ 
/*  93 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  94 */       if (page.isAsc())
/*  95 */         criteria.addOrder(Order.asc("userId"));
/*     */       else {
/*  97 */         criteria.addOrder(Order.asc("userId"));
/*     */       }
/*     */     }
/* 100 */     else if (page.isAsc())
/* 101 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/* 103 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/* 106 */     return findByCriteria(criteria, page);
/*     */   }
/*     */
			@Override
			public List<BoUsers> findAllBoUsers() {
				StringBuffer hql = new StringBuffer();
				hql.append("select bus.userName,bus.userSex,bus.userPhone,bus.phoneType,bus.versionType,bus.signature,bus.city from BoUsers bus");//可能有问题
				List<BoUsers> list=findByHQL(hql.toString());
				if(list ==null) {
					return null;
				}
				return list;
			} 
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUserssDaoImpl
 * JD-Core Version:    0.6.2
 */