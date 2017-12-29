package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoUsersValidation;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUsersValidationServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoUsersValidation> findByUserPhone(String paramString);

  public abstract List<BoUsersValidation> findPhoneValidations();

  public abstract String addValidation(String userPhone, String verificationCode);

  public abstract String toExcel(int num, List<T> list);
  
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUsersValidationServiceIface
 * JD-Core Version:    0.6.2
 */