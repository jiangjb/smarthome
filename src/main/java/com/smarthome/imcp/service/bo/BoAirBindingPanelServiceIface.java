package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoAirBindingPanel;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAirBindingPanelServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoAirBindingPanel> getPanelAll(String paramString1, String paramString2);

  public abstract List<BoAirBindingPanel> airBindingPanelList(String paramString1, String paramString2);

  public abstract BoAirBindingPanel findBydeviceAddress(String paramString1, String paramString2);

  public abstract BoAirBindingPanel findPanelBindingDeviceAddress(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoAirBindingPanel delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoAirBindingPanelServiceIface
 * JD-Core Version:    0.6.2
 */