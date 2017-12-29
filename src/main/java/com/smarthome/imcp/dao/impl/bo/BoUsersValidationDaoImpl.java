/*    */ package com.smarthome.imcp.dao.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.CommonsDaoImpl;
/*    */ import com.smarthome.imcp.dao.bo.BoUsersValidationDaoIface;
import com.smarthome.imcp.dao.model.bo.BoUsers;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*    */ import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boUsersValidationDao")
/*    */ public class BoUsersValidationDaoImpl extends CommonsDaoImpl<BoUsersValidation, Serializable>
/*    */   implements BoUsersValidationDaoIface<BoUsersValidation, Serializable>
/*    */ {
/*    */   public BoUsersValidationDaoImpl()
/*    */   {
/* 20 */     super(BoUsersValidation.class);
/*    */   }
/*    */

			@Override
			public List<BoUsersValidation> findPhoneValidatins() {
//				StringBuffer hql = new StringBuffer();
//				hql.append("select buv.id,buv.userPhone,buv.verificationCode from BoUsersValidation buv");//可能有问题
//				List<BoUsersValidation> list=findByHQL(hql.toString());
//				if(list ==null) {
//					return null;
//				}
//				return list;
				DetachedCriteria criteria = DetachedCriteria.forClass(BoUsersValidation.class);
				return findByCriteria(criteria);
			}

			@Override
			public String addValidation(String userPhone, String verificationCode) {
				String back="fail";
				Object[] values = { userPhone, verificationCode };
				StringBuffer hql = new StringBuffer();
				hql.append("insert into BoUsersValidation buv ");
				hql.append(" set buv.userPhone = ?");
				hql.append(" and buv.verificationCode = ?");
				int result=bulkUpdate(hql.toString(), values);
				if(result !=-1) {
					back="success";
				}
				return back;
			}
		}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.impl.bo.BoUsersValidationDaoImpl
 * JD-Core Version:    0.6.2
 */