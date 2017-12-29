package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoSensor;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoSensorServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoSensor> getListByUserCodes(String paramString1, String paramString2);

  public abstract List<BoSensor> get(String paramString);

  public abstract List<BoSensor> gets(String paramString1, String paramString2);

  public abstract List<BoSensor> getByModelId(String paramString);

  public abstract List<BoSensor> get(String paramString1, String paramString2);

  public abstract BoSensor find(String paramString1, String paramString2);

  public abstract BoSensor findBydeviceAddress(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract BoSensor findBydeviceAddress(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoSensor delete(T paramT);

  public abstract List<BoSensor> getSecurity(String paramString1, String paramString2);

  public abstract void Key();

  public abstract int getcountSensor(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoSensorServiceIface
 * JD-Core Version:    0.6.2
 */