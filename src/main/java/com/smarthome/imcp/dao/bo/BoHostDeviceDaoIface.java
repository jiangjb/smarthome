package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import java.io.Serializable;
import java.util.List;

public abstract interface BoHostDeviceDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoHostDevice> getByuserCode(String paramString);

  public abstract String addValidation(String deviceCode, String type);

  public abstract List<BoHostDevice> getAllHostD();

//  public abstract List<BoHostDevice> getAllDevices();

//  public abstract List<BoHostDevice> getAllRed();

  public abstract String addDeviceRed(String deviceAddress, String validationCode);

//  public abstract BoHostDevice findHostByUserPhone(String userPhone);

//  public abstract List<BoHostDevice> findHostByDevicecode(String deviceCode);

//  public abstract List<BoHostDevice> findRedByAddr(String deviceAddress);

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoHostDeviceDaoIface
 * JD-Core Version:    0.6.2
 */