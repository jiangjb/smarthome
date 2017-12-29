package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaFactory;
import com.smarthome.imcp.dao.model.bo.BoFactory;
import java.io.Serializable;
import java.util.List;

public abstract interface BoFactoryDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoFactory> getList(SearchCriteriaFactory paramSearchCriteriaFactory, Page paramPage);

  public abstract List<BoFactory> getAllList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoFactoryDaoIface
 * JD-Core Version:    0.6.2
 */