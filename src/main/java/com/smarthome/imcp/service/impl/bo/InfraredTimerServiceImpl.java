/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.InfraredTimerDaoIface;
		 import com.smarthome.imcp.dao.model.bo.InfraredTimer;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.InfraredTimerServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
/*    */ import org.hibernate.criterion.DetachedCriteria;
		 import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("infraredTimerService")
/*    */ public class InfraredTimerServiceImpl extends AbstractBasicService<InfraredTimer, Serializable>
/*    */   implements InfraredTimerServiceIface<InfraredTimer, Serializable>
/*    */ {
			@Autowired
	        private InfraredTimerDaoIface<InfraredTimer, Serializable> infraredTimerDao;

			@Override
			public List<InfraredTimer> getBy(String userCode, String modelId) {
				DetachedCriteria criteria = DetachedCriteria.forClass(InfraredTimer.class);
				criteria.createAlias("boUsers", "boUsers");
				criteria.add(Restrictions.eq("boUsers.userCode", userCode));
				criteria.createAlias("boModel", "boModel");
				criteria.add(Restrictions.eq("boModel.modelId", modelId));
				criteria.addOrder(Order.asc("id"));
				return this.infraredTimerDao.findByCriteria(criteria);
			}
			
			public InfraredTimer delete(InfraredTimer model)
			{
				if (chkUpdateValid(model)) {
					this.infraredTimerDao.delete(model);
				}
			return model;
			}
			public InfraredTimer save(InfraredTimer model)
			{
				if (chkSaveValid(model))
				{
					this.infraredTimerDao.save(model);
				}
				return model;
			}
   	
		}
