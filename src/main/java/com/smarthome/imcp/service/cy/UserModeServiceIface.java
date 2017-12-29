package com.smarthome.imcp.service.cy;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import com.smarthome.imcp.dao.model.cy.UserMode;
import java.io.Serializable;
import java.util.List;

public abstract interface UserModeServiceIface
{
  public abstract List<UserMode> getListByStatus(int paramInt);

  public abstract UserMode findByKey(Integer paramInteger1, Integer paramInteger2);

  public abstract List<UserMode> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract boolean chkSaveValid(UserMode paramUserMode);

  public abstract UserMode save(UserMode paramUserMode);

  public abstract UserMode findByKey(Serializable paramSerializable);

  public abstract boolean chkUpdateValid(UserMode paramUserMode);

  public abstract UserMode update(UserMode paramUserMode);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.cy.UserModeServiceIface
 * JD-Core Version:    0.6.2
 */