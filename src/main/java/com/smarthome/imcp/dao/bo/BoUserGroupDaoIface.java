package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaUserGroup;
import com.smarthome.imcp.dao.model.bo.BoUserGroup;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserGroupDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoUserGroup> getList(SearchCriteriaUserGroup paramSearchCriteriaUserGroup, Page paramPage);

  public abstract List<BoUserGroup> getAllList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoUserGroupDaoIface
 * JD-Core Version:    0.6.2
 */