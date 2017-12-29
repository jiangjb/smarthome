package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoInfraredButtons;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoInfraredButtonsServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoInfraredButtons> getListBy(String paramString1, String paramString2, String paramString3);

  public abstract List<BoInfraredButtons> getListBy(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

  public abstract List<BoInfraredButtons> getListBys(String paramString1, String paramString2);

  public abstract List<BoInfraredButtons> getListBy(String paramString1, String paramString2);

  public abstract BoInfraredButtons findBydeviceAddress(String paramString1, String paramString2, String paramString3);

  public abstract BoInfraredButtons findBydeviceAddress(String paramString1, String paramString2);

  public abstract BoInfraredButtons findBydeviceAddress(String paramString1, String paramString2, Integer paramInteger);

  public abstract List<BoInfraredButtons> getListBys(String paramString1, String paramString2, Integer paramInteger);

  public abstract BoInfraredButtons findBydeviceAddress(String paramString1, String paramString2, String paramString3, Integer paramInteger);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract void deleteButton(int paramInt1, int paramInt2);

  public abstract BoInfraredButtons delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoInfraredButtonsServiceIface
 * JD-Core Version:    0.6.2
 */