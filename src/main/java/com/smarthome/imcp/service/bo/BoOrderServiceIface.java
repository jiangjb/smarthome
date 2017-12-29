package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoOrder;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoOrderServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoOrder findByOrderId(String paramString, Integer paramInteger);

  public abstract List<BoOrder> getByuserPhone(String paramString);

  public abstract List<BoOrder> getByuserCode(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoOrder delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoOrderServiceIface
 * JD-Core Version:    0.6.2
 */