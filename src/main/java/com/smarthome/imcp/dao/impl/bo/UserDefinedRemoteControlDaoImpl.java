		 package com.smarthome.imcp.dao.impl.bo;

		 import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.UserDefinedRemoteControlDaoIface;
		 import com.smarthome.imcp.dao.model.bo.UserDefinedRemoteControl;
		 import java.io.Serializable;
		 import org.springframework.stereotype.Service;

		 @Service("userDefinedRemoteControlDao")
 		 public class UserDefinedRemoteControlDaoImpl extends CommonsDaoImpl<UserDefinedRemoteControl, Serializable>
		 	implements UserDefinedRemoteControlDaoIface<UserDefinedRemoteControl, Serializable>
		 {
			 public UserDefinedRemoteControlDaoImpl()
			 {
				 super(UserDefinedRemoteControl.class);
			 }


		}

