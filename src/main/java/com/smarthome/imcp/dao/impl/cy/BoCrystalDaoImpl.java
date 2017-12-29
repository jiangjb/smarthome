/*     */ package com.smarthome.imcp.dao.impl.cy;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*     */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaCrystal;
/*     */ import com.smarthome.imcp.dao.cy.BoCrystalDaoIface;
/*     */ import com.smarthome.imcp.dao.model.cy.BoCrystal;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.HibernateException;
/*     */ import org.hibernate.Query;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boCrystalDao")
/*     */ public class BoCrystalDaoImpl extends CommonsDaoImpl<BoCrystal, Serializable>
/*     */   implements BoCrystalDaoIface<BoCrystal, Serializable>
/*     */ {
/*     */   public BoCrystalDaoImpl()
/*     */   {
/*  29 */     super(BoCrystal.class);
/*     */   }
/*     */ 
/*     */   public List<BoCrystal> getList(SearchCriteriaCrystal searchCriteriacrystal, Page page)
/*     */   {
/*  35 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoCrystal.class);
/*  36 */     GlobalMethod.isNullorEmpty(searchCriteriacrystal);
/*     */ 
/*  39 */     criteria.createAlias("boUser", "boUser");
/*  40 */     criteria.add(Restrictions.eq("boUser.mntDelete", "N"));
/*     */ 
/*  42 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  43 */       if (page.isAsc())
/*  44 */         criteria.addOrder(Order.asc("id"));
/*     */       else {
/*  46 */         criteria.addOrder(Order.desc("id"));
/*     */       }
/*     */     }
/*  49 */     else if (page.isAsc())
/*  50 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/*  52 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/*  55 */     return findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public int update(int totalVar, int useVar, int nouseVar, int userId) {
/*  59 */     StringBuffer hql = new StringBuffer();
/*  60 */     hql.append("update BoCrystal c set c.total=c.total+?,c.balance=c.balance+?,c.freeze=c.freeze+?");
/*  61 */     hql.append(" where c.boUser.userId = ?");
/*     */ 
/*  63 */     Object[] values = { Integer.valueOf(totalVar), Integer.valueOf(useVar), Integer.valueOf(nouseVar), Integer.valueOf(userId) };
/*     */ 
/*  65 */     int count = bulkUpdate(hql.toString(), values);
/*  66 */     return count;
/*     */   }
/*     */ 
/*     */   public BoCrystal findByUserId(int userId) {
/*  70 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoCrystal.class);
/*  71 */     criteria.add(Restrictions.eq("boUser.userId", Integer.valueOf(userId)));
/*     */ 
/*  73 */     List list = findByCriteria(criteria);
/*  74 */     if ((list != null) && (!list.isEmpty())) {
/*  75 */       return (BoCrystal)list.get(0);
/*     */     }
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean transferCrystal(int fromId, int toId, int amount)
/*     */   {
/*  82 */     Query query = getCurrentSession().createSQLQuery("{call sp_transferCrystal(?, ?, ?)}");
/*  83 */     query.setParameter(0, Integer.valueOf(fromId));
/*  84 */     query.setParameter(1, Integer.valueOf(toId));
/*  85 */     query.setParameter(2, Integer.valueOf(amount));
/*     */     try
/*     */     {
/*  88 */       query.executeUpdate();
/*  89 */       return true; } catch (HibernateException ex) {
/*     */     }
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   public BoCrystal findByUserCode(String userCode)
/*     */   {
/*  97 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoCrystal.class);
/*  98 */     criteria.createAlias("boUser", "boUser");
/*  99 */     criteria.add(Restrictions.eq("boUser.userCode", userCode));
/*     */ 
/* 101 */     List list = findByCriteria(criteria);
/* 102 */     if ((list != null) && (!list.isEmpty())) {
/* 103 */       return (BoCrystal)list.get(0);
/*     */     }
/* 105 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoCrystal> findByDeviceType(int deviceTypeId)
/*     */   {
/* 110 */     Query query = getCurrentSession().getNamedQuery("getCrystalByDeviceType");
/* 111 */     query.setInteger(0, deviceTypeId);
/*     */ 
/* 113 */     return query.list();
/*     */   }
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.cy.BoCrystalDaoImpl
 * JD-Core Version:    0.6.2
 */