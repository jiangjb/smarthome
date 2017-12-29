package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoValidation;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoValidationServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoValidation findByUserPhone(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoValidation delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoValidationServiceIface
 * JD-Core Version:    0.6.2
 */