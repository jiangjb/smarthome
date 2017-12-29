/*     */ package com.smarthome.imcp.service.impl.bo;
/*     */ 
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.bo.BoUserDaoIface;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUser;
/*     */ import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsers;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*     */ import com.smarthome.imcp.dao.model.cy.BoCrystal;
/*     */ import com.smarthome.imcp.dao.model.system.SysKey;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.bo.BoUserServiceIface;
/*     */ import com.smarthome.imcp.service.cy.BoCrystalServiceIface;
/*     */ import com.smarthome.imcp.service.system.SysKeyServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("boUserService")
/*     */ public class BoUserServiceImpl extends AbstractBasicService<BoUser, Serializable>
/*     */   implements BoUserServiceIface<BoUser, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private BoUserDaoIface<BoUser, Serializable> boUserDao;
/*     */ 
/*     */   @Autowired
/*     */   private SysKeyServiceIface<SysKey, Serializable> sysKeyService;
/*     */ 
/*     */   @Autowired
/*     */   private BoCrystalServiceIface boCrystalService;
/*     */ 
/*     */   public void updateUserIP(Integer userId, String ip, String latitude, String longitude)
/*     */   {
/*  45 */     Object[] values = { ip, latitude, longitude, userId };
/*  46 */     StringBuffer hql = new StringBuffer();
/*  47 */     hql.append("update BoUser u");
/*  48 */     hql.append(" set u.userIp = ?,");
/*  49 */     hql.append(" u.latitude = ?,");
/*  50 */     hql.append(" u.longitude = ?");
/*  51 */     hql.append(" where u.userId = ?");
/*  52 */     hql.append(" and u.mntDelete = 'N'");
/*  53 */     this.boUserDao.bulkUpdate(hql.toString(), values);
/*     */   }
/*     */ 
/*     */   public BoUser findByUserCode(String userCode) {
/*  57 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/*  58 */     criteria.add(Restrictions.eq("userCode", userCode));
/*     */ 
/*  60 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/*  62 */     List list = this.boUserDao.findByCriteria(criteria);
/*  63 */     if ((list == null) || (list.isEmpty())) {
/*  64 */       return null;
/*     */     }
/*  66 */     return (BoUser)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoUser findByUserPhone(Integer fid, String userPhone) {
/*  70 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/*  71 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/*  72 */     criteria.add(Restrictions.eq("source", fid));
/*  73 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/*  75 */     List list = this.boUserDao.findByCriteria(criteria);
/*  76 */     if ((list == null) || (list.isEmpty())) {
/*  77 */       return null;
/*     */     }
/*  79 */     return (BoUser)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoUser findByUserPhonePwd(String userPhone, String pwd) {
/*  83 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/*  84 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/*  85 */     criteria.add(Restrictions.eq("userPwd", pwd));
/*  86 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/*  88 */     List list = this.boUserDao.findByCriteria(criteria);
/*  89 */     if ((list == null) || (list.isEmpty())) {
/*  90 */       return null;
/*     */     }
/*  92 */     return (BoUser)list.get(0);
/*     */   }
/*     */ 
/*     */   public BoUser findByUserPhonePwd(Integer fid, String userPhone, String pwd) {
/*  96 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUser.class);
/*  97 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/*  98 */     criteria.add(Restrictions.eq("userPwd", pwd));
/*  99 */     criteria.add(Restrictions.eq("source", fid));
/* 100 */     criteria.add(Restrictions.eq("mntDelete", "N"));
/*     */ 
/* 102 */     List list = this.boUserDao.findByCriteria(criteria);
/* 103 */     if ((list == null) || (list.isEmpty())) {
/* 104 */       return null;
/*     */     }
/* 106 */     return (BoUser)list.get(0);
/*     */   }
/*     */ 
/*     */   public List<BoUser> getAllList()
/*     */   {
/* 111 */     return this.boUserDao.getAllList();
/*     */   }
/*     */ 
/*     */   public List<BoUser> getList(SearchCriteria searchCriteria)
/*     */   {
/* 116 */     SearchCriteriaUser searchCriteriaUser = (SearchCriteriaUser)searchCriteria;
/* 117 */     if (chkCriteriaValid(searchCriteria)) {
/* 118 */       return this.boUserDao.getList(searchCriteriaUser);
/*     */     }
/* 120 */     return null;
/*     */   }
/*     */ 
/*     */   public List<BoUser> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/* 125 */     SearchCriteriaUser searchCriteriaUser = (SearchCriteriaUser)searchCriteria;
/* 126 */     if (chkCriteriaValid(searchCriteria)) {
/* 127 */       return this.boUserDao.getList(searchCriteriaUser, page);
/*     */     }
/* 129 */     return null;
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(BoUser model)
/*     */   {
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUser save(BoUser model)
/*     */   {
/* 139 */     if (chkSaveValid(model)) {
/* 140 */       model.setUserCode(this.sysKeyService.getPrimaryKey("BO_USER"));
/* 141 */       this.boUserDao.save(model);
/*     */ 
/* 143 */       BoCrystal crystal = new BoCrystal();
/* 144 */       crystal.setBalance(Integer.valueOf(0));
/* 145 */       crystal.setBoUser(model);
/* 146 */       crystal.setFreeze(Integer.valueOf(0));
/* 147 */       crystal.setTotal(Integer.valueOf(0));
/*     */ 
/* 149 */       this.boCrystalService.save(crystal);
/*     */     }
/* 151 */     return model;
/*     */   }
/*     */ 
/*     */   public BoUser findByKey(Serializable id)
/*     */   {
/* 156 */     BoUser model = (BoUser)this.boUserDao.findById(id);
/* 157 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkUpdateValid(BoUser model)
/*     */   {
/* 162 */     return true;
/*     */   }
/*     */ 
/*     */   public BoUser update(BoUser model)
/*     */   {
/* 167 */     if (chkUpdateValid(model)) {
/* 168 */       this.boUserDao.update(model);
/*     */     }
/* 170 */     return model;
/*     */   }
/*     */ 
/*     */   public boolean chkDeleteValid(String id)
/*     */   {
/* 175 */     return true;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String id)
/*     */   {
/* 180 */     if ((id == null) || ("".equals(id)))
/* 181 */       return;
/* 182 */     if (chkDeleteValid(id))
/* 183 */       this.boUserDao.deleteLogicByKey(Integer.valueOf(id));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String ids)
/*     */   {
/* 189 */     StringTokenizer st = new StringTokenizer(ids, ",");
/* 190 */     while (st.hasMoreElements()) {
/* 191 */       String id = st.nextToken();
/* 192 */       deleteByKey(id);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean validateUserPassword(String userCode, String password)
/*     */   {
/* 198 */     int count = this.boUserDao.getCount("from BoUser s where s.userCode = ? and s.userPwd = ?", new Object[] { userCode, password }).intValue();
/* 199 */     return count > 0;
/*     */   }

			/*@Override
			public String toExcel() {
				// TODO Auto-generated method stub
				return this.boUserDao.toExcel();
			}*/
/*     */ }

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUserServiceImpl
 * JD-Core Version:    0.6.2
 */