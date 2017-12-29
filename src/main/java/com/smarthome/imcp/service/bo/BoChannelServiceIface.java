package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoChannel;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoChannelServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoChannel findBy(String paramString1, String paramString2, String paramString3);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoChannel delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoChannelServiceIface
 * JD-Core Version:    0.6.2
 */