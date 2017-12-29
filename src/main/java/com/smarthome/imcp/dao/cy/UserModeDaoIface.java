package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaUserMode;
import com.smarthome.imcp.dao.model.cy.UserMode;
import java.io.Serializable;
import java.util.List;

public abstract interface UserModeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<UserMode> getListByStatus(int paramInt);

  public abstract UserMode findByKey(Integer paramInteger1, Integer paramInteger2);

  public abstract List<UserMode> getList(SearchCriteriaUserMode paramSearchCriteriaUserMode, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.UserModeDaoIface
 * JD-Core Version:    0.6.2
 */