package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaDict;
import com.smarthome.imcp.dao.model.system.SysDict;
import java.io.Serializable;
import java.util.List;

public abstract interface SysDictDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysDict> getList(SearchCriteriaDict paramSearchCriteriaDict, Page paramPage);

  public abstract boolean isExistsByDictCodeName(String paramString1, String paramString2);

  public abstract boolean isLastOneByDictCode(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysDictDaoIface
 * JD-Core Version:    0.6.2
 */