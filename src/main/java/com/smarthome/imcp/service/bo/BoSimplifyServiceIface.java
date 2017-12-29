package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoSimplify;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoSimplifyServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoSimplify> getListByUserCode(String paramString, Boolean paramBoolean);

  public abstract List<BoSimplify> getListByUserCode(String paramString1, String paramString2, Page paramPage);

  public abstract List<BoSimplify> getListByDeviceCode(String paramString);

  public abstract BoSimplify findByBoSimplifyCode(String paramString1, String paramString2);

  public abstract List<BoSimplify> getListByUserCode(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoSimplify delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoSimplifyServiceIface
 * JD-Core Version:    0.6.2
 */