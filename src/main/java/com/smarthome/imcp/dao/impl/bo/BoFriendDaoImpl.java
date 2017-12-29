/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.GlobalMethod;
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoFriendDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFriend;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFriend;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFriendDao")
/*    */ public class BoFriendDaoImpl extends CommonsDaoImpl<BoFriend, Serializable>
/*    */   implements BoFriendDaoIface<BoFriend, Serializable>
/*    */ {
/*    */   public BoFriendDaoImpl()
/*    */   {
/* 26 */     super(BoFriend.class);
/*    */   }
/*    */ 
/*    */   public BoFriend findByUserFriendId(int userId, int friendId)
/*    */   {
/* 31 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFriend.class);
/* 32 */     criteria.add(Restrictions.eq("user.userId", Integer.valueOf(userId)));
/* 33 */     criteria.add(Restrictions.eq("friend.userId", Integer.valueOf(friendId)));
/* 34 */     List list = findByCriteria(criteria);
/* 35 */     if ((list != null) && (list.size() > 0)) {
/* 36 */       return (BoFriend)list.get(0);
/*    */     }
/* 38 */     return null;
/*    */   }
/*    */ 
/*    */   public List<BoFriend> getListByUserId(int userId)
/*    */   {
/* 43 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFriend.class);
/* 44 */     criteria.add(Restrictions.eq("user.userId", Integer.valueOf(userId)));
/* 45 */     criteria.addOrder(Order.asc("id"));
/* 46 */     return findByCriteria(criteria);
/*    */   }
/*    */ 
/*    */   public List<BoFriend> getList(SearchCriteriaFriend searchCriteriafriend, Page page)
/*    */   {
/* 52 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoFriend.class);
/* 53 */     GlobalMethod.isNullorEmpty(searchCriteriafriend);
/*    */ 
/* 55 */     if (StringUtils.isEmpty(page.getOrderField())) {
/* 56 */       if (page.isAsc())
/* 57 */         criteria.addOrder(Order.asc("id"));
/*    */       else {
/* 59 */         criteria.addOrder(Order.desc("id"));
/*    */       }
/*    */     }
/* 62 */     else if (page.isAsc())
/* 63 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*    */     else {
/* 65 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*    */     }
/*    */ 
/* 68 */     return findByCriteria(criteria, page);
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoFriendDaoImpl
 * JD-Core Version:    0.6.2
 */