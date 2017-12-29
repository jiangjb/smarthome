package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaOperate;
import com.smarthome.imcp.dao.model.system.SysOperate;
import java.io.Serializable;
import java.util.List;

public abstract interface SysOperateDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysOperate> getList(SearchCriteriaOperate paramSearchCriteriaOperate, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysOperateDaoIface
 * JD-Core Version:    0.6.2
 */