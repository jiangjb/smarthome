package com.smarthome.imcp.dao.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.cy.SearchCriteriaCrystal;
import com.smarthome.imcp.dao.model.cy.BoCrystal;
import java.io.Serializable;
import java.util.List;

public abstract interface BoCrystalDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoCrystal> getList(SearchCriteriaCrystal paramSearchCriteriaCrystal, Page paramPage);

  public abstract int update(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract BoCrystal findByUserId(int paramInt);

  public abstract BoCrystal findByUserCode(String paramString);

  public abstract boolean transferCrystal(int paramInt1, int paramInt2, int paramInt3);

  public abstract List<BoCrystal> findByDeviceType(int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.cy.BoCrystalDaoIface
 * JD-Core Version:    0.6.2
 */