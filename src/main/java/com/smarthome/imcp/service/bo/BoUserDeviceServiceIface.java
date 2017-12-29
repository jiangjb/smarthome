package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoUserDevice;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserDeviceServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoUserDevice> getListByDeviceCode(String paramString);

  public abstract BoUserDevice findByUserDeviceCode(String paramString1, String paramString2);

  public abstract List<BoUserDevice> getListByUserCode(String paramString, Page paramPage);

  public abstract List<BoUserDevice> getListByUserId(int paramInt);

  public abstract BoUserDevice getMasterBinding(String paramString);

  public abstract Integer countByDeviceId(int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoUserDeviceServiceIface
 * JD-Core Version:    0.6.2
 */