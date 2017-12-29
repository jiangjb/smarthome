package com.smarthome.imcp.dao.system;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.criteria.system.SearchCriteriaMenu;
import com.smarthome.imcp.dao.model.system.SysMenu;
import java.io.Serializable;
import java.util.List;

public abstract interface SysMenuDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<SysMenu> getList(SearchCriteriaMenu paramSearchCriteriaMenu, Page paramPage);

  public abstract boolean isExistsByMenuCode(String paramString);

  public abstract boolean isExistsByMenuPcodeName(String paramString1, String paramString2);

  public abstract boolean isExistsByMenuCodePcodeName(String paramString1, String paramString2, String paramString3);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.system.SysMenuDaoIface
 * JD-Core Version:    0.6.2
 */