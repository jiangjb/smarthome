package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.smarthome.SearchCriteriaUsers;
import com.smarthome.imcp.dao.model.bo.BoUsers;
import java.io.Serializable;
import java.util.List;

public abstract interface BoUserssDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoUsers> getAllList();

  public abstract List<BoUsers> getList(SearchCriteriaUsers paramSearchCriteriaUsers);

  public abstract List<BoUsers> getList(SearchCriteriaUsers paramSearchCriteriaUsers, Page paramPage);

  public abstract List<BoUsers> findAllBoUsers();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoUserssDaoIface
 * JD-Core Version:    0.6.2
 */