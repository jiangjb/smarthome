/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.InfraredInfoDaoIface;
import com.smarthome.imcp.dao.model.bo.BoModelInfo;
import com.smarthome.imcp.dao.model.bo.InfraredInfo;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.InfraredInfoServiceIface;
/*    */ import java.io.Serializable;
		 import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("infraredInfoService")
/*    */ public class InfraredInfoServiceImpl extends AbstractBasicService<InfraredInfo, Serializable>
/*    */   implements InfraredInfoServiceIface<InfraredInfo, Serializable>
/*    */ {
			@Autowired
	        private InfraredInfoDaoIface<InfraredInfo, Serializable> infraredInfoDao;

			
			public InfraredInfo delete(InfraredInfo model)
			{
				if (chkUpdateValid(model)) {
					this.infraredInfoDao.delete(model);
				}
				return model;
			}
			
			public void deleteByKey(String idString)
			{
				if (chkDeleteValid(idString))
			       this.infraredInfoDao.deleteByKey(Integer.valueOf(Integer.parseInt(idString)));
			}
			
			public InfraredInfo save(InfraredInfo model)
			{
				if (chkSaveValid(model))
				{
					this.infraredInfoDao.save(model);
				}
				return model;
			}
			
			public InfraredInfo update(InfraredInfo model)
			{
				if (chkUpdateValid(model)) {
					this.infraredInfoDao.update(model);
				}
				return model;
			}
			
			public InfraredInfo findByKey(Serializable id)
			{
				InfraredInfo model = (InfraredInfo)this.infraredInfoDao.findById(id);
				return model;
			}

			@Override
			public List<InfraredInfo> getBy(String userCode, String modelId) {
				DetachedCriteria criteria = DetachedCriteria.forClass(InfraredInfo.class);
				criteria.createAlias("boUsers", "boUsers");
				criteria.add(Restrictions.eq("boUsers.userCode", userCode));
				criteria.createAlias("boModel", "boModel");
				criteria.add(Restrictions.eq("boModel.modelId", modelId));
				criteria.addOrder(Order.asc("id"));
				return this.infraredInfoDao.findByCriteria(criteria);
			}

   	
		}
