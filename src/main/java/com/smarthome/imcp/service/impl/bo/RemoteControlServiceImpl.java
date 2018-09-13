/*    */ package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.RemoteControlDaoIface;
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
			//8-27
			public RemoteControl findByKey(Serializable id){
				RemoteControl model = (RemoteControl)this.remoteControlDao.findById(id);
				return model;
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
			public RemoteControl findByUML(Integer userId, Integer miniBlackId, String nickName,String roomCode) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("userId", userId));
				criteria.add(Restrictions.eq("miniBlackId", miniBlackId));
				criteria.add(Restrictions.eq("nickName", nickName));
				criteria.add(Restrictions.eq("roomCode", roomCode));
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
			@Override
			public List<RemoteControl> getByRoomCode(String rmcode) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("roomCode", rmcode));
				return this.remoteControlDao.findByCriteria(criteria);
			}
			@Override
			public List<RemoteControl> getByRoomUser(int userId, String rmCode) {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				criteria.add(Restrictions.eq("userId", userId));
				criteria.add(Restrictions.eq("roomCode", rmCode));
				return this.remoteControlDao.findByCriteria(criteria);
			}
			@Override
			public List<RemoteControl> findAll() {
				DetachedCriteria criteria = DetachedCriteria.forClass(RemoteControl.class);
				return this.remoteControlDao.findByCriteria(criteria);
			}

		}
