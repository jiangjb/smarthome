/*     */ package com.smarthome.imcp.dao.impl.system;
/*     */ 
/*     */ import com.smarthome.imcp.common.GlobalMethod;
/*     */ import com.smarthome.imcp.common.Page;
/*     */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*     */ import com.smarthome.imcp.dao.criteria.system.SearchCriteriaUser;
import com.smarthome.imcp.dao.model.bo.BoUser;
import com.smarthome.imcp.dao.model.bo.BoUsers;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*     */ import com.smarthome.imcp.dao.model.system.SysUser;
/*     */ import com.smarthome.imcp.dao.system.SysUserDaoIface;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
import java.util.Set;

/*     */ import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
/*     */ import org.hibernate.criterion.DetachedCriteria;
/*     */ import org.hibernate.criterion.MatchMode;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.stereotype.Service;
/*     */ 
/*     */ @Service("sysUserDao")
/*     */ public class SysUserDaoImpl extends CommonsDaoImpl<SysUser, Serializable>             //继承了CommonsDaoImpl类，可以用它的方法
/*     */   implements SysUserDaoIface<SysUser, Serializable>
/*     */ {
/*     */   public SysUserDaoImpl()
/*     */   {
/*  27 */     super(SysUser.class);
/*     */   }
/*     */ 
/*     */   public List<SysUser> getList(SearchCriteriaUser searchCriteriaUser, Page page)
/*     */   {
/*  34 */     DetachedCriteria criteria = DetachedCriteria.forClass(SysUser.class);
/*  35 */     if (!GlobalMethod.isNullorEmpty(searchCriteriaUser)) {
/*  36 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getLoginName())) {
/*  37 */         criteria.add(Restrictions.like("loginName", searchCriteriaUser.getLoginName(), MatchMode.ANYWHERE));
/*     */       }
/*  39 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserName())) {
/*  40 */         criteria.add(Restrictions.like("useName", searchCriteriaUser.getUserName(), MatchMode.ANYWHERE));
/*     */       }
/*  42 */       if (StringUtils.isNotEmpty(searchCriteriaUser.getUserType())) {
/*  43 */         criteria.add(Restrictions.eq("userType", searchCriteriaUser.getUserType()));
/*     */       }
/*     */     }
/*  46 */     if (StringUtils.isEmpty(page.getOrderField())) {
/*  47 */       if (page.isAsc())
/*  48 */         criteria.addOrder(Order.asc("mntPosition")).addOrder(Order.asc("userId"));
/*     */       else {
/*  50 */         criteria.addOrder(Order.desc("mntPosition")).addOrder(Order.desc("userId"));
/*     */       }
/*     */     }
/*  53 */     else if (page.isAsc())
/*  54 */       criteria.addOrder(Order.asc(page.getOrderField()));
/*     */     else {
/*  56 */       criteria.addOrder(Order.desc(page.getOrderField()));
/*     */     }
/*     */ 
/*  59 */     return findByCriteria(criteria, page);
/*     */   }
/*     */ 
/*     */   public boolean isExistsByUserIdLoginName(Integer userId, String loginName)
/*     */   {
	          System.out.println("coming");
/*  64 */     Object[] values = { loginName };
/*  65 */     StringBuffer hql = new StringBuffer();

			  hql.append("select 1 from SysUser sysUser");
			  hql.append(" where sysUser.loginName = ?");
				
/*  72 */     if (userId.intValue() != Integer.parseInt("-1")) {
/*  73 */       hql.append(" and sysUser.userId = ?");
                System.out.println("hql==="+hql);//select 1 from SysUser sysUser where  sysUser.loginName = ? and sysUser.userId = ?
/*  74 */       values = new Object[] { loginName, userId };
//				for (int i = 0; i < values.length; i++) {  
//					   System.out.println("value["+i+"]="+values[i] + ", ");  
////					   System.out.println("value["+i+"]="+values[i].getClass().toString());//判断变量类型
//					} 
/*     */     }
/*  76 */     List list = findByHQL(hql.toString(), values);
/*  77 */     if (GlobalMethod.isNullorEmpty(list)) {
/*  78 */       return false;
/*     */     }
/*  80 */     return true;
/*     */   }

			@Override
//			public Boolean findByPhone(String userPhone) {
			public int findByPhone(String userPhone) {
				System.out.println("findByPhone");
				Object[] values = { userPhone };
				StringBuffer hql = new StringBuffer();

//				hql.append("select 1 from SysUser sysUser");
				hql.append("select sysUser.userId from SysUser sysUser");
				hql.append(" where sysUser.userPhone = ?");
				List list = findByHQL(hql.toString(), values);
//				SysUser sysUser=findByHQL(hql.toString(), values);
//				System.out.println("list:"+list);//list:[1]
				if (GlobalMethod.isNullorEmpty(list)) {
					return 0;
				}
				return  (int) list.get(0);
			}
			
/*     */   @Override
			public int findByEmail(String email) {
				System.out.println("findByPhone");
				Object[] values = { email };
				StringBuffer hql = new StringBuffer();
			
			//	hql.append("select 1 from SysUser sysUser");
				hql.append("select sysUser.userId from SysUser sysUser");
				hql.append(" where sysUser.email = ?");
				List list = findByHQL(hql.toString(), values);
			//	SysUser sysUser=findByHQL(hql.toString(), values);
			//	System.out.println("list:"+list);//list:[1]
				if (GlobalMethod.isNullorEmpty(list)) {
					return 0;
				}
				return  (int) list.get(0);
			}

///*     */   public SysUser checkUser(String loginName, String loginPwd, Session session)
			 public SysUser checkUser(String loginName, String loginPwd)
/*     */   { 
//			  List list = session.createQuery("select * from SysUser sysUser where sysUser.loginName like :loginName and sysUser.loginPwd=:loginPwd").setParameter("loginName",loginName).setParameter("loginPwd",loginPwd).list();
			
//			  Object[] values = { loginName,loginPwd };
//	          StringBuffer hql = new StringBuffer();
//	          hql.append("select 1 from SysUser sysUser");
//              hql.append(" where sysUser.mntDelete = ");
//              hql.append("'");
//	          hql.append("N");
//	          hql.append("'");
//	          hql.append(" and sysUser.loginName = ?");
//	          hql.append(" and sysUser.loginPwd = ?");
//	          List<SysUser> sysUserList = findByHQL(hql.toString(), values);
//	          System.out.println("sysUserList="+sysUserList);
//	          if (!GlobalMethod.isNullorEmpty(sysUserList))
//	          {
//	            return (SysUser)sysUserList.get(0);
//	          }
//	          return null;
//	         }
	
	          System.out.println("SysUserDaoImpl:loginName="+loginName+",loginPwd="+loginPwd);
	          DetachedCriteria criteria = DetachedCriteria.forClass(SysUser.class);
//	          Criteria criteria= session.createCriteria(SysUser.class);
//	          criteria.add(Restrictions.eq("sysUser.mntDelete", "N"));//问题出现在这里
	          criteria.add(Restrictions.eq("loginName", loginName));//问题出现在这里
	          criteria.add(Restrictions.eq("loginPwd", loginPwd));//问题出现在这里
			  System.out.println("---------------------------------------------");
//	          System.out.println("SysUserDaoImpl.java: criteria="+criteria);
	          List<SysUser> sysUserList = findByCriteria(criteria);
	          System.out.println("here...");
//			  List<SysUser> sysUserList = criteria.list();
	//			  System.out.println("sysUserList="+sysUserList);//sysUserList=[]
	          if (!GlobalMethod.isNullorEmpty(sysUserList))
	          {
	        	System.out.println("in....");
	            return (SysUser)sysUserList.get(0);
	          }
	          return null;
	        }
/*     */ 
/*     */   public void changePassword(Integer userId, String newPassword)
/*     */   {
/*  99 */     Object[] values = { newPassword, userId };
/* 100 */     StringBuffer hql = new StringBuffer();
/* 101 */     hql.append("update SysUser sysUser");
/* 102 */     hql.append(" set sysUser.loginPwd = ?");
/* 103 */     hql.append(" where sysUser.userId = ?");
/* 104 */     bulkUpdate(hql.toString(), values);
/*     */   }
         //用户管理-全部用户
			@Override
			public List<BoUser> findBoUsers() {
				StringBuffer hql = new StringBuffer();
				hql.append("select boUsers.userName,boUsers.userSex,boUsers.userPhone,boUsers.phoneType,boUsers.versionType,boUsers.signature,boUsers.city from BoUsers boUsers");
				List list = findByHQL(hql.toString());
				if (GlobalMethod.isNullorEmpty(list)) {
					return null;
				}
				return list;
			}
		//用户管理-登录验证码
			//查询
			@Override
			public List<BoUsersValidation> findValidations() {
				StringBuffer hql = new StringBuffer();
				hql.append("select bv.id,bv.userPhone,bv.verificationCode from BoUsersValidation bv");
				List list = findByHQL(hql.toString());
				if (GlobalMethod.isNullorEmpty(list)) {
					return null;
				}
				return list;
			}
			//添加
			@Override
			public String addValidation(String userPhone,String verificationCode) {
				String back="fail";
				Object[] values = { userPhone, verificationCode };
				StringBuffer hql = new StringBuffer();
				hql.append("insert into BoUsersValidation bv ");
				hql.append(" set bv.userPhone = ?");
				hql.append(" and bv.verificationCode = ?");
				int result=bulkUpdate(hql.toString(), values);
				if(result !=-1) {
					back="success";
				}
				return back;
			}
			//修改
			
			//删除
			//批量删除
			
			//导出Excel
//			@Override
//			public String toExcel() {
//				// TODO Auto-generated method stub
//				return null;
//			}
			
			@Override
			public String toExcel() {
				// TODO Auto-generated method stub
				return null;
			}
			
 	}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.system.SysUserDaoImpl
 * JD-Core Version:    0.6.2
 */