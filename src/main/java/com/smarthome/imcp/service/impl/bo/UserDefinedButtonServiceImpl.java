		 package com.smarthome.imcp.service.impl.bo;
 
		 import com.smarthome.imcp.dao.bo.UserDefinedButtonDaoIface;
		 import com.smarthome.imcp.dao.model.bo.UserDefinedButton;
		 import com.smarthome.imcp.service.AbstractBasicService;
		 import com.smarthome.imcp.service.bo.UserDefinedButtonServiceIface;
		 import java.io.Serializable;
		 import java.util.List;
		 import org.hibernate.criterion.DetachedCriteria;
		 import org.hibernate.criterion.Restrictions;
		 import org.springframework.beans.factory.annotation.Autowired;
		 import org.springframework.stereotype.Service;

		 @Service("userDefinedButtonService")
		 public class UserDefinedButtonServiceImpl extends AbstractBasicService<UserDefinedButton, Serializable>
		 	implements UserDefinedButtonServiceIface<UserDefinedButton, Serializable>
		 {
			@Autowired
	        private UserDefinedButtonDaoIface<UserDefinedButton, Serializable> userDefinedButtonDao;

			public UserDefinedButton save(UserDefinedButton model){
				if (chkSaveValid(model)) {
					this.userDefinedButtonDao.save(model);
				}
				return model;
			}

			@Override
			public List<UserDefinedButton> getListById(int id) {
				DetachedCriteria criteria = DetachedCriteria.forClass(UserDefinedButton.class);
				criteria.createAlias("userDefinedRemoteControl", "userDefinedRemoteControl");
				criteria.add(Restrictions.eq("userDefinedRemoteControl.id", id));
				return this.userDefinedButtonDao.findByCriteria(criteria);
			}
			
			public UserDefinedButton findByKey(Serializable id){
				return (UserDefinedButton)this.userDefinedButtonDao.findById(id);
			}
			
			public UserDefinedButton update(UserDefinedButton model)
			{
				if (chkUpdateValid(model)) {
					this.userDefinedButtonDao.update(model);
				}
				return model;
			}
			
			public void deleteByKey(String id)
			{
				if ((id == null) || ("".equals(id)))
					return;
				if (chkDeleteValid(id))
					this.userDefinedButtonDao.deleteByKey(Integer.valueOf(id));
			}
		}
