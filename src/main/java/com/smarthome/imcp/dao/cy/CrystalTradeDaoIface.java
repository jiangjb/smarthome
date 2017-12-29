package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaTradeCrystal;
import com.smarthome.imcp.dao.model.cy.CrystalTrade;
import java.io.Serializable;
import java.util.List;

public abstract interface CrystalTradeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<CrystalTrade> getList(SearchCriteriaTradeCrystal paramSearchCriteriaTradeCrystal, Page paramPage);

  public abstract List<CrystalTrade> getList(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.CrystalTradeDaoIface
 * JD-Core Version:    0.6.2
 */