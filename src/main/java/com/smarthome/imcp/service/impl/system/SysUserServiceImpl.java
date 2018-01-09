/*     */ package com.smarthome.imcp.service.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.common.ContextUtil;
/*     */ import com.smarthome.imcp.common.Md5;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.criteria.SearchCriteria;
/*     */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaUser;
/*     */ import com.smarthome.imcp.dao.model.system.SysUser;
/*     */ import com.smarthome.imcp.dao.system.SysUserDaoIface;
/*     */ import com.smarthome.imcp.dao.vo.system.UserChangePassword;
/*     */ import com.smarthome.imcp.exception.BusinessException;
/*     */ import com.smarthome.imcp.secur.CurrentUser;
/*     */ import com.smarthome.imcp.service.AbstractBasicService;
/*     */ import com.smarthome.imcp.service.secur.SecurServiceIface;
/*     */ import com.smarthome.imcp.service.system.SysUserServiceIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
		  import org.hibernate.criterion.DetachedCriteria;
		  import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("sysUserService")
/*     */ public class SysUserServiceImpl extends AbstractBasicService<SysUser, Serializable>
/*     */   implements SysUserServiceIface<SysUser, Serializable>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysUserDaoIface<SysUser, Serializable> sysUserDao;
/*     */ 
/*     */   @Autowired
/*     */   private SecurServiceIface securService;

//            @Autowired
//            private CommonsDaoIface commonsDao;
/*     */            
/*     */   private void authorizeCriteria(SearchCriteriaUser searchCriteria)
/*     */   {
/*  44 */     CurrentUser currentUser = ContextUtil.getCurrentUser();
/*  45 */     super.authFilterProvince(currentUser, searchCriteria);
/*  46 */     super.authFilterCity(currentUser, searchCriteria);
/*  47 */     super.authFilterCounty(currentUser, searchCriteria);
/*  48 */     super.authFilterTown(currentUser, searchCriteria);
/*  49 */     super.authFilterVillage(currentUser, searchCriteria);
/*     */   }         

/*     */   public List<SysUser> getList(SearchCriteria searchCriteria, Page page)
/*     */   {
/*  54 */     SearchCriteriaUser searchCriteriaUser = (SearchCriteriaUser)searchCriteria;
/*  55 */     if (searchCriteriaUser == null) searchCriteriaUser = new SearchCriteriaUser();
/*     */ 
/*  57 */     if (!chkCriteriaValid(searchCriteriaUser)) {
/*  58 */       return null;
/*     */     }
/*     */ 
/*  61 */     authorizeCriteria(searchCriteriaUser);
/*     */ 
/*  63 */     return this.sysUserDao.getList(searchCriteriaUser, page);
/*     */   }
/*     */ 
/*     */   public boolean chkSaveValid(SysUser sysUser)
/*     */   {
///*  69 */     if (!sysUser.getLoginPwd().equalsIgnoreCase(
///*  69 */       sysUser.getConfirmLoginPwd())) {
///*  70 */       throw new BusinessException("前后两次输入的密码不一致！");
///*     */     }
/*     */ 
/*  73 */     if (this.sysUserDao.isExistsByUserIdLoginName(sysUser.getUserId(), 
/*  73 */       sysUser.getLoginName())) {
/*  74 */       throw new BusinessException("此登陆名已存在，不能重复添加！");//
/*     */     }
/*  76 */     return true;
/*     */   }
/*     */   
/*     */   public SysUser save(SysUser sysUser)
/*     */   {
///*  81 */     if (!chkSaveValid(sysUser)) {//检查该用户是否已存在
////			if (1>0) {
///*  82 */       return null;
///*     */     }else {
//				Md5 md5 = new Md5();
//	/*  86 */   String loginPwd = md5.getMD5ofStr(sysUser.getLoginPwd());
////	              System.out.println("loginPwd="+loginPwd);
//	/*  87 */   sysUser.setLoginPwd(loginPwd);
	/*  88 */   this.sysUserDao.save(sysUser);//save
	/*  89 */   return sysUser;    
/*     */   }
/*     */ 
/*     */   public SysUser findByKey(Serializable id)
/*     */   {
/*  94 */     return (SysUser)this.sysUserDao.findById(id);
/*     */   }

			@Override
//			public Boolean findByUserPhone(String userPhone) {
			public int findByUserPhone(String userPhone) {
				// TODO Auto-generated method stub
				System.out.println("this.sysUserDao.findByPhone(userPhone)==="+this.sysUserDao.findByPhone(userPhone));
				return this.sysUserDao.findByPhone(userPhone);
			} 
			
			@Override
			public int findByUserEmail(String email) {
				System.out.println("this.sysUserDao.findByPhone(userPhone)==="+this.sysUserDao.findByEmail(email));
				return this.sysUserDao.findByEmail(email);
			}
/*     */ 
/*     */   public boolean chkUpdateValid(SysUser sysUser)
/*     */   {
//			  System.out.println("sysUser.getUserId()=="+sysUser.getUserId());//2(root)
//			  System.out.println("this.sysUserDao=="+this.sysUserDao);//this.sysUserDao==null
//			  System.out.println("this=="+this);//com.smarthome.imcp.service.impl.system.SysUserServiceImpl@2eaf030
//			  System.out.println("sysUser.getLoginName()=="+sysUser.getLoginName());//root
///* 100 */     if (this.sysUserDao.isExistsByUserIdLoginName(sysUser.getUserId(), //this.sysUserDao.method()  空指针
///* 100 */       sysUser.getLoginName())) {
///* 101 */       throw new BusinessException("此登陆名已存在，不能进行修改操作！");
///*     */     } 
			if (!this.sysUserDao.isExistsByUserIdLoginName(sysUser.getUserId(), //this.sysUserDao.method()  空指针
				sysUser.getLoginName())) {
				throw new BusinessException("此登陆名不存在，不能进行修改操作！");
			} 
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   public SysUser update(SysUser sysUser)
/*     */   {
/* 108 */     if (chkUpdateValid(sysUser)) {//这个出现了问题
/* 109 */       this.sysUserDao.update(sysUser);
/*     */     }
/* 111 */     return sysUser;
/*     */   }
/*     */ 
/*     */   public void deleteByKey(String userId)
/*     */   {
/* 116 */     if (chkDeleteValid(userId))
/* 117 */       this.sysUserDao.deleteByKey(Integer.valueOf(Integer.parseInt(userId)));
/*     */   }
/*     */ 
/*     */   public void deleteByKeys(String userIds)
/*     */   {
/* 123 */     StringTokenizer st = new StringTokenizer(userIds, ",");
/* 124 */     while (st.hasMoreElements()) {
/* 125 */       String userId = st.nextToken();
/* 126 */       deleteByKey(userId);
/*     */     }
/*     */   }
/*     */ 
/*     */   public SysUser checkUser(String loginName, String loginPwd)
/*     */   {
/* 132 */     return this.sysUserDao.checkUser(loginName, loginPwd);
/*     */   }
/*     */ 
/*     */   public void doChangePassword(UserChangePassword userChangePassword)
/*     */   {
/* 137 */     SysUser sysUser = findByKey(userChangePassword.getUserId());
/* 138 */     Md5 md5 = new Md5();
/*     */ 
/* 140 */     if (!md5.getMD5ofStr(userChangePassword.getOldPassword()).equals(
/* 140 */       sysUser.getLoginPwd())) {
/* 141 */       throw new BusinessException("原密码错误，请重新输入！");
/*     */     }
/* 143 */     this.sysUserDao.changePassword(userChangePassword.getUserId(), 
/* 144 */       md5.getMD5ofStr(userChangePassword.getNewPassword()));
/*     */   }
/*     */ 
/*     */   public void doChangePasswordNoOld(UserChangePassword userChangePassword)
/*     */   {
/* 149 */     Md5 md5 = new Md5();
/* 150 */     this.sysUserDao.changePassword(userChangePassword.getUserId(), 
/* 151 */       md5.getMD5ofStr(userChangePassword.getNewPassword()));
/*     */   }

			@Override
			public SysUser getSysUsersByName(String userName) {
				DetachedCriteria criteria = DetachedCriteria.forClass(SysUser.class);
		        criteria.add(Restrictions.eq("loginName", userName));
				 
				List<SysUser> list = this.sysUserDao.findByCriteria(criteria);
			    System.out.println(list);
			    if ((list == null) || (list.isEmpty())) {
			       return null;
			    }
				return list.get(0);
			}

//			@Override
//			public Set<String> getRoles(String userName) {
//				//userName > userID > Role
//				DetachedCriteria criteria = DetachedCriteria.forClass(SysUser.class);
//				criteria.add(Restrictions.eq("userName", userName));
//				return this.sysUserDao.findByCriteria(criteria);
//			}
//			
//			@Override
//			public Set<String> getPermissions(String userName) {
//				//userName > userID > roleID > Permission
//				DetachedCriteria criteria = DetachedCriteria.forClass(SysUser.class);
//				criteria.add(Restrictions.eq("userName", userName));
//				return this.sysUserDao.findByCriteria(criteria);
//			}
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.system.SysUserServiceImpl
 * JD-Core Version:    0.6.2
 */