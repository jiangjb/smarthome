package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoResendVerification;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoResendVerificationServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoResendVerification find(String paramString1, String paramString2, String paramString3);

  public abstract List<BoResendVerification> getAll();

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoResendVerification delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoResendVerificationServiceIface
 * JD-Core Version:    0.6.2
 */