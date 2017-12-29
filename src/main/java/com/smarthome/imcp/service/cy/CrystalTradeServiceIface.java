package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.CrystalTrade;
import java.io.Serializable;
import java.util.List;

public abstract interface CrystalTradeServiceIface
{
  public abstract List<CrystalTrade> getList(Integer paramInteger1, Integer paramInteger2, Integer paramInteger3, Page paramPage);

  public abstract List<CrystalTrade> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract boolean chkSaveValid(CrystalTrade paramCrystalTrade);

  public abstract CrystalTrade save(CrystalTrade paramCrystalTrade);

  public abstract CrystalTrade findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(CrystalTrade paramCrystalTrade);

  public abstract CrystalTrade update(CrystalTrade paramCrystalTrade);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.CrystalTradeServiceIface
 * JD-Core Version:    0.6.2
 */