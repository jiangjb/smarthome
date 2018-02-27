/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoUserssDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaUsers;
import com.smarthome.imcp.dao.model.bo.BoUserDevices;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUsers;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoUserssServiceIface;
/*     */ import com.smarthome.imcp.util.SendMsgUtil;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boUserssService")
/*     */ public class BoUserssServiceImpl extends AbstractBasicService<BoUsers, Serializable>
/*     */   implements BoUserssServiceIface<BoUsers, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoUserssDaoIface<BoUsers, Serializable> boUserDao;
/*     */ 
/*     */   public List<BoUsers> getList(SearchCriteria searchCriteria)
/*     */   {
/*  47 */     SearchCriteriaUsers searchCriteriaUser = (SearchCriteriaUsers)searchCriteria;
/*  48 */     if (chkCriteriaValid(searchCriteria)) {
/*  49 */       return this.boUserDao.getList(searchCriteriaUser);
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoUsers> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  56 */     SearchCriteriaUsers searchCriteriaUser = (SearchCriteriaUsers)searchCriteria;
/*  57 */     if (chkCriteriaValid(searchCriteria)) {
/*  58 */       return this.boUserDao.getList(searchCriteriaUser, page);
/*     */     }
/*  60 */     return null;
/*     */   }
/*     */    public BoUsers findByKey(Serializable id)
/*     */   {//NEW ADD 2018/2/27
/*  94 */     return (BoUsers)this.boUserDao.findById(id);
/*     */   }
/*     */   public BoUsers findByUserPhone(String userPhone) {
/*  64 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/*  65 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/*     */ 
/*  68 */     List<BoUsers> list = this.boUserDao.findByCriteria(criteria);
/*  69 */     System.out.println(list);
/*  70 */     if ((list == null) || (list.isEmpty())) {
/*  71 */       return null;
/*     */     }
/*  73 */     return list.get(0);
/*     */   }
/*     */ 
/*     */   public BoUsers findByUserPhonePwd(String userPhone, String pwd)
/*     */   {
/*  78 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/*  79 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/*  80 */     criteria.add(Restrictions.eq("userPwd", pwd));
/*  81 */     List list = this.boUserDao.findByCriteria(criteria);
/*  82 */     if ((list == null) || (list.isEmpty())) {
/*  83 */       return null;
/*     */     }
/*  85 */     return (BoUsers)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoUsers findByUserUserCode(String userCode)
/*     */   {
/*  90 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/*  91 */     criteria.add(Restrictions.eq("userCode", userCode));
/*  92 */     List list = this.boUserDao.findByCriteria(criteria);
/*  93 */     if ((list == null) || (list.isEmpty())) {
/*  94 */       return null;
/*     */     }
/*  96 */     return (BoUsers)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoUsers> getAllList()
/*     */   {
/* 102 */     return this.boUserDao.getAllList();
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoUsers model)
/*     */   {
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUsers save(BoUsers model)
/*     */   {
/* 112 */     SendMsgUtil s = new SendMsgUtil();
/*     */ 
/* 114 */     if (chkSaveValid(model))
/*     */     {
/* 116 */       this.boUserDao.save(model);
/*     */     }
/* 118 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoUsers model)
/*     */   {
/* 124 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUsers update(BoUsers model)
/*     */   {
/* 129 */     if (chkUpdateValid(model)) {
/* 130 */       this.boUserDao.update(model);
/*     */     }
/* 132 */     return model;
/*     */   }
/*     */ 
/*     */   public List<BoUsers> getByAuthorizeUserCode(String userCode)
/*     */   {
/* 138 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/* 139 */     criteria.add(Restrictions.eq("authorizationUserCode", userCode));
/* 140 */     return this.boUserDao.findByCriteria(criteria);
/*     */   }
/*     */ 
/*     */   public BoUsers findByUserEmail(String userEmail)
/*     */   {
/* 146 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsers.class);
/* 147 */     criteria.add(Restrictions.eq("userEmail", userEmail));
/* 148 */     List list = this.boUserDao.findByCriteria(criteria);
/* 149 */     System.out.println(list);
/* 150 */     if ((list == null) || (list.isEmpty())) {
/* 151 */       return null;
/*     */     }
/* 153 */     return (BoUsers)list.get(0);
/*     */   }
/*     */
			@Override
			public List<BoUsers> findAllBoUsers() {
				// TODO Auto-generated method stub
				return this.boUserDao.findAllBoUsers();
			}
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUserssServiceImpl
 * JD-Core Version:    0.6.2
 */