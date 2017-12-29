package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaLaunch;
import com.smarthome.imcp.dao.model.bo.BoLaunch;
import java.io.Serializable;
import java.util.List;

public abstract interface BoLaunchDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoLaunch> getList(SearchCriteriaLaunch paramSearchCriteriaLaunch);

  public abstract List<BoLaunch> getList(SearchCriteriaLaunch paramSearchCriteriaLaunch, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoLaunchDaoIface
 * JD-Core Version:    0.6.2
 */