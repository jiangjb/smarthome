package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoUserGroup;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserGroupServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoUserGroup> getAllList();

  public abstract BoUserGroup save(BoUserGroup paramBoUserGroup, String paramString);

  public abstract BoUserGroup update(BoUserGroup paramBoUserGroup, String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUserGroupServiceIface
 * JD-Core Version:    0.6.2
 */