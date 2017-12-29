package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoUsersValidation;

import java.io.Serializable;
import java.util.List;

public abstract interface BoUsersValidationDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{

	List<BoUsersValidation> findPhoneValidatins();

	String addValidation(String userPhone, String verificationCode);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoUsersValidationDaoIface
 * JD-Core Version:    0.6.2
 */