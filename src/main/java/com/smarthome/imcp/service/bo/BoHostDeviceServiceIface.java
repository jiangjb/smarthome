package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoHostDevice;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoHostDeviceServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoHostDevice> getAllList();

  public abstract List<BoHostDevice> getListByUserCode(String paramString1, String paramString2, Boolean paramBoolean);

  public abstract List<BoHostDevice> getListByUserCode(String paramString, Boolean paramBoolean);

  public abstract List<BoHostDevice> getListByUserCode(String paramString);

  public abstract List<BoHostDevice> getListByUserCodes(String paramString1, String paramString2, Boolean paramBoolean);

  public abstract List<BoHostDevice> getListByUserCodess(String paramString, Boolean paramBoolean);

  public abstract List<BoHostDevice> getListBy(String paramString);

  public abstract List<BoHostDevice> getLists(String paramString1, String paramString2);

  public abstract List<BoHostDevice> getListBy(String paramString1, String paramString2);

  public abstract List<BoHostDevice> getListBys(String paramString);

  public abstract BoHostDevice findBy(String paramString1, String paramString2, Integer paramInteger);

  public abstract BoHostDevice findBydeviceAddress(String paramString);

  public abstract BoHostDevice findBy(String paramString);

  public abstract List<BoHostDevice> getByuserCode(String paramString);

  public abstract List<BoHostDevice> getDeviceByAddress(String paramString);

  public abstract BoHostDevice findBydeviceAddress(String paramString1, String paramString2);

  public abstract BoHostDevice findBydeviceAddress(String paramString1, String paramString2, String paramString3);

  public abstract BoHostDevice lock(String paramString1, String paramString2);

  public abstract List<BoHostDevice> getUserCode(String paramString);

  public abstract List<BoHostDevice> getroomCode(String paramString1, String paramString2);

  public abstract List<BoHostDevice> getroomCode(String paramString);

  public abstract List<BoHostDevice> get(String paramString1, String paramString2);

  public abstract List<BoHostDevice> get(String paramString1, String paramString2, Boolean paramBoolean);

  public abstract BoHostDevice findByroomCode(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract List<BoHostDevice> getByroomCode(String paramString);

  public abstract BoHostDevice controlEnclosure(String paramString1, String paramString2, String paramString3);

  public abstract BoHostDevice delete(T paramT);

  public abstract String addHostDevice(String deviceCode, String type);


  public abstract List<BoHostDevice> getAllHostD();

//  public abstract List<BoHostDevice> getAllDevices();

//  public abstract List<BoHostDevice> getAllRed();

  public abstract String addDeviceRed(String deviceAddress, String validationCode);


  public abstract List<BoHostDevice> findHostByUserPhone(String userPhone);

  public abstract BoHostDevice findHostByDevicecode(String deviceCode);

  public abstract List<BoHostDevice> findRedByAddr(String deviceAddress);



}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoHostDeviceServiceIface
 * JD-Core Version:    0.6.2
 */