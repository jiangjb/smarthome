package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaDictCode;
import com.smarthome.imcp.dao.model.system.SysDictCode;
import java.io.Serializable;
import java.util.List;

public abstract interface SysDictCodeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysDictCode> getList(SearchCriteriaDictCode paramSearchCriteriaDictCode, Page paramPage);

  public abstract boolean isExistsByDictCode(String paramString);

  public abstract boolean isExistsByDictCodeName(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysDictCodeDaoIface
 * JD-Core Version:    0.6.2
 */