package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenuRole;
import com.smarthome.imcp.dao.model.system.SysMenuRole;
import java.io.Serializable;
import java.util.List;

public abstract interface SysMenuRoleDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysMenuRole> getList(SearchCriteriaMenuRole paramSearchCriteriaMenuRole, Page paramPage);

  public abstract boolean isExistsByMenuRoleName(String paramString);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysMenuRoleDaoIface
 * JD-Core Version:    0.6.2
 */