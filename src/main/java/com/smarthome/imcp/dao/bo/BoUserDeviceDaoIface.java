package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserDevice;
import com.smarthome.imcp.dao.model.bo.BoUserDevice;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserDeviceDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoUserDevice> getList(SearchCriteriaUserDevice paramSearchCriteriaUserDevice, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoUserDeviceDaoIface
 * JD-Core Version:    0.6.2
 */