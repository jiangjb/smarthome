package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.common.Page;
import com.smarthome.imcp.dao.model.bo.BoAdvice;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoAdviceServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract List<BoAdvice> getListByUserCode(String paramString, Page paramPage);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoAdviceServiceIface
 * JD-Core Version:    0.6.2
 */