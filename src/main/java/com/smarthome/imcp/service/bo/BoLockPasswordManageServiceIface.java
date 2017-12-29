package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoLockPasswordManage;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoLockPasswordManageServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoLockPasswordManage> getLock(Integer paramInteger);

  public abstract List<BoLockPasswordManage> get(String paramString);

  public abstract List<BoLockPasswordManage> getLock(String paramString1, String paramString2, Integer paramInteger);

  public abstract BoLockPasswordManage findBylockAddress(String paramString1, String paramString2, Integer paramInteger);

  public abstract BoLockPasswordManage find(String paramString, Integer paramInteger);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoLockPasswordManage delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoLockPasswordManageServiceIface
 * JD-Core Version:    0.6.2
 */