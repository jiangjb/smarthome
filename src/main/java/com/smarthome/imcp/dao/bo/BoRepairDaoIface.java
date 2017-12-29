package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaRepair;
import com.smarthome.imcp.dao.model.bo.BoRepair;
import java.io.Serializable;
import java.util.List;

public abstract interface BoRepairDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoRepair> getListByUserCode(String paramString, Page paramPage);

  public abstract List<BoRepair> getList(SearchCriteriaRepair paramSearchCriteriaRepair, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoRepairDaoIface
 * JD-Core Version:    0.6.2
 */