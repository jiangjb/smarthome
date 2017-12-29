package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaParamCode;
import com.smarthome.imcp.dao.model.system.SysParamCode;
import java.io.Serializable;
import java.util.List;

public abstract interface SysParamCodeDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysParamCode> getList(SearchCriteriaParamCode paramSearchCriteriaParamCode, Page paramPage);

  public abstract boolean isExistsByParamCode(String paramString);

  public abstract boolean isExistsByParamCodeName(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysParamCodeDaoIface
 * JD-Core Version:    0.6.2
 */