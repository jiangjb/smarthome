package com.smarthome.imcp.dao.bo;

import com.smarthome.imcp.dao.CommonsDaoIface;
import com.smarthome.imcp.dao.model.bo.BoIconLibrary;
import java.io.Serializable;
import java.util.List;

public abstract interface BoIconLibraryDaoIface<T, PK extends Serializable> extends CommonsDaoIface<T, PK>
{
  public abstract List<BoIconLibrary> getList();

  public abstract List<BoIconLibrary> getList1();

  public abstract List<BoIconLibrary> getList2();

  public abstract List<BoIconLibrary> getList3();
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.dao.bo.BoIconLibraryDaoIface
 * JD-Core Version:    0.6.2
 */