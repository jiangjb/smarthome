package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaDeviceType;
import com.smarthome.imcp.dao.model.bo.BoDeviceType;
import java.io.Serializable;
import java.util.List;

public abstract interface BoDeviceTypeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoDeviceType> getList(SearchCriteriaDeviceType paramSearchCriteriaDeviceType, Page paramPage);

  public abstract List<BoDeviceType> getList(SearchCriteriaDeviceType paramSearchCriteriaDeviceType);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoDeviceTypeDaoIface
 * JD-Core Version:    0.6.2
 */