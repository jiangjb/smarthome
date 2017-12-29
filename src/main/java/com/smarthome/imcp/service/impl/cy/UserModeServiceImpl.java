/*    */ package com.smarthome.imcp.service.impl.cy;
/*    */ 
/*    */ import com.smarthome.imcp.common.Page;
/*    */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*    */ import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaUserMode;
/*    */ import com.smarthome.imcp.dao.cy.UserModeDaoIface;
/*    */ import com.smarthome.imcp.dao.model.cy.UserMode;
/*    */ import com.smarthome.imcp.service.cy.UserModeServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import java.util.StringTokenizer;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("userModeService")
/*    */ public class UserModeServiceImpl
/*    */   implements UserModeServiceIface
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private UserModeDaoIface<UserMode, Serializable> userModeDao;
/*    */ 
/*    */   public List<UserMode> getListByStatus(int status)
/*    */   {
/* 31 */     return this.userModeDao.getListByStatus(status);
/*    */   }
/*    */ 
/*    */   public UserMode findByKey(Integer userId, Integer deviceId)
/*    */   {
/* 36 */     UserMode model = this.userModeDao.findByKey(userId, deviceId);
/* 37 */     return model;
/*    */   }
/*    */ 
/*    */   public List<UserMode> getList(SearchCriteria searchCriteria, Page page)
/*    */   {
/* 42 */     SearchCriteriaUserMode searchCriteriaUserMode = (SearchCriteriaUserMode)searchCriteria;
/* 43 */     return this.userModeDao.getList(searchCriteriaUserMode, page);
/*    */   }
/*    */ 
/*    */   public boolean chkSaveValid(UserMode model)
/*    */   {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   public UserMode save(UserMode model)
/*    */   {
/* 53 */     if (chkSaveValid(model)) {
/* 54 */       this.userModeDao.save(model);
/*    */     }
/* 56 */     return model;
/*    */   }
/*    */ 
/*    */   public UserMode findByKey(Serializable id)
/*    */   {
/* 61 */     UserMode model = (UserMode)this.userModeDao.findById(id);
/* 62 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkUpdateValid(UserMode model)
/*    */   {
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   public UserMode update(UserMode model)
/*    */   {
/* 72 */     if (chkUpdateValid(model)) {
/* 73 */       this.userModeDao.update(model);
/*    */     }
/* 75 */     return model;
/*    */   }
/*    */ 
/*    */   public boolean chkDeleteValid(String id)
/*    */   {
/* 80 */     return true;
/*    */   }
/*    */ 
/*    */   public void deleteByKey(String id)
/*    */   {
/* 85 */     if (chkDeleteValid(id))
/* 86 */       this.userModeDao.deleteByKey(id);
/*    */   }
/*    */ 
/*    */   public void deleteByKeys(String ids)
/*    */   {
/* 92 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 93 */     while (st.hasMoreElements()) {
/* 94 */       String id = st.nextToken();
/* 95 */       deleteByKey(id);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.cy.UserModeServiceImpl
 * JD-Core Version:    0.6.2
 */