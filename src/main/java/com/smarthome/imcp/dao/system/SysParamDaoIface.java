package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaParam;
import com.smarthome.imcp.dao.model.system.SysParam;
import java.io.Serializable;
import java.util.List;

public abstract interface SysParamDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysParam> getList(SearchCriteriaParam paramSearchCriteriaParam, Page paramPage);

  public abstract boolean isExistsByParamCodeName(String paramString1, String paramString2);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysParamDaoIface
 * JD-Core Version:    0.6.2
 */