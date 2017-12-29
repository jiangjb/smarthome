package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoAlarm;
import com.smarthome.imcp.dao.model.bo.BoDevice;
import com.smarthome.imcp.dao.model.bo.BoReport;

public abstract interface BoProcessServiceIface
{
  public abstract String[] getTude(String paramString);

  public abstract boolean doLogin(String paramString1, String paramString2);

  public abstract void doKeepAliveLost(String paramString1, String paramString2);

  public abstract void doKeepAlive(String paramString1, String paramString2);

  public abstract void doInitDevice();

  public abstract BoDevice findDevice(String paramString);

  public abstract void doSaveReport(BoReport paramBoReport);

  public abstract void doSaveAlarm(BoAlarm paramBoAlarm);

  public abstract void doUpdateDeviceWater(String paramString, int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoProcessServiceIface
 * JD-Core Version:    0.6.2
 */