package com.smarthome.imcp.service;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.criteria.SearchCriteria;
import java.io.Serializable;
import java.util.List;

public abstract interface BasicServiceIface<T, PK extends Serializable>
{
  public abstract boolean chkCriteriaValid(SearchCriteria paramSearchCriteria);

  public abstract List<T> getList(SearchCriteria paramSearchCriteria, Page paramPage);

  public abstract List<T> getList(SearchCriteria paramSearchCriteria);

  public abstract boolean chkSaveValid(T paramT);

  public abstract T save(T paramT);

  public abstract T findByKey(PK paramPK);

  public abstract boolean chkUpdateValid(T paramT);

  public abstract T update(T paramT);

  public abstract boolean chkDeleteValid(String paramString);

  public abstract void deleteByKey(String paramString);

  public abstract void deleteByKeys(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.BasicServiceIface
 * JD-Core Version:    0.6.2
 */