		 package com.smarthome.imcp.dao.impl.bo;

		 import com.smarthome.imcp.dao.CommonsDaoImpl;
		 import com.smarthome.imcp.dao.bo.UserDefinedButtonDaoIface;
		 import com.smarthome.imcp.dao.model.bo.UserDefinedButton;
		 import java.io.Serializable;
		 import org.springframework.stereotype.Service;

		 @Service("userDefinedButtonDao")
 		 public class UserDefinedButtonDaoImpl extends CommonsDaoImpl<UserDefinedButton, Serializable>
		 	implements UserDefinedButtonDaoIface<UserDefinedButton, Serializable>
		 {
			 public UserDefinedButtonDaoImpl()
			 {
				 super(UserDefinedButton.class);
			 }


		}

