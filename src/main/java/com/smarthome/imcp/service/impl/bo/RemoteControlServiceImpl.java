/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.RemoteControlDaoIface;
import com.smarthome.imcp.dao.model.bo.BoDevice;
import com.smarthome.imcp.dao.model.bo.MiniBlack;
import com.smarthome.imcp.dao.model.bo.RemoteControl;
/*    */ import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.RemoteControlServiceIface;
/*    */ import java.io.Serializable;
/*    */ import java.util.List;
		 import org.hibernate.Query;
/*    */ import org.hibernate.criterion.DetachedCriteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ 
/*    */ @Service("remoteControlService")
/*    */ public class RemoteControlServiceImpl extends AbstractBasicService<RemoteControl, Serializable>
/*    */   implements RemoteControlServiceIface<RemoteControl, Serializable>
/*    */ {
			@Autowired
	        private RemoteControlDaoIface<RemoteControl, Serializable> remoteControlDao;
			
			public RemoteControl save(RemoteControl model)
			{
				if (chkSaveValid(model))
				{
					this.remoteControlDao.save(model);
				}
				return model;
			}
			public RemoteControl update(RemoteControl model)
			{
				if (chkUpdateValid(model)) {
					this.remoteControlDao.update(model);
				}
				return model;
			}
			public void deleteByKey(String id)
			{
				if ((id == null) || ("".equals(id)))
					return;
				if (chkDeleteValid(id))
					this.remoteControlDao.deleteByKey(Integer.valueOf(id));
			}

			@Override
			public List<RemoteControl> findByUserId(int userid) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("userId", userid));
				List<RemoteControl> list = this.remoteControlDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list;
			}

			@Override
			public List<RemoteControl> findByMiniId(Integer valueOf) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("miniBlackId", valueOf));
				List<RemoteControl> list = this.remoteControlDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list;
			}

			@Override
			public RemoteControl findByUML(Integer userId, Integer miniBlackId, String m_label) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("userId", userId));
				criteria.add(Restrictions.eq("miniBlackId", miniBlackId));
				criteria.add(Restrictions.eq("label", m_label));
				List<RemoteControl> list = this.remoteControlDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return list.get(0);
			}

			@Override
			public List<RemoteControl> getBy(int userId, String modelId) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("userId", userId));
				criteria.add(Restrictions.eq("modelid", modelId));
				List<RemoteControl> list = this.remoteControlDao.findByCriteria(criteria);
				return list;
			}

		}
