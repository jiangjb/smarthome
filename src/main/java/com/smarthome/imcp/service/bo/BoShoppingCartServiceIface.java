package com.smarthome.imcp.service.bo;

import com.smarthome.imcp.dao.model.bo.BoShoppingCart;
import com.smarthome.imcp.service.BasicServiceIface;
import java.io.Serializable;
import java.util.List;

public abstract interface BoShoppingCartServiceIface<T, PK extends Serializable> extends BasicServiceIface<T, PK>
{
  public abstract BoShoppingCart findById(Integer paramInteger, String paramString);

  public abstract List<BoShoppingCart> getListByUserCodes(String paramString);

  public abstract List<BoShoppingCart> getListByUserCode(String paramString);

  public abstract boolean chkDeleteValid(T paramT);

  public abstract BoShoppingCart delete(T paramT);
}

/* Location:           C:\Users\znhome\Desktop\bak\smarthome.IMCPlatform\WEB-INF\classes\
 * Qualified Name:     com.smarthome.imcp.service.bo.BoShoppingCartServiceIface
 * JD-Core Version:    0.6.2
 */