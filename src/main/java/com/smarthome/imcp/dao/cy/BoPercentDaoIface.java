package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaPercent;
import com.smarthome.imcp.dao.model.cy.BoPercent;
import java.io.Serializable;
import java.util.List;

public abstract interface BoPercentDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoPercent> getList(SearchCriteriaPercent paramSearchCriteriaPercent, Page paramPage);

  public abstract List<BoPercent> getAllList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.BoPercentDaoIface
 * JD-Core Version:    0.6.2
 */