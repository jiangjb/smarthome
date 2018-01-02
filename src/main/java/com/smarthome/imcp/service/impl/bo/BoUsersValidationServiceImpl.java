/*    */ package com.smarthome.imcp.service.impl.bo;
/*    */ 
/*    */ import com.smarthome.imcp.dao.bo.BoUsersValidationDaoIface;
import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.dao.model.bo.BoUser;
/*    */ import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
/*    */ import com.smarthome.imcp.service.bo.BoUsersValidationServiceIface;
/*    */ import java.io.Serializable;
import java.util.HashMap;
/*    */ import java.util.List;
import java.util.Map;

/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("boUsersValidationService")
/*    */ public class BoUsersValidationServiceImpl extends AbstractBasicService<BoUsersValidation, Serializable>
/*    */   implements BoUsersValidationServiceIface<BoUsersValidation, Serializable>
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private BoUsersValidationDaoIface<BoUsersValidation, Serializable> boUsersValidationDao;
/*    */ 
			public BoUsersValidation findByKey(Serializable id)
/*     */   {
/* 156 */     BoUsersValidation model = (BoUsersValidation)this.boUsersValidationDao.findById(id);
/* 157 */     return model;
/*     */   }
/*    */   public BoUsersValidation findByUserPhone(String userPhone)
/*    */   {
/* 37 */     DetachedCriteria criteria = DetachedCriteria.forClass(BoUsersValidation.class);
/* 38 */     criteria.add(Restrictions.eq("userPhone", userPhone));
/* 39 */     List<BoUsersValidation> list = this.boUsersValidationDao.findByCriteria(criteria);
/* 40 */     if ((list == null) || (list.isEmpty())) {
/* 41 */       return null;
/*    */     }
/* 43 */     return list.get(0);
/*    */   }
/*    */
			@Override
			public List<BoUsersValidation> findPhoneValidations() {
				// TODO Auto-generated method stub
				return this.boUsersValidationDao.findPhoneValidatins();
			}
			@Override
			public String addValidation(String userPhone, String verificationCode) {
				return this.boUsersValidationDao.addValidation(userPhone,verificationCode);
			}
			//手机验证码导出Excel
			@Override
			public String toExcel(int num, List<BoUsersValidation> list) {
				return this.boUsersValidationDao.toExcel(num,list);
			}
			//new add
			public void deleteByKey(String id){
				if ((id == null) || ("".equals(id)))
					return;
				if (chkDeleteValid(id))
					this.boUsersValidationDao.deleteByKey(Integer.valueOf(id));
			}
			//new add
			 public BoUsersValidation save(BoUsersValidation model){
				 if (chkSaveValid(model)){
					 this.boUsersValidationDao.save(model);
				 }
				 return model;
			 }
		}
/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.impl.bo.BoUsersValidationServiceImpl
 * JD-Core Version:    0.6.2
 */