package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.bo.SearchCriteriaAdvice;
import com.smarthome.imcp.dao.model.bo.BoAdvice;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAdviceDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoAdvice> getListByUserCode(String paramString, Page paramPage);

  public abstract List<BoAdvice> getList(SearchCriteriaAdvice paramSearchCriteriaAdvice, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoAdviceDaoIface
 * JD-Core Version:    0.6.2
 */