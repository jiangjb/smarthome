package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoInfraredLearnControlMap;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoInfraredLearnControlMapServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoInfraredLearnControlMap findBy(String paramString1, String paramString2, String paramString3);

  public abstract BoInfraredLearnControlMap findBy(String paramString1, String paramString2, String paramString3, String paramString4);

  public abstract List<BoInfraredLearnControlMap> infraredLearnControlMapList(String paramString1, String paramString2);

  public abstract List<BoInfraredLearnControlMap> getListBy(String paramString1, String paramString2, String paramString3);

  public abstract List<BoInfraredLearnControlMap> getListBy(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoInfraredLearnControlMap delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoInfraredLearnControlMapServiceIface
 * JD-Core Version:    0.6.2
 */