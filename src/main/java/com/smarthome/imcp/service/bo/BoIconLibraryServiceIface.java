package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoIconLibrary;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoIconLibraryServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoIconLibrary> getList();

  public abstract List<BoIconLibrary> getList1();

  public abstract List<BoIconLibrary> getList2();

  public abstract List<BoIconLibrary> getList3();

  public abstract BoIconLibrary findByURL(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoIconLibrary delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoIconLibraryServiceIface
 * JD-Core Version:    0.6.2
 */