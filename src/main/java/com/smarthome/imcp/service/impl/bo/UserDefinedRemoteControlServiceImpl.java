		 package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.UserDefinedRemoteControlDaoIface;
		 import com.smarthome.imcp.dao.model.bo.UserDefinedRemoteControl;
		 import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.UserDefinedRemoteControlServiceIface;
		 import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.criterion.DetachedCriteria;
		 import org.hibernate.criterion.Restrictions;
		 import org.springframework.beans.factory.annotation.Autowired;
		 import org.springframework.stereotype.Service;

		 @Service("userDefinedRemoteControlService")
		 public class UserDefinedRemoteControlServiceImpl extends AbstractBasicService<UserDefinedRemoteControl, Serializable>
		 	implements UserDefinedRemoteControlServiceIface<UserDefinedRemoteControl, Serializable>
		 {
			@Autowired
	        private UserDefinedRemoteControlDaoIface<UserDefinedRemoteControl, Serializable> userDefinedRemoteControlDao;
			
			public UserDefinedRemoteControl save(UserDefinedRemoteControl model){
				if (chkSaveValid(model)) {
					this.userDefinedRemoteControlDao.save(model);
				}
				return model;
			}
			
			public UserDefinedRemoteControl findByKey(Serializable id){
				return (UserDefinedRemoteControl)this.userDefinedRemoteControlDao.findById(id);
			}
			
			public UserDefinedRemoteControl update(UserDefinedRemoteControl model){
				if (chkUpdateValid(model)) {
					this.userDefinedRemoteControlDao.update(model);
				}
				return model;
			}
			
			public void deleteByKey(String id)
			{
				if ((id == null) || ("".equals(id)))
					return;
				if (chkDeleteValid(id))
					this.userDefinedRemoteControlDao.deleteByKey(Integer.valueOf(id));
			}
			
			@Override
			public UserDefinedRemoteControl findBy(int userId, int miniBlackId, String nickName, int roomId,String buttonNames) {
				DetachedCriteria criteria = DetachedCriteria.forClass(UserDefinedRemoteControl.class);
				criteria.createAlias("boUsers", "boUsers");
				criteria.add(Restrictions.eq("boUsers.userId", userId));
				criteria.add(Restrictions.eq("miniBlackId", miniBlackId));
				criteria.add(Restrictions.eq("nickName", nickName));
				criteria.createAlias("boRoom", "boRoom");
				criteria.add(Restrictions.eq("boRoom.roomId", roomId));
				criteria.add(Restrictions.eq("buttonNames", buttonNames));
				List<UserDefinedRemoteControl> list = this.userDefinedRemoteControlDao.findByCriteria(criteria);
				if ((list == null) || (list.isEmpty())) {
					return null;
				}
				return (UserDefinedRemoteControl)list.get(0);
			}

			@Override
			public List<UserDefinedRemoteControl> findByUserId(int userid) {
				DetachedCriteria criteria = DetachedCriteria.forClass(UserDefinedRemoteControl.class);
				criteria.createAlias("boUsers", "boUsers");
				criteria.add(Restrictions.eq("boUsers.userId", userid));
				List<UserDefinedRemoteControl> list = this.userDefinedRemoteControlDao.findByCriteria(criteria);
				return list;
			}

			@Override
			public List<UserDefinedRemoteControl> findByMiniId(Integer id) {
				DetachedCriteria criteria = DetachedCriteria.forClass(UserDefinedRemoteControl.class);
				criteria.add(Restrictions.eq("miniBlackId", id));
				List<UserDefinedRemoteControl> list = this.userDefinedRemoteControlDao.findByCriteria(criteria);
				return list;
			}

			@Override
			public List<UserDefinedRemoteControl> getByRoomUser(int userId, String rmCode) {
				DetachedCriteria criteria = DetachedCriteria.forClass(UserDefinedRemoteControl.class);
				criteria.createAlias("boUsers", "boUsers");
				criteria.add(Restrictions.eq("boUsers.userId", userId));
				criteria.createAlias("boRoom", "boRoom");
				criteria.add(Restrictions.eq("boRoom.roomCode", rmCode));
				List<UserDefinedRemoteControl> list = this.userDefinedRemoteControlDao.findByCriteria(criteria);
				return list;
			}

   	
		}
