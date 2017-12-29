package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.bo.BoDevice;
import com.smarthome.imcp.service.BasicServiceIface;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDeviceServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoDevice> getAllList();

  public abstract BoDevice findByCode(String paramString);

  public abstract void updateIP(Integer paramInteger, int paramInt, String paramString, String[] paramArrayOfString);

  public abstract void updateStatus(String paramString, int paramInt);

  public abstract void updateStatus(int paramInt);

  public abstract void updateWater(String paramString, int paramInt);

  public abstract void updateAllStatus(int paramInt);

  public abstract List<BoDevice> getListOnline(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract String addDevice(String deviceCode, String type);

  public abstract List<BoDevice> findByDCode(String deviceCode);

  public abstract List<BoDevice> getAllHostDevices();

  public abstract String addHostDevice(String deviceCode, String type);

 public abstract String saveExcel(int choiceTo, String filepath) throws Exception;

 public abstract List<BoDevice> findByStatus(int status);

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoDeviceServiceIface
 * JD-Core Version:    0.6.2
 */