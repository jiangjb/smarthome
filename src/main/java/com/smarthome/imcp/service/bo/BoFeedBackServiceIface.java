package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoFeedBack;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;

public abstract interface BoFeedBackServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoFeedBack delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoFeedBackServiceIface
 * JD-Core Version:    0.6.2
 */