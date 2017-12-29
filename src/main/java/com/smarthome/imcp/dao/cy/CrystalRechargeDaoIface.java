package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaRecharge;
import com.smarthome.imcp.dao.model.cy.CrystalRecharge;
import java.io.Serializable;
import java.util.List;

public abstract interface CrystalRechargeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract CrystalRecharge findByOrderNo(String paramString);

  public abstract List<CrystalRecharge> getList(SearchCriteriaRecharge paramSearchCriteriaRecharge, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.CrystalRechargeDaoIface
 * JD-Core Version:    0.6.2
 */