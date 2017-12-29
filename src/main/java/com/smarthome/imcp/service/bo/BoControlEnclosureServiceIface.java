package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoControlEnclosure;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoControlEnclosureServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoControlEnclosure> controlEnclosure(String paramString1, String paramString2, Integer paramInteger);

  public abstract List<BoControlEnclosure> controlEnclosure(String paramString1, String paramString2, String paramString3);

  public abstract List<BoControlEnclosure> controlEnclosure(String paramString1, String paramString2);

  public abstract BoControlEnclosure controlEnclosures(String paramString1, String paramString2);

  public abstract BoControlEnclosure controlEnclosures(String paramString1, String paramString2, String paramString3);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoControlEnclosure delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoControlEnclosureServiceIface
 * JD-Core Version:    0.6.2
 */