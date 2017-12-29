package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoLockVerdict;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoLockVerdictServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoLockVerdict> getAll();

  public abstract List<BoLockVerdict> getByDeviceCode(String paramString);

  public abstract BoLockVerdict findLock(String paramString);

  public abstract BoLockVerdict findLock(String paramString1, String paramString2);

  public abstract BoLockVerdict findLock(String paramString1, String paramString2, String paramString3);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoLockVerdict delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoLockVerdictServiceIface
 * JD-Core Version:    0.6.2
 */