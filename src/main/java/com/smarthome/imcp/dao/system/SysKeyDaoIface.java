package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaKey;
import com.smarthome.imcp.dao.model.system.SysKey;
import java.io.Serializable;
import java.util.List;

public abstract interface SysKeyDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysKey> getList(SearchCriteriaKey paramSearchCriteriaKey, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysKeyDaoIface
 * JD-Core Version:    0.6.2
 */