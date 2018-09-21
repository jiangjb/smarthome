/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.AirconditionSleepDaoIface;
		 import com.smarthome.imcp.dao.model.bo.AirconditionSleep;
import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.dao.model.bo.BoModelInfo;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.AirconditionSleepServiceIface;
/*    */ import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("airconditionSleepService")
/*    */ public class AirconditionSleepServiceImpl extends AbstractBasicService<AirconditionSleep, Serializable>
/*    */   implements AirconditionSleepServiceIface<AirconditionSleep, Serializable>
/*    */ {
			@Autowired
	        private AirconditionSleepDaoIface<AirconditionSleep, Serializable> airconditionSleepDao;
			
			public AirconditionSleep save(AirconditionSleep model)
			{
				if (chkSaveValid(model))
				{
					this.airconditionSleepDao.save(model);
				}
				return model;
			}
			public AirconditionSleep update(AirconditionSleep model)
			{
				if (chkUpdateValid(model)) {
					this.airconditionSleepDao.update(model);
				}
				return model;
			}
			
			public AirconditionSleep findByKey(Serializable id)
			{
				AirconditionSleep model = (AirconditionSleep)this.airconditionSleepDao.findById(id);
				return model;
			}
			
			public void deleteByKey(String id)
			{
				if ((id == null) || ("".equals(id)))
					return;
				if (chkDeleteValid(id))
					this.airconditionSleepDao.deleteByKey(Integer.valueOf(id));
			}
			
			@Override
			public List<AirconditionSleep> findByrcid(int id) {
				DetachedCriteria criteria = DetachedCriteria.forClass(AirconditionSleep.class);
				criteria.createAlias("rcontrol", "rcontrol");
				criteria.add(Restrictions.eq("rcontrol.id", id));
				List list = this.airconditionSleepDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return (List<AirconditionSleep>)list;
			}
			
			@Override
			public void delete(AirconditionSleep model) {
				if (chkUpdateValid(model)) {
					this.airconditionSleepDao.delete(model);
				}
			}
   	
		}
