package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoAlarm;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAlarmServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<T> getListByDeviceCode(String paramString, Integer paramInteger, Page paramPage);

  public abstract List<BoAlarm> getListByDeviceCode336(String paramString, Integer paramInteger, Page paramPage);

  public abstract List<BoAlarm> getListByDeviceCode(String paramString, int paramInt1, int paramInt2, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoAlarmServiceIface
 * JD-Core Version:    0.6.2
 */