/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.bo.BoFriendDaoIface;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFriend;
/*    */ import com.smarthome.imcp.dao.model.bo.BoFriend;
/*    */ import com.smarthome.imcp.service.bo.BoFriendServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boFriendService")
/*    */ public class BoFriendServiceImpl
/*    */   implements BoFriendServiceIface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoFriendDaoIface<BoFriend, Serializable> boFriendDao;
/*    */ 
/*    */   public BoFriend findByUserFriendId(int userId, int friendId)
/*    */   {
/* 25 */     return this.boFriendDao.findByUserFriendId(userId, friendId);
/*    */   }
/*    */ 
/*    */   public List<BoFriend> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 30 */     SearchCriteriaFriend searchCriteriaFriend = (SearchCriteriaFriend)searchCriteria;
/* 31 */     return this.boFriendDao.getList(searchCriteriaFriend, page);
/*    */   }
/*    */ 
/*    */   public List<BoFriend> getListByUserId(int userId)
/*    */   {
/* 36 */     return this.boFriendDao.getListByUserId(userId);
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(BoFriend model)
/*    */   {
/* 41 */     return true;
/*    */   }
/*    */ 
/*    */   public BoFriend save(BoFriend model)
/*    */   {
/* 46 */     if (!chkSaveValid(model)) {
/* 47 */       return model;
/*    */     }
/*    */ 
/* 50 */     this.boFriendDao.save(model);
/*    */ 
/* 52 */     return model;
/*    */   }
/*    */ 
/*    */   public BoFriend findByKey(Serializable id)
/*    */   {
/* 57 */     BoFriend model = (BoFriend)this.boFriendDao.findById(id);
/* 58 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(BoFriend model)
/*    */   {
/* 63 */     return true;
/*    */   }
/*    */ 
/*    */   public BoFriend update(BoFriend model)
/*    */   {
/* 68 */     if (chkUpdateValid(model)) {
/* 69 */       this.boFriendDao.update(model);
/*    */     }
/* 71 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(String id)
/*    */   {
/* 76 */     return true;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String id)
/*    */   {
/* 81 */     if (chkDeleteValid(id))
/* 82 */       this.boFriendDao.deleteByKey(Integer.valueOf(id));
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String ids)
/*    */   {
/* 88 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 89 */     while (st.hasMoreElements()) {
/* 90 */       String id = st.nextToken();
/* 91 */       deleteByKey(id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoFriendServiceImpl
 * JD-Core Version:    0.6.2
 */