package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDevice;
import com.smarthome.imcp.dao.model.bo.BoDevice;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDeviceDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoDevice> getList(SearchCriteriaDevice paramSearchCriteriaDevice);

  public abstract List<BoDevice> getList(SearchCriteriaDevice paramSearchCriteriaDevice, Page paramPage);

  public abstract List<BoDevice> getAllList();

  public abstract List<BoDevice> getListOnline(SearchCriteriaDevice paramSearchCriteriaDevice, Page paramPage);

//  public abstract BoDevice findByDCode(String deviceCode);

  public abstract List<BoDevice> getAllHostDevices();

  public abstract String addHostDevice(String deviceCode, String type);



  /*public abstract int getSum();*/

//  public abstract List<BoDevice> findByStatus(int status);

}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoDeviceDaoIface
 * JD-Core Version:    0.6.2
 */