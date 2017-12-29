package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoModel;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoModelServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoModel> getListBy(String paramString);

  public abstract List<BoModel> getListBys(String paramString);

  public abstract List<BoModel> getBy(String paramString1, String paramString2);

  public abstract List<BoModel> getBys(String paramString1, String paramString2);

  public abstract List<BoModel> getBy();

  public abstract BoModel find(String paramString1, String paramString2);

  public abstract BoModel findby(String paramString1, String paramString2);

  public abstract BoModel finds(String paramString1, String paramString2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoModel delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoModelServiceIface
 * JD-Core Version:    0.6.2
 */