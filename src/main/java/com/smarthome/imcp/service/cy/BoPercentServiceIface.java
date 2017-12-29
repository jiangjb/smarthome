package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.BoPercent;
import java.io.Serializable;
import java.util.List;

public abstract interface BoPercentServiceIface
{
  public abstract List<BoPercent> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract boolean chkSaveValid(BoPercent paramBoPercent);

  public abstract BoPercent save(BoPercent paramBoPercent);

  public abstract BoPercent find();

  public abstract BoPercent find(double paramDouble);

  public abstract BoPercent findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(BoPercent paramBoPercent);

  public abstract BoPercent update(BoPercent paramBoPercent);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);

  public abstract List<BoPercent> getAllList();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.BoPercentServiceIface
 * JD-Core Version:    0.6.2
 */