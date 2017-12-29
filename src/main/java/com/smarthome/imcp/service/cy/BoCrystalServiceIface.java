package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.BoCrystal;
import java.io.Serializable;
import java.util.List;

public abstract interface BoCrystalServiceIface
{
  public abstract void update(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract List<BoCrystal> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract BoCrystal save(BoCrystal paramBoCrystal);

  public abstract BoCrystal findByUserId(int paramInt);

  public abstract BoCrystal findByUserCode(String paramString);

  public abstract BoCrystal findByKey(Serializable paramSerializable);

  public abstract BoCrystal update(BoCrystal paramBoCrystal);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);

  public abstract boolean transferCrystal(int paramInt1, int paramInt2, int paramInt3);

  public abstract List<BoCrystal> findCrystalByDeviceType(int paramInt);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.BoCrystalServiceIface
 * JD-Core Version:    0.6.2
 */